package main;

import controller.ControllerTUI;
import model.*;
import view.ViewTUI;

public class BankApp {
	public static void main(String args[]) {
		Bank bank = new Bank();
		ControllerTUI controllerTUI = new ControllerTUI(bank);
		ViewTUI viewTUI = new ViewTUI();
		
		controllerTUI.setView(viewTUI);
		viewTUI.setController(controllerTUI);
		
		Client client = new Client("Dan Santos", "20360262-6");
		bank.setClient(client);
		
		client.addAddressee(new Addressee("23404121-3", "Contador", false));
		client.addAddressee(new Addressee("13404345-3", "Mensualidad", false));
		client.addAddressee(new Addressee("19202175-5", "Dentista", true));
		client.addAddressee(new Addressee("20313773-1", "Universidad", true));
		
		bank.addAccount("20360262-1");
		
		viewTUI.mainMenu();
	}
}
