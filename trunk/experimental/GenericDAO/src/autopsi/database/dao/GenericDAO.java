/*
 * filename: Main.java
 * description: testing application for the experimental GenericDAO
 * author: Rudolf Mildner
 * 
 */

package autopsi.database.dao;

import autopsi.database.GenericDataObject;
import autopsi.database.attribute.GenericDataAttribute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class GenericDAO {
	
	private Connection dbConnection = null;
	private String dbTable = "";
	
	private Connection getConnection(){
		
		return null;
	}
	
	public void setDbTable(String newDbTableName){
		dbTable = newDbTableName;
	}
	
	public String getDbTable(){
		return dbTable;
	}
	
	/**
	 * selectObjects stellt an die Datenbank eine Select-Anfrage mit den Attributes aus selectionAttributes; falls die Liste keine selectionAttribute enthält, wird eine Liste aller Objekte der Datenbankabelle geliefert
	 * @param selectionAttributes Die Attribute, nach denen gesucht werden soll
	 * @return das Ergebnis der Anfrage als Liste generischer DataObjekte
	 */
	public List<GenericDataObject> selectObjects(List<GenericDataAttribute> selectionAttributes){
		List<GenericDataObject> list = new ArrayList<GenericDataObject>();
		String query = createQuery(selectionAttributes);
		return list;
	}
	
	/**
	 * Erzeugt ein SQL Select-Statement, welches aus der dbTable alle Tupel mit den in selectionAttributes gewählten Attributes auswählt
	 * @param selectionAttributes enthält die Attribute, nach denen die Tupel aus der Datenbank ausgewählt werden
	 * @return ein SQL Select Statement
	 */
	private String createQuery(List<GenericDataAttribute> selectionAttributes){
		
		return "";
	}
	
}
