package com.sanjiv.thread;

public class Account {
	
	private Integer accountNumber;
	private Integer balance = 10000;
	
	public Account(Integer accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}

}
