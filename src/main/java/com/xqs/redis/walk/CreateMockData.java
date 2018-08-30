package com.xqs.redis.walk;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class CreateMockData {
	private static final byte[] LINE_SEPARATOR = System.getProperty("line.separator").getBytes();

	public static void main(String[] args) {
		Integer number = 1000000;
		createImei(number);
		createWalk(number);
		System.out.println("finish");
	}

	public static void createImei(Integer number) {
		File imei = new File("F:\\悦享智能出行\\imei");
		try (FileOutputStream os = new FileOutputStream(imei)) {
			for (int i = 0; i < number; i++) {
				os.write(String.valueOf(i).getBytes());
				os.write(LINE_SEPARATOR);
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createWalk(Integer number) {
		File walk = new File("F:\\悦享智能出行\\walk");
		Random random = new Random();
		try (FileOutputStream os = new FileOutputStream(walk)) {
			for (int i = 0; i < number; i++) {
				os.write(String.valueOf(random.nextInt(50000)).getBytes());
				os.write(LINE_SEPARATOR);
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
