package carconfig;

import java.util.ArrayList;

public class Modell {
	
	private String name;
	private Paket ausstattung;
	private int lieferzeit;
	private double preis;
	public ArrayList<Paket> ausstattungsListe;
	

	
	public Modell(){
		this.ausstattungsListe=new ArrayList<>();
		this.ausstattung=new Paket();
	}
	
	public Modell(String name,double preis,int lieferzeit){
		this.ausstattungsListe=new ArrayList<>();
		this.name=name;
		this.preis=preis;
		this.lieferzeit=lieferzeit;
	}
	
	public String getName(){
		return this.name;
	}
	public int getLieferzeit(){
		return this.lieferzeit;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public double getPreis(){
		return this.preis;
	}
	
	public void setPreis(double preis){
		this.preis=preis;
	}
	
	public ArrayList<Paket> getAusstattungListe(){
		return this.ausstattungsListe;
	}
	
	public void ausstattungsListeAusgeben() {
		for (int i = 0; i < this.ausstattungsListe.size(); i++) {
			System.out.println("["+(1+i)+"] "+this.ausstattungsListe.get(i).getNamePaket());
		}
	}
		
	

	public void paketHinzufuegen(Paket ausstattung){
		if(ausstattung == null) {
			System.out.println("Fehler !");
		} else {
			this.ausstattungsListe.add(ausstattung);
		}
	}


	
}
