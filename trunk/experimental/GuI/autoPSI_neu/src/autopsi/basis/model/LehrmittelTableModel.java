package autopsi.basis.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.table.Lehrmittel;
import autopsi.javaspace.IServiceCommunicator;
import autopsi.javaspace.ServiceCommunicator;

public class LehrmittelTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8737097029189851737L;
	public List <GenericDataObject> lehrmittel = new ArrayList<GenericDataObject>();
	public List <GenericDataObject> lastDeletedObjects =  new ArrayList<GenericDataObject>();
	public IGenericDAO gdo;
	public ServiceCommunicator ogdo = null;
	public boolean onlinesuche = false;
	public String tablename = "LEHRMITTEL";
	public Lehrmittel suchLm = null;
	public String group = null;
	public String type = null;


	
	
	private final String [] columnName = {"Name", "Beschreibung", "File Link"};
	
	public List<GenericDataObject> getLehrmittel() {
		return this.lehrmittel;
	}
	
	private void readData() {
		String query="select * from Lehrmittel as l, Lehrmittel_Kategorie as kat, ATTACHABLE_OBJECT as a, ATTACHABLE_OBJECT_KATEGORIE as ok where l.GLOBAL_ID=a.GLOBAL_ID AND a.KATEGORIE_ID=ok.ID AND l.LEHRMITTEL_KATEGORIE_ID=kat.ID";
		try{
			IGenericDAO gdo = new GenericDAO();
			if (suchLm!=null) {
				if (this.suchLm.getName()!=null){
					query +=" AND LOWER(l.NAME) Like '%"+this.suchLm.getName().toLowerCase()+"%'";
				}
				if (this.suchLm.getDescription()!=null){
					query +=" AND LOWER(l.NAME) LIKE '%"+this.suchLm.getDescription().toLowerCase()+"%'";
				}
				if (this.type!=null && !this.group.equals("-")){
					query +=" AND kat.TITLE = '"+this.type+"'";
				}
				if (this.group != null && !this.group.equals("-")){
					query += " AND ok.TITLE = '"+ this.group+"'";
				}
				//System.out.println(query);
				this.lehrmittel =  gdo.unsafeQuery(query, suchLm);
			}
		} catch (Exception e){
			System.out.println("AAARGH;"+e.toString());
		}
	}

	public void readOnlineData(){
		this.onlinesuche = true;
		Lehrmittel temp = (Lehrmittel)this.ogdo.getObject(this.suchLm);
		this.lehrmittel = new ArrayList<GenericDataObject>();
		if (temp != null)
			this.lehrmittel.add(temp);
	}
	
	public LehrmittelTableModel (){
		this.gdo = new GenericDAO();
		this.ogdo = new ServiceCommunicator();
	}
	
	public void fireDataChanged() {
		this.onlinesuche = false;
		readData();
		fireTableDataChanged();
	}
	
	public void fireOnlineDataChanged() {
		this.onlinesuche = true;
		readOnlineData();
		fireTableDataChanged();
	}
	
	public void deleteSelectedRow(JTable table) {
		Lehrmittel l = new Lehrmittel();
		boolean selected = false;
		boolean deleted = false;
		boolean first = true;
		int auswahl = 0;
		l = null;
		//	 Check each cell in the range
	    for (int r=0; r<this.getRowCount(); r++) {
            if (table.isCellSelected(r, 1)) {
            	selected = true;
            	l=(Lehrmittel) this.getLehrmittel().get(r);
            	if (l.getGlobalId() != null) {
            		if (first) {
            			auswahl = JOptionPane.showConfirmDialog(null, "Sind sie sicher dass sie alle markierten Objekte löschen wollen?", "Löschen?",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            		}
	            	if (auswahl == JOptionPane.YES_OPTION) {
	            		//weiter mit löschen
	            		deleted = this.deleteLehrmittel(l);
	            		if (deleted) {
	            			if (first){
	            				JOptionPane.showMessageDialog(null, "Das Löschvorgang war erfolgreich." , "Gelöscht!" , JOptionPane.INFORMATION_MESSAGE);
	            			}
	            		} else {
	            			JOptionPane.showMessageDialog(null, "Das Objekt konnte nicht gelöscht werden." , "Löschen!" , JOptionPane.ERROR_MESSAGE);
	            		}
	            	}
            	} else {
            		JOptionPane.showMessageDialog(null, "Diese Prüfung kann nicht gelöscht werden." , "Null Object!" , JOptionPane.ERROR_MESSAGE);
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
	 */
	public boolean deleteLehrmittel(Lehrmittel l){
		if (l == null)
			return false;
		
		try{
			IGenericDAO gdo = new GenericDAO();
			//System.out.println("SELECT * FROM " + this.tablename+" WHERE GLOBAL_ID ="+p.getGlobalId());
			List <GenericDataObject> loeschen = gdo.unsafeQuery("SELECT * FROM " + this.tablename+" WHERE GLOBAL_ID ="+l.getGlobalId(), new Lehrmittel());
				lastDeletedObjects.add(loeschen.get(0));
			loeschen = gdo.unsafeQuery("DELETE FROM " + this.tablename+" WHERE GLOBAL_ID ="+l.getGlobalId(), new Lehrmittel());
			return true;
		} catch (Exception e){
			System.out.println("LehrmittelTableModel @ deletePruefung;"+e.toString());
		}
		return true;
	}
	
	public void restoreLastDeletedObjects(){
		if (this.lastDeletedObjects.size() != 0) {
			for (int i=0;i<this.lastDeletedObjects.size();i++){
				addLehrmittel(this.lastDeletedObjects.get(i));
			}
			JOptionPane.showMessageDialog(null, "Die gelöschten Objekte wurden erfolgreich wiederhergestellt." , "Wiederherstellung Erfolgreich!" , JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Sie haben bisher keine Objekte gelöscht." , "Keine gelöschten Objekte" , JOptionPane.INFORMATION_MESSAGE);
		}
		this.lastDeletedObjects.clear();
		this.fireDataChanged();
	}
	
	public void downloadObject(){
		if (this.onlinesuche==true && this.lehrmittel.size() != 0) {
			for (int i=0;i<this.lehrmittel.size();i++){
				addLehrmittel(this.lehrmittel.get(i));
			}
			JOptionPane.showMessageDialog(null, "Das Objekt wurde heruntergeladen." , "Download abgeschlossen." , JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Sie müssen zuerst ein Objekt auswählen." , "Kein Objekt zum herunterladen ausgewählt." , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void addLehrmittel(GenericDataObject l){
		try{
			if (l!=null){
				Lehrmittel lm = (Lehrmittel) l;
				lm.setLehrmittelKategorieId(0);
				gdo.setCurrentTable(this.tablename);
				gdo.addDataObject(lm);
			} else {
				System.out.println("NULLLL ELEMENTTTT!!!! :(((((((");
			}
		} catch (Exception e){
			System.out.println("LehrmittelTableModel @ addPruefung;"+e.toString());
		}
	}
	
	public void setSuchLehrmittel (Lehrmittel suchLm){
		this.suchLm=suchLm;
		fireDataChanged();
	}
	
	public void setGroup(String gruppe){
		this.group = gruppe;
	}
	public void setType(String type){
		this.type = type;
	}
	
	public int getColumnCount() {
		return columnName.length;
	}
	
	public int getRowCount() {
		if (lehrmittel != null) {
			return lehrmittel.size();
		} else {
			return 0;
		}
	}
	public String getColumnName(int c) {
		return columnName[c];
	}
	
	public Object getValueAt(int row, int col) {
		Lehrmittel lm = (Lehrmittel) lehrmittel.get(row);
		if (lm==null)
			return null;
		else if (col==0)
			return lm.getName();
		else if (col==1)
			return lm.getDescription();
		else if (col==2)
			return lm.getFileLink();
		else return null;
	}
}

