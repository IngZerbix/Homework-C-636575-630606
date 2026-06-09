package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {

	private String parametro;
	private IO io;

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	

	public IO getIo() {
		return this.io;
	}

	@Override
	public abstract void esegui(Partita partita);

	@Override
	public abstract String getNome();
}