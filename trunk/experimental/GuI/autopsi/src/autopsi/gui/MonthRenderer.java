package autopsi.gui;

import javax.swing.table.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Component;

public class MonthRenderer extends AbstractCellEditor implements TableCellEditor
{
    private JTextField component = new JTextField();

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int colIndex )
    {
    	System.out.println("Integ Celled");
    	component.setBackground(Color.blue);
        component.setText( value.toString() + " hlhl");
        return component;
    }

    public Object getCellEditorValue()
    {
        return new Integer(component.getText());
    }
}