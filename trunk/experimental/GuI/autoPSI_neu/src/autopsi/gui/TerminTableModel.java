package autopsi.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;

import autopsi.database.table.Termin;

public class TerminTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 8737097029189851737L;
	//List<GenericDataObject> termine = null;
	public List <GenericDataObject> termine;
	public Termin suchTermin = null;
	
	private final String [] columnName = {"Titel", "Beschreibung", "Datum", "Dauer"};
	
	public List<GenericDataObject> getTermine() {
		return this.termine;
	}
	
	private void readData() {
		String query="select * from Termin where";
		Boolean first = true;
		try{
			IGenericDAO gdo = new GenericDAO();
			if (suchTermin!=null) {
				if (suchTermin.getSecondaryTitle()!=null) {
					if (first){
						query += " SECONDARY_TITLE LIKE '%" + suchTermin.getSecondaryTitle()+"%'";
						first = false;
					} else {
						query += "AND SECONDARY_TITLE LIKE '%" + suchTermin.getSecondaryTitle()+"%'";
					}
				}
				if (first == false){
					this.termine =  gdo.unsafeQuery(query, suchTermin);
				}
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
