package model;

import exceptions.*;
import java.util.ListIterator;
import java.time.LocalDate;

/**
 * Clase que modela una cuenta de ahorros, hereda de la clase abstracta Account, 
 * e implementa el metodo makeImpositions de la forma en que lo realiza un cuenta de ahorros,
 * deposita un porcentaje del total de depositos de la cuenta en el ultimo mes.
 * @author Dan Santos
 * @version 04-06-2020
 * */
public class SavingAccount extends Account{
	
	private double profitabilityPercentage;				//Porcentaje de rentabilidad actual de la cuenta.
	
	/**
	 * Constructor por defecto de la clase, establece el porcentaje de rentabilidad en 1%
	 * */
	public SavingAccount() {
		this.profitabilityPercentage = 0.01f; 
	}
	
	/**
	 * Constructor, establece el porcentaje de rentabilidad en 1%
	 * */
	public SavingAccount(String accountNumber) throws WrongAccountNumber {
		super(0, accountNumber);
		this.profitabilityPercentage = 0.01f;
	}
	
	/**
	 * Constructor, establece el porcentaje de rentabilidad en 1%
	 * */
	public SavingAccount(int balance, String accountNumber) throws WrongAccountNumber {
		super(balance, accountNumber);
		this.profitabilityPercentage = 0.01f;
	}
	
	/**
	 * Constructor, establace los atributos de la clase.
	 * */
	public SavingAccount(int balance, String accountNumber, double profitabilityPercentage) throws WrongAccountNumber {
		super(balance, accountNumber);
		this.profitabilityPercentage = profitabilityPercentage;
	}
	
	/**
	 * Realiza las impociciones de la cuenta, para la cuenta de ahorros, deposita el porcentaje de rentabilidad
	 * actual del total de depositos realizados en el ultimo mes.
	 * */
	public void makeImpositions() {
		//acumulara el total de los depositos del ultimo mes
		int totalDeposit = 0;
		
		//Se obtiene un iterador del historial
		ListIterator<Transaction> history = getHistory();
		
		//Fecha actual, se usara para comparar los meses de la fecha de las transacciones
		final LocalDate currentDate = LocalDate.now();
		
		while(history.hasNext()) {
			Transaction transaction = history.next();
			LocalDate date = transaction.getDate();
			
			if((currentDate.getDayOfMonth() == date.getDayOfMonth()) && (currentDate.getDayOfYear() == date.getDayOfYear()))
				totalDeposit++;
		}
		
		//Se le suma al saldo actual el porcentaje de rentabilidad actual del total de depositos en el mes actual
		setBalance(getBalance() + (int) (totalDeposit * this.profitabilityPercentage));
	}
	
	/**
	 * Resta la cantidad "amount" del saldo mas el porcentaje de rentabilidad como penalizacion por
	 * retirar dinero de la cuenta, guardando la cuenta destino "accountNumber"
	 * @param amount cantidad a transferir
	 * @param asociatedAccount numero de cuenta a la que se realiza la transferencia
	 * */
	public void transferTo(int amount, Account asociatedAccount) {
		super.transferTo(amount * (int) (1 + this.profitabilityPercentage), asociatedAccount);
	}

	/**
	 * Establece el porcentaje de rentabilidad actual de la cuenta.
	 * @param profitabilityPercentage nuevo porcentaje de rentabilidad de la cuenta.
	 * */
	public void setProfitabilityPercentage(double profitabilityPercentage) {
		this.profitabilityPercentage = profitabilityPercentage;
	}
	
	/**
	 * @return porcentaje de rentabilidad actual de la cuenta.
	 * */
	public double getProfitabilityPercentage() {
		return this.profitabilityPercentage;
	}
}
