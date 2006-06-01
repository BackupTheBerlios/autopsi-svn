package autopsi.gui;
import java.awt.event.*;
import java.util.*;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JLabel;
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

import autopsi.basis.model.MonatTM;
import autopsi.gui.MonthRenderer;

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
	private JButton newTerminContainer;
	private JPanel tab0;
	private JLabel jLabel2;
	private JTextField txtSuche;
	private JPanel tab2;
	private JTextPane lblBeschreibung;
	private JTabbedPane infobar;
	private JPanel jPanel4;
	private JPanel jPanel1;
	private JToolBar toolbar;
	private JButton dayNext;
	private JButton dayBack;
	private JPanel jPanel3;
	private JButton deleteTC;
	private JButton editTC;
	private JPanel jPanel2;
	private JSeparator jSeparator1;
	private JCheckBox showTCObjects;
	private JCheckBox showTObjects;
	private JList objectList;
	private JScrollPane objectScroller;
	private JScrollPane TCterminScroller;
	private JLabel jLabel9;
	private JList todayList;
	private JScrollPane todayScrollPane;
	private JLabel jLabel8;
	private JPanel today;
	private JMenuItem newTC;
	private JMenu menu;
	private JButton monthNext;
	private JButton weekNext;
	private JButton weekBack;
	private JButton monthBack;
	private JLabel lblMonth;
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
	private JMenuItem zeigeTermine;
	private JMenuItem neuerZeitpunkt;
	private JMenu menu2;
	private JMenuBar mainMenu;
	private JLabel statusBar;
	DayFrame dayframe = new DayFrame();
	boolean mouseEntered,mouseDown = false;
	Date datum = new Date();
	int x = 0;
	int row1,row2,col1,col2 = 0;
	DateConverter converter = new DateConverter();
	boolean viewMonth = true;
	

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		mainFrame inst = new mainFrame();
		inst.setVisible(true);
	}
	
	public mainFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(240,240,240));
			this.setMaximumSize(new java.awt.Dimension(900, 653));
			this.setResizable(false);
			this.setTitle("Stephe @ autoPSI - Mai 2006");
			{ 
				
				table = new JTable();	
				MonatTM tableModel = new MonatTM();			
				table.setBounds(253, 28, 637, 497);
				table.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				table.setRowSelectionAllowed(false);
				table.setSelectionForeground(new java.awt.Color(0,0,0));
				table.setSelectionBackground(new java.awt.Color(255,255,255));
				table.setDefaultRenderer(String[].class, new MonthRenderer(false));	
				//table.setDefaultEditor(String[].class,new MonthEditor());
				table.setRowHeight(98);
				table.addMouseListener(this);
				table.addMouseMotionListener(this);
				
				
				DefaultTableColumnModel cm = new DefaultTableColumnModel();
				for (int i=0;i<7;i++)
				{
					TableColumn col = new TableColumn(0, 92);
					cm.addColumn(col);
					
				}
				table.setColumnModel(cm);
				table.setModel(tableModel);
				this.getContentPane().add(table);
				table.setCellSelectionEnabled(true);
				table.setGridColor(new java.awt.Color(0,0,0));
				table.setIntercellSpacing(new java.awt.Dimension(2, 2));
				table.setShowVerticalLines(false);
				table.setShowHorizontalLines(false);
				table.setBackground(new java.awt.Color(230,230,230));
			}
			{
				statusBar = new JLabel();
				getContentPane().add(statusBar);
				statusBar.setBounds(7, 527, 882, 21);
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
					jLabel8 = new JLabel();
					today.add(jLabel8);
					jLabel8.setText("heutige Termine:");
					jLabel8.setBounds(7, 7, 84, 14);
					jLabel8.setForeground(new java.awt.Color(255,255,255));
				}
				{
					todayScrollPane = new JScrollPane();
					
					today.add(todayScrollPane);
					todayScrollPane.setBounds(7, 21, 238, 147);
					todayScrollPane.setBackground(new java.awt.Color(255,255,255));
					todayScrollPane.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					{
						ListModel todayListModel = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two",
									"3","4","5","6","7","8","9","10"});
						todayList = new JList();
						todayScrollPane.setViewportView(todayList);
						todayList.setModel(todayListModel);
					}
				}
			}
			{
				toolbar = new JToolBar();
				getContentPane().add(toolbar);
				toolbar.setBounds(0, 0, 896, 28);
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
					jPanel4.setBounds(252, 0, 63, 28);
					jPanel4.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				}
				{
					weekBack = new JButton();
					toolbar.add(weekBack);
					weekBack.setBounds(343, 0, 28, 28);
					weekBack.setIcon(new ImageIcon("src/images/weekBack.GIF"));
					weekBack.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					weekBack.addMouseListener(this);
				}
				{
					lblMonth = new JLabel();
					toolbar.add(lblMonth);
					lblMonth.setText("Mai 2006");
					lblMonth.setFont(new java.awt.Font("Tahoma", 1, 18));
					lblMonth.setBounds(399, 0, 161, 28);
					lblMonth.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
				}
				{
					weekNext = new JButton();
					toolbar.add(weekNext);
					weekNext.setBounds(588, 0, 28, 28);
					weekNext.setIcon(new ImageIcon("src/images/weekNext.GIF"));
					weekNext.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					weekNext.addMouseListener(this);
				}
				{
					jPanel2 = new JPanel();
					toolbar.add(jPanel2);
					jPanel2.setBounds(644, 0, 56, 28);
					jPanel2.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					jPanel2.setLayout(null);
					{
						zoomBox = new JCheckBox();
						jPanel2.add(zoomBox);
						zoomBox.setText("Zoom");
						zoomBox.setBounds(4, 6, 49, 14);
						zoomBox.setBorder(BorderFactory.createEmptyBorder(
							0,
							0,
							0,
							0));
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
					viewButton2.setBounds(728, 0, 28, 28);
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
					viewButton1.setBounds(700, 0, 28, 28);
					viewButton1.setOpaque(false);

				}
				{
					monthNext = new JButton();
					toolbar.add(monthNext);
					monthNext.setBounds(616, 0, 28, 28);
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
					dayNext.setBounds(560, 0, 28, 28);
					dayNext.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					dayNext.setEnabled(false);
					dayNext.addMouseListener(this);
				}
				{
					dayBack = new JButton();
					toolbar.add(dayBack);
					dayBack.setBounds(371, 0, 28, 28);
					dayBack.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					dayBack.setIcon(new ImageIcon("src/images/dayBack.GIF"));
					dayBack.setEnabled(false);
					dayBack.addMouseListener(this);
				}
				{
					monthBack = new JButton();
					toolbar.add(monthBack);
					monthBack
						.setIcon(new ImageIcon("src/images/monthBack.GIF"));
					monthBack.setBounds(315, 0, 28, 28);
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
			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1);
				jPanel1.setBounds(0, 238, 252, 287);
				jPanel1.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				jPanel1.setBackground(new java.awt.Color(166,186,208));
				{
					infobar = new JTabbedPane();
					jPanel1.add(infobar);
					infobar.setBounds(350, 189, 231, 280);
					infobar.setBorder(BorderFactory.createEmptyBorder(
						0,
						0,
						0,
						0));
					infobar.setBackground(new java.awt.Color(102, 136, 174));
					infobar.setPreferredSize(new java.awt.Dimension(231, 272));
					{
						tab0 = new JPanel();
						infobar.addTab("Termin-Info" + "", null, tab0, null);
						tab0.setLayout(null);
						tab0.setPreferredSize(new java.awt.Dimension(224, 231));
						{
							lblBeschreibung = new JTextPane();
							tab0.add(lblBeschreibung);
							lblBeschreibung
								.setText("Thema: Matrizen\nPrüfungsanmeldung\nAnmeldung zum Vorbereitungstutorium");
							lblBeschreibung.setBounds(7, 112, 210, 126);
							lblBeschreibung.setOpaque(false);
							lblBeschreibung.setBorder(BorderFactory
								.createEmptyBorder(0, 0, 0, 0));
						}
						{
							lblTerminContainer = new JLabel();
							tab0.add(lblTerminContainer);
							lblTerminContainer
								.setText("VO Mathematik für Inf. I");
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
							lblTermin.setText("VO Mathe");
							lblTermin.setIcon(new ImageIcon(
								"src/images/termin.GIF"));
							lblTermin.setBounds(7, 7, 224, 21);
							lblTermin
								.setFont(new java.awt.Font("Tahoma", 1, 12));
						}
						{
							lblOrt = new JLabel();
							tab0.add(lblOrt);
							lblOrt.setText("Ort: AudiMax");
							lblOrt.setBounds(7, 56, 224, 14);
						}
						{
							jLabel9 = new JLabel();
							tab0.add(jLabel9);
							jLabel9.setText("Beschreibung:");
							jLabel9.setBounds(11, 89, 84, 14);
						}
						{
							jSeparator1 = new JSeparator();
							tab0.add(jSeparator1);
							jSeparator1.setBounds(7, 105, 217, 7);
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
							txtSuche.setBounds(70, 7, 147, 21);
							txtSuche.setBorder(new LineBorder(
								new java.awt.Color(0, 0, 0),
								1,
								false));
						}
						{
							jLabel2 = new JLabel();
							tab1.add(jLabel2);
							jLabel2.setText("Suche");
							jLabel2.setBounds(7, 7, 56, 21);
							jLabel2.setIcon(new ImageIcon(
								"src/images/suche.GIF"));
						}
						{
							objectScroller = new JScrollPane();
							tab1.add(objectScroller);
							objectScroller.setBounds(7, 70, 210, 168);
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
							txtSuche2.setBounds(70, 7, 147, 21);
							txtSuche2.setBorder(new LineBorder(
								new java.awt.Color(0, 0, 0),
								1,
								false));
						}
						{
							jLabel6 = new JLabel();
							tab2.add(jLabel6);
							jLabel6.setText("Suche");
							jLabel6.setBounds(7, 7, 63, 21);
							jLabel6.setIcon(new ImageIcon(
								"src/images/suche.GIF"));

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
							TCterminScroller.setBounds(7, 49, 210, 189);
							TCterminScroller.setBackground(new java.awt.Color(
								255,
								255,
								255));
							TCterminScroller.setBorder(new LineBorder(
								new java.awt.Color(0, 0, 0),
								1,
								false));
							{
								ListModel listTC2Model = new DefaultComboBoxModel(
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
								listTC2.setBorder(BorderFactory
									.createEmptyBorder(0, 0, 0, 0));
								listTC2.addMouseListener(this);
							}
						}
					}
				}
			}
			{
				mainMenu = new JMenuBar();
				setJMenuBar(mainMenu);
				mainMenu.setPreferredSize(new java.awt.Dimension(892, 24));
				{
					menu = new JMenu();
					mainMenu.add(menu);
					menu.setText("Termincontainer");
					menu.setBackground(new java.awt.Color(245,245,245));
					{
						newTC = new JMenuItem();
						menu.add(newTC);
						newTC.setText("neuer Termincontainer ...");
					}
				}
				
				{
					menu2 = new JMenu();
					mainMenu.add(menu2);
					menu2.setText("Termin");
					menu2.setBorderPainted(true);
					{
						neuerZeitpunkt = new JMenuItem();
						menu2.add(neuerZeitpunkt);
						neuerZeitpunkt.setText("neuer Termin ...");
					}
					{
						zeigeTermine = new JMenuItem();
						menu2.add(zeigeTermine);
						zeigeTermine.setText("Terminübersicht anzeigen");
					}
				}
			}
			pack();
			this.setSize(897, 604);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(viewButton1))
		{
			dayBack.setEnabled(false);
			dayNext.setEnabled(false);
			viewMonth = true;
			table.setRowHeight(98);
		}
		if(arg0.getSource().equals(viewButton2))
		{
			dayBack.setEnabled(true);
			dayNext.setEnabled(true);
			viewMonth = false;
			table.setRowHeight(497);
		}
		if(arg0.getSource().equals(table))
		{ //zeigt den angeklickten Tag in der Infobar an
			try
			{
				Object bla = (table.getValueAt(table.rowAtPoint(table.getMousePosition()),table.columnAtPoint(table.getMousePosition())));
				if(bla instanceof String[])
				{
				String [] datum = (String[])bla;
				lblDatum.setText(converter.toLong(datum[0]));
				}
			}
			catch(Exception ex)
			{				
			}		
		}
	}

	public void mousePressed(MouseEvent arg0) {
		
		if(arg0.getSource().equals(table)){
			mouseDown = true;
		}	
	}

	public void mouseReleased(MouseEvent arg0) {
		
		if(arg0.getSource().equals(table)){
			mouseDown = false;
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		
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
			statusBar.setText("Verschiebt die Ansicht um 1 Monat zurück.");	
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
			statusBar.setText("Verschiebt die Ansicht um 1 Monat nach vor.");	
		}
		if(arg0.getSource().equals(listTC2)) {
			statusBar.setText("Klicken Sie auf einen Termin um ihn in der Infobar anzuzeigen.");
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
			
			if(viewMonth)
			{
				table.setRowHeight(98);	
			}
			DefaultTableColumnModel cm = new DefaultTableColumnModel();
			for (int i=0;i<7;i++)
			{
				TableColumn col = new TableColumn(i, 92);
				cm.addColumn(col);
			}
			table.setColumnModel(cm);
			
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
	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(table)){
			
			x=0;
			if(zoomBox.getSelectedObjects()!=null) tableZoom();		
		}
	}
	
	private void tableZoom()
	{
//		 TableZoom Engine (c) 2006 by Stephe ;-) ---------------------------
		
		if(mouseEntered && zoomBox.getSelectedObjects()!= null)
		{
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
			DefaultTableColumnModel cm = new DefaultTableColumnModel();
			
			j = table.columnAtPoint(table.getMousePosition());
			for (i=0;i<7;i++)
			{
				if (i==j){ k = 200; } //Spalte j ist die Spalte, über die gerade "gehovert" wird
				else {k = 92; } //Die anderen Spalten werden verkleinert
				
				TableColumn col = new TableColumn(i, k);
				cm.addColumn(col);
			}
			table.setColumnModel(cm); //Spaltenlayout auf die Tabelle bringen
		}
			
		
//		 ------------------------------------------------------------------
	}

}
