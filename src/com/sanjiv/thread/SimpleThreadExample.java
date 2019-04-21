package com.sanjiv.thread;


class SimpleThread {
	
	private Integer k=0;

	public void process() {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				display();	
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				display();	
			}
		});
		
		t1.start();
		t2.start();
		
		
	}
	
	public void display() {
		for (int i = 0; i< 10; i++) {
			//k = k+1;
			System.out.println("Current Thread ::" +Thread.currentThread().getName());
			System.out.println("Value = " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}

public class SimpleThreadExample {

	public static void main(String[] args) {
		
		SimpleThread simpleThread = new SimpleThread();
		simpleThread.process();
	}

}
