package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;

public class Lehrmittel implements GenericDataObject {

	private int global_id;
	private int kategorie_id;
	private int lehrmittel_kategorie_id;
	private String name;
	private String description;
	private String file_link;
	
	
	public int getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(int globalId){
		this.global_id = globalId;
	}
	
	public int getKategorieId(){
		return this.kategorie_id;
	}
	
	public void setKategorieId(int kategorieId){
		this.kategorie_id = kategorieId;
	}
	
	public int getLehrmittelKategorieId(){
		return this.lehrmittel_kategorie_id;
	}
	
	public void setLehrmittelKategorieId(int lehrmittelKategorieId){
		this.lehrmittel_kategorie_id = lehrmittelKategorieId;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getFileLink(){
		return this.file_link;
	}
	
	public void setFileLink(String fileLink){
		return this.file_link;
	}
	
}
