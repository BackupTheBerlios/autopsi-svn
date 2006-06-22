package autopsi.basis.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;

import autopsi.database.table.Kontakt;
import autopsi.javaspace.IServiceCommunicator;
import autopsi.javaspace.ServiceCommunicator;

public class KontaktTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 8737097029189851737L;
	public List <GenericDataObject> kontakte;
	public Kontakt suchKontakt = null;
	public IGenericDAO gdo = new GenericDAO();
	public IServiceCommunicator ogdo = null;
	public String tablename = "KONTAKT";
	public List <GenericDataObject> lastDeletedObjects =  new ArrayList<GenericDataObject>();
	public String group = null;
	public boolean onlinesuche = false;
	
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
				if (this.group != null && !this.group.equals("-")){
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
	
	public void readOnlineData () {
		this.onlinesuche = true;
		Kontakt temp =(Kontakt) ogdo.getObject(this.suchKontakt);
		this.kontakte = new ArrayList<GenericDataObject>();
		this.kontakte.add(temp);
	}
	
	public KontaktTableModel (){
		this.ogdo = new ServiceCommunicator();
		this.kontakte = new ArrayList<GenericDataObject>();
	}
	
	public void deleteSelectedRow(JTable table) {
		Kontakt k = new Kontakt();
		boolean selected = false;
		boolean deleted = false;
		boolean first = true;
		int auswahl = 0;
		k = null;
		//	 Check each cell in the range
	    for (int r=0; r<this.getRowCount(); r++) {
            if (table.isCellSelected(r, 1)) {
            	selected = true;
            	k=(Kontakt) this.getKontakte().get(r);
            	if (k.getGlobalId() != null) {
            		if (first) {
            			auswahl = JOptionPane.showConfirmDialog(null, "Sind sie sicher dass sie alle markierte Kontakte löschen wollen?", "Löschen?",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            		}
	            	if (auswahl == JOptionPane.YES_OPTION) {
	            		//weiter mit löschen
	            		deleted = this.deleteKontakt(k);
	            		if (deleted) {
	            			if (first){
	            				JOptionPane.showMessageDialog(null, "Die markierten Kontakte wurden erfolgreich gelöscht." , "Gelöscht!" , JOptionPane.INFORMATION_MESSAGE);
	            			}
	            		} else {
	            			JOptionPane.showMessageDialog(null, "Der markierte Kontakt konnte nicht gelöscht werden." , "Löschen!" , JOptionPane.ERROR_MESSAGE);
	            		}
	            	}
            	} else {
            		JOptionPane.showMessageDialog(null, "Dieser Kontakt kann nicht gelöscht werden." , "Null Object!" , JOptionPane.ERROR_MESSAGE);
            	}
            	first = false;
            }    
	    }
	    
	    if (selected == false) {
	    	JOptionPane.showMessageDialog(null, "Bitte selektieren Sie mindestens eine Prüfung in der Tabelle", "Prüfung wurde nicht ausgewählt!" , JOptionPane.ERROR_MESSAGE);
	    }
	    if (deleted) {
	    	this.fireDataChanged();
	    }
	}
	
	/**
	 */
	public boolean deleteKontakt(Kontakt k){
		if (k == null)
			return false;
		
		try{
			IGenericDAO gdo = new GenericDAO();
			//System.out.println("SELECT * FROM " + this.tablename+" WHERE GLOBAL_ID ="+k.getGlobalId());
			List <GenericDataObject> loeschen = gdo.unsafeQuery("SELECT * FROM " + this.tablename+" WHERE GLOBAL_ID ="+k.getGlobalId(), new Kontakt());
				lastDeletedObjects.add(loeschen.get(0));
			loeschen = gdo.unsafeQuery("DELETE FROM " + this.tablename+" WHERE GLOBAL_ID ="+k.getGlobalId(), new Kontakt());
			return true;
		} catch (Exception e){
			System.out.println("PruefungTableModel @ deletePruefung;"+e.toString());
		}
		return true;
	}
	
	public void restoreLastDeletedObjects(){
		if (this.lastDeletedObjects.size() != 0) {
			for (int i=0;i<this.lastDeletedObjects.size();i++){
				addKontakt(this.lastDeletedObjects.get(i));
			}
			JOptionPane.showMessageDialog(null, "Die gelöschten Objekte wurden erfolgreich wiederhergestellt." , "Wiederherstellung Erfolgreich!" , JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Sie haben bisher keine Objekte gelöscht." , "Keine gelöschten Objekte" , JOptionPane.INFORMATION_MESSAGE);
		}
		this.lastDeletedObjects.clear();
		this.fireDataChanged();
	}
	
	public void downloadObject(){
		if (this.onlinesuche==true && this.kontakte.size() != 0) {
			for (int i=0;i<this.kontakte.size();i++){
				addKontakt(this.kontakte.get(i));
			}
			JOptionPane.showMessageDialog(null, "Die Prüfung wurde heruntergeladen." , "Download abgeschlossen." , JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Sie müssen zuerst nach eine Prüfung suchen." , "Keine Prüfung zum herunterladen." , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void addKontakt(GenericDataObject p){
		try{
			if (p!=null){
				gdo.setCurrentTable(this.tablename);
				gdo.addDataObject(p);
			} else {
				System.out.println("NULLLL ELEMENTTTT!!!! :(((((((");
			}
		} catch (Exception e){
			System.out.println("PruefungTableModel @ addPruefung;"+e.toString());
		}
	}
	
	public void fireOnlineDataChanged() {
		readOnlineData();
		fireTableDataChanged();
	}
	
	
	public void setSuchKontakt (Kontakt suchKontakt){
		this.suchKontakt=suchKontakt;
		//fireDataChanged();
	}
	
	public void setGroup(String gruppe){
		this.group = gruppe;
	}
	
	public void fireDataChanged() {
		this.onlinesuche = false;
		readData();
		this.fireTableDataChanged();
	}
	
	public int getColumnCount() {
		System.out.println("colCount = " + columnName.length);
		return columnName.length;
	}
	
	public int getRowCount() {
		if (kontakte != null) {
			return kontakte.size();
		} else {
			return 0;
		}
	}
	public String getColumnName(int c) {
		System.out.println("colName = " + columnName[c]);
		return columnName[c];
	}
	
	public Object getValueAt(int row, int col) {
		//System.out.println("getValueAt: r="+row+", c="+col);
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
