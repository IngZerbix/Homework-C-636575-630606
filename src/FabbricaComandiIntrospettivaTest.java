package diadia.src;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiIntrospettiva;

public class FabbricaComandiIntrospettivaTest {

	private FabbricaDiComandi factory;

	@Before
	public void setUp() {
		this.factory = new FabbricaDiComandiIntrospettiva();
	}

	@Test
	public void testCostruisciComandoValido() {
		Comando c = this.factory.costruisciComando("vai nord");
		assertEquals("vai", c.getNome());
		assertEquals("nord", c.getParametro());
	}

	@Test
	public void testCostruisciComandoInesistente() {
		Comando c = this.factory.costruisciComando("vola");
		
		assertEquals("nonValido", c.getNome()); 
	}

	@Test
	public void testCostruisciComandoVuoto() {
		Comando c = this.factory.costruisciComando("");
		assertEquals("nonValido", c.getNome());
	}
}