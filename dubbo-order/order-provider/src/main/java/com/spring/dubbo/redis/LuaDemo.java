package com.spring.dubbo.redis;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/11
 */
public class LuaDemo {
    public static void main(String[] args) throws Exception {
        Jedis jedis = RedisManager.getJedis();

        String lua = "local num=redis.call('incr',KEYS[1])\n" +
                "if tonumber(num)==1 then\n" +
                " redis.call('expire',KEYS[1],ARGV[1])\n" +
                " return 1\n" +
                "elseif tonumber(num)>tonumber(ARGV[2]) then\n" +
                " return 0\n" +
                "else\n" +
                " return 1\n" +
                "end";
        String luaSha = jedis.scriptLoad(lua);
        System.out.println(luaSha);// e3ecace37b338749e6cfb3b6901013836f396bdc
        List<String> keys = new ArrayList<>();
        keys.add("ip:limit:127.0.0.1");
        List<String> argvs = new ArrayList<>();
        argvs.add("6000");
        argvs.add("10");
        // Object obj = jedis.eval(lua, keys, argvs);
        // 先让 redis 缓存，redis重启后，缓存会消失
        // Object obj = jedis.evalsha("e3ecace37b338749e6cfb3b6901013836f396bdc", keys, argvs);
        Object obj = jedis.evalsha(luaSha, keys, argvs);
        System.out.println(obj);
    }
}
