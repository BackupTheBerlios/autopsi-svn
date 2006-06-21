package autopsi.database.table;

import net.jini.core.entry.Entry;
import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GSMethodForeign;
import autopsi.gui.component.GenericData;

public class Notiz extends GenericData implements Entry,GenericDataObject {

	
	public Integer global_id;
//	public Integer kategorie_id;
	public String title;
	public String note;
	
	
	public Notiz(){
		Class cl = this.getClass();
		try{
//			this.addAttribute("GlobalId",cl.getMethod("getGlobalId", new Class[] {}), cl.getMethod("setGlobalId", new Class[] {Integer.class} ));
			GSMethodForeign meth = new GSMethodForeign();
			meth.getMethod = cl.getMethod("getGlobalId", new Class[] {});
			meth.setMethod = cl.getMethod("setGlobalId", new Class[] {Integer.class} );
			meth.tableName = "attachable_object";
			meth.attribName = "global_id";
			meth.objectClass = AttachableObject.class;
			this.addAttribute("GlobalId", meth);
			this.addAttribute("KategorieId", cl.getMethod("getKategorieId", new Class[] {}), cl.getMethod("setKategorieId", new Class[] {Integer.class}));
			this.addAttribute("Titel",cl.getMethod("getTitle", new Class[] {}), cl.getMethod("setTitle", new Class[] {String.class} ));
			this.addAttribute("Notiz",cl.getMethod("getNote", new Class[] {}), cl.getMethod("setNote", new Class[] {String.class} ));

		}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des Notiz-Objekts::"+e.toString());
		}
	}
	
	public int getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(Integer globalId){
		this.global_id = globalId;
	}
	
//	public int getKategorieId(){
//		System.out.println("Notiz.getKategorieId()=="+kategorie_id);
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
	
	public String getNote(){
		return this.note;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
	public void onAdd(){
		System.out.println("Notiz.onAdd()");
		GenericDAO gdao = new GenericDAO();
		gdao.setCurrentTable("attachable_object");

		
	}
	
}
