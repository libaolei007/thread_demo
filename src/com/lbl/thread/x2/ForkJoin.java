package com.lbl.thread.x2;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoin extends RecursiveTask<Integer> {

	private int begin;
	private int end;

	public ForkJoin(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		System.out.println(Thread.currentThread().getName() + " ... ");
		
		int sum = 0;
		// 拆分任务
		if (end - begin <= 2) {
			// 计算
			for (int i = begin; i <= end; i++) {
				sum += i;
			}
		} else {
			// 拆分
			
			ForkJoin d1 = new ForkJoin(begin, (begin + end) / 2);
			ForkJoin d2 = new ForkJoin((begin + end)/2 + 1, end);
			
			// 执行任务
			d1.fork();
			d2.fork();
			
			Integer a = d1.join();
			Integer b = d2.join();
			
			sum = a + b;
		}

		return sum;
	}

	public static void main(String[] args) throws Exception {

		ForkJoinPool pool = new ForkJoinPool(3);

		Future<Integer> future = pool.submit(new ForkJoin(1, 1000000000));

		System.out.println("....");

		System.out.println("计算的值为：" + future.get());
	}

}
