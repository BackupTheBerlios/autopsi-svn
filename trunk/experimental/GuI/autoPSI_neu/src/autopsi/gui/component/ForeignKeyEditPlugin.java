package autopsi.gui.component;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ForeignKeyEditPlugin extends EditPlugin implements ActionListener {

	
	protected JPanel panel;
	protected EditPlugin edit;
	protected JButton moreButton;
	protected Class editedClass;
	protected String editedTable;
	protected String editedAttrib;
	protected JDialog parentFrame;
	
	
	public ForeignKeyEditPlugin(){
		this.panel = new JPanel();
		this.moreButton = new JButton("...");
		this.moreButton.addActionListener(this);
	}
	
	@Override
	public Component getEditor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getView() {
		return this.panel;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return edit.getValue();
	}

	@Override
	public void setValue(Object newValue) {
		this.edit.setValue(newValue);
	}

	public void setEditPlugin(EditPlugin editPlugin){
		this.edit = (EditPlugin)editPlugin;
		this.panel.removeAll();
		this.panel.add(edit.getView());
		this.panel.add(this.moreButton);
	}
	
	public EditPlugin getEditPlugin(){
		return this.edit;
	}
	
	@Override
	public void nameChanged() {
		// TODO Auto-generated method stub
		
	}
	
	public void setEditedClass(Class cl){
		this.editedClass = cl;
	}
	
	public String getEditedTable(){
		return this.editedTable;
	}
	
	public void setEditedTable(String cl){
		this.editedTable = cl;
	}
	
	public String getEditedAttrib(){
		return this.editedAttrib;
	}
	
	public void setEditedAttrib(String cl){
		this.editedAttrib = cl;
	}
	
	public JDialog getParentFrame(){
		return this.parentFrame;
	}
	
	public void setParentFrame(JDialog newParentFrame){
		this.parentFrame = newParentFrame;
	}
	
	public Class getEditedClass(){
		return this.editedClass;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(this.moreButton)){
			ForeignKeyChooseFrame fkcd = new ForeignKeyChooseFrame(this.parentFrame, this.editedTable, this.editedAttrib, this.editedClass);
//			fkcd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			fkcd.setVisible(true);
			if (fkcd.getValue() == null)
				System.out.println("fkcd==null!!");
			else
				System.out.println("Kein null in sicht");
			this.setValue(fkcd.getValue());
		}
		
	}

}
