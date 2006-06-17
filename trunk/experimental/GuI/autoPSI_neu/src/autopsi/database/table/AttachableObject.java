package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;

public class AttachableObject extends GenericData implements GenericDataObject {

	
	private Integer id;
	private Integer kategorie_id;
	
	
	public AttachableObject(){
		Class cl = this.getClass();
		try{
			this.addAttribute("Id",cl.getMethod("getId", new Class[] {}), cl.getMethod("setId", new Class[] {Integer.class} ));
			this.addAttribute("KategorieId",cl.getMethod("getKategorieId", new Class[] {}), cl.getMethod("setKategorieId", new Class[] {String.class} ));	}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des Attachable-Objects::"+e.toString());
		}
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getKategorieId(){
		return this.kategorie_id;
	}
	
	public void setKategorieId(int kategorieId){
		this.kategorie_id = kategorieId;
	}

}
