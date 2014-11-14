package com.java.thread;

public class SampleThread {

	public static void main(String[] args) {

		Thread t2 = new Thread(new R2());
		t2.start();

		Thread t1 = new Thread(new R1());
		t1.start();

	}

	public static void runner() {
		for (int i = 0; i < 10; i++) {
			System.out.println("hello thread" + i);

		}

	}

}

 class R2 implements Runnable {
	@Override
	public void run() {
		try {
			SampleThread.runner();
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class R1 implements Runnable {
	@Override
	public void run() {
		try {
			SampleThread.runner();
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
