package autopsi.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;

import model.Termin;

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
		try{
			IGenericDAO gdo = new GenericDAO();
			gdo.setCurrentTable("TERMIN");
			this.termine =  gdo.getDataObjects(this.suchTermin);
		}
		catch (Exception e){
			System.out.println("AAARGH;"+e.toString());
		}
	}
	
	public TerminTableModel (){
	}
	
	
	public void setSuchtermin (Termin suchtermin){
		this.suchTermin=suchtermin;
		fireDataChanged();
	}
	
	public void fireDataChanged() {
		readData();
		fireTableDataChanged();
	}
	
	public int getColumnCount() {
		System.out.println("colCount = " + columnName.length);
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
			return ter.getSecondarytitle();
		else if (col==1)
			return ter.getDescription();
		else if (col==2)
			return ter.getDate();
		else if (col==3)
			return ter.getDuration();
		else return null;
	}
	
	

}
