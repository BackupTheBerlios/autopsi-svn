package autopsi.basis.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.table.Pruefung;
import autopsi.database.table.Lva;
import autopsi.javaspace.*;

public class PruefungTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8737097029189851737L;
	public List <GenericDataObject> pruefungen;
	public List <GenericDataObject> lva;
	public Pruefung suchPruefung = null;
	public IGenericDAO gdo = new GenericDAO();
	public IServiceCommunicator ogdo = null;
	public String tablename = "PRUEFUNG";
	public List <GenericDataObject> lastDeletedObjects =  new ArrayList<GenericDataObject>();
	
	public String lvaname = null;
	public String group = null;
	
	private final String [] columnName = {"LVA-Nr","Titel", "Prüfer", "Note"};
	
	public List<GenericDataObject> getPruefungen() {
		return this.pruefungen;
	}
	
	private void readData() {
		String query="select * from "+this.tablename+" as p, LVA as l, ATTACHABLE_OBJECT as a, ATTACHABLE_OBJECT_KATEGORIE as ok where p.GLOBAL_ID=a.GLOBAL_ID AND a.KATEGORIE_ID=ok.ID AND p.LVA_ID = l.GLOBAL_ID";
		try{
			if (suchPruefung!=null) {
				if (this.suchPruefung.getExaminer()!=null){
					query +=" AND LOWER(p.EXAMINER) LIKE '%"+this.suchPruefung.getExaminer().toLowerCase()+"%'";
				}
				if (this.suchPruefung.getGrade()!=null){
					query +=" AND p.GRADE = "+this.suchPruefung.getGrade()+"";
				}
				if (this.lvaname!=null){
					query +=" AND LOWER(l.TITLE) LIKE '%"+this.lvaname.toLowerCase()+"%'";
				}
				if (this.group != null){
					query += " AND ok.TITLE = '"+ this.group+"'";
				}


				System.out.println(query);
				this.pruefungen =  gdo.unsafeQuery(query, suchPruefung);
			}
		} catch (Exception e){
			System.out.println("PruefungTableModel @ readData;"+e.toString());
		}
	}
	
	public void readOnlineData () {
		Pruefung temp =(Pruefung) ogdo.getObject(this.suchPruefung);
		this.pruefungen.clear();
		this.pruefungen.add(temp);
	}
	
	public PruefungTableModel (){
		this.ogdo = new ServiceCommunicator();
		this.pruefungen = new ArrayList<GenericDataObject>();
	}
	
	public void deleteSelectedRow(JTable table) {
		Pruefung p = new Pruefung();
		boolean selected = false;
		boolean deleted = false;
		boolean first = true;
		int auswahl = 0;
		p = null;
		//	 Check each cell in the range
	    for (int r=0; r<this.getRowCount(); r++) {
            if (table.isCellSelected(r, 1)) {
            	selected = true;
            	p=(Pruefung) this.getPruefungen().get(r);
            	if (p.getGlobalId() != null) {
            		if (first) {
            			auswahl = JOptionPane.showConfirmDialog(null, "Sind sie sicher dass sie alle markierte Prüfungen löschen wollen?", "Löschen?",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            		}
	            	if (auswahl == JOptionPane.YES_OPTION) {
	            		//weiter mit löschen
	            		deleted = this.deletePruefung(p);
	            		if (deleted) {
	            			if (first){
	            				JOptionPane.showMessageDialog(null, "Die Prüfung wurde erfolgreich gelöscht." , "Gelöscht!" , JOptionPane.INFORMATION_MESSAGE);
	            			}
	            		} else {
	            			JOptionPane.showMessageDialog(null, "Die Prüfungen konnten nicht gelöscht werden." , "Löschen!" , JOptionPane.ERROR_MESSAGE);
	            		}
	            	}
            	} else {
            		JOptionPane.showMessageDialog(null, "Diese Prüfung kann nicht gelöscht werden." , "Null Object!" , JOptionPane.ERROR_MESSAGE);
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
	public boolean deletePruefung(Pruefung p){
		if (p == null)
			return false;
		
		try{
			IGenericDAO gdo = new GenericDAO();
			//System.out.println("SELECT * FROM " + this.tablename+" WHERE GLOBAL_ID ="+p.getGlobalId());
			List <GenericDataObject> loeschen = gdo.unsafeQuery("SELECT * FROM " + this.tablename+" WHERE GLOBAL_ID ="+p.getGlobalId(), new Pruefung());
				lastDeletedObjects.add(loeschen.get(0));
			loeschen = gdo.unsafeQuery("DELETE FROM " + this.tablename+" WHERE GLOBAL_ID ="+p.getGlobalId(), new Pruefung());
			return true;
		} catch (Exception e){
			System.out.println("PruefungTableModel @ deletePruefung;"+e.toString());
		}
		return true;
	}
	
	public void restoreLastDeletedObjects(){
		if (this.lastDeletedObjects.size() != 0) {
			for (int i=0;i<this.lastDeletedObjects.size();i++){
				addPruefung(this.lastDeletedObjects.get(i));
			}
			JOptionPane.showMessageDialog(null, "Die gelöschten Objekte wurden erfolgreich wiederhergestellt." , "Wiederherstellung Erfolgreich!" , JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Sie haben bisher keine Objekte gelöscht." , "Keine gelöschten Objekte" , JOptionPane.INFORMATION_MESSAGE);
		}
		this.lastDeletedObjects.clear();
		this.fireDataChanged();
	}
	
	public void addPruefung(GenericDataObject p){
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
	
	
	public void setSuchPruefung (Pruefung suchPruefung){
		this.suchPruefung=suchPruefung;
		//fireDataChanged();
	}
	
	public void setLvaName(String name){
		this.lvaname = name;
	}
	public void setGroup(String group){
		this.group = group;
	}
	public void fireDataChanged() {
		readData();
		fireTableDataChanged();
	}
	
	public void fireOnlineDataChanged() {
		System.out.println("Online Suche gestartet.");
		readOnlineData();
		System.out.println("Online Suche fertig.");
		System.out.println("Online Data: " + this.pruefungen.size());
		fireTableDataChanged();
	}
	
	public int getColumnCount() {
		//System.out.println("colCount = " + columnName.length);
		return columnName.length;
	}
	
	public int getRowCount() {
		//System.out.println("rowcount = " + Lesers.size());
		if (pruefungen != null) {
			return pruefungen.size();
		} else {
			return 0;
		}
	}
	public String getColumnName(int c) {
		//System.out.println("colName = " + columnName[c]);
		return columnName[c];
	}
	
	public Object getValueAt(int row, int col) {
		Pruefung p = null;
		p = (Pruefung) pruefungen.get(row);
		Lva l = null;
		try{
			IGenericDAO gdo = new GenericDAO();
			this.lva = gdo.unsafeQuery("SELECT * FROM LVA WHERE GLOBAL_ID ="+p.getLvaId(), new Lva());
			l = (Lva) lva.get(0);
		} catch (Exception e){
			System.out.println("PruefungTableModel @ getValueAt;"+e.toString());
		}
				
		
		
		if (p==null)
			return null;
		else if (col==0)
			return "";
		else if (col==1)
			return "";
		else if (col==2)
			return p.getExaminer();
		else if (col==3)
			return p.getGrade();
		else return null;
	}
}
