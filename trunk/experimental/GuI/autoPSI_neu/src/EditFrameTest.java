

import autopsi.gui.frame.GenericEditFrame;

import autopsi.gui.component.*;
import autopsi.gui.frame.*;
import autopsi.database.table.*;

public class EditFrameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericEditFrame frame = new GenericEditFrame();
		Notiz n = new Notiz();
		frame.setObjectToEdit(n, true);
		frame.setTableToEdit("Notiz");
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
