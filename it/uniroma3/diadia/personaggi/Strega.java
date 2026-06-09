package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_SALUTATA = "Solo perche' mi hai salutato, non ti mando in un posto sfigato!";
	private static final String MESSAGGIO_NON_SALUTATA = "Sei proprio un maleducato, vai in un posto sfigato!";

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		if (this.haSalutato()) {
			return MESSAGGIO_SALUTATA;
		} else {
			return MESSAGGIO_NON_SALUTATA;
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "AHAHAH! Sei uno stolto! Grazie per l'oggetto " + attrezzo.getNome() + ". Ora è mio e me lo tengo! *scoppia a ridere*";
	}
}