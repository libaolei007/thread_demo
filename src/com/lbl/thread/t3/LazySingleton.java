package com.lbl.thread.t3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @description:单例模式-懒汉式
 * @author: libl
 * @date: 2019年4月13日
 */
public class LazySingleton {
	private LazySingleton() {
	}

	// 使用volatile可以避免线程安全性问题
	private static volatile LazySingleton instance;

	/**
	 * 双重检查加锁 避免了使用同步方法性能损耗问题
	 * 
	 * @return
	 */
	public static LazySingleton getInstance() {
		if (instance == null) {
			synchronized (LazySingleton.class) {
				if (instance == null) {
					instance = new LazySingleton(); // 因为指令重排序问题，仍然不能避免线程安全性问题
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 20; i++) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "：" + LazySingleton.getInstance());
				}
			});
		}
		threadPool.shutdown();
	}
}
