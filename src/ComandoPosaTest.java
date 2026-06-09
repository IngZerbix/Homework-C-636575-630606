package diadia.src;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

class ComandoPosaTest {
	
	private Partita partita;
	private ComandoPosa comandoPosa;
	private IOSimulator io; 
	
	@BeforeEach
	public void setUp() {
		this.comandoPosa = new ComandoPosa();
		
		this.io = new IOSimulator(new ArrayList<>());
		this.comandoPosa.setIO(this.io); 
		
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("stanza")
				.getLabirinto();
		
		this.partita = new Partita(labirinto);
		
		Attrezzo osso = new Attrezzo("Osso", 4);
		this.partita.getGiocatore().getBorsa().addAttrezzo(osso);
	}
	
	@Test
	void testSenzaOggetto() {
		this.comandoPosa.setParametro(null);
		this.comandoPosa.esegui(this.partita);
		
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("Osso"));
	}
	
	@Test
	void testOggettoPosato() {
		this.comandoPosa.setParametro("Osso");
		this.comandoPosa.esegui(this.partita);
		
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("Osso"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("Osso"));
	}
	
	@Test
	void testOggettoInesistente() {
		this.comandoPosa.setParametro("Torcia");
		this.comandoPosa.esegui(this.partita);
		
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("Torcia"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("Torcia"));	
	}
}