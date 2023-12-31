package com.elaina.register;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisLinkPool {
    private static final JedisPool jedisPool;
    static {
        //配置连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //最大连接
        poolConfig.setMaxTotal(16);
        //最大空闲连接
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(4);
        poolConfig.setMaxWaitMillis(3000);
        // 创建连接对象
        jedisPool = new JedisPool(
                poolConfig,
                "localhost",
                6379,
                1000,
                "redis");
    }
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
