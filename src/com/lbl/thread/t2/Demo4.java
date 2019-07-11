package com.lbl.thread.t2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 
 * @description:创建线程的方式-带返回值的线程-实现Callable接口
 * @author: libl
 * @date: 2019年4月9日
 */
public class Demo4 implements Callable<Integer> {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Demo4 d = new Demo4();
		FutureTask<Integer> task = new FutureTask<>(d);
		Thread thread = new Thread(task);
		thread.start();
		System.out.println("我先干点别的...");
		System.out.println("计算出的结果为：" + task.get());
	}

	@Override
	public Integer call() throws Exception {
		System.out.println("正在进行紧张计算...");
		Thread.sleep(2000);
		return 1;
	}

}
