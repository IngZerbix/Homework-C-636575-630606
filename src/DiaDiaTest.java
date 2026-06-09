package diadia.src;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

public class DiaDiaTest {
	
	@Test
	public void testPartitaVinta() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.getLabirinto();
		
		List<String> comandiDaSimulare = Arrays.asList("vai nord");
		IOSimulator io = new IOSimulator(comandiDaSimulare);
		
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		
		assertTrue("Il giocatore dovrebbe aver vinto andando a nord", io.hasMessaggio("Hai vinto!"));
	}
	

	
	@Test
	public void testRaccoltaOggetti() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1, "Atrio") 
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.getLabirinto();
		
		List<String> comandiFiniti = Arrays.asList("prendi osso", "fine");
		IOSimulator io = new IOSimulator(comandiFiniti);
		
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		
		System.out.println("MESSAGGI PRODOTTI DAL TEST RACCOLTA:");
		System.out.println(io.getMessaggiProdotti());
		
		assertTrue(io.hasMessaggio("osso")); 
	}
	
	@Test
	public void testDirezioneInesistente() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.getLabirinto();
		
		List<String> comandi = Arrays.asList("vai sud", "fine");
		IOSimulator io = new IOSimulator(comandi);
		
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		
		assertTrue(io.hasMessaggio("Direzione inesistente")); 
	}

	@Test
	public void testPrendiEPosaOggetto() {
	
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("chiave", 1, "Atrio")
				.getLabirinto();
		
		
		List<String> comandi = Arrays.asList("prendi chiave", "posa chiave", "fine");
		IOSimulator io = new IOSimulator(comandi);
		
		
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		
		
		assertTrue(gioco.getPartita().getStanzaCorrente().hasAttrezzo("chiave"));
	}
}
