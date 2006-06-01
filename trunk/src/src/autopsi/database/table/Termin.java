package autopsi.database.table;


import java.sql.Timestamp;
import autopsi.database.dao.GenericDataObject;


public class Termin implements GenericDataObject{

	private Integer id = null;
	private String secondary_title = null;
	private String description = null;
	private Timestamp date = null;
	private Integer duration = null;
	
	public Integer getSetId(boolean set, Integer newId){
		if (set)
			this.id = newId;
		return this.id;
	}
	
	public String getSetSecondaryTitle(boolean set, String newSecTitle){
		if (set)
			this.secondary_title = newSecTitle;
		return this.secondary_title;
	}
	
	public String getSetDescription(boolean set, String newDescription){
		if (set)
			this.description = newDescription;
		return this.description;
	}
	
	public Timestamp getSetDate(boolean set, Timestamp newDate){
		if (set)
			this.date = newDate;
		return this.date;
	}
	
	public Integer getSetDuration(boolean set, Integer newDuration){
		if (set)
			this.duration = newDuration;
		return this.duration;
	}
	
}
