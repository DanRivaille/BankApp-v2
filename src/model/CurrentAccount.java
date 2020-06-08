package model;

/**
 * Clase que modela una cuenta corriente, hereda de la clase abstracta Account, 
 * e implementa el metodo makeImpositions de la forma en que lo realiza un cuenta corriente,
 * descuenta del saldo actual la cantidad del costo de mantencion de la cuenta.
 * @author Dan Santos
 * @version 04-06-2020
 * */
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
	 * Resta la cantidad "amount" del saldo mas un 5% del costo de mantencion total que se cobra como costo por
	 * administracion de la cuenta, guardando la cuenta destino "accountNumber"
	 * @param amount cantidad a transferir
	 * @param asociatedAccount numero de cuenta a la que se realiza la transferencia
	 * */
	public void transferTo(int amount, Account asociatedAccount) {
		super.transferTo(amount + (int) (0.05f * this.maintenanceCost), asociatedAccount);
	}
	
	/**
	 * Establece el nuevo costo de mantencion de la cuenta.
	 * @param maintenanceCost nuevo costo de mantencion de la cuenta.
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
