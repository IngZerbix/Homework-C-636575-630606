package diadia.src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandi factory;
	
	@BeforeEach
	public void setUp() {
		this.factory = new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	void testComandoCompleto() {
		Comando comandoCreato = this.factory.costruisciComando("vai nord");
		
		assertEquals("vai", comandoCreato.getNome());
        assertEquals("nord", comandoCreato.getParametro());
	}

	@Test
	void testComandoSenzaParametro() {
		Comando comandoCreato = this.factory.costruisciComando("aiuto");
		
		assertEquals("aiuto", comandoCreato.getNome());
        assertNull(comandoCreato.getParametro());
	}
	
	@Test
	void testComandoInesistente() {
		Comando comandoCreato = this.factory.costruisciComando("vola");
		
		assertEquals("nonValido", comandoCreato.getNome());
	}
}
