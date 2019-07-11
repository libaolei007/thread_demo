package com.lbl.thread.t9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountDownLatchDemo {
	private int[] nums;

	public CountDownLatchDemo(int line) {
		nums = new int[line];
	}

	public void calc(String line, int index) {
		String[] nus = line.split(",");
		int total = 0;
		for (String num : nus) {
			total += Integer.valueOf(num);
		}
		nums[index] = total;
		System.out.println(Thread.currentThread().getName() + " 执行计算任务... " + line + " 结果为：" + total);
	}

	public void sum() {
		System.out.println("汇总开始......");
		int total = 0;
		for (int i = 0; i < nums.length; i++) {
			total += nums[i];
		}
		System.out.println("计算总和为......" + total);
	}

	public static void main(String[] args) {
		List<String> line = readFile();
		CountDownLatchDemo demo = new CountDownLatchDemo(line.size());
		for (int i = 0; i < line.size(); i++) {
			final int j = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					demo.calc(line.get(j), j);
				}
			}).start();
		}
		while (Thread.activeCount() > 1) {

		}
		demo.sum();
	}

	public static List<String> readFile() {
		List<String> contents = new ArrayList<>();
		BufferedReader bf = null;
		String line = null;
		String p1 = CountDownLatchDemo2.class.getResource("/").getPath().replaceFirst("/", "");
		String p2 = CountDownLatchDemo2.class.getPackage().getName().replace(".", "/");
		String p = p1 + p2 + File.separator + "nums.txt";
		try {
			bf = new BufferedReader(new FileReader(p));
			while ((line = bf.readLine()) != null) {
				contents.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bf != null) {
				try {
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return contents;
	}
}
