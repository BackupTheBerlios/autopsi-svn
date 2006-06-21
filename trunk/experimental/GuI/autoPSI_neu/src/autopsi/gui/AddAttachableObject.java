package autopsi.gui;

import java.util.List;

import javax.swing.JFrame;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.table.*;
import autopsi.gui.frame.GenericEditFrame;

public class AddAttachableObject {

	protected GenericEditFrame gef;
	protected GenericDAO gdao;
	protected GenericDataObject dbObj;
	protected JFrame owner;
	protected boolean newObject;
	protected Integer dbObjId;
	
	protected AddAttachableObject(){
		gef = new GenericEditFrame(owner);
		gdao = new GenericDAO();
		this.owner = owner;		
	}
	public AddAttachableObject(JFrame owner, GenericDataObject dbObj){
		this();
		this.newObject = true;
		this.dbObj = dbObj;
		run();
	}
	
	public AddAttachableObject(JFrame owner, Integer dbObjId){
		this();
		this.newObject = false;
		this.dbObjId = dbObjId;
		run();
	}
	
	public void run(){
		AttachableObject neuesObjekt = new AttachableObject();
		GenericDataObject konkretesObjekt = null;
		
		if (dbObj instanceof Notiz){
			neuesObjekt.setTableName("Notiz");
		}
		if (dbObj instanceof Kontakt){
			neuesObjekt.setTableName("Kontakt");	
		}
		if (dbObj instanceof Pruefung){
			neuesObjekt.setTableName("Pruefung");	
		}
		if (dbObj instanceof Lva){
			neuesObjekt.setTableName("Lva");
		}
		if (dbObj instanceof Lehrmittel){
			neuesObjekt.setTableName("Lehrmittel");
		}
		
		try {
			if(this.newObject){
				System.out.println("newObject");
				gef.setObjectToEdit(neuesObjekt, true);
				gef.setTableToEdit("attachable_object");
				gef.setInsertIntoTable(false);
				this.gdao.setCurrentTable("attachable_object");
				this.gdao.setDebug(true);
				gef.setVisible(true);
				neuesObjekt = (AttachableObject)gef.getObjectToEdit();
				gdao.addDataObject(neuesObjekt);
				System.out.println("Select * from attack_ where global_id = identity()");
				neuesObjekt = (AttachableObject)gdao.unsafeQuery("select * from attachable_object where global_id=identity()", new AttachableObject()).get(0);
				System.out.println("neuesObjekt.getId()=="+neuesObjekt.getId());
			}			
			else{
				neuesObjekt = (AttachableObject)(gdao.unsafeQuery("select * from attachable_object where global_id="+this.dbObjId, new AttachableObject()).get(0));
			}	
			gef = new GenericEditFrame(this.owner);
			
			if (dbObj instanceof Notiz){
				konkretesObjekt = new Notiz();
				gef.setTableToEdit("notiz");
				((Notiz)konkretesObjekt).setGlobalId(neuesObjekt.getId());				
			}
			if (dbObj instanceof Lehrmittel){
				konkretesObjekt = new Lehrmittel();
				gef.setTableToEdit("lehrmittel");
				((Lehrmittel)konkretesObjekt).setGlobalId(neuesObjekt.getId());				
			}
			if (dbObj instanceof Kontakt){
				konkretesObjekt = new Kontakt();
				gef.setTableToEdit("kontakt");
				((Kontakt)konkretesObjekt).setGlobalId(neuesObjekt.getId());				
			}
			if (dbObj instanceof Lva){
				konkretesObjekt = new Lva();
				gef.setTableToEdit("lva");
				((Lva)konkretesObjekt).setGlobalId(neuesObjekt.getId());				
			}
			if (dbObj instanceof Pruefung){
				konkretesObjekt = new Pruefung();
				gef.setTableToEdit("pruefung");
				((Pruefung)konkretesObjekt).setGlobalId(neuesObjekt.getId());				
			}
		
			gef.setObjectToEdit(konkretesObjekt, newObject);
			gef.setVisible(true);
		
		} catch (Exception e){
			System.out.println("mainFrame.actionPerformed()::konnte Objekt nicht in DB einfügen::"+e.toString());
		}
	}
	
}
