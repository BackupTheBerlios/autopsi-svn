package autopsi.database.table;

import net.jini.core.entry.Entry;
import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GSMethodPrimary;
import autopsi.gui.component.GenericData;

public class AttachableObject extends GenericData implements Entry,GenericDataObject {

	public Integer global_id;
	public Integer kategorie_id;
	public String table_name;
	
	
	public AttachableObject(){
		Class cl = this.getClass();
		try{
			GSMethodPrimary primary = new GSMethodPrimary();
			primary.getMethod = cl.getMethod("getId", new Class[] {});
			primary.setMethod = cl.getMethod("setId", new Class[] {Integer.class} );
			this.addAttribute("Id", primary);
			this.addAttribute("KategorieId",cl.getMethod("getKategorieId", new Class[] {}), cl.getMethod("setKategorieId", new Class[] {Integer.class} ));
			this.addAttribute("TableName",cl.getMethod("getTableName", new Class[] {}), cl.getMethod("setTableName", new Class[] {String.class} ));		}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des Attachable-Objects::"+e.toString());
		}
	}
	
	public int getId(){
		return this.global_id;
	}
	
	public void setId(Integer id){
		this.global_id = id;
	}
	
	public int getKategorieId(){
		return this.kategorie_id;
	}
	
	public void setKategorieId(Integer kategorieId){
		this.kategorie_id = kategorieId;
	}
	
	public String getTableName(){
		return this.table_name;
	}
	
	public void setTableName(String newTableName){
		this.table_name = newTableName;
	}

}