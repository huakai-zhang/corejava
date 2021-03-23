package com.spring.service.impl;

import cn.hutool.core.lang.Assert;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 春阳
 * @date 2021-03-23 18:47
 */
@Service
public class StockService {

    Logger logger = LoggerFactory.getLogger(StockService.class);

    /**
     * 不限库存
     */
    public static final long UNINITIALIZED_STOCK = -3L;

    /**
     * Redis 客户端
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 执行扣库存的脚本
     */
    public static final String STOCK_LUA;

    static {
        /**
         *
         * @desc 扣减库存Lua脚本
         * 库存（stock）-1：表示不限库存
         * 库存（stock）0：表示没有库存
         * 库存（stock）大于0：表示剩余库存
         *
         * @params 库存key
         * @return
         *   -3:库存未初始化
         *   -2:库存不足
         *   -1:不限库存
         *   大于等于0:剩余库存（扣减之后剩余的库存）
         *      redis缓存的库存(value)是-1表示不限库存，直接返回1
         */
        StringBuilder sb = new StringBuilder();
        sb.append("if (redis.call('exists', KEYS[1]) == 1) then\n");
        sb.append("local stock = tonumber(redis.call('get', KEYS[1]))\n");
        sb.append("local num = tonumber(ARGV[1])\n");
        sb.append("if (stock == -1) then\n");
        sb.append("return -1\n");
        sb.append("end\n");
        sb.append("if (stock >= num) then\n");
        sb.append("return redis.call('incrby', KEYS[1], 0 - num)\n");
        sb.append("end\n");
        sb.append("return -2\n");
        sb.append("end\n");
        sb.append("return -3\n");
        STOCK_LUA = sb.toString();
    }

    /**
     * @param key           库存key
     * @param expire        库存有效时间,单位秒
     * @param num           扣减数量
     * @param initStock     初始化库存数
     * @return -2:库存不足; -1:不限库存; 大于等于0:扣减库存之后的剩余库存
     */
    public long stock(String key, long expire, int num, int initStock) {
        long stock = stock(key, num);
        // 初始化库存
        if (stock == UNINITIALIZED_STOCK) {
            RLock redisLock = redissonClient.getLock(key);
            try {
                // 获取锁
                if (redisLock.tryLock()) {
                    // 双重验证，避免并发时重复回源到数据库
                    stock = stock(key, num);
                    if (stock == UNINITIALIZED_STOCK) {
                        // 获取初始化库存
                        // 将库存设置到redis
                        redisTemplate.opsForValue().set(key, initStock, expire, TimeUnit.SECONDS);
                        // 调一次扣库存的操作
                        stock = stock(key, num);
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                redisLock.unlock();
            }

        }
        return stock;
    }

    /**
     * 加库存(还原库存)
     *
     * @param key    库存key
     * @param num    库存数量
     * @return
     */
    public long addStock(String key, int num) {

        return addStock(key, null, num);
    }

    /**
     * 加库存
     *
     * @param key    库存key
     * @param expire 过期时间（秒）
     * @param num    库存数量
     * @return
     */
    public long addStock(String key, Long expire, int num) {
        boolean hasKey = redisTemplate.hasKey(key);
        // 判断key是否存在，存在就直接更新
        if (hasKey) {
            return redisTemplate.opsForValue().increment(key, num);
        }

        Assert.notNull(expire,"初始化库存失败，库存过期时间不能为null");
        RLock redisLock = redissonClient.getLock(key);
        try {
            if (redisLock.tryLock()) {
                // 获取到锁后再次判断一下是否有key
                hasKey = redisTemplate.hasKey(key);
                if (!hasKey) {
                    // 初始化库存
                    redisTemplate.opsForValue().set(key, num, expire, TimeUnit.SECONDS);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            redisLock.unlock();
        }

        return num;
    }

    /**
     * 获取库存
     *
     * @param key 库存key
     * @return -1:不限库存; 大于等于0:剩余库存
     */
    public int getStock(String key) {
        Integer stock = (Integer) redisTemplate.opsForValue().get(key);
        return stock == null ? -1 : stock;
    }

    /**
     * 扣库存
     *
     * @param key 库存key
     * @param num 扣减库存数量
     * @return 扣减之后剩余的库存【-3:库存未初始化; -2:库存不足; -1:不限库存; 大于等于0:扣减库存之后的剩余库存】
     */
    private Long stock(String key, int num) {
        // 脚本里的KEYS参数
        List<String> keys = new ArrayList<>();
        keys.add(key);
        // 脚本里的ARGV参数
        List<String> args = new ArrayList<>();
        args.add(Integer.toString(num));
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(Long.class);
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("stock.lua")));

        System.out.println(STOCK_LUA);
        return redisTemplate.execute(defaultRedisScript, keys, args);
    }
}
