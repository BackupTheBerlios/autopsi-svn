package autopsi.gui;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;

import autopsi.database.table.Termin;

public class TerminTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 8737097029189851737L;
	//List<GenericDataObject> termine = null;
	private List <GenericDataObject> termine;
	private Termin suchTermin = null;
	private String datum=null;
	
	private final String [] columnName = {"Titel", "Beschreibung", "Datum", "Dauer"};
	
	public List<GenericDataObject> getTermine() {
		return this.termine;
	}
	
	private void readData() {
		String query="select * from Termin as t";
		Boolean first = true;
		try{
			IGenericDAO gdo = new GenericDAO();
			if (suchTermin!=null) {
				if (suchTermin.getSecondaryTitle()!=null) {
					if (!first){
						query += " AND";
					} else {
						query += " where";
					}
					query += " LOWER(SECONDARY_TITLE) LIKE '%" + suchTermin.getSecondaryTitle().toLowerCase()+"%'";
					first = false;
				}
				if (suchTermin.getDescription()!=null) {
					if (!first){
						query += " AND";
					} else {
						query += " where";
					}
					query += " LOWER(DESCRIPTION) LIKE '%" + suchTermin.getDescription().toLowerCase()+"%'";
					first = false;
				}
				if (this.datum!=null){
					if (!first){
						query += " AND";
					} else {
						query += " where";
					}

					query += " DATE LIKE '%" + this.datum +"%'";
					first = false;
				}
				System.out.println("Query:"+query);
				this.termine =  gdo.unsafeQuery(query, suchTermin);
			}
		} catch (Exception e){
			System.out.println("AAARGH;"+e.toString());
		}
	}
	
	public TerminTableModel (){
	}
	
	
	public void setSuchTermin (Termin suchtermin){
		this.suchTermin=suchtermin;
		fireDataChanged();
	}
	
	public void setDatum (String datum){
		this.datum=datum;
	}
	
	public void fireDataChanged() {
		readData();
		fireTableDataChanged();
	}
	
	public int getColumnCount() {
		//System.out.println("colCount = " + columnName.length);
		return columnName.length;
	}
	
	public int getRowCount() {
		//System.out.println("rowcount = " + Lesers.size());
		if (termine != null) {
			return termine.size();
		} else {
			return 0;
		}
	}
	public String getColumnName(int c) {
		//System.out.println("colName = " + columnName[c]);
		return columnName[c];
	}
	
	public Object getValueAt(int row, int col) {
		//System.out.println("getValue row = " + row + " col = " + col); 
		Termin ter = (Termin) termine.get(row);
		
		if (ter==null)
			return null;
		else if (col==0)
			return ter.getSecondaryTitle();
		else if (col==1)
			return ter.getDescription();
		else if (col==2)
			return ter.getDate();
		else if (col==3)
			return ter.getDuration();
		else return null;
	}
	
	

}
