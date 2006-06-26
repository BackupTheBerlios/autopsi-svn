package autopsi.javaspace;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.table.AttachableObject;
import autopsi.database.table.AttachableObjectKategorie;
import autopsi.database.table.Kontakt;
import autopsi.database.table.Lehrmittel;
import autopsi.database.table.Lva;
import autopsi.database.table.Notiz;
import autopsi.database.table.Pruefung;
import autopsi.database.table.Termin;
import autopsi.database.table.TerminContainer;
import autopsi.javaspace.exception.EServiceFailure;
import autopsi.javaspace.exception.ESpaceNotFound;

public class ObjectSpaceSharer {

	
	protected ServiceCommunicator com;
	protected GenericDAO gdo;
	
	public ObjectSpaceSharer(){
		com = new ServiceCommunicator();
		gdo = new GenericDAO();
	}
	
	public void setAdress(String adress){
		com.setAdress(adress);
	}
	
	public String getAdress(){
		return com.getAdress();
	}
	
	public void shareObjects(){
		List<GenericDataObject> shOb = this.getSharedObjects();
		Iterator<GenericDataObject> iter = shOb.iterator();
		while(iter.hasNext()){
			GenericDataObject gO = iter.next();
			if (gO!=null)
				try {
					com.addObject(gO);
				} catch (Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
						return;
				}
			
				
		}
	}
	
	public void unshareObjects(){
		com.delAllOwnedObjects();
			
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
				
				lookupObj = new Kontakt();
				this.gdo.setCurrentTable("kontakt");
				((Kontakt)lookupObj).setGlobalId(aob.getId());
			}
			if (tableName.equals("notiz")){
				
				lookupObj = new Notiz();
				this.gdo.setCurrentTable("notiz");
				((Notiz)lookupObj).setGlobalId(aob.getId());
			}
			if (tableName.equals("lva")){
				
				lookupObj = new Lva();
				this.gdo.setCurrentTable("lva");
				((Lva)lookupObj).setGlobalId(aob.getId());
			}
			if (tableName.equals("lehrmittel")){
				
				lookupObj = new Lehrmittel();
				this.gdo.setCurrentTable("lehrmittel");
				((Lehrmittel)lookupObj).setGlobalId(aob.getId());
			}
			if (tableName.equals("pruefung")){
				
				lookupObj = new Pruefung();
				this.gdo.setCurrentTable("pruefung");
				((Pruefung)lookupObj).setGlobalId(aob.getId());
			}
			
			GenericDataObject obj = null;

			try{

				List<GenericDataObject> l = null;

				
				l = this.gdo.getDataObjects(lookupObj);
		
				try{
				obj = l.get(0);
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
			
			result.add(obj);
		}
		
		
		List<GenericDataObject> list = null;

		try{
			this.gdo.setCurrentTable("termincontainer");
			list = this.gdo.getDataObjects(new TerminContainer());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		Iterator<GenericDataObject> iter = list.iterator();
		while(iter.hasNext()){
			GenericDataObject gObj = iter.next();
			result.add(gObj);
		}
		
		
		try{
			this.gdo.setCurrentTable("termin");
			list = this.gdo.getDataObjects(new Termin());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		iter = list.iterator();
		while(iter.hasNext()){
			GenericDataObject gObj = iter.next();
			result.add(gObj);
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
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
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
				}
				Iterator<GenericDataObject> attObIter = aobs.iterator();
				while(attObIter.hasNext()){
					AttachableObject atOb = (AttachableObject)attObIter.next();
					result.add(atOb);
				}
			}
		}

		return result;
		
		
		
	}
	
}
