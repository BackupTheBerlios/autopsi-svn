package autopsi.gui.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class JSAdressDialog extends JDialog implements ActionListener {

	protected JButton okButton;
	protected JTextField adressField;
	
	public JSAdressDialog(JFrame owner){
		super(owner, true);
		this.setSize(200,100);
		this.setLayout(new BorderLayout());
		this.okButton = new JButton("connect");
		this.okButton.addActionListener(this);
		this.adressField = new JTextField("jini://localhost");
		this.add(okButton, BorderLayout.SOUTH);
		this.add(adressField, BorderLayout.CENTER);
		this.setVisible(true);
	}

	
	public String getAdress(){
		return this.adressField.getText();
	}
	
	public void setAdress(String newAdress){
		this.adressField.setText(newAdress);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(this.okButton)){
			this.dispose();
		}
		
	}
	
	
	
}
