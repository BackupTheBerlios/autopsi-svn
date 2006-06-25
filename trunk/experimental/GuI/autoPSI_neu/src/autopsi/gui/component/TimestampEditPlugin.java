package autopsi.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class TimestampEditPlugin extends EditPlugin implements FocusListener{

	protected Timestamp value = null;
	protected JPanel panel = null;
	protected JLabel label = null;
	protected JFormattedTextField dateEdit = null;
	
	
	public TimestampEditPlugin(){
		panel = new JPanel();
		this.panel.setAlignmentY(Component.LEFT_ALIGNMENT);
//		this.panel.setBackground(new Color(255,0,0));
		this.panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		dateEdit = new JFormattedTextField(createFormatter("##:##,##-##-####"));
		dateEdit.setPreferredSize(new Dimension(200, dateEdit.getPreferredSize().height));
		label = new JLabel();
		dateEdit.addFocusListener(this);
		panel.add(label);
		panel.add(dateEdit);
	}
	
	@Override
	public Component getEditor() {
		return panel;
	}

	@Override
	public Component getView() {
		return panel;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object newValue) {
		if (newValue != null)
			this.value = (Timestamp)newValue;
		else
			this.value = new Timestamp(System.currentTimeMillis());
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(this.value);
		Integer hour = cal.get(cal.HOUR);
		Integer min = cal.get(cal.MINUTE);
		Integer day = cal.get(cal.DAY_OF_MONTH);
		Integer month = cal.get(cal.MONTH);
		Integer year = cal.get(cal.YEAR);
		String hourS = hour.toString();
		String minS = min.toString();
		String dayS = day.toString();
		String monthS = month.toString();
		String yearS = year.toString();
		if (hour < 10)
			hourS = "0" + hour;
		if (min < 10)
			minS = "0" + min;
		if (day < 10)
			dayS = "0" + day;
		if (month < 10)
			monthS = "0" + month;
		
		
		System.out.println("TimestampEditPlugin.setValue::dateEdit.setText with::"+cal.get(cal.HOUR) + ":" + cal.get(cal.MINUTE)+ "," + cal.get(cal.DAY_OF_MONTH) + "-" + cal.get(cal.MONTH) + "-" + cal.get(cal.YEAR));
		this.dateEdit.setText(hourS + ":" + minS + "," + dayS + "-" + monthS + "-" + yearS);
		
	}

	@Override
	public void nameChanged() {
		this.label.setText(this.name+":");

	}
	
	protected MaskFormatter createFormatter(String s) {
		 MaskFormatter formatter = null;
		 try {
			 formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
		}
		return formatter;
	}

	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void focusLost(FocusEvent arg0) {
		java.util.Date geburtsdatum = null;
		try {
			GregorianCalendar c = null;
			Integer year = Integer.parseInt(dateEdit.getText().substring(12,16));
			System.out.println("year=="+year);
			Integer month = Integer.parseInt(dateEdit.getText().substring(9,11));
			month--;
			System.out.println("month=="+month);
			Integer day = Integer.parseInt(dateEdit.getText().substring(6,8));
			System.out.println("day=="+day);
			Integer hour = Integer.parseInt(dateEdit.getText().substring(3,5));
			System.out.println("hour=="+hour);
			Integer min = Integer.parseInt(dateEdit.getText().substring(0,2));
			System.out.println("min=="+min);
			Integer sec = 0;
			Integer nano = 0;
			c = new GregorianCalendar(year, month, day, hour, min);
			value = new Timestamp(c.getTimeInMillis());
		} catch (Exception e) {
			System.out.println("Konnte Datum nicht ändern::"+e.toString());
		}
	}

}
