package com.sanjiv.thread;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ReEntrantLock {
	
	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	
	public void increment() {
		for(int i=0; i<10000; i++) {
			count++;
		}
	}
	
	public void firstThread() {
		try{
			System.out.println("firstThread :" + Thread.currentThread().getName()); 
			System.out.println("Waiting...!!!");
			try {
				lock.lock();
				cond.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("firstThread Resumed...!!!");
			increment();
		}finally {
			lock.unlock();
		}
	}
	
	public void secondThread() {
		try{
			System.out.println("secondThread :" + Thread.currentThread().getName()); 
			lock.lock();
			increment();
			System.out.println("Hit Enter key to notify FirstThread...!");
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();
			cond.signal();
		}finally {
			lock.unlock();
		}
	}
	
	public void shutdown() {
		System.out.println("Total count : " + count);
	}	
}


public class ReEntrantLockExample {

	public static void main(String[] args) {
		
		ReEntrantLock reEntrantLock = new ReEntrantLock();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				reEntrantLock.firstThread();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				reEntrantLock.secondThread();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reEntrantLock.shutdown();
	}

}
