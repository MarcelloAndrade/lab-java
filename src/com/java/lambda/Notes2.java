package com.java.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Notes2 {

	public static void filter1(List<String> list, Predicate<String> condition) {
		for (String s : list)
			if (condition.test(s))
				System.out.println(s);
	}
	
	public static void filter2(List<String> list, Predicate<String> condition) {
		list.stream().filter(condition::test).forEach(System.out::println);
	}
	
	public static void main(String[] args) {

		List<String> lista1 = Arrays.asList("Santa Catarina", "Parana", "São Paulo", "Rio de Janeiro", "Brasilia", "Ceará");

		System.out.println("Estados que iniciam com a letra S");
		filter2(lista1, s -> s.startsWith("S"));

		System.out.println("");
		System.out.println("Estados que finalizam com a letra A");
		filter2(lista1, s -> s.endsWith("a"));

		System.out.println("");
		System.out.println("Imprime toda a lista");
		filter2(lista1, s -> true);

		System.out.println("");
		System.out.println("Não imprime toda a lista");
		filter2(lista1, s -> false);

		System.out.println("");
		System.out.println("Imprime os nomes com + de 10 caracteres.");
		filter2(lista1, s -> s.length() > 10);
	}

}