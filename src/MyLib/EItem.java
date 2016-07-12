package MyLib;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EItem extends JPanel {
	public EItem(String name, String rank, double salary) {
		setLayout(new GridLayout(1, 5, 5, 5));
		setMaximumSize(new Dimension(900, 25));

		JLabel nameL = new JLabel(name);
		JLabel salaryL = new JLabel("" + salary);
		JLabel rankL = new JLabel("" + rank);

		add(nameL);
		add(salaryL);
		add(rankL);
	}

}
