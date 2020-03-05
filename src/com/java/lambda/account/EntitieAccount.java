package com.java.lambda.account;

public class EntitieAccount {
	
	private int agency;
	private int number;
	private double balance;
	private EntitieClient client;
	private EnumAccount type;

	public EntitieAccount(int agency, int number, double balance, EntitieClient client, EnumAccount type) {
		this.agency = agency;
		this.number = number;
		this.balance = balance;
		this.client = client;
		this.type = type;
	}

	public int getAgency() {
		return agency;
	}

	public void setAgency(int agency) {
		this.agency = agency;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public EntitieClient getClient() {
		return client;
	}

	public void setClient(EntitieClient client) {
		this.client = client;
	}

	public EnumAccount getType() {
		return type;
	}

	public void setType(EnumAccount type) {
		this.type = type;
	}

}
