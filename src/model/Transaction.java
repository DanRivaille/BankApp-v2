package model;
import java.util.Date;

/**
 * Clase que modela la informacion de una transaccion bancaria.
 * Nota: un monto negativo representa un cargo a la cuenta, mientras que un positivo
 * representa un abono
 * 
 * @author DanSantos
 * @version 29-03-2020
 * */

public class Transaction {
	
	/**
	 * Constructor predeterminado, inicializa el monto en 0, y la fecha actual
	 * */
	public Transaction() {
		this(0);						//llama al contructos de abajo, con el valor por defecto del monto
	}
	
	/**
	 * Constructor, inicializa el monto con la cantidad ingresada y la fecha actual
	 * @param amount monto asociado a la transaccion realizada
	 * */
	public Transaction(int amount) {
		setAmount(amount);
		this.date = new Date();
		this.asociatedAccount = new Addressee();
	}
	
	/**
	 * Constructor, establece el monto, la fecha actual y guarda el numero de la cuenta asociada
	 * @param amount monto asociado a la transaccion
	 * @param accountNumber numero de la cuenta asociada a la transaccion
	 * */
	public Transaction(int amount, String accountNumber) {
		setAmount(amount);
		this.date = new Date();
		this.asociatedAccount = new Addressee(accountNumber);
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
	public Date getDate() {
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
	 * @param newAccountNumber establece un nuevo numero de cuenta para la cuenta asociada
	 * */
	public void setAccountNumber(String newAccountNumber) {
		this.asociatedAccount.setAccountNumber(newAccountNumber);
	}
	
	//Atributos
	private int amount;
	private Date date;
	private Addressee asociatedAccount;
}
