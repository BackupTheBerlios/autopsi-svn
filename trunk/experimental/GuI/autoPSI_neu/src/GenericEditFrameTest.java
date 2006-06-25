

import java.sql.Timestamp;

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
		GenericEditFrame frame = new GenericEditFrame();
		Termin t = new Termin();
		t.setDate(new Timestamp(System.currentTimeMillis()));
		frame.setTableToEdit("termin");
		frame.setObjectToEdit(t, true);
		frame.setVisible(true);
	}

}
