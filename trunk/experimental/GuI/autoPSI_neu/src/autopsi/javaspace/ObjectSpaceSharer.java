package autopsi.javaspace;

import java.util.ArrayList;
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
		List<AttachableObjectKategorie> aol = this.getCategories();
	}
	
	private List<AttachableObjectKategorie> getCategories(){
	
		this.gdo.setCurrentTable("attachable_object_kategorie");
		List<GenericDataObject> categories = null;
		try{
			categories = this.gdo.getDataObjects(new AttachableObjectKategorie());
		}
		catch (Exception e){
			System.out.println("ObjectSpaceSharer.shareObjects()::Konnte die zu sharenden Kategorien nicht auslesen::"+e.toString());
		}
		Iterator<GenericDataObject> iter = categories.iterator();
		ArrayList<AttachableObjectKategorie> result = new ArrayList<AttachableObjectKategorie>();
		while(iter.hasNext()){
			AttachableObjectKategorie aok = (AttachableObjectKategorie)iter.next();
			if (aok.getShareable() == true){
				System.out.println("ObjectSpaceSharer.getCategories()::aok.getTitle()=="+aok.getTitle());
				result.add(aok);
			}
		}
		return result;
	}
	
}
