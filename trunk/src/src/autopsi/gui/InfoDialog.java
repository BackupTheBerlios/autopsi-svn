package autopsi.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
public class InfoDialog extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JButton okbutton;
	private JLabel textfield;
	String title = "";
	String text = "";

	/**
	* Auto-generated main method to display this JDialog
	*/
	
	public InfoDialog(String title,String text) {
		this.title = title;
		this.text = text;
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setResizable(false);
				getContentPane().setLayout(null);
				{
					okbutton = new JButton();
					getContentPane().add(okbutton);
					okbutton.setText("OK");
					okbutton.setBounds(105, 77, 63, 21);
					okbutton.addActionListener(new ActionListener(){

						public void actionPerformed(ActionEvent arg0) {
							dispose();
						}
						
					});
				}
				{
					this.setTitle(title);
					textfield = new JLabel();
					getContentPane().add(textfield);
					textfield.setBounds(7, 7, 266, 63);
					textfield.setText(text);
				}
			}
			this.setSize(288, 135);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
