package diadia.src;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
public class BorsaTest {

	private Borsa borsaVuota;
	private Borsa borsa;
	
	private Attrezzo piuma;
	private Attrezzo libro;
	private Attrezzo ps;
	private Attrezzo piombo;
	
	@Before
	public void setUp() {
		this.borsaVuota = new Borsa();
		
		this.borsa = new Borsa();
		
		this.piuma = new Attrezzo("piuma", 1);
		this.libro = new Attrezzo("libro", 5);
		this.ps = new Attrezzo("ps", 5);
		this.piombo = new Attrezzo("piombo", 10);
		
		this.borsa.addAttrezzo(piombo);
		this.borsa.addAttrezzo(ps);
		this.borsa.addAttrezzo(piuma);
		this.borsa.addAttrezzo(libro);
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso_BorsaVuota() {
		assertTrue(this.borsaVuota.getContenutoOrdinatoPerPeso().isEmpty());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome_BorsaVuota() {
		assertTrue(this.borsaVuota.getContenutoOrdinatoPerNome().isEmpty());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_BorsaVuota() {
		assertTrue(this.borsaVuota.getContenutoRaggruppatoPerPeso().isEmpty());		
	}
	@Test
	public void testGetSortedSetOrdinatoPerPeso_BorsaVuota() {
		assertTrue(this.borsaVuota.getSortedSetOrdinatoPerPeso().isEmpty());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		List<Attrezzo> lista = this.borsa.getContenutoOrdinatoPerPeso();
		
		assertEquals(4, lista.size());
		
		assertEquals(this.piuma, lista.get(0));
		assertEquals(this.libro, lista.get(1)); 
		assertEquals(this.ps, lista.get(2));
		assertEquals(this.piombo, lista.get(3));
	}

	@Test
	public void testGetContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> set = this.borsa.getContenutoOrdinatoPerNome();
		
		assertEquals(4, set.size());
		
		Iterator<Attrezzo> it = set.iterator();
		assertEquals(this.libro, it.next());
		assertEquals(this.piombo, it.next());
		assertEquals(this.piuma, it.next());
		assertEquals(this.ps, it.next());
	}

	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> mappa = this.borsa.getContenutoRaggruppatoPerPeso();
		
		assertEquals(3, mappa.size());
		
		Set<Attrezzo> gruppoPeso5 = mappa.get(5);
		assertNotNull(gruppoPeso5);
		assertEquals(2, gruppoPeso5.size());
		assertTrue(gruppoPeso5.contains(this.libro));
		assertTrue(gruppoPeso5.contains(this.ps));
	}

	@Test
	public void testGetSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> set = this.borsa.getSortedSetOrdinatoPerPeso();
		
		assertEquals(4, set.size());
		
		Iterator<Attrezzo> it = set.iterator();
		assertEquals(this.piuma, it.next());
		assertEquals(this.libro, it.next());
		assertEquals(this.ps, it.next());
		assertEquals(this.piombo, it.next());
	}
}
