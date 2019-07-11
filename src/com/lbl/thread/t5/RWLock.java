package com.lbl.thread.t5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * @description: 读写锁：互斥锁和共享锁。写互斥，读共享，读写互斥。
 * @author: libl
 * @date: 2019年4月26日
 */
public class RWLock {
	private Map<String, Object> map = new HashMap<>();

	private ReadWriteLock rl = new ReentrantReadWriteLock();

	private Lock reader = rl.readLock();

	private Lock writer = rl.writeLock();

	public Object get(String key) {
		reader.lock();
		System.out.println(Thread.currentThread().getName() + "正在进行读操作...");
		try {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return map.get(key);
		} finally {
			reader.unlock();
			System.out.println(Thread.currentThread().getName() + "读操作执行完毕...");
		}
	}

	public void put(String key, String value) {
		writer.lock();
		System.out.println(Thread.currentThread().getName() + "正在进行写操作...");
		try {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
		} finally {
			writer.unlock();
			System.out.println(Thread.currentThread().getName() + "写操作执行完毕...");
		}
	}

}
