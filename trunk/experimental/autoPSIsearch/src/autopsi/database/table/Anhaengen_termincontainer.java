package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;

public class Anhaengen_termincontainer implements GenericDataObject {
	private Integer termincontainer_id;
	private Integer global_id;
	private String table_name;
	
	
	
	public int getTermincontainerId(){
		return this.termincontainer_id;
	}
	
	public void setTermincontainerId(int id){
		termincontainer_id = id;
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
