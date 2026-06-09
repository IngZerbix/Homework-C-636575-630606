package it.uniroma3.diadia.comandi;

import java.io.File;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		StringBuilder elencoComandi = new StringBuilder("Comandi disponibili: ");
		
		try {
			
			String path = this.getClass().getResource("").getPath();
			File cartella = new File(path);

			if (cartella.exists() && cartella.isDirectory()) {
				
				File[] files = cartella.listFiles();
				
				for (File file : files) {
					String nomeFile = file.getName();
					
					
					if (nomeFile.startsWith("Comando") && nomeFile.endsWith(".class") && !nomeFile.equals("ComandoNonValido.class")) {
						

						String nomeComando = nomeFile.substring(7, nomeFile.length() - 6).toLowerCase();
						
						elencoComandi.append(nomeComando).append(" ");
					}
				}
			}
		} catch (Exception e) {
			this.getIo().mostraMessaggio("Errore nella lettura dei comandi.");
		}
		
		this.getIo().mostraMessaggio(elencoComandi.toString());
	}

	@Override
	public String getNome() {
		return "aiuto";
	}
}