package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuratore {

	private static final String NOME_FILE = "diadia.properties";
	
	private static int cfuIniziali;
	private static int pesoMaxBorsa;

	static {
		Properties prop = new Properties();
		
		
		try (InputStream input = Configuratore.class.getClassLoader().getResourceAsStream(NOME_FILE)) {
			
			if (input == null) {
				System.err.println("ATTENZIONE: File " + NOME_FILE + " non trovato. Uso i valori di default.");
				cfuIniziali = 20;
				pesoMaxBorsa = 10;
			} else {
				prop.load(input);
				cfuIniziali = Integer.parseInt(prop.getProperty("cfu_iniziali"));
				pesoMaxBorsa = Integer.parseInt(prop.getProperty("peso_max_borsa"));
			}
			
		} catch (IOException | NumberFormatException ex) {
			System.err.println("Errore nel caricamento delle properties. Uso i valori di default.");
			cfuIniziali = 20;
			pesoMaxBorsa = 10;
		}
	}

	public static int getCFUIniziali() {
		return cfuIniziali;
	}

	public static int getPesoMaxBorsa() {
		return pesoMaxBorsa;
	}
}