package autopsi.database.table;

import net.jini.core.entry.Entry;
import autopsi.database.dao.GenericDataObject;

public class Anhaengen_termincontainer implements Entry,GenericDataObject {
	
	public Integer termincontainer_id;
	public Integer global_id;
	public String table_name;
	
	
	
	public int getTermincontainerId(){
		return this.termincontainer_id;
	}
	
	public void setTermincontainerId(int id){
		termincontainer_id = id;
	}
	
	public int getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(Integer id){
		global_id = id;
	}
	
	public String getTable_Name(){
		return table_name;
	}
	
	public void setTable_Name(String name){
		this.table_name = name;
	}
	
	
}
