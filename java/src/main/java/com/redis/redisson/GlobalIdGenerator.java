package com.redis.redisson;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.redisson.Redisson;
import org.redisson.RedissonScript;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author 春阳
 * @date 2021-03-29 16:59
 * 全局 Id 生成器
 */
public class GlobalIdGenerator {

    private static String luaScript="local function get_max_seq()\n" +
            "    local key = tostring(KEYS[1])\n" +
            "    local incr_amoutt = tonumber(KEYS[2])\n" +
            "    local seq = tostring(KEYS[3])\n" +
            "    local month_in_seconds = 24 * 60 * 60 * 30\n" +
            "    if (1 == redis.call('setnx', key, seq))\n" +
            "    then\n" +
            "        redis.call('expire', key, month_in_seconds)\n" +
            "        return seq\n" +
            "    else\n" +
            "        local prev_seq = redis.call('get', key)\n" +
            "        if (prev_seq < seq)\n" +
            "        then\n" +
            "            redis.call('set', key, seq)\n" +
            "            return seq\n" +
            "        else\n" +
            "            redis.call('incrby', key, incr_amoutt)\n" +
            "            return redis.call('get', key)\n" +
            "        end\n" +
            "    end\n" +
            "end\n" +
            "return get_max_seq()";

    private static RedissonClient redissonClient;

    private static String sha1;

    static {
        Config config=new Config();
        config.useSingleServer().setAddress("redis://192.168.25.128:6379").setPassword("1234");
        redissonClient= Redisson.create(config);

        sha1 = redissonClient.getScript().scriptLoad(luaScript);
    }


    public static String getNextSeq(String keyName, int incrby) {
        FastDateFormat seqDateFormat = FastDateFormat.getInstance("yyMMddHHmmssSSS");
        String seqDate = seqDateFormat.format(new Date());
        String candidateSeq = new StringBuilder(17).append(seqDate).append(RandomStringUtils.randomNumeric(2)).toString();
        List<Object> keys= Arrays.asList(keyName, incrby, candidateSeq);
        RedissonScript rScript=(RedissonScript) redissonClient.getScript();
        //这里遇到一个bug，默认情况下使用evalSha，不加Codec属性时，会报错。这个错误很神奇。花了3个小时才搞定。
        Long seqNext = rScript.evalSha(RScript.Mode.READ_ONLY, JsonJacksonCodec.INSTANCE, sha1, RScript.ReturnType.VALUE, keys);
        return candidateSeq + " ==> " +seqNext.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> System.out.println(GlobalIdGenerator.getNextSeq("LOG_ID", 1))).start();
        }
        Thread.sleep(5000);
        redissonClient.shutdown();
    }
}
