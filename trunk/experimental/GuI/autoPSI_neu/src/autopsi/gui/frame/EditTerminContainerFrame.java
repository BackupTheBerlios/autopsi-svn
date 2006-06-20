package autopsi.gui.frame;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.exception.EAttributeNotFound;
import autopsi.database.exception.EDatabase;
import autopsi.database.exception.EDatabaseConnection;
import autopsi.database.table.AttachableObjectKategorie;
import autopsi.database.table.Termin;
import autopsi.database.table.TerminContainer;
import autopsi.gui.DateConverter;
import autopsi.gui.MonthRenderer;
import autopsi.gui.WeekRenderer;

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
public class EditTerminContainerFrame extends javax.swing.JFrame implements java.awt.event.MouseListener{

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
	private JList terminList;
	private JScrollPane jScrollPane1;
	private JButton newTerminReihe;
	private JLabel jLabel7;
	private JLabel jLabel3;

	private JButton openTermin;
	private JButton editTermin;
	private JButton deleteTermin;
	private JButton newTermin;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JTextField sucheTermin_field;

	private JButton jButton6;
	private JButton jButton5;
	private JButton jButton4;
	private JList jList1;
	private JPanel jPanel1;
	private JTextArea desc_area;
	private JLabel jLabel2;
	private JTextField title_field;
	private JButton add_group;
	private JLabel jLabel4;
	private JButton edit_group;
	private JFormattedTextField endDate_field;
	private JFormattedTextField beginDate_field;
	private JComboBox jGroupBox;
	private JLabel jLabel1;
	private JPanel jPanel3;
	private JPanel jPanel2;
	private JButton ok_button;
	private JButton abort_button;
	private String title = "";
	private String desc = "";
	private IGenericDAO gdo; 
	private int ID;
	private EditTerminFrame owner;
	List<GenericDataObject> group_data;
	private mainFrame owner2;
	private int konstruktor = 0;
	private int selectedGroup;
	DefaultComboBoxModel terminModel = new DefaultComboBoxModel();
	
	
	private void readData(int id) throws EDatabaseConnection, EAttributeNotFound, EDatabase{
		
		TerminContainer lookup = new TerminContainer();
		lookup.setId(id);
		List<GenericDataObject> list = null;
		list = gdo.getDataObjects(lookup);
		title = ((TerminContainer)list.get(0)).getTitle();
		title_field.setText(title);
		desc = ((TerminContainer)list.get(0)).getDescription();
		desc_area.setText(desc);
	
		int i = 0;
		for(i = 0;i<group_data.size();i++){
			if(((AttachableObjectKategorie)group_data.get(i)).getId() == ((TerminContainer)list.get(0)).getGroupID())
				break;
		}
		jGroupBox.setSelectedIndex(i);
		selectedGroup = i;
		
		
	}
	private void update(){
		try{
			String query="";
			int group_id = ((AttachableObjectKategorie)(group_data.get(jGroupBox.getSelectedIndex()))).getId();
			if (ID<0) query = "insert into termincontainer (title,group_id, description) values ('"+title_field.getText()+ "',"+group_id+",'"+desc_area.getText()+"')";
			else query = "update termincontainer  set title = '" + title_field.getText() + "', group_id = " + group_id  + ", description='"+desc_area.getText()+"' where id="+ID;
			TerminContainer vorlage = new TerminContainer();
			System.out.println(query);
			gdo.unsafeQuery(query,vorlage);
			
			if(konstruktor == 1) {if(owner!=null) owner.updateTCList();}
			else {
				owner2.updateInfoBar(true);
				owner2.updateTable();
			}
			
		}
		catch (Exception e){
			System.out.println("Exception beim Updaten=="+e.toString());
		}
	}
	
	
	public EditTerminContainerFrame(EditTerminFrame owner, int id) { //Konstruktor f�r das EditTermin-Frame
		super();
		this.ID = id;
		this.owner = owner;
		gdo = new GenericDAO();
		gdo.setCurrentTable("termincontainer");
		this.konstruktor = 1;
		initGUI();
		
		
		addWindowListener(new WindowAdapter()
				{
				public void windowClosing(WindowEvent arg0)
				{ //wird das Fenster �ber den X-Button rechts oben geschlossen
				  //wird die Anwendung beendet.
					super.windowClosing(arg0);
					dispose();
					}
				});
	}
	
	public EditTerminContainerFrame(mainFrame owner, int id) { //Konstruktor f�r das MainFrame
		super();
		this.ID = id;
		this.owner2 = owner;
		gdo = new GenericDAO();
		gdo.setCurrentTable("termincontainer");
		this.konstruktor = 2;
		initGUI();
		
		
		addWindowListener(new WindowAdapter()
				{
				public void windowClosing(WindowEvent arg0)
				{ //wird das Fenster �ber den X-Button rechts oben geschlossen
				  //wird die Anwendung beendet.
					super.windowClosing(arg0);
					dispose();
					}
				});
	}

	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Termincontainer bearbeiten");
			getContentPane().setLayout(null);
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1);
				jTabbedPane1.setBounds(7, 14, 427, 294);
				jTabbedPane1.setFocusable(false);
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
						
						title_field = new JTextField();
						jPanel1.add(title_field);
						title_field.setBounds(91, 14, 322, 21);
						title_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						
					}
					{
						jLabel2 = new JLabel();
						jPanel1.add(jLabel2);
						jLabel2.setText("Beschreibung:");
						jLabel2.setBounds(7, 70, 84, 28);
					}
					{
						desc_area = new JTextArea();
						jPanel1.add(desc_area);
						desc_area.setBounds(91, 70, 322, 189);
						desc_area.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						
					}
					{
						ComboBoxModel jGroupBoxModel = new DefaultComboBoxModel();
						jGroupBox = new JComboBox();
						jPanel1.add(jGroupBox);
						jGroupBox.setModel(jGroupBoxModel);
						jGroupBox.setBounds(91, 42, 259, 21);
						jGroupBox.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						String query = "select * from attachable_object_kategorie";
						AttachableObjectKategorie kat = new AttachableObjectKategorie();
						group_data = gdo.unsafeQuery(query,kat);
						
						for(int i = 0; i < group_data.size();i++){
							kat = (AttachableObjectKategorie)group_data.get(i);
							jGroupBox.addItem(kat.getTitle());
						}
						
						jGroupBox.addFocusListener(new FocusListener(){

							

							public void focusGained(FocusEvent arg0) {
								
								List<GenericDataObject> groupList;
								AttachableObjectKategorie kat= new AttachableObjectKategorie();
																
								jGroupBox.setModel(new DefaultComboBoxModel());
								try
								{
									groupList = gdo.unsafeQuery("select * from attachable_object_kategorie order by id",kat);
									for(int i = 0;i<groupList.size();i++)
									{
										kat = (AttachableObjectKategorie)groupList.get(i);
										jGroupBox.addItem(kat.getTitle());
										
									}		
									String query = "select * from attachable_object_kategorie";
									group_data = gdo.unsafeQuery(query,kat);
									jGroupBox.setSelectedIndex(selectedGroup);
								}
								catch (Exception ex)
								{
									
								}
								
							}

							public void focusLost(FocusEvent arg0) {
								selectedGroup = jGroupBox.getSelectedIndex();
							}
						});
					}
					{
						jLabel4 = new JLabel();
						jPanel1.add(jLabel4);
						jLabel4.setText("Gruppe:");
						jLabel4.setBounds(7, 42, 63, 21);
					}
					{
						add_group = new JButton();
						jPanel1.add(add_group);
						add_group.setText("+");
						add_group.setBounds(350, 42, 42, 21);
						add_group.addMouseListener(this);
					}
					{
						edit_group = new JButton();
						jPanel1.add(edit_group);
						edit_group.setText("...");
						edit_group.setBounds(392, 42, 14, 21);
						edit_group.addMouseListener(this);
					}
				}
				{
					jPanel3 = new JPanel();
					jTabbedPane1.addTab(
						"verkn�pfte Objekte",
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
						jList1.setBounds(21, 14, 392, 210);
						jList1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						jList1.setVisibleRowCount(1);
					}
					{
						jButton4 = new JButton();
						jPanel3.add(jButton4);
						jButton4.setText("Hinzuf�gen");
						jButton4.setBounds(231, 238, 98, 21);
					}
					{
						jButton5 = new JButton();
						jPanel3.add(jButton5);
						jButton5.setText("L�schen");
						jButton5.setBounds(336, 238, 77, 21);
					}
					{
						jButton6 = new JButton();
						jPanel3.add(jButton6);
						jButton6.setText("�ffnen");
						jButton6.setBounds(21, 238, 70, 21);
					}

				}
				{
					jPanel2 = new JPanel();
					jTabbedPane1.addTab("Termine", null, jPanel2, null);
					jPanel2.setBackground(new java.awt.Color(255,255,255));
					jPanel2.setLayout(null);
				
					{
						sucheTermin_field = new JTextField();
						jPanel2.add(sucheTermin_field);
						sucheTermin_field.setBounds(56, 7, 357, 21);
						sucheTermin_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						sucheTermin_field.addKeyListener(new KeyListener(){

							public void keyTyped(KeyEvent arg0)
							{
								DefaultComboBoxModel model2 = new DefaultComboBoxModel();
								
								for(int i = 0;i<terminModel.getSize();i++)
								{
									if(terminModel.getElementAt(i).toString().toLowerCase().contains(sucheTermin_field.getText().toLowerCase()))
									{
										model2.addElement(terminModel.getElementAt(i));
									}
								}
								
								terminList.setModel(model2);
							}
							public void keyPressed(KeyEvent arg0)
							{						
							}
							public void keyReleased(KeyEvent arg0) 
							{						
							}				
						});
					}
					{
						jLabel5 = new JLabel();
						jPanel2.add(jLabel5);
						jLabel5.setText("Datum eingrenzen von (TT-MM-JJJJ)");
						jLabel5.setBounds(7, 32, 196, 28);
					}
					{
						jLabel6 = new JLabel();
						jPanel2.add(jLabel6);
						jLabel6.setText("bis");
						jLabel6.setBounds(294, 35, 14, 21);
					}
					{
						newTermin = new JButton();
						jPanel2.add(newTermin);
						newTermin.setBounds(210, 238, 42, 28);
						newTermin.setIcon(new ImageIcon("src/images/newTermin.GIF"));
					}
					{
						deleteTermin = new JButton();
						jPanel2.add(deleteTermin);
						deleteTermin.setBounds(371, 238, 42, 28);
						deleteTermin.setIcon(new ImageIcon("src/images/deleteTermin.GIF"));
					}
					{
						editTermin = new JButton();
						jPanel2.add(editTermin);
						editTermin.setBounds(329, 238, 42, 28);
						editTermin.setIcon(new ImageIcon("src/images/editTermin.GIF"));
					}
					{
						openTermin = new JButton();
						jPanel2.add(openTermin);
						openTermin.setBounds(7, 238, 42, 28);
						openTermin.setIcon(new ImageIcon("src/images/goToTermin.GIF"));
					}
					{
						jLabel3 = new JLabel();
						jPanel2.add(jLabel3);
						jLabel3.setText("Suchen:");
						jLabel3.setBounds(7, 5, 49, 28);
					}
					{
						newTerminReihe = new JButton();
						jPanel2.add(newTerminReihe);
						newTerminReihe.setBounds(252, 238, 56, 28);
						newTerminReihe.setIcon(new ImageIcon("src/images/newTerminReihe.GIF"));
						newTerminReihe.addMouseListener(this);
					}
					{
						jScrollPane1 = new JScrollPane();
						jPanel2.add(jScrollPane1);
						jScrollPane1.setBounds(7, 63, 406, 168);
						jScrollPane1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						{
							terminList = new JList();
							jScrollPane1.setViewportView(terminList);
							terminList.setModel(terminModel);
						}
					}
					{
						beginDate_field = new JFormattedTextField(createFormatter("##-##-####"));
						jPanel2.add(beginDate_field);
						beginDate_field.setBounds(189, 35, 98, 21);
						beginDate_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					}
					{
						endDate_field = new JFormattedTextField(createFormatter("##-##-####"));
						jPanel2.add(endDate_field);
						endDate_field.setBounds(315, 35, 98, 21);
						endDate_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					}
				
				}
			}
			{
				abort_button = new JButton();
				getContentPane().add(abort_button);
				abort_button.setText("Abbrechen");
				abort_button.setBounds(133, 315, 105, 21);
				abort_button.addMouseListener(this);
			}
			{
				apply_button = new JButton();
				getContentPane().add(apply_button);
				apply_button.setText("�bernehmen");
				apply_button.setBounds(266, 315, 112, 21);
				apply_button.addMouseListener(this);
			}
			{
				ok_button = new JButton();
				getContentPane().add(ok_button);
				ok_button.setText("OK");
				ok_button.setBounds(385, 315, 49, 21);
				ok_button.addMouseListener(this);
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setBounds(7, 343, 427, 21);
				jLabel7.setBorder(BorderFactory.createTitledBorder(""));
			}
			
			
			
			
			
			if(ID>-1) readData(ID);			
			pack();
			this.setSize(449, 408);
			this.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource().equals(abort_button)){
			this.dispose();
		}
		if(arg0.getSource().equals(ok_button)){
			update();
			dispose();
		}
		if(arg0.getSource().equals(apply_button)){
			update();
		}
		if(arg0.getSource().equals(newTerminReihe)){
			TerminReiheFrame terminreihe = new TerminReiheFrame(this, "Titel");
			terminreihe.setLocation(this.getLocation().x+20,this.getLocation().y+20);
			terminreihe.setTitle("Terminreihe hinzuf�gen");
			terminreihe.setVisible(true);
		}
		if(arg0.getSource().equals(add_group)){
			GenericEditFrame gef = new GenericEditFrame();
			AttachableObjectKategorie obj = new AttachableObjectKategorie();
			gef.setObjectToEdit(obj,true);
			gef.setTableToEdit("Attachable_Object_Kategorie");
			gef.setVisible(true);
		}
		if(arg0.getSource().equals(edit_group)){
			GenericEditFrame gef = new GenericEditFrame();
			if(jGroupBox.getSelectedIndex() != 0){
			AttachableObjectKategorie obj = (AttachableObjectKategorie)group_data.get(jGroupBox.getSelectedIndex());
			gef.setObjectToEdit(obj,false);
			gef.setTableToEdit("Attachable_Object_Kategorie");
			gef.setVisible(true);
			}
		}
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource().equals(apply_button)){
			jLabel7.setText("�nderungen �bernehmen");
		}
		if(arg0.getSource().equals(abort_button)){
			jLabel7.setText("Abbrechen");
		}
		if(arg0.getSource().equals(ok_button)){
			jLabel7.setText("OK");
		}
		if(arg0.getSource().equals(newTermin)){
			jLabel7.setText("Erstellt einen neuen Termin in diesem Termincontainer");
		}
		if(arg0.getSource().equals(editTermin)){
			jLabel7.setText("Bearbeitet den gew�hlten Termin");
		}
		if(arg0.getSource().equals(deleteTermin)){
			jLabel7.setText("L�scht den gew�hlten Termin");
		}
		if(arg0.getSource().equals(newTerminReihe)){
			jLabel7.setText("Erstellt sich wiederholende Termine");
		}
		if(arg0.getSource().equals(openTermin)){
			jLabel7.setText("Sprung zum gew�hlten Termin im Kalender");
		}
		if(arg0.getSource().equals(add_group)){
			jLabel7.setText("Neue Gruppe erstellen");
		}
		if(arg0.getSource().equals(edit_group)){
			jLabel7.setText("Gruppe bearbeiten");
		}
	}

	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource().equals(apply_button)){
			jLabel7.setText("");
		}
		if(arg0.getSource().equals(abort_button)){
			jLabel7.setText("");
		}
		if(arg0.getSource().equals(ok_button)){
			jLabel7.setText("");
		}
		if(arg0.getSource().equals(newTermin)){
			jLabel7.setText("");
		}
		if(arg0.getSource().equals(editTermin)){
			jLabel7.setText("");
		}
		if(arg0.getSource().equals(deleteTermin)){
			jLabel7.setText("");
		}
		if(arg0.getSource().equals(newTerminReihe)){
			jLabel7.setText("");
		}
		if(arg0.getSource().equals(openTermin)){
			jLabel7.setText("");
		}
		if(arg0.getSource().equals(add_group)){
			jLabel7.setText("");
		}
		if(arg0.getSource().equals(edit_group)){
			jLabel7.setText("");
		}
	}

	public void updateTerminList(List<Termin> termine)
	{
		System.out.println(termine.size());
		for(int i = 0;i<termine.size();i++)
		{
			DateConverter converter = new DateConverter();
			Date dat = new Date(termine.get(i).getDate().getTime());
			String datum = converter.toShortYear(dat.toString());
			String termin = datum+": "+termine.get(i).getSecondaryTitle()+",    Ort: "+termine.get(i).getPlace();
			terminModel.addElement(termin);		
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
}

