package com.lbl.thread.t1;
/**
 * 
 * @description:线程的基本认识
 * @author: libl  
 * @date: 2019年4月9日
 */
public class NewThread implements Runnable {

	@Override
	public synchronized void run() {
		while (true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("自定义线程...");
		}
	}

	public static void main(String[] args) {
		NewThread n = new NewThread();
		Thread thread = new Thread(n);
		thread.start();
		while (true) {
			synchronized(n) {
				System.out.println("main主方法...");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				n.notifyAll();
			}
			
		}
	}

}
