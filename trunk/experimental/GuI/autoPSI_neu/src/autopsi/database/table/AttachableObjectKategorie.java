package autopsi.database.table;

import net.jini.core.entry.Entry;
import autopsi.database.dao.GenericDataObject;

public class AttachableObjectKategorie implements Entry,GenericDataObject {
	
	public Integer id;
	public String title;
	public String description;
	public Boolean shareable;
	
	
	public int getId(){
		return this.id;
	}
	
	public void setId(Integer Id){
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
