package autopsi.basis.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.javaspace.ServiceCommunicator;

import autopsi.database.table.AttachableObject;
import autopsi.database.table.Lva;

public class LVATableModel extends AbstractTableModel {
	private static final long serialVersionUID = 32424324234L;

	public List <GenericDataObject> objects = new ArrayList<GenericDataObject>();
	public List <GenericDataObject> uni = new ArrayList<GenericDataObject>();
	public List <GenericDataObject> lastDeletedObjects =  new ArrayList<GenericDataObject>();
	public GenericDAO gdo = null;
	public ServiceCommunicator ogdo = null;
	public boolean onlinesuche = false;
	public String tablename = "LVA";
	public Lva searchObject = null;
	public String group = null;
	public String type = null;
	public Integer order = null;

	private final String [] columnName = {"LVA-Nr","Titel", "Beschreibung"};
	private final String [] columnDBName = {"l.LVA_NR", "l.TITLE", "l.DESCRIPTION"};
	
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
	 * Die Datens�tze werden nach order geordnet.
	 * @author	Alpay Firato
	 */
	private void readData() {
		String query="select * from LVA as l, LVA_KATEGORIE as kat, ATTACHABLE_OBJECT as a, ATTACHABLE_OBJECT_KATEGORIE as ok where l.GLOBAL_ID=a.GLOBAL_ID AND a.KATEGORIE_ID=ok.ID AND l.TYPE = kat.id";
		try{
			GenericDAO gdo = new GenericDAO();
			if (searchObject!=null) {
				if (this.searchObject.getLvaNr()!=null){
					query +=" AND LVA_NR ='"+this.searchObject.getLvaNr()+"'";
				}
				if (this.searchObject.getTitle()!=null){
					query +=" AND Lower(TITLE) Like '%"+this.searchObject.getTitle().toLowerCase()+"%'";
				}
				if (this.searchObject.getDescription()!=null){
					query +=" AND Lower(DESCRIPTION) Like '%"+this.searchObject.getDescription().toLowerCase()+"%'";
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
			Lva temp = (Lva)this.ogdo.getObject(this.searchObject);
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
	public LVATableModel (){
		this.gdo = new GenericDAO();
		this.ogdo = new ServiceCommunicator();
	}
	
	/**
	 * Diese Methode wird immer bei einer �nderung der lokalen Daten aufgerufen.
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
	 * Diese Methode l�scht die markierten Objekte aus der lokalen Datenbank.
	 * Nach einem erfolgreichen L�schvorgang wird fireDataChanged aufgerufen.
	 * Alle gel�schten Objekte wird als eine Liste abgespeichert damit man sp�ter
	 * diese Daten wiederherstellen kann wenn erfordert.
	 * @param	JTable	Das ist die Tabelle bei der die Daten markiert worden sind.
	 * @author	Alpay Firato
	 */
	public void deleteSelectedRow(JTable table) {
		Lva p = new Lva();
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
	            	p=(Lva) this.getObjects().get(r);
	            	if (p.getGlobalId() != null ) {
	            		if (first) {
	            			auswahl = JOptionPane.showConfirmDialog(null, "Sind sie sicher dass sie alle markierten Objekte l�schen wollen?", "L�schen?",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
	            		}
		            	if (auswahl == JOptionPane.YES_OPTION) {
		            		//weiter mit l�schen
		            		deleted = this.deleteLva(p);
		            		if (deleted) {
		            			if (first){
		            				JOptionPane.showMessageDialog(null, "Das L�schvorgang war erfolgreich." , "Gel�scht!" , JOptionPane.INFORMATION_MESSAGE);
		            			}
		            		} else {
		            			JOptionPane.showMessageDialog(null, "Das Objekt konnte nicht gel�scht werden." , "L�schen!" , JOptionPane.ERROR_MESSAGE);
		            		}
		            	}
	            	} else {
	            		JOptionPane.showMessageDialog(null, "Dieses Objekt kann nicht gel�scht werden." , "Null Object!" , JOptionPane.ERROR_MESSAGE);
	            	}
	            	first = false;
	            }    
		    }
		} else {
			JOptionPane.showMessageDialog(null, "Online Objekte k�nnen nicht gel�scht werden." , "Online Object!" , JOptionPane.ERROR_MESSAGE);
		}
	    
	    if (selected == false) {
	    	JOptionPane.showMessageDialog(null, "Bitte selektieren Sie mindestens eine Reihe in der Tabelle", "Data wurde nicht ausgew�hlt!" , JOptionPane.ERROR_MESSAGE);
	    }
	    if (deleted) {
	    	this.fireDataChanged();
	    }
	}
	
	/**
	 * Diese Methode l�scht das �bergebene Objekt aus der lokalen Datenbank.
	 * @param	Lva	Das ist die Lva die aus dem Datenbank entfernt werden soll.
	 * @return	boolean	Falls das Objekt erfolgreich gel�scht wurde dann wird true zur�ckgeliefert,
	 * sonnst false.
	 * @author	Alpay Firato
	 */
	public boolean deleteLva(Lva Object){
		if (Object == null)
			return false;
		
		try{
			GenericDAO gdo = new GenericDAO();
			List <GenericDataObject> loeschen = gdo.unsafeQuery("SELECT * FROM " + this.tablename+" WHERE GLOBAL_ID ="+Object.getGlobalId(), new Lva());
			lastDeletedObjects.add(loeschen.get(0));
			loeschen = gdo.unsafeQuery("DELETE FROM " + this.tablename+" WHERE GLOBAL_ID ="+Object.getGlobalId(), new Lva());
			return true;
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: " +e.toString(), "Error!" , JOptionPane.ERROR_MESSAGE);
		}
		return true;
	}
	
	/**
	 * Diese Methode kann alle gel�schten Objekte wiederherstellen und in die Datenbank einf�gen.
	 * @author	Alpay Firato
	 */
	public void restoreLastDeletedObjects(){
		if (this.lastDeletedObjects.size() != 0) {
			for (int i=0;i<this.lastDeletedObjects.size();i++){
				addLVA(this.lastDeletedObjects.get(i));
			}
			JOptionPane.showMessageDialog(null, "Die gel�schten Objekte wurden erfolgreich wiederhergestellt." , "Wiederherstellung Erfolgreich!" , JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Sie haben bisher keine Objekte gel�scht." , "Keine gel�schten Objekte" , JOptionPane.INFORMATION_MESSAGE);
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
				Lva temp = (Lva)this.objects.get(i);
				temp.setType(0);
				temp.setUniId(0);
				addLVA(temp);
			}
			JOptionPane.showMessageDialog(null, "Das ausgew�hlte Objekt wurde heruntergeladen." , "Download abgeschlossen." , JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Sie m�ssen zuerst ein Objekt ausw�hlen." , "Kein Objekt zum herunterladen ausgew�hlt." , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Diese Methode kann das �bergebene Objekt in die lokale Datenbank einf�gen.
	 * @param	GenericDataObject	Das Object, das in die Datenbank eingef�gt werden soll.
	 * @author	Alpay Firato
	 */
	public void addLVA(GenericDataObject Object){
		try{
			if (Object!=null){
				gdo.setCurrentTable("attachable_object");
				AttachableObject a = new AttachableObject();
				a.setTableName(this.tablename.toLowerCase());
				a.setKategorieId(0);
				gdo.addDataObject(a);
				a = (AttachableObject)gdo.unsafeQuery("select * from attachable_object where global_id=identity()", new AttachableObject()).get(0);
				Lva l = (Lva) Object;
				l.setGlobalId(a.getId());
				l.setUniId(0);
				gdo.setCurrentTable(this.tablename);
				gdo.addDataObject(l);	
			} else {
				JOptionPane.showMessageDialog(null, "Error: NULL OBJECT kann nicht in die Datenbank eingef�gt werden", "Error!" , JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: " +e.toString(), "Error!" , JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Diese Methode kann die Sortiereinfolge der angezeigten Daten �ndern.
	 * @param	Integer	Sortierreinfolge nach Spalten.
	 * @author Alpay Firato
	 */
	public void setOrder (Integer order){
		this.order=order;
		this.fireDataChanged();
	}
	
	/**
	 * Diese Methode �ndert das Suchobjekt und somit die Suchkriterien.
	 * @param	Lva	Template Suchobjekt nach dem man eine Suche startet.
	 * @author	Alpay Firato
	 */
	public void setSearchObject (Lva Object){
		this.searchObject=Object;
	}
	
	/**
	 * Diese Methode �ndert die Suchgruppe des zu suchenden Objektes.
	 * @param	String	Suchgruppe des Objekts.
	 * @author	Alpay Firato
	 */
	public void setGroup(String gruppe){
		this.group = gruppe;
	}
	
	/**
	 * Diese Methode �ndert die Kategorie/Type des zu suchenden Objektes.
	 * @param	String	Kategorie des Objekts.
	 * @author	Alpay Firato
	 */
	public void setType(String type){
		this.type = type;
	}
	
	/**
	 * Diese Methode liefert die Anzahl der Spalten als int-Wert zur�ck.
	 * @return	int	Anzahl der Spalten.
	 * @author	Alpay Firato
	 */
	public int getColumnCount() {
		return columnName.length;
	}
	
	/**
	 * Diese Methode liefert die Anzahl der Zeilen als int-Wert zur�ck.
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
	 * Diese Methode liefert den Header der Spalte als String-Wert zur�ck.
	 * @param	int	Spaltennummer.
	 * @return	String	Name der Spalte.
	 * @author	Alpay Firato
	 */
	public String getColumnName(int c) {
		return columnName[c];
	}
	
	/**
	 * Diese Methode liefert den Inhalt einer Zelle zur�ck.
	 * @param	int	Reihe der Zelle.
	 * @param	int	Spalte der Zelle.
	 * @return	Object	Wert der Zelle.
	 * @author	Alpay Firato
	 */
	public Object getValueAt(int row, int col) {
		Lva lva = (Lva) objects.get(row);
		if (lva==null)
			return null;
		else if (col==0)
			return lva.getLvaNr();
		else if (col==1)
			return lva.getTitle();
		else if (col==2)
			return lva.getDescription();
		else return null;
	}
}
