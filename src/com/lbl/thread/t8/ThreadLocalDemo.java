package com.lbl.thread.t8;

public class ThreadLocalDemo {
	private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		};
	};

	public int get() {
		int value = threadLocal.get();
		value++;
		threadLocal.set(value);
		return value;
	}

	public void set(int value) {
		threadLocal.set(value);
	}

	public static void main(String[] args) {
		ThreadLocalDemo t1 = new ThreadLocalDemo();
		ThreadLocalDemo t2 = new ThreadLocalDemo();
		ThreadLocalDemo t3 = new ThreadLocalDemo();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println(Thread.currentThread().getName() +":"+ t1.get());
					try {
						Thread.sleep(100);
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
					System.out.println(Thread.currentThread().getName() +":"+ t2.get());
					try {
						Thread.sleep(100);
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
					System.out.println(Thread.currentThread().getName() +":"+ t3.get());
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