package diadia.src;

import static org.junit.Assert.*;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

import java.io.StringReader;

public class CaricatoreLabirintoTest {

	@Test
	public void testCaricamentoMonolocale() throws FormatoFileNonValidoException {
		String specifica = 
				"Stanze: Atrio\n" +
				"Inizio: Atrio\n" +
				"Vincente: Atrio\n" +
				"Attrezzi: \n" +
				"Uscite: \n"; 
		
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(specifica));
		caricatore.carica();
		Labirinto labirinto = caricatore.getLabirinto();
		
		assertEquals("Atrio", labirinto.getEntrata().getNome());
		assertEquals("Atrio", labirinto.getUscita().getNome());
	}
	
	@Test
	public void testCaricamentoBilocaleConAttrezzo() throws FormatoFileNonValidoException {
		String specifica = 
				"Stanze: Atrio, Biblioteca\n" +
				"Inizio: Atrio\n" +
				"Vincente: Biblioteca\n" +
				"Attrezzi: Osso 5 Atrio\n" + 
				"Uscite: Atrio nord Biblioteca, Biblioteca sud Atrio\n";
		
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(specifica));
		caricatore.carica();
		Labirinto labirinto = caricatore.getLabirinto();
		
		assertEquals("Atrio", labirinto.getEntrata().getNome());
		assertEquals("Biblioteca", labirinto.getUscita().getNome());
		
		assertTrue(labirinto.getEntrata().hasAttrezzo("Osso"));
		
		Stanza stanzaAdiacente = labirinto.getEntrata().getStanzaAdiacente(Direzione.NORD);
		assertNotNull(stanzaAdiacente);
		assertEquals("Biblioteca", stanzaAdiacente.getNome());
	}
}