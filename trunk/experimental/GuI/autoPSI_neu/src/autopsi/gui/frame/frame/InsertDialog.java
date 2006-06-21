package autopsi.gui.frame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ComboBoxModel;

import autopsi.basis.model.AttachableListModel;
import autopsi.basis.model.AttachableTableModel;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.table.*;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class InsertDialog extends JDialog implements ActionListener {
	private JButton okButton;
	private JButton cancelButton;
	private JComboBox selectedObjectType;
	
	protected JFrame owner;
	private JList objectList;
	
	private Integer selectedId;
	private String selectedTable;
	private boolean isOk;

	public InsertDialog(Frame owner){
		super(owner, true);
		this.setSize(400,400);
		this.setAlwaysOnTop(true);
		getContentPane().setLayout(null);
		{
			okButton = new JButton();
			getContentPane().add(okButton);
			okButton.setText("Ok");
			okButton.setBounds(280, 329, 98, 28);
			okButton.addActionListener(this);
		}
		{
			cancelButton = new JButton();
			getContentPane().add(cancelButton);
			cancelButton.setText("Abbrechen");
			cancelButton.setBounds(175, 329, 98, 28);
			cancelButton.addActionListener(this);
		}
		{
			ComboBoxModel selectedObjectTypeModel = new DefaultComboBoxModel(
				new String[] { "- Bitte wählen Sie einen Typ -","Kontakt", "LVA","Pruefung","Lehrmittel","Notiz" });
			selectedObjectType = new JComboBox();
			getContentPane().add(selectedObjectType);
			selectedObjectType.setModel(selectedObjectTypeModel);
			selectedObjectType.setBounds(21, 14, 350, 28);
			
		}
		{
			AttachableListModel objectListModel = new AttachableListModel();
			objectList = new JList();
			getContentPane().add(objectList);
			objectList.setModel(objectListModel);
			objectList.setBounds(21, 56, 350, 259);
			selectedObjectType.addItemListener(new ItemListener(){

				public void itemStateChanged(ItemEvent arg0) {
					AttachableListModel model = new AttachableListModel();
					model.setTableName(selectedObjectType.getSelectedItem().toString());
					objectList.setModel(model);
				}
				
			});
		}
		this.setVisible(true);

	}
	
	public boolean getIsOk(){
		return this.isOk;
	}
	
	public Integer getAttachableObjectId(){
		return this.selectedId;
	}
	
	public String getAttachableObjectTableName(){
		return this.selectedTable;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(okButton)){
			System.out.println("InsertDialog.actionPerformed(...)::1");
			if(this.objectList.getSelectedIndex() == -1)
				this.selectedId = null;
			else{
				System.out.println("InsertDialog.actionPerformed(...)::2a");
				AttachableListModel lm = (AttachableListModel)this.objectList.getModel();
				GenericDataObject obj = (GenericDataObject)(lm.getObjectAt((objectList.getSelectedIndex())));
//				System.out.println("InsertDialog.actionPerformed(...)::2b");				
				if (obj instanceof Kontakt){
//					System.out.println("InsertDialog.actionPerformed(...)::3Kontakt");
					this.selectedTable = "kontakt";
					this.selectedId = ((Kontakt)obj).getGlobalId();
				}
				if (obj instanceof Notiz){
//					System.out.println("InsertDialog.actionPerformed(...)::3Notiz");
					this.selectedTable = "notiz";
					this.selectedId = ((Notiz)obj).getGlobalId();
				}
				if (obj instanceof Pruefung){
//					System.out.println("InsertDialog.actionPerformed(...)::3Pruefung");
					this.selectedTable = "pruefung";
					this.selectedId = ((Pruefung)obj).getGlobalId();
				}
				if (obj instanceof Lva){
//					System.out.println("InsertDialog.actionPerformed(...)::3Lva");
					this.selectedTable = "lva";
					this.selectedId = ((Lva)obj).getGlobalId();
				}
				if (obj instanceof Lehrmittel){
//					System.out.println("InsertDialog.actionPerformed(...)::3Lehrmittel");
					this.selectedTable = "lehrmittel";
					this.selectedId = ((Lehrmittel)obj).getGlobalId();
				}
			}
			if (selectedId != null){
				this.isOk = true;
				this.dispose();
			}
		}
		if (arg0.getSource().equals(cancelButton)){
			this.isOk = false;
			this.dispose();
		}		
	}
	
	
}
