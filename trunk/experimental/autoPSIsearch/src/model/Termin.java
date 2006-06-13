package model;
import autopsi.database.dao.GenericDataObject;
import java.sql.Timestamp;

public class Termin implements GenericDataObject {
	private Integer id=null;
	private String secondary_title=null;
	private String description=null;
	private Timestamp date=null;
	private Integer duration=null;
	
	public void setId(Integer id){
		this.id=id;
	}
	public void setSecondarytitle(String title) {
		this.secondary_title = title;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public void setDate(Timestamp date){
		this.date = date;
	}
	public void setDuration(Integer duration){
		this.duration=duration;
	}
	
	public Integer getId(){
		return this.id;
	}
	public String getSecondarytitle(){
		return this.secondary_title;
	}
	public String getDescription(){
		return this.description;
	}
	public Timestamp getDate(){
		return this.date;
	}
	public Integer getDuration(){
		return this.duration;
	}
	

}
