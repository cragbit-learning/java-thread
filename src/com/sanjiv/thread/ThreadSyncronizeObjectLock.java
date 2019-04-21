package com.sanjiv.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Worker {

	Object lock1 = new Object();
	Object lock2 = new Object();

	private Random random = new Random();
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();

	private void stageOne() {

		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("stageOne() ,Current Thread : " + Thread.currentThread().getName());
			list1.add(random.nextInt());
		}
	}

	private void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("stageTwo(), Current Thread : " + Thread.currentThread().getName());
			list2.add(random.nextInt());
		}
	}

	public void process() {
		for (int i = 0; i < 10; i++) {
			stageOne();
			stageTwo();
		}
	}

	public void arrange() {
		long start = System.currentTimeMillis();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				process();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				process();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("Total time taken: =" + (end - start));
		System.out.println("list1 count: " + list1.size());
		System.out.println("list1 count: " + list2.size());
	}

}

public class ThreadSyncronizeObjectLock {

	public static void main(String[] args) {
		Worker worker = new Worker();
		worker.arrange();
	}
}
