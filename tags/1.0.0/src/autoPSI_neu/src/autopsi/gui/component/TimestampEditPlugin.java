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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

/**
 * An EditPlugin used to edit Timestamp-Objects
 * @author Rudolf
 *
 */
public class TimestampEditPlugin extends EditPlugin implements FocusListener{

	protected Timestamp value = null;
	protected JPanel panel = null;
	protected JLabel label = null;
	protected JFormattedTextField dateEdit = null;
	
	/**
	 * Initializes a TimestampEditPlugin
	 *
	 */
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
	
	/**
	 * Returns the panel for editing
	 */
	public Component getEditor() {
		return panel;
	}

	/**
	 * Returns the panel for viewing
	 */
	public Component getView() {
		return panel;
	}

	/**
	 * Returns the EditPlugin' s value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets the EditPlugin' s value
	 */
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
		
		
		this.dateEdit.setText(hourS + ":" + minS + "," + dayS + "-" + monthS + "-" + yearS);
		
	}

	/**
	 * Sets the label if the value' s name has changed
	 */
	public void nameChanged() {
		this.label.setText(this.name+":");

	}
	
	/**
	 * Formats the input
	 * @param s
	 * @return
	 */
	protected MaskFormatter createFormatter(String s) {
		 MaskFormatter formatter = null;
		 try {
			 formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			
		}
		return formatter;
	}
	
	/**
	 * Unused
	 */
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * When the focus lost the EditPlugin' s value will be updated
	 * @param arg0 The FocusEvent
	 */
	public void focusLost(FocusEvent arg0) {
		java.util.Date geburtsdatum = null;
		try {
			GregorianCalendar c = null;
			Integer year = Integer.parseInt(dateEdit.getText().substring(12,16));

			Integer month = Integer.parseInt(dateEdit.getText().substring(9,11));
			month--;

			Integer day = Integer.parseInt(dateEdit.getText().substring(6,8));

			Integer hour = Integer.parseInt(dateEdit.getText().substring(3,5));

			Integer min = Integer.parseInt(dateEdit.getText().substring(0,2));

			Integer sec = 0;
			Integer nano = 0;
			c = new GregorianCalendar(year, month, day, hour, min);
			value = new Timestamp(c.getTimeInMillis());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
	}

}
