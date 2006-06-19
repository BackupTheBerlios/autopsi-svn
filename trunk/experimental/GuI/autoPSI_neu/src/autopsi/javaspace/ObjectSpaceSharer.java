package autopsi.javaspace;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.table.AttachableObject;
import autopsi.database.table.AttachableObjectKategorie;
import autopsi.database.table.Kontakt;
import autopsi.database.table.Lehrmittel;
import autopsi.database.table.Lva;
import autopsi.database.table.Notiz;
import autopsi.database.table.Pruefung;

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
		List<GenericDataObject> result = new ArrayList<GenericDataObject>();
		List<AttachableObject> aobs = this.getAttachableObjects();
		
		Iterator<AttachableObject> aobIter = aobs.iterator();
		while(aobIter.hasNext()){
			AttachableObject aob = aobIter.next();
			GenericDataObject lookupObj = null;
			String tableName = aob.getTableName().toLowerCase();
			System.out.println("tableName=="+tableName);
			if (tableName == "kontakt"){
				System.out.println("Ist ein Kontakt");
				lookupObj = new Kontakt();
				this.gdo.setCurrentTable("kontakt");
				((Kontakt)lookupObj).setGlobalId(aob.getId());
			}
			if (tableName == "notiz"){
				System.out.println("Ist eine Notiz");
				lookupObj = new Notiz();
				this.gdo.setCurrentTable("notiz");
				((Notiz)lookupObj).setGlobalId(aob.getId());
			}
			if (tableName == "lva"){
				lookupObj = new Lva();
				this.gdo.setCurrentTable("lva");
				((Lva)lookupObj).setGlobalId(aob.getId());
			}
			if (tableName == "lehrmittel"){
				lookupObj = new Lehrmittel();
				this.gdo.setCurrentTable("lehrmittel");
				((Lehrmittel)lookupObj).setGlobalId(aob.getId());
			}
			if (tableName == "pruefung"){
				lookupObj = new Pruefung();
				this.gdo.setCurrentTable("pruefung");
				((Pruefung)lookupObj).setGlobalId(aob.getId());
			}
			
			GenericDataObject obj = null;
			System.out.println("ObjecTSpaceSharer.getSharedObjects()::1");
			try{
				System.out.println("ObjecTSpaceSharer.getSharedObjects()::1a");
				List<GenericDataObject> l = null;
				l = this.gdo.getDataObjects(lookupObj);
				System.out.println("ObjecTSpaceSharer.getSharedObjects()::2");
				if (l == null)
					System.out.println("l==null");
				obj = l.get(0);
			}
			catch (Exception e){
				System.out.println("ObjectSpaceSharer.getSharedObjects()::Konnte Objekt nicht aus Tabelle bekommen::"+e.toString());
			}
			if (obj instanceof Kontakt)
				System.out.println("ObjectSpaceSharer.getSharedObjects()::Kontakt.getGlobalId()=="+((Kontakt)obj).getGlobalId());
			result.add(obj);
		}
		return result;
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
//					System.out.println("ObjectSpaceSharer.getAttachableObjects()::Konnte Objekte einer Kategorie nicht auslesen!::"+e.toString());
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
