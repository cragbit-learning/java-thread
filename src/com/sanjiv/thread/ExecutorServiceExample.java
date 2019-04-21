package com.sanjiv.thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class Task implements Runnable {
	
	private CountDownLatch latch;
	private Integer delay;
	private Semaphore semaphore;
	private BlockingQueue<Integer> queue;
	
	public Task(CountDownLatch latch, Semaphore semaphore, BlockingQueue<Integer> queue, Integer dellay) {
		this.latch = latch;
		this.semaphore = semaphore;
		this.queue = queue;
		this.delay = dellay;
	}

	@Override
	public void run() {
		while(true) {
			System.out.println("Thread " + Thread.currentThread().getName() + " trying to acquire permits.");
			try {
				semaphore.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println("Thread " + Thread.currentThread().getName() + " got permits.");
			System.out.println("Thread " + Thread.currentThread().getName() + " started working");
			
			try {
				System.out.println("Queue Size : " + queue.size());
				if(queue.isEmpty()) { 
					System.out.println("getting Killed thread : " + Thread.currentThread()); 
					Thread.currentThread().interrupt();
					return;
				}
				System.out.println("Removed Element from Queue : " + queue.take());
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				System.out.println("Thread " + Thread.currentThread().getName() + " has finished...!");
				semaphore.release();
				latch.countDown();
			}
		}
	}

}

public class ExecutorServiceExample {

	public static void main(String[] args) {

		System.out.println("Trying to find out availabe core...!");

		Integer nThread = Runtime.getRuntime().availableProcessors();

		System.out.println("Found availabe core = " + nThread);
		/*
		 * Trying to open twice the size of available core Because It's HTTP/Network
		 * call which can delay response Otherwise leave as same as available core in
		 * case of CPU intensive
		 */

		Integer executorThread = nThread * 2;
		
		Semaphore semaphore = new Semaphore(5);
		
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(executorThread);

		/*
		 * This is producer thread which keep on pushing data to BlockingQueue.
		 */
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Random random = new Random();
				Integer value = 0;
				while(value < 100) {
					try {
						//queue.put(random.nextInt(100));
						queue.put(value++);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t1.start();
		
		/*
		 * When we create an object of CountDownLatch, we specify the number of threads
		 * it should wait for, all such thread are required to do count down by calling
		 * CountDownLatch.countDown() once they are completed or ready to the job
		 */
		
		CountDownLatch latch = new CountDownLatch(executorThread);
		ExecutorService executors = Executors.newFixedThreadPool(executorThread);

		/*
		 * submit task to each thread to work on
		 */
		for (int i = 0; i < executorThread; i++) {
			executors.execute(new Task(latch, semaphore, queue, 3000));
		}
		/*
		 * Now it will not except any new task
		 */
		executors.shutdown();

		/*
		 * main thread wait to finish their job by child thread
		 */
		try {
			t1.join();
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Main thread has about to die
		System.out.println(Thread.currentThread().getName() + " Thread has finished");
	}

}
