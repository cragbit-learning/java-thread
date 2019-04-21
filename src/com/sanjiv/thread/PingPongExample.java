package com.sanjiv.thread;

class PingPong {

	public void ping() {
		while(true) {
			synchronized (this) {
				try {
					System.out.println(">>>>>>>>Ping<<<<<<<<<");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void pong() throws InterruptedException {
		while(true) {
			Thread.sleep(3000);
			synchronized (this) {
				System.out.println("#########Pong#########");
				notify();
			}
		}
		
	}
}

public class PingPongExample {

	public static void main(String[] args) {
		
		PingPong pingPong = new PingPong();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				pingPong.ping();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					pingPong.pong();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
