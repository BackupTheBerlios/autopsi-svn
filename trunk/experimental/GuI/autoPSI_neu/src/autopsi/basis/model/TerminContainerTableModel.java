package autopsi.basis.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.table.TerminContainer;

public class TerminContainerTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 8737097029189851737L;
	//List<GenericDataObject> termine = null;
	private List <GenericDataObject> termine;
	private TerminContainer suchTerminc = null;
	private String group=null;
	
	private final String [] columnName = {"Titel", "Beschreibung"};
	
	public List<GenericDataObject> getTermine() {
		return this.termine;
	}
	
	private void readData() {
		String query="select * from TERMINCONTAINER as t, ATTACHABLE_OBJECT_KATEGORIE as ok where t.GROUP_ID = ok.id";
		try{
			IGenericDAO gdo = new GenericDAO();
			if (suchTerminc!=null) {
				if (suchTerminc.getTitle()!=null) {
					query += " AND LOWER(t.TITLE) LIKE '%" + suchTerminc.getTitle().toLowerCase()+"%'";
				}
				if (suchTerminc.getDescription()!=null) {
					query += " AND LOWER(t.DESCRIPTION) LIKE '%" + suchTerminc.getDescription().toLowerCase()+"%'";
				}
				if (this.group != null){
					query +=" AND ok.TITLE LIKE '%"+ this.group+"%'";
				}
				System.out.println("Query:"+query);
				this.termine =  gdo.unsafeQuery(query, suchTerminc);
			}
		} catch (Exception e){
			System.out.println("AAARGH;"+e.toString());
		}
	}
	
	public TerminContainerTableModel (){
	}
	
	
	public void setSuchTerminc (TerminContainer suchterminc){
		this.suchTerminc=suchterminc;
		fireDataChanged();
	}
	
	public void setGroup (String group){
		this.group=group;
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
		if (termine != null) {
			return termine.size();
		} else {
			return 0;
		}
	}
	public String getColumnName(int c) {
		//System.out.println("colName = " + columnName[c]);
		return columnName[c];
	}
	
	public Object getValueAt(int row, int col) {
		//System.out.println("getValue row = " + row + " col = " + col); 
		TerminContainer ter = (TerminContainer) termine.get(row);
		
		if (ter==null)
			return null;
		else if (col==0)
			return ter.getTitle();
		else if (col==1)
			return ter.getDescription();
		else return null;
	}
	
	

}