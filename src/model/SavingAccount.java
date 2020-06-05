package model;

import java.util.ListIterator;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

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
