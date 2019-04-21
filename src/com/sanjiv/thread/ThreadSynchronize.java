package com.sanjiv.thread;

class Runner {
	private int count = 0;

	private synchronized void count() {
		count++;
	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i<10000; i++) {
					count();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i<10000; i++) {
					count();
				}
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
		System.out.println("Total count : " + count); 
	}
}

public class ThreadSynchronize {
	public static void main(String[] args) {
		Runner runner = new Runner();
		runner.doWork();
	}
}
