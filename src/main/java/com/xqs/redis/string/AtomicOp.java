package com.xqs.redis.string;

import com.xqs.redis.jedis.JedisUtil;

import redis.clients.jedis.Jedis;

/**
 * String数据类型原子操作
 * 
 * @author ycr
 *
 */
public class AtomicOp {
    public static Jedis jedisConn = JedisUtil.buildJedis();

    public static void main(String[] args) {
        testSetWithNxxx();
    }

    public static void testSetnx() {
        String key = "name";
        String value = "xqs";
        setnx(key, value);
    }

    public static void testSetWithNxxx() {
        String key = "name";
        String value = "xqs";
        String nxxx = "NX";
        del(key);// 确保key不存在
        setWithNxxx(key, value, nxxx);// nxxx参数为"NX"，表明当key不存在时添加成功，输出OK；如果失败则输出null
        get(key);

        value="zy";
        nxxx = "XX";
        setWithNxxx(key, value, nxxx);// nxxx参数为"XX"，表明当key存在时添加成功，输出OK；如果失败则输出null
        get(key);
    }

    /**
     * setnx：不存在则更新
     * 
     * @param key
     * @param value
     * @return
     */
    public static void setnx(String key, String value) {
        Long result = jedisConn.setnx(key, value);
        if (result == 1) {
            System.out.println("set " + key + ":" + value + " success! ^_^");
        } else {
            System.out.println("set " + key + ":" + value + " fail!");
        }
    }

    /**
     * set携带NX|XX参数：参数为NX，则与setnx一致，参数为XX，则与setnx相反
     * 
     * @param key
     * @param value
     * @param nxxx
     */
    public static void setWithNxxx(String key, String value, String nxxx) {
        System.out.println(jedisConn.set(key, value, nxxx));
    }

    public static void get(String key) {
        System.out.println(jedisConn.get(key));
    }

    public static void del(String key) {
        Long result = jedisConn.del(key);
        if (result == 1) {
            System.out.println("del " + key + " success! ^_^");
        } else {
            System.out.println("del " + key + " fail!");
        }

    }
}
