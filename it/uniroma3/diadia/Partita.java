package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Partita {

	private Stanza stanzaCorrente;
	private Labirinto labirinto; 
	private boolean finita;
	private Giocatore giocatore; 
	
	public Partita() {
		this.finita = false;
		this.giocatore = new Giocatore();
		
		this.labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N11")
				.addStanza("Aula N10")
				.addStanza("Laboratorio Campus")
				
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.addAdiacenza("Atrio", "Aula N11", Direzione.EST)
				.addAdiacenza("Atrio", "Aula N10", Direzione.SUD)
				.addAdiacenza("Atrio", "Laboratorio Campus", Direzione.OVEST)
				
				.addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.EST)
				.addAdiacenza("Aula N11", "Atrio", Direzione.OVEST)
				
				.addAdiacenza("Aula N10", "Atrio", Direzione.NORD)
				.addAdiacenza("Aula N10", "Aula N11", Direzione.EST)
				.addAdiacenza("Aula N10", "Laboratorio Campus", Direzione.OVEST)
				
				.addAdiacenza("Laboratorio Campus", "Atrio", Direzione.EST)
				.addAdiacenza("Laboratorio Campus", "Aula N11", Direzione.OVEST)
				
				.addAdiacenza("Biblioteca", "Atrio", Direzione.SUD)
				
				.addAttrezzo("lanterna", 3, "Aula N10")
				.addAttrezzo("osso", 1, "Atrio")
				
				.getLabirinto();
		
		this.stanzaCorrente = this.labirinto.getEntrata();
	}
	
	public Partita(Labirinto labirinto){
		this.labirinto = labirinto;
		this.finita = false;
		this.stanzaCorrente = this.labirinto.getEntrata();
		this.giocatore = new Giocatore();
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.stanzaCorrente = labirinto.getEntrata(); 
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public boolean vinta() {
		return this.getStanzaCorrente() == this.labirinto.getUscita();
	}

	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	public void setFinita() {
		this.finita = true;
	}

	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu() != 0;
	}
}