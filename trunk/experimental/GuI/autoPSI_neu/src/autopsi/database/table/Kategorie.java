package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;

public class Kategorie implements GenericDataObject {
	private Integer id;
	private String title;
	private String description;
	private Boolean shareable;
	
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int Id){
		this.id = Id;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public Boolean getShareable(){
		return this.shareable;
	}
	
	public void setShareable(Boolean shareable){
		this.shareable = shareable;
	}
	
	
	
}
