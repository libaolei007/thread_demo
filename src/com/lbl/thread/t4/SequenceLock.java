package com.lbl.thread.t4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @description: lock保证线程的安全性
 * @author: libl
 * @date: 2019年4月18日
 */
public class SequenceLock {
	private int value;
	private Lock lock = new ReentrantLock();

	public int getNext() {
		lock.lock();
		int a = value++;
		lock.unlock();
		return a;
	}

	public static void main(String[] args) {
		SequenceLock sl = new SequenceLock();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println(Thread.currentThread().getName() + "：" + sl.getNext());
					try {
						Thread.sleep(50);
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
					System.out.println(Thread.currentThread().getName() + "：" + sl.getNext());
					try {
						Thread.sleep(50);
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
					System.out.println(Thread.currentThread().getName() + "：" + sl.getNext());
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
