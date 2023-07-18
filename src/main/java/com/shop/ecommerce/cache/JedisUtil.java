package com.shop.ecommerce.cache;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.util.SafeEncoder;

public class JedisUtil {
    private final int expire = 60000;
    public Keys KEYS;
    public Strings STRINGS;

    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPoolWriper jedisPoolWriper) {
        this.jedisPool = jedisPoolWriper.getJedisPool();
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        jedis.close();
    }
    public void expire(String key) {
        expire(key, expire);
    }

    // *******************************************Keys*******************************************//
    public class Keys {
        public Keys(JedisUtil jedisUtil) {
        }

        public boolean exists(String key) {
            Jedis sjedis = getJedis();
            boolean exis = sjedis.exists(key);
            sjedis.close();
            return exis;
        }
    }

    // *******************************************Strings*******************************************//
    public class Strings {
        public Strings(JedisUtil jedisUtil) {
        }

        public String get(String key) {
            Jedis sjedis = getJedis();
            String value = sjedis.get(key);
            sjedis.close();
            return value;
        }

        public String set(String key, String value) {
            return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        public String set(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            String status = jedis.set(key, value);
            jedis.close();
            return status;
        }
    }
}