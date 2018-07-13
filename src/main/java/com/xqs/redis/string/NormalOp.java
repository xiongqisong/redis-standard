package com.xqs.redis.string;

import java.util.List;

import com.xqs.redis.jedis.JedisUtil;

import redis.clients.jedis.Jedis;

/**
 * String数据类型基础操作
 * 
 * @author ycr
 *
 */
public class NormalOp {
    public static Jedis jedisConn = JedisUtil.buildJedis();

    public static void main(String[] args) {
        // testSetrange();
        testMset();
    }

    public static void testSet() {
        String key = "name";
        String value = "xqs";
        String nxxx = "";
        String expx = "";
        int time = 60;
        set(key, value);
        get(key);

        String value2 = "zy";
        set(key, value2);// key已存在，观察key被覆盖
        get(key);
    }

    public static void testAppend() {
        String key = "name";
        String value = "xqs";
        set(key, value);
        get(key);
        String appendStr = "handsome";
        append(key, appendStr);
        get(key);
    }

    public static void testSetrange() {
        String key = "name";
        String value = "xqs123";
        set(key, value);
        get(key);
        strlen(key);

        // condition1：offset未越界，replaceStr未越界
        int offset = 3;
        String replaceStr = "   ";
        setrange(key, offset, replaceStr);// 6
        get(key);
        // 从下标3开始，后面的3个字符被替换为" "，value长度不变

        // condition2：offset越界，replaceStr无所谓越不越界
        offset = 6;
        replaceStr = "handsome";
        setrange(key, offset, replaceStr);// 14
        get(key);
        // 从下标6开始，该位置为value的尾部，replaceStr直接拼接在value的尾部

        // condition3：offset未越界，replaceStr越界
        offset = 11;
        replaceStr = "ABCD";
        setrange(key, offset, replaceStr);// 15
        get(key);
        // 从下标11开始，长度为4的字符串被替换为"ABCD"，此时value的长度会增加以容纳replaceStr超出当前长度的字符
    }

    public static void testMset() {
        String[] keysvalues = new String[] { "xqs", "25", "zy", "26" };
        String[] keys = new String[] { "xqs", "zy" };
        mset(keysvalues);
        mget(keys);
    }

    /**
     * set：插入key-value至缓存。 key存在，则覆盖；key不存在，则添加。
     * 
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        jedisConn.set(key, value);
    }

    public static void set(String key, String value, String nxxx, String expx, int time) {
        jedisConn.set(key, value, nxxx, expx, time);
    }

    /**
     * strlen：获取key对应的value的长度
     * 
     * @param key
     * @return
     */
    public static void strlen(String key) {
        System.out.println(jedisConn.strlen(key));
    }

    /**
     * append：在key对应的value的尾部追加字符。
     * 
     * @param key
     * @param appendStr
     */
    public static void append(String key, String appendStr) {
        jedisConn.append(key, appendStr);
    }

    /**
     * setrange：从offset位置开始，将长度为replaceStr的串替换为repalceStr。
     * 无论offset值是否超过value的长度，都将把replaceStr“完整地”放在offset开始的指定位置
     * 
     * @param key
     * @param offset
     * @param replaceStr
     */
    public static void setrange(String key, int offset, String replaceStr) {
        System.out.println(jedisConn.setrange(key, offset, replaceStr));
    }

    /**
     * mset：一次性的设置多个key-value，节省网络开销。注意参数形式与redis mset指令一致key value [keyn valuen]
     * 
     * @param keysvalues
     */
    public static void mset(String... keysvalues) {
        jedisConn.mset(keysvalues);
    }

    /**
     * get：根据key获取value
     * 
     * @param key
     */
    public static void get(String key) {
        System.out.println(jedisConn.get(key));
    }

    /**
     * mget：一次性的获取多个key的value。
     * 
     * @param keys
     */
    public static void mget(String... keys) {
        List<String> values = jedisConn.mget(keys);
        for (String value : values) {
            System.out.println(value);
        }
    }
}
