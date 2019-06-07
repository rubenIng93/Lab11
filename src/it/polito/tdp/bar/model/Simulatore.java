package it.polito.tdp.bar.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.bar.model.Evento.TipoEvento;

public class Simulatore {
	
	//CODA DEGLI EVENTI
	PriorityQueue<Evento> queue = new PriorityQueue<>();
	
	//MODELLO DEL MONDO
	private List<Gruppo> gruppi;
	private Map<Integer, Integer> mappaTavoliPosti;//come chiave il n.° di posti; come valore i posti disponibili
	private int tavoliLiberi;
	private Random rand = new Random();
	
	
	
	//PARAMETRI DI SIMULAZIONE
	private int NUMERO_TAVOLI = 15;
	private int TAVOLI_10POSTI = 2;
	private int TAVOLI_8POSTI = 4;
	private int TAVOLI_6POSTI = 4;
	private int TAVOLI_4POSTI = 5;
	private int numTotClienti = 2000;
	
		
	
	//STATISTICHE DA CALCOLARE
	private int soddisfatti;
	private int insoddisfatti;
	
	//VARIABILI INTERNE
	
	public void init() {
		
		gruppi = new ArrayList<>();
		
		for(int i = 0; i < numTotClienti; i++) {
			Gruppo inArrivo = new Gruppo(100 + i, rand.nextInt(10) + 1, rand.nextFloat()/*, oraArrivo*/);
			gruppi.add(inArrivo);
			
		}
		
		mappaTavoliPosti = new HashMap<>();
		mappaTavoliPosti.put(10, TAVOLI_10POSTI);
		mappaTavoliPosti.put(8, TAVOLI_8POSTI);
		mappaTavoliPosti.put(6, TAVOLI_6POSTI);
		mappaTavoliPosti.put(4, TAVOLI_4POSTI);
		
		tavoliLiberi = NUMERO_TAVOLI;
		
		soddisfatti = 0;
		insoddisfatti = 0;
		
		queue.clear();
		
		int oraArrivo = 0;
		for(Gruppo gruppo : gruppi) {
			Evento e = new Evento(oraArrivo, TipoEvento.ARRIVO_GRUPPO_CLIENTI, gruppo);
			queue.add(e);
			oraArrivo += rand.nextInt(10) + 1;
			//System.out.println(e);
		}
		
		
	}
	
	public void run() {
		
		while(!queue.isEmpty()) {
			
			Evento e = queue.poll();
			//System.out.println(e);
			
			Gruppo g = e.getGruppo();
			
			switch(e.getTipo()) {
			
			case ARRIVO_GRUPPO_CLIENTI:
				//Devo assegnare il tavolo in base al n persone e se ce ne sono disponibili
				if(this.assegnaPosto(g) == true) {
					this.soddisfatti ++;
					this.queue.add(new Evento(e.getOraArrivo()+rand.nextInt(60)+60, TipoEvento.PARTENZA_GRUPPO_CLIENTI, e.getGruppo()));
				}else if(this.assegnaPosto(g) == false) {
					//guardo la tolleranza e decido se si possono accontentare
					if(g.getTolleranza() > rand.nextFloat()) {//situazione in cui accettano
						this.soddisfatti++;
						this.queue.add(new Evento(e.getOraArrivo()+rand.nextInt(60)+60, TipoEvento.PARTENZA_GRUPPO_CLIENTI, e.getGruppo()));
					}else {
						this.insoddisfatti ++;
						this.queue.add(new Evento(e.getOraArrivo(), TipoEvento.PARTENZA_GRUPPO_CLIENTI, e.getGruppo()));
					}
				}
				break;
				
			case PARTENZA_GRUPPO_CLIENTI:
				//restituzione tavoli
				Integer tavoloDa = g.isHaTavolo();
				if(tavoloDa != null) {
					int disponibili = this.mappaTavoliPosti.get(tavoloDa);
					this.mappaTavoliPosti.put(tavoloDa, disponibili + 1);
				}
			}
		}
		
	}

	public int getNumTotClienti() {
		return numTotClienti;
	}

	public int getSoddisfatti() {
		return soddisfatti;
	}

	public int getInsoddisfatti() {
		return insoddisfatti;
	}

	public PriorityQueue<Evento> getQueue() {
		return queue;
	}
	
	
	/**
	 * Funzione per assegnare il posto in base alle regole:
	 * -tavolo più piccolo che possa contenere il gruppo;
	 * -occupare almeno il 50% dei posti del tavolo
	 * @param gruppo
	 * @return true se assegnato, false altrimenti
	 */
	private boolean assegnaPosto(Gruppo gruppo) {
		if(this.tavoliLiberi == 0)
			return false;
		else {
			int persone = gruppo.getNumPersone();
			if(persone <= 10 && persone > 8) {//gestione per 10,9
				if(this.mappaTavoliPosti.get(10) > 0) {
					int liberi = mappaTavoliPosti.get(10);
					mappaTavoliPosti.put(10, liberi - 1);
					gruppo.setHaTavolo(10);
					return true;
				}else
					return false;
			}else if(persone <= 8 && persone > 6) {//getione per 8,7
				if(this.mappaTavoliPosti.get(8) > 0) {
					int liberi = mappaTavoliPosti.get(8);
					mappaTavoliPosti.put(8, liberi - 1);
					gruppo.setHaTavolo(8);
					return true;
				}else {
					if(this.mappaTavoliPosti.get(10) > 0) {
						int liberi = mappaTavoliPosti.get(10);
						mappaTavoliPosti.put(10, liberi - 1);
						gruppo.setHaTavolo(10);
						return true;
					}else
						return false;
				}
			}else if(persone <= 6 && persone > 4) {//gestione per 6,5
				if(this.mappaTavoliPosti.get(6) > 0) {
					int liberi = mappaTavoliPosti.get(6);
					mappaTavoliPosti.put(6, liberi - 1);
					gruppo.setHaTavolo(6);
					return true;
				}else if(this.mappaTavoliPosti.get(8) > 0) {
					int liberi = mappaTavoliPosti.get(8);
					mappaTavoliPosti.put(8, liberi - 1);
					gruppo.setHaTavolo(8);
					return true;
				}else if(this.mappaTavoliPosti.get(10) > 0) {
					int liberi = mappaTavoliPosti.get(10);
					mappaTavoliPosti.put(10, liberi - 1);
					gruppo.setHaTavolo(10);
					return true;
				}else
					return false;
			}else if(persone <= 4 && persone >= 2) {//gestione per 4,3,2
				if(this.mappaTavoliPosti.get(4) > 0) {
					int liberi = mappaTavoliPosti.get(4);
					mappaTavoliPosti.put(4, liberi - 1);
					gruppo.setHaTavolo(4);
					return true;
				}else if((this.mappaTavoliPosti.get(6) > 0) && persone != 2) {
					int liberi = mappaTavoliPosti.get(6);
					mappaTavoliPosti.put(6, liberi - 1);
					gruppo.setHaTavolo(6);
					return true;
				}else if(persone == 4 && this.mappaTavoliPosti.get(8) > 0) {
					int liberi = mappaTavoliPosti.get(8);
					mappaTavoliPosti.put(8, liberi - 1);
					gruppo.setHaTavolo(8);
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	
	
	
	
	

}
