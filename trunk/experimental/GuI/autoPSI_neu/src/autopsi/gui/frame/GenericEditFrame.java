package autopsi.gui.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.gui.component.*;
import autopsi.database.table.Termin;

public class GenericEditFrame extends JDialog implements ActionListener {
	
	private GenericEditPanel editPanel=null;
	protected JButton cancelButton;
	protected JButton applyButton;
	protected JButton okButton;
	protected boolean canceled = false;
	protected IGenericDAO gdao = null;
	protected GenericDataObject lookupObject = null;
	
	
	public GenericEditFrame(){
		gdao = new GenericDAO();
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
		/*try{
			editPanel.setObjectToEdit(new TestClass());
		}
		catch (Exception e){
			System.out.println("Exception blablabla :: "+e.toString());
		}*/
	}
	
	public void setObjectToEdit(GenericDataObject obj, boolean newObject){
		if (newObject){
			try{
				editPanel.setObjectToEdit((GenericData)obj);
				this.lookupObject = null;
			}
			catch (Exception e){
				System.out.println("exception@GenericEditFrame.setObjectToEdit::"+e.toString());
			}
		}
		else{
			try{
				editPanel.setObjectToEdit((GenericData)obj);
				this.lookupObject = (GenericDataObject)(((GenericData)obj).clone());
//				System.out.println("lookupObject.note=="+((Notiz)lookupObject).getNote());
			}
			catch (Exception e){
				System.out.println("exception@GenericEditFrame.setObjectToEdit::"+e.toString());
			}			
		}
	}
	
	public GenericData getObjectToEdit(){
		System.out.println("GenericEditFrame::getObjectToEdit");
		GenericData d =editPanel.getEditedObject();
		System.out.println("beinahe nach GenericEditFrame::getObjectToEdit");
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
			this.canceled = false;
			if (this.lookupObject == null){
				try{
					System.out.println("versuche neues objekt einzufügen");
					gdao.addDataObject((GenericDataObject)editPanel.getEditedObject());
//					System.out.println("birthDate=="+((Kontakt)editPanel.getEditedObject()).getBirthDate().toString());
				}
				catch (Exception e){
					System.out.println("Konnte neue Daten nicht in die Tabelle einfügen::"+e.toString());
				}				
			}
			else{
				try{
					System.out.println("versuche objekt upzudaten");
					//System.out.println("updateObject::lookupObject.note=="+((Notiz)lookupObject).getNote());
					gdao.updDataObjects(this.lookupObject, (GenericDataObject)editPanel.getEditedObject());
				}
				catch (Exception e){
					System.out.println("Konnte veränderte Daten nicht in die Tabelle einfügen::"+e.toString());
				}
			}
			this.dispose();
		}
		
	}
	
}
