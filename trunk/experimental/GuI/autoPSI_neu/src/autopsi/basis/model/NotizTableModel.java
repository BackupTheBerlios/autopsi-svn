package autopsi.basis.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.table.Notiz;
import autopsi.database.table.Termin;
import autopsi.javaspace.IServiceCommunicator;
import autopsi.javaspace.ServiceCommunicator;

public class NotizTableModel  extends AbstractTableModel {

	private static final long serialVersionUID = 8737097029189851737L;
	public List <GenericDataObject> Notizs;
	public Notiz suchNotiz = null;
	public String group = null;
	public IServiceCommunicator ogdo = null;
	
	private final String [] columnName = {"Titel", "Note"};
	
	public List<GenericDataObject> getNotizs() {
		return this.Notizs;
	}
	
	private void readData() {
		String query="select * from Notiz as n, ATTACHABLE_OBJECT as a, ATTACHABLE_OBJECT_KATEGORIE as ok where n.GLOBAL_ID=a.GLOBAL_ID AND a.KATEGORIE_ID=ok.ID";
		try{
			IGenericDAO gdo = new GenericDAO();
			if (suchNotiz!=null) {
				if (this.suchNotiz.getTitle()!=null){
					query +=" AND LOWER(n.TITLE) LIKE '%"+this.suchNotiz.getTitle().toLowerCase()+"%'";
				}
				if (this.suchNotiz.getNote()!=null){
					query +=" AND LOWER(n.Note) LIKE '%"+this.suchNotiz.getNote().toLowerCase()+"%'";
				}
				System.out.println(query);
				this.Notizs =  gdo.unsafeQuery(query, suchNotiz);
			}
		} catch (Exception e){
			System.out.println("AAARGH;"+e.toString());
		}
	}
	
	public NotizTableModel (){
		this.ogdo = new ServiceCommunicator();
	}
	
	
	public void setSuchNotiz (Notiz suchNotiz){
		this.suchNotiz=suchNotiz;
		fireDataChanged();
	}
	
	public void setGroup(String gruppe){
		this.group = gruppe;
	}
	
	public void fireDataChanged() {
		readData();
		fireTableDataChanged();
	}
	
	public void fireOnlineDataChanged(){
		readOnlineData();
		fireTableDataChanged();
	}
	
	public void readOnlineData(){
		Notiz temp = null;
		try{
			GenericDataObject t = (GenericDataObject)this.ogdo.getObject(this.suchNotiz);
//			temp = (Notiz)
			if (t instanceof Termin)
				System.out.println("Ist ein Termin!!");
			temp = (Notiz)t;
		}
		catch (Exception e){
			System.out.println("NotizTableModel @ readOnlineData::"+e.toString());
		}
		this.Notizs = new ArrayList<GenericDataObject>();
		if (temp != null)
			this.Notizs.add(temp);
	}
	
	public int getColumnCount() {
		//System.out.println("colCount = " + columnName.length);
		return columnName.length;
	}
	
	public int getRowCount() {
		//System.out.println("rowcount = " + Lesers.size());
		if (Notizs != null) {
			return Notizs.size();
		} else {
			return 0;
		}
	}
	public String getColumnName(int c) {
		//System.out.println("colName = " + columnName[c]);
		return columnName[c];
	}
	
	public Object getValueAt(int row, int col) {
		Notiz n = (Notiz) Notizs.get(row);
		System.out.println("getValueAt, notiz=="+n.getTitle());
		if (n==null)
			return null;
		else if (col==0)
			return n.getTitle();
		else if (col==1)
			return n.getNote();
		else return null;
	}
}
