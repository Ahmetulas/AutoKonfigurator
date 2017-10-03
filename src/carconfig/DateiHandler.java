package carconfig;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;


public class DateiHandler {

	Scanner s;
	
	public DateiHandler(File f){
			try {
				s = new Scanner(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
}
