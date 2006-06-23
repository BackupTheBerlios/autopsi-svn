package autopsi.gui.frame;
import java.util.List;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import autopsi.basis.AutopsiConfigurator;
import autopsi.database.table.Termin;
import autopsi.database.table.TerminKategorie;
import autopsi.database.dao.*;

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
public class TerminReiheFrame extends javax.swing.JFrame implements java.awt.event.MouseListener, MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6652693923046118447L;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JCheckBox check1;
	private JLabel jLabel2;
	private JFormattedTextField endDate_field;
	private JLabel jLabel3;
	private JFormattedTextField beginDate_field;
	private JLabel jLabel1;
	private JTextField name_field;
	private JLabel jLabel4;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JComboBox typeBox;
	private JTextArea desc_field;
	private JButton ok_button;
	private JButton abort_button;
	private JLabel infoLabel;
	private JSeparator jSeparator2;
	private JLabel jLabel7;
	private JFormattedTextField zeit1;
	private JButton oneForAll1;
	private JTextField dauer1;
	private JTextField ort1;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JSeparator jSeparator1;
	
	private JTextField[] ortArray = new JTextField[7];
	private JFormattedTextField[] zeitArray = new JFormattedTextField[7];
	private JTextField[] dauerArray = new JTextField[7];
	private JButton[] buttonArray = new JButton[7];
	private JCheckBox[] checkArray = new JCheckBox[7];
	private String title;
	
	private EditTerminContainerFrame owner;
	private ArrayList<Termin> termine; //Die erstellten Termine
	private List<GenericDataObject> termin_kat_data;
	private IGenericDAO gdo = new GenericDAO();

	public TerminReiheFrame(EditTerminContainerFrame owner, String title) {
		super();
		this.title = title;
		this.owner = owner;	
		initGUI();
		
		addWindowListener(new WindowAdapter()
				{
				public void windowClosing(WindowEvent arg0)
				{ //wird das Fenster über den X-Button rechts oben geschlossen
				  //wird die Anwendung beendet.
					enableOwner();
					super.windowClosing(arg0);
					dispose();
					}
				});
	}
	
	private void enableOwner()
	{
		owner.setEnabled(true);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			owner.setEnabled(false);
			{
				for (int i=0;i<7;i++)
				{
					check1 = new JCheckBox();
					getContentPane().add(check1);
					check1.setText("Montag");
					check1.setBounds(7, 112+i*28, 93, 28);
					checkArray[i]=check1;
					checkArray[i].addMouseListener(this);
					checkArray[i].addMouseMotionListener(this);
				}
				checkArray[0].setText("Montag");
				checkArray[1].setText("Dienstag");
				checkArray[2].setText("Mittwoch");
				checkArray[3].setText("Donnerstag");
				checkArray[4].setText("Freitag");
				checkArray[5].setText("Samstag");
				checkArray[6].setText("Sonntag");
			}
			{
				name_field = new JTextField(title);
				getContentPane().add(name_field);
				name_field.setBounds(63, 14, 371, 21);
				name_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Name:");
				jLabel1.setBounds(7, 14, 35, 21);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Zeitraum:");
				jLabel2.setBounds(7, 42, 63, 21);
			}
			{
				beginDate_field = new JFormattedTextField(createFormatter("##-##-####"));
				beginDate_field.setText("");
				getContentPane().add(beginDate_field);
				beginDate_field.setBounds(63, 42, 77, 21);
				beginDate_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));	
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("bis");
				jLabel3.setBounds(147, 42, 21, 21);
			}
			{
				endDate_field = new JFormattedTextField(createFormatter("##-##-####"));
				getContentPane().add(endDate_field);
				endDate_field.setBounds(168, 42, 77, 21);
				endDate_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			}
			{
				jSeparator1 = new JSeparator();
				getContentPane().add(jSeparator1);
				jSeparator1.setBounds(7, 98, 427, 7);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Ort");
				jLabel4.setBounds(112, 84, 28, 14);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Zeit");
				jLabel5.setBounds(252, 84, 35, 14);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Dauer (min)");
				jLabel6.setBounds(315, 84, 56, 14);
			}
			{	
				for (int i=0;i<7;i++)
				{
					ort1 = new JTextField();
					getContentPane().add(ort1);
					ort1.setBounds(112, 112+i*28, 126, 21);
					ort1.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),1,false));
					ort1.setEnabled(false);
					ortArray[i]=ort1;
				}
			}
			{
				for (int i=0;i<7;i++)
				{
					zeit1 = new JFormattedTextField(createFormatter("##:##"));
					getContentPane().add(zeit1);
					zeit1.setBounds(252, 112+i*28, 49, 21);
					zeit1.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),1,false));
					zeit1.setEnabled(false);
					zeitArray[i]=zeit1;
				}
				
			}
			{
				for (int i=0;i<7;i++)
				{
					dauer1 = new JTextField();
					getContentPane().add(dauer1);
					dauer1.setBounds(315, 112+i*28, 49, 21);
					dauer1.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),1,false));
					dauer1.setEnabled(false);
					dauerArray[i]=dauer1;
				}
				
			}
			{
				for (int i=0;i<7;i++)
				{
					oneForAll1 = new JButton();
					getContentPane().add(oneForAll1);
					oneForAll1.setBounds(378, 112+i*28, 35, 21);
					oneForAll1.setIcon(new ImageIcon(AutopsiConfigurator.images+"/oneForAll.GIF"));
					oneForAll1.setEnabled(false);
					buttonArray[i]=oneForAll1;
					buttonArray[i].addMouseListener(this);
				}
				
			}
			{
				abort_button = new JButton();
				getContentPane().add(abort_button);
				abort_button.setText("Abbrechen");
				abort_button.setBounds(273, 427, 91, 21);
				abort_button.addMouseListener(this);
			}
			{
				ok_button = new JButton();
				getContentPane().add(ok_button);
				ok_button.setText("OK");
				ok_button.setBounds(371, 427, 63, 21);
				ok_button.addMouseListener(this);
			}
			{
				jSeparator2 = new JSeparator();
				getContentPane().add(jSeparator2);
				jSeparator2.setBounds(7, 315, 427, 7);
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("(TT-MM-JJJJ)");
				jLabel7.setBounds(252, 42, 70, 21);
			}
			{
				desc_field = new JTextArea();
				getContentPane().add(desc_field);
				desc_field.setBounds(7, 336, 427, 77);
				desc_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				desc_field.setWrapStyleWord(true);
			}
			{
				jLabel8 = new JLabel();
				getContentPane().add(jLabel8);
				jLabel8.setText("Beschreibung:");
				jLabel8.setBounds(7, 322, 91, 14);
			}
			{
				ComboBoxModel typeBoxModel = new DefaultComboBoxModel();
				typeBox = new JComboBox();
				getContentPane().add(typeBox);
				typeBox.setModel(typeBoxModel);
				typeBox.setBounds(70, 427, 140, 21);
				
				String query = "select * from termin_kategorie";
				TerminKategorie kat = new TerminKategorie();
				
				termin_kat_data = gdo.unsafeQuery(query,kat);
				
				for(int i = 0; i < termin_kat_data.size();i++){
					kat = (TerminKategorie)termin_kat_data.get(i);
					typeBox.addItem(kat.getName());
				}
			}
			{
				jLabel9 = new JLabel();
				getContentPane().add(jLabel9);
				jLabel9.setText("Termintyp:");
				jLabel9.setBounds(7, 428, 77, 21);
				{
					infoLabel = new JLabel();
					jLabel9.add(infoLabel);
					infoLabel.setIcon(new ImageIcon("src/images/info.GIF"));
					infoLabel.setBounds(-14, 7, 420, 21);
				}
			}
			pack();
			this.setSize(449, 506);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public void mouseClicked(MouseEvent arg0) {
		for(int i=0;i<7;i++)
		{
			if(arg0.getSource().equals(buttonArray[i]))
			{
				for(int j = 0;j<7;j++)
				{
					ortArray[j].setText(ortArray[i].getText());
					zeitArray[j].setText(zeitArray[i].getText());
					dauerArray[j].setText(dauerArray[i].getText());	
				}
			}
		}
		for(int i=0;i<7;i++) //Checkboxen überprüfen
		{
			if(arg0.getSource().equals(checkArray[i]))
			{
				boolean check;
				if(checkArray[i].getSelectedObjects()!=null) check = true;
				else check = false;
				ortArray[i].setEnabled(check);
				zeitArray[i].setEnabled(check);
				dauerArray[i].setEnabled(check);
				buttonArray[i].setEnabled(check);
			}
		}
		if(arg0.getSource().equals(abort_button))
			{
				enableOwner();
				dispose();
			}
		if(arg0.getSource().equals(ok_button))
			{
				createTerminList();
				enableOwner();
			}
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {		
		for(int i=0;i<7;i++)
		{
			if(arg0.getSource().equals(buttonArray[i]))
			{
				infoLabel.setText("Setzt alle Ort-,Zeit- und Dauer-Felder auf die Werte dieses Tags.");
			}
		}
		
	}

	public void mouseExited(MouseEvent arg0) {
		for(int i=0;i<7;i++)
		{
			if(arg0.getSource().equals(buttonArray[i]))
			{
				infoLabel.setText("");
			}
		}
	}

	public void mouseDragged(MouseEvent arg0) {
		
	}

	public void mouseMoved(MouseEvent arg0) {
		for(int i=0;i<7;i++) //Checkboxen überprüfen
		{
			if(arg0.getSource().equals(checkArray[i]))
			{
				boolean check;
				if(checkArray[i].getSelectedObjects()!=null) check = true;
				else check = false;
				ortArray[i].setEnabled(check);
				zeitArray[i].setEnabled(check);
				dauerArray[i].setEnabled(check);
				buttonArray[i].setEnabled(check);
			}
		}
		
	}

	private void createTerminList()
	{ //erstellt die Termine mit auf Basis der eingegeben Daten
		termine = new ArrayList<Termin>();
		
		try
		{
			GregorianCalendar begin = new GregorianCalendar(); //Beginndatum
			GregorianCalendar end = new GregorianCalendar();   //Enddatum
			GregorianCalendar counter = new GregorianCalendar(); //"Zähler"
			
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			Date dat = sf.parse(beginDate_field.getText());
			begin.setTime(dat);
			begin.set(Calendar.HOUR_OF_DAY,0);
			begin.set(Calendar.MINUTE,0);
			begin.set(Calendar.SECOND,0);
			dat = sf.parse(endDate_field.getText());
			end.setTime(dat);
			end.set(Calendar.HOUR_OF_DAY,23);
			end.set(Calendar.MINUTE,59);
			end.set(Calendar.SECOND,59);
			counter.setTime(begin.getTime());
			
			int[] count = {0,0,0,0,0,0,0}; 
			String name = name_field.getText();
			name = name.replace("'".toCharArray()[0],'´');
			Timestamp stamp;
			
			String stampstring ="";
			
			while(counter.before(end)) //solange Zähler innerhalb der Zeitspanne
			{
				

				if(checkArray[0].getSelectedObjects()!=null && counter.getTime().toString().contains("Mon"))
				{ count[0]++;
						stamp = new Timestamp(counter.getTimeInMillis());
						stampstring = stamp.toString().substring(0,10)+" "+zeitArray[0].getText()+":00.0";
						stamp = Timestamp.valueOf(stampstring);
						
						Termin ter = new Termin();
						ter.setDate(stamp);
						ter.setSecondaryTitle(name);
						ter.setDuration(Integer.parseInt(dauerArray[0].getText()));
						ter.setPlace(ortArray[0].getText());
						ter.setDescription(desc_field.getText());
						ter.setTerminKategorieId(((TerminKategorie)(termin_kat_data.get(typeBox.getSelectedIndex()))).getId());
						termine.add(ter);
	
				}
				if(checkArray[1].getSelectedObjects()!=null && counter.getTime().toString().contains("Tue"))	
				{count[1]++;
						stamp = new Timestamp(counter.getTimeInMillis());
						stampstring = stamp.toString().substring(0,10)+" "+zeitArray[1].getText()+":00.0";
						stamp = Timestamp.valueOf(stampstring);
							
						Termin ter = new Termin();
						ter.setDate(stamp);
						ter.setSecondaryTitle(name);
						ter.setDuration(Integer.parseInt(dauerArray[1].getText()));
						ter.setPlace(ortArray[1].getText());
						ter.setDescription(desc_field.getText());
						ter.setTerminKategorieId(((TerminKategorie)(termin_kat_data.get(typeBox.getSelectedIndex()))).getId());
						termine.add(ter);		
				}
				if(checkArray[2].getSelectedObjects()!=null && counter.getTime().toString().contains("Wed"))
				{count[2]++;
						stamp = new Timestamp(counter.getTimeInMillis());
						stampstring = stamp.toString().substring(0,10)+" "+zeitArray[2].getText()+":00.0";
						stamp = Timestamp.valueOf(stampstring);
							
						Termin ter = new Termin();
						ter.setDate(stamp);
						ter.setSecondaryTitle(name);
						ter.setDuration(Integer.parseInt(dauerArray[2].getText()));
						ter.setPlace(ortArray[2].getText());
						ter.setDescription(desc_field.getText());
						ter.setTerminKategorieId(((TerminKategorie)(termin_kat_data.get(typeBox.getSelectedIndex()))).getId());
						termine.add(ter);
				}
				if(checkArray[3].getSelectedObjects()!=null && counter.getTime().toString().contains("Thu"))
				{count[3]++;
						stamp = new Timestamp(counter.getTimeInMillis());
						stampstring = stamp.toString().substring(0,10)+" "+zeitArray[3].getText()+":00.0";
						stamp = Timestamp.valueOf(stampstring);
						
						Termin ter = new Termin();
						ter.setDate(stamp);
						ter.setSecondaryTitle(name);
						ter.setDuration(Integer.parseInt(dauerArray[3].getText()));
						ter.setPlace(ortArray[3].getText());
						ter.setDescription(desc_field.getText());
						ter.setTerminKategorieId(((TerminKategorie)(termin_kat_data.get(typeBox.getSelectedIndex()))).getId());
						termine.add(ter);	
				}
				if(checkArray[4].getSelectedObjects()!=null && counter.getTime().toString().contains("Fri"))
				{count[4]++;
						stamp = new Timestamp(counter.getTimeInMillis());
						stampstring = stamp.toString().substring(0,10)+" "+zeitArray[4].getText()+":00.0";
						stamp = Timestamp.valueOf(stampstring);
							
						Termin ter = new Termin();
						ter.setDate(stamp);
						ter.setSecondaryTitle(name);
						ter.setDuration(Integer.parseInt(dauerArray[4].getText()));
						ter.setPlace(ortArray[4].getText());
						ter.setDescription(desc_field.getText());
						ter.setTerminKategorieId(((TerminKategorie)(termin_kat_data.get(typeBox.getSelectedIndex()))).getId());
						termine.add(ter);
				}
				if(checkArray[5].getSelectedObjects()!=null && counter.getTime().toString().contains("Sat"))
				{count[5]++;
						stamp = new Timestamp(counter.getTimeInMillis());
						stampstring = stamp.toString().substring(0,10)+" "+zeitArray[5].getText()+":00.0";
						stamp = Timestamp.valueOf(stampstring);
						
						Termin ter = new Termin();
						ter.setDate(stamp);
						ter.setSecondaryTitle(name);
						ter.setDuration(Integer.parseInt(dauerArray[5].getText()));
						ter.setPlace(ortArray[5].getText());
						ter.setDescription(desc_field.getText());
						ter.setTerminKategorieId(((TerminKategorie)(termin_kat_data.get(typeBox.getSelectedIndex()))).getId());
						termine.add(ter);
				}
				if(checkArray[6].getSelectedObjects()!=null && counter.getTime().toString().contains("Sun"))
				{count[6]++;
						stamp = new Timestamp(counter.getTimeInMillis());
						stampstring = stamp.toString().substring(0,10)+" "+zeitArray[6].getText()+":00.0";
						stamp = Timestamp.valueOf(stampstring);
						
						Termin ter = new Termin();
						ter.setDate(stamp);
						ter.setSecondaryTitle(name);
						ter.setDuration(Integer.parseInt(dauerArray[6].getText()));
						ter.setPlace(ortArray[6].getText());
						ter.setDescription(desc_field.getText());
						ter.setTerminKategorieId(((TerminKategorie)(termin_kat_data.get(typeBox.getSelectedIndex()))).getId());
						termine.add(ter);
				}
				
				counter.set(Calendar.DAY_OF_MONTH,counter.get(Calendar.DAY_OF_MONTH)+1);
			}
		
			owner.updateTerminList(termine);
			owner.setVisible(true);
			dispose();
			
		}
		catch(Exception ex)
		{
			ShowErrorDialog("Ungültige Eingabe!","Felder wurden ungültig oder nicht ausgefüllt.");
			System.out.println(ex.toString());
		}
}
	private void ShowErrorDialog(String title, String text)
	{
		InfoDialog info = new InfoDialog(this, title,text);
		info.setLocation(this.getLocation().x+200,this.getLocation().y+200);
		info.setVisible(true);
	}
}
