package autopsi.gui;

import javax.swing.table.*;
import javax.swing.*;

import java.awt.Component;

public class MonthRenderer extends JLabel implements TableCellRenderer{
//	 This method is called each time a cell in a column
    // using this renderer needs to be rendered.
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel viewPanel = new JPanel();
	JList list = new JList();
	
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
        
    	/*if (value instanceof String[])
    	{
    		String termine = ((String)value);
    		
    		//for(int i = 0;i<termine.length;i++)
    		//{
    			
    		//}
    		
    		}*/
    	JLabel label = new JLabel("blabla");
		list.add(label);
		viewPanel.add(list);
		
    
        if (isSelected) {
            // cell (and perhaps other cells) are selected
        }

        if (hasFocus) {
            // this cell is the anchor and the table has the focus
        }

        // Configure the component with the specified value
 

        // Set tool tip if desired
 

        // Since the renderer is a component, return itself
        return viewPanel;
        //return this;
    }

    // The following methods override the defaults for performance reasons
    public void validate() {}
    public void revalidate() {}
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {}
    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {}
}
