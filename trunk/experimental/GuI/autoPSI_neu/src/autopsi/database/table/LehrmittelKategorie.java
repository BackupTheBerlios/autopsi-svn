package autopsi.database.table;

import net.jini.core.entry.Entry;
import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GenericData;

public class LehrmittelKategorie extends GenericData implements Entry,GenericDataObject{
	
	public Integer id;
	public String title;
		
		public LehrmittelKategorie(){
			Class cl = this.getClass();
			try{
				this.setObjectName("LehrmittelKategorie");
				this.addAttribute("Id",cl.getMethod("getId", new Class[] {}), cl.getMethod("setId", new Class[] {Integer.class} ));
				this.addAttribute("Kategoriename",cl.getMethod("getTitle", new Class[] {}), cl.getMethod("setTitle", new Class[] {String.class} ));	}
			catch (Exception e){
				System.out.println("Fehler beim Erstellen des Lehrmittel-Kategorie-Objekts::"+e.toString());
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
}