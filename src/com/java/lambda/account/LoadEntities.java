package com.java.lambda.account;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class LoadEntities {

	public static final List<EntitieAccount> ACCOUNTS;
	public static final List<EntitieClient> CLIENTS;
	public static final List<EntitieAddress> ADDRESSES;

	static {
		ACCOUNTS = new ArrayList<>();
		CLIENTS = new ArrayList<>();
		ADDRESSES = new ArrayList<EntitieAddress>();

		createDummyAddresses();
		createDummyClients();
		createDummyAccounts();
	}

	private static void createDummyAddresses() {
		IntStream.range(0, 10).forEach(LoadEntities::createDummyAddress);
	}

	private static void createDummyAddress(int index) {
		String country = index % 2 == 0 ? "Brazil" : "United States";
		EntitieAddress address = new EntitieAddress("Street " + index, index, "Complement " + index, "State " + index, country);
		ADDRESSES.add(address);
	}

	private static void createDummyClients() {
		IntStream.range(0, 50).forEach(LoadEntities::createDummyClient);
	}

	private static void createDummyClient(int index) {
		int addressPosition = index % (ADDRESSES.size() - 1);
		EntitieClient client = new EntitieClient("Client named " + index, "client" + index + "@bank.com", ADDRESSES.get(addressPosition));
		CLIENTS.add(client);
	}

	private static void createDummyAccounts() {
		IntStream.range(0, 200).forEach(LoadEntities::createDummyAccount);
	}

	private static void createDummyAccount(int index) {
		int addressClient = index % (CLIENTS.size() - 1);
		EntitieClient client = CLIENTS.get(addressClient);

		int randomBalance = ThreadLocalRandom.current().nextInt(1, 10000);

		EntitieAccount account = new EntitieAccount(index, index * 3, randomBalance, client, EnumAccount.randomType());

		client.getAccounts().add(account);

		ACCOUNTS.add(account);
	}
}