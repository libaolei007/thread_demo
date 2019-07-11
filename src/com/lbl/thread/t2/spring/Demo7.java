package com.lbl.thread.t2.spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 
 * @description:创建线程-利用spring注解实现线程
 * @author: libl
 * @date: 2019年4月10日
 */
@Service
public class Demo7 {

	@Async
	public void a() {
		while (true) {
			try {
				Thread.sleep(2000);
				System.out.println("a");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Async
	public void b() {
		while (true) {
			try {
				Thread.sleep(2000);
				System.out.println("b");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
