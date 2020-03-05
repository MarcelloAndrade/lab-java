package com.java.lambda.account;

public class Main {

	private static ServiceBank service = ServiceFactory.getService();

	public static void main(String[] args) {
		// printClientInfo();
		printClientAccountInfo();
	}

	/**
	 * Imprima na tela o nome e e-mail de todos os clientes (sem repetição)
	 */
	static void printClientInfo() {
		System.out.println("Imprima na tela o nome e e-mail de todos os clientes (sem repetição)");
		service.listClients()
			   .stream()
			   .map(c -> c.getName() + " - " + c.getEmail())
			   .distinct()
			   .forEach(System.out::println);
	}
	
	/**
	 * Imprima na tela o nome do cliente e a média do saldo de suas contas, ex: Cliente - 352
	 */
	static void printClientAccountInfo() {
		System.out.println("Imprima na tela o nome do cliente e a média do saldo de suas contas, ex: Cliente - 352");
		service.listClients()
			   .stream()
			   .forEach(c -> {
				   double media = service.listAccounts()
								   		 .stream()
								   		 .filter(conta -> conta.getClient().getName().equals(c.getName()))
								   		 .mapToDouble(conta -> conta.getBalance())
								   		 .average()
								   		 .orElse(0);
				   System.out.println(c.getName() +" - "+ media);
			   });
	}
	
	/**
	 * Considerando que só existem os países "Brazil" e "United States", 
	 * imprima na tela qual deles possui o cliente mais rico, ou seja,
	 * com o maior saldo somando todas as suas contas.
	 */

}
