package com.java.lambda.account;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum EnumAccount {

	CHECKING, SAVING, JOINT;

	private static final List<EnumAccount> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static EnumAccount randomType() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
	
	public static void main(String[] args) {
		System.out.println(randomType());
	}
}