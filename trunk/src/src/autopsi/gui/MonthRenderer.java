package autopsi.gui;

import javax.swing.table.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Component;

public class MonthRenderer extends DayCell implements TableCellRenderer
{

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
    		cell.setTab(termine[0]);
    		if (termine[0].substring(0,2).equals("Sa")||termine[0].substring(0,2).equals("So") )
    				{
    			cell.setBackColor();
    				}
    		cell.setVisible(true);
    		//for (int i = 0;i<termine.length;i++)
        	//{
        		JLabel item = new JLabel("nummer");
        		liste.add(item);
        	//}
        		return cell;
    	}
		return null;
		//ListModel listTModel = new DefaultComboBoxModel(
			//	new String[] { "Item One", "Item Two","bla","bla","bla","bla","bla" });
		//liste.setModel(listTModel);
    	//liste.setVisible(true);
		//cell.setSize(92,arg0.getRowHeight());
    	
    	//panel.add(label);
    	//panel.add(label2);
    	//panel.add(liste);
    	//panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        
	}

	
}