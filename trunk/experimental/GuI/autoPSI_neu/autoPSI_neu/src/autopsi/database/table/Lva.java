package autopsi.database.table;

import net.jini.core.entry.Entry;
import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GenericData;

public class Lva extends GenericData implements Entry,GenericDataObject {

	
	public Integer global_id;
//	public Integer kategorie_id;
	public String title;
	public Integer type;
	public String description;
	public String lva_nr;
	public Integer uni_id;
	
	
	public Lva(){
		Class cl = this.getClass();
		try{
			this.addAttribute("GlobalId",cl.getMethod("getGlobalId", new Class[] {}), cl.getMethod("setGlobalId", new Class[] {Integer.class} ));
//			this.addAttribute("KategorieId",cl.getMethod("getKategorieId", new Class[] {}), cl.getMethod("setKategorieId", new Class[] {Integer.class} ));
			this.addAttribute("Titel",cl.getMethod("getTitle", new Class[] {}), cl.getMethod("setTitle", new Class[] {String.class} ));
			this.addAttribute("LVA-Typ",cl.getMethod("getType", new Class[] {}), cl.getMethod("setType", new Class[] {Integer.class} ));
			this.addAttribute("Beschreibung",cl.getMethod("getDescription", new Class[] {}), cl.getMethod("setDescription", new Class[] {String.class} ));
			this.addAttribute("LVA-Nr.",cl.getMethod("getLvaNr", new Class[] {}), cl.getMethod("setLvaNr", new Class[] {String.class} ));
			this.addAttribute("Uni-Id",cl.getMethod("getUniId", new Class[] {}), cl.getMethod("setUniId", new Class[] {Integer.class} ));
		}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des LVA-Objekts::"+e.toString());
		}
	}
	
	public Integer getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(Integer globalId){
		this.global_id = globalId;
	}
	
//	public int getKategorieId(){
//		return this.kategorie_id;
//	}
//	
//	public void setKategorieId(Integer kategorieId){
//		this.kategorie_id = kategorieId;
//	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public Integer getType(){
		return this.type;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getLvaNr(){
		return this.lva_nr; 
	}
	
	public void setLvaNr(String lvaNr){
		this.lva_nr = lvaNr;
	}
	
	public Integer getUniId(){
		return this.uni_id;
	}
	
	public void setUniId(Integer uniId){
		this.uni_id = uniId;
	}
	
	
}
