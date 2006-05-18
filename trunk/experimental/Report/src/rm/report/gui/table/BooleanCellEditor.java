package rm.report.gui.table;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;

public class BooleanCellEditor extends AbstractCellEditor implements TableCellEditor, ItemListener {

	private JCheckBox box = new JCheckBox();
	private boolean value;
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int colIndex)
	{
		box.addItemListener(this);
		return box;
	}
	
	public Object getCellEditorValue()
	{
		System.out.println("VALUE=="+value);
		return value;
	}
	
	public void itemStateChanged(ItemEvent e){
		if (e.getStateChange() == ItemEvent.SELECTED){
			value = true;
		}
		if (e.getStateChange() == ItemEvent.DESELECTED)
			value = false;
	}
	
}
