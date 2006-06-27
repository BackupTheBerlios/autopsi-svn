package autopsi.database.table;

import net.jini.core.entry.Entry;
import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GSMethodForeign;
import autopsi.gui.component.GSMethodPrimary;
import autopsi.gui.component.GenericData;

public class Anhaengen_termin extends GenericData implements Entry,GenericDataObject {
	
	public Integer termin_id;
	public Integer global_id;
	public String table_name;
	
	
	public Anhaengen_termin(){

		Class cl = this.getClass();
		try{
			this.setObjectName("Objekt an Termin anhängen");
			
			GSMethodForeign foreign = new GSMethodForeign();
			foreign.getMethod = cl.getMethod("getTerminId", new Class[] {});
			foreign.setMethod = cl.getMethod("setTerminId", new Class[] {Integer.class} );
			foreign.tableName = "termin";
			foreign.attribName = "id";
			foreign.objectClass = AttachableObjectKategorie.class;
			this.addAttribute("Termin-Id", foreign);
			
			foreign = new GSMethodForeign();
			foreign.getMethod = cl.getMethod("getGlobalId", new Class[] {});
			foreign.setMethod = cl.getMethod("setGlobalId", new Class[] {Integer.class} );
			foreign.tableName = "attachable_object";
			foreign.attribName = "global_id";
			foreign.objectClass = AttachableObjectKategorie.class;
			this.addAttribute("angehängtes Objekt", foreign);
			
			this.addAttribute("Tabellenname",cl.getMethod("getTable_Name", new Class[] {}), cl.getMethod("setTable_Name", new Class[] {String.class} ));
		}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des Anhängen-Termin-Objekts::"+e.toString());
		}
	}
	
	public int getTerminId(){
		return this.termin_id;
	}
	
	public void setTerminId(Integer terminId){
		termin_id = terminId;
	}
	
	public int getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(Integer terminId){
		global_id = terminId;
	}
	
	public String getTable_Name(){
		return table_name;
	}
	
	public void setTable_Name(String name){
		this.table_name = name;
	}
	
	
}
