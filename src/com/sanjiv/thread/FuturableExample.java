package com.sanjiv.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class FutureDemo implements Callable<Integer> {

	private CountDownLatch latch;

	public FutureDemo(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println("started thread : " + Thread.currentThread().getName());
		Thread.sleep(3000);
		Random random = new Random();
		Integer value = random.nextInt(10);
		latch.countDown();
		System.out.println("finished thread : " + Thread.currentThread().getName());
		return value;
	}
}

public class FuturableExample {

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(5);
		ExecutorService executors = Executors.newFixedThreadPool(5);
		Future<Integer> future = null;
		List<Future<Integer>> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			future = executors.submit(new FutureDemo(latch));
			list.add(future);
		}
		executors.shutdown();

		try {
			try {
				for (Future<Integer> fu : list) {
					System.out.println("Return value = " + fu.get());
				}
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			latch.await();
			System.out.println("Main thread going to stop...!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
