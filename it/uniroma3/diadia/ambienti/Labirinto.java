package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {
	
	private Stanza entrata;
	private Stanza uscita;
	
	private Labirinto() {
	}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	
	public Stanza getEntrata() {
		return this.entrata;
	}
	
	public Stanza getUscita() {
		return this.uscita;
	}
	
	public void setEntrata(Stanza entrata) {
		this.entrata = entrata;
	}

	public void setUscita(Stanza uscita) {
		this.uscita = uscita;
	}


	public static class LabirintoBuilder {
		
		private Labirinto labirinto;
		private Map<String, Stanza> nome2stanza;
		
		public LabirintoBuilder() {
			this.labirinto = new Labirinto(); 
			this.nome2stanza = new HashMap<>();
		}
		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public LabirintoBuilder addStanza(String nome) {
			Stanza stanza = new Stanza(nome);
			this.nome2stanza.put(nome, stanza);
			return this;
		}

		public LabirintoBuilder addStanzaIniziale(String nome) {
			Stanza stanza = this.nome2stanza.get(nome);
			if (stanza == null) {
				stanza = new Stanza(nome);
				this.nome2stanza.put(nome, stanza);
			}
			this.labirinto.setEntrata(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nome) {
			Stanza stanza = this.nome2stanza.get(nome);
			if (stanza == null) {
				stanza = new Stanza(nome);
				this.nome2stanza.put(nome, stanza);
			}
			this.labirinto.setUscita(stanza);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso, String nomeStanza) {
			Stanza stanza = this.nome2stanza.get(nomeStanza);
			if (stanza != null) {
				stanza.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			}
			return this;
		}

		public LabirintoBuilder addAdiacenza(String stanzaDa, String stanzaA, Direzione dir) {
			Stanza da = this.nome2stanza.get(stanzaDa);
			Stanza a = this.nome2stanza.get(stanzaA);
			
			if (da != null && a != null) {
				da.impostaStanzaAdiacente(dir, a);
			}
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerLuce) {
			Stanza stanza = new StanzaBuia(nome, attrezzoPerLuce);
			
			this.nome2stanza.put(nome, stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nome, String direzione, String attrezzoSbloccante) {
		    try {
		        Direzione dirEnum = Direzione.valueOf(direzione.toUpperCase());
		        StanzaBloccata stanza = new StanzaBloccata(nome, dirEnum, attrezzoSbloccante);
		        this.nome2stanza.put(nome, stanza);
		    } catch (IllegalArgumentException e) {
		        System.err.println("Direzione " + direzione + " non valida per la stanza bloccata.");
		    }
		    return this;
		}
		public LabirintoBuilder addStanzaMagica(String nome, int sogliaMagica) {
			Stanza stanza = new StanzaMagica(nome, sogliaMagica);
			
			this.nome2stanza.put(nome, stanza);
			return this;
		}
		
		public LabirintoBuilder addMago(String nome, String presentazione, String nomeAttrezzo, int pesoAttrezzo, String nomeStanza) {
			Mago mago = new Mago(nome, presentazione, new Attrezzo(nomeAttrezzo, pesoAttrezzo));
			if (this.nome2stanza.containsKey(nomeStanza)) {
				this.nome2stanza.get(nomeStanza).setPersonaggio(mago);
			}
			return this; 
		}

		public LabirintoBuilder addCane(String nome, String presentazione, String ciboPreferito, String nomePremio, int pesoPremio, String nomeStanza) {
			Cane cane = new Cane(nome, presentazione, ciboPreferito, new Attrezzo(nomePremio, pesoPremio));
			if (this.nome2stanza.containsKey(nomeStanza)) {
				this.nome2stanza.get(nomeStanza).setPersonaggio(cane);
			}
			return this;
		}

		public LabirintoBuilder addStrega(String nome, String presentazione, String nomeStanza) {
			Strega strega = new Strega(nome, presentazione);
			if (this.nome2stanza.containsKey(nomeStanza)) {
				this.nome2stanza.get(nomeStanza).setPersonaggio(strega);
			}
			return this;
		}
	}
}