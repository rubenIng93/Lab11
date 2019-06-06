package it.polito.tdp.bar.model;

import java.time.LocalTime;

public class Evento implements Comparable<Evento>{
	
	public enum TipoEvento{
		ARRIVO_GRUPPO_CLIENTI,
		PARTENZA_GRUPPO_CLIENTI
	}
	
	private int oraArrivo;
	private TipoEvento tipo;
	private Gruppo gruppo;
	
	public Evento(int oraArrivo, TipoEvento tipo, Gruppo gruppo) {
		super();
		this.oraArrivo = oraArrivo;
		this.tipo = tipo;
		this.gruppo = gruppo;
	}

	public int getOraArrivo() {
		return oraArrivo;
	}

	public void setOraArrivo(int oraArrivo) {
		this.oraArrivo = oraArrivo;
	}

	public TipoEvento getTipo() {
		return tipo;
	}

	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}

	public Gruppo getGruppo() {
		return gruppo;
	}

	public void setGruppo(Gruppo gruppo) {
		this.gruppo = gruppo;
	}

	@Override
	public int compareTo(Evento other) {
		return this.oraArrivo - other.getOraArrivo();
	}

	@Override
	public String toString() {
		return String.format("Evento:  %s, %s, %s", oraArrivo, tipo, gruppo);
	}

	
	
	
	
	
	
	
	

}
