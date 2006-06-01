package autopsi.basis;

import autopsi.gui.mainFrame;
import autopsi.gui.editFrame;
import autopsi.database.table.Termin;


public class Basis {

	public static void main (String[] args)
	{	

		mainFrame frame = new mainFrame(); //Ein neues Mainframe (Liste der Patienten) starten

		frame.setLocation(290,100); //Startposition
		frame.setSize(897,604); //Fenstergröße
		frame.setTitle("autoPSI"); //Titel setzen
		frame.setVisible(true); //Frame anzeigen
		
	}
}
