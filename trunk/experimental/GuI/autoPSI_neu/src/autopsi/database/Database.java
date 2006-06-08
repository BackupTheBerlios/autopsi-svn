package autopsi.database;


import java.util.List;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.exception.EAttributeNotFound;
import autopsi.database.exception.EDatabase;
import autopsi.database.exception.EDatabaseConnection;


public class Database {

	private static IGenericDAO dao = new GenericDAO();
	
	
	
	public static void setDbUser(String userName, String userPassword){
		dao.setDbUser(userName, userPassword);
	}
	
	public static void setCurrentTable(String name){
		dao.setCurrentTable(name);
	}
	
	
	public static String getCurrentTable(){
		return dao.getCurrentTable();
	}
	public static List<GenericDataObject> getDataObjects(GenericDataObject lookupObj) throws EDatabase, EDatabaseConnection, EAttributeNotFound{
		return dao.getDataObjects(lookupObj);
	}
	
	public static void addDataObject(GenericDataObject newObj) throws EDatabaseConnection, EAttributeNotFound, EDatabase{
		dao.addDataObject(newObj);
	}
	
	public static void delDataObjects(GenericDataObject lookupObj) throws EDatabaseConnection, EAttributeNotFound, EDatabase{
		dao.delDataObjects(lookupObj);
	}
	
	public static void updDataObjects(GenericDataObject lookupObj, GenericDataObject newObj) throws EDatabaseConnection, EAttributeNotFound, EDatabase{
		dao.updDataObjects(lookupObj, newObj);
	}
}
