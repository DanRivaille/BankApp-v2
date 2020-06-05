package model;

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
	 * Constructor por defecto de la clase, establece el porcentaje de rentabilidad en 0.1%
	 * */
	public SavingAccount() {
		this.profitabilityPercentage = 0.1f; 
	}
	
	/**
	 * Constructor, establece el porcentaje de rentabilidad en 0.1%
	 * */
	public SavingAccount(int balance, String accountNumber) {
		super(balance, accountNumber);
		this.profitabilityPercentage = 0.1f;
	}
	
	/**
	 * Constructor, establace los atributos de la clase.
	 * */
	public SavingAccount(int balance, String accountNumber, double profitabilityPercentage) {
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
				
				//Se recorre el historial
				while(history.hasNext()) {
					//Se obtiene la transaccion actual
					Transaction transaction = history.next();
					LocalDate date = transaction.getDate();
					
					if(currentDate.getDayOfMonth() == date.getDayOfMonth())
						totalDeposit++;
				}
				
				//Se le suma al saldo actual el porcentaje de rentabilidad actual del total de depositos en el mes actual
				setBalance(getBalance() + (int) (totalDeposit * this.profitabilityPercentage));
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
