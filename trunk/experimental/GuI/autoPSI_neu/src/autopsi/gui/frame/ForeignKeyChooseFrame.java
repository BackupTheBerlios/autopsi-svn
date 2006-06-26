package autopsi.gui.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import autopsi.basis.model.AttachableTableModel;
import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.table.AttachableObject;
import autopsi.gui.component.GenericData;

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
	protected boolean isOk;
	
	public ForeignKeyChooseFrame(JDialog owner, String tableName, String attribName, Class editedClass){
		super(owner, true);
		this.tableName = tableName;
		this.attribName = attribName;
		this.editedClass = editedClass;
		this.initialize();
	}
	
	public ForeignKeyChooseFrame(JFrame owner, String tableName, String attribName, Class editedClass){
		super(owner, true);
		this.tableName = tableName;
		this.attribName = attribName;
		this.editedClass = editedClass;
		this.initialize();
	}
	
	public void initialize(){
		this.setSize(600,400);
		this.setLayout(new BorderLayout());
		
		GenericData gdPrototype;
		try {
			gdPrototype = (GenericData)editedClass.newInstance();
			this.setTitle(gdPrototype.getObjectName() + " auswählen");
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		
		objectTable = new JTable();	
		objectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
	
	public boolean getOk(){
		return this.isOk;
	}

	public void setOk(boolean newOk){
		this.isOk = newOk;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		this.isOk = true;
		if (arg0.getSource().equals(this.chooseButton)){
			if (this.objectTable.getSelectedRow() != -1){
			
				GenericDataObject obj = this.model.getObject(this.objectTable.getSelectedRow());
				Field fd=null;
				try {
					fd = obj.getClass().getField(this.attribName);
				} catch (Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
				try {
					this.value = fd.get(obj);
				} catch (Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
						}

				this.dispose();
			}
		}
		
	}
	
}
