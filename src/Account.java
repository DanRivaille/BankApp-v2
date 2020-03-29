/**
 * Clase que modela una cuenta bancaria, guarda el saldo de la cuenta su numero de cuenta y el tipo de cuenta que es
 * (cuenta rut o cuenta de ahorros).
 * 
 * @author DanSantos
 * @version 29-03-2020
 */

public class Account {

	public static void main(String args[]) {
		Account account = new Account();

		account.showInfo();

		if(account.getTypeAccount() == TypeAccount.RUT_ACCOUNT)
			System.out.println("RUT_ACCOUNT");
		else
			System.out.println("SAVING_ACCOUNT");

	}

	/**
	* Contructor por defecto, inicializa el saldo en 0 y el numero de cuenta en "00000000-0"
	*/
	public Account() {
		this.balance = 0;
		this.accountNumber = "00000000-0";
	}

	/**
	* Constructor, establece todos los atributos de la clase con los parametros entregados, valida el saldo
	* @param initialBalance saldo inicial que tendra la cuenta
	* @param newAccountNumber numero de cuenta, se asume que se haya validado
	*/
	public Account(int initialBalance, String newAccountNumber) {
		setBalance(initialBalance);
		this.accountNumber = newAccountNumber;
	}

	/**
	* Constructor, establece el numero de cuenta de la nueva cuenta, el saldo lo inicializa en 0
	* @param newAccountNumber numero de cuenta, se asume que se haya validado
	*/
	public Account(String newAccountNumber) {
		this.balance = 0;
		this.accountNumber = newAccountNumber;
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
	* Metodo estatico de clase. 
	* Obtiene el tipo de cuenta de la cuenta ingresada, se asume que ya se ha validado.
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

	public void showInfo() {
		System.out.println("Saldo: $" + getBalance() + "\nNumero de cuenta: " + getAccountNumber());
	}

	//Atributos
	private int balance;
	private String accountNumber;
}

enum TypeAccount {
	RUT_ACCOUNT, SAVING_ACCOUNT, NO_VALID
}