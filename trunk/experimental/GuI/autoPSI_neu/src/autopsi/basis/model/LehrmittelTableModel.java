package autopsi.basis.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.table.Lehrmittel;
import autopsi.database.table.Lva;

public class LehrmittelTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8737097029189851737L;
	public List <GenericDataObject> lvas;
	public Lehrmittel suchLm = null;
	public String group = null;
	
	private final String [] columnName = {"Name", "Beschreibung"};
	
	public List<GenericDataObject> getLvas() {
		return this.lvas;
	}
	
	private void readData() {
		String query="select * from Lehrmittel as l, Lehrmittel_Kategorie as kat, ATTACHABLE_OBJECT as a, ATTACHABLE_OBJECT_KATEGORIE as ok where l.GLOBAL_ID=a.GLOBAL_ID AND a.GLOBAL_ID=ok.ID AND l.KATEGORIE_ID=kat.ID";
		try{
			IGenericDAO gdo = new GenericDAO();
			if (suchLm!=null) {
				if (this.suchLm.getName()!=null){
					query +=" AND LOWER(l.NAME) ="+this.suchLm.getName()+"";
				}
				System.out.println(query);
				this.lvas =  gdo.unsafeQuery(query, suchLm);
			}
		} catch (Exception e){
			System.out.println("AAARGH;"+e.toString());
		}
	}
	
	public LehrmittelTableModel (){
	}
	
	
	public void setSuchLehrmittel (Lehrmittel suchLm){
		this.suchLm=suchLm;
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
		Lehrmittel lm = (Lehrmittel) lvas.get(row);
		if (lm==null)
			return null;
		else if (col==0)
			return lm.getName();
		else if (col==1)
			return lm.getDescription();
		else return null;
	}
}

