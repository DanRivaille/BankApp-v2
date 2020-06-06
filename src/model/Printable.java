package model;

/**
 * Interfaz que define los metodos basicos para imprimir la informacion de una clase por pantalla.
 * @author Dan Santos
 * @version 06-06-2020 
 * */
public interface Printable {
	/**
	 * Metodo que muestra la informacion general de la clase.
	 * */
	public void showInfo();
	
	/**
	 * Metodo que muestra la informacion especifica de la clase. no omitiendo ninguna informacion
	 * relevante.
	 * */
	public void showEspecificInfo();
}
