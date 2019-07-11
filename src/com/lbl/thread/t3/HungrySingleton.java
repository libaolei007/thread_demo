package com.lbl.thread.t3;

/**
 * 
 * @description: 单例问题-饿汉式
 * @author: libl
 * @date: 2019年4月13日
 * 
 */
public class HungrySingleton {
	// 构造方法私有化
	private HungrySingleton() {
	}

	private static HungrySingleton instance = new HungrySingleton();

	public static HungrySingleton getInstance() {
		return instance;
	}

	// 饿汉式没有线程安全问题
	public static void main(String[] args) {
		System.out.println(HungrySingleton.getInstance());
		System.out.println(HungrySingleton.getInstance());
		System.out.println(HungrySingleton.getInstance());
		System.out.println(HungrySingleton.getInstance());
	}

}
