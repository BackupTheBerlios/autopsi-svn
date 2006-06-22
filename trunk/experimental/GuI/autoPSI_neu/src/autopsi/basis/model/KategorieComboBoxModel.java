package autopsi.basis.model;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;

import autopsi.database.table.AttachableObjectKategorie;
import autopsi.database.table.LehrmittelKategorie;
import autopsi.database.table.TerminKategorie;
import autopsi.database.table.LvaKategorie;
public class KategorieComboBoxModel extends DefaultComboBoxModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 134543543534534L;
	private List <GenericDataObject> kategorien=null;
	private IGenericDAO gdo=new GenericDAO();
	
	public KategorieComboBoxModel(String tablename, TerminKategorie kat) {
		super ();
		kategorien = null;
		try {
			kategorien = gdo.unsafeQuery("select * from "+tablename, kat);
		} catch (Exception e) {
			System.out.println("KategoryComboBoxModel: "+ e.toString());
		}
		this.addElement("-");
		if (kategorien != null) {
			for (int i = 0; i<kategorien.size();i++){
				TerminKategorie kat1 = (TerminKategorie) kategorien.get(i);
				this.addElement(kat1.getName());
			}
		}
	}
	
	public KategorieComboBoxModel(String tablename, LvaKategorie kat) {
		super ();
		kategorien = null;
		try {
			kategorien = gdo.unsafeQuery("select * from "+tablename, kat);
		} catch (Exception e) {
			System.out.println("KategoryComboBoxModel: "+ e.toString());
		}
		this.addElement("-");
		if (kategorien != null) {
			for (int i = 0; i<kategorien.size();i++){
				LvaKategorie kat1 = (LvaKategorie) kategorien.get(i);
				this.addElement(kat1.getName());
			}
		}
	}
	public KategorieComboBoxModel (String tablename, AttachableObjectKategorie kat) {
		super ();
		kategorien = null;
		try {
			kategorien = gdo.unsafeQuery("select * from "+tablename, kat);
		} catch (Exception e) {
			System.out.println("KategoryComboBoxModel: "+ e.toString());
		}
		this.addElement("-");
		if (kategorien != null) {
			for (int i = 0; i<kategorien.size();i++){
				AttachableObjectKategorie kat1 = (AttachableObjectKategorie) kategorien.get(i);
				this.addElement(kat1.getTitle());
			}
		}
		
	}
	
	public KategorieComboBoxModel (String tablename, LehrmittelKategorie kat) {
		super ();
		kategorien = null;
		try {
			kategorien = gdo.unsafeQuery("select * from "+tablename, kat);
		} catch (Exception e) {
			System.out.println("KategoryComboBoxModel: "+ e.toString());
		}
		this.addElement("-");
		if (kategorien != null) {
			for (int i = 0; i<kategorien.size();i++){
				LehrmittelKategorie kat1 = (LehrmittelKategorie) kategorien.get(i);
				this.addElement(kat1.getTitle());
			}
		}
		
	}

}
