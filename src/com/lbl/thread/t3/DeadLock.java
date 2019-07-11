package com.lbl.thread.t3;

public class DeadLock {

	Object obj1 = new Object();
	Object obj2 = new Object();

	public void a() {
		synchronized (obj1) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (obj2) {
				System.out.println("a");
			}
		}
	}

	public void b() {
		synchronized (obj2) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (obj1) {
				System.out.println("b");
			}
		}
	}

	public static void main(String[] args) {
		DeadLock dl = new DeadLock();

		new Thread(new Runnable() {
			@Override
			public void run() {
				dl.a();
			}

		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				dl.b();
			}

		}).start();
	}
}
