package autopsi.gui;

/* Diese Klasse wandelt das java-date-Format der Form "Tue May 23 CEST 2006 etc".
 * in ein "lesbares" Format "Dienstag, 23. Mai 2006" um.
 */
public class DateConverter {

	public DateConverter()
	{
		
	}
	
	public String toDay(String datum)
	{
		String day="";
		if(datum.toString().substring(0,3).equalsIgnoreCase("mon")) day= "Montag";
		else if(datum.toString().substring(0,3).equalsIgnoreCase("tue")) day= "Dienstag";
		else if(datum.toString().substring(0,3).equalsIgnoreCase("wed")) day= "Mittwoch";
		else if(datum.toString().substring(0,3).equalsIgnoreCase("thu")) day= "Donnerstag";
		else if(datum.toString().substring(0,3).equalsIgnoreCase("fri")) day= "Freitag";
		else if(datum.toString().substring(0,3).equalsIgnoreCase("sat")) day= "Samstag";
		else if(datum.toString().substring(0,3).equalsIgnoreCase("sun")) day= "Sonntag";
		return day;
	}
	
	public String toMonth(String datum)
	{
		String month="";
		if(datum.toString().contains("Jan")) month = "Jänner";
		else if(datum.toString().contains("Feb")) month = "Februar";
		else if(datum.toString().contains("Mar")) month = "März";
		else if(datum.toString().contains("Apr")) month = "April";
		else if(datum.toString().contains("May")) month = "Mai";
		else if(datum.toString().contains("Jun")) month = "Juni";
		else if(datum.toString().contains("Jul")) month = "Juli";
		else if(datum.toString().contains("Aug")) month = "August";
		else if(datum.toString().contains("Sep")) month = "September";
		else if(datum.toString().contains("Oct")) month = "Oktober";
		else if(datum.toString().contains("Nov")) month = "November";
		else if(datum.toString().contains("Dec")) month = "Dezember";
		
		return month;
	}
	
	public String toYear(String datum)
	{
		String year = datum.toString().substring(datum.toString().length()-4);
		return year;	
	}
	
	public String toShort(String datum)
	{
		String shortD = toDay(datum).substring(0,2) + ", " + datum.substring(8,10) + ". "+ toMonth(datum);
		return shortD;
	}
	
	public String toLong(String datum)
	{
		String longD = toDay(datum) + ", " + datum.substring(8,10) + ". "+ toMonth(datum) + " " + toYear(datum);
		return longD;
	}
}
