package autopsi.gui;

import javax.swing.table.*;
import javax.swing.*;
import java.awt.Component;

public class MonthEditor extends AbstractCellEditor implements TableCellEditor
{
	private static final long serialVersionUID = 1L;
	DateConverter converter = new DateConverter();

	public Object getCellEditorValue()
    {
    	String [] blabla = {"eins","zwei"};
        return blabla;
    }

	public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
		if(arg1 instanceof String[])
    	{
    		String[] termine = (String[])arg1;
    		DayCell cell = new DayCell(false);
    		String title = converter.toShort(termine[0]);
    		cell.setTab(title);
    		if (termine[0].substring(0,2).equals("Sa")||termine[0].substring(0,2).equals("So") )
    				{
    			cell.setBackColor();
    				}
    		
    		cell.setVisible(true);
    		
        	return cell;
    	}
		return null;    
	}

	
}