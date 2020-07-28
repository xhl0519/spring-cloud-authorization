package com.auth.util;

/**
 * Redis连接池工具
 *
 * @author xhl
 * @create 2020/7/19
 */

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * 获取Redis连接池工具类
 * @author Administrator
 *
 */
public class RedisUtil {

    private static JedisPool jedisPool = null; //Redis连接池
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    /**
     * 初始化Redis连接池
     */
    static {

        try {
            JedisPoolConfig config = new JedisPoolConfig();
            // 连接耗尽时是否阻塞，false报异常，true阻塞直到超时，默认true
            config.setBlockWhenExhausted(true);
            // 设置的逐出策略类名，默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
            // 是否启用pool的jmx管理功能，默认true
            config.setJmxEnabled(true);
            // 最大空闲连接数, 默认8个 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(8);
            // 最大连接数, 默认8个
            config.setMaxTotal(200);
            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(1000 * 100);
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
            config.setTestOnBorrow(true);
            String host = resourceBundle.getString("spring.redis.host");
            String password = resourceBundle.getString("spring.redis.password");
            int port = Integer.valueOf(resourceBundle.getString("spring.redis.port"));
            int database = Integer.valueOf(resourceBundle.getString("spring.redis.database"));
            int timeout = Integer.valueOf(resourceBundle.getString("spring.redis.timeout"));
            jedisPool = new JedisPool(config, host, port, timeout, password, database);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取Jedis实例
     *
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放Jedis
     * @param jedis
     */
    public static void close(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}