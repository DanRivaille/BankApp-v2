package controller;
import view.*;
import model.*;
import java.util.ArrayList;

/**
 * Clase correspondiente al controlador del patron MVC
 * */

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

	/**
	 * Obtiene la informacion del nuevo destinatario a guardar y valida que no exita y en 
	 * los destinatarios guardados del cliente.
	 * */
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
		if(!Account.isValid(accountNumber)) {
			this.viewTUI.setOutput("Numero de cuenta ingresado no valido\n");
		}
		else if(client.existsAddressee(accountNumber)) {
			this.viewTUI.setOutput("Ya existe un destinatario con el numero de cuenta ingresado\n");
		}
		else {
			client.addAddressee(new Addressee(accountNumber, name, isFavorite));
			this.viewTUI.setOutput("Destinatario guardado correctamente\n");
		}
	}
	
	/**
	 * Muestra los destinatarios guardados por el cliente.
	 * */
	public void showAddressees() {
		ArrayList<Addressee> addressees = this.bank.getClient().getAddressees();
		
		for(Addressee addressee : addressees) {
			this.viewTUI.setOutput("Nombre: " + addressee.getName() + " - Numero de cuenta: " + addressee.getAccountNumber() + '\n');
		}
	}
	
	/**
	 * Muestra los destinatarios guardados como favoritos por el cliente.
	 * */
	public void showFavoritesAddressees() {
		ArrayList<Addressee> addressees = this.bank.getClient().getAddressees();
		
		for(Addressee addressee : addressees) {
			if(addressee.isFavorite())
				this.viewTUI.setOutput("Nombre: " + addressee.getName() + " - Numero de cuenta: " + addressee.getAccountNumber() + '\n');
		}
	}
	
	/**
	 * Obtiene los datos del destinatario a eliminar, valida que exista y lo elimina.
	 * */
	public void removeAddressee() {
		String accountNumber;

		this.viewTUI.setOutput("Numero de cuenta del destinatario a eliminar: ");
		accountNumber = this.viewTUI.getInput();

		Client client = this.bank.getClient();
		if(!Account.isValid(accountNumber)) {
			this.viewTUI.setOutput("Numero de cuenta ingresado no valido\n");
		}
		else if(client.existsAddressee(accountNumber)) {
			client.removeAddressee(accountNumber);
			this.viewTUI.setOutput("Destinatario eliminado correctamente\n");
		}
		else {
			this.viewTUI.setOutput("No existe un destinatario con el numero de cuenta ingresado\\n");
		}
	}

	//Atributos
	private ViewTUI viewTUI;
	private BankApp bank;
}