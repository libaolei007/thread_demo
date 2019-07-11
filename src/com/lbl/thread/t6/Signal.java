package com.lbl.thread.t6;

/**
 * 
 * @description: 线程间的通信  
 * @author: libl
 * @date: 2019年4月27日
 */
public class Signal {
	private volatile int signal;

	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}

	public static void main(String[] args) {
		Signal s = new Signal();

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (s) {
					System.out.println("修改状态的线程执行...");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					s.setSignal(1);
					System.out.println("状态值修改成功...");
					s.notify();
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (s) {
					while (s.getSignal() != 1) {
						try {
							s.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("模拟代码执行...");
				}
			}
		}).start();

	}
}
