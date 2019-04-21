package com.sanjiv.thread;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Semaphore;

class HttpCallService{

	private String url;
	private Semaphore semaphore = new Semaphore(2);
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	private void sendGet(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
	}

	public void httpServiceCall(String url) {
		
		try {
			System.out.println(Thread.currentThread().getName() + " ,waiting for semaphore...!"); 
			semaphore.acquire();
			System.out.println(Thread.currentThread().getName() + " ,Got the semaphore...!"); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			sendGet(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		semaphore.release();
		System.out.println(Thread.currentThread().getName() + " ,release semaphore...!"); 
	}
}

public class SemaphoreExample {

	public static void main(String[] args) {
		
		HttpCallService httpCallService = new HttpCallService();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				httpCallService.httpServiceCall("https://www.happiestminds.com");
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				httpCallService.httpServiceCall("https://www.mindtree.com");
			}
		});
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				httpCallService.httpServiceCall("https://yahoo.com");
			}
		});
		
		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				httpCallService.httpServiceCall("https://google.com");
			}
		});

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
