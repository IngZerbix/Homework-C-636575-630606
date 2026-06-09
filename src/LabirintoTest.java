package diadia.src;


import static org.junit.Assert.*;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

public class LabirintoTest {

	@Test
	public void testLabirintoMonolocale() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		
		assertNotNull(labirinto);
		assertEquals("Atrio", labirinto.getEntrata().getNome());
	}

	@Test
	public void testLabirintoBilocaleConAttrezzo() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD) 
				.addAttrezzo("Osso", 5, "Atrio")
				.getLabirinto();
		
		assertEquals("Atrio", labirinto.getEntrata().getNome());
		assertEquals("Biblioteca", labirinto.getUscita().getNome());
		
		assertTrue(labirinto.getEntrata().hasAttrezzo("Osso"));
		
		// Verifichiamo il collegamento (adatta a Direzione se necessario)
		assertEquals("Biblioteca", labirinto.getEntrata().getStanzaAdiacente(Direzione.NORD).getNome());
	}
}