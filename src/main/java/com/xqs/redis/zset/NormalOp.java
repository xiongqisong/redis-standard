package com.xqs.redis.zset;

import java.util.Set;

import com.xqs.redis.jedis.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class NormalOp {
	private static final Jedis JEDIS = JedisUtil.buildJedis();

	public static void main(String[] args) {
		String key = "yxzn";
//		String member = "0";
//		Double score = 1D;
//		System.out.println(zadd(key, member, score) == 1);
		Set<Tuple> result = zrange(key, 0, -1);
		for(Tuple r:result) {
			System.out.println(r.getElement() + ": " + r.getScore());
		}
		
	}

	public static Long zadd(String key, String member, Double score) {
		return JEDIS.zadd(key, score, member);
	}

	public static Set<Tuple> zrange(String key, long start, long end) {
		return JEDIS.zrangeWithScores(key, start, end);
	}

}
