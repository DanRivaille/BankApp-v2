package model;
import java.util.ArrayList;

/**
 * Clase que modela una cuenta bancaria, guarda el saldo de la cuenta su numero de cuenta y el tipo de cuenta que es
 * (cuenta rut o cuenta de ahorros).
 * 
 * @author DanSantos
 * @version 29-03-2020
 */

public class Account {

	/**
	* Contructor por defecto, inicializa el saldo en 0 y el numero de cuenta en "00000000-0"
	*/
	public Account() {
		this("00000000-0");			//llama al constructor da abajo con un numero de cuenta por defecto
	}

	/**
	* Constructor, establece el numero de cuenta de la nueva cuenta, el saldo lo inicializa en 0
	* @param newAccountNumber numero de cuenta, se asume que se haya validado
	*/
	public Account(String newAccountNumber) {
		this.balance = 0;
		this.accountNumber = newAccountNumber;
		this.history = new ArrayList<Transaction>();
	}
	
	/**
	* Constructor, establece todos los atributos de la clase con los parametros entregados, valida el saldo
	* @param initialBalance saldo inicial que tendra la cuenta
	* @param newAccountNumber numero de cuenta, se asume que se haya validado
	*/
	public Account(int initialBalance, String newAccountNumber) {
		setBalance(initialBalance);
		this.accountNumber = newAccountNumber;
		this.history = new ArrayList<Transaction>();
	}
	
	/**
	 * Suma la cantidad "amount" al saldo, guardando la cuenta de origen: "accountNumber"
	 * @param amount cantidad a depositar
	 * @param accountNumber numero de cuenta que realizo el deposito 
	 * */
	public void depositFrom(int amount, String accountNumber) {
		setBalance(amount + getBalance());
		addTransaction(amount, accountNumber);
	}
	
	/**
	 * Resta la cantidad "amount" del saldo, guardando la cuenta destino "accountNumber"
	 * @param amount cantidad a transferir
	 * @param accountNumber numero de cuenta a la que se realiza la transferencia
	 * */
	public void transferTo(int amount, String accountNumber) {
		setBalance(getBalance() - amount);
		addTransaction(-amount, accountNumber);
	}
	
	/**
	 * Añade una nueva transaccion al historial de movimientos, si alcanzo el maximo se elimina la mas antigua
	 * @param amount monto relacionado con la transaccion
	 * @param accountNumber asociada a la transaccion
	 * */
	public void addTransaction(int amount, String accountNumber) {
		if(this.history.size() >= 20)
			this.history.remove(0);
		
		this.history.add(new Transaction(amount, accountNumber));
	}
	
	/**
	* @return saldo actual de la cuenta
	*/
	public int getBalance() {
		return this.balance;
	}

	/**
	* @return numero de la cuenta
	*/
	public String getAccountNumber() {
		return this.accountNumber;
	}

	/**
	* Obtiene el tipo de cuenta (cuenta rut o cuenta de ahorros)
	* @return tipo de cuenta
	*/
	public TypeAccount getTypeAccount() {
		return Account.getTypeAccount(this.accountNumber);
	}

	/**
	* Metodo estatico de clase. Obtiene el tipo de cuenta de la cuenta ingresada, 
	* se asume que ya se ha validado.
	* @param accountNumber numero de cuenta de la que se desea saber el tipo de cuenta
	* @return tipo de cuenta de la cuenta ingresada como parametro
	*/
	public static TypeAccount getTypeAccount(String accountNumber) {
		switch(accountNumber.charAt(9))
		{
			case '1' : return TypeAccount.RUT_ACCOUNT;
			case '2' : return TypeAccount.SAVING_ACCOUNT;
			default  : return TypeAccount.NO_VALID;
		}
	}

	/**
	* Establece el saldo de la cuenta, realiza una validacion de que el nuevo saldo no sea negativo
	* @param newBalance nuevo saldo de la cuenta
	*/
	public void setBalance(int newBalance) {
		if(balance < 0)
			newBalance = 0;

		this.balance = newBalance;
	}
	
	/**
	 * Establece el numero de cuenta, asume que sea valido
	 * @param newAccountNumber nuevo numero de cuenta.
	 * */
	public void setAccountNumber(String newAccountNumber) {
		this.accountNumber = newAccountNumber;
	}

	/**
	 * Muestra la informacion general de la cuenta por consola
	 * */
	public void showInfo() {
		System.out.println("Saldo: $" + getBalance() + "\nNumero de cuenta: " + getAccountNumber());
		
		int numTransaction = 1;
		
		for(Transaction transaction : this.history) {
			System.out.print(numTransaction + ") Monto: " + transaction.getAmount());
			System.out.print(" - Cuenta: " + transaction.getAddressee().getAccountNumber());
			System.out.println(" - Fecha: " + transaction.getDate().toString());
			
			numTransaction++;
		}
	}

	//Atributos
	private int balance;
	private String accountNumber;
	private ArrayList<Transaction> history;
}