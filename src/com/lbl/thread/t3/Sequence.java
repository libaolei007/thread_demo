package com.lbl.thread.t3;

/**
 * @description: 线程安全问题-生成序列数字
 * @author: libl
 * @date: 2019年4月11日
 */
public class Sequence {

	private static int value;

	// 利用synchronized锁住，保证同一时刻只有一个线程进入代码块

	/**
	 * synchronized 放在普通方法上，内置锁就是当前类的实例
	 * 
	 * @return
	 */
	public synchronized int getNext() {
		return value++;
	}

	/**
	 * synchronized 修饰静态方法，内置锁就是当前Class字节码文件
	 * 
	 * @return
	 */
	public static synchronized int getPrevious() {
		return value--;
	}

	/**
	 * synchronized 修饰代码块
	 * 
	 * @return
	 */
	public static int sychronizedCode() {
		synchronized (Sequence.class) {
			return 0;
		}
	}

	/**
	 * 1.多线程环境下2.多个线程共享一个资源3.对资源进行非原子性操作
	 */
	public static void main(String[] args) {
		Sequence s = new Sequence();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println(Thread.currentThread().getName() + "：" + s.getNext());
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
					System.out.println(Thread.currentThread().getName() + "：" + s.getNext());
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
					System.out.println(Thread.currentThread().getName() + "：" + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
