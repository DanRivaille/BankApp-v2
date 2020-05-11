package model;
import view.*;
import controller.*;
import java.util.HashMap;

/**
 * Clase principal de la aplicacion
 * 
 * @author DanSantos
 * @version 04-04-2020
 * */

public class BankApp {

	private HashMap <String, Account> accounts;				//Cuentas guardadas del sistema
	private Client client;									//Guarda la informacion del cliente actual de la aplicacion
	
	public static void main(String args[]) {
		BankApp bank = new BankApp();
		ControllerTUI controllerTUI = new ControllerTUI(bank);
		ViewTUI viewTUI = new ViewTUI();
		
		controllerTUI.setView(viewTUI);
		viewTUI.setController(controllerTUI);
		
		Client client = new Client("Dan Santos", "20360262-6");
		bank.setClient(client);
		
		client.addAddressee(new Addressee("23404121-3", "Contador", false));
		client.addAddressee(new Addressee("13404345-3", "Mensualidad", false));
		client.addAddressee(new Addressee("19202175-5", "Dentista", true));
		client.addAddressee(new Addressee("20313773-1", "Universidad", true));
		
		bank.addAccount("20360262-1");
		
		viewTUI.mainMenu();
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
		addAccount(0, accountNumber);
	}
	
	/**
	 * Agrega una nueva cuenta al mapa de cuentas, crea la cuenta inicializada con el saldo
	 * ingresado.
	 * @param balance saldo inicial de la nueva cuenta a ingresar.
	 * @param accountNumber numero de la nueva cuenta a ingresar.
	 * */
	public void addAccount(int balance, String accountNumber) {
		if(!this.accounts.containsKey(accountNumber))
			this.accounts.put(accountNumber, new Account(balance, accountNumber));
	}
	
	/**
	 * Agrega la cuenta ingresada al mapa de cuentas
	 * @param account nueva cuenta a ingresar al mapa
	 * */
	public void addAccount(Account account) {
		if(!this.accounts.containsKey(account.getAccountNumber()))
			this.accounts.put(account.getAccountNumber(), account);
	}
	
	/**
	 * Comprueba que exista una cuenta guardada asociada al numero de cuenta ingresado.
	 * @return true si esta guardada la cuenta, false en caso contrario.
	 * */
	public boolean existsAccount(String accountNumber) {
		return this.accounts.containsKey(accountNumber);
	}
	
	/**
	 * @return cuenta asociada al numero de cuenta ingreado.
	 * */
	public Account getAccount(String accountNumber) {
		return this.accounts.get(accountNumber);
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
	public void setClient(Client newClient) {
		this.client = newClient;
	}
}