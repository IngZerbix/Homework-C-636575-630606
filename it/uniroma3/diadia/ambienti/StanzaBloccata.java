package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	
	private Direzione direzioneBloccata; 
	private String attrezzoSbloccaDirezione;
	
	public StanzaBloccata(String nome) {
		this(nome, Direzione.SUD, "passpartou"); 
	}
	
	public StanzaBloccata(String nome, Direzione direzione, String attrezzo) {
		super(nome);
		this.direzioneBloccata = direzione;
		this.attrezzoSbloccaDirezione = attrezzo;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione dir) {
		if(this.direzioneBloccata.equals(dir)) {
			if(!this.hasAttrezzo(this.attrezzoSbloccaDirezione)) {
				return this; 
			}
		}
		return super.getStanzaAdiacente(dir);
	}
	
	@Override
	public String toString() {
		String risultato = super.toString();
		
		if (!this.hasAttrezzo(this.attrezzoSbloccaDirezione)) {
			risultato += "\nAttenzione! La porta a " + this.direzioneBloccata + 
					     " è bloccata, serve un " + this.attrezzoSbloccaDirezione + " per aprirla!";
		}
		
		return risultato;
	}
}