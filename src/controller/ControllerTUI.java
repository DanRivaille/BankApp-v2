package controller;
import view.*;
import model.*;
import java.util.ArrayList;

/**
 * Clase correspondiente al controlador del patron MVC
 * */

public class ControllerTUI {

	private ViewTUI viewTUI;					//Vista del patron MVC a ser controlada.
	private Bank bank;							//Modelo del patron MVC a controlar.
	
	/**
	 * Contructor, establece los atributos de la clase
	 * @param bank intancia de la clase modelo
	 * */
	public 	ControllerTUI(Bank bank) {
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
	public void setModel(Bank bank) {
		this.bank = bank;
	}
	
	/**
	 * Busca una cuenta desde el modo administrador, usa el numero de cuenta para buscarla en el mapa de cuentas validas
	 * guardadas en el sistema.
	 * */
	public void searchNumberAccount() {
		String accountNumber;
		Account account;
		
		this.viewTUI.setOutput("Ingrese el numero de cuenta a buscar: ");
		accountNumber = this.viewTUI.getInput();
		
		account = this.bank.getAccount(accountNumber);
		
		if(null == account) {
			this.viewTUI.setOutput("No existe una cuenta con el numero de cuenta ingresado.\n");
		}
		else {
			account.showInfo();
		}
	}
	
	/**
	 * Busca una cuenta con un rango especifico, desde el modo administrador, lee el limite inferior y el limite superior de
	 * un rango, valida que sea valido (limite inferior es menor que el limite superior), y muestra todas las cuentas
	 * que entren en ese rango
	 * */
	public void searchRankAccount() {
		int lowerLimit;
		int upperLimit;
		
		this.viewTUI.setOutput("Ingrese el rango inferior: ");
		lowerLimit = Integer.parseInt(this.viewTUI.getInput());
		
		this.viewTUI.setOutput("Ingrese el rango superior: ");
		upperLimit = Integer.parseInt(this.viewTUI.getInput());
		
		if(upperLimit < lowerLimit) {
			this.viewTUI.setOutput("Rango ingresado no valido");
		}
		else {
			ArrayList<Account> accounts = this.bank.getAccountBalanceRank(lowerLimit, upperLimit);
			
			for(Account account : accounts) {
				account.showInfo();
				this.viewTUI.setOutput("\n");
			}
		}
	}

	/**
	 * Agrega una nueva cuenta al sistema, valida que no exista una cuenta con el numero de cuenta ingresado.
	 * */
	public void addAccount() {
		String accountNumber;
		
		this.viewTUI.setOutput("Ingrese el nuevo numero de la cuenta: ");
		accountNumber = this.viewTUI.getInput();
		
		
		if(!Account.isValid(accountNumber)) {
			this.viewTUI.setOutput("Numero de cuenta ingresado no valido.\n");
		}	
		else if(this.bank.existsAccount(accountNumber)) {
			this.viewTUI.setOutput("Ya existe una cuenta con el numero de cuenta ingresado.\n");
		}
		else {
			this.bank.addAccount(accountNumber);
			this.viewTUI.setOutput("Cuenta con numero (" + accountNumber + ") agregador correctamente.\n");
		}
	}
	
	/**
	 * Elimina una cuenta del sistema, valida que exista una cuenta con el numero de cuenta ingresado.
	 * */
	public void removeAccount() {
		String accountNumber;
		
		this.viewTUI.setOutput("Ingrese el nuevo numero de la cuenta: ");
		accountNumber = this.viewTUI.getInput();
		
		if(this.bank.existsAccount(accountNumber)) {
			this.bank.removeAccount(accountNumber);
			this.viewTUI.setOutput("Cuenta con numero (" + accountNumber + ") agregador correctamente.\n");
		}
		else {
			this.viewTUI.setOutput("No existe una cuenta con el numero de cuenta ingresado.\n");
		}
	}
	
	/**
	 * Obtiene la informacion del nuevo destinatario a guardar y valida que no exista y en 
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
		else if(!this.bank.existsAccount(accountNumber)) {
			this.viewTUI.setOutput("No existe una cuenta con el numero de cuenta ingresado\n");
		}
		else if(client.existsAddressee(accountNumber)) {
			this.viewTUI.setOutput("Ya existe un destinatario con el numero de cuenta ingresado\n");
		}
		else {
			client.addAddressee(new Addressee(this.bank.getAccount(accountNumber), name, isFavorite));
			this.viewTUI.setOutput("Destinatario guardado correctamente\n");
		}
	}
	
	/**
	 * Edita los atributos editables de un destinatario
	 * */
	public void editAddressee() {
		//Numero de cuenta del destinatario a editar
		String accountNumber;
		Client client = this.bank.getClient();

		this.viewTUI.setOutput("Numero de cuenta del destinatario a editar: ");
		accountNumber = this.viewTUI.getInput();

		if(!Account.isValid(accountNumber)) {
			this.viewTUI.setOutput("Numero de cuenta ingresado no valido\n");
		}
		else if(!client.existsAddressee(accountNumber)) {
			this.viewTUI.setOutput("No existe un destinatario con el numero de cuenta ingresado\\n");
		}
		else {
			Addressee addressee = client.getAddressee(accountNumber);
			
			String name;
			boolean isFavorite;

			this.viewTUI.setOutput("Nombre: ");
			name = this.viewTUI.getInput();

			this.viewTUI.setOutput("Guardarlo como favorito (1 - si, 2 - no): ");
			isFavorite = (this.viewTUI.getInput().equals("1")) ? true : false;
			
			addressee.setName(name);
			addressee.setFavorite(isFavorite);
			
			this.viewTUI.setOutput("Destinatario modificado correctamente\n");
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
	
	/**
	 * Realiza una transferencia de la cuenta origen elegida por el usuario, a la  cuenta destino.
	 * */
	public void makeTransfer() {
		Account originAccount = chooseMyAccount();
		
		if(originAccount != null) {
			Account destinyAccount = chooseDestinyAccount();
			
			if(destinyAccount != null) {
				this.viewTUI.setOutput("Ingrese el monto a transferir: ");
				
				
				this.viewTUI.setOutput("Cuenta origen: \n\n");
				originAccount.showInfo();
				
				this.viewTUI.setOutput("\n\nCuenta destino: \n\n");
				destinyAccount.showInfo();
			}
		}
	}
	
	/**
	 * Muestra la cuenta seleccionada por el usuario
	 * */
	public void showAccount() {
		Account account = chooseMyAccount();

		if(account != null)
			account.showInfo();
	}
	
	/**
	 * El usuario selecciona la cuenta propia en la que quiere operar.
	 * */
	public Account chooseMyAccount() {
		Client client = this.bank.getClient();
		Account account;
		int option;
		
		this.viewTUI.setOutput("Seleccione cuenta (1 - cuenta rut, 2 - cuenta ahorro): ");
		option = this.viewTUI.readOption();
		
		if(option == 1) {
			account = client.getAccount(TypeAccount.RUT_ACCOUNT);
		}
		else if(option == 2){
			account = client.getAccount(TypeAccount.SAVING_ACCOUNT);
		}
		else {
			this.viewTUI.setOutput("Opcion ingresada no valida\n");
			account = null;
		}
		
		return account;
	}
	
	/**
	 * Obtiene la cuenta seleccionada por el usuario dentro de las cuentas del sistema.
	 * */
	public Account chooseDestinyAccount() {
		String accountNumber;
		Account account = null;
		
		this.viewTUI.setOutput("Ingrese el numero de cuenta: ");
		accountNumber = this.viewTUI.getInput();
		
		if(this.bank.existsAccount(accountNumber))
			account = this.bank.getAccount(accountNumber);
		else
			this.viewTUI.setOutput("No se encontro una cuenta asociado al numero de cuenta ingresado");
		
		
		return account;
	}
}