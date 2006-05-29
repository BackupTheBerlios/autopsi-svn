package autopsi.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import javax.swing.WindowConstants;
import javax.swing.*;
import java.awt.Color;

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
public class DayCell extends javax.swing.JPanel {
	private boolean scroll;
	private String date;
	private JPanel jPanel1;
	private JTabbedPane jTabbedPane1;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new DayCell(true));
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public DayCell(boolean scroll) {
		super();
		this.scroll = scroll;
		initGUI();
		
	}
	public void setBackColor()
	{
		jPanel1.setBackground(Color.LIGHT_GRAY);
	}
	public void setTab(String title)
	{
		jTabbedPane1.setTitleAt(0,title);
	}
	
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(103, 97));
			}
			{
				this.setPreferredSize(new java.awt.Dimension(103, 97));
			}
			BoxLayout thisLayout = new BoxLayout(
				this,
				javax.swing.BoxLayout.X_AXIS);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(155, 116));
			this.setSize(92,76);
			this.setBackground(new java.awt.Color(255,255,255));
			{
				jTabbedPane1 = new JTabbedPane();
				this.add(jTabbedPane1);
				jTabbedPane1.setFont(new java.awt.Font("Tahoma",0,9));
	
				jPanel1 = new JPanel();
				jPanel1.setBackground(Color.white);
				jTabbedPane1.addTab("bla", null, jPanel1, null);			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
