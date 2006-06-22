package autopsi.database.table;

import net.jini.core.entry.Entry;
import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GSMethodForeign;
import autopsi.gui.component.GenericData;

public class Anhaengen_termincontainer extends GenericData implements Entry,GenericDataObject {
	
	public Integer termincontainer_id;
	public Integer global_id;
	public String table_name;
	
	
	public Anhaengen_termincontainer(){

		Class cl = this.getClass();
		try{
			this.setObjectName("Objekt an Termincontainer anhängen");
			
			GSMethodForeign foreign = new GSMethodForeign();
			foreign.getMethod = cl.getMethod("getTermincontainerId", new Class[] {});
			foreign.setMethod = cl.getMethod("setTermincontainerId", new Class[] {Integer.class} );
			foreign.tableName = "termincontainer";
			foreign.attribName = "id";
			foreign.objectClass = AttachableObjectKategorie.class;
			this.addAttribute("Termincontainer-Id", foreign);
			
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
	
	
	public Integer getTermincontainerId(){
		return this.termincontainer_id;
	}
	
	public void setTermincontainerId(Integer termincontainerId){
		termincontainer_id = termincontainerId;
	}
	
	public Integer getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(Integer termincontainerId){
		global_id = termincontainerId;
	}
	
	public String getTable_Name(){
		return table_name;
	}
	
	public void setTable_Name(String name){
		this.table_name = name;
	}
	
	
}
