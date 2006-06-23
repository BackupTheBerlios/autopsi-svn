package autopsi.gui.frame;

import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class JSAdressDialog extends JDialog implements ActionListener {

	protected JButton okButton;
	protected JButton cancelButton;
	protected JTextField adressField;
	protected boolean okClicked;
	
	public JSAdressDialog(JFrame owner){
		super(owner, true);
		this.setSize(200,80);
		this.setTitle("JavaSpace-Server");
		this.setLayout(new BorderLayout());
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = env.getDefaultScreenDevice();
		DisplayMode dm = gd.getDisplayMode();
		this.setLocation((dm.getWidth()-this.getWidth())/2,(dm.getHeight()-this.getHeight())/2);
		this.okButton = new JButton("connect");
		this.okButton.addActionListener(this);
		this.cancelButton = new JButton("abbrechen");
		this.okButton.addActionListener(this);
		this.adressField = new JTextField("jini://localhost");
		this.add(okButton, BorderLayout.SOUTH);
		this.add(cancelButton, BorderLayout.SOUTH);
		this.add(adressField, BorderLayout.CENTER);
		this.setVisible(true);
	}

	
	public String getAdress(){
		return this.adressField.getText();
	}
	
	public void setAdress(String newAdress){
		this.adressField.setText(newAdress);
	}
	
	public boolean getOkClicked(){
		return this.okClicked;
	}
	
	public void setAdress(boolean okClicked){
		this.okClicked = okClicked;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(this.okButton)){
			this.okClicked = true;
			this.dispose();
		}
		if (arg0.getSource().equals(this.cancelButton)){
			this.okClicked = false;
			this.dispose();
		}
		
	}
	
	
	
}
