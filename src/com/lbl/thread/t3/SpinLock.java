package com.lbl.thread.t3;

import java.util.Random;
/**
 * 
 * @description: 自旋锁-线程结束打印一句话
 * @author: libl
 * @date: 2019年4月15日
 */
public class SpinLock {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "线程开始了...");
				try {
					Thread.sleep(new Random().nextInt(2000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "线程结束了...");
			}

		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "线程开始了...");
				try {
					Thread.sleep(new Random().nextInt(2000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "线程结束了...");
			}

		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "线程开始了...");
				try {
					Thread.sleep(new Random().nextInt(2000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "线程结束了...");
			}

		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "线程开始了...");
				try {
					Thread.sleep(new Random().nextInt(2000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "线程结束了...");
			}

		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "线程开始了...");
				try {
					Thread.sleep(new Random().nextInt(2000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "线程结束了...");
			}

		}).start();
		
		while(Thread.activeCount() !=1) {
			//自旋
		}

		System.out.println("线程全部结束");

	}
}
