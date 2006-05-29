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
import javax.swing.border.SoftBevelBorder;
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
	private JPanel tab0;
	private JList listT;
	private JLabel jLabel2;
	private JTextField txtSuche;
	private JPanel tab2;
	private JTextPane lblBeschreibung;
	private JTabbedPane infobar;
	private JLabel jLabel4;
	private JButton deleteTermin;
	private JButton editTermin;
	private JButton neuerTermin;
	private JList listTC2;
	private JLabel jLabel7;
	private JList listT2;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JCheckBox zoomBox;
	private JTextField txtSuche2;
	private JList listTC;
	private JLabel jLabel3;
	private JPanel terminPanel;
	private JLabel lblTerminContainer;
	private JLabel lblOrt;
	private JLabel lblZeit;
	private JLabel lblTermin;
	private JPanel tab1;
	private JSeparator jSeparator1;
	private JMenuItem zeigeTermine;
	private JMenuItem neuerZeitpunkt;
	private JToolBar toolbar;
	private JMenu menu2;
	private JMenuBar mainMenu;
	private JLabel jLabel1;
	boolean mouseEntered = false;
	private JComboBox combo;
	Date datum = new Date();
	

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
			getContentPane().setBackground(new java.awt.Color(233,233,233));
			this.setMaximumSize(new java.awt.Dimension(900, 600));
			this.setResizable(false);
			this.setTitle("autoPSI [GUI Prototyp]");
			{
				toolbar = new JToolBar();
				getContentPane().add(toolbar);
				toolbar.setBounds(0, 0, 889, 35);
				toolbar.setBackground(new java.awt.Color(255,255,255));
				toolbar.setFloatable(false);
				toolbar.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				{
					jSeparator1 = new JSeparator();
					toolbar.add(jSeparator1);
					jSeparator1.setPreferredSize(new java.awt.Dimension(673, 24));
				}
				{
					zoomBox = new JCheckBox();
					toolbar.add(zoomBox);
					zoomBox.setText("Kalender-Zoom");
					zoomBox.setBounds(133, 21, 63, 28);
					zoomBox.setPreferredSize(new java.awt.Dimension(102, 13));
					zoomBox.setOpaque(false);
				}
				{
					ImageIcon icon2 = new ImageIcon("src/images/monatsansicht.gif");
					viewButton1 = new JButton(icon2);
					viewButton1.addMouseListener(this);
					toolbar.add(viewButton1);
					viewButton1.setText("Monatsansicht");
					viewButton1.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					viewButton1.setPreferredSize(new java.awt.Dimension(104, 22));
					viewButton1.setBackground(new java.awt.Color(255,255,255));
					viewButton1.setSize(104, 22);
					
				}
				{
					ImageIcon icon3 = new ImageIcon("src/images/wochenansicht.gif");
					viewButton2 = new JButton(icon3);
					toolbar.add(viewButton2);
					viewButton2.addMouseListener(this);
					viewButton2.setText("Wochenansicht");
					viewButton2.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					viewButton2.setPreferredSize(new java.awt.Dimension(104, 22));
					viewButton2.setSize(104, 19);
					viewButton2.setBackground(new java.awt.Color(255,255,255));
				}
			}
			{ 
				
				table = new JTable();
				getContentPane().add(table);
				MonatTM tableModel = new MonatTM();
			table.setDefaultRenderer(String.class, new MonthRenderer());
				table.setBounds(245, 35, 644, 490);
				table.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				table.setRowSelectionAllowed(false);
				table.setSelectionForeground(new java.awt.Color(0,0,0));
				table.setSelectionBackground(new java.awt.Color(255,255,255));
				
				table.setRowHeight(98);
				table.setVerifyInputWhenFocusTarget(false);
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
				table.setDefaultRenderer(String.class,new MonthRenderer());
				
			
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setBounds(1, 525, 889, 21);
				jLabel1.setBackground(new java.awt.Color(234,234,234));
				jLabel1.setOpaque(true);
			}
			{
				infobar = new JTabbedPane();
				getContentPane().add(infobar);
				infobar.setBounds(0, 126, 238, 364);
				{
					tab0 = new JPanel();
					infobar.addTab("Beschreibung", null, tab0, null);
					{
						lblBeschreibung = new JTextPane();
						tab0.add(lblBeschreibung);
						lblBeschreibung.setText("Thema: Matrizen\nPrüfungsanmeldung\nAnmeldung zum Vorbereitungstutorium");
						lblBeschreibung.setBounds(0, 91, 203, 98);
						lblBeschreibung.setOpaque(false);
						lblBeschreibung.setPreferredSize(new java.awt.Dimension(227, 317));
					}
				}
				{
					tab1 = new JPanel();
					infobar.addTab("Objekte", null, tab1, null);
					tab1.setLayout(null);
					tab1.setBackground(new java.awt.Color(244,244,244));
					tab1.setPreferredSize(new java.awt.Dimension(220, 336));
					{
						txtSuche = new JTextField();
						tab1.add(txtSuche);
						txtSuche.setBounds(70, 7, 154, 21);
						txtSuche.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						txtSuche.setText(" test");
					}
					{
						jLabel2 = new JLabel();
						tab1.add(jLabel2);
						jLabel2.setText("Suche");
						jLabel2.setBounds(7, 7, 56, 21);
						jLabel2.setIcon(new ImageIcon("src/images/suche.GIF"));
					}
					{
						ListModel listTModel = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two","bla","bla","bla","bla","bla" });
						listT = new JList();
						tab1.add(listT);
						listT.setModel(listTModel);
						listT.setBounds(7, 56, 217, 126);
						listT.setBorder(BorderFactory.createTitledBorder(""));
					}
					{
						jLabel3 = new JLabel();
						tab1.add(jLabel3);
						jLabel3.setText("An den Termin angehängte Objekte");
						jLabel3.setBounds(7, 39, 189, 14);
					}
					{
						jLabel4 = new JLabel();
						tab1.add(jLabel4);
						jLabel4.setText("An den Termincontainer angehängte Objekte");
						jLabel4.setBounds(7, 195, 217, 14);
					}
					{
						ListModel listTCModel = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
						listTC = new JList();
						tab1.add(listTC);
						listTC.setModel(listTCModel);
						listTC.setBounds(7, 217, 217, 112);
						listTC.setBorder(BorderFactory.createTitledBorder(""));
					}
				}
				{
					tab2 = new JPanel();
					infobar.addTab("alle Termine", null, tab2, null);
					tab2.setLayout(null);
					{
						txtSuche2 = new JTextField();
						tab2.add(txtSuche2);
						txtSuche2.setBounds(42, 4, 182, 21);
					}
					{
						jLabel5 = new JLabel();
						tab2.add(jLabel5);
						jLabel5.setText("heutige Termine");
						jLabel5.setBounds(7, 39, 196, 14);
					}
					{
						jLabel6 = new JLabel();
						tab2.add(jLabel6);
						jLabel6.setText("Suche");
						jLabel6.setBounds(7, 7, 35, 14);
					}
					{
						ListModel listT2Model = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
						listT2 = new JList();
						tab2.add(listT2);
						listT2.setModel(listT2Model);
						listT2.setBounds(7, 56, 217, 119);
						listT2.setBorder(BorderFactory.createTitledBorder(""));
					}
					{
						jLabel7 = new JLabel();
						tab2.add(jLabel7);
						jLabel7.setText("Termine dieses Termincontainers");
						jLabel7.setBounds(7, 190, 182, 14);
					}
					{
						ListModel listTC2Model = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
						listTC2 = new JList();
						tab2.add(listTC2);
						listTC2.setModel(listTC2Model);
						listTC2.setBounds(7, 210, 217, 119);
						listTC2.setBorder(BorderFactory.createTitledBorder(""));
					}
				}
			}
			{
				terminPanel = new JPanel();
				getContentPane().add(terminPanel);
				terminPanel.setBounds(0, 35, 238, 91);
				terminPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				terminPanel.setLayout(null);
				terminPanel.setBackground(new java.awt.Color(255,255,255));
				{
					lblTermin = new JLabel();
					terminPanel.add(lblTermin);
					lblTermin.setText("VO Mathe");
					lblTermin.setIcon(new ImageIcon("src/images/termin.GIF"));
					lblTermin.setBounds(4, 28, 189, 21);
					lblTermin.setFont(new java.awt.Font("Tahoma", 1, 12));
				}
				{
					lblZeit = new JLabel();
					terminPanel.add(lblZeit);
					lblZeit.setText("Montag, 22. Mai 2006 --- 9.00-10.00");
					lblZeit.setBounds(4, 55, 196, 14);
				}
				{
					lblOrt = new JLabel();
					terminPanel.add(lblOrt);
					lblOrt.setText("AudiMax");
					lblOrt.setBounds(4, 71, 133, 14);
				}
				{
					lblTerminContainer = new JLabel();
					terminPanel.add(lblTerminContainer);
					lblTerminContainer.setText("VO Mathematik für Inf. I");
					lblTerminContainer.setBounds(4, 4, 196, 21);
					lblTerminContainer.setIcon(new ImageIcon("src/images/lva.GIF"));
					lblTerminContainer.setFont(new java.awt.Font("Tahoma",1,10));
				}
			}
			{
				neuerTermin = new JButton();
				getContentPane().add(neuerTermin);
				neuerTermin.setBounds(0, 497, 42, 28);
				neuerTermin.setIcon(new ImageIcon("src/images/newTermin.GIF"));
				neuerTermin.addMouseListener(this);
			}
			{
				editTermin = new JButton();
				getContentPane().add(editTermin);
				editTermin.setBounds(42, 497, 42, 28);
				editTermin.setIcon(new ImageIcon("src/images/editTermin.GIF"));
				editTermin.addMouseListener(this);
			}
			{
				deleteTermin = new JButton();
				getContentPane().add(deleteTermin);
				deleteTermin.setBounds(84, 497, 42, 28);
				deleteTermin.setIcon(new ImageIcon("src/images/deleteTermin.GIF"));
				deleteTermin.addMouseListener(this);
			}
			{
				mainMenu = new JMenuBar();
				setJMenuBar(mainMenu);
				mainMenu.setPreferredSize(new java.awt.Dimension(892, 24));
				
				{
					menu2 = new JMenu();
					mainMenu.add(menu2);
					menu2.setText("Zeitpunkt");
					menu2.setBorderPainted(true);
					{
						neuerZeitpunkt = new JMenuItem();
						menu2.add(neuerZeitpunkt);
						neuerZeitpunkt.setText("neuer Zeitpunkt");
					}
					{
						zeigeTermine = new JMenuItem();
						menu2.add(zeigeTermine);
						zeigeTermine.setText("Terminübersicht anzeigen");
					}
				}
			}
			pack();
			this.setSize(900, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(viewButton1))
		{
			
		}
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(viewButton1)){
			jLabel1.setText("Wechselt das Kalenderlayout auf die Monatsansicht");
		}
		if(arg0.getSource().equals(viewButton2)){
			jLabel1.setText("Wechselt das Kalenderlayout auf die Wochenansicht");
		}
		if(arg0.getSource().equals(table)){
			jLabel1.setText(table.rowAtPoint(table.getMousePosition())+"");			
			mouseEntered = true;
		}
		if(arg0.getSource().equals(neuerTermin)){
			jLabel1.setText("Fügt einen neuen Termin hinzu. Wenn Sie vorher auf einen Tag klicken wird dieser automatisch in das Termindatum übernommen.");	
		}
		if(arg0.getSource().equals(editTermin)){
			jLabel1.setText("Bearbeiten des gerade gewählten Termins.");	
		}
		if(arg0.getSource().equals(deleteTermin)){
			jLabel1.setText("Löscht den gerade gewählten Termin.");	
		}
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(viewButton1)){
			jLabel1.setText("");
		}
		if(arg0.getSource().equals(viewButton2)){
			jLabel1.setText("");
		}
		if(arg0.getSource().equals(table)){
			jLabel1.setText("");
			table.setRowHeight(98);
			mouseEntered = false;
			
			DefaultTableColumnModel cm = new DefaultTableColumnModel();
			for (int i=0;i<7;i++)
			{
				TableColumn col = new TableColumn(i, 92);
				cm.addColumn(col);
			}
			table.setColumnModel(cm);
		}
		if(arg0.getSource().equals(neuerTermin)){
			jLabel1.setText("");
		}
		if(arg0.getSource().equals(editTermin)){
			jLabel1.setText("");	
		}
		if(arg0.getSource().equals(deleteTermin)){
			jLabel1.setText("");	
		}
	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(table)){
			jLabel1.setText(table.rowAtPoint(table.getMousePosition().getLocation())+"");	
			jLabel1.setText(table.getDefaultRenderer(String.class).toString());
			
//			 TableZoom Engine (c) 2006 by Stephe ;-) ---------------------------
			
			if(mouseEntered && zoomBox.getSelectedObjects()!= null)
			{
				int i,j,k = 0;
				j=table.rowAtPoint(table.getMousePosition()); 
				
				for (i=0;i<5;i++)
				{ 
					if(i==j) { k=186; } //Reihe j ist die Reihe, über die gerade "gehovert" wird
					else { k = 76; } //Die anderen Reihen werden verkleinert
					table.setRowHeight(i,k); //Reihe i wird mit Höhe k versehen
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
			
//			 ------------------------------------------------------------------
		}
	}

}
