package autopsi.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Array;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class DateEditPlugin extends EditPlugin implements FocusListener {

	
	protected Date value = null;
	protected JPanel panel = null;
	protected JLabel label = null;
	protected JFormattedTextField dateEdit = null;
	
	
	public DateEditPlugin(){
		panel = new JPanel();
		this.panel.setAlignmentY(Component.LEFT_ALIGNMENT);
		this.panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.panel.setBackground(new Color(255,0,0));
		dateEdit = new JFormattedTextField(createFormatter("##-##-####"));
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
			this.value = (Date)newValue;
		else
			this.value = new Date(System.currentTimeMillis());

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
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date geburtsdatum = null;
		try {
			geburtsdatum = sf.parse(dateEdit.getText());
			value = new java.sql.Date( geburtsdatum.getTime());
		} catch (ParseException e) {
			System.out.println("Konnte Datum nicht ändern::"+e.toString());
		}
	}

}
