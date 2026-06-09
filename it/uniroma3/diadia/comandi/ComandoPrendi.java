  package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando{
	

	
	@Override
	public void esegui(Partita partita) {
		Stanza stanza= partita.getStanzaCorrente();
		
		if(this.getParametro() == null) {
			getIo().mostraMessaggio("Devi specificare il nome dell'oggetto che vuoi raccogliere\n");
			return;
		}
		
		Attrezzo attrezzo = stanza.getAttrezzo(getParametro());
		
		if(attrezzo != null) {
			
			if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
				stanza.removeAttrezzo(attrezzo);
				this.getIo().mostraMessaggio("L'oggetto: " + getParametro() + " è stato aggiunto alla borsa.");
			}else {
				this.getIo().mostraMessaggio("L'oggetto: " + getParametro() + " è troppo pesante o la borsa è piena.");
				return;
			}
		}else {
			this.getIo().mostraMessaggio("L'oggetto: " + getParametro() + " non è presente nella stanza.");
		}
	}
	
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "prendi";
	}

}
