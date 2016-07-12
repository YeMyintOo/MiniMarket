package MyLib;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Header extends JPanel {
	public JLabel headL;
	public Header(String title) {
		headL = new JLabel(title);
		headL.setForeground(Color.white);
		headL.setBorder(new EmptyBorder(100, 100, 100, 100));
		headL.setFont(new Font("Times New Roman", Font.BOLD, 40));
		setLayout(new BorderLayout());
		add(headL, BorderLayout.CENTER);
		setPreferredSize(new Dimension(getWidth(), 150));
		setBackground(new Color(0,128,128));
		
	}
}
