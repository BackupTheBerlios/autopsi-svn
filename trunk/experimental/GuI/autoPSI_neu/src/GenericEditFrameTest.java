

import javax.swing.JFrame;

import autopsi.gui.frame.GenericEditFrame;

import autopsi.gui.component.*;
import autopsi.gui.frame.*;
import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.exception.EAttributeNotFound;
import autopsi.database.exception.EDatabase;
import autopsi.database.exception.EDatabaseConnection;
import autopsi.database.table.*;

public class GenericEditFrameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(200, 200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		ForeignKeyChooseFrame fkcf = new ForeignKeyChooseFrame(f, "lva_kategorie", "id", LvaKategorie.class);
		fkcf.setVisible(true);
		
		GenericEditFrame frame = new GenericEditFrame();
		LvaKategorie n = new LvaKategorie();
		n.setId((Integer)(fkcf.getValue()));
		GenericDAO gdao = new GenericDAO();
		try {
			n = (LvaKategorie)gdao.getDataObjects(n);
		} catch (Exception e){
			System.out.println("Konnte Universität nicht aus DB holen::"+e.toString());
		}
		frame.setTableToEdit("lva_kategorie");
		frame.setObjectToEdit(n, false);
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
