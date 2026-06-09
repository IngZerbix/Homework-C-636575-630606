package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiIntrospettiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	private Partita partita;
	private IO console;

	public DiaDia(IO io) {
		this.partita = new Partita();
		this.console = io;
	}

	
	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto);
		this.console = io;
	}

	public void gioca() {
		String istruzione; 

		console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiIntrospettiva();
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.setIO(this.console);
		comandoDaEseguire.esegui(this.partita);
		
		if (this.partita.vinta())
			this.console.mostraMessaggio("Hai vinto!");
		
		if (!this.partita.giocatoreIsVivo())
			this.console.mostraMessaggio("Hai esaurito i CFU...");

		return this.partita.isFinita();
		
		}

	
	public Partita getPartita() {
		return this.partita;
	}

public static void main(String[] argc) {
		
	try (Scanner scanner = new Scanner(System.in)) {
		
		IO io = new IOConsole(scanner);
		
		DiaDia gioco = new DiaDia(io); 
		gioco.gioca();
		
	} 
		//CaricatoreLabirinto caricatore = new CaricatoreLabirinto("labirinto.txt");
		//caricatore.carica();
		//DiaDia gioco = new DiaDia(caricatore.getLabirinto(), new IOConsole());
		//gioco.gioca();
	}
}