package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;

public class Anhaengen_termin implements GenericDataObject {
	private Integer termin_id;
	private Integer global_id;
	private String table_name;
	
	
	
	public int getTerminId(){
		return this.termin_id;
	}
	
	public void setTerminId(int id){
		termin_id = id;
	}
	
	public int getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(int id){
		global_id = id;
	}
	
	public String getTable_Name(){
		return table_name;
	}
	
	public void setTable_Name(String name){
		this.table_name = name;
	}
	
	
}
