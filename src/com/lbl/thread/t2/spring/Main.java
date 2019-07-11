package com.lbl.thread.t2.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
		Demo7 bean = ac.getBean(Demo7.class);
		bean.a();
		bean.b();
	}
}
