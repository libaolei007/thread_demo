package com.lbl.thread.t2;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @description:创建线程的方式-定时器
 * @author: libl
 * @date: 2019年4月9日
 */
public class Demo5 {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				//实现定时任务
				System.out.println("timer执行了...");
			}
		}, 0, 1000);
	}
}
