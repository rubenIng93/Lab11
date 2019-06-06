package it.polito.tdp.bar.model;


public class TestSimulatore {
	
	
	public static void main(String[] args) {
		
		Simulatore sim = new Simulatore();
		sim.init();
		//System.out.println(sim.getQueue());
		sim.run();
		System.out.println("Totali: "+sim.getNumTotClienti()+"\n");
		System.out.println("Soddisfatti : "+sim.getSoddisfatti()+"\n");
		System.out.println("Insoddisfatti : "+sim.getInsoddisfatti()+"\n");
		
	}

}
