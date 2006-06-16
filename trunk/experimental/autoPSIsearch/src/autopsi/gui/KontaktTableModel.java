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
	public String email = null;
	public Kontakt suchKontakt = null;
	
	private final String [] columnName = {"Vorname", "Nachname", "PLZ", "Ort"};
	
	public List<GenericDataObject> getKontakte() {
		return this.kontakte;
	}
	
	private void readData() {
		String query="select * from Kontakt as k, Email as e, ATTACHABLE_OBJECT as a, ATTACHABLE_OBJECT_KATEGORIE as ok  where";
		//String query="select * from Kontakt as k where";
		Boolean first = true;
		try{
			IGenericDAO gdo = new GenericDAO();
			if (suchKontakt!=null) {
				if (suchKontakt.getPrename()!=null) {
					if (first){
						query += " LOWER(k.PRENAME) LIKE '%" + suchKontakt.getPrename().toLowerCase()+"%'";
						first = false;
					} else {
						query += "AND LOWER(k.PRENAME) LIKE '%" + suchKontakt.getPrename().toLowerCase()+"%'";
					}
				}
				if (suchKontakt.getSurname()!=null) {
					if (first){
						query += " LOWER(SURNAME) LIKE '%" + suchKontakt.getSurname().toLowerCase()+"%'";
						first = false;
					} else {
						query += "AND LOWER(SURNAME) LIKE '%" + suchKontakt.getSurname().toLowerCase()+"%'";
					}
				}
				if (suchKontakt.getBirthDate()!=null) {
					if (first){
						query += " BIRTH_DATE LIKE '%" + suchKontakt.getBirthDate()+"%'";
						first = false;
					} else {
						query += "AND BIRTH_DATE LIKE '%" + suchKontakt.getBirthDate()+"%'";
					}
				}
				if (suchKontakt.getTelBusiness()!=null){
					if (first) {
						query += " (TEL_PRIVATE LIKE '%" + suchKontakt.getTelBusiness()+"%' OR";
						query += " TEL_BUSINESS LIKE '%" + suchKontakt.getTelBusiness()+"%' OR";
						query += " TEL_MOBILE LIKE '%" + suchKontakt.getTelBusiness()+"%')";
						first = false;
					} else {
						query += " AND (TEL_PRIVATE LIKE '%" + suchKontakt.getTelBusiness()+"%' OR";
						query += " TEL_BUSINESS LIKE '%" + suchKontakt.getTelBusiness()+"%' OR";
						query += " TEL_MOBILE LIKE '%" + suchKontakt.getTelBusiness()+"%')";
					}
				}
				if (this.email != null){
					if (first){
						query += " e.EMAIL LIKE '%" +this.email+"%'";
						first = false;
					} else {
						query += " AND e.EMAIL LIKE '%" +this.email+"%'";
					}
				}
				if (first == false){
					//System.out.println(query);
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
	
	public void setEmail (String email){
		this.email=email;
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
