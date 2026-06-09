package diadia.src;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.ComandoVai;

class ComandoVaiTest {
	
	private Partita partita;
	private ComandoVai comandoVai;
	private IOSimulator io; 
	
	@BeforeEach
	public void setUp() {
		this.comandoVai = new ComandoVai();
		
		this.io = new IOSimulator(new ArrayList<>());
		this.comandoVai.setIO(this.io);
		
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Inizio")
				.addStanza("Fine")
				.addAdiacenza("Inizio", "Fine", Direzione.EST)
				.getLabirinto();
		
		this.partita = new Partita(labirinto);
	}

	@Test
	void TestDirezioneNull() {
		this.comandoVai.setParametro(null);
		this.comandoVai.esegui(this.partita);
		
		assertEquals("Inizio", this.partita.getStanzaCorrente().getNome());
		
		assertTrue(this.io.hasMessaggio("Devi specificare una direzione"));
	}
	
	@Test
	void TestDirezioneDirezioneCorretta() {
		this.comandoVai.setParametro("est");
		this.comandoVai.esegui(this.partita);
		
		assertEquals("Fine", this.partita.getStanzaCorrente().getNome());
		assertEquals(19, this.partita.getGiocatore().getCfu()); 
	}
	
	@Test
	void TestDirezioneInesistente() {
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(this.partita);
		
		assertEquals("Inizio", this.partita.getStanzaCorrente().getNome());
		assertTrue(this.io.hasMessaggio("Direzione inesistente"));
	}
}