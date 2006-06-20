package autopsi.basis.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.table.Lva;
import autopsi.database.table.Universitaet;

public class LVATableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8737097029189851737L;
	public List <GenericDataObject> lvas;
	public List <GenericDataObject> uni;
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
				if (this.type!=null){
					query +=" AND kat.NAME = '"+this.type+"'";
				}
				System.out.println(query);
				this.lvas =  gdo.unsafeQuery(query, suchLva);
			}
		} catch (Exception e){
			System.out.println("AAARGH;"+e.toString());
		}
	}
	
	public LVATableModel (){
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
		if (lvas != null) {
			return lvas.size();
		} else {
			return 0;
		}
	}
	public String getColumnName(int c) {
		//System.out.println("colName = " + columnName[c]);
		return columnName[c];
	}
	
	public Object getValueAt(int row, int col) {
		Lva lva = (Lva) lvas.get(row);
		IGenericDAO gdo = new GenericDAO();
		this.uni =  gdo.unsafeQuery("selet * FROM UNIVERSITAET WHERE ID="+lva.getGlobalId(), new Universitaet());
		Universitaet u = (Universitaet) uni;
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
