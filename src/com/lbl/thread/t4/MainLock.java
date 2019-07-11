package com.lbl.thread.t4;

/**
 * 
 * @description: 测试自定义重入锁
 * @author: libl
 * @date: 2019年4月19日
 */
public class MainLock {

	MyLock lock = new MyLock();

	/*
	 * 1.进入函数a()，首先调用lock.lock()函数，进入步骤1：此时currentThread=thread0，执行步骤2：
	 * isLocked=false，currentThread=thread0，lockBy=null，不进入while循环内，分别执行步骤4,5,6：
	 * isLockd=true,lockBy=thread0,lockcount=1;
	 * 
	 * 2.进入函数b()，调用lock.lock()函数，进入步骤1：此时currentThread=thread0，执行步骤2：
	 * isLocked=true,currentThread=thread0,lockBy=thread0,不进入while循环内，分别执行步骤4,5,6：
	 * isLockd=true,lockBy=thread0,lockcount=2;
	 * 
	 * 3.执行函数b()中lock.unlock()函数，执行步骤7：lockBy=Thread.currentThread()=thread0 为true,
	 * 执行步骤8：lockcount=1,执行步骤9：lockcount为1,1==0 为false,退出函数
	 * 
	 * 4.执行函数a()中lock.unlock()函数，执行步骤7：lockBy=Thread.currentThread=thread0为true,
	 * 执行步骤8：lockcount=0，执行步骤9：lockcount为0,0=0为true,执行步骤10,11：
	 * 唤醒当前线程，并设置isLocked变量为false。
	 * 
	 */
	
	public void a() {
		lock.lock();   
		System.out.println("a");
		b();
		lock.unlock();
	}

	public void b() {
		lock.lock();
		System.out.println("b");
		lock.unlock();
	}

	public static void main(String[] args) {
		MainLock mainLock = new MainLock();
		new Thread(new Runnable() {

			@Override
			public void run() {
				mainLock.a();
			}

		}).start();
	}
}
