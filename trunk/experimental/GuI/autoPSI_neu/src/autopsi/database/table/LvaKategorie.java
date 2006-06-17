package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;

public class LvaKategorie implements GenericDataObject{
	
	private Integer id;
	private String name;
	
	public LvaKategorie(){
	}
	
	
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