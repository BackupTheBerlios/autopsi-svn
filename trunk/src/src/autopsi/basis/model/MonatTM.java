package autopsi.basis.model;

import java.util.*;

import javax.swing.table.AbstractTableModel;
import autopsi.database.dao.*;
import autopsi.database.table.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Iterator;


public class MonatTM extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	protected final String [] columnName = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
	Date datum = new Date();
	protected IGenericDAO igdao = new GenericDAO();
	protected List<GenericDataObject> termine;
	protected Timestamp begin = null;
	protected Timestamp end = null;
	
	

	//Konstruktor mit Suchtext, Suchkriterium und Sortierungsattribut
	public MonatTM(Timestamp begin, Timestamp end)
	{
		super();
		this.setTimeSpace(begin, end);
		Termin tc = new Termin();
		
		igdao.setCurrentTable("Termin");
		try
		{
			termine = igdao.getDataObjects(tc);
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}		
	}
	
	public void setTimeSpace(Timestamp begin, Timestamp end){
		this.begin = begin;
		this.end = end;
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
		Date d = new Date(begin.getTime());
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		Timestamp ts = new Timestamp(c.getTimeInMillis());
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)+ ((7*row)+col+1 ));
		Iterator iter = termine.iterator();
		while(iter.hasNext()){
			Termin t = (Termin)iter.next();
			if (t.getSetDate(false, null) compareTo(c.getTime()) == 0){	
				return t.getSetSecondaryTitle(false, null);
			}
		}
		
	/*	datum.setMonth(4);
		datum.setDate(7*(row)+col+1);
		String dat = datum.toString();
		
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
		*/
		return null;
		
		
	}

}
