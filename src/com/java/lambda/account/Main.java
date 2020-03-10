package com.java.lambda.account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.security.ntlm.Client;

public class Main {

	private static ServiceBank service = ServiceFactory.getService();

	public static void main(String[] args) {
		// printClientInfo();
		// printClientAccountInfo();
		// imprimirPaisClienteMaisRico();
		imprimirClientesComPoupanca();
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
	 * imprima na tela qual deles possui o maior saldo, ou seja,
	 * com o maior saldo somando todas as suas contas.
	 */
	static void imprimirPaisClienteMaisRico() {
		double sumClientBrazil = 
				service.listAccounts()
				.stream()
				.filter(conta -> conta.getClient().getAddress().getCountry().equals("Brazil"))
				.mapToDouble(conta -> conta.getBalance())
				.sum();
		
		double sumClienteUSA =
				service.listAccounts()
				.stream()
				.filter(conta -> conta.getClient().getAddress().getCountry().equals("United States"))
				.mapToDouble(conta -> conta.getBalance())
				.sum();
		
		System.out.println(Double.compare(sumClientBrazil, sumClienteUSA));
	}
	
	/**
	 * Imprime na tela o saldo médio das contas da agência
	 * @param agency
	 */
	public static void imprimirSaldoMedio(int agency) {
		double average = 
			service
			.listAccounts()
			.stream()
			.filter(conta -> conta.getAgency() == agency)
			.mapToDouble(conta -> conta.getBalance())
			.average()
			.getAsDouble();
		System.out.println(average);
	}
	
	/**
	 * Imprime na tela o nome de todos os clientes que possuem conta poupança (tipo SAVING)
	 */
	public static void imprimirClientesComPoupanca() {
		service
		.listAccounts()
		.stream()
		.filter(conta -> conta.getType().name().equals(EnumAccount.SAVING.name()))
		.map(conta -> conta.getClient().getName())
		.distinct()
		.forEach(System.out::println);
	}
	
	/**
	 * Retorna uma lista de Strings com o "estado" de todos os clientes da agência
	 */
	public static List<String> getEstadoClientes(int agency) {
		List<String> stateOfAllClients = 
				service
				.listAccounts()
				.stream()
				.filter(conta -> conta.getAgency() == agency)
				.map(conta -> conta.getClient().getAddress().getState())
				.collect(Collectors.toList());
		return (List<String>) stateOfAllClients;
	}
	
	/**
	 * Retorna o somatório dos saldos das contas do cliente em questão 
	 */
	public static double getMaiorSaldo(String clientEmail) {
		double sumBalance =
				service
				.listAccounts()
				.stream()
				.filter(conta -> conta.getClient().getEmail().equals(clientEmail))
				.mapToDouble(conta -> conta.getBalance())
				.sum();
		return sumBalance;
	}
	
	/**
	 * Realiza uma operação de saque na conta de acordo com os parâmetros recebidos
	 */
	public static void sacar(int agency, int number, double value) {
		service
		.listAccounts()
		.stream()
		.filter(conta -> conta.getAgency( )== agency &&
						conta.getNumber() == number)
		.map(conta -> conta.getBalance() - value);
	}
	
	/**
	 * Realiza um deposito para todos os clientes do país em questão	
	 */
	public static void depositar(String country, double value) {
		service
		.listAccounts()
		.stream()
		.filter(conta -> conta.getClient().getAddress().getCountry().equals(country))
		.map(conta -> conta.getBalance() + value);
	}

	/**
	 * Realiza uma transferência entre duas contas de uma agência.
	 */
	public static void transferir(int agency, int numberSource, int numberTarget, double value) {
		service
		.listAccounts()
		.stream()
		.filter(conta -> conta.getAgency() == agency &&
						conta.getNumber() == numberSource)
		.map(conta -> conta.getBalance() - value);
		service
		.listAccounts()
		.stream()
		.filter(conta -> conta.getAgency() == agency &&
						conta.getNumber() == numberTarget)
		.map(conta -> conta.getBalance() + value);
	}

	/**
	 * Retorna uma lista com todas as contas conjuntas (JOINT) dos clientes
	 */
	public static List<EntitieAccount> getContasConjuntas(List<Client> clients) {
		List<EntitieAccount> jointAccounts = new ArrayList<EntitieAccount>();
		service
		.listAccounts()
		.stream()
		.filter(conta -> conta.getClient().equals(clients))
		.map(conta -> conta.getType().equals("JOINT"))
		.collect(Collectors.toList());
		return jointAccounts;
	}

	/**
	 * Retorna uma lista com o somatório dos saldos de todas as contas do estado 
	 */
	public static double getSomaContasEstado(String state) {
		double sumAccountState =
				service
				.listAccounts()
				.stream()
				.filter(conta -> conta.getClient().getAddress().getState().equals(state))
				.mapToDouble(conta -> conta.getBalance())
				.sum();
		return sumAccountState;
	}

	/**
	 * Retorna um array com os e-mails de todos os clientes que possuem contas conjuntas
	 */
	public static String[] getEmailsClientesContasConjuntas() {
		List<String> emailsAllClientsJoinAccounts =
				service
				.listClients()
				.stream()
				.filter(cliente -> cliente.getAccounts().equals(EnumAccount.JOINT))
				.map(cliente -> cliente.getEmail())
				.collect(Collectors.toList());
		return (String []) emailsAllClientsJoinAccounts.toArray();
				
	}

}
