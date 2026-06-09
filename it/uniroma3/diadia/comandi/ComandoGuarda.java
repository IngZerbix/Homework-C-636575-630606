package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoGuarda extends AbstractComando{
	
	@Override
	public void esegui(Partita partita) {
		
		this.getIo().mostraMessaggio("Si stampano tutte le informazioni della Stanza corrente:\n");
		this.getIo().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		
		this.getIo().mostraMessaggio("\nSi stampano le informazioni sullo stato della partita: ");
		this.getIo().mostraMessaggio("I CFU rimanenti sono: " + partita.getGiocatore().getCfu());
		
		Borsa borsa = partita.getGiocatore().getBorsa();
		
		if (borsa.isEmpty()) {
			this.getIo().mostraMessaggio("La borsa è vuota.");
		}else {
			
			if (this.getParametro() == null) {
				this.getIo().mostraMessaggio("Contenuto borsa (standard): " + borsa.toString());
			} 
			else if (this.getParametro().equalsIgnoreCase("peso")) {
				this.getIo().mostraMessaggio("Contenuto borsa (ordinato per peso): " + borsa.getContenutoOrdinatoPerPeso().toString());
			} 
			else if (this.getParametro().equalsIgnoreCase("nome")) {
				this.getIo().mostraMessaggio("Contenuto borsa (ordinato per nome): " + borsa.getContenutoOrdinatoPerNome().toString());
			} 
			else if (this.getParametro().equalsIgnoreCase("gruppi")) {
				this.getIo().mostraMessaggio("Contenuto borsa (raggruppato per peso): " + borsa.getContenutoRaggruppatoPerPeso().toString());
			} 
			else {
				this.getIo().mostraMessaggio("Parametro '" + this.getParametro() + "' non riconosciuto. Uso il formato standard.");
				this.getIo().mostraMessaggio("Contenuto borsa: " + borsa.toString());
			}
		}
	}

	@Override
	public String getNome() {
		return "guarda";
	}
}
