import java.util.*;

/**
 * Clase que modela la informacion de una transaccion bancaria
 * 
 * @author DanSantos
 * @version 29-03-2020
 * */

public class Transaction {
	
	/**
	 * Constructor predeterminado, establece el monto en 0, y la fecha actual
	 * */
	public Transaction() {
		this.amount = 0;
		this.date = new Date();
	}
	
	public Transaction(int amount) {
		this.amount = amount;
		this.date = new Date();
	}
	
	//Atributos
	private int amount;
	private Date date;
}
