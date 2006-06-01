package autopsi.basis;

import autopsi.gui.mainFrame;
import autopsi.gui.editFrame;
import autopsi.database.table.Termin;
import autopsi.database.table.TerminContainer;
import autopsi.database.Database;
import autopsi.database.dao.*;
import java.util.List;
import autopsi.database.dao.GenericDataObject;


public class Basis {

	public static void main (String[] args)
	{	
		IGenericDAO database = new GenericDAO();
		database.setCurrentTable("Termincontainer");
		try{
		List<GenericDataObject> l = database.getDataObjects(new TerminContainer());
		System.out.println("size=="+l.size());
		System.out.println("Datensatz0=="+ ((TerminContainer)(l.get(1))).getSetTitle(false, null) );
		}
		catch (Exception e){
			System.out.println("Error=="+e.toString());
		}
		
		
		
		IGenericDAO db2 = new GenericDAO();
		List<GenericDataObject> list = null;
		TerminContainer tc = new TerminContainer();
		tc.getSetTitle(true, "VU Grundzüge der Informatik");
		db2.setCurrentTable("Termincontainer");
		try{
			list = db2.getDataObjects(tc);
		}
		catch (Exception e){
			System.out.println("Exception::"+e.toString());
		}
		for (int i=0;i<list.size();i++)
			System.out.println("Element Nr."+i+"hat Titel '"+((TerminContainer)list.get(i)).getSetTitle(false,null)+"'");
		
		mainFrame frame = new mainFrame(); //Ein neues Mainframe (Liste der Patienten) starten

		frame.setLocation(290,100); //Startposition
		frame.setSize(897,604); //Fenstergröße
		frame.setTitle("autoPSI"); //Titel setzen
		frame.setVisible(true); //Frame anzeigen
		
	}
}
