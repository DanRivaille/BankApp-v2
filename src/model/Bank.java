package model;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase principal de la aplicacion
 * 
 * @author DanSantos
 * @version 04-04-2020
 * */

public class Bank {

	private HashMap <String, Account> accounts;				//Cuentas guardadas del sistema
	private Client client;									//Guarda la informacion del cliente actual de la aplicacion
	
	/**
	 * Constructor prederminado de la clase, instancia los atributos objeto.
	 * */
	public Bank() {
		this(new Client());
	}
	
	/**
	 * Constructor que establece el cliente e instancia los demas atributos objeto.
	 * @param cliente cliente inicial.
	 * */
	public Bank(Client client) {
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
	public void removeAccount(String accountNumber) {
		if(this.accounts.containsKey(accountNumber)) {
			this.accounts.remove(accountNumber);
		}
	}
	
	/**
	 * Comprueba que exista una cuenta guardada asociada al numero de cuenta ingresado.
	 * @return true si esta guardada la cuenta, false en caso contrario.
	 * */
	public boolean existsAccount(String accountNumber) {
		return this.accounts.containsKey(accountNumber);
	}
	
	/**
	 * @return cuenta asociada al numero de cuenta ingreado, si no existe una cuenta con el numero de cuenta ingresado
	 * retorna null, sino retorna la cuenta.
	 * */
	public Account getAccount(String accountNumber) {
		if(this.accounts.containsKey(accountNumber)) {
			return this.accounts.get(accountNumber);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Funcion que retorna una lista con las cuentas que entran en el rango ingresad como parametro
	 * @return ArrayList que contiene las cuentas que cumplen con el rango ingresado.
	 * */
	public ArrayList<Account> getAccountBalanceRank(int lowerLimit, int upperLimit) {
		//Lista que almacenara las cuentas correspondientes
		ArrayList<Account> accountBalanceRank = new ArrayList<Account>();
		
		//Iterador que recorrera las claves del mapa de cuentas
		Iterator<String> accountNumber = this.accounts.keySet().iterator();
		
		//Se recorre el mapa de cuentas
		while(accountNumber.hasNext()) {
			//Se obtiene la cuenta actual
			Account account = this.accounts.get(accountNumber.next());
			
			//Se obtiene el saldo de la cuenta actual
			int balance = account.getBalance();
			
			//Si el saldo de la cuenta entra en el rango ingresado, se ingresa a la lista
			if((balance >= lowerLimit) && (balance <= upperLimit)) { 
				accountBalanceRank.add(account);
			}
		}
		
		//Se retorna la lista con todas las cuentas que cumplieron el rango
		return accountBalanceRank;
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