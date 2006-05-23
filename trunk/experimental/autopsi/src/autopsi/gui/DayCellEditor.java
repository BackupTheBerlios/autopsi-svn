package autopsi.gui;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.sql.Date;

public class DayCellEditor extends AbstractCellEditor implements TableCellEditor
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();

	private JButton button = new JButton("+");
	private Date oldValue = null;
	private JComboBox list = new JComboBox();
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int colIndex )
	{
		
		
		oldValue = (Date)value;
		String date = oldValue.toString();
		
		list.addItem(date);
		panel.add(list);
		panel.add(button);
		return panel;
	}
	
    public Object getCellEditorValue()
    {
    	//String d = year.getText()+"-"+month.getText()+"-"+day.getText();
    	Date date = new Date(0);
		try{
			return date;
		}
		catch (IllegalArgumentException e){
			return oldValue;
		}
    }
}

