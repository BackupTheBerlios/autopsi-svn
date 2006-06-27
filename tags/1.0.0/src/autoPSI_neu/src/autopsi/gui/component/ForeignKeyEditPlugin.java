package autopsi.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import autopsi.gui.frame.ForeignKeyChooseFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Plugin that wraps another plugin; used to select the value of a foreign key; is able
 * to lookup to a database table and to show a window in which the user can
 * graphically select which entry he wants to use as value for the current value. 
 * @author Rudolf
 *
 */
public class ForeignKeyEditPlugin extends EditPlugin implements ActionListener {

	
	protected JPanel panel;
	protected EditPlugin edit;
	protected JButton moreButton;
	protected Class editedClass;
	protected String editedTable;
	protected String editedAttrib;
	protected JDialog parentFrame;
	
	
	/**
	 * Initializes a ForeignKeyEditPlugin
	 *
	 */
	public ForeignKeyEditPlugin(){
		this.panel = new JPanel();
		this.panel.setLayout(new FlowLayout(FlowLayout.LEADING));
//		this.panel.setAlignmentY(Component.LEFT_ALIGNMENT);
//		this.panel.setBackground(new Color(255,0,0));
		this.moreButton = new JButton("...");
		this.moreButton.addActionListener(this);
	}
	
	/**
	 * Returns editing view
	 */
	public Component getEditor() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns view for showing-only
	 */
	public Component getView() {
		return this.panel;
	}

	/**
	 * Returns the value of the EditPlugin
	 */
	public Object getValue() {
		// TODO Auto-generated method stub
		return edit.getValue();
	}

	/**
	 * Sets the value of the EditPlugin
	 * @param newValue The new value
	 */
	public void setValue(Object newValue) {
		this.edit.setValue(newValue);
	}

	/**
	 * Sets the wrapped EditPlugin
	 * @param editPlugin The wrapped EditPlugin
	 */
	public void setEditPlugin(EditPlugin editPlugin){
		this.edit = (EditPlugin)editPlugin;
		this.panel.removeAll();
		this.panel.add(edit.getView());
		this.panel.add(this.moreButton);
	}
	
	/**
	 * Returns the wrapped EditPlugin
	 * @return The wrapped EditPlugin
	 */
	public EditPlugin getEditPlugin(){
		return this.edit;
	}
	
	/**
	 * Unused
	 */
	public void nameChanged() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Sets the edited class
	 * @param cl
	 */
	public void setEditedClass(Class cl){
		this.editedClass = cl;
	}
	
	/**
	 * Returns the edited table
	 * @return The Edited table
	 */
	public String getEditedTable(){
		return this.editedTable;
	}
	
	/**
	 * Sets the edited table
	 * @param cl The new table to edit
	 */
	public void setEditedTable(String cl){
		this.editedTable = cl;
	}
	
	/**
	 * Returns the edited attribute
	 * @return The edited attribute
	 */
	public String getEditedAttrib(){
		return this.editedAttrib;
	}
	
	/**
	 * Sets the edited attribute
	 * @param cl The edited attribute
	 */
	public void setEditedAttrib(String cl){
		this.editedAttrib = cl;
	}
	
	/**
	 * Returns the parent frame
	 * @return The parent frame
	 */
	public JDialog getParentFrame(){
		return this.parentFrame;
	}
	
	/**
	 * Sets the parent frame
	 * @param newParentFrame The new parentFrame
	 */
	public void setParentFrame(JDialog newParentFrame){
		this.parentFrame = newParentFrame;
	}
	
	/**
	 * Returns the edited class
	 * @return The edited class. 
	 */
	public Class getEditedClass(){
		return this.editedClass;
	}

	/**
	 * EventHandler that is called if the button "..." is pressed. 
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(this.moreButton)){
			ForeignKeyChooseFrame fkcd = new ForeignKeyChooseFrame(this.parentFrame, this.editedTable, this.editedAttrib, this.editedClass);
//			fkcd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			fkcd.setVisible(true);
			if (fkcd.getOk())
				this.setValue(fkcd.getValue());
		}
		
	}

}
