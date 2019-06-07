package it.polito.tdp.bar.model;


public class Gruppo {
	
	private int idGruppo;
	private int numPersone;
	private float tolleranza;
	//private LocalTime oraArrivo;
	private Integer tavoloDa;
	
	public Gruppo(int idGruppo, int numPersone, float tolleranza/*, LocalTime oraArrivo*/) {
		super();
		
		this.idGruppo = idGruppo;
		this.numPersone = numPersone;
		if(tolleranza > 0.9) {
			this.tolleranza = (float) (tolleranza - 0.1);
		}else {
			this.tolleranza = tolleranza;
		}		
		//this.oraArrivo = oraArrivo;
		this.tavoloDa = null;
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

	public Integer isHaTavolo() {
		return tavoloDa;
	}

	public void setHaTavolo(Integer haTavolo) {
		this.tavoloDa = haTavolo;
	}

	public void setNumPersone(int numPersone) {
		this.numPersone = numPersone;
	}

	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}

	@Override
	public String toString() {
		return String.format("Gruppo -> idGruppo = %s, numPersone = %s, tolleranza = %s, tavoloDa = %s", idGruppo,
				numPersone, tolleranza, tavoloDa);
	}

	

	
	
	
	
	

	
	
	
	

}
