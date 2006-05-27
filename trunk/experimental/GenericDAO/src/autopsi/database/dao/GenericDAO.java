package autopsi.database.dao;


import java.lang.String;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import autopsi.database.exception.EDatabaseConnection;
import autopsi.database.exception.EAttributeNotFound;
import autopsi.database.exception.EDatabase;
import autopsi.database.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Iterator;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

public class GenericDAO implements IGenericDAO{

	private final static String SQL_SELECT1 = "SELECT * FROM ";
	private final static String SQL_INSERT1 = "INSERT INTO ";
	
	private Connection dbCon= null;
	private String currentTable;
	
	
	protected Connection connect(){
		try{
			if ((dbCon == null) || (dbCon.isClosed())){
				Class.forName("org.hsqldb.jdbcDriver");
				dbCon = DriverManager.getConnection("jdbc:hsqldb:data/Database", "sa", "ab");
				dbCon.setAutoCommit(false);
			}
		}
		catch (SQLException e){
			System.out.println("Couldn't open database connection");
			return null;
		}
		catch (ClassNotFoundException e){
			System.out.println("Database Driver not found");
			return null;
		}
		return dbCon;
	}
	
	public void setCurrentTable(String tableName){
		//set current table to tableName
		currentTable = tableName;
	}
	
	public String getCurrentTable(){
		return currentTable;
	}
	
	private boolean columnInTable(ResultSetMetaData tableInformation, String columnName){
		boolean inColumn = false;
		try{
			for (int k=1;k<tableInformation.getColumnCount()+1;k++){
				if (tableInformation.getColumnName(k).toLowerCase().equals(columnName)){
					inColumn = true;
					break;
				}
			}
		}
		catch (SQLException e){
			System.out.println("SQLException in columnInTable");
		}
		return inColumn;
	}
	
	
	private SQLFields getSQLFields(GenericDataObject obj) throws EAttributeNotFound{
		SQLFields fields = new SQLFields();
		Field[] fd = obj.getClass().getDeclaredFields();
		AccessibleObject.setAccessible(fd, true);
		try{
			for(int i=0;i<fd.length;i++){
				
				if(fd[i].get(obj) == null)
					continue;
				
		/*		if (!columnInTable(tableInformation, fd[i].getName()))
					throw new EAttributeNotFound();
		*/		
				SQLField field = null;
				if ( fd[i].getType().equals(String.class) ){
					field = new SQLField(fd[i].getName(), "'"+fd[i].get(obj).toString()+"'");
					fields.add(field);
					continue;
				}
				
				field = new SQLField(fd[i].getName(),fd[i].get(obj).toString());
				fields.add(field);
			}
		}
		catch (IllegalAccessException e){
			System.out.println("GenericDAO: Couldn't get attributes through reflection");
		}
			
		return fields;
	}
	
	private void checkAttributes(ResultSetMetaData metaData, GenericDataObject obj) throws EAttributeNotFound{
		Field[] fd = obj.getClass().getDeclaredFields();
		AccessibleObject.setAccessible(fd, true);
		for (int i=0;i<fd.length;i++){
			if (!columnInTable(metaData, fd[i].getName()))
				throw new EAttributeNotFound();
		}
	}
	
	/**
	 * getWhere durchsucht liefert einen String, welcher der WHERE-Klausen einer SQL-Abfrage entspricht. 
	 * @param lookupObj 
	 * @return 
	 */
	private String getWhere(GenericDataObject lookupObj, ResultSetMetaData tableInformation) throws EAttributeNotFound{
		String ret = "";
		Field[] fd = lookupObj.getClass().getDeclaredFields();
		AccessibleObject.setAccessible(fd, true);
		try{
			boolean seperate = false;
			for (int i=0;i<fd.length;i++){
				//if field of lookupObject is null that means that this value is unknown
				if (fd[i].get(lookupObj) == null)
					continue;
				
				//if field has no equivalent in the current table, simply skip it
				if (!columnInTable(tableInformation, fd[i].getName()))
					throw new EAttributeNotFound();
				
				if (seperate)
					ret=ret+" AND ";
				else {
					ret=ret+" WHERE ";
					seperate = true;
				}
				ret=ret+fd[i].getName()+"=";
				System.out.println(fd[i].getName());
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
		System.out.println("WHERE_IS=="+ret);
		return ret;
	}
	
	public List<GenericDataObject> getDataObjects(GenericDataObject lookupObj) throws EDatabase, EDatabaseConnection, EAttributeNotFound{
		if (connect() == null)
			throw new EDatabaseConnection();
		
		SQLTable sqlTable = new SQLTable(currentTable);
		SQLFields sqlFields = getSQLFields(lookupObj);
		SQLStatement sqlSelect = new SQLSelect(sqlTable, sqlFields);
		String query = sqlSelect.getQuery();
		List<GenericDataObject> res = null;
		try{
			
			PreparedStatement ps = null;
			try{
				ps = dbCon.prepareStatement(query);
			}
			catch (SQLException e){
				ps = dbCon.prepareStatement("SELECT * FROM "+currentTable);
				
				if (ps ==null)
					throw new EDatabase();
				
				this.checkAttributes(ps.getMetaData(), lookupObj);
				throw new EDatabase();
			}
			
			if (!ps.execute())
				throw new EDatabase();
			
			ResultSet rs = ps.getResultSet();
			res = new ArrayList<GenericDataObject>();
			while (rs.next()){
				GenericDataObject obj = lookupObj.getClass().newInstance();
				Field[] fd = obj.getClass().getDeclaredFields();
				AccessibleObject.setAccessible(fd, true);
				for(int i=0;i<fd.length;i++){
					Object dbObj = null;
					dbObj = rs.getObject(fd[i].getName());	
					fd[i].set(obj, dbObj);
				}
				res.add(obj);
			}
		}
		catch (SQLException e){
			System.out.println("Exception;"+e.toString());
			throw new EDatabase();
		}
		catch (InstantiationException e){
			System.out.println("Exception;"+e.toString());
			throw new EDatabase();
		}
		catch (IllegalArgumentException e){
			System.out.println("Exception;"+e.toString());
			throw new EDatabase();
		}
		catch (IllegalAccessException e){
			System.out.println("Exception;"+e.toString());
			throw new EDatabase();
		}
		return res;
	}
	
	public void addDataObject(GenericDataObject newObj) throws EDatabaseConnection, EAttributeNotFound, EDatabase{
		
		//get a database connection
		if (connect() == null)
			throw new EDatabaseConnection();
		
		SQLTable table = new SQLTable(currentTable);
		SQLFields fields = getSQLFields(newObj);
		SQLStatement sqlInsert = new SQLInsert(table, fields);
		
		String query = sqlInsert.getQuery();
		try{
			PreparedStatement ps = null;
			try{
				ps = dbCon.prepareStatement(query);
			}
			catch (SQLException e){
				ps = dbCon.prepareStatement("SELECT * FROM "+currentTable);
				
				if (ps == null){
					throw new EDatabase();
				}
					
				this.checkAttributes(ps.getMetaData(), newObj);
				throw new EDatabase();
			}
			
			ps.execute();
			dbCon.commit();
			
		}
		catch (SQLException e){
			System.out.println("Exception@GenericDAO.addDataObject=="+e.toString());
			throw new EDatabase();
		}
	}
	
	
	public void delDataObjects(GenericDataObject lookupObj) throws EDatabaseConnection, EAttributeNotFound, EDatabase{
		//get a database connection or use the existing one (-> late binding)
		if (connect() == null)
			throw new EDatabaseConnection();
		
		//use the SQLStatement SQLDelete to receive a SQL statement
		SQLTable sqlTable = new SQLTable(currentTable);
		SQLFields sqlFields = getSQLFields(lookupObj);
		SQLStatement sqlDelete = new SQLDelete(sqlTable, sqlFields);
		String query = sqlDelete.getQuery();
		
		try{
			PreparedStatement ps = null;
			try{
				ps = dbCon.prepareCall(query);
			}
			catch (SQLException e){
				ps = dbCon.prepareStatement("SELECT * FROM "+currentTable);
				
				if (ps == null)
					throw new EDatabase();
				
				this.checkAttributes(ps.getMetaData(), lookupObj);
				
				throw new EDatabase();
			}
			
			ps.execute();
			dbCon.commit();
			
		}
		catch (SQLException e){
			System.out.println("Exception@GenericDAO.delDataObjects=="+e.toString());
			throw new EDatabase();
		}
	}
	
	public void updDataObjects(GenericDataObject lookupObj, GenericDataObject newObj) throws EDatabaseConnection, EAttributeNotFound, EDatabase{
		if (connect() == null)
			throw new EDatabaseConnection();
		
		SQLTable sqlTable = new SQLTable(currentTable);
		SQLFields sqlLookupFields = getSQLFields(lookupObj);
		SQLFields sqlNewFields = getSQLFields(newObj);
		SQLStatement sqlUpdate = new SQLUpdate(sqlTable, sqlLookupFields, sqlNewFields);
		String query = sqlUpdate.getQuery();
		
		try{
			PreparedStatement ps = null;
			try{
				ps = dbCon.prepareStatement(query);
			}
			catch (SQLException e){
				ps = dbCon.prepareStatement("SELECT * FROM "+currentTable);
				
				if (ps == null)
					throw new EDatabase();
				
				this.checkAttributes(ps.getMetaData(), lookupObj);
				this.checkAttributes(ps.getMetaData(), newObj);
				
				throw new EDatabase();
			}
			
			ps.execute();
			dbCon.commit();
		}
		catch (SQLException e){
			System.out.println("Exception@GenericDAO.updDataObjects=="+e.toString());
			throw new EDatabase();
		}
	}
	
}
