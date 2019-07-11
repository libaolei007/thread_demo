package com.lbl.thread.t6;

public class Main {
	public static void main(String[] args) {
		/*
		 * Tmall tmall = new Tmall(); 
		 * PushTarget push = new PushTarget(tmall);
		 * TakeTarget take = new TakeTarget(tmall);
		 */

		Tmall2 tmall = new Tmall2();
		PushTarget2 push = new PushTarget2(tmall);
		TakeTarget2 take = new TakeTarget2(tmall);

		new Thread(push).start();
		new Thread(push).start();
		new Thread(push).start();
		new Thread(push).start();
		new Thread(push).start();
		new Thread(push).start();

		new Thread(take).start();
		new Thread(take).start();
		new Thread(take).start();
	}
}
