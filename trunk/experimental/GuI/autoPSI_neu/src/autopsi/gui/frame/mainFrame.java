package autopsi.gui.frame;
import java.awt.Point;
import java.awt.event.*;
import java.util.*;
import javax.swing.BorderFactory;

import javax.swing.*;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import javax.swing.JTable;
import javax.swing.JToolBar;


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
public class mainFrame extends javax.swing.JFrame implements java.awt.event.MouseListener,java.awt.event.MouseMotionListener{

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {

			e.printStackTrace();
		}
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 7899036380605041556L;
	private JButton viewButton2;
	private JButton viewButton1;
	private JTable table;
	private JLabel lblZeit;
	private JButton jJumpToToday;
	private JButton newTerminContainer;
	private JPanel tab0;
	private JLabel jLabel2;
	private JTextField txtSuche;
	private JPanel tab2;
	private JTextPane lblBeschreibung;
	private JTabbedPane infobar;
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
	private JMenu menu_search;
	private JMenuItem menu_add_LM;
	private JMenuItem menu_add_Pruefung;
	private JMenuItem menu_add_Kontakt;
	private JMenuItem menu_add_T;
	private JMenuItem menu_add_TC;
	private JMenu menu_add;
	private JPanel jPanel5;
	private JFormattedTextField dateJumper;
	private JLabel jLabel1;
	private JPanel jPanel4;
	private JToolBar toolbar;
	private JButton dayNext;
	private JButton dayBack;
	private JPanel jPanel3;
	private JButton deleteTC;
	private JButton editTC;
	private JPanel jPanel2;
	private JCheckBox showTCObjects;
	private JCheckBox showTObjects;
	private JList objectList;
	private JScrollPane objectScroller;
	private JScrollPane TCterminScroller;
	private JList todayList;
	private JScrollPane todayScrollPane;
	private JLabel lblToday;
	private JPanel today;
	private JButton monthNext;
	private JButton weekNext;
	private JButton weekBack;
	private JButton monthBack;
	private JLabel lblMonth = new JLabel();
	private JButton deleteTermin;
	private JButton editTermin;
	private JButton neuerTermin;
	private JList listTC2;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JCheckBox zoomBox;
	private JTextField txtSuche2;
	private JPanel terminPanel;
	private JLabel lblTerminContainer;
	private JLabel lblOrt;
	private JLabel lblDatum;
	private JLabel lblTermin;
	private JPanel tab1;
	private JMenuBar mainMenu;
	private JLabel statusBar;
	private int terminId=-1;
	private Termin[] currentValue = null;
	String[] data;
	private Point currentCell = new Point();
	private int selection;
	
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
	
	ListModel listTC2Model = new DefaultComboBoxModel(); //Listmodel für verwandte Termine
	public boolean delete_ok = false;
	
	
	
	public mainFrame() {
		super();
		initGUI();		
	}
	
	private void setTable()
	{
		table = new JTable();	
		setTimeSpace(calStart);
		MonatTM tablemodel = new MonatTM(tsBegin,tsEnd);
		table.setModel(tablemodel);

		table.setBounds(287, 35, 651, 483);
		table.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
		table.setRowSelectionAllowed(false);
		table.setSelectionForeground(new java.awt.Color(0,0,0));
		table.setSelectionBackground(new java.awt.Color(255,255,255));
		table.setDefaultRenderer(Termin[].class, new MonthRenderer(null));
		table.setRowHeight(98);
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
	}
	
	private void initGUI() {
		try {
		
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(240,240,240));
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
				statusBar.setBorder(BorderFactory.createTitledBorder(""));
			}
			{
				terminPanel = new JPanel();
				getContentPane().add(terminPanel);
				terminPanel.setBounds(0, 28, 252, 28);
				terminPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				terminPanel.setLayout(null);
				terminPanel.setBackground(new java.awt.Color(51,73,98));
				{
					lblDatum = new JLabel();
					terminPanel.add(lblDatum);
					lblDatum.setText("Montag, 22. Mai 2006");
					lblDatum.setBounds(7, 3, 196, 21);
					lblDatum.setFont(new java.awt.Font("Tahoma",1,14));
					lblDatum.setForeground(new java.awt.Color(255,255,255));
				}
			}
			{
				today = new JPanel();
				today.setLayout(null);
				getContentPane().add(today);
				today.setBounds(0, 56, 252, 182);
				today.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				today.setBackground(new java.awt.Color(102,136,174));
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
					todayScrollPane.setBounds(7, 21, 238, 154);
					todayScrollPane.setBackground(new java.awt.Color(255,255,255));
					todayScrollPane.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					{
						
						todayList = new JList();
						todayList.addMouseListener(this);
						todayScrollPane.setViewportView(todayList);
					
					}
				}
			}
			{
				toolbar = new JToolBar();
				getContentPane().add(toolbar);
				toolbar.setBounds(0, 0, 938, 28);
				toolbar.setFloatable(false);
				toolbar.setLayout(null);
				{
					neuerTermin = new JButton();
					toolbar.add(neuerTermin);
					neuerTermin.setIcon(new ImageIcon(
						"src/images/newTermin.GIF"));
					neuerTermin.setBounds(0, 0, 28, 28);
					neuerTermin.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					neuerTermin.addMouseListener(this);
				}
				{
					jPanel3 = new JPanel();
					toolbar.add(jPanel3);
					jPanel3.setBounds(84, 0, 84, 28);
					jPanel3.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				}
				{
					editTC = new JButton();
					toolbar.add(editTC);
					editTC.setIcon(new ImageIcon(
						"src/images/editTerminContainer.GIF"));
					editTC.setBounds(196, 0, 28, 28);
					editTC.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
				}
				{
					jPanel4 = new JPanel();
					toolbar.add(jPanel4);
					jPanel4.setBounds(252, 0, 14, 28);
					jPanel4.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				}
				{
					weekBack = new JButton();
					toolbar.add(weekBack);
					weekBack.setBounds(294, 0, 28, 28);
					weekBack.setIcon(new ImageIcon("src/images/weekBack.GIF"));
					weekBack.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					weekBack.addMouseListener(this);
				}
				{
					updateTable();
					toolbar.add(lblMonth);
					lblMonth.setFont(new java.awt.Font("Tahoma",1,12));
					lblMonth.setBounds(350, 0, 343, 28);
					lblMonth.setHorizontalTextPosition(JLabel.CENTER);
					lblMonth.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
				}
				{
					weekNext = new JButton();
					toolbar.add(weekNext);
					weekNext.setBounds(721, 0, 28, 28);
					weekNext.setIcon(new ImageIcon("src/images/weekNext.GIF"));
					weekNext.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					weekNext.addMouseListener(this);
				}
				{
					jJumpToToday = new JButton();
					toolbar.add(jJumpToToday);
					jJumpToToday
						.setIcon(new ImageIcon("src/images/jumpToToday.GIF"));
					jJumpToToday.setBounds(798, 0, 28, 28);
					jJumpToToday.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					jJumpToToday.addMouseListener(this);
				}
				{
					jPanel2 = new JPanel();
					toolbar.add(jPanel2);
					jPanel2.setBounds(826, 0, 56, 28);
					jPanel2.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					jPanel2.setLayout(null);
					{
						zoomBox = new JCheckBox();
						jPanel2.add(zoomBox);
						zoomBox.setText("Zoom");
						zoomBox.setBounds(4, 6, 49, 14);
						zoomBox.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
					}
				}
				{
					ImageIcon icon3 = new ImageIcon(
						"src/images/wochenansicht.gif");
					viewButton2 = new JButton(icon3);
					toolbar.add(viewButton2);
					viewButton2.addMouseListener(this);
					viewButton2.addMouseMotionListener(this);
					viewButton2.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					viewButton2
						.setBackground(new java.awt.Color(255, 255, 255));
					viewButton2.setBounds(910, 0, 28, 28);
					viewButton2.setOpaque(false);
				}
				{
					ImageIcon icon2 = new ImageIcon(
						"src/images/monatsansicht.gif");
					viewButton1 = new JButton(icon2);
					toolbar.add(viewButton1);
					viewButton1.addMouseListener(this);
					viewButton1.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					viewButton1
						.setBackground(new java.awt.Color(255, 255, 255));
					viewButton1.setBounds(882, 0, 28, 28);
					viewButton1.setOpaque(false);
				}
				{
					monthNext = new JButton();
					toolbar.add(monthNext);
					monthNext.setBounds(749, 0, 28, 28);
					monthNext
						.setIcon(new ImageIcon("src/images/monthNext.GIF"));
					monthNext.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					monthNext.addMouseListener(this);
				}
				{
					dayNext = new JButton();
					toolbar.add(dayNext);
					dayNext.setIcon(new ImageIcon("src/images/dayNext.GIF"));
					dayNext.setBounds(693, 0, 28, 28);
					dayNext.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					dayNext.setVisible(false);
					dayNext.addMouseListener(this);
				}
				{
					dayBack = new JButton();
					toolbar.add(dayBack);
					dayBack.setBounds(322, 0, 28, 28);
					dayBack.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					dayBack.setIcon(new ImageIcon("src/images/dayBack.GIF"));
					dayBack.setVisible(false);
					dayBack.addMouseListener(this);
				}
				{
					monthBack = new JButton();
					toolbar.add(monthBack);
					monthBack
						.setIcon(new ImageIcon("src/images/monthBack.GIF"));
					monthBack.setBounds(266, 0, 28, 28);
					monthBack.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					monthBack.addMouseListener(this);
				}
				{
					deleteTC = new JButton();
					toolbar.add(deleteTC);
					deleteTC.setIcon(new ImageIcon(
						"src/images/deleteTerminContainer.GIF"));
					deleteTC.setBounds(224, 0, 28, 28);
					deleteTC.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
				}
				{
					newTerminContainer = new JButton();
					toolbar.add(newTerminContainer);
					newTerminContainer.setIcon(new ImageIcon(
						"src/images/newTerminContainer.GIF"));
					newTerminContainer.setBounds(168, 0, 28, 28);
					newTerminContainer.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
				}
				{
					editTermin = new JButton();
					toolbar.add(editTermin);
					editTermin.setIcon(new ImageIcon(
						"src/images/editTermin.GIF"));
					editTermin.setBounds(28, 0, 28, 28);
					editTermin.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					editTermin.addMouseListener(this);
				}
				{
					deleteTermin = new JButton();
					toolbar.add(deleteTermin);
					deleteTermin.setIcon(new ImageIcon(
						"src/images/deleteTermin.GIF"));
					deleteTermin.setBounds(56, 0, 28, 28);
					deleteTermin.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					deleteTermin.addMouseListener(this);
				}
				{
					jPanel5 = new JPanel();
					toolbar.add(jPanel5);
					jPanel5.setBounds(777, 0, 21, 28);
					jPanel5.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
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
				infobar.setBounds(0, 238, 252, 280);
				infobar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				infobar.setBackground(new java.awt.Color(102, 136, 174));
				{
					tab0 = new JPanel();
					infobar.addTab("Termin-Info" + "", null, tab0, null);
					tab0.setLayout(null);
					tab0.setPreferredSize(new java.awt.Dimension(224, 231));
					{
						lblBeschreibung = new JTextPane();
						tab0.add(lblBeschreibung);
						lblBeschreibung.setBounds(7, 98, 231, 147);
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
					tab1.setBackground(new java.awt.Color(244, 244, 244));
					tab1.setPreferredSize(new java.awt.Dimension(220, 336));
					{
						txtSuche = new JTextField();
						tab1.add(txtSuche);
						txtSuche.setBounds(70, 7, 168, 21);
						txtSuche.setBorder(new LineBorder(new java.awt.Color(
							0,
							0,
							0), 1, false));
					}
					{
						jLabel2 = new JLabel();
						tab1.add(jLabel2);
						jLabel2.setText("Suche");
						jLabel2.setBounds(7, 7, 56, 21);
						jLabel2.setIcon(new ImageIcon("src/images/suche.GIF"));
					}
					{
						objectScroller = new JScrollPane();
						tab1.add(objectScroller);
						objectScroller.setBounds(7, 70, 231, 175);
						objectScroller.setBorder(new LineBorder(
							new java.awt.Color(0, 0, 0),
							1,
							false));
						{
							ListModel objectListModel = new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
							objectList = new JList();
							objectScroller.setViewportView(objectList);
							objectList.setModel(objectListModel);
						}
					}
					{
						showTObjects = new JCheckBox();
						tab1.add(showTObjects);
						showTObjects.setText("zeige Objekte des Termins");
						showTObjects.setBounds(3, 35, 161, 14);
					}
					{
						showTCObjects = new JCheckBox();
						tab1.add(showTCObjects);
						showTCObjects
							.setText("zeige Objekte des Termincontainers");
						showTCObjects.setBounds(3, 52, 224, 14);
					}
				}
				{
					tab2 = new JPanel();
					infobar.addTab("verwandte Termine", null, tab2, null);
					tab2.setLayout(null);
					{
						txtSuche2 = new JTextField();
						tab2.add(txtSuche2);
						txtSuche2.setBounds(70, 7, 168, 21);
						txtSuche2.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						txtSuche2.addKeyListener(new KeyListener() {

							public void keyTyped(KeyEvent arg0) {
								
								ArrayList<String> liste = new ArrayList<String>();
								listTC2.setModel(new DefaultComboBoxModel(new String[] {}));
								for (int i = 0;i<listTC2Model.getSize();i++)
								{
									if(listTC2Model.getElementAt(i).toString().contains(txtSuche2.getText()))
									{
										liste.add(listTC2Model.getElementAt(i).toString());
									}
								}
								String[] data = new String[liste.size()];
								for (int j = 0;j<liste.size();j++)
								{
									data[j]=liste.get(j);
								}		
									listTC2.setModel(new DefaultComboBoxModel(data));	
							}

							public void keyPressed(KeyEvent arg0) {		
							}

							public void keyReleased(KeyEvent arg0) {	
							}});
					}
					{
						jLabel6 = new JLabel();
						tab2.add(jLabel6);
						jLabel6.setText("Suche");
						jLabel6.setBounds(7, 7, 63, 21);
						jLabel6.setIcon(new ImageIcon("src/images/suche.GIF"));
					}
					{
						jLabel7 = new JLabel();
						tab2.add(jLabel7);
						jLabel7.setText("Termine dieses Termincontainers");
						jLabel7.setBounds(7, 35, 182, 14);
					}
					{
						TCterminScroller = new JScrollPane();
						tab2.add(TCterminScroller);
						TCterminScroller.setBounds(7, 49, 231, 196);
						TCterminScroller.setBackground(new java.awt.Color(
							255,
							255,
							255));
						TCterminScroller.setBorder(new LineBorder(
							new java.awt.Color(0, 0, 0),
							1,
							false));
						{
							listTC2Model = new DefaultComboBoxModel(
								new String[] { "VO Mathe I am 8. Mai 2006",
										"VO Mathe I am 15. Mai 2006",
										"VO Mathe I am 22. Mai 2006",
										"VO Mathe I am 22. Mai 2006",
										"VO Mathe I am 22. Mai 2006",
										"VO Mathe I am 22. Mai 2006",
										"VO Mathe I am 22. Mai 2006",
										"VO Mathe I am 22. Mai 2006",
										"VO Mathe I am 22. Mai 2006",
										"VO Mathe I am 22. Mai 2006",
										"VO Mathe I am 29. Mai 2006" });
							listTC2 = new JList();
							TCterminScroller.setViewportView(listTC2);
							listTC2.setModel(listTC2Model);
							listTC2.setBounds(0, 0, 217, 119);
							listTC2.setBorder(BorderFactory.createEmptyBorder(
								0,
								0,
								0,
								0));
							listTC2.addMouseListener(this);
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
				timetable.setBounds(253, 35, 35, 483);
				timetable.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				timetable.setVisible(false);
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
					}
					{
						menu_add_T = new JMenuItem();
						menu_add.add(menu_add_T);
						menu_add_T.setText("Termin ...");
					}
					{
						menu_add_Kontakt = new JMenuItem();
						menu_add.add(menu_add_Kontakt);
						menu_add_Kontakt.setText("Kontakt ...");
					}
					{
						menu_add_Pruefung = new JMenuItem();
						menu_add.add(menu_add_Pruefung);
						menu_add_Pruefung.setText("Prüfung ...");
					}
					{
						menu_add_LM = new JMenuItem();
						menu_add.add(menu_add_LM);
						menu_add_LM.setText("Lehrmittel ...");
					}
				}
				{
					menu_search = new JMenu();
					mainMenu.add(menu_search);
					menu_search.setText("Suchen ...");
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
		if(arg0.getSource().equals(viewButton1))
		{
			setModel("month");
			layoutTable();
			timetable.setVisible(false);
		}
		if(arg0.getSource().equals(viewButton2) )
		{
			setModel("week");
			layoutTable();
			timetable.setVisible(true);
		}
		if(arg0.getSource().equals(table))
		{ //zeigt den angeklickten Tag in der Infobar an
			if(zoomBox.getSelectedObjects()!=null) tableZoom();
			currentCell.x = table.rowAtPoint(table.getMousePosition());
			currentCell.y = table.columnAtPoint(table.getMousePosition());
			try
			{
				currentValue = (Termin[])table.getValueAt(table.rowAtPoint(table.getMousePosition()),table.columnAtPoint(table.getMousePosition()));

				c_marker.set(Integer.parseInt(currentValue[0].getSecondaryTitle().substring(0,4)),Integer.parseInt(currentValue[0].getSecondaryTitle().substring(5,7))-1,Integer.parseInt(currentValue[0].getSecondaryTitle().substring(8,10)));
			}
			catch(Exception ex) {System.out.println(ex.toString());}; 

			try
			{
				
				
				Calendar cal2 = new GregorianCalendar();
	    		cal2.set(Integer.parseInt(currentValue[0].getSecondaryTitle().substring(0,4)),Integer.parseInt(currentValue[0].getSecondaryTitle().substring(5,7))-1,Integer.parseInt(currentValue[0].getSecondaryTitle().substring(8,10)));
	    		Date dat = new Date(cal2.getTimeInMillis());
	    		String title = converter.toLong(dat.toString());
	    		lblDatum.setText(title);
	    		
	    		data = new String[currentValue.length-1];
	    		
	    		
	    		
	    			for(int i = 0;i<currentValue.length-1;i++)
		    		{
		    			data[i]=currentValue[i+1].getDate().toString().substring(11,16) + " " + currentValue[i+1].getSecondaryTitle();
		    			
		    		}
	    			loadList(data);

	    		if(viewMonth) lblToday.setText("Heutige Termine:");
	    		else lblToday.setText("Heutige Termine zwischen "+(table.getSelectedRow()+6)+":00 und "+(table.getSelectedRow()+7)+":00");
				
	    		if(viewMonth) setModel("month");
				else setModel("week");
				
			}
			catch(Exception ex){System.out.println(ex.toString());};
					
		}
		if(arg0.getSource().equals(dayNext))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+1);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(dayBack))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)-1);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(weekNext))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+7);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(weekBack))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)-7);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(monthNext))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+28);		
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(monthBack))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)-28);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(jJumpToToday)) {
			setTimeSpace(new GregorianCalendar());
		}
		if(arg0.getSource().equals(todayList)) {
			selection = todayList.getSelectedIndex();
			loadTerminData(false);
		}
		if(arg0.getSource().equals(neuerTermin)) {
			EditTerminFrame newTermin = new EditTerminFrame(this,c_marker, null);
			newTermin.setTitle("neuen Termin hinzufügen");
			newTermin.setLocation(this.getLocation().x+30,this.getLocation().y+30);
			newTermin.setVisible(true);
			updateTable();
			updateInfoBar(false);
		}
		if(arg0.getSource().equals(editTermin)) {
			
			if(terminId != -1){
			EditTerminFrame newTermin = new EditTerminFrame(this,null, terminId);
			newTermin.setTitle("Termin bearbeiten");
			newTermin.setLocation(this.getLocation().x+30,this.getLocation().y+30);
			newTermin.setVisible(true);
		
			}
			
		}
		if(arg0.getSource().equals(deleteTermin)) {
			System.out.println("++++ " + terminId);
			GenericDAO gdo = new GenericDAO();
			if(terminId != -1){
				String query = "delete from termin where id =" +terminId;
				try {
					SecurityDialog diag = new SecurityDialog(this,"Termin löschen","Wollen Sie den gewählten Termin wirklich löschen?");
					diag.setLocation(this.getLocation().x+40,this.getLocation().y+40);
					diag.setVisible(true);
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
		if(arg0.getSource().equals(view_dayNext))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+1);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(view_dayBack))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)-1);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(view_weekNext))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+7);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(view_weekBack))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)-7);
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(view_monthNext))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)+28);		
			setTimeSpace(calStart);
		}
		if(arg0.getSource().equals(view_monthBack))
		{
			calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH)-28);
			setTimeSpace(calStart);
		}
		
	}

	public void mouseEntered(MouseEvent arg0) 
	{	
		if(arg0.getSource().equals(viewButton1)){
			statusBar.setText("Wechselt das Kalenderlayout auf die Monatsansicht");
		}
		if(arg0.getSource().equals(viewButton2)){
			statusBar.setText("Wechselt das Kalenderlayout auf die Wochenansicht");
		}
		if(arg0.getSource().equals(table)){		
			statusBar.setText("Klicken Sie auf einen Tag um ihn und dessen Termine in der Infobar anzuzeigen");
			mouseEntered = true;
		}
		if(arg0.getSource().equals(neuerTermin)){
			statusBar.setText("Fügt einen neuen Termin hinzu. Wenn Sie vorher auf einen Tag klicken wird dieser automatisch in das Termindatum übernommen.");	
		}
		if(arg0.getSource().equals(editTermin)){
			statusBar.setText("Bearbeiten des gerade gewählten Termins.");	
		}
		if(arg0.getSource().equals(deleteTermin)){
			statusBar.setText("Löscht den gerade gewählten Termin.");	
		}
		if(arg0.getSource().equals(zoomBox)){
			statusBar.setText("Aktiviert/Deaktiviert die automatische Vergrößerung eines Tages in der Monatsansicht wenn der Mauszeiger darübergeführt wird.");	
		}
		
		if(arg0.getSource().equals(monthBack)){
			statusBar.setText("Verschiebt die Ansicht um 1 Monat (4 Wochen) zurück.");	
		}
		if(arg0.getSource().equals(weekBack)){
			statusBar.setText("Verschiebt die Ansicht um 1 Woche zurück.");	
		}
		if(arg0.getSource().equals(dayBack)){
			statusBar.setText("Verschiebt die Ansicht um 1 Tag zurück.");	
		}
		if(arg0.getSource().equals(dayNext)){
			statusBar.setText("Verschiebt die Ansicht um 1 Tag nach vor.");	
		}
		if(arg0.getSource().equals(weekNext)){
			statusBar.setText("Verschiebt die Ansicht um 1 Woche nach vor.");	
		}
		if(arg0.getSource().equals(monthNext)){
			statusBar.setText("Verschiebt die Ansicht um 1 Monat (4 Wochen) nach vor.");	
		}
		if(arg0.getSource().equals(listTC2)) {
			statusBar.setText("Klicken Sie auf einen Termin um ihn in der Infobar anzuzeigen.");
		}
		if(arg0.getSource().equals(dateJumper)) {
			statusBar.setText("Geben Sie ein Datum im Format TT.MM.JJJJ ein und bestätigen sie mit 'ENTER'");
		}
		if(arg0.getSource().equals(jJumpToToday)) {
			statusBar.setText("Setzt den Kalender auf das heutige Datum.");
		}
		if(arg0.getSource().equals(todayList)) {
			statusBar.setText("Klick auf einen Termin öffnet dessen Details");
		}
	}

	public void mouseExited(MouseEvent arg0) {
		
		if(arg0.getSource().equals(viewButton1)){
			statusBar.setText("");
		}
		if(arg0.getSource().equals(viewButton2)){
			statusBar.setText("");
		}
		if(arg0.getSource().equals(table)){
			statusBar.setText("");
			mouseEntered = false;
			layoutTable();
			
		}
		if(arg0.getSource().equals(neuerTermin)){
			statusBar.setText("");
		}
		if(arg0.getSource().equals(editTermin)){
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(deleteTermin)){
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(zoomBox)){
			statusBar.setText("");	
		}
		
		if(arg0.getSource().equals(monthBack)){
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(weekBack)){
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(dayBack)){
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(dayNext)){
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(weekNext)){
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(monthNext)){
			statusBar.setText("");	
		}
		if(arg0.getSource().equals(listTC2)) {
			statusBar.setText("");
		}
		if(arg0.getSource().equals(jJumpToToday)) {
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
							else { k = 31; } //Die anderen Reihen werden verkleinert
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
			table.setRowHeight(98);	
		}
		else table.setRowHeight(0,20);
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
			table.setRowHeight(0,20);
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
		this.setTitle("autoPSI | " + lblMonth.getText());
	}
	
	/*Liest Termine aus einer Tag-Zelle aus und füllt sie in die Today-Liste
	 * 
	 */
	private void loadList(String[] data)
	
	{
		ListModel listmodel = new DefaultComboBoxModel(data);
		todayList.setModel(listmodel);
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
			dayBack.setVisible(true);
			dayNext.setVisible(true);
			viewMonth = false;
			table.setRowHeight(31);
			setTimeSpace(calStart);
			timetable.setRowHeight(31);
			timetable.setRowHeight(0,20);
			table.setShowVerticalLines(true);
		}
		else if (view.equals("month"))
		{
			lblToday.setText("heutige Termine:");
			table.setDefaultRenderer(Termin[].class, new MonthRenderer(c_marker));
			view_dayBack.setVisible(false);
			view_dayNext.setVisible(false);
			dayBack.setVisible(false);
			dayNext.setVisible(false);
			viewMonth = true;
			table.setRowHeight(98);
			setTimeSpace(calStart);
			table.setShowVerticalLines(false);
		}
	}
	
	public void loadTerminData(boolean update)
	{
		try
		{
			currentValue = (Termin[])table.getValueAt(currentCell.x,currentCell.y);
			if(todayList.getModel().getSize()>0) {
				IGenericDAO igdao = new GenericDAO();
				Termin termin = new Termin();
		
				String  query = "select * from termin where date='" +currentValue[0].getSecondaryTitle().toString()+" "+todayList.getSelectedValue().toString().substring(0,5)+":000'";
				List<GenericDataObject> data = igdao.unsafeQuery(query,termin);					
				termin = (Termin)data.get(0);
			
				int dauer = termin.getDuration();
				terminId = termin.getId();
				int stunden = 0;
				int minuten = 0;
				
				while(dauer>59)
				{
				dauer = dauer -60;
				stunden++;
				}
				minuten = dauer;
				
				lblZeit.setText("Zeit: "+todayList.getSelectedValue().toString().substring(0,5)+"           Dauer "+stunden+":"+minuten);

				
				lblTermin.setText(termin.getSecondaryTitle());
				
				lblOrt.setText("Ort: "+termin.getPlace());
				lblBeschreibung.setText(termin.getDescription());
				
				IGenericDAO gdo = new GenericDAO();
				String query2 = "select * from termincontainer where id="+termin.getTerminContainerID();
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
				
			}
			
		}
		catch(Exception ex) {System.out.println("error: " + ex.toString());}			
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
	public void updateInfoBar(boolean select)
	{
		currentValue = (Termin[])table.getValueAt(currentCell.x,currentCell.y);
		data = new String[currentValue.length-1];
	
	
		
	
		for(int i = 0;i<currentValue.length-1;i++)
		{
			data[i]=currentValue[i+1].getDate().toString().substring(11,16) + " " + currentValue[i+1].getSecondaryTitle();
		
		}
		loadList(data);
		if(select) todayList.setSelectedIndex(selection);
		
		loadTerminData(true);
	}

}