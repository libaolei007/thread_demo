package com.lbl.thread.t4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 
 * @description:自定义可重入的锁
 * @author: libl
 * @date: 2019年4月18日
 */

public class MyLock implements Lock {

	private boolean isLocked = false;
	private Thread lockBy = null;
	private int lockcount = 0;

	@Override
	public synchronized void lock() {
		Thread currentThread = Thread.currentThread();// 1
		while (isLocked && currentThread != lockBy) {// 2
			try {
				wait();// 3
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isLocked = true;// 4
		lockBy = currentThread;// 5
		lockcount++;// 6
	}

	@Override
	public synchronized void unlock() {
		if (lockBy == Thread.currentThread()) {// 7
			lockcount--;// 8
			if (lockcount == 0) {// 9
				notify();// 10
				isLocked = false;// 11
			}
		}

	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public Condition newCondition() {
		return null;
	}

}
