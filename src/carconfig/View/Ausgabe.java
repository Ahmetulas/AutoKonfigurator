package carconfig.View;

import carconfig.Modell.Modell;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ausgabe {


    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar c = Calendar.getInstance();

    public void fehlerAnzeige (String fehler){
    System.out.println("Es ist ein Felhler aufgetreten: "+ fehler);
    }

    public void hauptmenueAnzeigen(){
        System.out.println("Willkommen bei Mercedes-Benz! \n");
        System.out.println("[1] Modell auswählen");
        System.out.println("[2] Exit");
    }

    public void bestellungAusgeben(Modell modellParam, int i){
            System.out.println("------------------------------------------");
			System.out.println("		IHRE BESTELLUNG:");
			System.out.println("------------------------------------------");
            System.out.println("MODELL:		" + modellParam.getName());
			System.out.println("Preis\n(exkl.pakete):	" + modellParam.getPreis() + ",-\n");
			System.out.println("AUSSTATTUNG: ");
			modellParam.getAusstattungListe().get(i - 1).getAlleInfos();
			System.out.println("---------------------");
			System.out.println("GESAMTPREIS:	" + ((modellParam.getPreis()) + (modellParam.getAusstattungListe().get(i - 1).getPreisPaket())) + ",-\n");
			System.out.println("[1] bestätigen");
			System.out.println("[" + (modellParam.getAusstattungListe().size() + 1) + "]" + " zurueck");
    }

public void beendenAusgabe(){
  System.out.println("Aufwiedersehen !");

}


    public void gesamtDatenAusgebenMail(Modell modell, int i, PrintWriter writer){
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
    //ausgabe.mailAusgabe(Modell modell, int i, Printwriter writer){

    //}
}
