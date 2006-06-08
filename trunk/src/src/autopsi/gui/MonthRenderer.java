package autopsi.gui;

import javax.swing.table.*;
import javax.swing.*;
import java.util.*;

import java.awt.Color;
import java.awt.Component;
public class MonthRenderer extends WeekDayCell implements TableCellRenderer
{
	String [] data;
	DateConverter converter = new DateConverter();
	GregorianCalendar marker=null;;
    public MonthRenderer(GregorianCalendar mark) {
		super();
		// TODO Auto-generated constructor stub
		this.marker = mark;
	}

	private static final long serialVersionUID = 1L;
  
    public Object getCellEditorValue()
    {
        return null;
    }

	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
		if(arg1 instanceof String[])
    	{
			
    		data = (String[])arg1;
    		
    		DayCell cell = new DayCell();
    		
    		String[] liste = new String[data.length-1];
    		for (int i = 0;i<liste.length;i++)
    		{
    			liste[i]=data[i+1];
    		}
    		
    		Calendar cal = new GregorianCalendar();
    		cal.set(Integer.parseInt(data[0].substring(0,4)),Integer.parseInt(data[0].substring(5,7))-1,Integer.parseInt(data[0].substring(8,10)));
    		Date dat = new Date(cal.getTimeInMillis());
    		String title = converter.toShort(dat.toString());
    		cell.setTab(title);
    				
    		if(title.startsWith("So"))
    			cell.setBackColor(new Color(167,202,254));
    		if(title.startsWith("Sa"))
    			cell.setBackColor(new Color(202,223,254));
    		
    		if(this.marker!=null) //im dateJumper gew�hltes Datum wird im Kalender rot hinterlegt
    		{ 	
    			if(this.marker.getTime().toString().substring(0,10).equals(cal.getTime().toString().substring(0,10)))
    			{
    				cell.setBackColor(new Color(255,156,144));	
    				marker=null;
    			}		
    		}
    		
    		cell.fillList(liste);                	
    		cell.setVisible(true);
    		
        	return cell;
    	}
		return null;       
	}

	
}