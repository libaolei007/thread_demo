package com.lbl.thread.t5;

/**
 * 
 * @description:读-读能共存，读-写不能共存，写-写不能共存。
 * @author: libl
 * @date: 2019年4月26日
 */
public class MainRW {
	public static void main(String[] args) {
		RWLock rwl = new RWLock();
		rwl.put("key1", "value1");
		rwl.put("key2", "value2");
		rwl.put("key3", "value3");

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(rwl.get("key1"));
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(rwl.get("key2"));
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(rwl.get("key3"));
			}
		}).start();
	}
}
