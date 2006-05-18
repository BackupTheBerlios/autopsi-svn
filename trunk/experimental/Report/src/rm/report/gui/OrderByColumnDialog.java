package rm.report.gui;

import javax.swing.*;
import java.util.ArrayList;
import rm.report.util.*;
import java.awt.*;
import java.awt.event.*;

import rm.report.util.TableColumn;

public class OrderByColumnDialog extends JDialog implements ActionListener {
	
	private ArrayList<TableColumn> cols = null;
	public static TableColumn tc = null;
	public static boolean ascending = true;
	private JComboBox box = new JComboBox();
	
	public OrderByColumnDialog(Frame owner, ArrayList<TableColumn> cols){
		super(owner, true);
		this.cols = cols;
		this.setSize(300,200);
		
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
		for (int i=0;i<cols.size();i++){
			box.addItem(cols.get(i).getName());
		}
		pane.add(box);
		ButtonGroup p = new ButtonGroup();
		JRadioButton rb = new JRadioButton("aufsteigend");
		p.add(rb);
		pane.add(rb);
		rb = new JRadioButton("absteigend");
		p.add(rb);
		pane.add(rb);
		tc = cols.get(0);
		box.addActionListener(this);
		JButton button = new JButton("Ok");
		button.addActionListener(this);
		pane.add(button);
		this.add(pane);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		tc = cols.get(box.getSelectedIndex());
		
		String cmd = e.getActionCommand();
		if (cmd.equals("Ok")){
			this.setVisible(false);
			this.dispose();
		}
		
		if (cmd.equals("aufsteigend"))
			ascending = true;
		if (cmd.equals("absteigend"))
			ascending = false;
		
	}

}
