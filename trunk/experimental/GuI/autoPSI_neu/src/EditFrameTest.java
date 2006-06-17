

import autopsi.gui.frame.GenericEditFrame;
import autopsi.gui.frame.Kontakt;

import autopsi.gui.component.*;
import autopsi.gui.frame.*;
import autopsi.database.table.Termin;

public class EditFrameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericEditFrame frame = new GenericEditFrame();
		Termin n = new Termin();
		frame.setObjectToEdit(n, true);
		frame.setTableToEdit("Termin");
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
