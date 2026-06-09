package diadia.src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

class StanzaBuiaTest {
	private StanzaBuia stanzaBuia;
	private Attrezzo attrezzo;

	
	
	@BeforeEach
	public void setUp() {
		this.stanzaBuia = new StanzaBuia("Stanza", "lanterna");
		this.attrezzo = new Attrezzo("lanterna", 2);
	}
	
	
	@Test
	void testSiPossiedeLaLanterna() {
		this.stanzaBuia.addAttrezzo(this.attrezzo);
		
		assertNotEquals("Qui c'è buio pesto", this.stanzaBuia.getDescrizione());
	}
	
	@Test
	void testNonSiPossiedeLaLanterna() {
		assertEquals("Qui c'è buio pesto", this.stanzaBuia.getDescrizione());
	}
}
