package autopsi.gui;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

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
public class DayFrame extends javax.swing.JDialog {
	private JScrollPane jScrollPane1;
	private JList list;
	private JButton deleteTermin;
	private JButton editTermin;
	private JButton addTermin;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		DayFrame inst = new DayFrame();
		inst.setVisible(true);
		
	}
	
	public DayFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
		
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setSize(169, 240);
			this.setResizable(false);
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(223,223,223));
			this.setAlwaysOnTop(true);
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(0, 0, 168, 175);
				{
					ListModel listModel = new DefaultComboBoxModel(
						new String[] { "9.00 VO Mathe I","10.00 VU GDI","12.00 Essen mit Gerd","13.00 AU Eprog","15.00 VO IUG I","19.00 Pfadfinder"});
					list = new JList();
					jScrollPane1.setViewportView(list);
					jScrollPane1.setAlignmentX(0.0f);
					jScrollPane1.setAlignmentY(0.0f);
					jScrollPane1.setOpaque(false);
					jScrollPane1.getVerticalScrollBar().setBounds(143, 11, 16, 142);
					jScrollPane1.getHorizontalScrollBar().setBounds(9, 153, 134, 16);
					jScrollPane1.setLayout(null);
					list.setModel(listModel);
					list.setPreferredSize(jScrollPane1.getSize());
					list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					list.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
					list.setSelectionBackground(new java.awt.Color(0,0,128));
					list.setFont(new java.awt.Font("Tahoma",0,10));
					list.setAlignmentX(0.0f);
					list.setAlignmentY(0.0f);
					list.setBounds(0, 0, 161, 175);
				}
			}
			{
				addTermin = new JButton();
				getContentPane().add(addTermin);
				addTermin.setBounds(7, 182, 42, 28);
				addTermin.setIcon(new ImageIcon("src/images/neuerTermin.GIF"));
				addTermin.setOpaque(false);
			}
			{
				editTermin = new JButton();
				getContentPane().add(editTermin);
				editTermin.setIcon(new ImageIcon("src/images/editTermin.GIF"));
				editTermin.setBounds(58, 182, 42, 28);
				editTermin.setOpaque(false);
			}
			{
				deleteTermin = new JButton();
				getContentPane().add(deleteTermin);
				deleteTermin.setIcon(new ImageIcon("src/images/deleteTermin.GIF"));
				deleteTermin.setBounds(112, 182, 42, 28);
				deleteTermin.setOpaque(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
