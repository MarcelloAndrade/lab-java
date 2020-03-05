package com.java.lambda.account;

import java.util.ArrayList;
import java.util.List;

public class EntitieClient {

	private String name;
	private String email;
	private EntitieAddress address;
	private List<EntitieAccount> accounts;

	public EntitieClient() {
		this.accounts = new ArrayList<>();
	}

	public EntitieClient(String name, String email, EntitieAddress address) {
		this();
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EntitieAddress getAddress() {
		return address;
	}

	public void setAddress(EntitieAddress address) {
		this.address = address;
	}

	public List<EntitieAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<EntitieAccount> accounts) {
		this.accounts = accounts;
	}

}
