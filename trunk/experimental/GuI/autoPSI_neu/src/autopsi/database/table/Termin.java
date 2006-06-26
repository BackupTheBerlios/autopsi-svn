package autopsi.database.table;


import net.jini.core.entry.Entry;
import java.sql.Timestamp;

import javax.swing.JOptionPane;

import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GSMethodForeign;
import autopsi.gui.component.GSMethodPrimary;
import autopsi.gui.component.GenericData;


public class Termin extends GenericData implements Entry,GenericDataObject{

	public Integer id;
	public Integer termin_kategorie_id;
	public Integer termincontainer_id;
	public String secondary_title;
	public String description;
	public Timestamp date;
	public Integer duration;
	public String place;
	public Integer group_id;
	
	
	public Termin(){
		Class cl = this.getClass();
		try{
			this.setObjectName("Termin");
			
			GSMethodPrimary primary = new GSMethodPrimary();
			primary.getMethod = cl.getMethod("getId", new Class[] {});
			primary.setMethod = cl.getMethod("setId", new Class[] {Integer.class} );
			primary.show = false;
			this.addAttribute("Id", primary);
//			this.addAttribute("Id",cl.getMethod("getId", new Class[] {}), cl.getMethod("setId", new Class[] {Integer.class} ));
			
			GSMethodForeign foreign = new GSMethodForeign();
			foreign.getMethod = cl.getMethod("getTerminKategorieId", new Class[] {});
			foreign.setMethod = cl.getMethod("setTerminKategorieId", new Class[] {Integer.class} );
			foreign.tableName = "termin_kategorie";
			foreign.attribName = "id";
			foreign.objectClass = TerminKategorie.class;
			this.addAttribute("Termin-Kategorie", foreign);
//			this.addAttribute("TerminKategorieId",cl.getMethod("getTerminKategorieId", new Class[] {}), cl.getMethod("setTerminKategorieId", new Class[] {Integer.class} ));	
			
			foreign = new GSMethodForeign();
			foreign.getMethod = cl.getMethod("getTerminContainerID", new Class[] {});
			foreign.setMethod = cl.getMethod("setTerminContainerID", new Class[] {Integer.class} );
			foreign.tableName = "termincontainer";
			foreign.attribName = "id";
			foreign.objectClass = TerminContainer.class;
			this.addAttribute("Termin-Container", foreign);
//			this.addAttribute("TermincontainerId",cl.getMethod("getTerminContainerID", new Class[] {}), cl.getMethod("setTerminContainerID", new Class[] {Integer.class} ));
			this.addAttribute("Sekundärer Titel",cl.getMethod("getSecondaryTitle", new Class[] {}), cl.getMethod("setSecondaryTitle", new Class[] {String.class} ));	
			this.addAttribute("Beschreibung",cl.getMethod("getDescription", new Class[] {}), cl.getMethod("setDescription", new Class[] {String.class} ));
			this.addAttribute("Datum",cl.getMethod("getDate", new Class[] {}), cl.getMethod("setDate", new Class[] {Timestamp.class} ));	
			this.addAttribute("Dauer",cl.getMethod("getDuration", new Class[] {}), cl.getMethod("setDuration", new Class[] {Integer.class} ));
			this.addAttribute("Ort",cl.getMethod("getPlace", new Class[] {}), cl.getMethod("setPlace", new Class[] {String.class} ));
			
			foreign = new GSMethodForeign();
			foreign.getMethod = cl.getMethod("getTerminContainerID", new Class[] {});
			foreign.setMethod = cl.getMethod("setTerminContainerID", new Class[] {Integer.class} );
			foreign.tableName = "attachable_object_kategorie";
			foreign.attribName = "id";
			foreign.objectClass = AttachableObjectKategorie.class;
			this.addAttribute("Gruppe", foreign);
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
	
	public Integer getTerminKategorieId(){
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
	
	public int getGroupID(){
		return this.group_id;
	}
	
	public void setGroupID(Integer newGroupId){
		this.group_id = newGroupId;
	}
	
}
