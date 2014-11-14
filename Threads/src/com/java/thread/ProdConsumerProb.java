package com.java.thread;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

class Consumer implements Runnable {
	private ArrayList<Integer> list;

	public Consumer(ArrayList<Integer> list) {
		this.list = list;
	}

	@Override
	public void run() {

		while (true) {
			try {
				System.out.println("CONSUMING ");
				consume();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	public void consume() throws InterruptedException {
		synchronized (list) {
			while (list.isEmpty()) {
				list.wait();
			}

		}
		synchronized (list) {
			list.remove(0);
			list.notifyAll();

		}

	}

}

class Producer implements Runnable {
	private ArrayList<Integer> list;

	public Producer(ArrayList<Integer> list) {
		this.list = list;
	}

	@Override
	public void run() {

		for (int i = 0; i < 7; i++) {
			try {
				System.out.println("PRODUCING " + i);
				produce(i);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	public void produce(int i) throws InterruptedException {
		synchronized (list) {
			while (list.size() >= 7) {
				list.wait();
			}

		}
		synchronized (list) {
			list.add(i);
			list.notifyAll();

		}
	}

}

public class ProdConsumerProb {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();

		Thread p = new Thread(new Producer(list));
		Thread c = new Thread(new Consumer(list));
		p.start();
		c.start();

	}

}
