package main;

import controller.*;
import model.*;
import view.*;
import exceptions.*;

/**
 * Clase principal de la aplicacion
 * @author Dan Santos
 * @version 11-05-2020
 * */

public class BankApp {
	
	public static void main(String args[]) {
		Bank bank = new Bank();
		ControllerTUI controllerTUI = new ControllerTUI(bank);
		ViewTUI viewTUI = new ViewTUI();
		
		controllerTUI.setView(viewTUI);
		viewTUI.setController(controllerTUI);
		
		Client client = new Client("Dan Santos", "20360262-6");
		bank.setClient(client);
		
		try {
			bank.addAccount(new CurrentAccount("20360262-1"));
			bank.addAccount(new SavingAccount("20360262-2"));
			bank.addAccount(new CurrentAccount("00000000-1"));
			bank.addAccount(new SavingAccount("00000001-2"));
			bank.addAccount(new CurrentAccount("00000002-1"));
		} catch (WrongAccountNumber e) {
			viewTUI.setOutput("Algun numero de cuenta de los datos iniciales esta erroneo\n");
		}
		
		client.addAddressee(new Addressee(bank.getAccount("00000000-1"), "Contador", false));
		client.addAddressee(new Addressee(bank.getAccount("00000001-2"), "Dentista", true));
		client.addAddressee(new Addressee(bank.getAccount("00000002-1"), "Universidad", false));
		
		viewTUI.mainMenu();
	}
}
