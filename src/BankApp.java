import java.util.HashMap;

import java.util.ArrayList;

import model.*;

/**
 * Clase principal de la aplicacion
 * 
 * @author DanSantos
 * @version 04-04-2020
 * */

public class BankApp {

	public static void main(String args[]) {
		Client client = new Client("Dan Santos", "20360262-2");
		
		client.addAddressee(new Addressee("23404121-3", "Contador", false));
		client.addAddressee(new Addressee("13404345-3", "Mensualidad", false));
		client.addAddressee(new Addressee("19202175-5", "Dentista", true));
		client.addAddressee(new Addressee("20313773-1", "Universidad", true));
		
		ArrayList<Addressee> list = client.getAddressees();
		for(Addressee addressee : list) {
			System.out.println("Nombre: " + addressee.getName() + " - N.Cuenta: " + addressee.getAccountNumber());
		}
	}
	
	/**
	 * Constructor prederminado de la clase, instancia los atributos objeto.
	 * */
	public BankApp() {
		this(new Client());
	}
	
	/**
	 * Constructor que establece el cliente e instancia los demas atributos objeto.
	 * @param cliente cliente inicial.
	 * */
	public BankApp(Client client) {
		this.accounts = new HashMap<String, Account>();
		this.client = client;
	}
	
	/**
	 * Agrega una nueva cuenta al mapa de cuentas, crea la cuenta con el saldo en 0.
	 * @param accountNumber numero  de la nueva cuenta a ingresar.
	 * */
	public void addAccount(String accountNumber) {
		this.accounts.put(accountNumber, new Account(accountNumber));
	}
	
	/**
	 * Agrega una nueva cuenta al mapa de cuentas, crea la cuenta inicializada con el saldo
	 * ingresado.
	 * @param balance saldo inicial de la nueva cuenta a ingresar.
	 * @param accountNumber numero de la nueva cuenta a ingresar.
	 * */
	public void addAccount(int balance, String accountNumber) {
		this.accounts.put(accountNumber, new Account(balance, accountNumber));
	}
	
	/**
	 * @return cuenta asociada al numero de cuenta ingresado
	 * */
	public Account getAccount(String accountNumber) {
		return this.accounts.get(accountNumber);
	}
	
	/**
	 * Comprueba que exista una cuenta guardada asociada al numero de cuenta ingresado.
	 * @return true si esta guardada la cuenta, false en caso contrario.
	 * */
	public boolean existsAccount(String accountNumber) {
		return this.accounts.containsKey(accountNumber);
	}
	
	/**
	 * @return instancia del objeto cliente actual.
	 * */
	public Client getClient() {
		return this.client;
	}
	
	/**
	 * Establece un nuevo cliente.
	 * @param newCliente nuevo cliente.
	 * */
	public void setCliente(Client newClient) {
		this.client = newClient;
	}
	
	//Atributos
	private HashMap <String, Account> accounts;
	private Client client;
}
