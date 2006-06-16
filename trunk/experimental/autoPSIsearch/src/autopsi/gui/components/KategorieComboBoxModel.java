package autopsi.gui.components;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;

import autopsi.database.table.TerminKategorie;
import autopsi.database.table.LvaKategorie;
public class KategorieComboBoxModel extends DefaultComboBoxModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 134543543534534L;
	
	public KategorieComboBoxModel(String tablename, TerminKategorie kat) {
		super ();
		List <GenericDataObject> kategorien = null;
		IGenericDAO gdo=new GenericDAO();
		try {
			kategorien = gdo.unsafeQuery("select * from "+tablename, kat);
		} catch (Exception e) {
			System.out.println("KategoryComboBoxModel: "+ e.toString());
		}
		
		if (kategorien != null) {
			for (int i = 0; i<kategorien.size();i++){
				TerminKategorie kat1 = (TerminKategorie) kategorien.get(i);
				this.addElement(kat1.getName());
			}
		}
	}
	
	public KategorieComboBoxModel(String tablename, TerminKategorie kat, Boolean search) {
		super ();
		List <GenericDataObject> kategorien = null;
		IGenericDAO gdo=new GenericDAO();
		if (search) {
			this.addElement("-");
		}
		
		try {
			kategorien = gdo.unsafeQuery("select * from "+tablename, kat);
		} catch (Exception e) {
			System.out.println("KategoryComboBoxModel: "+ e.toString());
		}
		
		if (kategorien != null) {
			for (int i = 0; i<kategorien.size();i++){
				TerminKategorie kat1 = (TerminKategorie) kategorien.get(i);
				this.addElement(kat1.getName());
			}
		}
	}
	
	public KategorieComboBoxModel(String tablename, LvaKategorie kat) {
		super ();
		List <GenericDataObject> kategorien=null;
		IGenericDAO gdo=new GenericDAO();
		try {
			kategorien = gdo.unsafeQuery("select * from "+tablename, kat);
		} catch (Exception e) {
			System.out.println("KategoryComboBoxModel: "+ e.toString());
		}
		
		if (kategorien != null) {
			for (int i = 0; i<kategorien.size();i++){
				LvaKategorie kat1 = (LvaKategorie) kategorien.get(i);
				this.addElement(kat1.getName());
			}
		}
	}
	
	public KategorieComboBoxModel(String tablename, LvaKategorie kat, Boolean search) {
		super ();
		List <GenericDataObject> kategorien=null;
		IGenericDAO gdo=new GenericDAO();
		if (search) {
			this.addElement("-");
		}
		try {
			kategorien = gdo.unsafeQuery("select * from "+tablename, kat);
		} catch (Exception e) {
			System.out.println("KategoryComboBoxModel: "+ e.toString());
		}
		
		if (kategorien != null) {
			for (int i = 0; i<kategorien.size();i++){
				LvaKategorie kat1 = (LvaKategorie) kategorien.get(i);
				this.addElement(kat1.getName());
			}
		}
	}

}
