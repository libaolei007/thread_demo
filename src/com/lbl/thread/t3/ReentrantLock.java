package com.lbl.thread.t3;

/**
 * 
 * @description: 重入锁 synchronized修饰方法锁的是对象。
 * @author: libl
 * @date: 2019年4月13日
 */
public class ReentrantLock {
	public synchronized void a() {
		System.out.println("a");
		b();
	}

	public synchronized void b() {
		System.out.println("b");
	}

	public synchronized void c() {
		System.out.println("c");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void d() {
		System.out.println("d");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		/**
		 * 我们发现a和b都被打印了，说明a()的锁和b()锁是同一个对象的同一把锁 这种现象就叫锁的重入，它避免了死锁。
		 */
		new Thread(new Runnable() {

			@Override
			public void run() {
				ReentrantLock rl = new ReentrantLock();
				rl.a();
			}

		}).start();

		/**
		 * 我们发现调用c()的时候不会调用d(),只有c()睡醒后才会调用d()。
		 * 说明这两个线程运行的时候其中一个会锁住rl1对象,另一个线程只能等待锁释放才能使用这个对象。
		 */

		ReentrantLock rl1 = new ReentrantLock();

		new Thread(new Runnable() {

			@Override
			public void run() {
				rl1.c();
			}

		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				rl1.d();
			}

		}).start();

	}
}
