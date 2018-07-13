package com.xqs.redis.jedis;

import redis.clients.jedis.Jedis;

public class JedisUtil {
    public static void main(String[] args) {
        Jedis jedis = JedisUtil.buildJedis();

    }

    // 默认连接localhost:6379
    public static Jedis buildJedis() {
        return buildJedis("localhost", 6379);
    }

    // 指定host
    public static Jedis buildJedis(String host) {
        return buildJedis(host, 6379);
    }

    // 指定port
    public static Jedis buildJedis(int port) {
        return buildJedis("localhost", port);
    }

    // 连接Redis并返回Jedis连接实例
    public static Jedis buildJedis(String host, int port) {
        Jedis jedis = new Jedis(host, port);
        if (healthCheck(jedis)) {
            return jedis;
        } else {
            throw new RuntimeException(String.format("can not connect redis on %s:%d", host, port));
        }
    }

    // 健康监测
    public static boolean healthCheck(Jedis jedisConn) {
        return "PONG".equals(jedisConn.ping());
    }

    public static void flushall(Jedis jedisConn) {
        jedisConn.flushAll();
    }
}
