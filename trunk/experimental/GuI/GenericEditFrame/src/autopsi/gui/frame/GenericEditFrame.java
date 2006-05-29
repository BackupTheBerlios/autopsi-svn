package autopsi.gui.frame;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import autopsi.gui.component.GenericEditPanel;

public class GenericEditFrame extends JFrame {
	
	public GenericEditFrame(){
		this.setLayout(new BorderLayout());
		GenericEditPanel editPanel = new GenericEditPanel();
		JPanel buttonPanel = new JPanel();
		JButton cancelButton = new JButton("abbrechen");
		JButton applyButton = new JButton("anwenden");
		JButton okButton = new JButton("ok");
		this.add(editPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(cancelButton);
		buttonPanel.add(applyButton);
		buttonPanel.add(okButton);
		this.setSize(400, 400);
	}
	
}
