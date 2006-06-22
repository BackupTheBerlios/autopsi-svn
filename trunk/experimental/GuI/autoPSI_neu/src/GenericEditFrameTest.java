

import autopsi.gui.frame.GenericEditFrame;

import autopsi.gui.component.*;
import autopsi.gui.frame.*;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.table.*;

public class GenericEditFrameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericEditFrame frame = new GenericEditFrame();
		GenericDataObject n = new Anhaengen_termincontainer();
		frame.setObjectToEdit(n, true);
		frame.setTableToEdit("anhaengen_termincontainer");
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
