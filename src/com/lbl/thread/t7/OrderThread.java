package com.lbl.thread.t7;

public class OrderThread {
	private int signal;

	public synchronized void a() {
		while (signal != 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		signal++;
		System.out.println("a");
		notifyAll();
	}

	public synchronized void b() {
		while (signal != 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		signal++;
		System.out.println("b");
		notifyAll();
	}

	public synchronized void c() {
		while (signal != 2) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		signal = 0;
		System.out.println("c");
		notifyAll();
	}

	public static void main(String[] args) {
		OrderThread c = new OrderThread();

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