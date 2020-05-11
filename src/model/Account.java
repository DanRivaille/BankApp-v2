package model;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Clase que modela una cuenta bancaria, guarda el saldo de la cuenta su numero de cuenta y el tipo de cuenta que es
 * (cuenta rut o cuenta de ahorros).
 * 
 * @author DanSantos
 * @version 29-03-2020
 */

public class Account {

	private int balance;							//Saldo de la cuenta.
	private String accountNumber;					//Numero de cuenta 
	private LinkedList<Transaction> history;		//Historial de movimientos de la cuenta (guarda los ultimos 20 movimientos)
	
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
		this.history = new LinkedList<Transaction>();
	}
	
	/**
	* Constructor, establece todos los atributos de la clase con los parametros entregados, valida el saldo
	* @param initialBalance saldo inicial que tendra la cuenta
	* @param newAccountNumber numero de cuenta, se asume que se haya validado
	*/
	public Account(int initialBalance, String newAccountNumber) {
		setBalance(initialBalance);
		this.accountNumber = newAccountNumber;
		this.history = new LinkedList<Transaction>();
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
	 * Comprueba que el numero de cuenta ingresado es un numero de cuenta valido.
	 * @param accountNumber numero de cuenta a validar.
	 * @return true si es un numero de cuenta valido, false en caso contrario.
	 * */
	public static boolean isValid(String accountNumber) {
		boolean isValid = true;
		
		if(accountNumber.length() != 10) {							//Si tiene un largo distinto que un numero de cuenta valido
			isValid = false;											//no es valida
		}
		else if(accountNumber.charAt(8) != '-') {					//Si no tiene el guion en la posicion correspondiente
			isValid = false;											//no es valida
		}
		else if(Account.getTypeAccount(accountNumber) == TypeAccount.NO_VALID) {		//Si tiene un digito identificador del tipo de cuenta no valido
			isValid = false;																//no es valida
		}
		else {														//Si algun caracter no es un digito
			for(int i = 0; i < 8; ++i) {								//no es valida
				if(!Character.isDigit(accountNumber.charAt(i))) {
					isValid = false;
					break;
				}
			}
		}
			
		return isValid;
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
	 * Obtiene una interador de la lista del historial de transacciones.
	 * @return iterador del historial de transacciones.
	 * */
	public ListIterator<Transaction> getHistory() {
		 return this.history.listIterator();
	}

	/**
	* Establece el saldo de la cuenta
	* @param newBalance nuevo saldo de la cuenta
	*/
	public void setBalance(int newBalance) {
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
}