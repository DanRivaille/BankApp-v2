package view;
import controller.*;
import java.io.*;

public class ViewTUI {

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

	public void mainMenu() {
		int option;

		do {
			System.out.println("Bienvenido a BankApp\n");
			System.out.println("1 - Destinatarios favoritos");
			System.out.println("2 - Destinatarios regulares");
			System.out.println("3 - Agregar destinatario");
			System.out.println("4 - Eliminar destinario");
			System.out.println("5 - Salir");
			System.out.print("\nIngrese una opcion: ");

			try {
				option = Integer.parseInt(getInput());
			}
			catch(NumberFormatException e) {
				option = -1;
			}

			switch(option) {
				case 1  : this.controllerTUI.showFavoritesAddressees(); break;
				case 2  : this.controllerTUI.showAddressees(); break;
				case 3  : this.controllerTUI.addAddressee(); break;
				case 4  : this.controllerTUI.removeAddressee(); break;
				case 5  : break;
				default : System.out.println("Opcion no valida, intentelo nuevamente");
			}

			pause();

		}while(option != 5);
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

	//Atributos
	private BufferedReader keyboardInput;
	private ControllerTUI controllerTUI;
}