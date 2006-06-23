package autopsi.basis.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;

import autopsi.database.table.AttachableObject;
import autopsi.database.table.Kontakt;
import autopsi.javaspace.ServiceCommunicator;

public class KontaktTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 8737097029189851737L;

	public List <GenericDataObject> kontakte = new ArrayList<GenericDataObject>();
	public List <GenericDataObject> lastDeletedObjects =  new ArrayList<GenericDataObject>();
	public GenericDAO gdo;
	public ServiceCommunicator ogdo = null;
	public boolean onlinesuche = false;
	public String tablename = "kontakt";
	public Kontakt suchKontakt = null;
	public String group = null;
	public Integer order = null;
	
	private final String [] columnName = {"Vorname", "Nachname", "PLZ", "Ort"};
	private final String [] columnDBName = {"Prename", "Surename", "A_ZIPCODE", "A_CITY"};
	
	public List<GenericDataObject> getKontakte() {
		return this.kontakte;
	}
	
	public GenericDataObject getKontaktAt(int at) {
		return this.kontakte.get(at);
	}
	
	
	/**
	 * Diese Methode liest die Daten von der lokalen Datenbank mit Hilfe des Generic-DAO aus.
	 * Diese Methode speichert alle Objekte aus der Datenbank als eine Liste ab.
	 * Die Datensätze werden nach order geordnet.
	 * @author	Alpay Firato
	 */
	private void readData() {
		String query="select * from Kontakt as k, ATTACHABLE_OBJECT as a, ATTACHABLE_OBJECT_KATEGORIE as ok where k.GLOBAL_ID=a.GLOBAL_ID AND a.KATEGORIE_ID=ok.ID";
		try{
			GenericDAO gdo = new GenericDAO();
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
				
				if (this.order!=null) {
					query += " ORDER BY "+columnDBName[this.order];
				}
				//System.out.println(query);
				this.kontakte =  gdo.unsafeQuery(query, suchKontakt);
			}
		} catch (Exception e){
			System.out.println("AAARGH;"+e.toString());
		}
	}
	
	/**
	 * Diese Methode liest die Daten aus dem Javaspace mit Hilfe des ServiceCommunicators aus.
	 * Das, aus dem Javaspace, rausgelesene Objekt wird in eine Liste abgespeichert.
	 * @author	Alpay Firato
	 */
	public void readOnlineData () {
		try {
			this.onlinesuche = true;
			Kontakt temp =(Kontakt) ogdo.getObject(this.suchKontakt);
			this.kontakte = new ArrayList<GenericDataObject>();
			this.kontakte.add(temp);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " +e.toString(), "Error!" , JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Im Konstruktor wird die beiden Generic-DAO und Javaspace ServiceCommunicator angelegt.
	 * @author	Alpay Firato
	 */
	public KontaktTableModel (){
		this.gdo = new GenericDAO();
		this.ogdo = new ServiceCommunicator();
	}
	
	/**
	 * Diese Methode wird immer bei einer Aenderung der lokalen Daten aufgerufen.
	 * @author	Alpay Firato
	 */
	public void fireDataChanged() {
		this.onlinesuche = false;
		readData();
		fireTableDataChanged();
	}
	
	/**
	 * Diese Methode wird immer bei einer Aenderung der online Daten aufgerufen.
	 * @author	Alpay Firato
	 */
	public void fireOnlineDataChanged() {
		this.onlinesuche = true;
		readOnlineData();
		fireTableDataChanged();
	}
	
	/**
	 * Diese Methode löscht die markierten Objekte aus der lokalen Datenbank.
	 * Nach einem erfolgreichen Löschvorgang wird fireDataChanged aufgerufen.
	 * Alle gelöschten Objekte wird als eine Liste abgespeichert damit man später
	 * diese Daten wiederherstellen kann wenn erfordert.
	 * @param	JTable	Das ist die Tabelle bei der die Daten markiert worden sind.
	 * @author	Alpay Firato
	 */
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
            			auswahl = JOptionPane.showConfirmDialog(null, "Sind sie sicher dass sie alle markierten Objekte löschen wollen?", "Löschen?",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            		}
	            	if (auswahl == JOptionPane.YES_OPTION) {
	            		//weiter mit löschen
	            		deleted = this.deleteKontakt(k);
	            		if (deleted) {
	            			if (first){
	            				JOptionPane.showMessageDialog(null, "Das Löschvorgang war erfolgreich." , "Gelöscht!" , JOptionPane.INFORMATION_MESSAGE);
	            			}
	            		} else {
	            			JOptionPane.showMessageDialog(null, "Das Objekt konnte nicht gelöscht werden." , "Löschen!" , JOptionPane.ERROR_MESSAGE);
	            		}
	            	}
            	} else {
            		JOptionPane.showMessageDialog(null, "Dieses Objekt kann nicht gelöscht werden." , "Null Object!" , JOptionPane.ERROR_MESSAGE);
            	}
            	first = false;
            }    
	    }
	    
	    if (selected == false) {
	    	JOptionPane.showMessageDialog(null, "Bitte selektieren Sie mindestens eine Reihe in der Tabelle", "Data wurde nicht ausgewählt!" , JOptionPane.ERROR_MESSAGE);
	    }
	    if (deleted) {
	    	this.fireDataChanged();
	    }
	}
	
	/**
	 * Diese Methode löscht das übergebene Objekt aus der lokalen Datenbank.
	 * @param	Kontakt	Das ist der Kontakt der aus dem Datenbank entfernt werden soll.
	 * @author	Alpay Firato
	 */
	public boolean deleteKontakt(Kontakt k){
		if (k == null)
			return false;
		
		try{
			GenericDAO gdo = new GenericDAO();
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
	
	/**
	 * Diese Methode kann alle gelöschten Objekte wiederherstellen und in die Datenbank einfügen.
	 * @author	Alpay Firato
	 */
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
	
	/**
	 * Diese Methode kann aus dem Javaspace gefundene Objekte in die lokale Datenbank integrieren.
	 * @author	Alpay Firato
	 */
	public void downloadObject(){
		if (this.onlinesuche==true && this.kontakte.size() != 0) {
			for (int i=0;i<this.kontakte.size();i++){
				addKontakt(this.kontakte.get(i));
			}
			JOptionPane.showMessageDialog(null, "Das ausgewählte Objekt wurde heruntergeladen." , "Download abgeschlossen." , JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Sie müssen zuerst ein Objekt auswählen." , "Kein Objekt zum herunterladen ausgewählt." , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Diese Methode kann das übergebene Objekt in die lokale Datenbank einfügen.
	 * @param	GenericDataObject	Das Object das in die Datenbank eingefügt werden soll.
	 * @author	Alpay Firato
	 */
	public void addKontakt(GenericDataObject p){
		try{
			if (p!=null){
				gdo.setCurrentTable("attachable_object");
				AttachableObject a = new AttachableObject();
				a.setTableName(this.tablename.toLowerCase());
				a.setKategorieId(0);
				gdo.addDataObject(a);
				a = (AttachableObject)gdo.unsafeQuery("select * from attachable_object where global_id=identity()", new AttachableObject()).get(0);
				Kontakt k = (Kontakt) p;
				k.setGlobalId(a.getId());
				k.setKategorieId(0);
				gdo.setCurrentTable(this.tablename);
				gdo.addDataObject(k);
			} else {
				System.out.println("NULLLL ELEMENTTTT!!!! :(((((((");
			}
		} catch (Exception e){
			System.out.println("KontaktTableModel @ addPruefung;"+e.toString());
		}
	}
	
	/**
	 * Diese Methode kann die Sortiereinfolge der angezeigten Daten ändern.
	 * @param	Integer	Sortierreinfolge nach Spalten.
	 * @author Alpay Firato
	 */
	public void setOrder (Integer order){
		this.order=order;
		this.fireDataChanged();
	}
	
	
	/**
	 * Diese Methode ändert das Suchobjekt und somit die Suchkriterien.
	 * @param	Kontakt	Template Suchobjekt nach dem man eine Suche startet.
	 * @author	Alpay Firato
	 */
	public void setSuchKontakt (Kontakt suchKontakt){
		this.suchKontakt=suchKontakt;
	}
	
	/**
	 * Diese Methode ändert die Suchgruppe des zu suchenden Objektes.
	 * @param	String	Suchgruppe des Objekts.
	 * @author	Alpay Firato
	 */
	public void setGroup(String gruppe){
		this.group = gruppe;
	}
	
	public int getColumnCount() {
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
