package autopsi.gui.frame;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import autopsi.gui.component.GenericEditPanel;
import autopsi.gui.component.*;

public class GenericEditFrame extends JFrame {
	
	private GenericEditPanel editPanel=null;
	
	public GenericEditFrame(){
		this.setLayout(new BorderLayout());
		editPanel = new GenericEditPanel();
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
		try{
			editPanel.setObjectToEdit(new TestClass());
		}
		catch (Exception e){
			System.out.println("Exception :: "+e.toString());
		}
	}
	
	public void setObjectToEdit(GenericData obj){
		try{
			editPanel.setObjectToEdit(obj);
		}
		catch (Exception e){
			System.out.println("exception@GenericEditFrame.setObjectToEdit::"+e.toString());
		}
	}
	
}
