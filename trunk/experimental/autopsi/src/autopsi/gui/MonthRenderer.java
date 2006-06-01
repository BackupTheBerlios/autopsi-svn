package autopsi.gui;

import javax.swing.table.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
import java.util.*;
public class MonthRenderer extends DayCell implements TableCellRenderer
{
	DateConverter converter = new DateConverter();
	
    public MonthRenderer(boolean scroll) {
		super(scroll);
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList liste = new JList();
    private JPanel panel = new JPanel();
  
   
    public Object getCellEditorValue()
    {
    	String [] blabla = {"eins","zwei"};
        return blabla;
    }

	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
		if(arg1 instanceof String[])
    	{
    		String[] termine = (String[])arg1;
    		DayCell cell = new DayCell(false);
    		String title = converter.toShort(termine[0]);
    		cell.setTab(title);
    		if (title.substring(0,2).equals("Sa")||title.substring(0,2).equals("So") )
    				{
    			cell.setBackColor();
    				}
    		
    		cell.setVisible(true);
    		
        	return cell;
    	}
		return null;       
	}

	
}