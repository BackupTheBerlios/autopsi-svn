package rm.report.gui.table;


import javax.swing.table.AbstractTableModel;

import rm.report.util.*;
import rm.report.dao.*;
import rm.report.data.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

public class DatabaseTableModel extends AbstractTableModel{

	private static Log log = LogFactory.getLog("rm.report.gui");
	private List<Redakteur> redakteurListe = null;
	private List<Artikel> artikelListe = null;
	private RedakteurDAO redDAO = null; 
	private TableColumn sortColumn;
	private boolean sortAsc = true;
	private ArtikelDAO artDAO = null;
	private int currentTable = Constants.TABLE_ARTIKEL;
	
	public ArrayList<TableColumn> visibleColumns = new ArrayList<TableColumn>();
	private DBTable artikelTable = new ArtikelDBTable();
	private DBTable redakteurTable = new RedakteurDBTable();
	
	
	public DatabaseTableModel(){
		redDAO = new JdbcRedakteurDAO();			
		artDAO = new JdbcArtikelDAO();
		this.setVisibleColumns(Constants.TABLE_ARTIKEL);
		TableColumn temp = artikelTable.getColumn(0);
		sortColumn = new TableColumn(temp.getName(), temp.getEditable(), temp.getOwner());
		updateData();
	}
	
	public void updateData(){
		if (this.getCurrentTable()==Constants.TABLE_REDAKTEUR){
			redakteurListe = redDAO.getAlleRedakteure(sortColumn, sortAsc);
			artikelListe = artDAO.getAlleArtikel(null, sortAsc);
		}
		if (this.getCurrentTable()==Constants.TABLE_ARTIKEL)		{
			artikelListe = artDAO.getAlleArtikel(sortColumn, sortAsc);
			redakteurListe = redDAO.getAlleRedakteure(null, sortAsc);
		}
	}
	
	public void setOrder(TableColumn orderColumn, boolean ascending){
		sortColumn = new TableColumn(orderColumn.getName(),orderColumn.getEditable(), orderColumn.getOwner());
		sortAsc = ascending;
		updateData();
		fireTableDataChanged();
	}

	

	

	
	public int getColumnCount(){
		return visibleColumns.size();
	}
	
	public int getRowCount(){
		if (this.getCurrentTable() == Constants.TABLE_REDAKTEUR)
				return redakteurListe.size();
		if (this.getCurrentTable() == Constants.TABLE_ARTIKEL)
			return artikelListe.size();
		return 0;
	}
	
	
	public Object getValueAt(int row, int col){
		TableColumn tc = visibleColumns.get(col);
		if (tc.getOwner().equals("Redakteur")){
			Redakteur red = redakteurListe.get(row);

			if (tc.getName().equals("Svnr"))
				return red.getSvnr();
			if (tc.getName().equals("Vorname"))
				return red.getVorname();
			if (tc.getName().equals("Nachname"))
				return red.getNachname();
			if (tc.getName().equals("TelNr"))
				return red.getTelNr();
			if (tc.getName().equals("Funktion"))
				return red.getFunktion();
			if (tc.getName().equals("Kuerzel"))
				return red.getKuerzel();
			if (tc.getName().equals("Gehalt"))
				return red.getGehalt();
		}
			

		if (tc.getOwner().equals("Artikel")){
			Artikel art = artikelListe.get(row);
			
			if (tc.getName().equals("Id"))
				return art.getId();
			if (tc.getName().equals("Titel"))
				return art.getTitel();
			if (tc.getName().equals("Untertitel"))
				return art.getUntertitel();
			if (tc.getName().equals("Text"))
				return art.getText();
			if (tc.getName().equals("Datum"))
				return art.getDatum();
			if (tc.getName().equals("Fertig"))
				return art.getFertig();
			if (tc.getName().equals("Thema"))
				return art.getThema();
			if (tc.getName().equals("Place_Of_Creation"))
				return art.getErstellOrt();		
			if (tc.getName().equals("ReporterSvnr"))
				return art.getRedakteurSvnr();
		}
		
		
		return "???";
	}
	
	public void setValueAt(Object value, int row, int col){
		TableColumn tc = visibleColumns.get(col);
		
		if (tc.getOwner().equals("Redakteur")){
			Redakteur red = redakteurListe.get(row);
			
			if (tc.getName().equals("Svnr")){
				red.setSvnr((Integer)value);
			}
			if (tc.getName().equals("Vorname")){
				red.setVorname((String)value);
			}
			if (tc.getName().equals("Nachname")){
				red.setNachname((String)value);
			}
			if (tc.getName().equals("TelNr")){
				red.setTelNr((Integer)value);
			}
			if (tc.getName().equals("Funktion")){
				red.setFunktion((String)value);
			}
			if (tc.getName().equals("Kuerzel")){
				red.setKuerzel((String)value);
			}
			if (tc.getName().equals("Gehalt")){
				red.setGehalt((Double)value);
			}
			
			updateData(red);
		}
		
		if (tc.getOwner().equals("Artikel")){
			Artikel art = artikelListe.get(row);
		
			if (tc.getName().equals("Id"))
				art.setId((Integer)value);
			if (tc.getName().equals("Thema"))
				art.setThema((String)value);
			if (tc.getName().equals("Titel"))
				art.setTitel((String)value);
			if (tc.getName().equals("Untertitel"))
				art.setUntertitel((String)value);	
			if (tc.getName().equals("Place_Of_Creation"))
				art.setErstellOrt((String)value);				
			if (tc.getName().equals("Datum"))
				art.setDate((Date)value);				
			if (tc.getName().equals("Fertig"))
				art.setFertig((Boolean)value);
			if (tc.getName().equals("ReporterSvnr"))
				art.setRedakteurSvnr((Integer)value);		

			updateData(art);
		}
		fireTableCellUpdated(row,col);
	}
	
	public Class getColumnClass(int c){
		return getValueAt(0,c).getClass();
	}
	
	public boolean isCellEditable(int row, int col){
		return this.visibleColumns.get(col).getEditable();
	}
	
	public String getColumnName(int c){
			return visibleColumns.get(c).getName();
	}
	
	public void updateData(Object obj){
		if(obj instanceof Redakteur){
			Redakteur red = (Redakteur)obj;
			redDAO.updateRedakteur(red.getSvnr(),red);
		}
		if (obj instanceof Artikel){
			Artikel art = (Artikel)obj;
			artDAO.updateArtikel(art.getId(),art);
		}
		
		updateData();	
	}
	
	public void deleteData(int[] rows){
		if (getCurrentTable() == Constants.TABLE_REDAKTEUR){
			for (int i=0;i<rows.length;i++){
				log.info("deleting row number"+rows[i]);
				redDAO.delRedakteur(redakteurListe.get(rows[i]).getSvnr());
			}
		}
		if (getCurrentTable() == Constants.TABLE_ARTIKEL){
			for (int i=0;i<rows.length;i++){
				log.info("deleting row number"+rows[i]);
				artDAO.delArtikel(artikelListe.get(rows[i]).getId());
			}
		}
						
		updateData();
	}
	
	public void insertData(){
		if (getCurrentTable() == Constants.TABLE_REDAKTEUR){
			redDAO.addRedakteur(new Redakteur());
		}
		if (getCurrentTable() == Constants.TABLE_ARTIKEL){
			if (this.redakteurListe.size()==0)
				log.fatal("Konnte keine Datensatz hinzufügen, da keine Redakteure vorhanden!");
			else
				artDAO.addArtikel(new Artikel(), this.redakteurListe.get(0).getSvnr());
		}
	
		updateData();
		
	}
	
	public void addColumn(TableColumn tc){
		visibleColumns.add(tc);
		this.fireTableStructureChanged();
	}
	
	public void setVisibleColumns(int table){
		visibleColumns.clear();
		if (table == Constants.TABLE_REDAKTEUR){
			currentTable = table;
			for (int i=0;i<redakteurTable.getColumnCount();i++){
				TableColumn origTc=redakteurTable.getColumn(i);
				TableColumn tc = new TableColumn(origTc.getName(), origTc.getEditable(), origTc.getOwner());
				visibleColumns.add(tc);
			}
			sortColumn = new TableColumn(visibleColumns.get(0).getName(), visibleColumns.get(0).getEditable(), visibleColumns.get(0).getOwner());
		}
		
		if (table == Constants.TABLE_ARTIKEL){
			currentTable = table;
			for (int i=0;i<artikelTable.getColumnCount();i++){
				TableColumn origTc=artikelTable.getColumn(i);
				TableColumn tc = new TableColumn(origTc.getName(), origTc.getEditable(), origTc.getOwner());
				visibleColumns.add(tc);
			}
			sortColumn = new TableColumn(visibleColumns.get(0).getName(), visibleColumns.get(0).getEditable(), visibleColumns.get(0).getOwner());

		}
		this.fireTableStructureChanged();
	}
	
	public int getCurrentTable(){
		return currentTable;
	}
	
	public void setSort(TableColumn tc, boolean ascending){
		this.sortColumn = tc;
		this.sortAsc = ascending;
	}
	
}