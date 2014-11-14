package com.java.thread;

import java.util.Collection;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {

	private int count;
	private final Lock lock = new ReentrantLock();

	private Condition isEmpty = lock.newCondition();

	private Condition isFull = lock.newCondition();

	public void isFull() throws InterruptedException {
		lock.lock();
		try {
			while (count == 10)
				isFull.await();

			count++;

			isFull.signalAll();
		} finally {
			lock.unlock();
		}

	}

	public void isEmpty() throws InterruptedException {
		System.out.println("inside is empty");
		lock.lock();
		try {
			while (count == 0)
				isEmpty.await();

			count--;
			System.out.println("is empty " + count);
			isEmpty.signalAll();
		} finally {
			lock.unlock();
		}

	}

	public static void main(String[] args) {
		final ConditionExample ce = new ConditionExample();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {

				try {

					while (true)
						ce.isEmpty();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					while (true)
						ce.isFull();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();

	}
}
