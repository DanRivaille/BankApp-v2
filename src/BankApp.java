import java.util.HashMap;

import model.Account;

/**
 * Clase principal de la aplicacion
 * 
 * @author DanSantos
 * @version 04-04-2020
 * */

public class BankApp {

	public static void main(String args[]) {
		BankApp bankApp = new BankApp();
		
		bankApp.addAccount("16211031-2");
		bankApp.addAccount(150_000, "12010421-2");
		bankApp.addAccount(50_000, "20410221-1");
		bankApp.addAccount(100_000, "16021002-2");
		
		Account account1 = bankApp.getAccount("20360262-1");
		Account account2 = bankApp.getAccount("12010421-2");
		
		account2.transferTo(50_000, "20360262-1");
		account1.depositFrom(50_000, "12010421-2");
		
		account1.showInfo();
		account2.showInfo();

	}
	
	/**
	 * Constructor prederminado de la clase, instancia los atributos objeto.
	 * */
	public BankApp() {
		this.accounts = new HashMap<String, Account>();
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
	
	//Atributos
	private HashMap <String, Account> accounts;
}
