package com.java.lambda.account;

public class ServiceFactory {

	public static ServiceBank getService() {
		return new ServiceBankImpl();
	}
}
