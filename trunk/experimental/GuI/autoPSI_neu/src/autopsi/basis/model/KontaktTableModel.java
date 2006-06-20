package autopsi.basis.model;

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
	public String group = null;
	
	private final String [] columnName = {"Vorname", "Nachname", "PLZ", "Ort"};
	
	public List<GenericDataObject> getKontakte() {
		return this.kontakte;
	}
	
	private void readData() {
		String query="select * from Kontakt as k, ATTACHABLE_OBJECT as a, ATTACHABLE_OBJECT_KATEGORIE as ok where k.GLOBAL_ID=a.GLOBAL_ID AND a.KATEGORIE_ID=ok.ID";
		try{
			IGenericDAO gdo = new GenericDAO();
			if (suchKontakt!=null) {
				if (suchKontakt.getPrename()!=null) {
					query += " AND LOWER(k.PRENAME) LIKE '%" + suchKontakt.getPrename().toLowerCase()+"%'";
				}
				if (suchKontakt.getSurname()!=null) {
					query += " AND LOWER(SURNAME) LIKE '%" + suchKontakt.getSurname().toLowerCase()+"%'";
				}
				if (suchKontakt.getBirthDate()!=null) {
					query += " AND BIRTH_DATE LIKE '%" + suchKontakt.getBirthDate()+"%'";
				}
				if (suchKontakt.getTelBusiness()!=null){
					query += " AND (TEL_PRIVATE LIKE '%" + suchKontakt.getTelBusiness()+"%' OR";
					query += " TEL_BUSINESS LIKE '%" + suchKontakt.getTelBusiness()+"%' OR";
					query += " TEL_MOBILE LIKE '%" + suchKontakt.getTelBusiness()+"%')";
				}
				if (suchKontakt.getFirstEmail()!=null){
					query += " AND ( LOWER(FIRST_EMAIL) LIKE '%" + suchKontakt.getFirstEmail().toLowerCase() + "%' OR";
					query += " LOWER(SECOND_EMAIL) LIKE '%" + suchKontakt.getFirstEmail().toLowerCase() +"%')";
				}
				if (this.group != null){
					query += " AND ok.TITLE = '"+ this.group+"'";
				}
				if (suchKontakt.getAZipCode()!=null) {
					query += " AND A_ZIPCODE  = " + suchKontakt.getAZipCode();
				}
				if (suchKontakt.getACity()!=null) {
					query += " AND A_CITY LIKE '%" + suchKontakt.getACity()+"%'";
				}
				if (suchKontakt.getAAdress()!=null) {
					query += " AND A_ADRESS LIKE '%" + suchKontakt.getAAdress()+"%'";
				}
				System.out.println(query);
				this.kontakte =  gdo.unsafeQuery(query, suchKontakt);
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
	
	public void setGroup(String gruppe){
		this.group = gruppe;
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
