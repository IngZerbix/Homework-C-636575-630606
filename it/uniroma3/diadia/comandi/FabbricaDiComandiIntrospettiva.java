package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiIntrospettiva implements FabbricaDiComandi {

	@Override
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;

		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); 
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); 

		try {
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
			if (nomeComando != null) {
				nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
				nomeClasse += nomeComando.substring(1);
			} else {
				nomeClasse += "NonValido";
			}

			comando = (Comando) Class.forName(nomeClasse).getDeclaredConstructor().newInstance();

		} catch (Exception e) {
			
			comando = new ComandoNonValido();
		}

		comando.setParametro(parametro);
		return comando;
	}
}