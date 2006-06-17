package autopsi.database.table;


import java.sql.Timestamp;
import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GenericData;


public class Termin extends GenericData implements GenericDataObject{

	private Integer id = null;
	private Integer termin_kategorie_id;
	private Integer termincontainer_id;
	private String secondary_title = null;
	private String description = null;
	private Timestamp date = null;
	private Integer duration = null;
	private String place = null;
	
	
	public Termin(){
		Class cl = this.getClass();
		try{
			this.addAttribute("Id",cl.getMethod("getId", new Class[] {}), cl.getMethod("setId", new Class[] {Integer.class} ));
			this.addAttribute("TerminKategorieId",cl.getMethod("getTerminKategorieId", new Class[] {}), cl.getMethod("setTerminKategorieId", new Class[] {Integer.class} ));	
			this.addAttribute("TermincontainerId",cl.getMethod("getTerminContainerID", new Class[] {}), cl.getMethod("setTerminContainerID", new Class[] {Integer.class} ));
			this.addAttribute("Sekundärer Titel",cl.getMethod("getSecondaryTitle", new Class[] {}), cl.getMethod("setSecondaryTitle", new Class[] {String.class} ));	
			this.addAttribute("Beschreibung",cl.getMethod("getDescription", new Class[] {}), cl.getMethod("setDescription", new Class[] {String.class} ));
			this.addAttribute("Datum",cl.getMethod("getDate", new Class[] {}), cl.getMethod("setDate", new Class[] {Timestamp.class} ));	
			this.addAttribute("Dauer",cl.getMethod("getDuration", new Class[] {}), cl.getMethod("setDuration", new Class[] {Integer.class} ));
			this.addAttribute("Ort",cl.getMethod("getPlace", new Class[] {}), cl.getMethod("setPlace", new Class[] {String.class} ));
		}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des Termin-Kategorie-Objekts::"+e.toString());
		}
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public int getTerminKategorieId(){
		return this.termin_kategorie_id;
	}
	
	public void setTerminKategorieId(Integer terminKategorieId){
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
	
	public void setDuration(Integer duration){
		this.duration = duration;
	}
	
	public String getPlace(){
		return this.place;
	}
	
	public void setPlace(String place){
		this.place = place;
	}
	
	public Integer getTerminContainerID(){
		return this.termincontainer_id;
	}
	
	public void setTerminContainerID(Integer terminContainer_id){
		this.termincontainer_id = terminContainer_id;
	}
	
}
