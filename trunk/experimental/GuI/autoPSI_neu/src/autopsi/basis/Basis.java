package autopsi.basis;


import autopsi.gui.*;
import autopsi.gui.frame.mainFrame;
import autopsi.database.table.Termin;
import autopsi.database.table.TerminContainer;
import autopsi.database.Database;
import autopsi.database.dao.*;
import java.util.List;
import autopsi.database.dao.GenericDataObject;
import java.util.GregorianCalendar;

public class Basis {

	public static void main (String[] args)
	{	
		mainFrame frame = new mainFrame(); //Ein neues Mainframe (Liste der Patienten) starten

		frame.setLocation(290,100); //Startposition
		frame.setSize(950,604); //Fenstergröße
		frame.setVisible(true); //Frame anzeigen
		
		
	}
}
