package controller;
import view.*;
import model.*;

public class ControllerTUI {

	/**
	 * Contructor, establece los atributos de la clase
	 * @param bank intancia de la clase modelo
	 * */
	public 	ControllerTUI(BankApp bank) {
		this.bank = bank;
	}

	/**
	 * Contructor predeterminado
	 * */
	public 	ControllerTUI() {
	}

	/**
	 * Establece la instacia del atributo de la clase vista a ser controlada.
	 * */
	public void setView(ViewTUI viewTUI) {
		this.viewTUI = viewTUI;
	}
	
	/**
	 * Establece la instancia del atributo de la clase modelo.
	 * */
	public void setModel(BankApp bank) {
		this.bank = bank;
	}

	public void addAddressee() {
		String name;
		String accountNumber;
		boolean isFavorite;

		this.viewTUI.setOutput("Nombre: ");
		name = this.viewTUI.getInput();

		this.viewTUI.setOutput("Numero de cuenta: ");
		accountNumber = this.viewTUI.getInput();

		this.viewTUI.setOutput("Guardarlo como favorito (1 - si, 2 - no): ");
		isFavorite = (this.viewTUI.getInput().equals("1")) ? true : false;
		
		Client client = this.bank.getClient();
		if(client.existsAddressee(accountNumber))
			this.viewTUI.setOutput("Ya existe un destinatario con el numero de cuenta ingresado\n");
		else
			client.addAddressee(new Addressee(accountNumber, name, isFavorite));
	}

	//Atributos
	private ViewTUI viewTUI;
	private BankApp bank;
}