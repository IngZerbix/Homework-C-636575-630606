package diadia.src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Direzione; 
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	@Test
	void testimpostaStanzaAdiacenteNULLStanza() {
		Stanza A1 = new Stanza("Test 1");
		A1.impostaStanzaAdiacente(Direzione.SUD, null);
		assertNull(A1.getStanzaAdiacente(Direzione.SUD), "Il primo test non funziona.");
	}
	
	@Test
	void testimpostaStanzaAdiacenteNULLDirezione() {
		Stanza A2 = new Stanza("Test 2");
		Stanza B2 = new Stanza("Appoggio");
		A2.impostaStanzaAdiacente(null, B2);
		assertNull(A2.getStanzaAdiacente(null), "Il secondo test non funziona.");
	}
	
	@Test
	void testimpostaStanzaAdiacenteFunzionante() {
		Stanza A3 = new Stanza("Test 3");
		Stanza B3 = new Stanza("Appoggio");
		A3.impostaStanzaAdiacente(Direzione.NORD, B3);
		assertEquals(B3, A3.getStanzaAdiacente(Direzione.NORD));
	}
	
	
	@Test
	void testaddAttrezzoOggettoNull() {
		Stanza D1 = new Stanza("test 4");
		Attrezzo C1 = null;
		
		D1.addAttrezzo(C1);
		assertFalse(D1.hasAttrezzo(null), "Il quarto test non funziona.");
	}
	
	@Test
	void testaddAttrezzoFunzionante() {
		Stanza D1 = new Stanza("test 5");
		Attrezzo C1 = new Attrezzo("osso", 2);
		
		D1.addAttrezzo(C1);
		assertTrue(D1.hasAttrezzo(C1.getNome()), "Il quinto test non funziona.");
	}
	
	@Test
	void testaddAttrezzoOverflow() {
		Stanza D1 = new Stanza("test 6");
		Attrezzo C2 = new Attrezzo("osso", 2);
		for (int i=0; i<10;i++) {
			D1.addAttrezzo(C2);
		}
		
		Attrezzo C3 = new Attrezzo ("Prova", 3);
		D1.addAttrezzo(C3);
		
		assertFalse(D1.hasAttrezzo(C3.getNome()), "Il sesto test non funziona.");
	}
}