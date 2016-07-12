package MyLib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SItem extends JPanel {

	public int totalD;

	public SItem(String code, String name, String date, int total, int shop) {
		setBackground(new Color(238, 224, 229));
		setLayout(new GridLayout(1, 6, 5, 5));
		setMaximumSize(new Dimension(650, 35));
		setPreferredSize(new Dimension(650, 35));
		totalD = total - shop;
		JLabel dateL = new JLabel(date, JLabel.CENTER);
		dateL.setForeground(new Color(0, 139, 69));
		JLabel codeL = new JLabel(code, JLabel.CENTER);
		codeL.setForeground(new Color(238, 44, 44));
		JLabel nameL = new JLabel(name, JLabel.CENTER);
		JLabel totalL = new JLabel("" + total, JLabel.CENTER);
		totalL.setForeground(new Color(69, 139, 0));
		JLabel shopL = new JLabel("- " + shop, JLabel.CENTER);
		shopL.setForeground(new Color(238, 44, 44));
		JLabel resutL = new JLabel("" + totalD, JLabel.CENTER);
		resutL.setForeground(new Color(0, 134, 139));

		add(dateL);
		add(codeL);
		add(nameL);
		add(totalL);
		add(shopL);
		add(resutL);

	}
}
