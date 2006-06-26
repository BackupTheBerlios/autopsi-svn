package autopsi.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Array;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

public class DateEditPlugin extends EditPlugin implements FocusListener {

	
	protected Date value = null;
	protected JPanel panel = null;
	protected JLabel label = null;
	protected JFormattedTextField dateEdit = null;
	
	
	public DateEditPlugin(){
		panel = new JPanel();
		this.panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		this.panel.setAlignmentY(Component.LEFT_ALIGNMENT);
//		this.panel.setBackground(new Color(255,0,0));
		dateEdit = new JFormattedTextField();
		dateEdit.setPreferredSize(new Dimension(100,dateEdit.getPreferredSize().height));
		dateEdit.setDocument(new PlainDocument() {
	    	private static final long serialVersionUID = 8723098029189851737L;
			public void insertString(int offset, String str, AttributeSet a)
					throws BadLocationException {
				// Eingaben von Buchstaben ist nicht erlaubt...
				if (!str.matches(".*[[0-9]|-].*"))
					return;
				//Höchstens 15 Zeichen
				if (offset>9)
					return;
				if (offset==1)
					super.insertString(offset, "-", a);
				if (offset==4)
					super.insertString(offset, "-", a);
					
				super.insertString(offset, str, a);
			}
		});
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
			this.value = (Date)newValue;
		else
			this.value = new Date(System.currentTimeMillis());
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(this.value);
		Integer day = cal.get(cal.DAY_OF_MONTH);
		Integer month = cal.get(cal.MONTH)+1;
		Integer year = cal.get(cal.YEAR);
		String dayS = day.toString();
		String monthS = month.toString();
		String yearS = year.toString();
		if (day < 10)
			dayS = "0" + day;
		if (month < 10)
			monthS = "0" + month;
		
		this.dateEdit.setText(dayS + "-" + monthS + "-" + yearS);
	}

	@Override
	public void nameChanged() {
		this.label.setText(this.name+":");

	}

	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void focusLost(FocusEvent arg0) {
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		
		java.util.Date geburtsdatum = null;
		try {
			geburtsdatum = sf.parse(dateEdit.getText());
			value = new java.sql.Date( geburtsdatum.getTime());
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
	}

}
