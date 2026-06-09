package diadia.src;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.AbstractComando;

public class AbstractComandoTest {

	private AbstractComando comandoAstratto;

	@Before
	public void setUp() {
		this.comandoAstratto = new AbstractComando() {
			@Override
			public void esegui(Partita partita) {
			}
			
			@Override
			public String getNome() {
				return "comandoDummy";
			}
		};
	}

	@Test
	public void testSetEGetParametro() {
		this.comandoAstratto.setParametro("nord");
		assertEquals("nord", this.comandoAstratto.getParametro());
	}
	
	@Test
	public void testGetParametroInizialmenteNull() {
		assertNull(this.comandoAstratto.getParametro());
	}

	@Test
	public void testSetEGetIO() {
		IO io = new IOSimulator(new ArrayList<>());
		this.comandoAstratto.setIO(io);
		
		assertNotNull(this.comandoAstratto.getIo());
		assertEquals(io, this.comandoAstratto.getIo());
	}
}