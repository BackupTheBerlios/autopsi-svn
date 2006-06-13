package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;

public class Email implements GenericDataObject {

	private Integer id;
	private Integer kontakt_id;
	private String email;
	
	
	public int getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public int getKontaktId(){
		return this.kontakt_id;
	}
	
	public void setKontaktId(Integer kontaktId){
		this.kontakt_id = kontaktId;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
}
