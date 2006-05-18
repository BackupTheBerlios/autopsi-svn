package rm.report.dao;

import java.util.List;
import java.util.ArrayList;
import rm.report.data.*;
import rm.report.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.sql.*;

public class JdbcRedakteurDAO implements RedakteurDAO{

	private static Log log = LogFactory.getLog(JdbcRedakteurDAO.class);
	public static Connection dbCon = null;
	
	private final static String RECEIVEALL = "SELECT * FROM Redakteur ";
	private final static String ADDSTART = "INSERT INTO Redakteur VALUES(";
	private final static String ADDEND = ")";

	private final static String SQL_INSERT = "INSERT INTO REDAKTEUR (vorname,nachname,tel_nr,funktion,kuerzel,gehalt) VALUES(?,?,?,?,?,?);";
	private final static String SQL_UPDATE ="UPDATE Redakteur SET kuerzel=?,nachname=?,tel_nr=?,vorname=?,gehalt=?, funktion=? WHERE svnr=?";
	private final static String SQL_DELETE = "DELETE FROM Redakteur WHERE svnr=?";
	private final static String SQL_COMMIT="COMMIT";	
	
	private static Connection getConnection(){
		try {
			if (dbCon == null || dbCon.isClosed()) {
				Class.forName("org.hsqldb.jdbcDriver");
				dbCon = DriverManager.getConnection(
						"jdbc:hsqldb:data/Database", "admin", "abc");
				dbCon.setAutoCommit(false);
			}
		} catch (SQLException e) {
			log.fatal("Error opening database connection: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			log.fatal("Database Driver not found");
		}
		return dbCon;
	}
	
	
	public Redakteur getRedakteur(int svnr){
		return null;
	}
	
	public int addRedakteur(Redakteur neuerRedakteur){
		String statement = "";
		try{
			dbCon = this.getConnection();
			PreparedStatement ps = dbCon.prepareStatement(SQL_INSERT);
			ps.setString(1,neuerRedakteur.getVorname());
			ps.setString(2,neuerRedakteur.getNachname());
			ps.setInt(3,neuerRedakteur.getTelNr());
			ps.setString(4,neuerRedakteur.getFunktion());
			ps.setString(5,neuerRedakteur.getKuerzel());
			ps.setDouble(6,neuerRedakteur.getGehalt());
			ps.execute();
			dbCon.commit();
		}
		catch (SQLException e){
			log.fatal("SQL Fehler "+e.getMessage());
		}
		return 0;
	}
	

		
	public int updateRedakteur(int svnr, Redakteur neuerRedakteur){
		try{
			dbCon = getConnection();
			PreparedStatement ps = dbCon.prepareStatement(SQL_UPDATE);
			ps.setString(1,neuerRedakteur.getKuerzel());
			ps.setString(2, neuerRedakteur.getNachname());
			ps.setInt(3,neuerRedakteur.getTelNr());
			ps.setString(4,neuerRedakteur.getVorname());
			ps.setDouble(5,neuerRedakteur.getGehalt());
			ps.setString(6,neuerRedakteur.getFunktion());
			ps.setInt(7,svnr);
			ps.execute();
			dbCon.commit();
		}
		catch(SQLException e){
			log.fatal("Error - "+e.toString()+"'");
		}
		return 0;
	}
	
	public int delRedakteur(int svnr){
		try{
			getConnection();
			PreparedStatement ps = dbCon.prepareStatement(SQL_DELETE);
			ps.setInt(1,svnr);
			ps.execute();
			dbCon.commit();
			
		}
		catch (SQLException e){
			log.fatal("Error beim erzeugen des SQL statements: "+ e.toString());
		}
		return 0;
	}
	
	public List<Redakteur> getAlleRedakteure(TableColumn orderColumn, boolean ascending){
		List<Redakteur> rds = new ArrayList<Redakteur>();
		try{
			dbCon = this.getConnection();
			
			String query = RECEIVEALL;
			if (!(orderColumn==null)){
				query = query+"ORDER BY " + orderColumn.getName();
				if (ascending)
					query = query + " ASC;";
				else
					query = query + " DESC;";
			}
			
			PreparedStatement ps = dbCon.prepareStatement(query);
			if (!ps.execute()){
				return null;
			}
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				Redakteur red = new Redakteur();
				red.setSvnr(rs.getInt("svnr"));
				red.setVorname(rs.getString("vorname"));
				red.setNachname(rs.getString("nachname"));
				red.setFunktion(rs.getString("funktion"));
				red.setKuerzel(rs.getString("kuerzel"));
				red.setGehalt(rs.getDouble("gehalt"));
				rds.add(red);
			}
		}
		catch (SQLException e){
			log.info("SQLException e=="+e.getMessage());
		}
		return rds;
	}
		


	
}
