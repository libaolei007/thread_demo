package com.lbl.thread.t6;

public class TakeTarget2 implements Runnable {
	private Tmall2 tmall;

	public TakeTarget2(Tmall2 tmall) {
		this.tmall = tmall;
	}

	@Override
	public void run() {
		while (true) {
			tmall.take();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
