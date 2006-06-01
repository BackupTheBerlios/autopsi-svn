package autopsi.gui;

import java.util.*;
public class DateConverter {

	public DateConverter()
	{
		
	}
	
	public String toDay(Date datum)
	{
		String day="";
		if(datum.toString().substring(0,3).equalsIgnoreCase("mon")) day= "Montag";
		if(datum.toString().substring(0,3).equalsIgnoreCase("tue")) day= "Dienstag";
			if(datum.toString().substring(0,3).equalsIgnoreCase("wed")) day= "Mittwoch";
		if(datum.toString().substring(0,3).equalsIgnoreCase("thu")) day= "Donnerstag";
		if(datum.toString().substring(0,3).equalsIgnoreCase("fri")) day= "Freitag";
		if(datum.toString().substring(0,3).equalsIgnoreCase("sat")) day= "Samstag";
		if(datum.toString().substring(0,3).equalsIgnoreCase("sun")) day= "Sonntag";
		return day;
	}
	
	public String toMonth(Date datum)
	{
		String month="";
		if(datum.toString().contains("Jan")) month = "Jänner";
		if(datum.toString().contains("Feb")) month = "Februar";
		if(datum.toString().contains("Mar")) month = "März";
		if(datum.toString().contains("Apr")) month = "April";
		if(datum.toString().contains("May")) month = "Mai";
		if(datum.toString().contains("Jun")) month = "Juni";
		if(datum.toString().contains("Jul")) month = "Juli";
		if(datum.toString().contains("Aug")) month = "August";
		if(datum.toString().contains("Sep")) month = "September";
		if(datum.toString().contains("Oct")) month = "Oktober";
		if(datum.toString().contains("Nov")) month = "November";
		if(datum.toString().contains("Dec")) month = "Dezember";
		
		return month;
	}
}
