import java.util.HashMap;

/**
 * Clase principal de la aplicacion
 * 
 * @author DanSantos
 * @version 04-04-2020
 * */

public class BankApp {

	public static void main(String args[]) {
		BankApp bankApp = new BankApp();
		
		bankApp.accounts.put("20360262-1", new Account("20360262-1"));
		bankApp.accounts.put("12010421-2", new Account(150_000, "12010421-2"));
		bankApp.accounts.put("20410221-1", new Account(50_000, "20410221-1"));
		bankApp.accounts.put("16211031-2", new Account("16211031-2"));
		bankApp.accounts.put("16021002-2", new Account(100_000, "16021002-2"));
		
		Account account1 = bankApp.accounts.get("20360262-1");
		Account account2 = bankApp.accounts.get("12010421-2");
		
		account2.transferTo(50_000, "20360262-1");
		account1.depositFrom(50_000, "12010421-2");
		
		account1.showInfo();
		account2.showInfo();

	}
	
	public BankApp() {
		this.accounts = new HashMap<String, Account>();
	}
	
	//Atributos
	public HashMap <String, Account> accounts;

}
