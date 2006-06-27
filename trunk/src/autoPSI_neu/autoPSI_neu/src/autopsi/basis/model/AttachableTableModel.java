package autopsi.basis.model;



import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.exception.EAttributeNotFound;
import autopsi.database.exception.EDatabase;
import autopsi.database.exception.EDatabaseConnection;
import autopsi.gui.component.GenericData;

public class AttachableTableModel implements TableModel {

	
	protected GenericDAO gdao;
	protected String tableName;
	protected String editedAttrib;
	protected Class editedClass;
	protected List<GenericDataObject> objs;
	protected Field[] fields;
	
	public AttachableTableModel(){
		this.gdao = new GenericDAO();
	}
	
	public GenericDataObject getObject(int row){
		return objs.get(row);
	}
	
	public void setObjectType(String tableName, String editedAttrib, Class editedClass){
		this.tableName = tableName;
		this.editedAttrib = editedAttrib;
		this.editedClass = editedClass;
		GenericDataObject gdPrototype = null;
		try {
			gdPrototype = (GenericDataObject)editedClass.newInstance();
			
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		fields = editedClass.getDeclaredFields();
		AccessibleObject.setAccessible(fields, true);
		this.gdao.setCurrentTable(this.tableName);
		try {
			objs = this.gdao.getDataObjects(gdPrototype);
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
	}

	public int getRowCount() {
		return this.objs.size();
	}

	public int getColumnCount() {
		return fields.length;
	}

	public String getColumnName(int arg0) {
		Field[] fd = this.editedClass.getDeclaredFields();
		return fd[arg0].getName();
	}

	public Class<?> getColumnClass(int arg0) {
		return fields[arg0].getClass();
	}

	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		try {
			return fields[arg1].get(objs.get(arg0));
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		return null;
	}

	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}




}
