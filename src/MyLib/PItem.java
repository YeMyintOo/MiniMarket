package MyLib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PItem extends JPanel {

	public double totalD;

	public PItem(String code, String name, String date, int qua, double price) {
		setBackground(new Color(238, 224, 229));
		setLayout(new GridLayout(1, 5, 5, 5));
		setMaximumSize(new Dimension(650, 35));
		setPreferredSize(new Dimension(670, 35));
		totalD = qua * price;
		JLabel dateL = new JLabel(date, JLabel.CENTER);
		dateL.setForeground(new Color(0, 139, 69));
		JLabel codeL = new JLabel(code, JLabel.CENTER);
		codeL.setForeground(new Color(238, 44, 44));
		JLabel nameL = new JLabel(name, JLabel.CENTER);
		JLabel quaL = new JLabel("" + qua, JLabel.CENTER);
		JLabel priceL = new JLabel("" + price, JLabel.CENTER);
		JLabel totalL = new JLabel("" + totalD, JLabel.CENTER);
		totalL.setForeground(new Color(142, 56, 142));

		//add(dateL);
		add(codeL);
		add(nameL);
		add(quaL);
		add(priceL);
		add(totalL);

	}
}
