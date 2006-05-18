package rm.report.gui;

import javax.swing.*;
import java.util.ArrayList;
import rm.report.util.*;
import java.awt.*;
import java.awt.event.*;

public class AddColumnDialog extends JDialog implements ActionListener{
	
	private ArrayList<TableColumn> cols = null;
	public static TableColumn tc = null;
	private JComboBox box = new JComboBox();
	
	public AddColumnDialog(Frame owner, ArrayList<TableColumn> cols){
		super(owner, true);
		this.cols = cols;
		this.setSize(300,200);
		
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
		for (int i=0;i<cols.size();i++){
			box.addItem(cols.get(i).getName());
		}
		tc = cols.get(0);
		box.addActionListener(this);
		pane.add(box);
		JButton button = new JButton("Ok");
		button.addActionListener(this);
		pane.add(button);
		this.add(pane);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		tc = cols.get(box.getSelectedIndex());
		
		if (e.getActionCommand().equals("Ok")){
			this.setVisible(false);
			this.dispose();
		}
	}
}
