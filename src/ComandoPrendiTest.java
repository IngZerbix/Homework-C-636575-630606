package diadia.src;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest {
	
	private Partita partita;
	private ComandoPrendi comando;
	private IOSimulator io; 
	
	@BeforeEach
	public void setUp() {
		this.comando = new ComandoPrendi();
		
		this.io = new IOSimulator(new ArrayList<>());
		this.comando.setIO(this.io); 
		
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Stanza")
				.addAttrezzo("Osso", 4, "Stanza")
				.addAttrezzo("Anguria", 145, "Stanza")
				.getLabirinto();
		
		this.partita = new Partita(labirinto);
	}
	
	@Test
	void testSenzaOggetto() {
		this.comando.setParametro(null);
		this.comando.esegui(this.partita);
		
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("Osso"));
	}
	
	@Test
	void testPrendoOggetto() {
		this.comando.setParametro("Osso");
		this.comando.esegui(this.partita);
		
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("Osso"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("Osso"));
	}
	
	@Test
	void testOggettoInesistente() {
		this.comando.setParametro("Torcia");
		this.comando.esegui(this.partita);
		
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("Torcia"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("Torcia"));
	}
	
	@Test
	void testOggettoTroppoPesante() {
		this.comando.setParametro("Anguria");
		this.comando.esegui(this.partita);
		
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("Anguria"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("Anguria"));
	}
}