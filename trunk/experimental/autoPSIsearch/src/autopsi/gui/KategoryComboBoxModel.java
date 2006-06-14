package autopsi.gui;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;

import autopsi.database.table.TerminKategorie;
public class KategoryComboBoxModel extends DefaultComboBoxModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 134543543534534L;
	List <GenericDataObject> kategorien;
	
	public KategoryComboBoxModel(String tablename, TerminKategorie kat) {
		super ();
		IGenericDAO gdo=new GenericDAO();
		try {
			this.kategorien = gdo.unsafeQuery("select * from "+tablename, kat);
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

}
