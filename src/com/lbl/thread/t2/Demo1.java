package com.lbl.thread.t2;

/**
 * 
 * @description: 创建线程的方式-继承Thread类
 * @author: libl
 * @date: 2019年4月9日
 */
public class Demo1 extends Thread {
	public Demo1(String name) {
		super(name);
	}

	@Override
	public void run() {
//		while(!interrupted()) {
//			System.out.println(getName() + "线程执行了...");
//		}
		for(int i=0;i<10;i++) {
			System.out.println(this.isInterrupted());
			System.out.println(getName() + "线程执行了...");
		}
	}

	public static void main(String[] args) {
		Demo1 d1 = new Demo1("First Thread");
		Demo1 d2 = new Demo1("Second Thread");
		/*
		 * java中线程两种类型：用户线程和守护线程  Thread.setDaemon(false)为用户线程，默认也为用户线程，
		 * Thread.setDaemon(true)设置为守护线程。
		 * 
		 * 区别：
		 * 1.主线程结束后用户线程还会继续运行，JVM存活。
		 * 2.如果没有用户线程，都是守护线程，那么JVM结束（随之而来的是所有的一切烟消云散，包括所有的守护线程）。
		 * 
		 */
		d1.setDaemon(true);
		d2.setDaemon(true);
//		d1.start();
		d2.start();
		d2.interrupt();
		System.out.println(d2.getName() + d2.isInterrupted()+"11111111111111");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
