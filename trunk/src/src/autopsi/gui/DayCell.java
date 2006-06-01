package autopsi.gui;
import java.awt.BorderLayout;
import java.awt.CardLayout;

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
	private JLabel title = new JLabel();
	private JList list;
	private JScrollPane jScrollPane1;

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
		
	}
	public void setTab(String title2)
	{
	title.setText(title2);
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
				javax.swing.BoxLayout.Y_AXIS);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(155, 116));
			this.setSize(92,76);
			this.setBackground(new java.awt.Color(0,0,64));
			{

				title.setLayout(null);
				this.add(title);
				title.setText("Datum2");
				title.setBackground(new java.awt.Color(0,0,64));
				title.setOpaque(true);
				title.setForeground(new java.awt.Color(255,255,255));
				{
					jScrollPane1 = new JScrollPane();
					this.add(jScrollPane1);
					{
						ListModel listModel = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
						list = new JList();
						jScrollPane1.setViewportView(list);
						list.setModel(listModel);
					}
				}
				title.setPreferredSize(new java.awt.Dimension(this.getSize().width, 14));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
