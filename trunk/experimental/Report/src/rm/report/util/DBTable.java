package rm.report.util;

import java.util.ArrayList;

public class DBTable {
	private String name;
	protected ArrayList<TableColumn> columns = new ArrayList<TableColumn>();
	
	public DBTable(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public TableColumn getColumn(int col){
		return columns.get(col);
	}
	
	public int getColumnCount(){
		return columns.size();
	}
	
	public ArrayList<TableColumn> getColumns(){
		return columns;
	}
	
	public boolean inColumns(TableColumn searched){
		for (int i=0;i<columns.size();i++){
			if (columns.get(i).equals(searched))
				return true;
		}
		return false;
	}
}
