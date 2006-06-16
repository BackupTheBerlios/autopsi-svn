package autopsi.gui.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import autopsi.gui.component.*;

public class GenericEditFrame extends JDialog implements ActionListener {
	
	private GenericEditPanel editPanel=null;
	protected JButton cancelButton;
	protected JButton applyButton;
	protected JButton okButton;
	protected boolean canceled = false;
	
	
	public GenericEditFrame(){
		this.setLayout(new BorderLayout());
		editPanel = new GenericEditPanel();
		JPanel buttonPanel = new JPanel();
		cancelButton = new JButton("abbrechen");
		applyButton = new JButton("anwenden");
		okButton = new JButton("ok");
		this.add(editPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(cancelButton);
		buttonPanel.add(applyButton);
		buttonPanel.add(okButton);
		this.setSize(400, 400);
		cancelButton.addActionListener(this);
		okButton.addActionListener(this);
		try{
			editPanel.setObjectToEdit(new TestClass());
		}
		catch (Exception e){
			System.out.println("Exception blablabla :: "+e.toString());
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
	
	public GenericData getObjectToEdit(){
		System.out.println("GenericEditFrame::getObjectToEdit");
		GenericData d =editPanel.getEditedObject();
		System.out.println("beinahe nach GenericEditFrame::getObjectToEdit");
		return d;
	}
	
	public boolean getCanceled(){
		return this.canceled;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(cancelButton)){
			this.canceled = true;
			this.dispose();
		}
		if (arg0.getSource().equals(okButton)){
			this.canceled = false;
			TestClass c = (TestClass)this.getObjectToEdit();
			System.out.println("name=="+c.getTest1().toString());
		}
		
	}
	
}
