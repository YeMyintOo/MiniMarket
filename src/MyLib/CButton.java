package MyLib;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class CButton extends JButton {
	public Color color;
	public CButton(String name) {
		super(name);
		color = new Color(210,105,30);
		setBackground(Color.white);
		setForeground(color);
		setHorizontalAlignment(JButton.CENTER);
		setFont(new Font("Arial",Font.BOLD,15));
		setFocusable(false);
	}

}
