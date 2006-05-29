package autopsi.basis;

import autopsi.gui.mainFrame;
import autopsi.gui.editFrame;


public class Basis {

	public static void main (String[] args)
	{
		mainFrame frame = new mainFrame(); //Ein neues Mainframe (Liste der Patienten) starten
	
	frame.setLocation(290,100); //Startposition
	frame.setSize(900,600); //Fenstergröße
	frame.setTitle("autoPSI"); //Titel setzen
	
	frame.setVisible(true); //Frame anzeigen
		
	}
}
