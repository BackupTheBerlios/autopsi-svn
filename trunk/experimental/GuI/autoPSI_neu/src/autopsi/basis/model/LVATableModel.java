package autopsi.basis.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.table.AttachableObject;
import autopsi.database.table.Lva;
import autopsi.database.table.Universitaet;
import autopsi.javaspace.ServiceCommunicator;

public class LVATableModel extends AbstractTableModel {

	public List <GenericDataObject> lvas = new ArrayList<GenericDataObject>();
	public List <GenericDataObject> uni = new ArrayList<GenericDataObject>();
	public List <GenericDataObject> lastDeletedObjects =  new ArrayList<GenericDataObject>();
	public GenericDAO gdo;
	public ServiceCommunicator ogdo = null;
	public boolean onlinesuche = false;
	public String tablename = "LVA";
	public Lva suchLva = null;
	public String group = null;
	public String type = null;
	
	
	private final String [] columnName = {"LVA-Nr","Titel", "Beschreibung", "UNI"};
	
	public List<GenericDataObject> getLvas() {
		return this.lvas;
	}
	
	private void readData() {
		String query="select * from LVA as l, LVA_KATEGORIE as kat, ATTACHABLE_OBJECT as a, ATTACHABLE_OBJECT_KATEGORIE as ok where l.GLOBAL_ID=a.GLOBAL_ID AND a.KATEGORIE_ID=ok.ID AND l.TYPE = kat.id";
		try{
			IGenericDAO gdo = new GenericDAO();
			if (suchLva!=null) {
				if (this.suchLva.getLvaNr()!=null){
					query +=" AND LVA_NR ="+this.suchLva.getLvaNr()+"";
				}
				if (this.suchLva.getTitle()!=null){
					query +=" AND Lower(TITLE) Like '%"+this.suchLva.getTitle().toLowerCase()+"%'";
				}
				if (this.suchLva.getDescription()!=null){
					query +=" AND Lower(DESCRIPTION) Like '%"+this.suchLva.getDescription().toLowerCase()+"%'";
				}
				if (this.type!=null && !this.type.equals("-")){
					query +=" AND kat.NAME = '"+this.type+"'";
				}
				if (this.group != null && !this.group.equals("-")){
					query += " AND ok.TITLE = '"+ this.group+"'";
				}
				System.out.println(query);
				this.lvas =  gdo.unsafeQuery(query, suchLva);
			}
		} catch (Exception e){
			System.out.println("LVATableModel @ readData;"+e.toString());
		}
	}
	
	
	public void readOnlineData(){
		try {
			this.onlinesuche = true;
			Lva temp = (Lva)this.ogdo.getObject(this.suchLva);
			this.lvas = new ArrayList<GenericDataObject>();
			this.lvas.add(temp);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fehler: " +e.toString(), "Ein Fehler ist aufgetreten!" , JOptionPane.ERROR_MESSAGE);
		}
	}

	
	public LVATableModel (){
		this.gdo = new GenericDAO();
		this.ogdo = new ServiceCommunicator();
	}
	
	
	public void fireDataChanged() {
		this.onlinesuche = false;
		readData();
		this.fireTableDataChanged();
	}
	
	public void fireOnlineDataChanged(){
		this.onlinesuche = true;
		readOnlineData();
		fireTableDataChanged();
	}
	

	
	public void deleteSelectedRow(JTable table) {
		Lva p = new Lva();
		boolean selected = false;
		boolean deleted = false;
		boolean first = true;
		int auswahl = 0;
		p = null;
		//	 Check each cell in the range
	    for (int r=0; r<this.getRowCount(); r++) {
            if (table.isCellSelected(r, 1)) {
            	selected = true;
            	p=(Lva) this.getLvas().get(r);
            	if (p.getGlobalId() != null ) {
            		if (first) {
            			auswahl = JOptionPane.showConfirmDialog(null, "Sind sie sicher dass sie alle markierten Objekte löschen wollen?", "Löschen?",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            		}
	            	if (auswahl == JOptionPane.YES_OPTION) {
	            		//weiter mit löschen
	            		deleted = this.deleteLva(p);
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
	 */
	public boolean deleteLva(Lva p){
		if (p == null)
			return false;
		
		try{
			IGenericDAO gdo = new GenericDAO();
			//System.out.println("SELECT * FROM " + this.tablename+" WHERE GLOBAL_ID ="+p.getGlobalId());
			List <GenericDataObject> loeschen = gdo.unsafeQuery("SELECT * FROM " + this.tablename+" WHERE GLOBAL_ID ="+p.getGlobalId(), new Lva());
				lastDeletedObjects.add(loeschen.get(0));
			loeschen = gdo.unsafeQuery("DELETE FROM " + this.tablename+" WHERE GLOBAL_ID ="+p.getGlobalId(), new Lva());
			return true;
		} catch (Exception e){
			System.out.println("PruefungTableModel @ deletePruefung;"+e.toString());
		}
		return true;
	}
	
	public void restoreLastDeletedObjects(){
		if (this.lastDeletedObjects.size() != 0) {
			for (int i=0;i<this.lastDeletedObjects.size();i++){
				addLVA(this.lastDeletedObjects.get(i));
			}
			JOptionPane.showMessageDialog(null, "Die gelöschten Objekte wurden erfolgreich wiederhergestellt." , "Wiederherstellung Erfolgreich!" , JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Sie haben bisher keine Objekte gelöscht." , "Keine gelöschten Objekte" , JOptionPane.INFORMATION_MESSAGE);
		}
		this.lastDeletedObjects.clear();
		this.fireDataChanged();
	}
	
	public void downloadObject(){
		if (this.onlinesuche==true && this.lvas.size() != 0) {
			for (int i=0;i<this.lvas.size();i++){
				addLVA(this.lvas.get(i));
			}
			JOptionPane.showMessageDialog(null, "Das ausgewählte Objekt wurde heruntergeladen." , "Download abgeschlossen." , JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Sie müssen zuerst ein Objekt auswählen." , "Kein Objekt zum herunterladen ausgewählt." , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void addLVA(GenericDataObject p){
		try{
			if (p!=null){
				gdo.setCurrentTable("attachable_object");
				AttachableObject a = new AttachableObject();
				a.setTableName(this.tablename.toLowerCase());
				a.setKategorieId(0);
				gdo.addDataObject(a);
				a = (AttachableObject)gdo.unsafeQuery("select * from attachable_object where global_id=identity()", new AttachableObject()).get(0);
				Lva l = (Lva) p;
				l.setGlobalId(a.getId());
				l.setUniId(0);
				gdo.setCurrentTable(this.tablename);
				gdo.addDataObject(l);
				
				
			} else {
				System.out.println("NULLLL ELEMENTTTT!!!! :(((((((");
			}
		} catch (Exception e){
			System.out.println("PruefungTableModel @ addPruefung;"+e.toString());
		}
	}
	
	public void setSuchLVa (Lva suchLva){
		this.suchLva=suchLva;
		fireDataChanged();
	}
	
	public void setGroup(String gruppe){
		this.group = gruppe;
	}
	public void setType(String type){
		this.type = type;
	}
	
	public int getColumnCount() {
		//System.out.println("colCount = " + columnName.length);
		return columnName.length;
	}
	
	public int getRowCount() {
		if (lvas != null) {
			System.out.println("rowcount = " + lvas.size());
		} else {
			System.out.println("rowcount = 0");
		}
		
		if (lvas != null) {
			return lvas.size();
		} else {
			return 0;
		}
	}
	public String getColumnName(int c) {
		System.out.println("colName = " + columnName[c]);
		return columnName[c];
	}
	
	public Object getValueAt(int row, int col) {
		System.out.println("getValueAt: r="+row+", c="+col);
		Universitaet u = new Universitaet();
		Lva lva = (Lva) lvas.get(row);
		try {
			IGenericDAO gdo = new GenericDAO();
			this.uni =  gdo.unsafeQuery("select * FROM UNIVERSITAET WHERE ID="+lva.getGlobalId(), new Universitaet());
			
		} catch (Exception e){
			System.out.println("LVATableModel @ getValueAt;"+e.toString());
		}
		if (! this.uni.isEmpty()) {
			u = (Universitaet) this.uni.get(0);
		} else {
			u.setName("-");
		}
		if (lva==null)
			return null;
		else if (col==0)
			return lva.getLvaNr();
		else if (col==1)
			return lva.getTitle();
		else if (col==2)
			return lva.getDescription();
		else if (col==3)
			return u.getName();
		else return null;
	}
}
