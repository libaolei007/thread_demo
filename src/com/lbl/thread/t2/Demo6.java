package com.lbl.thread.t2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @description:创建线程的方式-线程池
 * @author: libl
 * @date: 2019年4月9日
 */
public class Demo6 {
	public static void main(String[] args) {
		// ExecutorService threadPool = Executors.newFixedThreadPool(10);//分配固定的线程数
		ExecutorService threadPool = Executors.newCachedThreadPool();// 根据需要自动建立相应的线程数
		for (int i = 0; i < 50; i++) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		threadPool.shutdown(); // 关闭线程池
	}
}
