

import autopsi.gui.frame.GenericEditFrame;
import autopsi.gui.frame.Termin;

import autopsi.gui.component.*;
import autopsi.gui.frame.*;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericEditFrame frame = new GenericEditFrame();
		Notiz n = new Notiz();
		n.setGlobalId(0);
		frame.setObjectToEdit(n, false);
		frame.setTableToEdit("Notiz");
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
