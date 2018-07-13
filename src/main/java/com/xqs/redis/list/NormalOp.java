package com.xqs.redis.list;

import java.util.List;

import com.xqs.redis.jedis.JedisUtil;

import redis.clients.jedis.Jedis;

/**
 * list数据类型是一个双向链表，主要指令：
 * lpush：从左侧放入元素
 * rpush：从右侧放入元素
 * lpop：从左侧弹出元素
 * rpop：从右侧弹出元素
 * lrange：取出list中一定范围内的元素
 * 这使得list可以实现很多种数据类型，比如：
 * lpush和lpop或者rpush和rpop组合---“先进后出的栈”
 * lpush和rpop或者rpush和rpop组合---“先进先出的队列”
 * lpush或rpush配合lrange---“存储元素的列表”
 * @author ycr
 *
 */
public class NormalOp {
    public static Jedis jedisConn = JedisUtil.buildJedis();

    public static void main(String[] args) {
        testPush();
    }

    public static void testPush() {
        JedisUtil.flushall(jedisConn);
        String key = "names";
        String[] strings = new String[] { "xqs", "zy" };
        lpush(key, strings);
        lrange(key, 0, -1);

        strings = new String[] { "gr", "gd" };
        rpush(key, strings);
        lrange(key, 0, -1);
    }

    public static void lpush(String key, String... strings) {
        jedisConn.lpush(key, strings);
    }

    public static void rpush(String key, String... strings) {
        jedisConn.rpush(key, strings);
    }

    public static void lrange(String key, int start, int end) {
        List<String> values = jedisConn.lrange(key, start, end);
        for (String value : values) {
            System.out.println(value);
        }
    }
}
