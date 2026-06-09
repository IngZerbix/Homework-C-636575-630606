package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		
		if (personaggio == null) {
			this.getIo().mostraMessaggio("Non c'è nessuno a cui regalare qualcosa in questa stanza!");
			return;
		}
		
		if (this.getParametro() == null) {
			this.getIo().mostraMessaggio("Cosa vuoi regalare? Devi specificare il nome di un oggetto.");
			return;
		}
		
		Attrezzo attrezzoDaRegalare = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
		
		if (attrezzoDaRegalare == null) {
			this.getIo().mostraMessaggio("Non hai un oggetto chiamato '" + this.getParametro() + "' nella tua borsa!");
			return;
		}
		
		partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro()); 
		String risposta = personaggio.riceviRegalo(attrezzoDaRegalare, partita); 
		
		this.getIo().mostraMessaggio(risposta);
	}

	@Override
	public String getNome() {
		return "regala";
	}
}