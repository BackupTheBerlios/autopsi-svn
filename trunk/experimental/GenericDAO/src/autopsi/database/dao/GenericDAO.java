package autopsi.database.dao;


import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import autopsi.database.exception.EDatabaseConnectionError;
import autopsi.database.exception.EDatabase;
import autopsi.database.attribute.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Iterator;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

public class GenericDAO implements IGenericDAO{

	private final static String SQL_SELECT1 = "SELECT * FROM ";
	
	private Connection dbCon= null;
	private String currentTable;
	
	
	protected boolean connect(){
		try{
			if ((dbCon == null) || (dbCon.isClosed())){
				Class.forName("org.hsqldb.jdbcDriver");
				dbCon = DriverManager.getConnection("jdbc:hsqldb:data/Database", "sa", "");
				dbCon.setAutoCommit(false);
			}
		}
		catch (SQLException e){
			System.out.println("Couldn't open database connection");
			return false;
		}
		catch (ClassNotFoundException e){
			System.out.println("Database Driver not found");
			return false;
		}
		return true;
	}
	
	public void setCurrentTable(String tableName){
		currentTable = tableName;
	}
	
	public String getCurrentTable(){
		return currentTable;
	}
	
	public String getWhere(GenericDataObject lookupObj){
		String ret = "";
		Field[] fd = lookupObj.getClass().getDeclaredFields();
		AccessibleObject.setAccessible(fd, true);
		try{
			boolean seperate = false;
			for (int i=0;i<fd.length;i++){
				if (fd[i].get(lookupObj) == null)
					continue;
				if (seperate)
					ret=ret+" AND ";
				else {
					ret=ret+" WHERE ";
					seperate = true;
				}
				ret=ret+fd[i].getName()+"=";
				if ( fd[i].getType().equals(String.class) ){
					ret=ret+"'"+fd[i].get(lookupObj).toString()+"'";
					continue;
				}
				ret=ret+fd[i].get(lookupObj).toString();
			}
		}
		catch (Exception e){
			System.out.println("exception@getWhere;"+e.toString());
		}
		return ret;
	}
	
	public List<GenericDataObject> getDataObjects(GenericDataObject lookupObj) throws EDatabaseConnectionError, EDatabase{
		if (!connect())
			throw new EDatabaseConnectionError();
		String query= SQL_SELECT1+currentTable+this.getWhere(lookupObj);
		List<GenericDataObject> res = null;
		try{
			PreparedStatement ps = dbCon.prepareStatement(query);
			if (!ps.execute())
				throw new EDatabase();
			ResultSet rs = ps.getResultSet();
			res = new ArrayList<GenericDataObject>();
			while (rs.next()){
				GenericDataObject obj = lookupObj.getClass().newInstance();
				Field[] fd = obj.getClass().getDeclaredFields();
				AccessibleObject.setAccessible(fd, true);
				for(int i=0;i<fd.length;i++){
					fd[i].set(obj, rs.getObject(fd[i].getName()));
				}
				res.add(obj);
			}
		}
		catch (Exception e){
			System.out.println("Exception;"+e.toString());
		}
		return res;
	}
	
	//to delete
	public List<GenericDataObject> getAllTableData(){
		return null;
	}
	
	public boolean addDataObject(GenericDataObject lookupObj){
		return false;
	}
	
	
	public boolean delDataObjects(GenericDataObject lookupObj){
		return false;
	}
	
	//to delete
	public boolean updDataObjects(GenericDataObject lookupObj, GenericDataObject obj){
		return false;
	}
	
}
