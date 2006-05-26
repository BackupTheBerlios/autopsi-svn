/**
 * filename: IGenericDAO.java
 * description: Interface for GenericDAO functionality
 * @author: Rudolf Mildner
 */

package autopsi.database.dao;

import autopsi.database.exception.EDatabaseConnectionError;
import autopsi.database.exception.EDatabase;
import autopsi.database.dao.IGenericDAO;
import java.util.List;
import java.util.ArrayList;

public interface IGenericDAO {
	
	/**
	 * sets the table in which the GenericDAO will operate
	 * @param name the name of the table
	 */
	public void setCurrentTable(String name);
	
	
	public String getCurrentTable();
	/**
	 * searches the current database table for the object described by the param id
	 * @param obj the old GenericDataObject
	 * @return the GenericDataObject to which the param id corresponds
	 */
	public List<GenericDataObject> getDataObjects(GenericDataObject lookupObj) throws EDatabaseConnectionError, EDatabase;
	
	
	public boolean addDataObject(GenericDataObject lookupObj);
	
	
	public boolean delDataObjects(GenericDataObject lookupObj);
	
	
	public boolean updDataObjects(GenericDataObject lookupObj, GenericDataObject obj);
}
