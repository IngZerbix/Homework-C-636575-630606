package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO{
	
	private List<String> righeDaLeggere;
	private int indiceRigaCorrente;
	
	private List<String> messaggiProdotti;
	
	public IOSimulator(List<String> righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.indiceRigaCorrente = 0;
		this.messaggiProdotti = new ArrayList<>();
	}
	
	

	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti.add(messaggio);
	}

	@Override
	public String leggiRiga() {
		
				if (this.indiceRigaCorrente < this.righeDaLeggere.size()) {
					String riga = this.righeDaLeggere.get(this.indiceRigaCorrente);
					this.indiceRigaCorrente++;
					return riga;
				} else {
					
					return "fine";	
				}
	}
	
	public List<String> getMessaggiProdotti() {
		return this.messaggiProdotti;
	}
	
	public boolean hasMessaggio(String stringa) {
		for (String messaggio : this.messaggiProdotti) {
			if (messaggio.contains(stringa)) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public String toString() {
		StringBuilder stringa = new StringBuilder();
		
		stringa.append("Storico messaggi prodotti:\n");
		for (String m : this.messaggiProdotti) {
			stringa.append(m).append("\n");
		}
		
		return stringa.toString();
	}
}
