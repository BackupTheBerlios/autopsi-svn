package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;

public class Notiz implements GenericDataObject {

	
	private int global_id;
	private int kategorie_id;
	private String title;
	private String note;
	
	
	public int getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobId(int globalId){
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
	
	public String getNote(){
		return this.note;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
}
