package diadia.src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.Direzione; 
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	
	private StanzaBloccata stanza;
	private Attrezzo attrezzo;
	private Stanza stanza2;
	
	@BeforeEach
	public void setUp() {
		this.stanza = new StanzaBloccata("Stanza", Direzione.SUD, "chiave"); 
		this.attrezzo = new Attrezzo("chiave", 1);
		this.stanza2 = new Stanza("Sud");
		
		this.stanza.impostaStanzaAdiacente(Direzione.SUD, stanza2);
	}

	@Test
	void testNoChiave() {
		assertEquals(this.stanza, this.stanza.getStanzaAdiacente(Direzione.SUD));
	}
	
	@Test
	void testChiave() {
		this.stanza.addAttrezzo(this.attrezzo);
		
		assertEquals(this.stanza2, this.stanza.getStanzaAdiacente(Direzione.SUD));
	}
}