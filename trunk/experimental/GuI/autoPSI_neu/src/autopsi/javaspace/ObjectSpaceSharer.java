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
		Iterator<GenericDataObject> iter = shOb.iterator();
		while(iter.hasNext()){
			GenericDataObject gO = iter.next();
			if (gO!=null)
				com.addObject(gO);
			else
				System.out.println("ObjectSpaceSharer.shareObjects()::gO==null");
		}
	}
	
	private List<GenericDataObject> getSharedObjects(){
		List<GenericDataObject> result = new ArrayList<GenericDataObject>();
		List<AttachableObject> aobs = this.getAttachableObjects();
		
		Iterator<AttachableObject> aobIter = aobs.iterator();
		while(aobIter.hasNext()){
			AttachableObject aob = aobIter.next();
			GenericDataObject lookupObj = null;
			String tableName = aob.getTableName().toLowerCase();
			if (tableName.equals("kontakt")){
				System.out.println("Ist ein Kontakt, globalId=="+aob.getId());
				lookupObj = new Kontakt();
				this.gdo.setCurrentTable("kontakt");
				((Kontakt)lookupObj).setGlobalId(aob.getId());
			}
			if (tableName.equals("notiz")){
				System.out.println("Ist eine Notiz, globalId=="+aob.getId());
				lookupObj = new Notiz();
				this.gdo.setCurrentTable("notiz");
				((Notiz)lookupObj).setGlobalId(aob.getId());
			}
			if (tableName.equals("lva")){
				System.out.println("Ist eine Lva, globalId=="+aob.getId());
				lookupObj = new Lva();
				this.gdo.setCurrentTable("lva");
				((Lva)lookupObj).setGlobalId(aob.getId());
			}
			if (tableName.equals("lehrmittel")){
				System.out.println("Ist ein Lehrmittel, globalId=="+aob.getId());
				lookupObj = new Lehrmittel();
				this.gdo.setCurrentTable("lehrmittel");
				((Lehrmittel)lookupObj).setGlobalId(aob.getId());
			}
			if (tableName.equals("pruefung")){
				System.out.println("Ist eine Prüfung, globalId=="+aob.getId());
				lookupObj = new Pruefung();
				this.gdo.setCurrentTable("pruefung");
				((Pruefung)lookupObj).setGlobalId(aob.getId());
			}
			
			GenericDataObject obj = null;
//			System.out.println("ObjecTSpaceSharer.getSharedObjects()::1");
			try{
//				System.out.println("ObjecTSpaceSharer.getSharedObjects()::1a");
				List<GenericDataObject> l = null;
			/*	if (lookupObj instanceof Kontakt)
					System.out.println("lookupObj instanceof Kontakt, global_id=="+((Kontakt)lookupObj).global_id);
				if (lookupObj instanceof Lva)
					System.out.println("lookupObj instanceof Lva, global_id=="+((Lva)lookupObj).global_id);
				if (lookupObj instanceof Notiz)
					System.out.println("lookupObj instanceof Notiz, global_id=="+((Notiz)lookupObj).global_id);		
				if (lookupObj instanceof Pruefung)
					System.out.println("lookupObj instanceof Pruefung, global_id=="+((Pruefung)lookupObj).global_id);		
				if (lookupObj instanceof Lehrmittel)
					System.out.println("lookupObj instanceof Lehrmittel, global_id=="+((Lehrmittel)lookupObj).global_id);		
			*/
				if (lookupObj == null)
					System.out.println("ObjectSpaceSharer.getSharedObjects()::lookupObj==null");
				l = this.gdo.getDataObjects(lookupObj);
				if (l == null)
					System.out.println("l==null");
				try{
				obj = l.get(0);
				}
				catch(Exception e){
					System.out.println("ObjectSpaceSharer.getSharedObjects()::Kein solchesObjekt vorhanden::"+e.toString());
				}
			}
			catch (Exception e){
				System.out.println("ObjectSpaceSharer.getSharedObjects()::Konnte Objekt nicht aus Tabelle bekommen::"+e.toString());
			}
			if (obj instanceof Kontakt)
				System.out.println("ObjectSpaceSharer.getSharedObjects()::Kontakt.getGlobalId()=="+((Kontakt)obj).getGlobalId());
//			if (obj == null)
//				System.out.println("ObjectSpaceSharer.getSharedObjects()::obj==null");
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
//					System.out.println("ObjectSpaceSharer.getAttachableObjects()::atOb.getGlobalId()=="+atOb.getId());
					result.add(atOb);
				}
			}
		}

		return result;
		
		
		
	}
	
}
