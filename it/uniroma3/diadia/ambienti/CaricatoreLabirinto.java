package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class CaricatoreLabirinto {

	private static final String STANZE_MARKER = "Stanze:";             
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  
	private static final String ATTREZZI_MARKER = "Attrezzi:";
	private static final String USCITE_MARKER = "Uscite:";
	
	private static final String STANZE_BUIE_MARKER = "StanzeBuie:";
	private static final String STANZE_BLOCCATE_MARKER = "StanzeBloccate:";
	private static final String STANZE_MAGICHE_MARKER = "StanzeMagiche:";
	private static final String PERSONAGGI_MARKER = "Personaggi:";

	private LineNumberReader reader;
	private Labirinto.LabirintoBuilder builder;
	private Set<String> stanzeValide;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.stanzeValide = new HashSet<String>();
		this.builder = Labirinto.newBuilder();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader reader) {
		this.stanzeValide = new HashSet<String>();
		this.builder = new LabirintoBuilder();
		this.reader = new LineNumberReader(reader);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();      
			this.leggiECreaStanzeBloccate();  
			this.leggiECreaStanzeMagiche();   
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECollocaPersonaggi();   
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}
	
	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while (scannerDiParole.hasNext()) {
				String token = scannerDiParole.next().trim();
				if (!token.isEmpty()) { 
					result.add(token);
				}
			}
		}
		return result;
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza);
			this.stanzeValide.add(nomeStanza);
		}
	}

	
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String specifiche = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for (String specifica : separaStringheAlleVirgole(specifiche)) {
			try (Scanner scanner = new Scanner(specifica)) {
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza buia."));
				String nomeStanza = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("l'attrezzo per illuminare la stanza."));
				String attrezzoLuminoso = scanner.next();
				this.builder.addStanzaBuia(nomeStanza, attrezzoLuminoso);
				this.stanzeValide.add(nomeStanza);
			}
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String specifiche = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for (String specifica : separaStringheAlleVirgole(specifiche)) {
			try (Scanner scanner = new Scanner(specifica)) {
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza bloccata."));
				String nomeStanza = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la direzione bloccata."));
				String direzione = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("l'attrezzo sbloccante."));
				String chiave = scanner.next();
				this.builder.addStanzaBloccata(nomeStanza, direzione, chiave);
				this.stanzeValide.add(nomeStanza);
			}
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String specifiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for (String specifica : separaStringheAlleVirgole(specifiche)) {
			try (Scanner scanner = new Scanner(specifica)) {
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza magica."));
				String nomeStanza = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la soglia magica."));
				int soglia = Integer.parseInt(scanner.next());
				this.builder.addStanzaMagica(nomeStanza, soglia);
				this.stanzeValide.add(nomeStanza);
			} catch (NumberFormatException e) {
				check(false, "Soglia magica non valida");
			}
		}
	}


	
	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER).trim();
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER).trim();
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
		this.builder.addStanzaVincente(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				String nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				String pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				String nomeStanza = scannerLinea.next();
				posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
			}				
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		try {
			int peso = Integer.parseInt(pesoAttrezzo);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.builder.addAttrezzo(nomeAttrezzo, peso, nomeStanza);
		} catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}

	private boolean isStanzaValida(String nomeStanza) {
		return this.stanzeValide.contains(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for (String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la stanza di partenza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita."));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di un'uscita."));
					String stanzaDestinazione = scannerDiLinea.next();
					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			} 
		}
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa), "Stanza di partenza sconosciuta: " + stanzaDa);
		check(isStanzaValida(nomeA), "Stanza di destinazione sconosciuta: " + nomeA);
		
		Direzione direzioneEnum = null;
		try {
			direzioneEnum = Direzione.valueOf(dir.toUpperCase());
		} catch (IllegalArgumentException e) {
			check(false, "Direzione non valida nel file di specifica: " + dir);
		}
		
		this.builder.addAdiacenza(stanzaDa, nomeA, direzioneEnum);
	}
	
	private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException {
		String specifiche = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
		for (String specifica : separaStringheAlleVirgole(specifiche)) {
			try (Scanner scanner = new Scanner(specifica)) {
				check(scanner.hasNext(), msgTerminazionePrecoce("il tipo di personaggio (Mago/Cane/Strega)."));
				String tipo = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome del personaggio."));
				String nome = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la presentazione del personaggio."));
				String presentaz = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la stanza in cui collocare il personaggio."));
				String stanza = scanner.next();
				check(isStanzaValida(stanza), "Stanza " + stanza + " per il personaggio inesistente");

				if (tipo.equals("Mago")) {
					String attrezzo = scanner.next();
					int peso = scanner.nextInt();
					this.builder.addMago(nome, presentaz, attrezzo, peso, stanza);
				} else if (tipo.equals("Cane")) {
					String cibo = scanner.next();
					String premio = scanner.next();
					int pesoPremio = scanner.nextInt();
					this.builder.addCane(nome, presentaz, cibo, premio, pesoPremio, stanza);
				} else if (tipo.equals("Strega")) {
					this.builder.addStrega(nome, presentaz, stanza);
				} else {
					check(false, "Tipo di personaggio sconosciuto: " + tipo);
				}
			} catch (Exception e) {
				check(false, "Errore nella lettura dei parametri del personaggio.");
			}
		}
	}

	// --- FINE NUOVO METODO PER I PERSONAGGI ---

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Labirinto getLabirinto() {
		return this.builder.getLabirinto();
	}
}