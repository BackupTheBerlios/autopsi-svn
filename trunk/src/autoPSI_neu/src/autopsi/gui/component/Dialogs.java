package autopsi.gui.component;

import javax.swing.JOptionPane;

public class Dialogs {
	public void showErrorDialog (String content, String title) {
		JOptionPane.showMessageDialog(null, content, title, JOptionPane.ERROR_MESSAGE);
		
	}
	
	public void showInfoDialog (String content, String title) {
		JOptionPane.showMessageDialog(null, content, title, JOptionPane.INFORMATION_MESSAGE);
		
	}

}
