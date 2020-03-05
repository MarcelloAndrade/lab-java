package com.java.lambda.account;

import java.util.List;

public class ServiceBankImpl implements ServiceBank {

	protected ServiceBankImpl() {
	}

	@Override
	public List<EntitieAccount> listAccounts() {
		return LoadEntities.ACCOUNTS;
	}

	@Override
	public List<EntitieClient> listClients() {
		return LoadEntities.CLIENTS;
	}

}
