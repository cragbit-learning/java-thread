package com.sanjiv.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class CountDownLatchThread implements Runnable {

	private CountDownLatch latch;

	public CountDownLatchThread(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {

		System.out.println("Started task :" + Thread.currentThread().getName());

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();
		System.out.println("Completed task :" + Thread.currentThread().getName());
	}
}

public class CountDownLatchExample {

	public static void main(String[] args) {

		ExecutorService executors = Executors.newFixedThreadPool(2);
		CountDownLatch latch = new CountDownLatch(5);
		for (int i = 0; i < 5; i++) {
			executors.submit(new CountDownLatchThread(latch));
		}
		System.out.println("All Task submitted...!");
		executors.shutdown();
		try {
			executors.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All Task completed...!");
	}
}

class urlCrawler {

	List<String> urls = Arrays.asList("http://google.com", "http://yahoo.com", "http://mindtree.com",
			"http://happiestminds.com", "http://ibm.com");

}
