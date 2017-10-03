package carconfig.View;

import carconfig.View.Ausgabe;
import carconfig.Modell.Modell;
import carconfig.Modell.Konfigurator;
import carconfig.Daten.DateiHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Menue
{

	private Ausgabe ausgabe;
	private Konfigurator konf;
	private Modell modell;


	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Calendar c = Calendar.getInstance();
	Scanner s= new Scanner (System.in);


	public Menue(){
		ausgabe = new Ausgabe ();
		modell = new Modell();
		konf = new Konfigurator();
	}

	public void hauptmenue()
	{
		int i = 1;
		ausgabe.hauptmenueAnzeigen();
		i = s.nextInt();

		switch(i)
		{
			case 1: {
			    this.modellAuswahl();
	        }
			break;

			case 2: {
				ausgabe.beendenAusgabe();
	            i = 0;
	        }
			break;
		}
	}


    public void modellAuswahl(){
		int i=0;
		konf.modellNamenAusgeben();
		System.out.println("["+(konf.getModellListe().size()+1)+"]"+"zurück");
		i=s.nextInt();

		if(i==konf.getModellListe().size()+1){
			this.hauptmenue();
		}else if (i<=konf.getModellListe().size() && i>0){
			System.out.println("Sie haben "+ konf.getModellListe().get(i-1).getName()+ " ausgewaehlt.");
			System.out.println("Preis: "+ konf.getModellListe().get(i-1).getPreis());
			this.paketeAuswahl(konf.getModellListe().get(i-1));
		}else {
		    ausgabe.fehlerAnzeige("falsche Eingabe!");
		    this.modellAuswahl();
		}
}


	public void paketeAuswahl(Modell modellParam) {
        int i = 0;
        int y = 0;
        modellParam.ausstattungsListeAusgeben();
        System.out.println("[" + (modellParam.getAusstattungListe().size() + 1) + "]" + "zurück");

        i = s.nextInt();
        if (i==modellParam.getAusstattungListe().size()+1){
            this.modellAuswahl();
        }

				else if (i <= modellParam.getAusstattungListe().size() && i > 0)
				{
				    ausgabe.bestellungAusgeben(modellParam,i);
				y = i;
				i = s.nextInt();

			if (i == 1) {
				System.out.println("Vielen Dank für Ihre Bestellung, prüfen Sie Ihre Mail-Box!  ");

				try {
					PrintWriter writer = new PrintWriter(
							"/Users/ahmetulas/Documents/workspace/Carconfig/src/carconfig/mail.txt", "UTF-8");
					ausgabe.gesamtDatenAusgebenMail(modellParam, y, writer);
					writer.close();
				} catch (IOException e) {
					e.getMessage();
				}


			}
			else if (i == modellParam.getAusstattungListe().size() + 1) {
				this.paketeAuswahl(modellParam);
			}
		}
		else {
			ausgabe.fehlerAnzeige("falsche Eingabe");
			this.paketeAuswahl(modellParam);
		}

	}

}
