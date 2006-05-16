package autopsi.gui;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JLabel;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
public class mainFrame extends javax.swing.JFrame implements java.awt.event.MouseListener{

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
	private JLabel viewLabel;
	private JButton viewButton2;
	private JButton viewButton1;
	private JTable table;
	private JTabbedPane tab2;
	private JTabbedPane tab1;
	private JTabbedPane tab;
	private JLabel jLabel1;
	private JPanel infopanel;
	private JMenuItem zeigeTermine;
	private JMenuItem neuerZeitpunkt;
	private JToolBar toolbar;
	private JMenu menu2;
	private JMenuBar mainMenu;

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
				toolbar.setBounds(0, 0, 896, 28);
				toolbar.setBackground(new java.awt.Color(229,229,229));
				{
					viewLabel = new JLabel();
					toolbar.add(viewLabel);
					viewLabel.setText("Kalenderansicht: ");
					viewLabel.setPreferredSize(new java.awt.Dimension(88, 14));
				}
				{
					viewButton1 = new JButton();
					viewButton1.addMouseListener(this);
					toolbar.add(viewButton1);
					viewButton1.setText("Monat");
					viewButton1.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					viewButton1
						.setPreferredSize(new java.awt.Dimension(51, 26));
				}
				{
					viewButton2 = new JButton();
					toolbar.add(viewButton2);
					viewButton2.setText("Woche");
					viewButton2.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
					viewButton2.setPreferredSize(new java.awt.Dimension(76, 19));
				}
			}
			{
				TableModel tableModel = new DefaultTableModel(new String[][] {
						{ "One", "Two" }, { "Three", "Four" } }, new String[] {
						"Column 1", "Column 2" });
				table = new JTable();
				getContentPane().add(table);
				table.setModel(tableModel);
				table.setBounds(203, 35, 686, 476);
				table.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				table.setShowHorizontalLines(false);
				table.setRowSelectionAllowed(false);
				table.setSelectionForeground(new java.awt.Color(255,255,255));
				table.setSelectionBackground(new java.awt.Color(0,0,160));
			}
			{
				infopanel = new JPanel();
				getContentPane().add(infopanel);
				infopanel.setBounds(7, 35, 189, 224);
				infopanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				infopanel.setBackground(new java.awt.Color(255,255,255));
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("-zeigt Informationen an-");
				jLabel1.setBounds(7, 518, 882, 21);
				jLabel1.setBackground(new java.awt.Color(255,255,255));
				jLabel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				jLabel1.setOpaque(true);
			}
			{
				tab = new JTabbedPane();
				getContentPane().add(tab);
				tab.setBounds(7, 266, 189, 245);
				{
					tab1 = new JTabbedPane();
					tab.addTab("Objekte", null, tab1, null);
				}
				{
					tab2 = new JTabbedPane();
					tab.addTab("alle Termine", null, tab2, null);
					tab2.setBackground(new java.awt.Color(241,243,248));
				}
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
			jLabel1.setText("Dieser Button ändert die Ansicht");
		}
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(viewButton1)){
			jLabel1.setText("-Infos-");
		}
	}

}
