package MyLib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Item extends JPanel {
	public JButton delB;
	public double totalD;
	public Item(String name,int qua,double price) {
		setLayout(new GridLayout(1,5,5,5));
		setMaximumSize(new Dimension(900,25));
		totalD=qua*price;
		JLabel nameL=new JLabel(name);
		JLabel quaL=new JLabel(""+qua);
		JLabel priceL=new JLabel(""+price);
		JLabel totalL=new JLabel(""+totalD);
		delB=new CButton("x");
		delB.setForeground(Color.red);
		
		add(nameL);
		add(quaL);
		add(priceL);
		add(totalL);
		add(delB);
		
	}
}
