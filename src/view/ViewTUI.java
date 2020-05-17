package view;
import controller.*;
import java.io.*;

/**
 * Clase correspondiente a la vista del patron MVC 
 * */

public class ViewTUI {

	private BufferedReader keyboardInput;				//Lector de la entrada del usuario por teclado.
	private ControllerTUI controllerTUI;				//Controlador de la vista.
	
	/**
	 * Contructor, establece todos los atributos de la clase
	 * @param controllerTUI instancia del controlador de la clase vista
	 * */
	public ViewTUI(ControllerTUI controllerTUI) {
		this();
		this.controllerTUI = controllerTUI;
	}

	/**
	 * Contructor, establece el lector del teclado
	 * */
	public ViewTUI() {
		this.keyboardInput = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * Establece el controlador de la clase
	 * @param controllerTUI instancia del controlador de la clase vista
	 * */
	public void setController(ControllerTUI controllerTUI) {
		this.controllerTUI = controllerTUI;
	}

	/**
	 * Menu principal de la aplicacion.
	 * */
	public void mainMenu() {
		int option;

		System.out.println("Bienvenido a BankApp\n");
		System.out.println("1 - Modo Administrador");
		System.out.println("2 - Modo Cliente");
		System.out.println("3 - Salir");
		System.out.print("\nIngrese una opcion: ");

		option = readOption();
		
		while((option < 1) || (option > 3)) {
			System.out.println("Opcion no valida, intentelo nuevamente"); 
			pause();
			
			option = readOption();
		}

		switch(option) {
			case 1  : mainMenuAdmin(); break;
			case 2  : mainMenuClient(); break;
		}
		
		System.out.println("Gracias vuelva pronto."); 
		pause();
	}
	
	/**
	 * Menu principal de la aplicacion en modo administrador.
	 * */
	public void mainMenuAdmin() {
		int option;

		do {
			System.out.println("Bienvenido a BankApp\n");
			System.out.println("1 - Buscar Cuenta por numero de cuenta");
			System.out.println("2 - Buscar Cuenta por rango de saldo");
			System.out.println("3 - Agregar Cuenta");
			System.out.println("4 - Eliminar Cuenta");
			System.out.println("5 - Salir");
			System.out.print("\nIngrese una opcion: ");

			option = readOption();

			switch(option) {
				case 1  : this.controllerTUI.searchNumberAccount();	pause();	break;
				case 2  : this.controllerTUI.searchRankAccount();	pause();	break;
				case 3  : this.controllerTUI.addAccount();		pause();	break;
				case 4  : this.controllerTUI.removeAccount();	pause();	break;
				case 5  : break;
				default : System.out.println("Opcion no valida, intentelo nuevamente"); pause();
			}

		}while(option != 5);
	}
	
	/**
	 * Menu principal de la aplicacion en modo cliente.
	 * */
	public void mainMenuClient() {
		int option;

		do {
			System.out.println("Bienvenido a BankApp\n");
			System.out.println("1 - Mis Cuentas");
			System.out.println("2 - Transferencias");
			System.out.println("3 - Salir");
			System.out.print("\nIngrese una opcion: ");

			option = readOption();

			switch(option) {
				case 1  : this.controllerTUI.showAccount();   pause(); break;
				case 2  : menuTransfer(); 					  		   break;
				case 3  : break;
				default : System.out.println("Opcion no valida, intentelo nuevamente"); pause();
			}

		}while(option != 3);
	}
	
	/**
	 * Menu de transferencias
	 * */
	public void menuTransfer() {
		int option;

		do {
			System.out.println("1 - Transferir");
			System.out.println("2 - Destinatarios");
			System.out.println("3 - Volver");
			System.out.print("\nIngrese una opcion: ");

			option = readOption();

			switch(option) {
				case 1  : this.controllerTUI.makeTransfer(); pause(); break;
				case 2  : menuAddressees(); break;
				case 3  : break;
				default : System.out.println("Opcion no valida, intentelo nuevamente"); pause();
			}

		}while(option != 3);
	}
	
	/**
	 * Menu de destinatarios
	 * */
	public void menuAddressees() {
		int option;

		do {
			System.out.println("1 - Destinatarios favoritos");
			System.out.println("2 - Destinatarios regulares");
			System.out.println("3 - Agregar destinatario");
			System.out.println("4 - Eliminar destinario");
			System.out.println("5 - Modificar destinario");
			System.out.println("6 - Volver");
			System.out.print("\nIngrese una opcion: ");

			option = readOption();

			switch(option) {
				case 1  : this.controllerTUI.showFavoritesAddressees(); pause(); break;
				case 2  : this.controllerTUI.showAddressees(); 			pause(); break;
				case 3  : this.controllerTUI.addAddressee(); 			pause(); break;
				case 4  : this.controllerTUI.removeAddressee(); 		pause(); break;
				case 5  : this.controllerTUI.editAddressee(); 			pause(); break;
				case 6	: break;
				default : System.out.println("Opcion no valida, intentelo nuevamente"); pause();
			}

		}while(option != 6);
	}

	/**
	 * Obtiene la entrada por teclado del usuario.
	 * @return objeto String que contiene la linea ingresada por el usuario.
	 * */
	public String getInput() {
		String line;

		try {
			line = this.keyboardInput.readLine();
		}
		catch(IOException e) {
			line = "";
		}

		return line;
	}

	/**
	 * Imprime el texto ingresado por pantalla.
	 * @param line texto a ser mostrado por pantalla.
	 * */
	public void setOutput(String line) {
		System.out.print(line);
	}

	/**
	 * Simula el pause y el system("cls") de C
	 * */
	public void pause() {
		System.out.print("Pulse una tecla para continuar...");
		getInput();
		System.out.print("\n\n\n\n\n\n\n\n");
	}

	/**
	 * Lee la opcion ingresada por el usuario.
	 * @return opcion ingresada por el usuario.
	 * */
	public int readOption() {
		int option;
		
		try {
			option = Integer.parseInt(getInput());
		}
		catch(NumberFormatException e) {
			option = -1;
		}
		
		return option;
	}
}