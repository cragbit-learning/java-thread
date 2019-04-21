package com.sanjiv.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ThreadRunner implements Runnable {
	
	private Integer id;
	
	public ThreadRunner(Integer id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		System.out.println("Task number : " + id + " : doing by  " + Thread.currentThread().getName()); 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("completed task:" + id + " : By " + Thread.currentThread().getName());
	}
	
}

public class ThreadExecutorService {

	public static void main(String[] args) {
		ExecutorService executors = Executors.newFixedThreadPool(2);
		for(int i=0; i<5;i++) {
			executors.submit(new ThreadRunner(i));
		}
		System.out.println("############All Task Submitted...!##############");
		executors.shutdown(); // It will not take any new task.
		//executors.submit(new ThreadRunner(5));
		
		try {
			if(executors.awaitTermination(20, TimeUnit.SECONDS)) {
				System.out.println(" executor terminated successfully");
			}else {
				System.out.println("  the timeout elapsed before termination");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("###############All Task completed##############");
	}
}
