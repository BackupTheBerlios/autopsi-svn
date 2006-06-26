package autopsi.database.table;

import javax.swing.JOptionPane;

import net.jini.core.entry.Entry;
import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GSMethodPrimary;
import autopsi.gui.component.GenericData;

public class AttachableObjectKategorie extends GenericData implements Entry,GenericDataObject {
	
	public Integer id;
	public String title;
	public String description;
	public Boolean shareable;
	
	
	public AttachableObjectKategorie(){
		Class cl = this.getClass();
		try{
			this.setObjectName("Anhängbare Objekte - Kategorie");
			
			GSMethodPrimary primary = new GSMethodPrimary();
			primary.getMethod = cl.getMethod("getId", new Class[] {});
			primary.setMethod = cl.getMethod("setId", new Class[] {Integer.class} );
			primary.show = false;
			this.addAttribute("Id", primary);
			
//			this.addAttribute("Id",cl.getMethod("getId", new Class[] {}), cl.getMethod("setId", new Class[] {Integer.class} ));
			this.addAttribute("Titel",cl.getMethod("getTitle", new Class[] {}), cl.getMethod("setTitle", new Class[] {String.class} ));
			this.addAttribute("Beschreibung",cl.getMethod("getDescription", new Class[] {}), cl.getMethod("setDescription", new Class[] {String.class} ));		
			this.addAttribute("Geshared",cl.getMethod("getShareable", new Class[] {}), cl.getMethod("setShareable", new Class[] {Boolean.class} ));	
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
	}
	
	public int getId(){
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
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public Boolean getShareable(){
		return this.shareable;
	}
	
	public void setShareable(Boolean shareable){
		this.shareable = shareable;
	}
	
	
	
}
