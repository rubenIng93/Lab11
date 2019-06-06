package it.polito.tdp.bar.model;

import java.time.LocalTime;

public class Gruppo {
	
	private int numPersone;
	private float tolleranza;
	//private LocalTime oraArrivo;
	private boolean haTavolo;
	
	public Gruppo(int numPersone, float tolleranza/*, LocalTime oraArrivo*/) {
		super();
		this.numPersone = numPersone;
		if(tolleranza > 0.9) {
			this.tolleranza = (float) (tolleranza - 0.1);
		}else {
			this.tolleranza = tolleranza;
		}		
		//this.oraArrivo = oraArrivo;
		this.haTavolo = false;
	}

	public int getNumPersone() {
		return numPersone;
	}

	public float getTolleranza() {
		return tolleranza;
	}

	/*public LocalTime getOraArrivo() {
		return oraArrivo;
	}

	public void setOraArrivo(LocalTime oraArrivo) {
		this.oraArrivo = oraArrivo;
	}*/

	public boolean isHaTavolo() {
		return haTavolo;
	}

	public void setHaTavolo(boolean haTavolo) {
		this.haTavolo = haTavolo;
	}

	public void setNumPersone(int numPersone) {
		this.numPersone = numPersone;
	}

	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}

	@Override
	public String toString() {
		return String.format("Gruppo:  %s, %s", numPersone, tolleranza);
	}
	
	
	

	
	
	
	

}
