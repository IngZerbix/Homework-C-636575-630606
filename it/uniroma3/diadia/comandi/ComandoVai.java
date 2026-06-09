package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {


	@Override
	public void esegui(Partita partita) {
		if (this.getParametro() == null) {
			this.getIo().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}

		Direzione direzione;
		try {
		
			direzione = Direzione.valueOf(this.getParametro().toUpperCase());
		} catch (IllegalArgumentException e) {
			this.getIo().mostraMessaggio("Direzione inesistente");
			return;
		}

		Stanza prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		
		if (prossimaStanza == null) {
			this.getIo().mostraMessaggio("Direzione bloccata o inesistente");
			return;
		}

		partita.setStanzaCorrente(prossimaStanza);
		this.getIo().mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}

	@Override
	public String getNome() {
		return "vai";
	}


}