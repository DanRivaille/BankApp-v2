package exceptions;

/**
 * Excepcion que sera disparada cuando se intente realizar una transaccion con un monto mayor al saldo 
 * contable actual de la cuenta.
 * @author Dan Santos
 * @version 05-07-2020
 */

public class ExcessiveTransactionAmount extends Exception{

	private static final long serialVersionUID = 2L;

}
