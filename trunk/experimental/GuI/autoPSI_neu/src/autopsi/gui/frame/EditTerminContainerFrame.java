package autopsi.gui.frame;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;


import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import autopsi.basis.AutopsiConfigurator;
import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.exception.EAttributeNotFound;
import autopsi.database.exception.EDatabase;
import autopsi.database.exception.EDatabaseConnection;
import autopsi.database.table.Anhaengen_termincontainer;
import autopsi.database.table.AttachableObjectKategorie;
import autopsi.database.table.Kontakt;
import autopsi.database.table.Lehrmittel;
import autopsi.database.table.Lva;
import autopsi.database.table.Notiz;
import autopsi.database.table.Pruefung;
import autopsi.database.table.Termin;
import autopsi.database.table.TerminContainer;
import autopsi.gui.DateConverter;

/** The EditTerminContainerFrame is shown when the user wants
 * to edit or add a date container. it features several text fields,
 * and listings for attached objects and dates.
 * 
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

	
	private static final long serialVersionUID = 1L;

	{
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JTabbedPane jTabbedPane1;
	private JList terminList;
	private JScrollPane jScrollPane1;
	private JButton newTerminReihe;
	private JLabel jLabel7;
	private JLabel jLabel3;
	private List<GenericDataObject> attachedObjects;
	private JButton openTermin;
	private JButton deleteTermin;
	private JButton newTermin;
	private JTextField sucheTermin_field;

	private JButton open_button;
	private JButton jDelObjectButton;
	private JButton jAddObjectButton;
	private JList jList1;
	private JPanel jPanel1;
	private JTextArea desc_area;
	private JLabel jLabel2;
	private JTextField title_field;
	private JButton add_group;
	private JLabel jLabel4;
	private JScrollPane jScrollPane2;
	private JButton edit_group;
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
	private List<GenericDataObject> group_data;
	private List<GenericDataObject> id_list;
	private List<GenericDataObject> termin_list;
	private List<Termin> container_termine = new ArrayList<Termin>();
	private JFrame owner;
	private int selectedGroup;
	private DefaultComboBoxModel terminModel = new DefaultComboBoxModel();
	private JScrollPane jScrollPane;
	private DefaultListModel lm;
	private int lastID = 0;
	private ArrayList<String[]> queryList = new ArrayList<String[]>();
	
	
	/**
	 * If a date container is edited, this method reads the data from the database which
	 * is shown in the textfields and lists.
	 * @throws EDatabaseConnection
	 * @throws EAttributeNotFound
	 * @throws EDatabase
	 */
	void readData() throws EDatabaseConnection, EAttributeNotFound, EDatabase{
		TerminContainer lookup = new TerminContainer();
		lookup.setId(ID);
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
		loadObjectList();
		firstUpdateTerminList();	
	}
	
	/**
	 * Updates the data for an edited date container
	 *
	 */
	private void update(){
		try{
			String query="";
			String title =title_field.getText();
			title = title.replace("'".toCharArray()[0],'�');
			String desc = desc_area.getText();
			desc.replace("'".toCharArray()[0],'�');
			
			int group_id = ((AttachableObjectKategorie)(group_data.get(jGroupBox.getSelectedIndex()))).getId();
			if (ID<0)//Wenn der Termincontainer neu erstellt werden muss:
				{
				//Erstelle zuerst den Termin:
				query = "insert into termincontainer (title,group_id, description) values ('"+title+ "',"+group_id+",'"+desc+"')";
				TerminContainer vorlage = new TerminContainer();
				gdo.unsafeQuery(query,vorlage);
				//Hol dann die ID des Termins
				id_list = gdo.unsafeQuery("select * from termincontainer where id = identity()",vorlage);
				try
				{
					lastID = ((TerminContainer)id_list.get(0)).getId();
					for(int i = 0;i<container_termine.size();i++)//F�ge die erstellten Termine ein
					{
						container_termine.get(i).setTerminContainerID(lastID);
						gdo.unsafeQuery("insert into termin (GROUP_ID,TERMIN_KATEGORIE_ID, secondary_title, description, date, duration, place, termincontainer_id) values ("+group_id+ ","+container_termine.get(i).getTerminKategorieId()+ ",'"+container_termine.get(i).getSecondaryTitle()+"','"+container_termine.get(i).getDescription()+"','"+container_termine.get(i).getDate()+"',"+container_termine.get(i).getDuration()+",'"+container_termine.get(i).getPlace()+"',"+lastID+")",new Termin());
					}
					
					for(int j = 0;j<queryList.size();j++)//H�nge die Attachableobjects an den Termincontainer an
					{
						String query2 = "insert into anhaengen_termincontainer values (" + lastID+","+ Integer.parseInt(queryList.get(j)[0])+",'"+queryList.get(j)[1]+"')";
						gdo.unsafeQuery(query2,new Anhaengen_termincontainer());
					
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
				}
			else      // Termincontainer wird upgedatet
				{
					for(int i = 0;i<container_termine.size();i++)
					{
						container_termine.get(i).setTerminContainerID(lastID);
						gdo.unsafeQuery("insert into termin (GROUP_ID,TERMIN_KATEGORIE_ID, secondary_title, description, date, duration, place, termincontainer_id) values ("+group_id+ ","+container_termine.get(i).getTerminKategorieId()+ ",'"+container_termine.get(i).getSecondaryTitle()+"','"+container_termine.get(i).getDescription()+"','"+container_termine.get(i).getDate()+"',"+container_termine.get(i).getDuration()+",'"+container_termine.get(i).getPlace()+"',"+ID+")",new Termin());
						
					}
					for(int j = 0;j<queryList.size();j++)
					{
						String query2 = "insert into anhaengen_termincontainer values (" + ID+","+ Integer.parseInt(queryList.get(j)[0])+",'"+queryList.get(j)[1]+"')";
						gdo.unsafeQuery(query2,new Anhaengen_termincontainer());
						
					}
					query = "update termincontainer  set title = '" + title + "', group_id = " + group_id  + ", description='"+desc+"' where id="+ID;
					
					TerminContainer vorlage = new TerminContainer();
					gdo.unsafeQuery(query,vorlage);
				}
			
			if(owner instanceof mainFrame)
			{
				mainFrame actor = (mainFrame)owner;
				actor.updateInfoBar(false);
				actor.updateTable();
			}
			else if(owner instanceof EditTerminFrame)
			{
				EditTerminFrame actor = (EditTerminFrame)owner;
				actor.updateTCList();
			}
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
	}
	/**
	 * Constructor for the EditTerminContainerFrame.
	 * @param owner the frame from which the EditTerminContainerFrame is launched.
	 * @param id The id from the date container to be edited
	 */
	public EditTerminContainerFrame(JFrame owner, int id) { 
		super();
		this.ID = id;
		this.owner = owner;
		gdo = new GenericDAO();
		gdo.setCurrentTable("termincontainer");
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
	 * Builds the user interface (buttons, tables, panels etc.)
	 * 
	 */
	private void initGUI() {
		try {
			this.setIconImage(new ImageIcon(AutopsiConfigurator.images + "/autopsi.png").getImage() );
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
					{
						jScrollPane2 = new JScrollPane();
						jPanel1.add(jScrollPane2);
						jScrollPane2.setBounds(91, 77, 322, 175);
						jScrollPane2.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						{
							desc_area = new JTextArea();
							jScrollPane2.setViewportView(desc_area);
							desc_area.setBounds(91, 70, 21, 21);
							desc_area.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
							desc_area.setWrapStyleWord(true);
							desc_area.setLineWrap(true);

						}
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
						jAddObjectButton = new JButton();
						jPanel3.add(jAddObjectButton);
						jAddObjectButton.setText("Hinzuf�gen");
						jAddObjectButton.setBounds(231, 238, 98, 21);
						jAddObjectButton.addMouseListener(this);
					}
					{
						jDelObjectButton = new JButton();
						jPanel3.add(jDelObjectButton);
						jDelObjectButton.setText("L�schen");
						jDelObjectButton.setBounds(336, 238, 77, 21);
						jDelObjectButton.addMouseListener(this);
					}
					{
						open_button = new JButton();
						jPanel3.add(open_button);
						open_button.setText("�ffnen");
						open_button.setBounds(14, 238, 70, 21);
						open_button.addMouseListener(this);
					}
					{
						jList1 = new JList();
						jPanel3.add(jList1);
						jList1.setBounds(13, 15, 397, 208);
						jList1.setBorder(new LineBorder(new java.awt.Color(
							0,
							0,
							0), 1, false));
						jList1.setVisibleRowCount(1);
						lm = new DefaultListModel();
						jList1.setModel(lm);
						jScrollPane = new JScrollPane(jList1);
						jScrollPane.setBounds(14, 14, 399, 210);
						jPanel3.add(jScrollPane);
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

								if(sucheTermin_field.getText().length()>0)
								{
									for(int i = 0;i<terminModel.getSize();i++)
									{
										if(terminModel.getElementAt(i).toString().toLowerCase().contains(sucheTermin_field.getText().toLowerCase()))
										{
											model2.addElement(terminModel.getElementAt(i));
										}
									}
									terminModel.removeAllElements();
									for(int k = 0;k<model2.getSize();k++)
									{
										terminModel.addElement(model2.getElementAt(k));
									}
									terminList.setModel(terminModel);
								}
								else 
								{
									terminModel.removeAllElements();
									for(int j = 0; j < container_termine.size();j++){
										Termin term = (Termin)container_termine.get(j);
										String datum = term.getDate().toString().substring(8,10)+"-"+term.getDate().toString().substring(5,7)+"-"+
										term.getDate().toString().substring(0,4)+" um "+term.getDate().toString().substring(11,16);
										String termin = datum+": "+((Termin)container_termine.get(j)).getSecondaryTitle() +",     Ort: " +((Termin)container_termine.get(j)).getPlace();
										
										terminModel.addElement(termin);

									}
								}
								
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
						newTermin = new JButton();
						jPanel2.add(newTermin);
						newTermin.setBounds(238, 235, 42, 28);
						newTermin.setIcon(new ImageIcon(AutopsiConfigurator.images+"/newTermin.GIF"));
						newTermin.addMouseListener(this);
					}
					{
						deleteTermin = new JButton();
						jPanel2.add(deleteTermin);
						deleteTermin.setBounds(371, 235, 42, 28);
						deleteTermin.setIcon(new ImageIcon(AutopsiConfigurator.images+"/deleteTermin.GIF"));
						deleteTermin.addMouseListener(this);
					}
					{
						
					}
					{
						if(ID>0)
						{
							openTermin = new JButton();
							jPanel2.add(openTermin);
							openTermin.setBounds(7, 235, 42, 28);
							openTermin.setIcon(new ImageIcon(AutopsiConfigurator.images+"/goToTermin.GIF"));
							openTermin.addMouseListener(this);
						}
						
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
						newTerminReihe.setBounds(280, 235, 56, 28);
						newTerminReihe.setIcon(new ImageIcon(AutopsiConfigurator.images+"/newTerminReihe.GIF"));
						newTerminReihe.addMouseListener(this);
					}
					{
						jScrollPane1 = new JScrollPane();
						jPanel2.add(jScrollPane1);
						jScrollPane1.setBounds(7, 35, 406, 196);
						jScrollPane1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						{
							terminList = new JList();
							jScrollPane1.setViewportView(terminList);
							terminList.setModel(terminModel);
							terminList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						}
					}
				
				}
			}
			{
				abort_button = new JButton();
				getContentPane().add(abort_button);
				abort_button.setText("Abbrechen");
				abort_button.setBounds(273, 315, 105, 21);
				abort_button.addMouseListener(this);
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
			
			
			
			
			
			if(ID>-1){ readData();}//Wenn der Termincontainer schon existiert, so lade die Daten aus der Datenbank
			
				
			pack();
			this.setSize(449, 408);
			this.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Event Listener for mouse activities
	 * @param arg0 The event given by the controls who are added to the MouseListener
	 */
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource().equals(deleteTermin)){
			
				try
				{
					String t = terminModel.getElementAt(terminList.getSelectedIndex()).toString();
					
					for (int i = 0;i<container_termine.size();i++)
					{
						int day1 = Integer.parseInt(t.substring(0,2));
						int day2 = Integer.parseInt(container_termine.get(i).getDate().toString().substring(8,10));
					
						int month1 = Integer.parseInt(t.substring(3,5));
						int month2 = Integer.parseInt(container_termine.get(i).getDate().toString().substring(5,7));
						
						int year1 = Integer.parseInt(t.substring(6,10));
						int year2 = Integer.parseInt(container_termine.get(i).getDate().toString().substring(0,4));
						
						String time1 = t.substring(14,19);
						String time2 = container_termine.get(i).getDate().toString().substring(11,16);

						if(day1==day2 && month1==month2 && year1==year2 && time1.equals(time2))
						{
							
							container_termine.remove(i);
							if(ID>0)
							{
								try
								{
									String date = year1+"-"+month1+"-"+day1+" "+time1+":00.0";
									gdo.unsafeQuery("delete from termin where date='"+date+"'",new Termin());
									
									if(owner instanceof mainFrame)
									{
										mainFrame actor = (mainFrame)owner;
										actor.updateTable();
										GregorianCalendar greg = new GregorianCalendar();
										greg.setTime(actor.getMarker().getTime());
										actor.loadTerminList(false, greg);
										this.toFront();
									}
									
								}
								catch(Exception ex){
									JOptionPane.showMessageDialog(null, "Error: "+ex.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
									}
							}
							
						}
					}
					terminModel.removeAllElements();
					for(int j = 0; j < container_termine.size();j++){
						Termin term = (Termin)container_termine.get(j);
						String datum = term.getDate().toString().substring(8,10)+"-"+term.getDate().toString().substring(5,7)+"-"+
						term.getDate().toString().substring(0,4)+" um "+term.getDate().toString().substring(11,16);
						String termin = datum+": "+((Termin)container_termine.get(j)).getSecondaryTitle() +",     Ort: " +((Termin)container_termine.get(j)).getPlace();
						
						terminModel.addElement(termin);
						sucheTermin_field.getKeyListeners()[0].keyTyped(null);

					}
					
					
				}
				catch(Exception ex)
				{
					showErrorDialog("Fehler!",ex.toString());
				}
			
			}
		
		
		if(arg0.getSource().equals(abort_button)){
			this.dispose();
		}
		if(arg0.getSource().equals(ok_button)){
			update();
			dispose();
		}
		
		
		if(arg0.getSource().equals(newTermin)){
			EditTerminFrame etframe = new EditTerminFrame(this,null,null,false);
			etframe.setVisible(true);
			
			
		}
		
		if(arg0.getSource().equals(newTerminReihe)){
			TerminReiheFrame terminreihe = new TerminReiheFrame(this, title_field.getText());
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
		if(arg0.getSource().equals(jAddObjectButton)){//F�gt ein AttachableObject mit passenden Attributen zur Liste hinzu
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
				this.gdo.setCurrentTable("anhaengen_termincontainer");
				try {
					String[] id_table = new String[2];
					id_table[0] = globalId + "";
					id_table[1] = table;
					queryList.add(id_table);//Speichert die anzuh�ngenden ObjektIDs und die Tabllennamen
					
					
				} catch (Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
				
			}
			
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
			if(jList1.getSelectedIndex() != -1){
				try{
					obj = attachedObjects.get(jList1.getSelectedIndex());
				}catch(Exception e){
					
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					
				}
			lm.removeElementAt(jList1.getSelectedIndex());
			}
				
				
			if(obj!= null){
				
				Anhaengen_termincontainer at = new Anhaengen_termincontainer();
				at.setTermincontainerId(this.ID);
				
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
				
				this.gdo.setCurrentTable("anhaengen_termincontainer");
				
				try{
					this.gdo.delDataObjects(at);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
				
				this.gdo.setCurrentTable("anhaengen_termincontainer");
				try{
					this.gdo.delDataObjects(at);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
				
				this.loadObjectList();
			}
		}
		if(arg0.getSource().equals(openTermin)){//Geht im Kalender zum gew�hlten Termin
			try
			{
				if(owner instanceof mainFrame && terminList.getSelectedIndex()>-1)
				{
					mainFrame actor = (mainFrame)owner;
					GregorianCalendar greg = new GregorianCalendar();
					greg.set(Calendar.YEAR,Integer.parseInt(terminModel.getElementAt(terminList.getSelectedIndex()).toString().substring(6,10)));
					greg.set(Calendar.MONTH,Integer.parseInt(terminModel.getElementAt(terminList.getSelectedIndex()).toString().substring(3,5))-1);
					greg.set(Calendar.DAY_OF_MONTH,Integer.parseInt(terminModel.getElementAt(terminList.getSelectedIndex()).toString().substring(0,2)));
					GregorianCalendar greg2 = new GregorianCalendar();				
					greg2.setTime(greg.getTime());

					actor.setMarker(greg2);
					actor.setTimeSpace(greg);
					actor.layoutTable();
					actor.loadTerminList(false, greg);
					this.toFront();
				}
			}
			catch(Exception ex) {}
			
		}
	}
	
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource().equals(newTermin)){
			jLabel7.setText("Erstellt einen neuen Termin in diesem Termincontainer");
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
		if(arg0.getSource().equals(newTermin)){
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
	
	/**
	 * Loads the list of dates for the first time (when the frame is launched)
	 *
	 */
	private void firstUpdateTerminList(){
		try {
			termin_list = gdo.unsafeQuery("select * from termin where TERMINCONTAINER_ID = "+ID,new Termin());
			for(int i = 0; i < termin_list.size();i++){
				Termin term = (Termin)termin_list.get(i);
				String datum = term.getDate().toString().substring(8,10)+"-"+term.getDate().toString().substring(5,7)+"-"+
				term.getDate().toString().substring(0,4)+" um "+term.getDate().toString().substring(11,16);
				String termin = datum+": "+((Termin)termin_list.get(i)).getSecondaryTitle() +",     Ort: " +((Termin)termin_list.get(i)).getPlace();
				
				terminModel.addElement(termin);
			}
		
		
		
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
	}
	
	/**
	 * Updates the list of dates
	 * @param termine the list of dates to be added to the dates list
	 */
	public void updateTerminList(List<Termin> termine)
	{
		for(int i = 0;i<termine.size();i++)
		{
			String datum = termine.get(i).getDate().toString().substring(8,10)+"-"+termine.get(i).getDate().toString().substring(5,7)+"-"+
							termine.get(i).getDate().toString().substring(0,4)+" um "+termine.get(i).getDate().toString().substring(11,16);
			String termin = datum+": "+termine.get(i).getSecondaryTitle()+",    Ort: "+termine.get(i).getPlace();
			
			container_termine.add(termine.get(i));
			terminModel.addElement(termin);		
		}
	}
	
	/**
	 * mask formatter
	 * @param s The mask (e. g. ##-##-####, whereas # stands for a digit
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
	 * displays an error dialog in case of system or handling errors
	 * @param title The title of the error dialog
	 * @param text The text to be shown in the dialog
	 */
	private void showErrorDialog(String title, String text)
	{
		InfoDialog info = new InfoDialog(this, title,text);
		info.setLocation(this.getLocation().x+200,this.getLocation().y+200);
		info.setModal(true);
		info.setVisible(true);	
	}
	
	/**
	 * Loads the list of attached objects
	 *
	 */
	private void loadObjectList(){
		this.attachedObjects = new ArrayList<GenericDataObject>();
		lm.clear();
		List<GenericDataObject> objs = null;
		gdo.setCurrentTable("anhaengen_termincontainer");
		try {
			objs = gdo.unsafeQuery("SELECT * FROM anhaengen_termincontainer where termincontainer_id="+this.ID, new Anhaengen_termincontainer());
		
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		Iterator<GenericDataObject> iter = objs.iterator();
		String tableName = "";
		int globalId = -1;
		while(iter.hasNext()){
			Anhaengen_termincontainer t = (Anhaengen_termincontainer)iter.next();
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
			}
			
			
			jList1.setModel(lm);
		}
	}