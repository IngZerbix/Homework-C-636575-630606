package diadia.src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

class PartitaTest {

	private Partita partita;
	private Labirinto labirinto;

	@BeforeEach
	public void setUp() {
		this.labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.getLabirinto();
		
		this.partita = new Partita(this.labirinto);
	}

	@Test
	void testisFinitaFalse() {
		assertFalse(this.partita.isFinita(), "Il primo test non funziona: appena iniziata non deve essere finita");
	}
	
	@Test
	void testisFinita0CFU() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita(), "Il secondo test non funziona: con 0 CFU deve finire");
	}
	
	@Test
	void testisFinitaCFUnon0(){
		this.partita.getGiocatore().setCfu(20); 
		assertFalse(this.partita.isFinita(), "Il terzo test non funziona: con CFU > 0 non deve finire");
	}
	
	@Test
	void testVintaNull() {
		this.partita.setStanzaCorrente(null);
		assertFalse(this.partita.vinta(), "Il quarto test non funziona: in una stanza nulla non si vince");
	}
	
	@Test
	void testVintaTrue() {
		this.partita.setStanzaCorrente(this.labirinto.getUscita());
		assertTrue(this.partita.vinta(), "Il quinto test non funziona: nella stanza vincente si deve vincere");
	}
	
	@Test
	void testVintaFalse() {
		assertFalse(this.partita.vinta(), "Il sesto test non funziona: nell'atrio non si vince");
	}
}