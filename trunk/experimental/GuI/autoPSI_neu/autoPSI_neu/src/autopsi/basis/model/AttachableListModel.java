package autopsi.basis.model;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.exception.EAttributeNotFound;
import autopsi.database.exception.EDatabase;
import autopsi.database.exception.EDatabaseConnection;
import autopsi.database.table.Kontakt;
import autopsi.database.table.Lehrmittel;
import autopsi.database.table.Lva;
import autopsi.database.table.Notiz;
import autopsi.database.table.Pruefung;

public class AttachableListModel implements ListModel {

	
	protected GenericDAO gdao;
	protected List<GenericDataObject> objs;
	protected String tableName;
	
	public AttachableListModel(){
		this.gdao = new GenericDAO();
	}
	
	
	public String getTableName(){
		return this.tableName;
	}
	
	public void setTableName(String newTableName){
		this.tableName = newTableName.toLowerCase();
		this.gdao.setCurrentTable(this.tableName);
		if (this.tableName.equals("kontakt")){
			try {
				objs = this.gdao.getDataObjects(new Kontakt());
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		}
		if (this.tableName.equals("lva")){
		
			try {
				objs = this.gdao.getDataObjects(new Lva());
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		}
		if (this.tableName.equals("pruefung")){
			
			try {
				objs = this.gdao.getDataObjects(new Pruefung());
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		}
		if (this.tableName.equals("lehrmittel")){
			
			try {
				objs = this.gdao.getDataObjects(new Lehrmittel());
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		}
		if (this.tableName.equals("notiz")){
		
			try {
				objs = this.gdao.getDataObjects(new Notiz());
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public int getSize() {
		if (this.objs == null)
			return 0;
		else 
			return this.objs.size();
	}

	public GenericDataObject getObjectAt(int arg0) {
		if (objs == null)
			return null;
		return this.objs.get(arg0);
	}
	
	public Object getElementAt(int arg0) {
		if (this.objs == null)
			return null;
		
		GenericDataObject ob = this.objs.get(arg0);
		if (ob instanceof Kontakt){
			Kontakt k = (Kontakt)ob;
			return new String(k.getPrename() + " | " + k.getSurname());
		}
		if (ob instanceof Notiz){
			Notiz k = (Notiz)ob;
			return new String(k.getTitle());
		}
		if (ob instanceof Lva){
			Lva k = (Lva)ob;
			return new String(k.getLvaNr() + " | " + k.getTitle());
		}
		if (ob instanceof Lehrmittel){
			Lehrmittel k = (Lehrmittel)ob;
			return new String(k.getName());
		}
		if (ob instanceof Pruefung){
			Pruefung k = (Pruefung)ob;
			this.gdao.setCurrentTable("lva");
			Lva l = new Lva();
			l.setGlobalId(k.getLvaId());
			List<GenericDataObject> lList = null;
			try {
				lList = this.gdao.getDataObjects(l);
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
			if (lList.get(0) == null)
				return new String("");
			
			l = (Lva)lList.get(0);
			return new String(l.getLvaNr() + " | " + l.getTitle() + " | " + k.getExaminer() + " | " + k.getGrade());
		}
		return null;
	}

	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

}
