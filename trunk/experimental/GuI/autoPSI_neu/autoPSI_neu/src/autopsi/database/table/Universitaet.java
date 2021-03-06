package autopsi.database.table;

import javax.swing.JOptionPane;

import net.jini.core.entry.Entry;

import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GSMethodPrimary;
import autopsi.gui.component.GenericData;

public class Universitaet extends GenericData implements Entry,GenericDataObject {

	public Integer id;
	public String name;

	
	public Universitaet(){
		Class cl = this.getClass();
		try{
			this.setObjectName("Universität");
			
			GSMethodPrimary primary = new GSMethodPrimary();
			primary.getMethod = cl.getMethod("getId", new Class[] {});
			primary.setMethod = cl.getMethod("setId", new Class[] {Integer.class} );
			primary.show = false;
			this.addAttribute("Id", primary);
		
			this.addAttribute("Universitätsname",cl.getMethod("getName", new Class[] {}), cl.getMethod("setName", new Class[] {String.class} ));	}
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
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
}
