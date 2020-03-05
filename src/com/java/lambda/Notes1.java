package com.java.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class Notes1 {
	
	public static void main(String[] args) {
		List<String> list = Arrays.asList("Pedro", "Maria", "Cladia", "Paulo", "Flay");

		// Ex: 1
//		list.sort(new ComparatorPilot());
		list.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
		System.out.println(list);
		
		// Ex: 2
//		list.forEach(new PrintPilot());
		list.forEach(l -> System.out.println(l));
	}		
}

//Ex: 1
class ComparatorPilot implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		if(o1.length() < o2.length())
			return -1;
		if(o1.length() > o2.length())
			return 1;
		return 0;
	}
}

//Ex: 2
class PrintPilot implements Consumer<String> {
	@Override
	public void accept(String t) {
		System.out.println(t);
	}
}
