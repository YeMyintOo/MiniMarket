package MyLib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class List extends JPanel{
	public JButton getB;
	public List(String code,String name){
		setBackground(new Color(238, 224, 229));
		setLayout(new GridLayout(1, 3, 5, 5));
		setMaximumSize(new Dimension(400, 35));
		setPreferredSize(new Dimension(400, 35));
		JLabel nameL = new JLabel(name, JLabel.CENTER);
		nameL.setForeground(new Color(0, 139, 69));
		JLabel codeL = new JLabel(code, JLabel.CENTER);
		getB=new CButton("Get");
		add(codeL);
		add(nameL);
		add(getB);
	}
}
