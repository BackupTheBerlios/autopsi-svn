package autopsi.basis.model;

import javax.swing.table.AbstractTableModel;
import javax.swing.*;
public class MonatTM extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private final String [] columnName = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
	//private String [][] termine = {{"9.00 Mathe VO","10.00 Grundzüge der Informatik VU"},{"9.00 Mathe VO","11.00 UI Design VU"}};

	//Konstruktor mit Suchtext, Suchkriterium und Sortierungsattribut
	public MonatTM()
	{
		super();
		
	}
	
	public Object getColumnClass()
	{
		return JLabel.class;
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
	String [] hallo = {"blabla","huhuhu"};
		try
		{	
			if(row<5)
			{
				if(col<7)
				{
					if(col<4)
					{
						return hallo[1];
					}
					return hallo[0];
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
