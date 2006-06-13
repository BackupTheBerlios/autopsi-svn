package autopsi.database.table;


import java.sql.Timestamp;
import autopsi.database.dao.GenericDataObject;


public class Termin implements GenericDataObject{

	private Integer id = null;
	private String secondary_title = null;
	private String description = null;
	private Timestamp date = null;
	private Integer duration = null;
	private String place = null;
	
	
	public Integer getId(){
		return this.id;
	}
	
	public void setIt(int id){
		this.id = id;
	}
	
	public String getSecondaryTitle(){
		return this.secondary_title;
	}
	
	public void setSecondaryTitle(String secondaryTitle){
		this.secondary_title = secondaryTitle;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String newDescription){
		this.description = newDescription;
	}
	
	public Timestamp getDate(){
		return this.date;
	}
	
	public void setDate(Timestamp date){
		this.date = date;
	}
	
	public Integer getDuration(){
		return this.duration;
	}
	
	public void setDuration(int duration){
		this.duration = duration;
	}
	
	public String getPlace(String place){
		return this.place;
	}
	
	public void setPlace(String place){
		this.place = place;
	}
	
}
