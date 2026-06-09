package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	String AttrezzoConsenteDiVedere;
	
	public StanzaBuia(String nome) {
		this(nome, "lanterna");
	}
	
	public StanzaBuia(String nome, String attrezzo) {
		super(nome);
		this.AttrezzoConsenteDiVedere = attrezzo;
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(this.AttrezzoConsenteDiVedere))
			return super.getDescrizione();
		else
			return "Qui c'è buio pesto";
	}
}
