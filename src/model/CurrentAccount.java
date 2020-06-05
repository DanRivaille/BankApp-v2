package model;

public class CurrentAccount extends Account {

	public int maintenanceCost;				//Costo de mantenimiento actual de la cuenta.
	
	/**
	 * Constructor por defecto de la clase.
	 * */
	public CurrentAccount() {
		this.maintenanceCost = 5_000;
	}

	/**
	 * Contructor, establece el costo de mantencion de la cuenta en $5.000
	 * */
	public CurrentAccount(int balance, String accountNumber) {
		super(balance, accountNumber);
		this.maintenanceCost = 5_000;
	}
	
	/**
	 * Constructor, establece el costo de mantencion ingresado.
	 * */
	public CurrentAccount(int balance, String accountNumber, int maintenanceCost) {
		super(balance, accountNumber);
		this.maintenanceCost = maintenanceCost;
	}
	
	/**
	 * Realiza las impociciones de la cuenta, cobra el total del costo de mantencion de la cuenta.
	 * */
	public void makeImpositions() {
		setBalance(getBalance() - this.maintenanceCost);
	}
	
	/**
	 * Establece el nuevo costo de mantencion de la cuenta.
	 * @param maintenance nuevo costo de mantencion de la cuenta.
	 * */
	public void setMaintenanceCost(int maintenanceCost) {
		this.maintenanceCost = maintenanceCost;
	}
	
	/**
	 * @return costo de mantencion total actual de la cuenta.
	 * */
	public int getMaintenanceCost() {
		return this.maintenanceCost;
	}
}
