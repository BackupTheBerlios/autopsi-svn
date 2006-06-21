package autopsi.gui;

import javax.swing.table.*;
import javax.swing.*;

import autopsi.database.table.Termin;

import java.util.*;

import java.awt.Color;
import java.awt.Component;
public class WeekRenderer extends WeekDayCell implements TableCellRenderer
{
	Termin [] data;
	DateConverter converter = new DateConverter();
	GregorianCalendar marker=null;;
    public WeekRenderer(GregorianCalendar mark) {
		super();
		// TODO Auto-generated constructor stub
		this.marker = mark;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  
   
    public Object getCellEditorValue()
    {
    	String [] blabla = {"eins","zwei"};
        return blabla;
    }

	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
		if(arg1 instanceof Termin[])
    	{
			
    		data = (Termin[])arg1;
    		
    		WeekDayCell cell = new WeekDayCell();
    		
    		Termin[] liste = new Termin[data.length-1];
    		for (int i = 0;i<liste.length;i++)
    		{
    			liste[i]=data[i+1];
    		}
    	
    		Calendar cal = new GregorianCalendar();
    		cal.set(Integer.parseInt(data[0].getSecondaryTitle().toString().substring(0,4)),Integer.parseInt(data[0].getSecondaryTitle().toString().substring(5,7))-1,Integer.parseInt(data[0].getSecondaryTitle().toString().substring(8,10)));
    		Date dat = new Date(cal.getTimeInMillis());
    		String title = converter.toShort(dat.toString());
    		
    		if(this.marker!=null)
    		{ 		
    			if(this.marker==cal)
        			cell.setBackColor(new Color(253,146,134));
    		}
    		
    		
    		if(title.startsWith("So"))
    			cell.setBackColor(new Color(167,202,254));
    		if(title.startsWith("Sa"))
    			cell.setBackColor(new Color(202,223,254));
    		
    		if(arg4==0) //Spaltenüberschrift
    		{
    			cell.setBackColor(new Color(0,0,0));
    			cell.setDayName(title.substring(0,11)+".");
    		}
    		
    		if(this.marker!=null) //im dateJumper gewähltes Datum wird im Kalender rot hinterlegt
    		{ 	
    			if(this.marker.getTime().toString().substring(0,10).equals(cal.getTime().toString().substring(0,10)))
    			{
    				cell.setBackColor(new Color(255,156,144));	
    				marker=null;
    			}
    		}
    		String[] liste2 = new String[data.length-1];
    		
    		for (int i = 0;i<data.length-1;i++)
    		{
    			liste2[i] = data[i+1].getDate().toString().substring(11,16) + " " + data[i+1].getSecondaryTitle();
    		}
    		cell.fillList(liste2);                 	
    		cell.setVisible(true);
    		
        	return cell;
    	}
		return null;       
	}

	
}