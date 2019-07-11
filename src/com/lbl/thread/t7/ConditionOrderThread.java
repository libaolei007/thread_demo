package com.lbl.thread.t7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionOrderThread {
	private int signal;
	Lock lock = new ReentrantLock();
	Condition a = lock.newCondition();
	Condition b = lock.newCondition();
	Condition c = lock.newCondition();

	public void a() {
		lock.lock();
		while (signal != 0) {
			try {
				a.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		signal++;
		System.out.println("a");
		b.signal();
		lock.unlock();
	}

	public void b() {
		lock.lock();
		while (signal != 1) {
			try {
				b.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		signal++;
		System.out.println("b");
		c.signal();
		lock.unlock();
	}

	public void c() {
		lock.lock();
		while (signal != 2) {
			try {
				c.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		signal = 0;
		System.out.println("c");
		a.signal();
		lock.unlock();
	}

	public static void main(String[] args) {
		ConditionOrderThread c = new ConditionOrderThread();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					c.a();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					c.b();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					c.c();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}