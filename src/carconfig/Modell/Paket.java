package carconfig.Modell;

import java.io.PrintWriter;

public class Paket {

	private String name;
	private double preis;
	private String beschreibung;
	
	public Paket(String name, String beschreibung, double preis){
		this.name=name;
		this.beschreibung=beschreibung;
		this.preis=preis;
	}
	
	public Paket(){
		
	}
	
	public String getNamePaket(){
		return this.name;
	}
	public double getPreisPaket(){
		return this.preis;
	}
	public String getBeschreibungPaket(){
		return this.beschreibung;
	}

	public void getAlleInfos(){
		System.out.println("Name:			"+this.getNamePaket());
		System.out.println("Beschreibung:	"+this.getBeschreibungPaket());
		System.out.println("Preis:			"+this.getPreisPaket());
	}

	public void getAlleInfosMail(PrintWriter writer){
		writer.println("Name:			"+this.getNamePaket());
		writer.println("Beschreibung:	"+this.getBeschreibungPaket());
		writer.println("Preis:			"+this.getPreisPaket());
	}
	
}


