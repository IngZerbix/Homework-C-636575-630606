package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO_CANE = "Grrr... Ti ho morso! (-1 CFU)";
	private String ciboPreferito;
	private Attrezzo attrezzoDaLasciare; 

	public Cane(String nome, String presentaz, String ciboPreferito, Attrezzo attrezzoDaLasciare) {
		super(nome, presentaz);
		this.ciboPreferito = ciboPreferito;
		this.attrezzoDaLasciare = attrezzoDaLasciare;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		return MESSAGGIO_CANE;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo.getNome().equals(this.ciboPreferito)) {
			String risposta = "Arf! Il cane mangia " + attrezzo.getNome() + " scodinzolando.";
			if (this.attrezzoDaLasciare != null) {
				risposta += " Dalla gioia ha lasciato cadere un oggetto!";
				partita.getStanzaCorrente().addAttrezzo(this.attrezzoDaLasciare);
				this.attrezzoDaLasciare = null;
			}
			return risposta;
		} else {
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
			return "Grrr! Questo non è " + this.ciboPreferito + "! Ti morde! (-1 CFU)";
		}
	}
}