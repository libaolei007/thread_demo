package com.lbl.thread.t9;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	public void method(Semaphore semaphore) {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" is run...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		semaphore.release();
	}
	
	
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<>();
		map.put(null, "123");
		SemaphoreDemo sd = new SemaphoreDemo();
		Semaphore s = new Semaphore(10);
		while(true) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					sd.method(s);
				}
			}).start();
		}
		
	}
}
