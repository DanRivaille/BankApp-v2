package model;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import exceptions.*;

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
	 * @param client cliente inicial.
	 * */
	public Bank(Client client) {
		this.accounts = new HashMap<String, Account>();
		this.client = client;
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
	 * @param accountNumber numero de la cuenta a eliminar.
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
	 * Realiza una transaccion entra la cuenta origen hacia la cuenta destino, transfiriendo el
	 * monto especificado, se valida que las dos cuentas esten en el sistema.
	 * @param originAccount cuenta de origen de los fondos.
	 * @param destinyAccount cuenta destino en la que depositaran los fondos.
	 * @param amount monto asociado a la transaccion.
	 * */
	public void transactionBetweenAccounts(String originAccountNumber, String destinyAccountNumber, int amount) throws ExcessiveTransactionAmount{
		if(existsAccount(originAccountNumber) && existsAccount(destinyAccountNumber)) {
			Account originAccount = this.accounts.get(originAccountNumber);
			Account destinyAccount = this.accounts.get(destinyAccountNumber);
			
			originAccount.transferTo(amount, destinyAccount);
			destinyAccount.depositFrom(amount, originAccount);
		}
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
	 * Funcion que retorna una lista con las cuentas que entran en el rango ingresado como parametro
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
	 * Realizara las imposiciones de todas las cuentas en el sistema
	 * */
	public void makeImpositions() {
		Iterator<String> accountNumber = this.accounts.keySet().iterator();
		
		while(accountNumber.hasNext()) {
			this.accounts.get(accountNumber.next()).makeImpositions();
		}
	}
	
	/**
	 * @return instancia del objeto cliente actual.
	 * */
	public Client getClient() {
		return this.client;
	}
	
	/**
	 * Establece un nuevo cliente.
	 * @param newClient nuevo cliente.
	 * */
	public void setClient(Client newClient) {
		this.client = newClient;
	}
}