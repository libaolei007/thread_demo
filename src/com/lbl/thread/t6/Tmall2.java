package com.lbl.thread.t6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tmall2 {

	public final int MAX_COUNT = 10;

	Lock lock = new ReentrantLock();

	Condition p = lock.newCondition();
	Condition t = lock.newCondition();

	private int count;

	public void push() {
		lock.lock();
		while (count >= MAX_COUNT) {
			System.out.println(Thread.currentThread().getName() + "库存数量达到上限，生产者停止生产");
			try {
				p.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count++;
		System.out.println(Thread.currentThread().getName() + "生产者生产,当前库存为" + count);
		t.signal();
		lock.unlock();
	}

	public void take() {
		lock.lock();
		while (count <= 0) {
			System.out.println(Thread.currentThread().getName() + "库存数量为零，消费者停止消费");
			try {
				t.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count--;
		System.out.println(Thread.currentThread().getName() + "消费者消费,当前库存为" + count);
		p.signal();
		lock.unlock();
	}

}
