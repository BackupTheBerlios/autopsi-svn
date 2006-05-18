package rm.report.gui.table;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;


public class IntegerCellEditor extends AbstractCellEditor implements TableCellEditor
{
    private JTextField component = new JTextField();

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int colIndex )
    {
    	System.out.println("Integ Celled");
        component.setText( value.toString() );
        return component;
    }

    public Object getCellEditorValue()
    {
        return new Integer(component.getText());
    }
}
