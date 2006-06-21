package autopsi.gui.frame;
import java.awt.Point;
import java.awt.event.*;
import java.util.*;
import javax.swing.BorderFactory;
import java.awt.*;
import javax.swing.*;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import javax.swing.JTable;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.*;

import autopsi.basis.model.*;
import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.exception.EAttributeNotFound;
import autopsi.database.exception.EDatabase;
import autopsi.database.exception.EDatabaseConnection;
import autopsi.database.table.*;
import autopsi.gui.DateConverter;
import autopsi.gui.MonthRenderer;
import autopsi.gui.WeekRenderer;
import autopsi.gui.AddAttachableObject;
import autopsi.javaspace.ObjectSpaceSharer;
import autopsi.javaspace.ServiceCommunicator;
import autopsi.javaspace.SpaceThread;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import javax.swing.text.MaskFormatter;


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
public class mainFrame extends javax.swing.JFrame implements java.awt.event.MouseListener,java.awt.event.MouseMotionListener,WindowListener, ActionListener{

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {

			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 7899036380605041556L;
	private JTable table;
	private JMenuItem menu_add_Notiz;
	private JMenuItem menu_add_Lva;
	private JLabel lblZeit;
	private JPanel tab0;
	private JPanel tab2;
	private JTextPane lblBeschreibung;
	private JTabbedPane infobar;
	private JLabel toolBar;
	private JCheckBox zoomBox;
	private JLabel button_editTC;
	private JLabel button_deleteTC;
	private JLabel jLabel4;
	private JLabel button_newTC;
	private JLabel button_deleteTermin;
	private JLabel button_editTermin;
	private JLabel button_newTermin;
	private JLabel button_search;
	private JLabel button_monthBack;
	private JLabel button_weekBack;
	private JLabel button_dayBack;
	private JLabel button_dayNext;
	private JLabel button_weekNext;
	private JLabel button_monthNext;
	private JLabel button_view1;
	private JLabel button_view2;
	private JLabel button_space;
	private JLabel button_jumpToToday;
	private JLabel jLabel3;
	private JTable timetable;
	private JMenuItem view_dayNext;
	private JMenuItem view_weekNext;
	private JMenuItem view_monthNext;
	private JMenuItem view_dayBack;
	private JMenuItem view_weekBack;
	private JMenuItem view_monthBack;
	private JSeparator jSeparator2;
	private JMenuItem view_week;
	private JMenuItem view_month;
	private JMenu view;
	private JMenuItem menu_add_LM;
	private JMenuItem menu_add_Pruefung;
	private JMenuItem menu_add_Kontakt;
	private JMenuItem menu_add_T;
	private JMenuItem menu_add_TC;
	private JMenu menu_add;
	private JFormattedTextField dateJumper;
	private JLabel jLabel1;
	private JCheckBox showTCObjects;
	private JCheckBox showTObjects;
	private JList objectList;
	private JScrollPane objectScroller;
	private JScrollPane TCterminScroller;
	private JList todayList;
	private JScrollPane todayScrollPane;
	private JLabel lblToday;
	private JPanel today;
	private JLabel tableBar;
	private JList listTC2;
	private JLabel jLabel7;
	private JLabel lblTerminContainer;
	private JLabel lblOrt;
	private JLabel lblDatum;
	private JLabel lblDatumShadow;
	private JLabel lblTermin;
	private JPanel tab1;
	private JMenuBar mainMenu;
	private JLabel statusBar;
	private int terminId=-1;
	private Termin[] currentValue;
	String[] data;
	private Point currentCell = new Point();
	private int selection=-1;
	private boolean online = false;
	private JLabel lblMonth = new JLabel("tutu");
	private JLabel lblMonthShadow = new JLabel("tutu");
	boolean mouseEntered,mouseDown = false;
	Date datum = new Date();
	int x = 0;
	int row1,row2,col1,col2 = 0;
	DateConverter converter = new DateConverter();
	boolean viewMonth = true;
	Timestamp tsBegin=null;
	Timestamp tsEnd=null;
	GregorianCalendar calStart= new GregorianCalendar(); //Das Startdatum
	GregorianCalendar calEnd= new GregorianCalendar(); //Das Enddatum
	GregorianCalendar c = new GregorianCalendar();
	GregorianCalendar c_marker = new GregorianCalendar();
	DefaultComboBoxModel todayListModel = new DefaultComboBoxModel();
	DefaultComboBoxModel listTC2Model = new DefaultComboBoxModel(); //Listmodel für verwandte Termine
	DefaultComboBoxModel objectListModel = new DefaultComboBoxModel();
	public boolean delete_ok = false;
	protected ObjectSpaceSharer oss;
	private String deletedObject = "";
	private Termin[] relatedTermine = null;
	private int tcID;
	private int doubleClickCounter = 0;
	
	public mainFrame() {
		super();
		initGUI();	
		loadTerminList(true);
	}
	
	private void setTable()
	{
		this.addWindowListener(this);
		table = new JTable();	
		setTimeSpace(calStart);
		

		table.setBounds(289, 53, 651, 465);
		table.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
		table.setRowSelectionAllowed(false);
		table.setSelectionForeground(new java.awt.Color(0,0,0));
		table.setSelectionBackground(new java.awt.Color(255,255,255));
		table.setDefaultRenderer(Termin[].class, new MonthRenderer(null));
		table.setRowHeight(93);
		table.addMouseListener(this);
		table.addMouseMotionListener(this);
						
		DefaultTableColumnModel cm = new DefaultTableColumnModel();
		for (int i=0;i<7;i++)
		{
			TableColumn col = new TableColumn(0, 97);
			cm.addColumn(col);				
		}
		table.setColumnModel(cm);
		this.getContentPane().add(table);
		table.setCellSelectionEnabled(true);
		table.setGridColor(new java.awt.Color(0,0,0));
		table.setIntercellSpacing(new java.awt.Dimension(2, 2));
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setBackground(new java.awt.Color(230,230,230));
		table.setAutoscrolls(false);

		MonatTM tablemodel = new MonatTM(tsBegin,tsEnd);
		table.setModel(tablemodel);
	}
	
	private void initGUI() {
		try {
			
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(235,235,235));
			this.setMaximumSize(new java.awt.Dimension(900, 653));
			this.setResizable(false);
			this.setTitle("autoPSI");
			
			setTable();
			{
				statusBar = new JLabel();
				getContentPane().add(statusBar);
				statusBar.setBounds(7, 525, 672, 21);
				statusBar.setBackground(new java.awt.Color(192,192,192));
				statusBar.setIcon(new ImageIcon("src/images/info.GIF"));
				statusBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			}
			{
				today = new JPanel();
				today.setLayout(null);
				getContentPane().add(today);
				today.setBounds(2, 53, 252, 175);
				today.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				today.setBackground(new java.awt.Color(60,80,120));
				{
					lblToday = new JLabel();
					today.add(lblToday);
					lblToday.setText("heutige Termine:");
					lblToday.setBounds(7, 7, 238, 14);
					lblToday.setForeground(new java.awt.Color(255,255,255));
				}
				{
					todayScrollPane = new JScrollPane();
					
					today.add(todayScrollPane);
					todayScrollPane.setBounds(7, 21, 238, 147);
					todayScrollPane.setBackground(new java.awt.Color(255,255,255));
					todayScrollPane.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					{
						
						todayList = new JList();
						todayList.addMouseListener(this);
						todayScrollPane.setViewportView(todayList);
						todayList.setModel(todayListModel);
					
					}
				}
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Springe zu Datum: (TT-MM-JJJJ)");
				jLabel1.setBounds(686, 525, 168, 21);
			}
			{
				dateJumper = new JFormattedTextField(createFormatter("##-##-####"));
				getContentPane().add(dateJumper);
				dateJumper.setText("");
				dateJumper.setBounds(854, 525, 84, 21);
				dateJumper.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				dateJumper.addKeyListener(new KeyListener(){

					public void keyTyped(KeyEvent arg0) {
						if(arg0.getKeyChar()==(KeyEvent.VK_ENTER)){
							try
							{
								
								SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
								Date jumpDate = sf.parse(dateJumper.getText());
								c_marker.setTime(jumpDate);
								c.setTime(c_marker.getTime());
								if(viewMonth)
								{
									table.setDefaultRenderer(Termin[].class, new MonthRenderer(c_marker));
								}
								else
								{
									table.setDefaultRenderer(Termin[].class, new WeekRenderer(c_marker));
								}			
								setTimeSpace(c);
								layoutTable();
							}
							catch(Exception ex)
							{
								showErrorDialog("Falsches Datumsformat","Geben Sie ein Datum im Format TT.MM.JJJJ ein!");
								dateJumper.setText("");
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
				
			

				infobar = new JTabbedPane();
				getContentPane().add(infobar);
				infobar.setBounds(2, 259, 252, 259);
				infobar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				infobar.setBackground(new java.awt.Color(102, 136, 174));
				{
					tab0 = new JPanel();
					infobar.addTab("Termin-Info" + "", null, tab0, null);
					tab0.setLayout(null);
					tab0.setPreferredSize(new java.awt.Dimension(224, 231));
					tab0.setEnabled(false);
					tab0.setBackground(new java.awt.Color(240,240,240));
					{
						lblBeschreibung = new JTextPane();
						tab0.add(lblBeschreibung);
						lblBeschreibung.setBounds(7, 98, 231, 126);
						lblBeschreibung.setOpaque(false);
						lblBeschreibung.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					}
					{
						lblTerminContainer = new JLabel();
						tab0.add(lblTerminContainer);
						lblTerminContainer.setBounds(7, 28, 231, 21);
						lblTerminContainer.setIcon(new ImageIcon(
							"src/images/lva.GIF"));
						lblTerminContainer.setFont(new java.awt.Font(
							"Tahoma",
							1,
							10));
					}
					{
						lblTermin = new JLabel();
						tab0.add(lblTermin);
						lblTermin
							.setIcon(new ImageIcon("src/images/termin.GIF"));
						lblTermin.setBounds(7, 7, 231, 21);
						lblTermin.setFont(new java.awt.Font("Tahoma", 1, 12));
					}
					{
						lblOrt = new JLabel();
						tab0.add(lblOrt);
						lblOrt.setText("Ort:");
						lblOrt.setBounds(7, 56, 231, 14);
						lblOrt.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						lblZeit = new JLabel();
						tab0.add(lblZeit);
						lblZeit.setText("Beginn:");
						lblZeit.setBounds(7, 74, 231, 14);
						lblZeit.setFont(new java.awt.Font("Tahoma",0,12));
					}
				}
				{
					tab1 = new JPanel();
					infobar.addTab("Objekte", null, tab1, null);
					tab1.setLayout(null);
					tab1.setBackground(new java.awt.Color(240,240,240));
					tab1.setPreferredSize(new java.awt.Dimension(220, 336));
					{
						objectScroller = new JScrollPane();
						tab1.add(objectScroller);
						objectScroller.setBounds(7, 49, 231, 175);
						objectScroller.setBorder(new LineBorder(
							new java.awt.Color(0, 0, 0),
							1,
							false));
						{
							objectList = new JList();
							objectScroller.setViewportView(objectList);
							objectList.setModel(objectListModel);
						}
					}
					{
						showTObjects = new JCheckBox();
						tab1.add(showTObjects);
						showTObjects.setText("zeige Objekte des Termins");
						showTObjects.setBounds(3, 7, 161, 14);
						showTObjects.setOpaque(false);
						showTObjects.setSelected(true);
						showTObjects.addMouseMotionListener(this);
					}
					{
						showTCObjects = new JCheckBox();
						tab1.add(showTCObjects);
						showTCObjects
							.setText("zeige Objekte des Termincontainers");
						showTCObjects.setBounds(3, 28, 224, 14);
						showTCObjects.setOpaque(false);
						showTCObjects.setSelected(true);
						showTCObjects.addMouseMotionListener(this);
					}
				}
				{
					tab2 = new JPanel();
					infobar.addTab("verwandte Termine", null, tab2, null);
					tab2.setLayout(null);
					tab2.setBackground(new java.awt.Color(240,240,240));
					{
						jLabel7 = new JLabel();
						tab2.add(jLabel7);
						jLabel7.setText("Termine dieses Termincontainers");
						jLabel7.setBounds(7, 7, 182, 14);
					}
					{
						TCterminScroller = new JScrollPane();
						tab2.add(TCterminScroller);
						TCterminScroller.setBounds(7, 28, 231, 196);
						TCterminScroller.setBackground(new java.awt.Color(
							255,
							255,
							255));
						TCterminScroller.setBorder(new LineBorder(
							new java.awt.Color(0, 0, 0),
							1,
							false));
						{
							listTC2Model = new DefaultComboBoxModel();
							listTC2 = new JList();
							TCterminScroller.setViewportView(listTC2);
							listTC2.setModel(listTC2Model);
							listTC2.setBounds(0, 0, 217, 119);
							listTC2.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
							listTC2.addMouseListener(this);
							listTC2.addMouseMotionListener(this);
						}
					}
				}
			}
			{
				TableModel timetableModel = new DefaultTableModel(
					new String[][] {{ "Zeit"},{"7.00-"},{"8.00-"},{"9.00-"},{"10.00-"},{"11.00-"},{"12.00-"},{"13.00-"}
					,{"14.00-"},{"15.00-"},{"16.00-"},{"17.00-"},{"18.00-"},{"19.00-"},{"20.00-"},{"21.00-"}},
					new String[] { "Column 1"});
				timetable = new JTable();
				getContentPane().add(timetable);
				timetable.setModel(timetableModel);
				timetable.setBounds(255, 53, 35, 465);
				timetable.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				timetable.setVisible(false);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setIcon(new ImageIcon("src/images/infoBar.png"));
				jLabel3.setBounds(2, 19, 252, 56);
				{
					lblDatum = new JLabel();
					jLabel3.add(lblDatum);
					lblDatum.setText("");
					lblDatum.setBounds(7, 8, 210, 28);
					lblDatum.setFont(new java.awt.Font("Tahoma",1,12));
					lblDatum.setForeground(new java.awt.Color(0,0,0));
				}
				{
					lblDatumShadow = new JLabel();
					jLabel3.add(lblDatumShadow);
					lblDatumShadow.setText("");
					lblDatumShadow.setBounds(8, 9, 210, 28);
					lblDatumShadow.setFont(new java.awt.Font("Tahoma",1,12));
					lblDatumShadow.setForeground(new java.awt.Color(170,170,170));
				}
			}
			{
				button_newTermin = new JLabel();
				getContentPane().add(button_newTermin);
				button_newTermin.setIcon(new ImageIcon("src/images/icons/neuerTermin.png"));
				button_newTermin.setBounds(0, -7, 31, 35);
				button_newTermin.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_newTermin.addMouseListener(this);
			}
			{
				button_editTermin = new JLabel();
				getContentPane().add(button_editTermin);
				button_editTermin.setIcon(new ImageIcon("src/images/icons/editTermin.PNG"));
				button_editTermin.setBounds(31, -7, 31, 35);
				button_editTermin.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_editTermin.addMouseListener(this);
			}
			{
				button_deleteTermin = new JLabel();
				getContentPane().add(button_deleteTermin);
				button_deleteTermin.setIcon(new ImageIcon("src/images/icons/deleteTermin.png"));
				button_deleteTermin.setBounds(62, -7, 31, 35);
				button_deleteTermin.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_deleteTermin.addMouseListener(this);
			}
			{
				zoomBox = new JCheckBox();
				getContentPane().add(zoomBox);
				zoomBox.setBounds(742, 3, 63, 21);
				zoomBox.setText("Zoom");
				zoomBox.setOpaque(false);
			}
			{
				button_search = new JLabel();
				getContentPane().add(button_search);
				button_search.setIcon(new ImageIcon("src/images/icons/suche.png"));
				button_search.setBounds(248, -7, 31, 35);
				button_search.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_search.addMouseListener(this);
			}
			{
				button_monthBack = new JLabel();
				getContentPane().add(button_monthBack);
				button_monthBack.setIcon(new ImageIcon("src/images/icons/monthBack.png"));
				button_monthBack.setBounds(310, -7, 51, 35);
				button_monthBack.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_monthBack.addMouseListener(this);
			}
			{
				button_weekBack = new JLabel();
				getContentPane().add(button_weekBack);
				button_weekBack.setIcon(new ImageIcon("src/images/icons/weekBack.png"));
				button_weekBack.setBounds(361, -7, 51, 35);
				button_weekBack.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_weekBack.addMouseListener(this);
			}
			{
				button_dayBack = new JLabel();
				getContentPane().add(button_dayBack);
				button_dayBack.setIcon(new ImageIcon("src/images/icons/dayBack.png"));
				button_dayBack.setBounds(412, -7, 51, 35);
				button_dayBack.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_dayBack.addMouseListener(this);
				button_dayBack.setVisible(false);
			}
			{
				button_jumpToToday = new JLabel();
				getContentPane().add(button_jumpToToday);
				button_jumpToToday.setIcon(new ImageIcon("src/images/icons/today.png"));
				button_jumpToToday.setBounds(463, -7, 31, 35);
				button_jumpToToday.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_jumpToToday.addMouseListener(this);
			}	
			{
				button_dayNext = new JLabel();
				getContentPane().add(button_dayNext);
				button_dayNext.setIcon(new ImageIcon("src/images/icons/dayNext.png"));
				button_dayNext.setBounds(494, -7, 51, 35);
				button_dayNext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_dayNext.addMouseListener(this);
				button_dayNext.setVisible(false);
			}
			{
				button_weekNext = new JLabel();
				getContentPane().add(button_weekNext);
				button_weekNext.setIcon(new ImageIcon("src/images/icons/weekNext.png"));
				button_weekNext.setBounds(545, -7, 51, 35);
				button_weekNext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_weekNext.addMouseListener(this);
			}
			{
				button_monthNext = new JLabel();
				getContentPane().add(button_monthNext);
				button_monthNext.setIcon(new ImageIcon("src/images/icons/monthNext.png"));
				button_monthNext.setBounds(596, -7, 51, 35);
				button_monthNext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_monthNext.addMouseListener(this);
			}
			{
				tableBar = new JLabel();
				getContentPane().add(tableBar);
				tableBar.setIcon(new ImageIcon("src/images/tableBar.png"));
				tableBar.setBounds(289, 30, 651, 35);
				
				tableBar.add(lblMonth);
				tableBar.add(lblMonthShadow);

			}
			{
				button_view1 = new JLabel();
				getContentPane().add(button_view1);
				button_view1.setIcon(new ImageIcon("src/images/icons/monatsansicht.png"));
				button_view1.setBounds(675, -7, 31, 35);
				button_view1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_view1.addMouseListener(this);
			}
			{
				button_view2 = new JLabel();
				getContentPane().add(button_view2);
				button_view2.setIcon(new ImageIcon("src/images/icons/wochenansicht.png"));
				button_view2.setBounds(706, -7, 31, 35);
				button_view2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_view2.addMouseListener(this);
			}
			{
				button_space = new JLabel();
				getContentPane().add(button_space);
				button_space.setIcon(new ImageIcon("src/images/icons/space_offline.png"));
				button_space.setBounds(872, -7, 68, 35);
				button_space.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				button_space.addMouseListener(this);
			}
			{
				button_newTC = new JLabel();
				getContentPane().add(button_newTC);
				button_newTC.setIcon(new ImageIcon("src/images/icons/neuerTC.png"));
				button_newTC.setBounds(124, -7, 31, 35);
				button_newTC.addMouseListener(this);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setIcon(new ImageIcon("src/images/infoBar2.png"));
				jLabel4.setBounds(2, 205, 252, 56);
			}
			{
				button_editTC = new JLabel();
				getContentPane().add(button_editTC);
				button_editTC.setIcon(new ImageIcon("src/images/icons/editTC.png"));
				button_editTC.setBounds(155, -7, 31, 35);
				button_editTC.addMouseListener(this);
			}
			{
				button_deleteTC = new JLabel();
				getContentPane().add(button_deleteTC);
				button_deleteTC.setIcon(new ImageIcon("src/images/icons/deleteTC.png"));
				button_deleteTC.setBounds(186, -7, 31, 35);
				button_deleteTC.addMouseListener(this);
			}
			{
				toolBar = new JLabel();
				toolBar.setIcon(new ImageIcon("src/images/toolBar.png"));
				getContentPane().add(toolBar);
				toolBar.setBounds(0, -5, 950, 35);
			}
			{
				mainMenu = new JMenuBar();
				setJMenuBar(mainMenu);
				mainMenu.setPreferredSize(new java.awt.Dimension(892, 24));
				{
					menu_add = new JMenu();
					mainMenu.add(menu_add);
					menu_add.setText("Hinzufügen");
					{
						menu_add_TC = new JMenuItem();
						menu_add.add(menu_add_TC);
						menu_add_TC.setText("Termincontainer ...");
						menu_add_TC.addActionListener(this);
					}
					{
						menu_add_T = new JMenuItem();
						menu_add.add(menu_add_T);
						menu_add_T.setText("Termin ...");
						menu_add_T.addActionListener(this);
					}
					{
						menu_add_Kontakt = new JMenuItem();
						menu_add.add(menu_add_Kontakt);
						menu_add_Kontakt.setText("Kontakt ...");
						menu_add_Kontakt.addActionListener(this);
					}
					{
						menu_add_Pruefung = new JMenuItem();
						menu_add.add(menu_add_Pruefung);
						menu_add_Pruefung.setText("Prüfung ...");
						menu_add_Pruefung.addActionListener(this);
					}
					{
						menu_add_LM = new JMenuItem();
						menu_add.add(menu_add_LM);
						menu_add_LM.setText("Lehrmittel ...");
						menu_add_LM.addActionListener(this);
					}
					{
						menu_add_Lva = new JMenuItem();
						menu_add.add(menu_add_Lva);
						menu_add_Lva.setText("Lva...");
						menu_add_Lva.addActionListener(this);
					}
					{
						menu_add_Notiz = new JMenuItem();
						menu_add.add(menu_add_Notiz);
						menu_add_Notiz.setText("Notiz...");
						menu_add_Notiz.addActionListener(this);
					}
				}
				{
					view = new JMenu();
					mainMenu.add(view);
					view.setText("Ansicht");
					view.addMouseListener(this);
					{
						view_month = new JMenuItem();
						view.add(view_month);
						view_month.setText("Monatsansicht");
						view_month.addMouseListener(this);
					}
					{
						view_week = new JMenuItem();
						view.add(view_week);
						view_week.setText("Wochenansicht");
						view_week.addMouseListener(this);
					}
					{
						jSeparator2 = new JSeparator();
						view.add(jSeparator2);
					}
					{
						view_monthBack = new JMenuItem();
						view.add(view_monthBack);
						view_monthBack.setText("4 Wochen zurück");
						view_monthBack.addMouseListener(this);
					}
					{
						view_weekBack = new JMenuItem();
						view.add(view_weekBack);
						view_weekBack.setText("1 Woche zurück");
						view_weekBack.addMouseListener(this);
					}
					{
						view_dayBack = new JMenuItem();
						view.add(view_dayBack);
						view_dayBack.setText("1 Tag zurück");
						view_dayBack.addMouseListener(this);
						view_dayBack.setVisible(false);
					}
					
					{
						view_dayNext = new JMenuItem();
						view.add(view_dayNext);
						view_dayNext.setText("1 Tag weiter");
						view_dayNext.addMouseListener(this);
						view_dayNext.setVisible(false);
					}
					{
						view_weekNext = new JMenuItem();
						view.add(view_weekNext);
						view_weekNext.setText("1 Woche weiter");
						view_weekNext.addMouseListener(this);
					}
					{
						view_monthNext = new JMenuItem();
						view.add(view_monthNext);
						view_monthNext.setText("4 Wochen weiter");
						view_monthNext.addMouseListener(this);
					}
					
					lblMonth.setBounds(8, -6, 399, 35);
					lblMonthShadow.setBounds(9, -5, 399, 35);
					lblMonthShadow.setForeground(new java.awt.Color(170,170,170));
					lblMonth.setFont(new java.awt.Font("Tahoma",1,13));
					lblMonthShadow.setFont(new java.awt.Font("Tahoma",1,13));
					/*todayList.addFocusListener(new FocusListener(){

						public void focusGained(FocusEvent arg0) {
							
						}

						public void focusLost(FocusEvent arg0) {
							if(arg0.getOppositeComponent().equals(editTermin) || arg0.getOppositeComponent().equals(deleteTermin)){
								
							}else{
								terminId = -1;
							}
	
						}
						
					});*/
				}

			}
			pack();
			this.setSize(950, 604);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource().equals(button_view1))
		{
			setModel("month");
			layoutTable();
			timetable.setVisible(false);
		}
		if(arg0.getSource().equals(button_view2) )
		{
			zoomBox.setSelected(false);
			setModel("week");
			layoutTable();
			timetable.setVisible(true);
		}
		if(arg0.getSource().equals(table))
		{ //zeigt den angeklickten Tag in der Infobar an
			
			if(zoomBox.getSelectedObjects()!=null) tableZoom();
			
			selection = -1; 
			currentCell.x = table.rowAtPoint(table.getMousePosition());
			currentCell.y = table.columnAtPoint(table.getMousePosition());
			terminId=-1;
			try
			{
				currentValue = (Termin[])table.getValueAt(table.rowAtPoint(table.getMousePosition()),table.columnAtPoint(table.getMousePosition()));
				
				c_marker.set(Integer.parseInt(currentValue[0].getDate().toString().substring(0,4)),Integer.parseInt(currentValue[0].getDate().toString().substring(5,7))-1,Integer.parseInt(currentValue[0].getDate().toString().substring(8,10)));
			}
			catch(Exception ex) {System.out.println(" table click  "+ ex.toString());}; 
			
			try
			{		
				Calendar cal2 = new GregorianCalendar();
	    		cal2.set(Integer.parseInt(currentValue[0].getDate().toString().substring(0,4)),Integer.parseInt(currentValue[0].getDate().toString().substring(5,7))-1,Integer.parseInt(currentValue[0].getDate().toString().substring(8,10)));
	    		Date dat = new Date(cal2.getTimeInMillis());
	    		String title = converter.toLong(dat.toString());
	    		lblDatum.setText(title);
	    		lblDatumShadow.setText(title);
	    		loadList();

	    		if(viewMonth) setModel("month");
				else setModel("week");
				
			}
			catch(Exception ex){System.out.println(" table click 2 " + ex.toString());};
					
		}
		if(arg0.getSource().equals(button_dayNext))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+1);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(button_dayBack))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)-1);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(button_weekNext))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+7);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(button_weekBack))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)-7);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(button_monthNext))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+28);		
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(button_monthBack))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)-28);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(button_jumpToToday)) {
			setTimeSpace(new GregorianCalendar());
		}
		if(arg0.getSource().equals(button_space)){
			space();
		}
		if(arg0.getSource().equals(todayList)) {
			selection = todayList.getSelectedIndex()+1;
			loadTerminData();
		}
		if(arg0.getSource().equals(button_newTermin)) {
			EditTerminFrame newTermin = new EditTerminFrame(this,c_marker,-1);
			newTermin.setTitle("neuen Termin hinzufügen");
			newTermin.setLocation(this.getLocation().x+30,this.getLocation().y+30);
			newTermin.setVisible(true);
		}
		if(arg0.getSource().equals(button_newTC)) {
			EditTerminContainerFrame frame = new EditTerminContainerFrame(this,-1);
			frame.setLocation(this.getLocation().x+20,this.getLocation().y+20);
			frame.setTitle("neuen Termincontainer hinzufügen");
;			frame.setVisible(true);
		}
		if(arg0.getSource().equals(button_editTermin)) {
			
			if(terminId != -1 && selection>=0){
			EditTerminFrame newTermin = new EditTerminFrame(this,null, terminId);
			newTermin.setTitle("Termin bearbeiten");
			newTermin.setLocation(this.getLocation().x+30,this.getLocation().y+30);
			newTermin.setVisible(true);
			}
			else showErrorDialog("Fehler!", "Kein Termin ausgewählt!");
			
		}
		if(arg0.getSource().equals(button_search)) {
			
			SearchFrame search = new SearchFrame();
			
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gd = env.getDefaultScreenDevice();
			DisplayMode dm = gd.getDisplayMode();
			search.setLocation((dm.getWidth()-search.getWidth())/2,(dm.getHeight()-search.getHeight())/2);
			search.setVisible(true);
		}
		
		if(arg0.getSource().equals(button_deleteTermin)) {
			if(terminId!=-1)
			{
				deletedObject = "termin";
				SecurityDialog diag = new SecurityDialog(this,"Termin löschen","Wollen Sie den gewählten Termin wirklich löschen?");
				diag.setLocation(this.getLocation().x+40,this.getLocation().y+40);
				diag.setVisible(true);
				
			}
			else showErrorDialog("Fehler!", "Kein Termin ausgewählt!");
			
			
		}
		if(arg0.getSource().equals(button_deleteTC)) {
			if(terminId!=-1)
			{
				deletedObject = "termincontainer";
				SecurityDialog diag = new SecurityDialog(this,"Termin löschen","Wollen Sie den gewählten Termincontainer wirklich löschen? Achtung: Dies löscht auch alle Termine in diesem Termincontainer!");
				diag.setLocation(this.getLocation().x+40,this.getLocation().y+40);
				diag.setVisible(true);
				
			}
			else showErrorDialog("Fehler!", "Kein Termin ausgewählt!");
		}
		if(arg0.getSource().equals(listTC2)) {
			doubleClickCounter++;
			if(doubleClickCounter==2)
			{
				GregorianCalendar goToTermin = new GregorianCalendar();
				goToTermin.set(Calendar.YEAR,Integer.parseInt(relatedTermine[listTC2.getSelectedIndex()].getDate().toString().substring(0,4)));
				goToTermin.set(Calendar.MONTH,Integer.parseInt(relatedTermine[listTC2.getSelectedIndex()].getDate().toString().substring(5,7))-1);
				goToTermin.set(Calendar.DAY_OF_MONTH,Integer.parseInt(relatedTermine[listTC2.getSelectedIndex()].getDate().toString().substring(8,10)));
				goToTermin.set(Calendar.HOUR_OF_DAY,0);
				goToTermin.set(Calendar.MINUTE,0);
				goToTermin.set(Calendar.SECOND,0);
				c_marker.setTimeInMillis(goToTermin.getTimeInMillis());
				setTimeSpace(goToTermin);
				layoutTable();	
			}	
		}
		if(arg0.getSource().equals(showTObjects))
		{	
				loadTerminData();
		}	
		if(arg0.getSource().equals(showTCObjects))
		{	
				loadTerminData();
		}
	}

	public void mousePressed(MouseEvent arg0)
	{	
		if(arg0.getSource().equals(table)){
			mouseDown = true;
		}	
		
	}

	public void mouseReleased(MouseEvent arg0)
	{	
		if(arg0.getSource().equals(table)){
			mouseDown = false;
		}
		if(arg0.getSource().equals(view_week))
		{
			setModel("week");
			layoutTable();
			timetable.setVisible(true);
		}
		if(arg0.getSource().equals(view_month))
		{
			setModel("month");
			layoutTable();
			timetable.setVisible(false);
		}
		if(arg0.getSource().equals(button_dayNext))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+1);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(button_dayBack))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)-1);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(button_weekNext))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+7);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(button_weekBack))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)-7);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(button_monthNext))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+28);		
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(button_monthBack))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)-28);
			setTimeSpace(calStart);
		}
		
	}

	public void mouseEntered(MouseEvent arg0) 
	{	
		if(arg0.getSource().equals(button_view1)){
			statusBar.setText("Wechselt das Kalenderlayout auf die Monatsansicht");
		}
		if(arg0.getSource().equals(button_view2)){
			statusBar.setText("Wechselt das Kalenderlayout auf die Wochenansicht");
		}
		if(arg0.getSource().equals(table)){		
			statusBar.setText("Klicken Sie auf einen Tag um ihn und dessen Termine in der Infobar anzuzeigen");
			mouseEntered = true;
		}
		if(arg0.getSource().equals(button_newTermin)){
			button_newTermin.setIcon(new ImageIcon("src/images/icons/neuerTermin_hover.png"));
			statusBar.setText("Fügt einen neuen Termin hinzu. Wenn Sie vorher auf einen Tag klicken wird dieser automatisch in das Termindatum übernommen.");	
		}
		if(arg0.getSource().equals(button_editTermin)){
			button_editTermin.setIcon(new ImageIcon("src/images/icons/editTermin_hover.png"));
			statusBar.setText("Bearbeiten des gerade gewählten Termins.");	
		}
		if(arg0.getSource().equals(button_deleteTermin)){
			button_deleteTermin.setIcon(new ImageIcon("src/images/icons/deleteTermin_hover.png"));
			statusBar.setText("Löscht den gerade gewählten Termin.");	
		}
		if(arg0.getSource().equals(button_newTC)){
			button_newTC.setIcon(new ImageIcon("src/images/icons/neuerTC_hover.png"));
			statusBar.setText("Fügt einen neuen Termincontainer hinzu.");	
		}
		if(arg0.getSource().equals(button_editTC)){
			button_editTC.setIcon(new ImageIcon("src/images/icons/editTC_hover.png"));
			statusBar.setText("Bearbeitet einen Termincontainer.");	
		}
		if(arg0.getSource().equals(button_deleteTC)){
			button_deleteTC.setIcon(new ImageIcon("src/images/icons/deleteTC_hover.png"));
			statusBar.setText("Löscht einen Termincontainer.");	
		}
		if(arg0.getSource().equals(button_search)){
			button_search.setIcon(new ImageIcon("src/images/icons/suche_hover.png"));
			statusBar.setText("Sucht nach Terminen, Termincontainern und anderen Daten.");	
		}
		if(arg0.getSource().equals(button_monthBack)){
			button_monthBack.setIcon(new ImageIcon("src/images/icons/monthBack_hover.png"));
			statusBar.setText("Verschiebt die Ansicht um 4 Wochen zurück.");	
		}
		if(arg0.getSource().equals(button_weekBack)){
			button_weekBack.setIcon(new ImageIcon("src/images/icons/weekBack_hover.png"));
			statusBar.setText("Verschiebt die Ansicht um 1 Woche zurück.");	
		}
		if(arg0.getSource().equals(button_dayBack)){
			button_dayBack.setIcon(new ImageIcon("src/images/icons/dayBack_hover.png"));
			statusBar.setText("Verschiebt die Ansicht um 1 Tag zurück.");	
		}
		if(arg0.getSource().equals(button_monthNext)){
			button_monthNext.setIcon(new ImageIcon("src/images/icons/monthNext_hover.png"));
			statusBar.setText("Verschiebt die Ansicht um 4 Wochen weiter.");	
		}
		if(arg0.getSource().equals(button_weekNext)){
			button_weekNext.setIcon(new ImageIcon("src/images/icons/weekNext_hover.png"));
			statusBar.setText("Verschiebt die Ansicht um 1 Woche weiter.");	
		}
		if(arg0.getSource().equals(button_dayNext)){
			button_dayNext.setIcon(new ImageIcon("src/images/icons/dayNext_hover.png"));
			statusBar.setText("Verschiebt die Ansicht um 1 Tag weiter.");	
		}
		if(arg0.getSource().equals(button_jumpToToday)){
			button_jumpToToday.setIcon(new ImageIcon("src/images/icons/today_hover.png"));
			statusBar.setText("Setzt die Ansicht auf das heutige Datum.");	
		}
		if(arg0.getSource().equals(button_view1)){
			button_view1.setIcon(new ImageIcon("src/images/icons/monatsansicht_hover.png"));
			statusBar.setText("Wechselt auf die Monatsansicht.");	
		}
		if(arg0.getSource().equals(button_view2)){
			button_view2.setIcon(new ImageIcon("src/images/icons/wochenansicht_hover.png"));
			statusBar.setText("Wechselt auf die Wochenansicht.");	
		}
		if(arg0.getSource().equals(button_space)){
			if(!online) button_space.setIcon(new ImageIcon("src/images/icons/space_offline_hover.png"));
			else button_space.setIcon(new ImageIcon("src/images/icons/space_online_hover.png"));
			statusBar.setText("Verbindet oder trennt die Verbindung zum autoSpace Service.");	
		}
		if(arg0.getSource().equals(zoomBox)){
			statusBar.setText("Aktiviert/Deaktiviert die automatische Vergrößerung eines Tages in der Monatsansicht wenn der Mauszeiger darübergeführt wird.");	
		}
		
		if(arg0.getSource().equals(listTC2)) {
			statusBar.setText("Doppelklick auf einen Eintrag zeigt den zugehörigen Tag im Kalender an.");
		}
		if(arg0.getSource().equals(dateJumper)) {
			statusBar.setText("Geben Sie ein Datum im Format TT.MM.JJJJ ein und bestätigen sie mit 'ENTER'");
		}
		if(arg0.getSource().equals(button_jumpToToday)) {
			statusBar.setText("Setzt den Kalender auf das heutige Datum.");
		}
		if(arg0.getSource().equals(todayList)) {
			statusBar.setText("Klick auf einen Termin öffnet dessen Details");
		}
	}

	public void mouseExited(MouseEvent arg0) {
		
		if(arg0.getSource().equals(button_view1)){
			statusBar.setText("");
		}
		if(arg0.getSource().equals(button_view2)){
			statusBar.setText("");
		}
		if(arg0.getSource().equals(table)){
			statusBar.setText("");
			mouseEntered = false;
			layoutTable();
			
		}
		if(arg0.getSource().equals(button_newTermin)){
			button_newTermin.setIcon(new ImageIcon("src/images/icons/neuerTermin.png"));
			statusBar.setText("");
		}
		if(arg0.getSource().equals(button_editTermin)){
			button_editTermin.setIcon(new ImageIcon("src/images/icons/editTermin.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_deleteTermin)){
			button_deleteTermin.setIcon(new ImageIcon("src/images/icons/deleteTermin.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_newTC)){
			button_newTC.setIcon(new ImageIcon("src/images/icons/neuerTC.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_editTC)){
			button_editTC.setIcon(new ImageIcon("src/images/icons/editTC.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_deleteTC)){
			button_deleteTC.setIcon(new ImageIcon("src/images/icons/deleteTC.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_search)){
			button_search.setIcon(new ImageIcon("src/images/icons/suche.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_monthBack)){
			button_monthBack.setIcon(new ImageIcon("src/images/icons/monthBack.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_weekBack)){
			button_weekBack.setIcon(new ImageIcon("src/images/icons/weekBack.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_dayBack)){
			button_dayBack.setIcon(new ImageIcon("src/images/icons/dayBack.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_monthNext)){
			button_monthNext.setIcon(new ImageIcon("src/images/icons/monthNext.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_weekNext)){
			button_weekNext.setIcon(new ImageIcon("src/images/icons/weekNext.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_dayNext)){
			button_dayNext.setIcon(new ImageIcon("src/images/icons/dayNext.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_jumpToToday)){
			button_jumpToToday.setIcon(new ImageIcon("src/images/icons/today.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_view1)){
			button_view1.setIcon(new ImageIcon("src/images/icons/monatsansicht.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_view2)){
			button_view2.setIcon(new ImageIcon("src/images/icons/wochenansicht.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(button_space)){
			if(!online) button_space.setIcon(new ImageIcon("src/images/icons/space_offline.png"));
			else button_space.setIcon(new ImageIcon("src/images/icons/space_online.png"));
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(zoomBox)){
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(listTC2)) {
			statusBar.setText("");
		}
		if(arg0.getSource().equals(button_jumpToToday)) {
			statusBar.setText("");
		}
		if(arg0.getSource().equals(todayList)) {
			statusBar.setText("");
		}
	}

	public void mouseDragged(MouseEvent arg0)
	{	
	}
	
	public void mouseMoved(MouseEvent arg0)
	{
		if(arg0.getSource().equals(table))
		{	
			if(zoomBox.getSelectedObjects()!=null) tableZoom();		
		}
			
		if(arg0.getSource().equals(listTC2))
		{	
				this.doubleClickCounter = 0;
		}
	}
	/* Zoom-Funktion für das Hovern über die Tabelle
	 * 
	 */
	private void tableZoom()
	/* Diese Methode vergrößert die Zelle, über der sich der Cursor befindet,
	 * indem die jeweilige Reihenhöhe und Spaltenbreite erhöht werden.
	 */
	{
//		 TableZoom Engine (c) 2006 by Stephe ;-) ---------------------------
		
		if(mouseEntered && zoomBox.getSelectedObjects()!= null)
		{
			try{
				int i,j,k = 0;
				j=0;
				if(viewMonth)
				{
					try
					{
						j=table.rowAtPoint(table.getMousePosition()); 
					}
					catch(Exception ex)
					{
						
					}
					
					for (i=0;i<5;i++)
					{ 
						if(i==j) { k=186; } //Reihe j ist die Reihe, über die gerade "gehovert" wird
						else { k = 76; } //Die anderen Reihen werden verkleinert
						table.setRowHeight(i,k); //Reihe i wird mit Höhe k versehen
						
					}
				}
				else
				{
					try
					{
						j=table.rowAtPoint(table.getMousePosition()); 
					}
					catch(Exception ex)
					{
						
					}
					
						for (i=1;i<15;i++)
						{ 
							if(i==j) { k=150; } //Reihe j ist die Reihe, über die gerade "gehovert" wird
							else { k = 29; } //Die anderen Reihen werden verkleinert
							table.setRowHeight(i,k); //Reihe i wird mit Höhe k versehen
							timetable.setRowHeight(i,k);
						}
				}
				
				DefaultTableColumnModel cm = new DefaultTableColumnModel();
				
				j = table.columnAtPoint(table.getMousePosition());
				for (i=0;i<7;i++)
				{
					if (i==j){ k = 200; } //Spalte j ist die Spalte, über die gerade "gehovert" wird
					else {k = 97; } //Die anderen Spalten werden verkleinert
					
					TableColumn col = new TableColumn(i, k);
					cm.addColumn(col);
				}
				table.setColumnModel(cm); //Spaltenlayout auf die Tabelle bringen
			}
		
			catch(Exception ex)
			{	
			}
		}	
	}
	
	/* Setzt das Layout für die Tabelle
	 * 
	 */
	private void layoutTable()
	{
		DefaultTableColumnModel cm = new DefaultTableColumnModel();
		for (int i=0;i<7;i++)
		{
			TableColumn col = new TableColumn(i, 97);
			cm.addColumn(col);
		}
		table.setColumnModel(cm);
		
		if(viewMonth)
		{
			table.setRowHeight(93);	
		}
		else table.setRowHeight(0,22);
		if(viewMonth) setModel("month");
		else setModel("week");
		updateTable();
	}
	
	/* Liest die Daten für die Tabelle ein
	 * 
	 */
	public void updateTable()
	{
		updateDateIndicator();
			
		if(viewMonth) //Monatsansicht
		{			
			MonatTM tableModel = new MonatTM(tsBegin, tsEnd);
			table.setModel(tableModel);			
		}
		else  //Wochenansicht
		{
			WocheTM tableModel = new WocheTM(tsBegin, tsEnd);
			table.setModel(tableModel);		
			table.setRowHeight(0,22);
		}	
	}

	/* Erneuert die Anzeige für die angezeigte Zeitspanne
	 * 
	 */
	private void updateDateIndicator()
	{
		Date dat = new Date(tsBegin.getTime());
		Date dat2 = new Date(tsEnd.getTime());
		DateConverter converter = new DateConverter();
		lblMonth.setText(converter.toShortYear(dat.toString())+ " - " + converter.toShortYear(dat2.toString()));
		lblMonthShadow.setText(converter.toShortYear(dat.toString())+ " - " + converter.toShortYear(dat2.toString()));
		this.setTitle("autoPSI | " + lblMonth.getText());
	}
	
	/* Diese Methode berechnet das Startdatum für die Monatsansicht.
	 * Je nach übergebenem Datum wird das Startdatum so berechnet, dass es immer 
	 * mit einem Montag beginnt.
	 */
	private void setTimeSpace(GregorianCalendar cal)
	 
	{
		Date dat = new Date(cal.getTimeInMillis());
		if(viewMonth)
		{
			if(dat.toString().substring(0,3).equals("Tue"))
			{
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-1);
			}
			else if(dat.toString().substring(0,3).equals("Wed"))
			{
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-2);
			}
			else if(dat.toString().substring(0,3).equals("Thu"))
			{
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-3);
			}
			else if(dat.toString().substring(0,3).equals("Fri"))
			{
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-4);
			}
			else if(dat.toString().substring(0,3).equals("Sat"))
			{
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-5);
			}
			else if(dat.toString().substring(0,3).equals("Sun"))
			{
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-6);
			}
		}
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		tsBegin = new Timestamp(cal.getTimeInMillis());
		calStart.setTime(cal.getTime());
		calEnd.setTime(calStart.getTime()); //Endzeit auf Startzeit setzen
		if(viewMonth) calEnd.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+34); //Monatsansicht (35 Tage)
		else calEnd.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+6); //Wochenansicht (7 Tage)
		calEnd.set(Calendar.HOUR_OF_DAY,23);
		calEnd.set(Calendar.MINUTE,59);
		tsEnd = new Timestamp(calEnd.getTimeInMillis());
		updateTable();
	}
	
	private void showErrorDialog(String title, String text)
	{
		InfoDialog info = new InfoDialog(this, title,text);
		info.setLocation(this.getLocation().x+200,this.getLocation().y+200);
		info.setVisible(true);
		
	}
	
	private void setModel(String view)
	{
		if (view.equals("week"))
		{
			//lblToday.setText("Keine Termine ausgewählt.");
			table.setDefaultRenderer(Termin[].class, new WeekRenderer(c_marker));
			view_dayBack.setVisible(true);
			view_dayNext.setVisible(true);
			button_dayBack.setVisible(true);
			button_dayNext.setVisible(true);
			viewMonth = false;
			table.setRowHeight(29);
			setTimeSpace(calStart);
			timetable.setRowHeight(29);
			timetable.setRowHeight(0,22);
			table.setShowVerticalLines(true);
		}
		else if (view.equals("month"))
		{
			lblToday.setText("heutige Termine:");
			table.setDefaultRenderer(Termin[].class, new MonthRenderer(c_marker));
			view_dayBack.setVisible(false);
			view_dayNext.setVisible(false);
			button_dayBack.setVisible(false);
			button_dayNext.setVisible(false);
			viewMonth = true;
			table.setRowHeight(93);
			setTimeSpace(calStart);
			table.setShowVerticalLines(false);
		}
	}
	
	/*
	 * Lädt Termindetails in den unteren Teil der InfoBar
	 */
	
	protected MaskFormatter createFormatter(String s) {
		 MaskFormatter formatter = null;
		 try {
			 formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
		}
		return formatter;
	}
	
	/*Liest Termine aus einer Tag-Zelle aus und füllt sie in die Today-Liste
	 * 
	 */
	private void loadList()
	{
		todayListModel.removeAllElements();
		currentValue = (Termin[])table.getValueAt(currentCell.x,currentCell.y);

		for(int i = 1;i<currentValue.length;i++)
		{
			
			String terminItem = currentValue[i].getDate().toString().substring(11,16) + 
								" " + currentValue[i].getSecondaryTitle();
			todayListModel.addElement(terminItem);
		}	
		if(viewMonth) lblToday.setText("heutige Termine:");
		else
			{
			int sel = table.getSelectedRow();
			System.out.println(sel);
			if(sel==1)lblToday.setText("Termine zwischen 00:00 und 8:00");
			else if(sel==15) lblToday.setText("Termine ab 21:00");
			else lblToday.setText("Termine zwischen "+ table.getSelectedRow()+9+";00 und "+table.getSelectedRow()+10+":00");
			
			}
	}
	
	public void updateInfoBar(boolean delete)
	{
		GenericDAO gdo = new GenericDAO();
		List<GenericDataObject> delTC;
		try
		{
			delTC = gdo.unsafeQuery("select * from termin where id = " + terminId,new Termin());
			tcID = ((Termin)delTC.get(0)).getTerminContainerID();
		}
		catch(Exception e){}
		
		
		
	
		if(delete)
			{
			todayListModel.removeAllElements();
			
			
			if(deletedObject.equals("termincontainer"))
			{
				
				try
				{
					
				
					String query = "delete from " + deletedObject + " where id =" +tcID;
				
					try {				
						if(delete_ok)					
						gdo.unsafeQuery(query,null);
						updateTable();
						delete_ok = false;
						//Liste muss noch geupdatet werden
					} catch (EDatabaseConnection e) {
						e.printStackTrace();
					} catch (EAttributeNotFound e) {
						e.printStackTrace();
					} catch (EDatabase e) {
						showErrorDialog("Fehler","Der Termincontainer konnte nicht gelöscht werden");
					}
				
					
				query = "delete from termin where termincontainer_id = "+tcID;
				gdo.unsafeQuery(query,null);
				}
				catch(Exception e) {System.out.println("get TC ID::: "+e.toString());}
			}
			
			
			
			if(terminId != -1){
				String query = "delete from " + deletedObject + " where id =" +terminId;
				try {				
					if(delete_ok)					
					gdo.unsafeQuery(query,null);
					updateTable();
					delete_ok = false;
					//Liste muss noch geupdatet werden
				} catch (EDatabaseConnection e) {
					e.printStackTrace();
				} catch (EAttributeNotFound e) {
					e.printStackTrace();
				} catch (EDatabase e) {
					showErrorDialog("Fehler","Der Termin konnte nicht gelöscht werden");
				}
			}	
		}
		
		updateTable();
		loadTerminList(false);
		loadTerminData();
		selection = -1;
		terminId=-1;
	}

	public void windowOpened(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
		//closing all database connections
		GenericDAO gdao = new GenericDAO();
		try {
			gdao.unsafeQuery("Shutdown compact", new Notiz());
		} catch (Exception e){
			System.out.println("mainFrame.windowClosed(..)::Konnte Datenbankverbindungen nicht schließen::"+e.toString());
		}
		//deleting shared objects from JavaSpace
		if (this.oss != null)
			this.oss.unshareObjects();
	}

	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//Setzt die Space-Einstellungen
	private void space()
	{
		if(!online)
		{
			
			//loading all shared objects into JavaSpace
			if (this.oss == null)
				this.oss = new ObjectSpaceSharer();
			JSAdressDialog jsad = new JSAdressDialog(this);
			this.oss.setAdress(jsad.getAdress());
				SpaceThread thr = new SpaceThread(this.oss);


			online=true;
			button_space.setIcon(new ImageIcon("src/images/icons/space_online_hover.png"));
		}
		else 
		{
			
			//stop lease renewal (means objects will be removed from JavaSpace)
			if (this.oss != null){
				System.out.println("mainFrame.space()::going offline");
				this.oss.unshareObjects();
			}
			
			
			online = false;
			button_space.setIcon(new ImageIcon("src/images/icons/space_offline.png"));
		}
	}

	private void loadTerminList(boolean first)
	{
			GregorianCalendar c1 = new GregorianCalendar();
			if(!first)
				{
				c1.set(Calendar.YEAR,Integer.parseInt(currentValue[0].getDate().toString().substring(0,4)));
				c1.set(Calendar.MONTH,Integer.parseInt(currentValue[0].getDate().toString().substring(5,7))-1);
				c1.set(Calendar.DAY_OF_MONTH,Integer.parseInt(currentValue[0].getDate().toString().substring(8,10)));
				}
			c1.set(Calendar.HOUR_OF_DAY,0);
			c1.set(Calendar.MINUTE,0);
			c1.set(Calendar.SECOND,1);
			Timestamp t1 = new Timestamp(c1.getTimeInMillis());
			c1.set(Calendar.HOUR_OF_DAY,23);
			c1.set(Calendar.MINUTE,59);
			c1.set(Calendar.SECOND,59);
			Timestamp t2 = new Timestamp(c1.getTimeInMillis());
			
			List<GenericDataObject> termine;
			GenericDAO gdo = new GenericDAO();
			try
			{
				termine = gdo.unsafeQuery("select * from termin where date>='"+t1.toString()+"' and date<='"+t2.toString()+"'",new Termin());
				currentValue = new Termin[termine.size()+1];
				Termin dayTermin = new Termin();
				dayTermin.setDate(t1);
				currentValue[0]=dayTermin;
				todayListModel.removeAllElements();
				for(int i = 0;i<termine.size();i++)
				{
					Termin countTermin = (Termin)termine.get(i);
					currentValue[i+1] = countTermin;
					todayListModel.addElement(countTermin.getDate().toString().substring(11,16)+"  "+countTermin.getSecondaryTitle());
				}
				if(first)
				{
					Calendar cal2 = new GregorianCalendar();
		    		cal2.set(Integer.parseInt(currentValue[0].getDate().toString().substring(0,4)),Integer.parseInt(currentValue[0].getDate().toString().substring(5,7))-1,Integer.parseInt(currentValue[0].getDate().toString().substring(8,10)));
		    		Date dat = new Date(cal2.getTimeInMillis());
		    		String title = converter.toLong(dat.toString());
		    		lblDatum.setText(title);
		    		lblDatumShadow.setText(title);
				}
				if(first && termine.size()>0)
				{
					selection = 1;
					loadTerminData();
				}
				
			}
			catch(Exception ex) {System.out.println("loadTerminList:: "+ex.toString());}

	}
	
	public void loadTerminData()
	{
		Termin showTermin = new Termin();
		System.out.println(selection);
		if(selection>=0)	
		{
			try
			{	
				showTermin = currentValue[selection];
				IGenericDAO igdao = new GenericDAO();
				String  query = "select * from termin where id="+showTermin.getId();
				List<GenericDataObject> data = igdao.unsafeQuery(query,new Termin());		
				showTermin = (Termin)data.get(0);
				currentValue[selection] = showTermin;
				
				
				if(currentValue.length>0) {
					
					int dauer = showTermin.getDuration();
					terminId = showTermin.getId();
					int stunden = 0;
					int minuten = 0;
					
					while(dauer>59)
					{
					dauer = dauer -60;
					stunden++;
					}
					minuten = dauer;
					
					lblZeit.setText("Zeit: "+showTermin.getDate().toString().substring(12,16)+"           Dauer "+stunden+":"+minuten);

					
					lblTermin.setText(showTermin.getSecondaryTitle());
					
					lblOrt.setText("Ort: "+showTermin.getPlace());
					lblBeschreibung.setText(showTermin.getDescription());
					
					IGenericDAO gdo = new GenericDAO();
					String query2 = "select * from termincontainer where id="+showTermin.getTerminContainerID();
					TerminContainer cont = new TerminContainer();
					List<GenericDataObject> dat = gdo.unsafeQuery(query2,cont);
					
					try
					{
						cont = (TerminContainer)dat.get(0);
						lblTerminContainer.setText(cont.getTitle());
					}
					catch(Exception ex)
					{
						System.out.println("cast error");
					}	
					
					query = "select * from termin where termincontainer_id="+showTermin.getTerminContainerID()+" order by date";
					data = igdao.unsafeQuery(query,new Termin());		
					relatedTermine = new Termin[data.size()];
					Termin rTermin;
					
					listTC2Model.removeAllElements();
					for(int i=0;i<data.size();i++)
					{
						
						rTermin = (Termin)data.get(i);
						relatedTermine[i]=rTermin;
						
						String datum = rTermin.getDate().toString();
						listTC2Model.addElement(datum.substring(8,10)+"-"+datum.substring(5,7)+"-"+datum.substring(0,4)+ ":  " +datum.substring(11,16)+"  "+rTermin.getSecondaryTitle().toString());
					}
					
					objectListModel.removeAllElements();
					if(showTObjects.getSelectedObjects()!=null)
					{
						String tablename="";
						int globalID;
						List<GenericDataObject> list1;
						List<GenericDataObject> list2;
						list1 = gdo.unsafeQuery("select * from anhaengen_termin where termin_id ="+terminId,new Anhaengen_termin());
						for(int i = 0;i<list1.size();i++)
						{
							Anhaengen_termin at = (Anhaengen_termin)list1.get(i);
							tablename = at.getTable_Name().toLowerCase();
							globalID = at.getGlobalId();
							if(tablename.equals("kontakt")){
								list2 = gdo.unsafeQuery("select * from kontakt where global_id = " + globalID,new Kontakt());
								Kontakt k = (Kontakt)list2.get(0);
								objectListModel.addElement(k.getPrename() + " " + k.getSurname());
							}
							if(tablename.equals("notiz")){
								list2 = gdo.unsafeQuery("select * from notiz where global_id = " + globalID,new Notiz());
								Notiz n = (Notiz)list2.get(0);
								objectListModel.addElement(n.getTitle() + ":   " + n.getNote());
							}
							if(tablename.equals("pruefung")){
								list2 = gdo.unsafeQuery("select * from " + table + " where global_id = "+ globalID,new Pruefung());
								Lva l = new Lva();
								Pruefung p = ((Pruefung)list2.get(0));
								list2 = gdo.unsafeQuery("select * from lva where global_id = " + p.getLvaId(),new Lva());
								l= (Lva)list2.get(0);
								String pr = l.getLvaNr() + " - "  + l.getTitle() + " - "+ p.getExaminer()+ " : " + p.getGrade();
								
								objectListModel.addElement(pr);
							}
							if(tablename.equals("lehrmittel")){
								list2 = gdo.unsafeQuery("select * from lehrmittel where global_id = " + globalID,new Lehrmittel());
								String leh = ((Lehrmittel)list2.get(0)).getName();
								objectListModel.addElement(leh);
							}
							if(tablename.equals("lva")){
								list2 = gdo.unsafeQuery("select * from lva where global_id = " + globalID,new Lva());
								String lva = "LVA-Nr.: " + ((Lva)list2.get(0)).getLvaNr()+",         Titel: " + ((Lva)list2.get(0)).getTitle(); 
								objectListModel.addElement(lva);	
							}
						}	
				    }
					if(showTCObjects.getSelectedObjects()!=null)
					{
						String tablename="";
						int globalID;
						List<GenericDataObject> list1;
						List<GenericDataObject> list2;
						list1 = gdo.unsafeQuery("select * from anhaengen_termincontainer where termincontainer_id ="+showTermin.getTerminContainerID(),new Anhaengen_termincontainer());
						for(int i = 0;i<list1.size();i++)
						{
							Anhaengen_termincontainer at = (Anhaengen_termincontainer)list1.get(i);
							tablename = at.getTable_Name().toLowerCase();
							globalID = at.getGlobalId();
							if(tablename.equals("kontakt")){
								list2 = gdo.unsafeQuery("select * from kontakt where global_id = " + globalID,new Kontakt());
								Kontakt k = (Kontakt)list2.get(0);
								objectListModel.addElement(k.getPrename() + " " + k.getSurname());
							}
							if(tablename.equals("notiz")){
								list2 = gdo.unsafeQuery("select * from notiz where global_id = " + globalID,new Notiz());
								Notiz n = (Notiz)list2.get(0);
								objectListModel.addElement(n.getTitle() + ":   " + n.getNote());
							}
							if(tablename.equals("pruefung")){
								list2 = gdo.unsafeQuery("select * from " + table + " where global_id = "+ globalID,new Pruefung());
								Lva l = new Lva();
								Pruefung p = ((Pruefung)list2.get(0));
								list2 = gdo.unsafeQuery("select * from lva where global_id = " + p.getLvaId(),new Lva());
								l= (Lva)list2.get(0);
								String pr = l.getLvaNr() + " - "  + l.getTitle() + " - "+ p.getExaminer()+ " : " + p.getGrade();
								
								objectListModel.addElement(pr);
							}
							if(tablename.equals("lehrmittel")){
								list2 = gdo.unsafeQuery("select * from lehrmittel where global_id = " + globalID,new Lehrmittel());
								String leh = ((Lehrmittel)list2.get(0)).getName();
								objectListModel.addElement(leh);
							}
							if(tablename.equals("lva")){
								list2 = gdo.unsafeQuery("select * from lva where global_id = " + globalID,new Lva());
								String lva = "LVA-Nr.: " + ((Lva)list2.get(0)).getLvaNr()+",         Titel: " + ((Lva)list2.get(0)).getTitle(); 
								objectListModel.addElement(lva);
								
							}
						}
					}
					
					
			}
			}
			catch(Exception ex) {System.out.println("error: " + ex.toString());}		
		}	
		else
		{
			lblZeit.setText("");
			lblOrt.setText("");
			lblBeschreibung.setText("");
			lblTerminContainer.setText("");
			lblTermin.setText("");
			listTC2.removeAll();
			objectList.removeAll();
		}
	}
	

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(this.menu_add_Kontakt)){
			System.out.println("mainFrame.actionPerformed()::füge Kontakt hinzu");
			new AddAttachableObject(this, new Kontakt());
		}
		if (arg0.getSource().equals(this.menu_add_Notiz)){
			System.out.println("mainFrame.actionPerformed()::füge Notiz hinzu");
			new AddAttachableObject(this, new Notiz());
		}
		if (arg0.getSource().equals(this.menu_add_LM)){
			System.out.println("mainFrame.actionPerformed()::füge Lehrmittel hinzu");
			new AddAttachableObject(this, new Lehrmittel());
		}
		if (arg0.getSource().equals(this.menu_add_Lva)){
			System.out.println("mainFrame.actionPerformed()::füge LVA hinzu");
			new AddAttachableObject(this, new Lva());
		}
		if (arg0.getSource().equals(this.menu_add_Pruefung)){
			System.out.println("mainFrame.actionPerformed()::füge Prüfung hinzu");
			new AddAttachableObject(this, new Pruefung());
		}
	}
}