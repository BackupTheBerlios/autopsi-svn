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
import autopsi.database.sql.SQLStatement;
import java.util.List;
import java.util.ArrayList;

public interface IGenericDAO {
	
	
	/**
	 * Sets the username and password which the IGenericDAO will connect with to the database. 
	 * @param userName the username
	 * @param userPassword the password
	 */
	public void setDbUser(String userName, String userPassword);
	
	/**
	 * Sets the table in which the GenericDAO will operate
	 * @param name the name of the table
	 */
	public void setCurrentTable(String name);
	
	
	/**
	 * Returns the name of the current table the implementing class of the IGenericDAO works with at the moment
	 * * @return The name of the current table
	 */
	public String getCurrentTable();
	
	/**
	 * /**
	 * Searches the current database table for the object described by the param id
	 * @param lookupObj The lookup object
	 * @return The return value is a List of GenericDataObjects that match the lookupObject
	 * @throws EDatabase
	 * @throws EDatabaseConnection
	 * @throws EAttributeNotFound
	 */
	public List<GenericDataObject> getDataObjects(GenericDataObject lookupObj) throws EDatabase, EDatabaseConnection, EAttributeNotFound;
	
	/**
	 * Adds an object to the current table
	 * @param newObj The object that will be added to the database
	 * @throws EDatabaseConnection
	 * @throws EAttributeNotFound
	 * @throws EDatabase
	 */
	public void addDataObject(GenericDataObject newObj) throws EDatabaseConnection, EAttributeNotFound, EDatabase;
	
	/**
	 * Deletes an objects from the current table
	 * @param lookupObj The lookup object
	 * @throws EDatabaseConnection
	 * @throws EAttributeNotFound
	 * @throws EDatabase
	 */
	public void delDataObjects(GenericDataObject lookupObj) throws EDatabaseConnection, EAttributeNotFound, EDatabase;
	
	
	/**
	 * Updates all objects that look like lookupObj to newObj
	 * @param lookupObj The lookup object
	 * @param newObj The object that will be used to update the objects that matched the lookup object
	 * @throws EDatabaseConnection
	 * @throws EAttributeNotFound
	 * @throws EDatabase
	 */
	public void updDataObjects(GenericDataObject lookupObj, GenericDataObject newObj) throws EDatabaseConnection, EAttributeNotFound, EDatabase;

	
	public List<GenericDataObject> unsafeQuery(String query, GenericDataObject prototype)  throws EDatabase, EDatabaseConnection, EAttributeNotFound;
	
}
