package MyLib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RItem extends JPanel {

	public RItem(String code, String name, int stock,double bprice) {
		setBackground(new Color(238, 224, 229));
		setLayout(new GridLayout(1, 6, 5, 5));
		setMaximumSize(new Dimension(650, 35));
		setPreferredSize(new Dimension(650, 35));

		JLabel codeL = new JLabel(code, JLabel.CENTER);
		codeL.setForeground(new Color(238, 44, 44));
		JLabel nameL = new JLabel(name, JLabel.CENTER);
		JLabel bpriceL = new JLabel("" + bprice, JLabel.CENTER);
		bpriceL.setForeground(new Color(238, 44, 44));

		add(codeL);
		add(nameL);
		add(bpriceL);

	}
}
