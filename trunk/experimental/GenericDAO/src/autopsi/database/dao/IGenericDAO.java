/**
 * filename: IGenericDAO.java
 * description: Interface for GenericDAO functionality
 * @author: Rudolf Mildner
 */

package autopsi.database.dao;

import autopsi.database.exception.EAttributeNotFound;
import autopsi.database.exception.EDatabaseConnection;
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
	public List<GenericDataObject> getDataObjects(GenericDataObject lookupObj) throws EDatabase, EDatabaseConnection, EAttributeNotFound;
	
	
	public void addDataObject(GenericDataObject newObj) throws EDatabaseConnection, EAttributeNotFound, EDatabase;
	
	
	public void delDataObjects(GenericDataObject lookupObj) throws EDatabaseConnection, EAttributeNotFound, EDatabase;
	
	
	public void updDataObjects(GenericDataObject lookupObj, GenericDataObject newObj) throws EDatabaseConnection, EAttributeNotFound, EDatabase;
}
