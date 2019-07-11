package com.lbl.thread.t4;

/**
 * 
 * @description: volatile关键字保证变量可见性和一致性。
 * @author: libl
 * @date: 2019年4月15日
 */
public class Volatile {
	public volatile boolean run = false;

	public static void main(String[] args) {
		Volatile v = new Volatile();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(i);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				v.run = true;
			}

		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (!v.run) {

				}
				System.out.println("执行结束...");
			}

		}).start();
	}

}
