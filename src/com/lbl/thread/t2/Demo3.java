package com.lbl.thread.t2;
/**
 * 
 * @description:创建线程的方式-使用匿名内部类
 * @author: libl  
 * @date: 2019年4月9日
 */
public class Demo3 {
	public static void main(String[] args) {
		
		new Thread(){
			public void run() {
				System.out.println("Thread线程运行了...");
			} ;
		}.start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Runnable线程运行了...");
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("...");
			}
		}) {
			public void run() {
				System.out.println("run被子类覆写...");
			};
		}.start();
		
	}
}
