package model;
import java.time.LocalDate;

/**
 * Clase que modela la informacion de una transaccion bancaria.
 * Nota: un monto negativo representa un cargo a la cuenta, mientras que un positivo
 * representa un abono
 * 
 * @author DanSantos
 * @version 29-03-2020
 * */

public class Transaction {

	private int amount;							//Monto asociado a la transaccion, (negativo indica un cargo, positivo un abono)
	private LocalDate date;							//Fecha cuando se realizo la transaccion
	private Addressee asociatedAccount;		//Cuenta asociada a la transaccion
	
	/**
	 * Constructor, inicializa el monto con la cantidad ingresada y la fecha actual
	 * @param amount monto asociado a la transaccion realizada
	 * */
	public Transaction(int amount) {
		setAmount(amount);
		this.date = LocalDate.now();
		this.asociatedAccount = new Addressee();
	}
	
	/**
	 * Constructor, establece el monto, la fecha actual y guarda el numero de la cuenta asociada
	 * @param amount monto asociado a la transaccion
	 * @param asociatedAccount numero de la cuenta asociada a la transaccion
	 * */
	public Transaction(int amount, Account asociatedAccount) {
		setAmount(amount);
		this.date = LocalDate.now();
		this.asociatedAccount = new Addressee(asociatedAccount);
	}
	
	/**
	 * @return monto actual de la transaccion
	 * */
	public int getAmount() {
		return this.amount;
	}
	
	/**
	 * @return fecha guardada de la transaccion
	 * */
	public LocalDate getDate() {
		return this.date;
	}
	
	/**
	 * @return Destinatario de la cuenta asociada a la transaccion
	 * */
	public Addressee getAddressee() {
		return this.asociatedAccount;
	}
	
	/**
	 * @param newAmount establece un nuevo monto de la transaccion
	 * */
	public void setAmount(int newAmount) {
		this.amount = newAmount;
	}
	
	/**
	 * @param newAsociatedAccount establece un nuevo numero de cuenta para la cuenta asociada
	 * */
	public void setAccount(Account newAsociatedAccount) {
		this.asociatedAccount.setAccount(newAsociatedAccount);
	}
}
