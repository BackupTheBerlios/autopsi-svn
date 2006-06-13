package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;

public class Lva implements GenericDataObject {

	
	private int global_id;
	private int kategorie_id;
	private String title;
	private String type;
	private String description;
	private String lva_nr;
	private int uni_id;
	
	
	public int getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(int globalId){
		this.global_id = globalId;
	}
	
	public int getKategorieId(){
		return this.kategorie_id;
	}
	
	public void setKategorieId(int kategorieId){
		this.kategorie_id = kategorieId;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(description){
		this.description = description;
	}
	
	public String getLvaNr(){
		return this.lva_nr; 
	}
	
	public void setLvaNr(String lvaNr){
		this.lva_nr = lvaNr;
	}
	
	public int getUniId(){
		return this.uni_id;
	}
	
	public void setUniId(int uniId){
		this.uni_id = uniId;
	}
	
	
}
