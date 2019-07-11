package com.lbl.thread.t2;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @description: 创建线程-lambda表达式
 * @author: libl
 * @date: 2019年4月10日
 */
public class Demo8 {
	public static void main(String[] args) {
		System.out.println(add(Arrays.asList(10, 20, 30, 40)));
	}

	/**
	 * parallelStream是并行执行流
	 * @param values
	 * @return
	 */
	public static int add(List<Integer> values) {
//		values.parallelStream().forEach(System.out :: println); //可以看出打印的是无序数字
		return values.parallelStream().mapToInt(a -> a).sum();
	}
}
