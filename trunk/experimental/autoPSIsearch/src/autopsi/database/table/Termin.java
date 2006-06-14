package autopsi.database.table;


import java.sql.Timestamp;
import autopsi.database.dao.GenericDataObject;


public class Termin implements GenericDataObject{

	private Integer id = null;
	private Integer termin_kategorie_id=null;
	private String secondary_title = null;
	private String description = null;
	private Timestamp date = null;
	private Integer duration = null;
	private String place = null;
	
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getTerminKategorieId(){
		return this.termin_kategorie_id;
	}
	
	public void setTerminKategorieId(int terminKategorieId){
		this.termin_kategorie_id = terminKategorieId;
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
	
	public String getPlace(){
		return this.place;
	}
	
	public void setPlace(String place){
		this.place = place;
	}
	
}
