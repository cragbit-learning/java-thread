package com.sanjiv.thread;

import java.util.Scanner;

class WaitNotify {

	
	public void anotherProducer() {

		synchronized (this) {

			System.out.println("Another Producer Thread : " + Thread.currentThread().getName());
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Another Producer Resumed...!");
			notify();
		}
	}
	
	public void producer() {

		synchronized (this) {

			System.out.println("Producer Thread : " + Thread.currentThread().getName());
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Producer Resumed...!");
			notify();
		}
	}

	public void consumer() {
		synchronized (this) {
			System.out.println("Consumer Thread : " + Thread.currentThread().getName());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Please hit Enter Key to return...!");
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();
			notify();

			try {
				System.out.println("Sleeping for 3 second...!");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

public class WaitNotifyExample {

	public static void main(String[] args) {

		WaitNotify waitNotify = new WaitNotify();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				waitNotify.producer();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				waitNotify.consumer();
			}
		});
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				waitNotify.anotherProducer();
			}
		});

		t1.start();
		t2.start();
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
