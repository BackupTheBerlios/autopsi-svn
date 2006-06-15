package autopsi.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;

import autopsi.database.table.Kontakt;

public class KontaktTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 8737097029189851737L;
	public List <GenericDataObject> kontakte;
	public Kontakt suchKontakt = null;
	
	private final String [] columnName = {"Vorname", "Nachname", "PLZ", "Ort"};
	
	public List<GenericDataObject> getKontakte() {
		return this.kontakte;
	}
	
	private void readData() {
		String query="select * from Kontakt where";
		Boolean first = true;
		try{
			IGenericDAO gdo = new GenericDAO();
			if (suchKontakt!=null) {
				if (suchKontakt.getPrename()!=null) {
					if (first){
						query += " PRENAME LIKE '%" + suchKontakt.getPrename()+"%'";
						first = false;
					} else {
						query += "AND PRENAME LIKE '%" + suchKontakt.getPrename()+"%'";
					}
				}
				if (first == false){
					this.kontakte =  gdo.unsafeQuery(query, suchKontakt);
				}
			}
		} catch (Exception e){
			System.out.println("AAARGH;"+e.toString());
		}
	}
	
	public KontaktTableModel (){
	}
	
	
	public void setSuchKontakt (Kontakt suchKontakt){
		this.suchKontakt=suchKontakt;
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
		if (kontakte != null) {
			return kontakte.size();
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
		Kontakt kont = (Kontakt) kontakte.get(row);
		
		if (kont==null)
			return null;
		else if (col==0)
			return kont.getPrename();
		else if (col==1)
			return kont.getSurname();
		else if (col==2)
			return kont.getAZipCode();
		else if (col==3)
			return kont.getACity();
		else return null;
	}
	
	

}
