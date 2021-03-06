package autopsi.basis.model;

import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import autopsi.database.dao.*;
import autopsi.database.table.*;
import autopsi.gui.frame.InfoDialog;

import java.sql.Timestamp;
import java.util.List;


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
			termine = igdao.unsafeQuery("select * from termin where date >='"+begin + "' and date <='"+end+"' order by date",tc);
		}
		catch(Exception ex)
		{
			showErrorDialog();
			System.exit(0);
		}		
	}
	
	
	public void setTimeSpace(Timestamp begin, Timestamp end){
		this.begin = begin;
		this.end = end;
	}
	
	public Class getColumnClass(int c)
	{
		return Termin[].class;
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
		return false; //Zellen sind nicht editierbar
	}

	public Object getValueAt(int row, int col)  //Werte aus den Spalten zur�ckgeben
	{
		
		Date d = new Date(begin.getTime());
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)+ ((7*row)+col));

		Timestamp stamp = new Timestamp(c.getTimeInMillis());
		List<Termin> ret = new ArrayList<Termin>();
		Termin ter = new Termin();
		ter.setDate(stamp);
		ret.add(ter);
		for (int i = 0;i<termine.size();i++)
		{
			if (((Termin)termine.get(i)).getDate().toString().substring(0,10).equals(stamp.toString().substring(0,10))){	
				 ret.add((Termin)termine.get(i));
			}
		}
		Termin[] retur= new Termin[ret.size()];
		for(int i = 0;i<ret.size();i++)
		{
			retur[i] = ret.get(i);
		}

			return retur;
	}
	
	private void showErrorDialog()
	{
		JOptionPane.showMessageDialog(null, "Verbindung zur Datenbank konnte nicht hergestellt werden. M�glicherweise wurden mehrere Instanzen von autoPSI gestartet.", "Fehler!",JOptionPane.ERROR_MESSAGE);
	}

}
