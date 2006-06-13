package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;

public class Universitaet implements GenericDataObject {

	private int id;
	private String name;
	
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
}
