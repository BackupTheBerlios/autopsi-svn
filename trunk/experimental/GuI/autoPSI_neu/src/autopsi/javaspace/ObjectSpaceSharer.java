package autopsi.javaspace;

import java.util.Iterator;
import java.util.List;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.table.AttachableObjectKategorie;

public class ObjectSpaceSharer {

	
	protected ServiceCommunicator com = null;
	protected GenericDAO gdo = null;
	
	public ObjectSpaceSharer(){
		com = new ServiceCommunicator();
		gdo = new GenericDAO();
	}
	
	public void shareObjects(){
		this.get
	}
	
	private List<GenericDataObject> getCategories(){
	
		this.gdo.setCurrentTable("attachable_object_kategorie");
		List<GenericDataObject> categories = null;
		try{
			categories = this.gdo.getDataObjects(new AttachableObjectKategorie());
		}
		catch (Exception e){
			System.out.println("ObjectSpaceSharer.shareObjects()::Konnte die zu sharenden Kategorien nicht auslesen::"+e.toString());
		}
		Iterator<GenericDataObject> iter = categories.iterator();
	}
	
}
