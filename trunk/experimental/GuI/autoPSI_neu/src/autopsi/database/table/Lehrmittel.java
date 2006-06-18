package autopsi.database.table;

import net.jini.core.entry.Entry;
import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GenericData;

public class Lehrmittel extends GenericData implements Entry,GenericDataObject {

	public Integer global_id;
	public Integer kategorie_id;
	public Integer lehrmittel_kategorie_id;
	public String name;
	public String description;
	public String file_link;
	
	
	public Lehrmittel(){
		Class cl = this.getClass();
		try{
			this.addAttribute("GlobalId",cl.getMethod("getGlobalId", new Class[] {}), cl.getMethod("setGlobalId", new Class[] {Integer.class} ));
			this.addAttribute("KategorieId",cl.getMethod("getKategorieId", new Class[] {}), cl.getMethod("setKategorieId", new Class[] {Integer.class} ));	
			this.addAttribute("LehrmittelKategorieId",cl.getMethod("getLehrmittelKategorieId", new Class[] {}), cl.getMethod("setLehrmittelKategorieId", new Class[] {Integer.class} ));
			this.addAttribute("Name",cl.getMethod("getName", new Class[] {}), cl.getMethod("setName", new Class[] {String.class} ));
			this.addAttribute("Beschreibung",cl.getMethod("getDescription", new Class[] {}), cl.getMethod("setDescription", new Class[] {String.class} ));
			this.addAttribute("URL/File Link",cl.getMethod("getFileLink", new Class[] {}), cl.getMethod("setFileLink", new Class[] {String.class} ));
		}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des Lehrmittel-Objekts::"+e.toString());
		}
	}
	
	public int getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(Integer globalId){
		this.global_id = globalId;
	}
	
	public int getKategorieId(){
		return this.kategorie_id;
	}
	
	public void setKategorieId(Integer kategorieId){
		this.kategorie_id = kategorieId;
	}
	
	public int getLehrmittelKategorieId(){
		return this.lehrmittel_kategorie_id;
	}
	
	public void setLehrmittelKategorieId(Integer lehrmittelKategorieId){
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
		this.file_link = fileLink;
	}
	
}
