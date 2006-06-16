package autopsi.gui.frame;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.exception.EAttributeNotFound;
import autopsi.database.exception.EDatabase;
import autopsi.database.exception.EDatabaseConnection;
import autopsi.database.table.Termin;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class EditTerminFrame extends javax.swing.JFrame implements java.awt.event.MouseListener{

	/**	 * 
	 */
	private static final long serialVersionUID = 1L;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JTabbedPane jTabbedPane1;
	private JButton apply_button;
	private JLabel jLabel4;
	private JLabel jLabel8;
	private JTextField duration_field;
	private JFormattedTextField timeField;
	private JFormattedTextField dateField;
	private JLabel jLabel11;
	private JComboBox choose_Type;
	private JLabel jLabel10;
	private JLabel jLabel9;
	private JTextField place_field;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JTextField sec_titlefield;
	private JLabel jLabel5;
	private JLabel jLabel3;
	private JButton jButton6;
	private JButton jButton5;
	private JButton jButton4;
	private JTextField jTextField2;
	private JList jList1;
	private JPanel jPanel1;
	private JTextArea desc_area;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JPanel jPanel3;
	private JButton ok_button;
	private JButton abort_button;
	
	private String dat = "";
	private String time = "";
	private String sec_title = "";
	private String date;
	private int duration;
	private String desc = "";
	private IGenericDAO gdo; 
	private String place;
	private Integer ID;
	private GregorianCalendar c = null;
	private boolean ok = false;
	private GregorianCalendar cal=null;
	
	public EditTerminFrame(GregorianCalendar cal, Integer id) {
		super();
		this.ID = id;
		this.cal = cal;
		gdo = new GenericDAO();
		gdo.setCurrentTable("termin");
		initGUI();
		
		addWindowListener(new WindowAdapter()
				{
				public void windowClosing(WindowEvent arg0)
				{ //wird das Fenster über den X-Button rechts oben geschlossen
				  //wird die Anwendung beendet.
					super.windowClosing(arg0);
					dispose();
					}
				});
	}
	
	private void readData(Integer id) throws EDatabaseConnection, EAttributeNotFound, EDatabase{
		
		
		Termin lookup = new Termin();
		lookup.setId(id);
		List<GenericDataObject> list = null;
		list = gdo.getDataObjects(lookup);
		sec_title = ((Termin)list.get(0)).getSecondaryTitle();
		sec_titlefield.setText(sec_title);
		duration = ((Termin)list.get(0)).getDuration();
		String duration_string = "" + duration;
		duration_field.setText(duration_string);
		desc = ((Termin)list.get(0)).getDescription();
		desc_area.setText(desc);
		place = ((Termin)list.get(0)).getPlace();
		place_field.setText(place);
		date = ((Termin)list.get(0)).getDate().toString().substring(0,10);
		time = ((Termin)list.get(0)).getDate().toString().substring(11);
		
		
		
		
	}
	private void update(){
		try{
			try
			{
				SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date jumpDate = sf.parse(dateField.getText());
				c = new GregorianCalendar();
				c.setTime(jumpDate);
			
			}
			catch(Exception ex)
			{
				showErrorDialog("Falsches Datumsformat","Geben Sie ein Datum im Format TT-MM-JJJJ ein!");
				
			}
			
			sec_title = sec_titlefield.getText();
			date = dateField.getText();
			System.out.println("aha");
			
			Date dat = new Date(c.getTimeInMillis());
			date = dat.toString();
			
			
			date = date.substring(0,10) + " " + timeField.getText()+":00.0";
			System.out.println(date);
			desc = desc_area.getText();
			place = place_field.getText();
			
			try{
			String test = "0123456789";
			boolean testOk = false;
			for(int i = 0;i<duration_field.getText().length();i++)
			{
				for(int k = 0;k<test.length();k++)
				{
					if(duration_field.getText().substring(i,i+1).equals(test.substring(k,k+1)))  testOk = true;
				}
				
			}
			if(testOk) duration = Integer.parseInt(duration_field.getText());
			else throw new Exception();
			
			
			}catch(Exception e){
				showErrorDialog("Falsche Eingabe","Geben Sie eine Dauer ein!");
			}
			
			
			String query="";
			if (ID==null) query = "insert into termin (secondary_title, description, date, duration, place) values ('"+sec_title+"','"+desc+"','"+date+"',"+duration+",'"+place+"')";
			else query = "update termin  set secondary_title='"+sec_title+"', description='"+desc+"', date='"+date+"',duration="+duration+",place='"+place+"' where id="+ID;
			Termin vorlage = new Termin();
			gdo.unsafeQuery(query,vorlage);
			ok = true;
			
		}
		catch (Exception e){
			System.out.println("Exception beim Updaten=="+e.toString());
		}
	}
	private void initGUI() {
		try {
			if(cal!=null) {
				Timestamp datum = new Timestamp(cal.getTimeInMillis());
				dat = datum.toString();
			}
			
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1);
				jTabbedPane1.setBounds(7, 14, 567, 280);
				jTabbedPane1.setTabPlacement(JTabbedPane.LEFT);
				{
					jPanel1 = new JPanel();
					jTabbedPane1.addTab("allgemeine Informationen", null, jPanel1, null);
					jPanel1.setLayout(null);
					jPanel1.setBackground(new java.awt.Color(255, 255, 255));
					{
						jLabel1 = new JLabel();
						jPanel1.add(jLabel1);
						jLabel1.setText("Titel:");
						jLabel1.setBounds(7, 10, 35, 28);
					}
					{
						jLabel2 = new JLabel();
						jPanel1.add(jLabel2);
						jLabel2.setText("Beschreibung:");
						jLabel2.setBounds(7, 182, 84, 21);
					}
					{
						desc_area = new JTextArea();
						jPanel1.add(desc_area);
						desc_area.setBounds(91, 182, 322, 84);
						desc_area.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						desc_area.setLineWrap(true);

					}
					{
						sec_titlefield = new JTextField();
						jPanel1.add(sec_titlefield);
						sec_titlefield.setBounds(91, 42, 322, 21);
						sec_titlefield.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						
					}
					{
						jLabel4 = new JLabel();
						jPanel1.add(jLabel4);
						jLabel4.setText("Sekundär Titel:");
						jLabel4.setBounds(7, 42, 77, 21);
					}
					{
						jLabel6 = new JLabel();
						jPanel1.add(jLabel6);
						jLabel6.setText("Datum:");
						jLabel6.setBounds(7, 77, 56, 21);
					}
					{
						jLabel7 = new JLabel();
						jPanel1.add(jLabel7);
						jLabel7.setText("Dauer: (min)");
						jLabel7.setBounds(303, 77, 63, 21);
					}
					{
						jLabel8 = new JLabel();
						jPanel1.add(jLabel8);
						jLabel8.setBounds(91, 14, 322, 21);
						jLabel8.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					}
					{
						place_field = new JTextField();
						jPanel1.add(place_field);
						place_field.setBounds(91, 112, 63, 21);
						place_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					}
					{
						jLabel9 = new JLabel();
						jPanel1.add(jLabel9);
						jLabel9.setText("Ort:");
						jLabel9.setBounds(7, 112, 63, 21);
					}
					{
						jLabel10 = new JLabel();
						jPanel1.add(jLabel10);
						jLabel10.setText("Uhrzeit:");
						jLabel10.setBounds(203, 77, 42, 21);
					}
					{
						ComboBoxModel choose_TypeModel = new DefaultComboBoxModel(
							new String[] { "-Kein Typ gewählt-", "privat","Prüfung"});
						choose_Type = new JComboBox();
						jPanel1.add(choose_Type);
						choose_Type.setModel(choose_TypeModel);
						choose_Type.setBounds(91, 147, 119, 21);
						choose_Type.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					}
					{
						jLabel11 = new JLabel();
						jPanel1.add(jLabel11);
						jLabel11.setText("Termintyp:");
						jLabel11.setBounds(7, 140, 98, 21);
					}
					{
						dateField = new JFormattedTextField(createFormatter("##-##-####"));
						jPanel1.add(dateField);
						dateField.setBounds(91, 77, 98, 21);
						dateField.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						if(cal!=null) dateField.setText(dat.substring(8,10)+"-"+dat.substring(5,7)+"-"+dat.substring(0,4));
					}
					{
						timeField = new JFormattedTextField(createFormatter("##:##"));
						jPanel1.add(timeField);
						timeField.setBounds(252, 77, 42, 21);
						timeField.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					}
					{
						duration_field = new JTextField();
						jPanel1.add(duration_field);
						duration_field.setBounds(371, 77, 42, 21);
						duration_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					}
				}
				{
					jPanel3 = new JPanel();
					jTabbedPane1.addTab(
						"verknüpfte Objekte",
						null,
						jPanel3,
						null);
					jPanel3.setBackground(new java.awt.Color(255,255,255));
					jPanel3.setLayout(null);
					{
						ListModel jList1Model = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
						jList1 = new JList();
						jPanel3.add(jList1);
						jList1.setModel(jList1Model);
						jList1.setBounds(21, 35, 392, 189);
						jList1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						jList1.setVisibleRowCount(1);
					}
					{
						jTextField2 = new JTextField();
						jPanel3.add(jTextField2);
						jTextField2.setBounds(70, 7, 343, 21);
						jTextField2.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					}
					{
						jButton4 = new JButton();
						jPanel3.add(jButton4);
						jButton4.setText("Hinzufügen");
						jButton4.setBounds(231, 238, 98, 21);
					}
					{
						jButton5 = new JButton();
						jPanel3.add(jButton5);
						jButton5.setText("Löschen");
						jButton5.setBounds(336, 238, 77, 21);
					}
					{
						jButton6 = new JButton();
						jPanel3.add(jButton6);
						jButton6.setText("Öffnen");
						jButton6.setBounds(21, 238, 70, 21);
					}
					{
						jLabel3 = new JLabel();
						jPanel3.add(jLabel3);
						jLabel3.setText("Suchen:");
						jLabel3.setBounds(11, 5, 49, 28);
					}

				}
			}
			{
				abort_button = new JButton();
				getContentPane().add(abort_button);
				abort_button.setText("Abbrechen");
				abort_button.setBounds(294, 301, 105, 21);
				abort_button.addMouseListener(this);
			}
			{
				apply_button = new JButton();
				getContentPane().add(apply_button);
				apply_button.setText("Übernehmen");
				apply_button.setBounds(406, 301, 112, 21);
				apply_button.addMouseListener(this);
			}
			{
				ok_button = new JButton();
				getContentPane().add(ok_button);
				ok_button.setText("OK");
				ok_button.setBounds(525, 301, 49, 21);
				ok_button.addMouseListener(this);
			}
			{
				jLabel5 = new JLabel("- Zeigt Informationen an -");
				this.add(jLabel5);
				jLabel5.setBounds(0, 343, 588, 21);
				jLabel5.setBorder(BorderFactory.createTitledBorder(""));
			}

			this.setSize(617, 401);
			this.setPreferredSize(new java.awt.Dimension(617, 401));
			this.setResizable(false);
			if(ID!=null && cal==null) readData(ID);
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		if(arg0.getSource().equals(abort_button)){
			this.dispose();
		}
		if(arg0.getSource().equals(ok_button)){
			update();
			if(ok)
				dispose();
			
		}
		if(arg0.getSource().equals(apply_button)){
			update();
			}
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource().equals(apply_button)){
			jLabel5.setText("Änderungen übernehmen");
		}
		if(arg0.getSource().equals(abort_button)){
			jLabel5.setText("Abbrechen");
		}
		if(arg0.getSource().equals(ok_button)){
			jLabel5.setText("OK");
		}
	}

	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource().equals(apply_button)){
			jLabel5.setText("");
		}
		if(arg0.getSource().equals(abort_button)){
			jLabel5.setText("");
		}
		if(arg0.getSource().equals(ok_button)){
			jLabel5.setText("");
		}
		
	}
	private void showErrorDialog(String title, String text)
	{
		InfoDialog info = new InfoDialog(this, title,text);
		info.setLocation(this.getLocation().x+200,this.getLocation().y+200);
		info.setModal(true);
		info.setVisible(true);
		
		
		
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

}


