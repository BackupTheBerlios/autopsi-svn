package rm.report.gui.table;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.sql.Date;

public class DateCellEditor extends AbstractCellEditor implements TableCellEditor
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
		oldValue = (Date)value;
		String date = oldValue.toString();
		year.setText(date.substring(0,4));
		month.setText(date.substring(5,7));
		day.setText(date.substring(8,10));
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
