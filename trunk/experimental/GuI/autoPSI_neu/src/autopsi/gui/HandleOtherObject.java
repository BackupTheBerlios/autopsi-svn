package autopsi.gui;


import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.table.AttachableObject;
import autopsi.database.table.AttachableObjectKategorie;
import autopsi.database.table.Kontakt;
import autopsi.database.table.Lehrmittel;
import autopsi.database.table.LehrmittelKategorie;
import autopsi.database.table.Lva;
import autopsi.database.table.LvaKategorie;
import autopsi.database.table.Notiz;
import autopsi.database.table.Pruefung;
import autopsi.database.table.Universitaet;
import autopsi.gui.HandleAttachableObject.ObjectAction;
import autopsi.gui.frame.GenericEditFrame;

public class HandleOtherObject {

	protected enum ObjectAction {add, edit, delete};

	protected GenericEditFrame gef;
	protected GenericDAO gdao;
	protected GenericDataObject dbObj;
	protected Integer dbObjId;
	protected JFrame owner;
	protected ObjectAction action;
	protected String tableName = "";
	
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
	
	public HandleOtherObject(JFrame owner, Integer dbObjId, String tableName){
		this(owner);
		this.action = ObjectAction.edit;
		this.dbObjId = dbObjId;
		this.tableName = tableName;
		this.getObjectFromId();
		run();
	}
	
	public HandleOtherObject(JFrame owner, Integer dbObjId, String tableName, boolean dummyDelete){
		this(owner);
		this.action = ObjectAction.delete;
		this.dbObjId = dbObjId;
		this.tableName = tableName;
		this.getObjectFromId();
		run();
	}
	
	protected void getObjectFromId(){
		GenericDataObject gesuchtesObjekt = null;
		this.gdao.setCurrentTable(this.tableName);
		if (this.tableName.toLowerCase().equals("universitaet")){
			gesuchtesObjekt = new Universitaet();
			((Universitaet)gesuchtesObjekt).setId(this.dbObjId);
		}
		if (this.tableName.toLowerCase().equals("lva_kategorie")){
			gesuchtesObjekt = new LvaKategorie();
			((LvaKategorie)gesuchtesObjekt).setId(this.dbObjId);
		}
		if (this.tableName.toLowerCase().equals("lehrmittel_kategorie")){
			gesuchtesObjekt = new LehrmittelKategorie();
			((LehrmittelKategorie)gesuchtesObjekt).setId(this.dbObjId);
		}
		if (this.tableName.toLowerCase().equals("attachable_object_kategorie")){
			gesuchtesObjekt = new AttachableObjectKategorie();
			((AttachableObjectKategorie)gesuchtesObjekt).setId(this.dbObjId);
		}
		
		try{
			this.dbObj = this.gdao.getDataObjects(gesuchtesObjekt).get(0);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
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
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		
	}
	
	public void addNewObject(){
		if (dbObj instanceof Universitaet){
			gef.setTableToEdit("universitaet");
		}
		if (dbObj instanceof LvaKategorie){
			gef.setTableToEdit("lva_kategorie");
		}
		if (dbObj instanceof LehrmittelKategorie){
			gef.setTableToEdit("lehrmittel_kategorie");
		}
		if (dbObj instanceof AttachableObjectKategorie){
			gef.setTableToEdit("attachable_object_kategorie");
		}
		
		try {
			gef.setObjectToEdit(this.dbObj, true);
			gef.setInsertIntoTable(true);
			gef.setVisible(true);
		
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
	}
	
	public void editExistingObject(){

		
		gef.setTableToEdit(this.tableName);
		gef.setObjectToEdit(this.dbObj, false);
		gef.setInsertIntoTable(true);
		gef.setVisible(true);
		
	}


public void deleteExistingObject(){

	
	if (this.dbObj instanceof Universitaet){
		List<GenericDataObject> lvas = null;
		this.gdao.setCurrentTable("lva");
		try{
			lvas = this.gdao.getDataObjects(new Lva());
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		Iterator<GenericDataObject> iter = lvas.iterator();
		while(iter.hasNext()){
			Lva l = (Lva)iter.next();
			Lva lookup = new Lva();
			lookup.setGlobalId(l.getGlobalId());
			l.setUniId(0);
			try{
				this.gdao.updDataObjects(lookup, l);
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
		}
	}
	if (this.dbObj instanceof LvaKategorie){
		List<GenericDataObject> lvas = null;
		this.gdao.setCurrentTable("lva");
		try{
			lvas = this.gdao.getDataObjects(new Lva());
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		Iterator<GenericDataObject> iter = lvas.iterator();
		while(iter.hasNext()){
			Lva l = (Lva)iter.next();
			Lva lookup = new Lva();
			lookup.setGlobalId(l.getGlobalId());
			l.setType(0);
			try{
				this.gdao.updDataObjects(lookup, l);
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
		}
	}
	if (this.dbObj instanceof LehrmittelKategorie){
		List<GenericDataObject> lehrmittel = null;
		this.gdao.setCurrentTable("lehrmittel");
		try{
			lehrmittel = this.gdao.getDataObjects(new Lehrmittel());
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		Iterator<GenericDataObject> iter = lehrmittel.iterator();
		while(iter.hasNext()){
			Lehrmittel l = (Lehrmittel)iter.next();
			Lehrmittel lookup = new Lehrmittel();
			lookup.setGlobalId(l.getGlobalId());
			l.setLehrmittelKategorieId(0);
			try{
				this.gdao.updDataObjects(lookup, l);
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
		}
	}
	if (this.dbObj instanceof AttachableObjectKategorie){
		List<GenericDataObject> attachableObjects = null;
		this.gdao.setCurrentTable("attachable_object");
		try{
			attachableObjects = this.gdao.getDataObjects(new AttachableObject());
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
		Iterator<GenericDataObject> iter = attachableObjects.iterator();
		while(iter.hasNext()){
			AttachableObject l = (AttachableObject)iter.next();
			AttachableObject lookup = new AttachableObject();
			lookup.setId(l.getId());
			l.setKategorieId(0);
			try{
				this.gdao.updDataObjects(lookup, l);
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
		}
	}
	
	
	if (this.dbObj instanceof Universitaet){
		this.gdao.setCurrentTable("universitaet");
	}
	if (this.dbObj instanceof LvaKategorie){
		this.gdao.setCurrentTable("lva_kategorie");
	}
	if (this.dbObj instanceof LehrmittelKategorie){
		this.gdao.setCurrentTable("lehrmittel_kategorie");
	}
	if (this.dbObj instanceof AttachableObjectKategorie){
		this.gdao.setCurrentTable("attachable_object_kategorie");
	}


	try{
		this.gdao.delDataObjects(this.dbObj);
	}
	catch (Exception e){
		JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
		}
}
	
}
