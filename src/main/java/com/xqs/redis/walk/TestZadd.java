package com.xqs.redis.walk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.xqs.redis.zset.NormalOp;

public class TestZadd {
	public static void main(String[] args) throws IOException {
		// 10000 600ms
		// 100000 4060ms 3689ms(先加载数据到内存)
		// 1000000 39609ms 38618ms
		// 10000000 
		File imeiFile = new File("F:\\悦享智能出行\\imei");
		File walkFile = new File("F:\\悦享智能出行\\walk");
		BufferedReader reader = null, reader2 = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(imeiFile)));
			reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(walkFile)));

			String line = null;
			String line2 = null;

			List<Integer> imeis = new ArrayList<>(1000000);
			List<Integer> walks = new ArrayList<>(1000000);
			while ((line = reader.readLine()) != null && (line2 = reader2.readLine()) != null) {
				Integer imei = Integer.valueOf(line);
				Integer walk = Integer.valueOf(line2);
				imeis.add(imei);
				walks.add(walk);
			}
			
			long start = System.currentTimeMillis();
			for(int i=0;i<1000000;i++) {
				NormalOp.zadd("yxzn", imeis.get(i).toString(), walks.get(i).doubleValue());
			}
			System.out.println("cost: " + (System.currentTimeMillis() - start) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				reader.close();
			if (reader2 != null)
				reader2.close();
		}

	}
}
