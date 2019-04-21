package com.sanjiv.thread;

import java.util.LinkedList;

class ProducerConsumerViaLowLevel {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final Integer LIMIT = 10;

	public void producer() {
		Integer value = 0;
		while (true) {
			synchronized (this) {
				if(list.size() == LIMIT) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				list.add(value++);
			}
		}
	}

	public void consumer() {
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (this) {
				if(list.size() == 0) {
					notify();
				}else {
					System.out.println("Removed Item: " + list.removeFirst());
				}
			}
		}
	}

}

public class ProducerConsumerViaLowLevelExample {

	public static void main(String[] args) {
		
		ProducerConsumerViaLowLevel producerConsumerViaLowLevel  = new ProducerConsumerViaLowLevel();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				producerConsumerViaLowLevel.producer();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				producerConsumerViaLowLevel.consumer();
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
	}
}
