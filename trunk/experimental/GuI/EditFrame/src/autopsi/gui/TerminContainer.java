package autopsi.gui;


import autopsi.database.dao.GenericDataObject;

public class TerminContainer implements GenericDataObject{

	private Integer id = null;
	private String title = null;
	private String description = null;
	
	public Integer getSetId(boolean set, Integer newId){
		if (set)
			this.id = newId;
		return this.id;
	}
	
	public String getSetTitle(boolean set, String newTitle){
		if (set)
			this.title = newTitle;
		return this.title;
	}
	
	public String getSetDescription(boolean set, String newDesc){
		if (set)
			this.description = newDesc;
		return this.description;
	}
	
	
	
}
