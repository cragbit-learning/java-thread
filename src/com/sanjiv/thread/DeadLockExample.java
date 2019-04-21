package com.sanjiv.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class DeadLock {

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	public void firstThread(Account acc1, Account acc2) {
		Random randon = new Random();
		accquireLock(lock1, lock2);
		try {
			for (int i = 0; i < 1000; i++) {
				transfer(acc1, acc2, randon.nextInt(70));
			}
		} finally {
			lock1.unlock();
			lock2.unlock();
		}
	}

	public void secondThread(Account acc2, Account acc1) {
		Random randon = new Random();
		accquireLock(lock2, lock1);
		try {
			for (int i = 0; i < 1000; i++) {
				transfer(acc2, acc1, randon.nextInt(70));
			}
		} finally {
			lock2.unlock();
			lock1.unlock();
		}
	}

	public void accquireLock(Lock firstLock, Lock secondLock) {

		while (true) {
			boolean gotLock1 = false;
			boolean gotLock2 = false;
			try {
				gotLock1 = firstLock.tryLock();
				gotLock2 = secondLock.tryLock();

			} finally {
				if (gotLock1 && gotLock2) {
					return;
				}
				if (gotLock1) {
					firstLock.unlock();
				}
				if (gotLock2) {
					secondLock.unlock();
				}
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void transfer(Account fromAccount, Account toAccount, Integer amount) {

		fromAccount.setBalance(fromAccount.getBalance() - amount);
		toAccount.setBalance(toAccount.getBalance() + amount);
	}

	public void balance(Account acc1, Account acc2) {

		System.out.println("Account1 Balance : " + acc1.getBalance());
		System.out.println("Account2 Balance : " + acc2.getBalance());
		System.out.println("Total Balance : " + (acc1.getBalance() + acc2.getBalance()));
	}

}

public class DeadLockExample {

	public static void main(String[] args) {
		DeadLock deadLock = new DeadLock();

		Account acc1 = new Account(1001);
		Account acc2 = new Account(2001);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				deadLock.firstThread(acc1, acc2);
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				deadLock.secondThread(acc2, acc1);
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
		deadLock.balance(acc1, acc2);
	}

}
