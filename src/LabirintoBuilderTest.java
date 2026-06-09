package diadia.src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Direzione; 

public class LabirintoBuilderTest {
	
	private Labirinto.LabirintoBuilder builder;

	@BeforeEach
	public void setUp() {
		this.builder = Labirinto.newBuilder();
	}

	@Test
	public void testAddStanzaInizialeEUscita() {
		Labirinto labirinto = this.builder
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.getLabirinto();

		assertNotNull(labirinto.getEntrata());
		assertEquals("Atrio", labirinto.getEntrata().getNome());
		
		assertNotNull(labirinto.getUscita());
		assertEquals("Biblioteca", labirinto.getUscita().getNome());
	}

	@Test
	public void testAddAdiacenza() {
		Labirinto labirinto = this.builder
				.addStanzaIniziale("Atrio")
				.addStanza("Aula N11")
				.addAdiacenza("Atrio", "Aula N11", Direzione.EST)
				.addAdiacenza("Aula N11", "Atrio", Direzione.OVEST)
				.getLabirinto();

		Stanza atrio = labirinto.getEntrata();
		Stanza aulaN11 = atrio.getStanzaAdiacente(Direzione.EST); 

		assertNotNull(aulaN11);
		assertEquals("Aula N11", aulaN11.getNome());
		assertEquals("Atrio", aulaN11.getStanzaAdiacente(Direzione.OVEST).getNome());
	}

	@Test
	public void testAddAttrezzo() {
		Labirinto labirinto = this.builder
				.addStanzaIniziale("Atrio")
				.addAttrezzo("lanterna", 3, "Atrio")
				.getLabirinto();

		Stanza atrio = labirinto.getEntrata();
		assertTrue(atrio.hasAttrezzo("lanterna"));
		assertEquals(3, atrio.getAttrezzo("lanterna").getPeso());
	}
	
	@Test
	public void testCreazioneLabirintoCompleto() {
		Labirinto labirinto = this.builder
				.addStanzaIniziale("Atrio")
				.addStanzaBuia("Cantina", "lanterna")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Cantina", Direzione.NORD)
				.addAdiacenza("Cantina", "Biblioteca", Direzione.NORD)
				.addAttrezzo("osso", 1, "Atrio")
				.getLabirinto();
		
		Stanza atrio = labirinto.getEntrata();
		Stanza cantina = atrio.getStanzaAdiacente(Direzione.NORD);
		
		assertTrue(atrio.hasAttrezzo("osso"));
		assertEquals("Cantina", cantina.getNome());
		assertEquals("Biblioteca", cantina.getStanzaAdiacente(Direzione.NORD).getNome());
	}
}