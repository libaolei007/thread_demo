package com.lbl.thread.t8;
/**
 * 
 * @description: 线程的加塞 
 * @author: libl  
 * @date: 2019年5月25日
 */
public class ThreadJoin {
	public void a(Thread joinThread) {
		System.out.println("线程开始执行...");
		joinThread.start();
		try {
			joinThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程执行结束...");
	}
	
	public void b() {
		System.out.println("加塞线程开始执行...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("加塞线程执行结束...");
	}
	public static void main(String[] args) {
		ThreadJoin t = new ThreadJoin();
		
		Thread j= new Thread(new Runnable() {
			@Override
			public void run() {
				t.b();
			}
		});
		 new Thread(new Runnable() {
				@Override
				public void run() {
					t.a(j);
				}
			}).start();;	
		
	}
}
