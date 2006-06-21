package autopsi.gui.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import autopsi.basis.model.AttachableTableModel;
import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.table.AttachableObject;

public class ForeignKeyChooseFrame extends JDialog implements ActionListener{

	
	protected GenericDAO gdao;
	
	protected JScrollPane pane;
	protected JTable objectTable;
	protected JButton chooseButton;
	protected AttachableTableModel model;
	
	protected String tableName;
	protected String attribName;
	protected Class editedClass;
	protected Object value;
	
	public ForeignKeyChooseFrame(JDialog owner, String tableName, String attribName, Class editedClass){
		super(owner, true);
		this.tableName = tableName;
		this.attribName = attribName;
		this.editedClass = editedClass;
		this.setSize(600,400);
		this.setLayout(new BorderLayout());
		objectTable = new JTable();	
		model = new AttachableTableModel();
		model.setObjectType(this.tableName, this.attribName, this.editedClass);
		objectTable.setModel(model);
		chooseButton = new JButton("auswählen");
		chooseButton.addActionListener(this);
		this.pane = new JScrollPane(this.objectTable);
		this.add(pane, BorderLayout.CENTER);
		this.add(chooseButton, BorderLayout.SOUTH);
	}
	
	public Object getValue(){
		return this.value;
	}
	
	public void setValue(Object newValue){
		this.value = newValue;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(this.chooseButton)){
			if (this.objectTable.getSelectedRow() != -1){
			
				GenericDataObject obj = this.model.getObject(this.objectTable.getSelectedRow());
				Field fd=null;
				try {
					fd = obj.getClass().getField(this.attribName);
				} catch (Exception e){
					System.out.println("ForeignKeyChooseFrame.actionPerformed()::Exception::"+e.toString());
				}
				try {
					this.value = fd.get(obj);
				} catch (Exception e){
					System.out.println("ForeignKeyChooseFrame.actionPerformed()::Exception::"+e.toString());
				}
	//			System.out.println("value=="+this.value.toString());
				this.dispose();
			}
		}
		
	}
}
