package com.java.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class A {
	private int a;
	private String b;
}

class B {
	private int a;
	private String b;
}

public class DeadLock {

	public static void main(String[] args) {
		final A a = new A();
		final B b = new B();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (b) {

					System.out.println("current thread with lock b"
							+ Thread.currentThread());

				}

				synchronized (a) {

					System.out.println("current thread with lock a"
							+ Thread.currentThread());

				}

			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (a) {

					System.out.println("current thread with lock b"
							+ Thread.currentThread());

				}

				synchronized (b) {

					System.out.println("current thread with lock a"
							+ Thread.currentThread());

				}

			}

		});

		t1.start();
		t2.start();
	}

}
