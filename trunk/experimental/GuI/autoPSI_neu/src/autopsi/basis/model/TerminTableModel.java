package autopsi.basis.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;

import autopsi.database.table.AttachableObject;
import autopsi.database.table.Termin;
import autopsi.javaspace.ServiceCommunicator;

public class TerminTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 8737097029189851737L;

	public List <GenericDataObject> objects = new ArrayList<GenericDataObject>();
	public List <GenericDataObject> uni = new ArrayList<GenericDataObject>();
	public List <GenericDataObject> lastDeletedObjects =  new ArrayList<GenericDataObject>();
	public GenericDAO gdo = null;
	public ServiceCommunicator ogdo = null;
	public boolean onlinesuche = false;
	public String tablename = "Termin";
	public Termin searchObject = null;
	public String group = null;
	public String type = null;
	public String datum = null;
	public Integer order = null;
	
	private final String [] columnName = {"Titel", "Beschreibung", "Datum", "Dauer"};
	private final String [] columnDBName = {"t.SECONDARY_TITLE", "T.DESCRIPTION", "t.DATE", "t.DURATION"};
	
	/**
	 * Diese Methode liefert alle Objekte als eine Liste von GenericDataObjects.
	 * @return	List<GenericDataObject>	Liste der Objekte.
	 * @author	Alpay Firato
	 */
	public List<GenericDataObject> getObjects() {
		return this.objects;
	}
	
	/**
	 * Diese Methode liefert den angezeigten GenericDataObject an der angegebenen Zeile.
	 * @param	int	Zellennummer an der das Objekt angezeigt wird.
	 * @return	GenericDataObjekt	Objekt als GenericDataObject.
	 * @author	Alpay Firato
	 */
	public GenericDataObject getObjectAt(int at) {
		if (objects != null) {
			if (at < objects.size())
				return this.objects.get(at);
		}
		return null;
	}
	
	/**
	 * Diese Methode liest die Daten von der lokalen Datenbank mit Hilfe des Generic-DAO aus.
	 * Danach werden alle Objekte, die aus der Datenbank ausgelwsen worden sind, in eine Liste gespeichert.
	 * Die Datensätze werden nach order geordnet.
	 * @author	Alpay Firato
	 */
	private void readData() {
		String query="select * from TERMIN as t, ATTACHABLE_OBJECT_KATEGORIE as ok, TERMIN_KATEGORIE as kat where t.TERMIN_KATEGORIE_ID = kat.id AND t.GROUP_ID = ok.id";
		try{
			GenericDAO gdo = new GenericDAO();
			if (searchObject!=null) {
				if (searchObject.getSecondaryTitle()!=null) {
					query += " AND LOWER(t.SECONDARY_TITLE) LIKE '%" + searchObject.getSecondaryTitle().toLowerCase()+"%'";
				}
				if (searchObject.getDescription()!=null) {
					query += " AND LOWER(t.DESCRIPTION) LIKE '%" + searchObject.getDescription().toLowerCase()+"%'";
				}
				if (this.datum!=null){
					query += " AND t.DATE LIKE '%" + this.datum +"%'";
				}
				if (this.type!=null && !this.type.equals("-")){
					query +=" AND kat.NAME = '"+this.type+"'";
				}
				if (this.group != null && !this.group.equals("-")){
					query += " AND ok.TITLE = '"+ this.group+"'";
				}				
				if (this.order!=null) {
					query += " ORDER BY "+columnDBName[this.order];
				}
				//System.out.println(query);
				this.objects =  gdo.unsafeQuery(query, searchObject);
			}
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: " +e.toString(), "Error!" , JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Diese Methode liest die Daten aus dem Javaspace mit Hilfe des ServiceCommunicators aus.
	 * Das, aus dem Javaspace, rausgelesene Objekt wird in eine Liste abgespeichert.
	 * @author	Alpay Firato
	 */	
	public void readOnlineData(){
		try {
			this.onlinesuche = true;
			Termin temp = (Termin)this.ogdo.getObject(this.searchObject);
			this.objects = new ArrayList<GenericDataObject>();
			this.objects.add(temp);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " +e.toString(), "Error!" , JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Im Konstruktor wird Generic-DAO und Javaspace-ServiceCommunicator angelegt.
	 * @author	Alpay Firato
	 */
	public TerminTableModel (){
		this.gdo = new GenericDAO();
		this.ogdo = new ServiceCommunicator();
	}
	
	/**
	 * Diese Methode wird immer bei einer Änderung der lokalen Daten aufgerufen.
	 * @author	Alpay Firato
	 */	
	public void fireDataChanged() {
		this.onlinesuche = false;
		this.readData();
		this.fireTableDataChanged();
	}
	
	/**
	 * Diese Methode wird immer bei einer Aenderung der online Daten aufgerufen.
	 * @author	Alpay Firato
	 */	
	public void fireOnlineDataChanged(){
		this.onlinesuche = true;
		this.readOnlineData();
		this.fireTableDataChanged();
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
		Termin p = new Termin();
		boolean selected = false;
		boolean deleted = false;
		boolean first = true;
		int auswahl = 0;
		p = null;
		//	 Check each cell in the range
		if (this.onlinesuche == false) {
		    for (int r=0; r<this.getRowCount(); r++) {
	            if (table.isCellSelected(r, 1)) {
	            	selected = true;
	            	p=(Termin) this.getObjects().get(r);
	            	if (p.getId() != null ) {
	            		if (first) {
	            			auswahl = JOptionPane.showConfirmDialog(null, "Sind sie sicher dass sie alle markierten Objekte löschen wollen?", "Löschen?",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
	            		}
		            	if (auswahl == JOptionPane.YES_OPTION) {
		            		//weiter mit löschen
		            		deleted = this.deleteTermin(p);
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
		} else {
			JOptionPane.showMessageDialog(null, "Online Objekte können nicht gelöscht werden." , "Online Object!" , JOptionPane.ERROR_MESSAGE);
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
	 * @param	Termin	Das ist der Termin der aus dem Datenbank entfernt werden soll.
	 * @return	boolean	Falls das Objekt erfolgreich gelöscht wurde dann wird true zurückgeliefert,
	 * sonnst false.
	 * @author	Alpay Firato
	 */
	public boolean deleteTermin(Termin Object){
		if (Object == null)
			return false;
		
		try{
			GenericDAO gdo = new GenericDAO();
			List <GenericDataObject> loeschen = gdo.unsafeQuery("SELECT * FROM " + this.tablename+" WHERE ID ="+Object.getId(), new Termin());
			lastDeletedObjects.add(loeschen.get(0));
			loeschen = gdo.unsafeQuery("DELETE FROM " + this.tablename+" WHERE ID ="+Object.getId(), new Termin());
			return true;
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: " +e.toString(), "Error!" , JOptionPane.ERROR_MESSAGE);
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
				addTermin(this.lastDeletedObjects.get(i));
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
		if (this.onlinesuche==true && this.objects.size() != 0) {
			for (int i=0;i<this.objects.size();i++){
				addTermin(this.objects.get(i));
			}
			JOptionPane.showMessageDialog(null, "Das ausgewählte Objekt wurde heruntergeladen." , "Download abgeschlossen." , JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Sie müssen zuerst ein Objekt auswählen." , "Kein Objekt zum herunterladen ausgewählt." , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Diese Methode kann das übergebene Objekt in die lokale Datenbank einfügen.
	 * @param	GenericDataObject	Das Object, das in die Datenbank eingefügt werden soll.
	 * @author	Alpay Firato
	 */
	public void addTermin(GenericDataObject Object){
		try{
			if (Object!=null){
				gdo.setCurrentTable("attachable_object");
				AttachableObject a = new AttachableObject();
				a.setTableName(this.tablename.toLowerCase());
				a.setKategorieId(0);
				gdo.addDataObject(a);
				a = (AttachableObject)gdo.unsafeQuery("select * from attachable_object where global_id=identity()", new AttachableObject()).get(0);
				Termin l = (Termin) Object;
				l.setId(a.getId());
				l.setGroupID(0);
				gdo.setCurrentTable(this.tablename);
				gdo.addDataObject(l);	
			} else {
				JOptionPane.showMessageDialog(null, "Error: NULL OBJECT kann nicht in die Datenbank eingefügt werden", "Error!" , JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: " +e.toString(), "Error!" , JOptionPane.ERROR_MESSAGE);
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
	 * @param	Lva	Template Suchobjekt nach dem man eine Suche startet.
	 * @author	Alpay Firato
	 */
	public void setSearchObject (Termin Object){
		this.searchObject=Object;
	}
	
	/**
	 * Diese Methode ändert das Suchdatum des zu suchenden Objektes.
	 * @param	String	Suchdatum des Objekts.
	 * @author	Alpay Firato
	 */
	public void setDatum (String datum){
		this.datum=datum;
	}
	
	/**
	 * Diese Methode ändert die Suchgruppe des zu suchenden Objektes.
	 * @param	String	Suchgruppe des Objekts.
	 * @author	Alpay Firato
	 */
	public void setGroup(String gruppe){
		this.group = gruppe;
	}
	
	/**
	 * Diese Methode ändert die Kategorie/Type des zu suchenden Objektes.
	 * @param	String	Kategorie des Objekts.
	 * @author	Alpay Firato
	 */
	public void setType(String type){
		this.type = type;
	}
	
	/**
	 * Diese Methode liefert die Anzahl der Spalten als int-Wert zurück.
	 * @return	int	Anzahl der Spalten.
	 * @author	Alpay Firato
	 */
	public int getColumnCount() {
		return columnName.length;
	}
	
	/**
	 * Diese Methode liefert die Anzahl der Zeilen als int-Wert zurück.
	 * @return	int	Anzahl der Zeilen.
	 * @author	Alpay Firato
	 */
	public int getRowCount() {
		if (objects != null) {
			return objects.size();
		} else {
			return 0;
		}
	}
	
	/**
	 * Diese Methode liefert den Header der Spalte als String-Wert zurück.
	 * @param	int	Spaltennummer.
	 * @return	String	Name der Spalte.
	 * @author	Alpay Firato
	 */
	public String getColumnName(int c) {
		return columnName[c];
	}
	
	/**
	 * Diese Methode liefert den Inhalt einer Zelle zurück.
	 * @param	int	Reihe der Zelle.
	 * @param	int	Spalte der Zelle.
	 * @return	Object	Wert der Zelle.
	 * @author	Alpay Firato
	 */
	public Object getValueAt(int row, int col) {
		Termin ter = (Termin) objects.get(row);
		
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