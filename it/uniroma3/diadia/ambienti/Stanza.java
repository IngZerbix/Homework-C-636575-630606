package it.uniroma3.diadia.ambienti;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	private String nome;
	
	private Map<String, Attrezzo> attrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;


	public Stanza(String nome) {
		this.nome = nome;
		this.attrezzi = new HashMap<>();
		this.stanzeAdiacenti = new HashMap<>();
	}


	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		if(direzione != null) {
			this.stanzeAdiacenti.put(direzione, stanza);
		}
	}



	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo!=null) { 
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
		
		return false;
	}


	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}


	public boolean removeAttrezzo(Attrezzo attrezzo) {
		
		if(attrezzo != null) {
			return this.attrezzi.remove(attrezzo.getNome()) != null;
		}
		
		return false;
	}


	public Set<Direzione> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}
	
	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		
		risultato.append("\nUscite: ");
		for (Direzione direzione : this.stanzeAdiacenti.keySet())
				risultato.append(" " + direzione);
		
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi.values()) 
				risultato.append(attrezzo.toString()).append(" ");
		
		
		return risultato.toString();
	}
	
	
	
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}


	public String getNome() {
		return this.nome;
	}

	public String getDescrizione() {
		return this.toString();
	}


	public Collection<Attrezzo> getAttrezzi() {
		return this.attrezzi.values();
	}
	
	
	public int getAttrezzoNumero() {
		return this.attrezzi.size();
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}
	
	public void setPersonaggio(AbstractPersonaggio personaggio) {
	    this.personaggio = personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
	    return this.personaggio;
	}

}