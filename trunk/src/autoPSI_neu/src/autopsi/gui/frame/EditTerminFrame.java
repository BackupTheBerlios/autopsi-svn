package autopsi.gui.frame;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

import autopsi.gui.frame.EditTerminContainerFrame;
import autopsi.basis.AutopsiConfigurator;
import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.exception.EAttributeNotFound;
import autopsi.database.exception.EDatabase;
import autopsi.database.exception.EDatabaseConnection;
import autopsi.database.table.*;


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

	private List<GenericDataObject> attachedObjects;
	private DefaultListModel lm;
	private JTabbedPane jTabbedPane1;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JComboBox group_Box;
	private JButton newTC;
	private JComboBox tcTitle_box;
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
	private JButton open_button;
	private JButton jDelObjectButton;
	private JButton jAddObjectButton;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane1;
	private JButton editTC;
	private JButton edit_Group_button;
	private JButton edit_Type_button;
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
	private GregorianCalendar c = new GregorianCalendar();
	private boolean ok = false;
	private GregorianCalendar cal=null;
	private String tkat = "";
	private JFrame owner;
	List<GenericDataObject> group_data;
	List<GenericDataObject> termin_kat_data;
	List<GenericDataObject> termin_cont_data;
	private JButton group_add;
	private JButton type_add;
	private int selectedType;
	private int selectedGroup;
	private int selectedTC;
	ArrayList<String[]> queryList = new ArrayList<String[]>();
	List<GenericDataObject> id_list;
	private int lastID = 0;
	private boolean newTCsender;
	private boolean read_ok = false;
	/**
	 * Der Konstruktor von EditTerminFrame.
	 * @param owner Ist das aufrufende Fenster
	 * @param cal Ist ein �bergebenes Datum f�r das Datumsfeld
	 * @param id Ist die ID, unter der der Termin in der Datenbank gespeichert ist
	 * @param tcsender Gibt an, ob das EditTerminFrame von einem Termincontainer aus aufgerufen wurde.
	 */
	public EditTerminFrame(JFrame owner, GregorianCalendar cal, Integer id, boolean tcsender) {
		super();
		this.ID = id;
		this.cal = cal;
		this.newTCsender = tcsender;
		this.owner = owner;
		gdo = new GenericDAO();
		gdo.setCurrentTable("termin");
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
	/**
	 * Hier werden die Daten des Termins aus der Datenbank in die Felder gelesen
	 * @param id Ist die ID, unter der der Termin in der Datenbank gespeichert ist
	 * @throws EDatabaseConnection
	 * @throws EAttributeNotFound
	 * @throws EDatabase
	 */
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
		dateField.setText(date.substring(8,10)+"-"+date.substring(5,7)+"-"+date.substring(0,4));
		time = ((Termin)list.get(0)).getDate().toString().substring(11);
		timeField.setText(time.substring(0,2)+":" + time.substring(3,5));
		
		
		int i = 0;
		for(i = 0;i<termin_kat_data.size();i++){
			if(((TerminKategorie)termin_kat_data.get(i)).getId() == ((Termin)list.get(0)).getTerminKategorieId())
				break;
		}
		
		choose_Type.setSelectedIndex(i);
		selectedType = i;
		updateTCList();	
		for(i = 0;i<termin_cont_data.size();i++){
			if(((TerminContainer)termin_cont_data.get(i)).getId() == ((Termin)list.get(0)).getTerminContainerID())
				break;
		}
		tcTitle_box.setSelectedIndex(i);
		selectedTC = i;
		for(i = 0;i<group_data.size();i++){
			if(((AttachableObjectKategorie)group_data.get(i)).getId() == ((Termin)list.get(0)).getGroupID())
				break;
		}
		group_Box.setSelectedIndex(i);
		selectedGroup = i;
		loadObjectList();
	}
	/**
	 * Hier werden die Daten des neuen bzw. alten Termins in die Datenbank geschrieben
	 *
	 */
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
			
			

			int termin_kat = ((TerminKategorie)(termin_kat_data.get(choose_Type.getSelectedIndex()))).getId();
			tkat = "" + termin_kat;
			
			int tc_id = ((TerminContainer)(termin_cont_data.get(tcTitle_box.getSelectedIndex()))).getId();
			if((group_data.get(group_Box.getSelectedIndex())) instanceof AttachableObjectKategorie){
				
			}
			int group_id = ((AttachableObjectKategorie)(group_data.get(group_Box.getSelectedIndex()))).getId();	
			readFieldData();
			if(read_ok)
			{
				String query="";
				
				Termin vorlage = new Termin();
				
				
				if(owner instanceof mainFrame)
				{
					if (ID<0)//Wenn es sich um einen neuen Termin handelt:
					{
						
						
						query = "insert into termin (GROUP_ID,TERMIN_KATEGORIE_ID, secondary_title, description, date, duration, place, termincontainer_id) values ("+group_id+ ","+tkat+ ",'"+sec_title+"','"+desc+"','"+date+"',"+duration+",'"+place+"',"+tc_id+")";
					
						gdo.unsafeQuery(query,vorlage);//Erstellt den Termin anhand der query
						id_list = gdo.unsafeQuery("select * from termin where id =identity()",vorlage);
					
					try
					{
						lastID = ((Termin)id_list.get(0)).getId();//Liest die letzte ID aus der Datenbank ein, die erzeugt wurde
						
						
						
					for(int j = 0;j<queryList.size();j++)//H�ngt alle anzuh�ngenden Objekte an
					{
				
						String query2 = "insert into anhaengen_termin values (" + lastID+","+ Integer.parseInt(queryList.get(j)[0])+",'"+queryList.get(j)[1]+"')";
						gdo.unsafeQuery(query2,new Anhaengen_termin());
						
					}
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
						
					}
					}
					else 
						{
						
						for(int j = 0;j<queryList.size();j++)//H�ngt alle anzuh�ngenden Objekte an
						{
							String query2 = "insert into anhaengen_termin values (" + ID+","+ Integer.parseInt(queryList.get(j)[0])+",'"+queryList.get(j)[1]+"')";
							gdo.unsafeQuery(query2,new Anhaengen_termin());
							
						}
						
						query = "update termin  set GROUP_ID = " + group_id + ",TERMIN_KATEGORIE_ID = " + tkat + ", secondary_title='"+sec_title+"', description='"+desc+"', date='"+date+"',duration="+duration+",place='"+place+"',termincontainer_id="+tc_id+" where id="+ID;
						
						gdo.unsafeQuery(query,vorlage);//Updatet den Termin
						}
						ok = true;
						
							mainFrame actor = (mainFrame)owner;
							actor.updateTable();
							actor.updateInfoBar(false);
					}
			else if(owner instanceof EditTerminContainerFrame)
			{
			System.out.println("owner!!");
				EditTerminContainerFrame actor = (EditTerminContainerFrame)owner;
				Termin data = new Termin();
				data.setSecondaryTitle(sec_title);
				data.setDuration(duration);
				data.setPlace(place);
				data.setDescription(desc);
				data.setDate(Timestamp.valueOf(date));
				query = "update termin  set GROUP_ID = " + group_id + ",TERMIN_KATEGORIE_ID = " + tkat + ", secondary_title='"+sec_title+"', description='"+desc+"', date='"+date+"',duration="+duration+",place='"+place+"',termincontainer_id="+tc_id+" where id="+ID;
				
				ArrayList<Termin> dat = new ArrayList<Termin>();
				dat.add(data);
				System.out.println(data.getSecondaryTitle()+" "+data.getDate().toString());
				actor.updateTerminList(dat);
				ok=true;
				
			}
					
			}
			
			}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error while editing: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
			
	}
	/**
	 * Hier werden alle GUI-Komponenten erstellt.
	 *
	 */
	private void initGUI() {
		try {
			this.setIconImage(new ImageIcon(AutopsiConfigurator.images + "/autopsi.png").getImage() );
			if(cal!=null) {
				Timestamp datum = new Timestamp(cal.getTimeInMillis());
				dat = datum.toString();
			}
			
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1);
				jTabbedPane1.setBounds(7, 7, 434, 287);
				{
					jPanel1 = new JPanel();
					jTabbedPane1.addTab("allgemeine Informationen", null, jPanel1, null);
					jPanel1.setLayout(null);
					jPanel1.setBackground(new java.awt.Color(255, 255, 255));
					{
						jLabel1 = new JLabel();
						jPanel1.add(jLabel1);
						jLabel1.setText("Termincontainer:");
						jLabel1.setBounds(7, 10, 84, 28);
					}
					{
						jLabel2 = new JLabel();
						jPanel1.add(jLabel2);
						jLabel2.setText("Beschreibung:");
						jLabel2.setBounds(7, 182, 84, 21);
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
						jLabel4.setText("Sekund�rtitel:");
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
						ComboBoxModel tcTitle_boxModel = new DefaultComboBoxModel();
						tcTitle_box = new JComboBox();
						tcTitle_box.setModel(tcTitle_boxModel);
						jPanel1.add(tcTitle_box);
						tcTitle_box.setBounds(91, 14, 224, 21);
						tcTitle_box.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						String query = "select * from termincontainer";
						TerminContainer cont = new TerminContainer();
						termin_cont_data = gdo.unsafeQuery(query,cont);
						
						for(int i = 0; i < termin_cont_data.size();i++){
							cont = (TerminContainer)termin_cont_data.get(i);
							tcTitle_box.addItem(cont.getTitle());
						}
						
						tcTitle_box.addFocusListener(new FocusListener(){

							public void focusGained(FocusEvent arg0) {//Wenn die Termincontainerbox gew�hlt wird, werden die bestehenden Termincontainer aus der DB geladen
								
								List<GenericDataObject> tcList;
								TerminContainer tc= new TerminContainer();
																
								tcTitle_box.setModel(new DefaultComboBoxModel());
								try
								{
									tcList = gdo.unsafeQuery("select * from termincontainer order by id",tc);
									for(int i = 0;i<tcList.size();i++)
									{
										tc = (TerminContainer)tcList.get(i);
										tcTitle_box.addItem(tc.getTitle());
										
									}		
									String query = "select * from termincontainer";
									termin_cont_data = gdo.unsafeQuery(query,tc);
									tcTitle_box.setSelectedIndex(selectedTC);
								}
								catch (Exception ex)
								{
									
								}
								
							}

							public void focusLost(FocusEvent arg0) {
								selectedTC = tcTitle_box.getSelectedIndex();
							}
						});
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
						ComboBoxModel choose_TypeModel = new DefaultComboBoxModel();
						choose_Type = new JComboBox();
						jPanel1.add(choose_Type);
						choose_Type.setModel(choose_TypeModel);
						choose_Type.setBounds(238, 112, 119, 21);
						choose_Type.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						String query = "select * from termin_kategorie";
						TerminKategorie kat = new TerminKategorie();
						termin_kat_data = gdo.unsafeQuery(query,kat);
						
						for(int i = 0; i < termin_kat_data.size();i++){
							kat = (TerminKategorie)termin_kat_data.get(i);
							choose_Type.addItem(kat.getName());
						}
						choose_Type.addFocusListener(new FocusListener(){

							public void focusGained(FocusEvent arg0) {//Hier werden die Termintypen aus der DB geladen
								
								List<GenericDataObject> typeList;
								TerminKategorie kat= new TerminKategorie();
																
								choose_Type.setModel(new DefaultComboBoxModel());
								try
								{
									typeList = gdo.unsafeQuery("select * from termin_kategorie order by id",kat);
									for(int i = 0;i<typeList.size();i++)
									{
										kat = (TerminKategorie)typeList.get(i);
										choose_Type.addItem(kat.getName());
										
									}		
									String query = "select * from termin_kategorie";
									termin_kat_data = gdo.unsafeQuery(query,kat);
									choose_Type.setSelectedIndex(selectedType);
								}
								catch (Exception ex)
								{
									
								}
								
							}

							public void focusLost(FocusEvent arg0) {
								selectedType = choose_Type.getSelectedIndex();
							}
						});
						
						
					}
					{
						jLabel11 = new JLabel();
						jPanel1.add(jLabel11);
						jLabel11.setText("Termintyp:");
						jLabel11.setBounds(175, 112, 63, 21);
					}
					{
						dateField = new JFormattedTextField(createFormatter("##-##-####"));
						jPanel1.add(dateField);
						dateField.setBounds(91, 77, 98, 21);
						dateField.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						if(cal!=null) dateField.setText(dat.substring(8,10)+"-"+dat.substring(5,7)+"-"+dat.substring(0,4));
					}
					{
						timeField = new JFormattedTextField();
						jPanel1.add(timeField);
						timeField.setBounds(252, 77, 42, 21);
						timeField.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						timeField.setDocument(new PlainDocument() {
					    	private static final long serialVersionUID = 8723098029189851737L;
							public void insertString(int offset, String str, AttributeSet a)
									throws BadLocationException {
								// Eingaben von Buchstaben ist nicht erlaubt...
								if (!str.matches(".*[[0-9]].*"))
									return;
								//H�chstens 15 Zeichen
								if (offset>4)
									return;
								if (offset==1)
									super.insertString(offset, ":", a);
									
								super.insertString(offset, str, a);
							}
						});
					}
					{
						duration_field = new JTextField();
						jPanel1.add(duration_field);
						duration_field.setBounds(371, 77, 42, 21);
						duration_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						duration_field.setDocument(new PlainDocument() {
					    	private static final long serialVersionUID = 8723098029189851737L;
							public void insertString(int offset, String str, AttributeSet a)
									throws BadLocationException {
								// Eingaben von Buchstaben ist nicht erlaubt...
								if (!str.matches(".*[[0-9]].*"))
									return;
								
								
									
								super.insertString(offset, str, a);
							}
						});
					}
					{
						newTC = new JButton();
						jPanel1.add(newTC);
						newTC.setText("erstellen");
						newTC.setBounds(322, 14, 77, 21);
						newTC.addMouseListener(this);
					}
					{
						ComboBoxModel Group_BoxModel = new DefaultComboBoxModel();
						group_Box = new JComboBox();
						jPanel1.add(group_Box);
						group_Box.setModel(Group_BoxModel);
						group_Box.setBounds(238, 147, 119, 21);
						group_Box.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));

						String query = "select * from attachable_object_kategorie";
						AttachableObjectKategorie kat = new AttachableObjectKategorie();
						group_data = gdo.unsafeQuery(query,kat);
						
						for(int i = 0; i < group_data.size();i++){
							kat = (AttachableObjectKategorie)group_data.get(i);
							group_Box.addItem(kat.getTitle());
						}
						group_Box.addFocusListener(new FocusListener(){

							public void focusGained(FocusEvent arg0) {
								
								List<GenericDataObject> groupList;
								AttachableObjectKategorie kat= new AttachableObjectKategorie();
																
								group_Box.setModel(new DefaultComboBoxModel());
								try
								{
									groupList = gdo.unsafeQuery("select * from attachable_object_kategorie order by id",kat);
									for(int i = 0;i<groupList.size();i++)
									{
										kat = (AttachableObjectKategorie)groupList.get(i);
										group_Box.addItem(kat.getTitle());
										
									}		
									String query = "select * from attachable_object_kategorie";
									group_data = gdo.unsafeQuery(query,kat);
									group_Box.setSelectedIndex(selectedGroup);
								}
								catch (Exception ex)
								{
									
								}
								
							}

							public void focusLost(FocusEvent arg0) {
								selectedGroup = group_Box.getSelectedIndex();
							}
						});
						
					
					}
					
					{
						jLabel3 = new JLabel();
						jPanel1.add(jLabel3);
						jLabel3.setText("Gruppe:");
						jLabel3.setBounds(175, 147, 49, 21);
					}
				}
				{
					jPanel3 = new JPanel();
					jTabbedPane1.addTab("verkn�pfte Objekte", null, jPanel3, null);
					jPanel3.setBackground(new java.awt.Color(255,255,255));
					jPanel3.setLayout(null);
					{

					}
					{
						jAddObjectButton = new JButton();
						jPanel3.add(jAddObjectButton);
						jAddObjectButton.setText("Hinzuf�gen");
						jAddObjectButton.setBounds(231, 238, 98, 21);
						this.jAddObjectButton.addMouseListener(this);
					}
					{
						jDelObjectButton = new JButton();
						jPanel3.add(jDelObjectButton);
						jDelObjectButton.setText("L�schen");
						jDelObjectButton.setBounds(336, 238, 77, 21);
						this.jDelObjectButton.addMouseListener(this);
					}
					{
						open_button = new JButton();
						jPanel3.add(open_button);
						open_button.setText("�ffnen");
						open_button.setBounds(21, 238, 70, 21);
						open_button.addMouseListener(this);
					}
					{
					
						jList1 = new JList();
						jList1.setBounds(21, 14, 392, 210);
						jList1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						jList1.setVisibleRowCount(1);
						jScrollPane1 = new JScrollPane(jList1);
						jPanel3.add(jScrollPane1);
						jScrollPane1.setBounds(7, 14, 413, 217);
					}

				}
			}

			{
					type_add = new JButton();
					jPanel1.add(type_add);
					type_add.setText("+");
					type_add.setBounds(357, 112, 42, 21);
					type_add.addMouseListener(this);
			}
			{
					group_add = new JButton();
					jPanel1.add(group_add);
					group_add.setText("+");
					group_add.setBounds(357, 147, 42, 21);
					group_add.addMouseListener(this);
			}
			{
				edit_Type_button = new JButton();
				jPanel1.add(edit_Type_button);
				edit_Type_button.setText("...");
				edit_Type_button.setBounds(399, 112, 14, 21);
				edit_Type_button.addMouseListener(this);
			}
			{
				edit_Group_button = new JButton();
				jPanel1.add(edit_Group_button);
				edit_Group_button.setText("+");
				edit_Group_button.setBounds(399, 147, 14, 21);
				edit_Group_button.addMouseListener(this);
			}
			{
				editTC = new JButton();
				jPanel1.add(editTC);
				editTC.setText("...");
				editTC.setBounds(399, 14, 14, 21);
				editTC.addMouseListener(this);
			}
			{
				jScrollPane2 = new JScrollPane();
				jPanel1.add(jScrollPane2);
				jScrollPane2.setBounds(91, 182, 322, 70);
				jScrollPane2.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				{
					desc_area = new JTextArea();
					jScrollPane2.setViewportView(desc_area);
					desc_area.setBounds(91, 182, 21, 21);
					desc_area.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
					desc_area.setLineWrap(true);
					desc_area.setWrapStyleWord(true);

				}
			}
			{
				abort_button = new JButton();
				getContentPane().add(abort_button);
				abort_button.setText("Abbrechen");
				abort_button.setBounds(280, 301, 105, 21);
				abort_button.addMouseListener(this);
			}
			{
				ok_button = new JButton();
				getContentPane().add(ok_button);
				ok_button.setText("OK");
				ok_button.setBounds(392, 301, 49, 21);
				ok_button.addMouseListener(this);
			}
			{
				jLabel5 = new JLabel("- Zeigt Informationen an -");
				this.add(jLabel5);
				jLabel5.setBounds(7, 329, 434, 21);
				jLabel5.setBorder(BorderFactory.createTitledBorder(""));
			}
			
			lm = new DefaultListModel();
			jList1.setModel(lm);
			
			this.setSize(456, 387);
			this.setPreferredSize(new java.awt.Dimension(456, 387));
			this.setResizable(false);
			if(ID!=null && cal==null) {readData(ID);}
			
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		
	}
	/**
	 * Hier werden alle Aktionen definiert, die bei Mausklicken ausgl�st werden m�ssen
	 */
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getSource().equals(abort_button)){
			this.dispose();
		}
		if(arg0.getSource().equals(ok_button)){
			if(!newTCsender) update();
			else {
				addToList();
				dispose();
			}
			if(ok)
				dispose();
			
		}
		
		if(arg0.getSource().equals(newTC)){
			EditTerminContainerFrame frame = new EditTerminContainerFrame(this,-1);
			frame.setLocation(this.getLocation().x+20,this.getLocation().y+20);
			frame.setTitle("neuen Termincontainer hinzuf�gen");
;			frame.setVisible(true);
			}	
		if(arg0.getSource().equals(open_button)){
			int index = jList1.getSelectedIndex();
			GenericDataObject obj = null; 
			if(index != -1){
				try{
					obj = attachedObjects.get(index);
					}catch(Exception e){
						
						showErrorDialog("Fehler!","Objekt konnte nicht ge�ffnet werden!");
					}
				GenericEditFrame gef = new GenericEditFrame();
				if(obj instanceof Kontakt){
					gef.setObjectToEdit((Kontakt)obj,false);
					gef.setTableToEdit("Kontakt");
				}
				if (obj instanceof Notiz){
					gef.setObjectToEdit((Notiz)obj, false);
					gef.setTableToEdit("Notiz");
				}
				if (obj instanceof Lva){
					gef.setObjectToEdit((Lva)obj, false);
					gef.setTableToEdit("Lva");
				}				
				if (obj instanceof Lehrmittel){
					gef.setObjectToEdit((Lehrmittel)obj, false);
					gef.setTableToEdit("Lehrmittel");
				}
				if (obj instanceof Pruefung){
					gef.setObjectToEdit((Pruefung)obj, false);
					gef.setTableToEdit("Pruefung");
				}
				
				
				gef.setVisible(true);
				
			
			
			}
		}
		if(arg0.getSource().equals(jDelObjectButton)){
			
			GenericDataObject obj = null;
			if(jList1.getSelectedIndex() != -1)
				try{
					obj = attachedObjects.get(jList1.getSelectedIndex());
				}catch(Exception e){
					
					showErrorDialog("Fehler!","Objekt noch nicht in Datenbank!");
				}
			lm.removeElementAt(jList1.getSelectedIndex());
			if(obj!= null){
				
				Anhaengen_termin at = new Anhaengen_termin();
				at.setTerminId(this.ID);
				
				if (obj instanceof Notiz){
					at.setGlobalId(((Notiz)obj).getGlobalId());
				}
				if (obj instanceof Kontakt){
					at.setGlobalId(((Kontakt)obj).getGlobalId());
				}
				if (obj instanceof Lva){
					at.setGlobalId(((Lva)obj).getGlobalId());
				}
				if (obj instanceof Lehrmittel){
					at.setGlobalId(((Lehrmittel)obj).getGlobalId());
				}
				if (obj instanceof Pruefung){
					at.setGlobalId(((Pruefung)obj).getGlobalId());
				}
				
				this.gdo.setCurrentTable("anhaengen_termin");
				
				try{
					this.gdo.delDataObjects(at);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
				
				this.gdo.setCurrentTable("anhaengen_termin");
				try{
					this.gdo.delDataObjects(at);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
						}
				
				
			}
		}
		if (arg0.getSource().equals(this.jAddObjectButton)){
			InsertDialog id = new InsertDialog(this);
			if(id.getIsOk()){
				jDelObjectButton.setVisible(false);
				Integer globalId = id.getAttachableObjectId();
				List<GenericDataObject> list;
				String table = id.getAttachableObjectTableName();
				if(table.toLowerCase().equals("kontakt")){
					try {
						list = gdo.unsafeQuery("select * from " + table + " where global_id = "+ globalId,new Kontakt());
						String kon = ((Kontakt)list.get(0)).getPrename()+" " + ((Kontakt)list.get(0)).getSurname();
						lm.addElement(kon);
					} catch (Exception e){
						JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
							}
				}
				if(table.toLowerCase().equals("lva")){
					try {
						list = gdo.unsafeQuery("select * from " + table + " where global_id = "+ globalId,new Lva());
						String lva = "LVA-Nr.: " + ((Lva)list.get(0)).getLvaNr()+",         Titel: " + ((Lva)list.get(0)).getTitle(); 
						lm.addElement(lva);
					} catch (Exception e){
						JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
							}
				}
				if(table.toLowerCase().equals("notiz")){
					try {
						list = gdo.unsafeQuery("select * from " + table + " where global_id = "+ globalId,new Notiz());
						String not = ((Notiz)list.get(0)).getTitle();
						lm.addElement(not);
					} catch (Exception e){
						JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
						}
				}
				if(table.toLowerCase().equals("lehrmittel")){
					try {
						list = gdo.unsafeQuery("select * from " + table + " where global_id = "+ globalId,new Lehrmittel());
						String leh = ((Lehrmittel)list.get(0)).getName();
						lm.addElement(leh);
					} catch (Exception e){
						JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
							}
				}
				if(table.toLowerCase().equals("pruefung")){
					try {
						list = gdo.unsafeQuery("select * from " + table + " where global_id = "+ globalId,new Pruefung());
						Lva l = new Lva();
						Pruefung p = ((Pruefung)list.get(0));
						list = gdo.unsafeQuery("select * from lva where global_id = " + p.getLvaId(),new Lva());
						l= (Lva)list.get(0);
						String pr = l.getLvaNr() + " - "  + l.getTitle() + " - "+ p.getExaminer()+ " : " + p.getGrade();
						
						
						
						lm.addElement(pr);
					} catch (Exception e){
						JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
						}
				}
				
				try {
					String[] id_table = new String[2];
					id_table[0] = globalId + "";
					id_table[1] = table;
					queryList.add(id_table);
					
					
					
				} catch (Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
				
			}

		}
		if(arg0.getSource().equals(type_add)){
			GenericEditFrame gef = new GenericEditFrame();
			TerminKategorie obj = new TerminKategorie();
			gef.setObjectToEdit(obj,true);
			gef.setTableToEdit("Termin_Kategorie");
			gef.setVisible(true);
		}
		if(arg0.getSource().equals(edit_Type_button)){
			GenericEditFrame gef = new GenericEditFrame();
			if(choose_Type.getSelectedIndex()!=0){
			TerminKategorie obj = (TerminKategorie)termin_kat_data.get(choose_Type.getSelectedIndex());
			gef.setObjectToEdit(obj,false);
			gef.setTableToEdit("Termin_Kategorie");
			gef.setVisible(true);
			}
		}
		if(arg0.getSource().equals(group_add)){
			GenericEditFrame gef = new GenericEditFrame();
			AttachableObjectKategorie obj = new AttachableObjectKategorie();
			gef.setObjectToEdit(obj,true);
			gef.setTableToEdit("Attachable_Object_Kategorie");
			gef.setVisible(true);
		}
		if(arg0.getSource().equals(edit_Group_button)){
			GenericEditFrame gef = new GenericEditFrame();
			if(group_Box.getSelectedIndex() != 0){
			AttachableObjectKategorie obj = (AttachableObjectKategorie)group_data.get(group_Box.getSelectedIndex());
			gef.setObjectToEdit(obj,false);
			gef.setTableToEdit("Attachable_Object_Kategorie");
			gef.setVisible(true);
			}
		}
		if(arg0.getSource().equals(editTC)){
			if(tcTitle_box.getSelectedIndex()>0)
			{
				int id = ((TerminContainer)(termin_cont_data.get(tcTitle_box.getSelectedIndex()))).getId();
				EditTerminContainerFrame frame = new EditTerminContainerFrame(this,id);
				frame.setLocation(this.getLocation().x+20,this.getLocation().y+20);
				frame.setTitle("Termincontainer bearbeiten");
	;			frame.setVisible(true);
			}
			else showErrorDialog("Fehler!","Dieser Termincontainer kann nicht bearbeitet werden!");
			
		}
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}
	/**
	 * Hier werden alle Texte erstellt, die in der Infoleiste angezeigt werden, wenn man mit der Maus �ber eine Komponente f�hrt.
	 */
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource().equals(edit_Group_button)){
			jLabel5.setText("Gew�hlte Gruppe bearbeiten");
		}
		if(arg0.getSource().equals(group_add)){
			jLabel5.setText("Gruppe hinzuf�gen");
		}
		if(arg0.getSource().equals(edit_Type_button)){
			jLabel5.setText("Gew�hlten Termintyp bearbeiten");
		}
		if(arg0.getSource().equals(type_add)){
			jLabel5.setText("Termintyp hinzuf�gen");
		}
		if(arg0.getSource().equals(editTC)){
			jLabel5.setText("Termincontainer bearbeiten");
		}
		if(arg0.getSource().equals(newTC)){
			jLabel5.setText("neuen Termincontainer erstellen");
		}
	}
	/**
	 * Hier werden alle Texte erstellt, die in der Infoleiste angezeigt werden, wenn man mit der Maus eine Komponente verl�sst.
	 */
	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource().equals(edit_Group_button)){
			jLabel5.setText("");
		}
		if(arg0.getSource().equals(group_add)){
			jLabel5.setText("");
		}
		if(arg0.getSource().equals(edit_Type_button)){
			jLabel5.setText("");
		}
		if(arg0.getSource().equals(type_add)){
			jLabel5.setText("");
		}
		if(arg0.getSource().equals(editTC)){
			jLabel5.setText("");
		}
		if(arg0.getSource().equals(newTC)){
			jLabel5.setText("");
		}
	}
	/**
	 * Hier wird ein Fehlerdialog erstellt
	 * @param title Ist der Titel des Fehlerdialogs
	 * @param text Ist die angezeigte Meldung des Fehlerdialogs
	 */
	private void showErrorDialog(String title, String text)
	{
		InfoDialog info = new InfoDialog(this, title,text);
		info.setLocation(this.getLocation().x+200,this.getLocation().y+200);
		info.setModal(true);
		info.setVisible(true);	
	}
	/**
	 * Hier wird ein Formatter erstellt
	 * @param s
	 * @return ist ein funktionierender MaskFormatter
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
	 * Hier wird die Termincontainerliste in der Combobox der Termincontainer aktualisiert. 
	 *
	 */
	public void updateTCList()
	{
		List<GenericDataObject> tcList;
		TerminContainer cont = new TerminContainer();
		
		
		tcTitle_box.setModel(new DefaultComboBoxModel());
		try
		{
			tcList = gdo.unsafeQuery("select * from termincontainer order by id",cont);
			
			for(int i = 0;i<tcList.size();i++)
			{
				cont = (TerminContainer)tcList.get(i);
				tcTitle_box.addItem(cont.getTitle());
				
			}		
			String query = "select * from termincontainer";
			
			termin_cont_data = gdo.unsafeQuery(query,cont);
			tcTitle_box.setSelectedIndex(selectedTC);
		}
		catch (Exception ex)
		{
			
		}
	}
	/**
	 * Hier wird die Liste der angeh�ngten Objekte geladen.
	 *
	 */
	private void loadObjectList(){
		this.attachedObjects = new ArrayList<GenericDataObject>();
		lm.clear();
		List<GenericDataObject> objs = null;
		gdo.setCurrentTable("anhaengen_termin");
		try {
			objs = gdo.unsafeQuery("SELECT * FROM anhaengen_termin where termin_id="+this.ID, new Anhaengen_termin());
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		Iterator<GenericDataObject> iter = objs.iterator();
		String tableName = "";
		int globalId = -1;
		while(iter.hasNext()){
			Anhaengen_termin t = (Anhaengen_termin)iter.next();
			tableName = t.getTable_Name();
			globalId = t.getGlobalId();
			List<GenericDataObject> ob = null;
			tableName = tableName.toLowerCase();
			
			if(tableName.equals("kontakt")){
				gdo.setCurrentTable("kontakt");
				try {
					ob = gdo.unsafeQuery("select * from kontakt where global_id="+globalId, new Kontakt());
				} 
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
						}
				Iterator<GenericDataObject> iter2 = ob.iterator();
				Kontakt k;
				while(iter2.hasNext())
				{
					k = (Kontakt) iter2.next();
					this.attachedObjects.add(k);
					lm.addElement(k.getPrename() + " "+ k.getSurname());	
				}
			}
			if(tableName.equals("notiz")){
				gdo.setCurrentTable("notiz");
				try {
					ob = gdo.unsafeQuery("select * from notiz where global_id="+globalId, new Notiz());
				} 
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
						}
				Iterator<GenericDataObject> iter2 = ob.iterator();
				Notiz n;
				while(iter2.hasNext())
				{
					n = (Notiz) iter2.next();
					this.attachedObjects.add(n);
					lm.addElement(n.getTitle());	
				}
			}
			if(tableName.equals("lva")){
				gdo.setCurrentTable("lva");
				try {
					ob = gdo.unsafeQuery("select * from lva where global_id="+globalId, new Lva());
				} 
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
						}
				Iterator<GenericDataObject> iter2 = ob.iterator();
				Lva l;
				while(iter2.hasNext())
				{
					l = (Lva) iter2.next();
					this.attachedObjects.add(l);
					lm.addElement(l.getLvaNr() + " , "+l.getTitle());	
				}
			}
			if(tableName.equals("lehrmittel")){
				gdo.setCurrentTable("lehrmittel");
				try {
					ob = gdo.unsafeQuery("select * from lehrmittel where global_id="+globalId, new Lehrmittel());
				} 
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
				Iterator<GenericDataObject> iter2 = ob.iterator();
				Lehrmittel l;
				while(iter2.hasNext())
				{
					l = (Lehrmittel) iter2.next();
					this.attachedObjects.add(l);
					lm.addElement(l.getName());	
				}
			}
			if(tableName.equals("pruefung")){
				gdo.setCurrentTable("pruefung");
				try {
					ob = gdo.unsafeQuery("select * from pruefung where global_id="+globalId, new Pruefung());
				} 
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
						}
				Iterator<GenericDataObject> iter2 = ob.iterator();
				Pruefung p;
				while(iter2.hasNext())
				{
					p = (Pruefung) iter2.next();
					this.attachedObjects.add(p);
					List<GenericDataObject> list = null;
					Lva l = new Lva();
					try {
						list = gdo.unsafeQuery("select * from lva where global_id = " + p.getLvaId(),new Lva());
						l= (Lva)list.get(0);
					} catch (Exception e){
						JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
							}
					lm.addElement(l.getLvaNr() + " - "  + l.getTitle() + " - "+ p.getExaminer()+ " : " + p.getGrade());	
				}
			}
			
			
			jList1.setModel(lm);
		}
	}
	/**
	 * Hier wird ein Termin zu der Liste eines Termincontainers hinzugef�gt, wenn das TerminEditFrame aus einem Termincontainer aus aufgerufen wurde.
	 *
	 */
	private void addToList()
	{
		EditTerminContainerFrame actor = (EditTerminContainerFrame)owner;
		List<Termin> data = new ArrayList<Termin>();
		readFieldData();
		Termin t = new Termin();
		t.setDate(Timestamp.valueOf(date));
		t.setSecondaryTitle(sec_title);
		t.setDuration(duration);
		t.setPlace(place);
		t.setTerminKategorieId(Integer.parseInt(tkat));
		data.add(t);
		actor.updateTerminList(data);
		
	}
	/**
	 * Hier werden die Daten aus den Eingabefeldern ausgelesen.
	 *
	 */
	private void readFieldData()
	{
	
		int termin_kat = ((TerminKategorie)(termin_kat_data.get(choose_Type.getSelectedIndex()))).getId();
		tkat = "" + termin_kat;
	
		sec_title = sec_titlefield.getText();		
		sec_title = sec_title.replace("'".toCharArray()[0],'�');
			
		date = dateField.getText();
		
		Date dat = new Date(c.getTimeInMillis());
		date = dat.toString();
		date = date.substring(0,10) + " " + timeField.getText()+":00.0";
		
		desc = desc_area.getText();
		desc = desc.replace("'".toCharArray()[0],'�');
		
		place = place_field.getText();
		
		place = place.replace("'".toCharArray()[0],'�');
		
		try{
			
		
		if(timeField.getText().length()!=5) throw new Exception();

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
		
		read_ok=true;
		
		}catch(Exception e){
			showErrorDialog("Falsche Eingabe","Felder wurden ung�ltig oder nicht ausgef�llt!");
		}
	
		if(sec_title.length()<1) sec_title = tcTitle_box.getSelectedItem().toString();
	
	}
}