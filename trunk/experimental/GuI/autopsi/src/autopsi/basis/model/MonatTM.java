package autopsi.basis.model;

import javax.swing.table.AbstractTableModel;

public class MonatTM extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private final String [] columnName = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
	//private String [][] termine = {{"9.00 Mathe VO","10.00 Grundz�ge der Informatik VU"},{"9.00 Mathe VO","11.00 UI Design VU"}};

	//Konstruktor mit Suchtext, Suchkriterium und Sortierungsattribut
	public MonatTM()
	{
		super();
		
	}
	
	public int getRowCount()
	{
		return 5; //Zeilenanzahl zur�ckgeben
	}

	public int getColumnCount()
	{
		return 7; //Spaltenanzahl zur�ckgeben
	}

	public String getColumnName(int c)
	{
		return columnName[c]; //Spaltenname zur�ckgeben
	}

	public boolean isCellEditable(int arg0, int arg1) 
	{
		return true; //Zellen sind nicht editierbar
	}

	public Object getValueAt(int row, int col)  //Werte aus den Spalten zur�ckgeben
	{
		try
		{	
			if(row<20)
			{
				if(col<20)
				{
					String ret = ""+row+" "+col+"";
					return ret;
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
