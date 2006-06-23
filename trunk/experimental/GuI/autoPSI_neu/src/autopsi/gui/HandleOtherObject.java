package autopsi.gui;

import javax.swing.JFrame;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.table.AttachableObject;
import autopsi.database.table.Kontakt;
import autopsi.database.table.Lehrmittel;
import autopsi.database.table.Lva;
import autopsi.database.table.Notiz;
import autopsi.database.table.Pruefung;
import autopsi.gui.HandleAttachableObject.ObjectAction;
import autopsi.gui.frame.GenericEditFrame;

public class HandleOtherObject {

	protected enum ObjectAction {add, edit, delete};

	protected GenericEditFrame gef;
	protected GenericDAO gdao;
	protected GenericDataObject dbObj;
	protected JFrame owner;
	protected Integer dbObjId;
	protected ObjectAction action;
	
	protected HandleOtherObject(JFrame owner){
		gef = new GenericEditFrame(owner);
		gdao = new GenericDAO();
		this.owner = owner;		
	}
	
	public HandleOtherObject(JFrame owner, GenericDataObject dbObj){
		this(owner);
		this.action = ObjectAction.add;
		this.dbObj = dbObj;
		run();
	}
	
	public HandleOtherObject(JFrame owner, Integer dbObjId){
		this(owner);
		this.action = ObjectAction.edit;
		this.dbObjId = dbObjId;
		run();
	}
	
	public HandleOtherObject(JFrame owner, Integer dbObjId, boolean dummyDelete){
		this(owner);
		this.action = ObjectAction.delete;
		this.dbObjId = dbObjId;
		run();
	}
	
	public void run(){
		
		try{
			if (this.action == ObjectAction.add){
				addNewObject();
			}
			
			if (this.action == ObjectAction.edit){
				editExistingObject();
			}
			
			if (this.action == ObjectAction.delete){
				deleteExistingObject();
			}
		}
		catch (Exception e){
			System.out.println("HandleAttachableObject.run()::Konnte Aktion nicht ausführen::"+e.toString());
		}
		
	}
	
	public void addNewObject(){
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
			System.out.println("newObject");
			gef.setObjectToEdit(neuesObjekt, true);
			gef.setTableToEdit("attachable_object");
			gef.setInsertIntoTable(false);
			this.gdao.setCurrentTable("attachable_object");
//				this.gdao.setDebug(true);
			gef.setVisible(true);
			neuesObjekt = (AttachableObject)gef.getObjectToEdit();
			gdao.addDataObject(neuesObjekt);
			System.out.println("Select * from attack_ where global_id = identity()");
			neuesObjekt = (AttachableObject)gdao.unsafeQuery("select * from attachable_object where global_id=identity()", new AttachableObject()).get(0);
			System.out.println("neuesObjekt.getId()=="+neuesObjekt.getId());		
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
			
			gef.setObjectToEdit(konkretesObjekt, true);
			gef.setVisible(true);
		
		} catch (Exception e){
			System.out.println("HandleAttachableObject.addNewObject::konnte Objekt nicht in DB einfügen::"+e.toString());
		}
	}
	
	public void editExistingObject(){
		
		AttachableObject neuesObjekt = new AttachableObject();
		GenericDataObject konkretesObjekt = null;
		
		try {
			gdao.setCurrentTable("attachable_object");
			neuesObjekt = (AttachableObject)(gdao.unsafeQuery("select * from attachable_object where global_id="+this.dbObjId, new AttachableObject()).get(0));
		} catch (Exception e){
			System.out.println("AddAttachableObject.editExistingObject::Konnte Anhängbares Objekt nicht aus der Datenbank bekommen::"+e.toString());
		}
		
		if (neuesObjekt.getTableName().toLowerCase().equals("notiz")){
			this.dbObj = new Notiz();
			((Notiz)dbObj).setGlobalId(neuesObjekt.getId());
		}
		if (neuesObjekt.getTableName().toLowerCase().equals("kontakt")){
			this.dbObj = new Kontakt();	
			((Kontakt)dbObj).setGlobalId(neuesObjekt.getId());
		}
		if (neuesObjekt.getTableName().toLowerCase().equals("pruefung")){
			this.dbObj = new Pruefung();
			((Pruefung)dbObj).setGlobalId(neuesObjekt.getId());
		}
		if (neuesObjekt.getTableName().toLowerCase().equals("lva")){
			this.dbObj = new Lva();
			((Lva)dbObj).setGlobalId(neuesObjekt.getId());
		}
		if (neuesObjekt.getTableName().toLowerCase().equals("lehrmittel")){
			this.dbObj = new Lehrmittel();
			((Lehrmittel)dbObj).setGlobalId(neuesObjekt.getId());
		}
		
		gef.setTableToEdit("attachable_object");
		gef.setObjectToEdit(neuesObjekt, false);
		gef.setInsertIntoTable(true);
		gef.setVisible(true);
		
		if (neuesObjekt.getTableName().toLowerCase().equals("notiz")){
			this.dbObj = new Notiz();
			((Notiz)dbObj).setGlobalId(neuesObjekt.getId());
			gef.setTableToEdit("notiz");
			gdao.setCurrentTable("notiz");
		}
		if (neuesObjekt.getTableName().toLowerCase().equals("kontakt")){
			this.dbObj = new Kontakt();	
			((Kontakt)dbObj).setGlobalId(neuesObjekt.getId());
			gef.setTableToEdit("kontakt");
			gdao.setCurrentTable("kontakt");
		}
		if (neuesObjekt.getTableName().toLowerCase().equals("pruefung")){
			this.dbObj = new Pruefung();
			((Pruefung)dbObj).setGlobalId(neuesObjekt.getId());
			gef.setTableToEdit("pruefung");
			gdao.setCurrentTable("pruefung");
		}
		if (neuesObjekt.getTableName().toLowerCase().equals("lva")){
			this.dbObj = new Lva();
			((Lva)dbObj).setGlobalId(neuesObjekt.getId());
			gef.setTableToEdit("lva");
			gdao.setCurrentTable("lva");
		}
		if (neuesObjekt.getTableName().toLowerCase().equals("lehrmittel")){
			this.dbObj = new Lehrmittel();
			((Lehrmittel)dbObj).setGlobalId(neuesObjekt.getId());
			gef.setTableToEdit("lehrmittel");
			gdao.setCurrentTable("lehrmittel");
		}
		
		try{
			konkretesObjekt = gdao.getDataObjects(this.dbObj).get(0);
		}
		catch (Exception e){
			System.out.println("AddAttachableObject.editExistingObject()::Konnte Objekt nicht aus Datenbank bekommen::"+e.toString());
		}
		
		gef.setObjectToEdit(konkretesObjekt, false);
		gef.setInsertIntoTable(true);
		gef.setVisible(true);
	}


public void deleteExistingObject(){
	
	AttachableObject neuesObjekt = new AttachableObject();
	GenericDataObject konkretesObjekt = null;
	
	
	try {
		this.gdao.setCurrentTable("attachable_object");
		neuesObjekt = (AttachableObject)(gdao.unsafeQuery("select * from attachable_object where global_id="+this.dbObjId, new AttachableObject()).get(0));
	} catch (Exception e){
		System.out.println("AddAttachableObject.deleteExistingObject::Konnte Anhängbares Objekt nicht aus der Datenbank bekommen::"+e.toString());
	}
	
	if (neuesObjekt.getTableName().toLowerCase().equals("notiz")){
		this.dbObj = new Notiz();
		((Notiz)dbObj).setGlobalId(neuesObjekt.getId());
	}
	if (neuesObjekt.getTableName().toLowerCase().equals("kontakt")){
		this.dbObj = new Kontakt();	
		((Kontakt)dbObj).setGlobalId(neuesObjekt.getId());
	}
	if (neuesObjekt.getTableName().toLowerCase().equals("pruefung")){
		this.dbObj = new Pruefung();
		((Pruefung)dbObj).setGlobalId(neuesObjekt.getId());
	}
	if (neuesObjekt.getTableName().toLowerCase().equals("lva")){
		this.dbObj = new Lva();
		((Lva)dbObj).setGlobalId(neuesObjekt.getId());
	}
	if (neuesObjekt.getTableName().toLowerCase().equals("lehrmittel")){
		this.dbObj = new Lehrmittel();
		((Lehrmittel)dbObj).setGlobalId(neuesObjekt.getId());
	}
	
	gdao.setCurrentTable("attachable_object");

	
	try{
		gdao.delDataObjects(neuesObjekt);
	}
	catch (Exception e){
		System.out.println("AddAttachableObject.deleteExistingObject::Konnte Objekt nicht aus Datenbank bekommen::"+e.toString());
	}
	
	if (neuesObjekt.getTableName().toLowerCase().equals("notiz")){
		this.dbObj = new Notiz();
		((Notiz)dbObj).setGlobalId(neuesObjekt.getId());
		gdao.setCurrentTable("notiz");
	}
	if (neuesObjekt.getTableName().toLowerCase().equals("kontakt")){
		this.dbObj = new Kontakt();	
		((Kontakt)dbObj).setGlobalId(neuesObjekt.getId());
		gdao.setCurrentTable("kontakt");
	}
	if (neuesObjekt.getTableName().toLowerCase().equals("pruefung")){
		this.dbObj = new Pruefung();
		((Pruefung)dbObj).setGlobalId(neuesObjekt.getId());
		gdao.setCurrentTable("pruefung");
	}
	if (neuesObjekt.getTableName().toLowerCase().equals("lva")){
		this.dbObj = new Lva();
		((Lva)dbObj).setGlobalId(neuesObjekt.getId());
		gdao.setCurrentTable("lva");
	}
	if (neuesObjekt.getTableName().toLowerCase().equals("lehrmittel")){
		this.dbObj = new Lehrmittel();
		((Lehrmittel)dbObj).setGlobalId(neuesObjekt.getId());
		gdao.setCurrentTable("lehrmittel");
	}
	
	try{
		gdao.delDataObjects(this.dbObj);
	}
	catch (Exception e){
		System.out.println("AddAttachableObject.deleteExistingObject::Konnte Objekt nicht aus Datenbank bekommen::"+e.toString());
	}
}
	
}
