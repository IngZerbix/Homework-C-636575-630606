package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public interface Comando {

	/**
	 * esecuzione del comando
	 */
	
	public void esegui(Partita partita);

	/**
	 * set parametro del comando
	 */
	
	public void setParametro(String parametro);
	
	/**
	 * set IOConsole del comando
	 */
	
	public void setIO(IO io);
	
	/**
	 * get parametro del comando
	 */
	
	public String getParametro();
	
	/**
	 * get Nome del comando
	 */
	
	public String getNome();
}