

import autopsi.gui.frame.GenericEditFrame;

import autopsi.gui.component.*;
import autopsi.gui.frame.*;
import autopsi.database.table.*;

public class GenericEditFrameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericEditFrame frame = new GenericEditFrame();
		Kontakt n = new Kontakt();
		frame.setObjectToEdit(n, true);
		frame.setTableToEdit("Kontakt");
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
