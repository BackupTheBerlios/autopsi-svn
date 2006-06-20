package autopsi.basis.model;



import java.util.List;

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
	protected GenericDataObject gdPrototype;
	protected List<GenericDataObject> objs;
	
	public AttachableTableModel(){
		this.gdao = new GenericDAO();
	}
	
	public void setObjectType(String tableName, GenericDataObject gdPrototype){
		this.tableName = tableName;
		this.gdPrototype = gdPrototype;
		this.gdao.setCurrentTable(this.tableName);
		try {
			objs = this.gdao.getDataObjects(this.gdPrototype);
		} catch (EDatabaseConnection e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EAttributeNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EDatabase e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getRowCount() {
		if (this.objs == null)
			return 0;
		else
			return this.objs.size();
	}

	public int getColumnCount() {
		if (this.gdPrototype == null)
			return 0;
//		else return this.gdPrototype.getAttribCount();
		return 0;
	}

	public String getColumnName(int arg0) {
		return "";
	}

	public Class<?> getColumnClass(int arg0) {
		return null;
	}

	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
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
