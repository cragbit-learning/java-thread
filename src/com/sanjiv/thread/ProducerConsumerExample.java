package com.sanjiv.thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ProducerConsumer {

	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

	public void producer() {
		Random randon = new Random();
		while (true) {
			try {
				Integer value  = randon.nextInt(100);
				queue.put(value);
				System.out.println("Putting item in queue : " + value + " ,by Producer thread : " + Thread.currentThread().getName()); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void consumer() {
		while (true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				System.out.println("Removed Value from Queue : " + queue.take()  + " , By Consumer Thread : " + Thread.currentThread().getName());
				System.out.println(" Queue Size : " + queue.size());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public BlockingQueue<Integer> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}
	
	
}

public class ProducerConsumerExample {

	public static void main(String[] args) {
		
		ProducerConsumer producerConsumer = new ProducerConsumer();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				producerConsumer.producer();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				producerConsumer.consumer();
			}
		});
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					try {
						System.out.println("Removed Value from Queue : " + producerConsumer.getQueue().take()  + " , By Consumer Thread : " + Thread.currentThread().getName());
						System.out.println(" Queue Size : " + producerConsumer.getQueue().size());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
