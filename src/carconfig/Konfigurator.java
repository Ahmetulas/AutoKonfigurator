package carconfig;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Konfigurator {

	private ArrayList<Modell> modellListe;
	public Konfigurator() {

		this.modellListe = new ArrayList<>();
		this.generateObjectsFromJSON(this.dateiInhalt());
	}

	private void generateObjectsFromJSON(String json) {

		JSONObject obj = new JSONObject(json);
		JSONArray arr = obj.getJSONArray("modelle");

		for (int i = 0; i < arr.length(); ++i) {
			Modell modell = new Modell(arr.getJSONObject(i).getString("name"), arr.getJSONObject(i).getDouble("preis"),
					arr.getJSONObject(i).getInt("lieferzeit"));
			JSONArray arrpak = arr.getJSONObject(i).getJSONArray("pakete");
			for (int x = 0; x < arrpak.length(); x++) {
				Paket paket = new Paket(arrpak.getJSONObject(x).getString("name"),
						arrpak.getJSONObject(x).getString("beschreibung"), arrpak.getJSONObject(x).getDouble("preis"));
				modell.paketHinzufuegen(paket);
			}
			this.modellListe.add(modell);
		}

	}

	private String dateiInhalt() {

		BufferedReader br = null;
		FileReader fr = null;
		String dateiinhalt = "";

		try {
			fr = new FileReader("/Users/ahmetulas/Documents/workspace/Carconfig/src/carconfig/file.json");
			br = new BufferedReader(fr);
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				dateiinhalt += currentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {

				ex.printStackTrace();
			}
		}

		return dateiinhalt;
	}

	public ArrayList<Modell> getModellListe() {
		return this.modellListe;
	}

	public void ModellNamenAusgeben() {

		for (int i = 0; i < this.modellListe.size(); i++) {
			String name = this.modellListe.get(i).getName();
			System.out.println("[" + (i + 1) + "]" + name);
		}
	}
}
