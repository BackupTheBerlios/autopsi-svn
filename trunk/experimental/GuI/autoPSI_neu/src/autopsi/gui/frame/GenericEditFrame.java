package autopsi.gui.frame;

import java.awt.BorderLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.gui.component.*;
import autopsi.database.table.Termin;

public class GenericEditFrame extends JDialog implements ActionListener {
	
	private GenericEditPanel editPanel=null;
	protected JButton cancelButton;
//	protected JButton applyButton;
	protected JButton okButton;
	protected boolean canceled = false;
	protected GenericDAO gdao = null;
	protected GenericDataObject lookupObject = null;
	protected JFrame owner;
	protected boolean insertIntoTable = true;
	
	
	public GenericEditFrame(){
		gdao = new GenericDAO();
		gdao.setDebug(true);
		this.setLayout(new BorderLayout());
		editPanel = new GenericEditPanel(this);
		JPanel buttonPanel = new JPanel();
		cancelButton = new JButton("abbrechen");

		okButton = new JButton("ok");
		this.add(editPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(cancelButton);

		buttonPanel.add(okButton);
		this.setSize(400, 600);
		cancelButton.addActionListener(this);
		okButton.addActionListener(this);
		
	}
	
	public GenericEditFrame(JFrame owner){
		super(owner, true);
		gdao = new GenericDAO();
		gdao.setDebug(true);
		this.setLayout(new BorderLayout());
		editPanel = new GenericEditPanel(this);
		JPanel buttonPanel = new JPanel();
		cancelButton = new JButton("abbrechen");

		okButton = new JButton("ok");
		this.add(editPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(cancelButton);

		buttonPanel.add(okButton);
		this.setSize(400, 600);
		cancelButton.addActionListener(this);
		okButton.addActionListener(this);
	}
	
	public void setInsertIntoTable(boolean newInsertIntoTable){
		this.insertIntoTable = newInsertIntoTable;
	}
	
	public boolean getInsertIntoTable(){
		return this.insertIntoTable;
	}
	
	public void setObjectToEdit(GenericDataObject obj, boolean newObject){

		try {
			if(newObject)
				this.setTitle(((GenericData)obj).getObjectName() + " hinzufügen");
			else
				this.setTitle(((GenericData)obj).getObjectName() + " bearbeiten");
				
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		if (newObject){
			try{

				editPanel.setObjectToEdit((GenericData)obj);

				this.lookupObject = null;
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
		}
		else{
			try{
				editPanel.setObjectToEdit((GenericData)obj);
				this.lookupObject = (GenericDataObject)(((GenericData)obj).clone());
				

			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
				}			
	}
	
	this.pack();
}
	
	public GenericData getObjectToEdit(){
		
		GenericData d =editPanel.getEditedObject();
		
		return d;
	}
	
	public void setTableToEdit(String table){
		gdao.setCurrentTable(table);
	}
	
	public String getTableToEdit(){
		return gdao.getCurrentTable();
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
			if (!this.insertIntoTable){
				this.dispose();
				return;
			}
			this.canceled = false;
			if (this.lookupObject == null){
				try{
			
					GenericData gData = (GenericData)editPanel.getEditedObject();
					gData.onAdd();
					GenericDataObject temp = (GenericDataObject)editPanel.getEditedObject();
					gdao.addDataObject(temp);
}
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
						}				
			}
			else{
				try{
					
					
					GenericData gData = (GenericData)editPanel.getEditedObject();
					gData.onUpdate();
					GenericDataObject temp = (GenericDataObject)editPanel.getEditedObject();
					gdao.updDataObjects(this.lookupObject, temp);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
			}
			this.dispose();
		}
		
	}
	
}
