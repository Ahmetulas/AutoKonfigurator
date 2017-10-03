package carconfig;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Menue {

	private Konfigurator konf;
	private DateiHandler dh;
	private Modell modell;
	private int i;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Calendar c = Calendar.getInstance();
	Scanner s= new Scanner (System.in);

	

	public Menue(){
		modell = new Modell();
		konf = new Konfigurator();
	}
	

	public void hauptmenue(){
		int i = 1;

		System.out.println("Willkommen bei Mercedes-Benz! \n");
		System.out.println("[1] Modell auswählen");
		System.out.println("[2] Exit");
		i = s.nextInt();

		switch(i){
		case 1: this.ModellAuswahl();
		break;
		case 2: System.out.println("Aufwiedersehen !");
				i =0;
		break;
				}
	}


public void ModellAuswahl(){
		i=0;

		konf.ModellNamenAusgeben();
		System.out.println("["+(konf.getModellListe().size()+1)+"]"+"zurück");
		i=s.nextInt();

		if(i==konf.getModellListe().size()+1){
			this.hauptmenue();
		}else if (i<=konf.getModellListe().size() && i>0){
			System.out.println("Sie haben "+ konf.getModellListe().get(i-1).getName()+ " ausgewaehlt.");
			System.out.println("Preis: "+ konf.getModellListe().get(i-1).getPreis());
			this.paketeAuswahl(konf.getModellListe().get(i-1));
		}else {
			System.out.println("falsche Eingabe !");
		this.ModellAuswahl();
		}
}


	public void paketeAuswahl(Modell modellParam) {
		int i = 0;
		int ip = 0;
		modellParam.ausstattungsListeAusgeben();
		System.out.println("[" + (modellParam.getAusstattungListe().size() + 1) + "]" + "zurück");

		i = s.nextInt();
		if (i == modellParam.getAusstattungListe().size() + 1) {
			this.ModellAuswahl();
		}

		else if (i <= modellParam.getAusstattungListe().size() && i > 0) {
			System.out.println("------------------------------------------");
			System.out.println("		IHRE BESTELLUNG:");
			System.out.println("------------------------------------------");
			System.out.println("MODELL:		" + modellParam.getName());
			System.out.println("Preis\n(exkl.pakete):	" + modellParam.getPreis() + ",-\n");
			System.out.println("AUSSTATTUNG: ");

			modellParam.getAusstattungListe().get(i - 1).getAlleInfos();
			System.out.println("---------------------");
			System.out.println("GESAMTPREIS:	"
					+ ((modellParam.getPreis()) + (modellParam.getAusstattungListe().get(i - 1).getPreisPaket()))
					+ ",-\n");
			System.out.println("[1] bestätigen");
			System.out.println("[" + (modellParam.getAusstattungListe().size() + 1) + "]" + " zurueck");
			ip = i;
			i = s.nextInt();

			if (i == 1) {
				System.out.println("Vielen Dank für Ihre Bestellung, prüfen Sie Ihre Mail-Box!  ");

				try {
					PrintWriter writer = new PrintWriter(
							"/Users/ahmetulas/Documents/workspace/Carconfig/src/carconfig/mail.txt", "UTF-8");
					this.gesamtDatenAusgebenMail(modellParam, ip, writer);
					writer.close();
				} catch (IOException e) {
					e.getMessage();
				}
			} else if (i == modellParam.getAusstattungListe().size() + 1) {
				this.paketeAuswahl(modellParam);
			}
		}

		else {
			System.out.println("falsche Eingabe !");
			this.paketeAuswahl(modellParam);
		}

	}

public void gesamtDatenAusgebenMail(Modell modell,int i, PrintWriter writer){
	writer.println("----------Mercedes-Benz AG----------\nVielen Dank für Ihre Bestellung.");
	writer.println("-----------------------------------");
	writer.println("IHRE BESTELLUNG:");
	writer.println("-----------------------------------");
	writer.println("MODELL:			"+modell.getName());
	writer.println("Preis:			"+modell.getPreis()+",-\n");
	writer.println("AUSSTATTUNG: ");
	modell.getAusstattungListe().get(i-1).getAlleInfosMail(writer);;
	writer.println("-----------------------------------");
	writer.println("GESAMTPREIS:	"+((modell.getPreis()) + (modell.getAusstattungListe().get(i-1).getPreisPaket()))+",-");
	writer.println("-----------------------------------");
	writer.println("-----------------------------------");
	c.add(Calendar.DATE, modell.getLieferzeit()); 
	String output = sdf.format(c.getTime());
	writer.println("Die Lieferung erfolgt am: "+output);

}
}
