package autopsi.basis.model;

import java.util.*;

import javax.swing.table.AbstractTableModel;
import autopsi.database.dao.*;
import autopsi.database.table.*;
import java.sql.Timestamp;
import java.util.List;


public class WocheTM extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	protected final String [] columnName = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
	Date datum = new Date();
	protected IGenericDAO igdao = new GenericDAO();
	protected List<GenericDataObject> termine;
	protected Timestamp begin = null;
	protected Timestamp end = null;
	
	

	//Konstruktor mit Suchtext, Suchkriterium und Sortierungsattribut
	public WocheTM(Timestamp begin, Timestamp end)
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
		return 16; //Zeilenanzahl zurückgeben
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
		return false; //Zellen sind nicht editierbar
	}

	public Object getValueAt(int row, int col)  //Werte aus den Spalten zurückgeben
	{
		
		Date d = new Date(begin.getTime());
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)+col );
		
		Timestamp stamp = new Timestamp(c.getTimeInMillis());
		
		List<String> ret = new ArrayList();
		ret.add(stamp.toString().substring(0,10));
		
		for (int i = 0;i<termine.size();i++)
		{
			if (((Termin)termine.get(i)).getSetDate(false, null).toString().substring(0,10).equals(stamp.toString().substring(0,10)))
			{	
				//System.out.println("termin    "+Integer.parseInt(((Termin)termine.get(i)).getSetDate(false, null).toString().substring(11,13)));
				//System.out.println("row       "+row);
				if(row+6==Integer.parseInt(((Termin)termine.get(i)).getSetDate(false, null).toString().substring(11,13)))
				{
					 ret.add(((Termin)termine.get(i)).getSetDate(false, null).toString().substring(11,16)+" "+((Termin)termine.get(i)).getSetSecondaryTitle(false, null));
				}
				
			}
		}
		String [] data = new String[ret.size()];
		for(int j = 0;j<ret.size();j++)
		{
		data[j]=ret.get(j);	
		}
		
		
			return data;
	}

}
