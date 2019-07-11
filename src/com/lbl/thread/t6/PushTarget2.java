package com.lbl.thread.t6;

public class PushTarget2 implements Runnable {
	private Tmall2 tmall;

	public PushTarget2(Tmall2 tmall) {
		this.tmall = tmall;
	}

	@Override
	public void run() {
		while(true) {
			tmall.push();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
