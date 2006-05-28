package autopsi.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.sql.Date;

public class DayCellEditor extends AbstractCellEditor implements TableCellEditor
{


	JPanel panel = new JPanel();
	private JTextField year = new JTextField(4);
	private JTextField month = new JTextField(2);
	private JTextField day = new JTextField(2);
	private Date oldValue = null;
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int colIndex )
	{
		panel.add(year);
		panel.add(month);
		panel.add(day);
		
		return panel;
	}
	
    public Object getCellEditorValue()
    {
    	String d = year.getText()+"-"+month.getText()+"-"+day.getText();
    	Date date = new Date(0);
		try{
			date = date.valueOf(d);
			return date;
		}
		catch (IllegalArgumentException e){
			return oldValue;
		}
    }
}