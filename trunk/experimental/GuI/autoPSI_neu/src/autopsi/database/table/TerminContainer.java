package autopsi.database.table;


import javax.swing.JOptionPane;

import net.jini.core.entry.Entry;

import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GSMethodForeign;
import autopsi.gui.component.GSMethodPrimary;
import autopsi.gui.component.GenericData;

public class TerminContainer extends GenericData implements Entry,GenericDataObject{

	public Integer id;
	public Integer group_id;
	public String title;
	public String description;

	
	public TerminContainer(){
		Class cl = this.getClass();
		try{
			this.setObjectName("Termincontainer");
			
			GSMethodPrimary primary = new GSMethodPrimary();
			primary.getMethod = cl.getMethod("getId", new Class[] {});
			primary.setMethod = cl.getMethod("setId", new Class[] {Integer.class} );
			primary.show = false;
			this.addAttribute("Id", primary);
//			this.addAttribute("Id",cl.getMethod("getId", new Class[] {}), cl.getMethod("setId", new Class[] {Integer.class} ));

			GSMethodForeign foreign = new GSMethodForeign();
			foreign.getMethod = cl.getMethod("getGroupID", new Class[] {});
			foreign.setMethod = cl.getMethod("setGroupID", new Class[] {Integer.class} );
			foreign.tableName = "attachable_object_kategorie";
			foreign.attribName = "id";
			foreign.objectClass = AttachableObjectKategorie.class;
			this.addAttribute("Anhängbare Objekt - Kategorie", foreign);
			
			this.addAttribute("Titel",cl.getMethod("getTitle", new Class[] {}), cl.getMethod("setTitle", new Class[] {String.class} ));	
			this.addAttribute("Beschreibung",cl.getMethod("getDescription", new Class[] {}), cl.getMethod("setDescription", new Class[] {String.class} ));
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getGroupID(){
		return this.group_id;
	}
	
	public void setGroupID(Integer newGroupId){
		this.group_id = newGroupId;
	}
}
