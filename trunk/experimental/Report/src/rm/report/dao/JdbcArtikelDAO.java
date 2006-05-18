package rm.report.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import rm.report.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import rm.report.data.Artikel;
import rm.report.data.Redakteur;

public class JdbcArtikelDAO implements ArtikelDAO {

	private static Log log = LogFactory.getLog(JdbcArtikelDAO.class);
	private static Connection dbCon = null;


	private final static String SQL_SELECTALL = "SELECT * FROM Artikel";
	private final static String SQL_INSERT = "INSERT INTO Artikel (Titel,Subtitle,Text,Datum,Fertig,Thema,Place_of_Creation,Redakteur_svnr) VALUES(?,?,?,?,?,?,?,?);";
	private final static String SQL_UPDATE = "UPDATE Artikel SET Titel=?,Subtitle=?,Text=?,Datum=?,Fertig=?,Thema=?,Place_of_Creation=?,Redakteur_svnr=? WHERE id=?";
	private final static String SQL_DELETE = "DELETE FROM Artikel WHERE id=?";
	

	private static Connection getConnection(){
		Redakteur red = new Redakteur();
		try {
			if (dbCon == null || dbCon.isClosed()) {
				// Mit diesem Aufruf wird der Treiber geladen
				// Der Treiber registriert sich selbst beim DriverManager
				Class.forName("org.hsqldb.jdbcDriver");
				
				// Datenbankverbindung aufbauen
				// Der DriverManager wählt selbst den korrekten Treiber aus
				dbCon = DriverManager.getConnection(
						"jdbc:hsqldb:data/Database", "admin", "abc");
				dbCon.setAutoCommit(false);
			}
		} catch (SQLException e) {
			log.fatal("Error opening database connection: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			log.fatal("Database Driver not found");
			e.printStackTrace();
		}
		return dbCon;
	}
	
	public Artikel getArtikel(int id){
		String query = "";
		try{
			if (getConnection() == null){
				log.fatal("Error!");
				return null;
			}
			query = "";
			PreparedStatement ps = dbCon.prepareStatement(query);
			if(!ps.execute()){
				log.fatal("Error!");
				return null;
			}
		}
		catch (SQLException e){
			log.fatal("Query gescheitert! : '"+query+"'");
		}
		return null;
	}
	
	public List<Artikel> getAlleArtikel(TableColumn orderColumn, boolean ascending){
		ArrayList<Artikel> list = new ArrayList<Artikel>();
		try{
			getConnection();
			
			String query = SQL_SELECTALL;
			if (!(orderColumn==null)){
				query=query+ " ORDER BY "+orderColumn.getName();
				if (ascending)
					query = query+" ASC;";
				else
					query = query+" DESC;";
			}
			
			PreparedStatement ps = dbCon.prepareStatement(query);		
			ps.execute();
			ResultSet res = ps.getResultSet();
			while(res.next()){
				Artikel art = new Artikel();
				art.setDate(res.getDate("DATUM"));
				art.setErstellOrt(res.getString("PLACE_OF_CREATION"));
				art.setFertig(res.getBoolean("FERTIG"));
				art.setId(res.getInt("ID"));
				art.setText(res.getString("TEXT"));
				art.setThema(res.getString("THEMA"));
				art.setTitel(res.getString("TITEL"));
				art.setUntertitel(res.getString("SUBTITLE"));
				art.setRedakteurSvnr(res.getInt("REDAKTEUR_SVNR"));
				list.add(art);
			}
		}
		catch (SQLException e){
			log.fatal("Query gescheitert! : '"+e.getMessage());
		}
		return list;
	}
	
	public int addArtikel(Artikel neuerArtikel, int validSvnr){
		try{
			getConnection();
			
			PreparedStatement ps = dbCon.prepareStatement(SQL_INSERT);
			ps.setString(1,neuerArtikel.getTitel());
			ps.setString(2,neuerArtikel.getUntertitel());
			ps.setString(3,neuerArtikel.getText());
			ps.setDate(4,neuerArtikel.getDatum());
			ps.setBoolean(5,neuerArtikel.getFertig());
			ps.setString(6,neuerArtikel.getThema());
			ps.setString(7,neuerArtikel.getErstellOrt());
			ps.setInt(8,validSvnr);
			
			ps.execute();
			dbCon.commit();
		}
		catch (SQLException e){
			log.fatal("SQLException "+e.getMessage());
		}
		
		return 0;
	}
	
	public int updateArtikel(int id, Artikel neuerArtikel){
		String query;
		try{
			dbCon = this.getConnection(); 
			
			
			PreparedStatement ps = dbCon.prepareStatement(SQL_UPDATE);
			ps.setString(1,neuerArtikel.getTitel());
			ps.setString(2,neuerArtikel.getUntertitel());
			ps.setString(3,neuerArtikel.getText());
			ps.setDate(4,neuerArtikel.getDatum());
			ps.setBoolean(5,neuerArtikel.getFertig());
			ps.setString(6,neuerArtikel.getThema());
			ps.setString(7,neuerArtikel.getErstellOrt());
			ps.setInt(8,neuerArtikel.getRedakteurSvnr());
			ps.setInt(9,id);
			
			
			ps.execute();
			dbCon.commit();
		}
		catch (SQLException e){
			log.fatal("SQLException=="+e.getMessage());
		}
		return 0;
	}
	
	public int delArtikel(int id){
		try{
			getConnection();
			PreparedStatement ps = dbCon.prepareStatement(SQL_DELETE);
			ps.setInt(1,id);
			ps.execute();
			dbCon.commit();
		}
		catch (SQLException e){
			log.fatal("SQLException "+e.getMessage());
		}
		
		return 0;
	}
}