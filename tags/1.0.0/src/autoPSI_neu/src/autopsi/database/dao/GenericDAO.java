package autopsi.database.dao;


import java.beans.Statement;
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

	
	private Connection dbCon= null;
	private String currentTable;
	private String username = "SA";
	private String password = "AB";
	private boolean debug = false;
	
	/**
	 * Returns the whether debug is set for this GenericDAO or not
	 * @return True if debug is set, false otherwise
	 */
	public boolean getDebug(){
		return this.debug;
	}

	/**
	 * Sets the debug status of this GenericDAO
	 * @param newDebug The new debug state
	 */
	public void setDebug(boolean newDebug){
		this.debug = newDebug;
	}
	
	/**
	 * Sets the username and password which the IGenericDAO will connect with to the database. 
	 * @param userName the username
	 * @param userPassword the password
	 */
	public void setDbUser(String userName, String userPassword){
		this.username = userName;
		this.password = userPassword;
	}
	
	/**
	 * Tries to get a connection from the hsql database
	 * @return Returns a valid database connection if possible, otherwise null
	 */
	protected Connection connect(){
		try{
			if ((dbCon == null) || (dbCon.isClosed())){
				Class.forName("org.hsqldb.jdbcDriver");
				dbCon = DriverManager.getConnection("jdbc:hsqldb:data/Database;shutdown=true", username, password);
				dbCon.setAutoCommit(false);
			}
		}
		catch (SQLException e){
			System.out.println("Couldn't open database connection\nException says ::"+e.toString());
			System.out.println();
			return null;
		}
		catch (ClassNotFoundException e){
			System.out.println("Database Driver not found\nException says ::"+e.toString());
			return null;
		}
		return dbCon;
	}
	
	/**
	 * Sets the table in which the GenericDAO will operate
	 * @param name the name of the table
	 */
	public void setCurrentTable(String tableName){
		//set current table to tableName
		currentTable = tableName;
	}
	
	/**
	 * Returns the name of the current table the implementing class of the IGenericDAO works with at the moment
	 * * @return The name of the current table
	 */
	public String getCurrentTable(){
		return currentTable;
	}
	
	/**
	 * Returns true if the column columnName is in table
	 * @param tableInformation The Table information that can be gathered e.g. from an PreparedStatement
	 * @param columnName The column name to search
	 * @return True if the column columnName is in the Table, false otherwise
	 */
	protected boolean columnInTable(ResultSetMetaData tableInformation, String columnName){
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
	
	/**
	 * Inspects an GenericDataObject and returns all its not-null-fields in form of a SQLField. 
	 * The methods gets all fields of obj that are != null and creates SQLField objects that represent that fields. 
	 * Note that this method reads out even private access fields of obj (Will possibly be changed in the future).
	 * @param obj The GenericDataObject to inspect
	 * @return A SQLFields object that represents the fields of obj
	 * @throws EAttributeNotFound
	 */
	public SQLFields getSQLFields(GenericDataObject obj) throws EAttributeNotFound{
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
				field = new SQLField(fd[i].getName(), fd[i].get(obj).toString());
				fields.add(field);
			}
		}
		catch (IllegalAccessException e){
			System.out.println("GenericDAO: Couldn't get attributes through reflection");
		}
			
		return fields;
	}
	
	/**
	 * Inspects an GenericDataObject and returns all its fields (including these which are == null )in form of a SQLField. 
	 * The methods gets all fields of obj and creates SQLField objects that represent that fields. 
	 * Note that this method reads out even private access fields of obj (Will possibly be changed in the future).
	 * @param obj The GenericDataObject to inspect
	 * @return A SQLFields object that represents the fields of obj
	 * @throws EAttributeNotFound
	 */
	public SQLFields getSQLAllFields(GenericDataObject obj) throws EAttributeNotFound{
		SQLFields fields = new SQLFields();
		Field[] fd = obj.getClass().getDeclaredFields();
		AccessibleObject.setAccessible(fd, true);
		try{
			for(int i=0;i<fd.length;i++){
				SQLField field = null;
				if(fd[i].get(obj) == null){
					field = new SQLField(fd[i].getName(), "NULL");
					fields.add(field);
				}
				else{
					field = new SQLField(fd[i].getName(), fd[i].get(obj).toString());
					fields.add(field);
				}
			}
		}
		catch (IllegalAccessException e){
			System.out.println("GenericDAO: Couldn't get attributes through reflection");
		}
			
		return fields;
	}
	
	protected void checkAttributes(ResultSetMetaData metaData, GenericDataObject obj) throws EAttributeNotFound{
		Field[] fd = obj.getClass().getDeclaredFields();
		AccessibleObject.setAccessible(fd, true);
		for (int i=0;i<fd.length;i++){
			if (!columnInTable(metaData, fd[i].getName()))
				throw new EAttributeNotFound();
		}
	}
	
	/**
	 * /**
	 * Searches the current database table for the object described by the param id
	 * All fields which are != null in lookupObject will be considered for the SQL query. 
	 * A null objects therefore is a sort of a wildcard.
	 * @param lookupObj The lookup object
	 * @return The return value is an ArrayList of GenericDataObjects that match the lookupObject
	 * @throws EDatabase
	 * @throws EDatabaseConnection
	 * @throws EAttributeNotFound
	 */
	public List<GenericDataObject> getDataObjects(GenericDataObject lookupObj) throws EDatabase, EDatabaseConnection, EAttributeNotFound{
		if (connect() == null)
			throw new EDatabaseConnection();
		
		SQLTable sqlTable = new SQLTable(currentTable);
		SQLFields sqlFields = getSQLFields(lookupObj);
//		sqlFields.beginTraversal();
//		while(sqlFields.next())
//			System.out.println("next=="+sqlFields.getCurrentName()+";"+sqlFields.getCurrentValue());
		
		SQLStatement sqlSelect = new SQLSelect(sqlTable, sqlFields);
		String query = sqlSelect.getQuery();
		
		if (this.debug)
			System.out.println("GenericDAO.getDataObjects(...)::query=="+query);
		
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
		catch (Exception e){
			System.out.println("GenericDAO.getDataObjects(...)::"+e.toString());
			throw new EDatabase();
		}
		return res;
	}
	
	
	
	public void addDataObject(GenericDataObject newObj) throws EDatabaseConnection, EAttributeNotFound, EDatabase{
		
		//get a database connection
		if (connect() == null)
			throw new EDatabaseConnection();
		
		SQLTable table = new SQLTable(currentTable);
		SQLFields fields = getSQLAllFields(newObj);
		SQLStatement sqlInsert = new SQLInsert(table, fields);
		String query = sqlInsert.getQuery();
		
		if (this.debug)
			System.out.println("GenericDAO.addDataObject::query=="+query);
		
		try{
			PreparedStatement ps = null;
			try{
				ps = dbCon.prepareStatement(query);
			}
			catch (SQLException e){
				if (debug)
					System.out.println("GenericDAO.addDataObject::Error bei Query, versuche testweise Query"+"SELECT * FROM "+currentTable);
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
		
		if (this.debug)
			System.out.println("GenericDAO.delDataObject::query=="+query);
		
		try{
			PreparedStatement ps = null;
			try{
				ps = dbCon.prepareCall(query);
			}
			catch (SQLException e){
				if (debug)
					System.out.println("GenericDAO.addDataObject::Error bei Query, versuche testweise Query"+"SELECT * FROM "+currentTable);
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
		
		if (this.debug)
			System.out.println("GenericDAO.updDataObject::query=="+query);
		
		try{
			PreparedStatement ps = null;
			try{
				ps = dbCon.prepareStatement(query);
			}
			catch (SQLException e){
				if (debug)
					System.out.println("GenericDAO.addDataObject::Error bei Query, versuche testweise Query"+"SELECT * FROM "+currentTable);
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
	
	public List<GenericDataObject> complexQuery(SQLStatement stm, GenericDataObject dataObjectTemplate) throws EDatabaseConnection, EDatabase, EAttributeNotFound {
		if (connect() == null)
			throw new EDatabaseConnection();
		
		List<GenericDataObject> res = null;
		String query = stm.getQuery();
		
		if (this.debug)
			System.out.println("GenericDAO.complexQuery::query=="+query);
			
		PreparedStatement ps = null;
		try{
			try{
				ps = dbCon.prepareStatement(query);
			}
			catch (SQLException e){
				if (debug)
					System.out.println("GenericDAO.addDataObject::Error bei Query, versuche testweise Query"+"SELECT * FROM "+currentTable);
				ps = dbCon.prepareStatement("SELECT * FROM "+currentTable);
				
				if (ps ==null)
					throw new EDatabase();
				
				this.checkAttributes(ps.getMetaData(), dataObjectTemplate);
				throw new EDatabase();
			}
			
			if (!ps.execute())
				throw new EDatabase();
			
			ResultSet rs = ps.getResultSet();
			res = new ArrayList<GenericDataObject>();
			while (rs.next()){
				GenericDataObject obj = dataObjectTemplate.getClass().newInstance();
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
	
	public List<GenericDataObject> unsafeQuery(String query, GenericDataObject prototype)  throws EDatabase, EDatabaseConnection, EAttributeNotFound{
		if (connect() == null)
			throw new EDatabaseConnection();
		
		if (this.debug)
			System.out.println("GenericDAO.unsafeQuery::query=="+query);
		
		List<GenericDataObject> res = null;
		try{
			
			java.sql.Statement ps = null;
			try{
				ps = dbCon.createStatement();
			}
			catch (SQLException e){
				System.out.println("unsafeQuery::prepareStatement fehlgeschlagen::"+e.toString());
				
				if (debug)
					System.out.println("GenericDAO.addDataObject::Error bei Query, versuche testweise Query"+"SELECT * FROM "+currentTable);
				ps = dbCon.prepareStatement("SELECT * FROM "+currentTable);
				
				if (ps ==null)
					throw new EDatabase();
				
/*				this.checkAttributes(ps.getMetaData(), prototype);
				throw new EDatabase();*/
			}
			
/*			if (!ps.execute()){
				System.out.println("ps.execute() konnte nicht ausgeführt werden");
				throw new EDatabase();
			}
			dbCon.commit();
			*/
				
//			ResultSet rs = ps.getResultSet();
			ResultSet rs = ps.executeQuery(query);
			ps.close();
			dbCon.commit();
			res = new ArrayList<GenericDataObject>();
			while (rs.next()){
				GenericDataObject obj = prototype.getClass().newInstance();
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
	
	protected void shutdownDB() throws Throwable{
		try{
			System.out.println("closing DB Connection with SHUTDOWN");
			PreparedStatement pshut = dbCon.prepareStatement("SHUTDOWN IMMEDIATELY");
			pshut.execute();
			dbCon.commit();
		}
		catch (Exception e){
			System.out.println("Couldn' t close DB");
		}
	}
	
}
