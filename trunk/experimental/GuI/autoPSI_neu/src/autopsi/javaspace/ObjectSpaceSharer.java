package autopsi.javaspace;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.table.AttachableObject;
import autopsi.database.table.AttachableObjectKategorie;

public class ObjectSpaceSharer {

	
	protected ServiceCommunicator com = null;
	protected GenericDAO gdo = null;
	
	public ObjectSpaceSharer(){
		com = new ServiceCommunicator();
		gdo = new GenericDAO();
	}
	
	public void shareObjects(){
		List<GenericDataObject> shOb = this.getSharedObjects();
	}
	
	private List<GenericDataObject> getSharedObjects(){
		List<AttachableObject> aobs = this.getAttachableObjects();
		return null;
	}
	
	private List<AttachableObject> getAttachableObjects(){
	
		this.gdo.setCurrentTable("attachable_object_kategorie");
		List<GenericDataObject> categories = null;
		try{
			categories = this.gdo.getDataObjects(new AttachableObjectKategorie());
		}
		catch (Exception e){
			System.out.println("ObjectSpaceSharer.shareObjects()::Konnte die zu sharenden Kategorien nicht auslesen::"+e.toString());
		}
		Iterator<GenericDataObject> iter = categories.iterator();
		ArrayList<AttachableObject> result = new ArrayList<AttachableObject>();
		while(iter.hasNext()){
			AttachableObjectKategorie aok = (AttachableObjectKategorie)iter.next();
			if (aok.getShareable() == true){
				
				AttachableObject lookupObj = new AttachableObject();
				lookupObj.setKategorieId(aok.getId());
				this.gdo.setCurrentTable("attachable_object");
				List<GenericDataObject> aobs = null;
				try{
					aobs = this.gdo.getDataObjects(lookupObj);
				}
				catch (Exception e){
					System.out.println("ObjectSpaceSharer.getAttachableObjects()::Konnte Objekte einer Kategorie nicht auslesen!::"+e.toString());
				}
				Iterator<GenericDataObject> attObIter = aobs.iterator();
				while(attObIter.hasNext()){
					AttachableObject atOb = (AttachableObject)attObIter.next();
					System.out.println("ObjectSpaceSharer.getAttachableObjects()::atOb.getGlobalId()=="+atOb.getId());
					result.add(atOb);
				}
			}
		}

		return result;
		
		
		
	}
	
}
