package it.uniroma3.diadia.giocatore;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;

	
	public Borsa() {
	    this.pesoMax = Configuratore.getPesoMaxBorsa();
		this.attrezzi = new HashMap<>();
	}

	//addAttrezzo
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null)
				return false;
		
		if(this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
				return false;
		
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
		
	}
	
	public int getPesoMax() {
		return this.pesoMax;
	}
	
	public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		int pesoTotale = 0;
		
		for(Attrezzo a : this.attrezzi.values())
			pesoTotale+=a.getPeso();
		
		return pesoTotale;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		
		List<Attrezzo> risultato = new ArrayList<>(this.attrezzi.values());
		
		risultato.sort(Comparator.comparingInt(Attrezzo::getPeso).thenComparing(Attrezzo::getNome));
		
		return risultato;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		
		SortedSet<Attrezzo> risultato = new TreeSet<>(Comparator.comparing(Attrezzo::getNome));	
		
		risultato.addAll(this.attrezzi.values());
		
		return risultato;
	}
	
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
	    Map<Integer, Set<Attrezzo>> mappaRisultato = new HashMap<>();

	    for (Attrezzo a : this.attrezzi.values()) {
	        int peso = a.getPeso();
	        
	        if (!mappaRisultato.containsKey(peso)) {
	            mappaRisultato.put(peso, new HashSet<>());
	        }
	        
	        mappaRisultato.get(peso).add(a);
	    }
	    
	    return mappaRisultato;
	}
	
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		
		SortedSet<Attrezzo> risultato = new TreeSet<>(Comparator.comparingInt(Attrezzo::getPeso).thenComparing(Attrezzo::getNome));
		
		risultato.addAll(this.attrezzi.values());
		
		return risultato;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa (").append(this.getPeso()).append("kg/").append(this.getPesoMax()).append("kg): ");
			for (Attrezzo a : this.attrezzi.values()) {
				s.append(a.toString()).append(" ");
			}
		} else {
			s.append("Borsa vuota");
		}
		return s.toString();
	}
}
