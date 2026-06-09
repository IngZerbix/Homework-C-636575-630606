package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando{

	
	@Override
	public void esegui(Partita partita) {
		
		Borsa borsa = partita.getGiocatore().getBorsa();
		
		if (this.getParametro() == null) {
			this.getIo().mostraMessaggio("Se vuoi posare qualcosa devi specificare");
			return ;
		}
		
		if(borsa.isEmpty()) {
			this.getIo().mostraMessaggio("La borsa è vuota");
			return;
		}
		
		if(borsa.hasAttrezzo(this.getParametro())) {
			Attrezzo attrezzo = borsa.getAttrezzo(this.getParametro());
			
			
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			
			this.getIo().mostraMessaggio("L'oggetto: " + attrezzo + " è stato posato.\n");
		}else {
			this.getIo().mostraMessaggio("L'oggetto: " + this.getParametro() + " non è presente nella borsa.\n");
		}
	}

	@Override
	public String getNome() {
		return "posa";
	}
}
