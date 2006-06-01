package autopsi.basis.model;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import autopsi.gui.*;
public class MonatTM extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private final String [] columnName = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
	Date datum = new Date();
	

	//Konstruktor mit Suchtext, Suchkriterium und Sortierungsattribut
	public MonatTM()
	{
		super();
	}
	
	public Class getColumnClass(int c)
	{
		return String[].class;
	}
	public int getRowCount()
	{
		return 5; //Zeilenanzahl zurückgeben
	}

	public int getColumnCount()
	{
		return 7; //Spaltenanzahl zurückgeben
	}

	public String getColumnName(int c)
	{
		return columnName[c]; //Spaltenname zurückgeben
	}

	public boolean isCellEditable(int arg0, int arg1) 
	{
		return true; //Zellen sind nicht editierbar
	}

	public Object getValueAt(int row, int col)  //Werte aus den Spalten zurückgeben
	{
		DateConverter converter = new DateConverter();
	
		datum.setMonth(4);
		datum.setDate(7*(row)+col+1);
		String dat = converter.toDay(datum).substring(0,2) + ", " +datum.getDate() + ". "+ converter.toMonth(datum);
		
	String [] hallo = {dat,"Spalte: "+ col};
		try
		{	
			if(row<5)
			{
				if(col<7)
				{
					
					return hallo;
				}
			}
			return null;
				
		}
		catch (Exception ex)
		{
			return null;
		}
		
		
	}

}
