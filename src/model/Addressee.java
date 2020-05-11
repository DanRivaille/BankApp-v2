package model;
/**
 * Clase que modelara la informacion de un destinatario
 * 
 *  @author DanSantos
 *  @version 29-03-2020
 * */

public class Addressee {
	
	private String accountNumber;			//Numero de la cuenta.
	private String name;					//Nombre asociado del destinatario.
	private boolean favorite;				//Indica si el destinatario se guardo como favorito.
	
	/**
	 * Constructor predeterminado, establece los valores por defecto de los atributos
	 * */
	public Addressee() {
		this("00000000-0");			//llama al constructor de abajo con el valor por defecto del nunmero de cuenta
	}
	
	/**
	 * Constructor que solo establece el numero de cuenta del destinatario
	 * @param accountNumber numero de cuenta inicial del destinatario 
	 * */
	public Addressee(String accountNumber) {
		this.accountNumber = accountNumber;
		this.name = "";
		this.favorite = false;
	}
	
	/**
	 * Constructor que establece todos los atributos de la clase con los parametros ingresados
	 * @param accountNumber numero de cuenta del destinatario, se asume que ha sido validado
	 * @param name nombre del destinatario
	 * @param isFavorite indica si el destinatario esta guardado como favorito o no
	 * */
	public Addressee(String accountNumber, String name, boolean isFavorite) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.favorite = isFavorite;
	}
	
	/**
	 * @return el numero de cuenta del destinatario
	 * */
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	/**
	 * @return nombre actual del destinatario
	 * */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return indica si el destinatario esta guardado como favorito o no
	 * */
	public boolean isFavorite() {
		return this.favorite;
	}
	
	/**
	 * @param newAccountNumber establece un nuevo numero de cuenta
	 * */
	public void setAccountNumber(String newAccountNumber) {
		this.accountNumber = newAccountNumber;
	}
	
	/**
	 * @param newName establece un nuevo nombre al destinatario
	 * */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	 * @param isFavorite indica si se quiere guardar como favorito o no
	 * */
	public void setFavorite(boolean isFavorite) {
		this.favorite = isFavorite;
	}
}
