package com.lbl.thread.t4;

public class MainLock2 {
	public int value;

	MyLock2 myLock = new MyLock2();

	public int getNext() {
		myLock.lock();
		int a = value++;
		myLock.unlock();
		return a;
	}
	
	public void a() {
		myLock.lock();
		System.out.println("a");
		b();
		myLock.unlock();
	}

	public void b() {
		myLock.lock();
		System.out.println("b");
		myLock.unlock();
	}
	
	public static void main(String args[]) {
		MainLock2 mainLock = new MainLock2();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				mainLock.a();
			}
			
		}).start();
		
		
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ":" + mainLock.getNext());
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ":" + mainLock.getNext());
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ":" + mainLock.getNext());
				}
			}
		}).start();
	}
}
