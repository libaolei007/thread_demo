package com.lbl.thread.t2;

/**
 * 
 * @description:创建线程的方式-实现Runnable接口-它作为线程的任务而存在
 * @author: libl
 * @date: 2019年4月9日
 */
public class Demo2 implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.println("线程执行了...");
		}
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new Demo2());
		thread.start();
	}
}
