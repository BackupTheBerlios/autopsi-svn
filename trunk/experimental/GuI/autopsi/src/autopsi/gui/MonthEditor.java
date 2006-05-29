package autopsi.gui;

import javax.swing.table.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Component;

public class MonthEditor extends AbstractCellEditor implements TableCellEditor
{
    private JList liste = new JList();
    private DayCell cell = new DayCell(true);
   
    public Object getCellEditorValue()
    {
    	String [] blabla = {"eins","zwei"};
        return blabla;
    }

/*	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
		if(arg1 instanceof String[])
    	{
    		//String[] termine = (String[])value;
    		
    		//for (int i = 0;i<termine.length;i++)
        	//{
        		JLabel item = new JLabel("nummer");
        		liste.add(item);
        	//}
    	}
		//ListModel listTModel = new DefaultComboBoxModel(
			//	new String[] { "Item One", "Item Two","bla","bla","bla","bla","bla" });
		//liste.setModel(listTModel);
    	//liste.setVisible(true);
		//cell.setSize(92,arg0.getRowHeight());
    	
    	//panel.add(label);
    	//panel.add(label2);
    	//panel.add(liste);
    	//panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
  
	}*/

	public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
		cell.setVisible(true);
		return cell;
	}

	
}