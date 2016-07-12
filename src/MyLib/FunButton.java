package MyLib;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class FunButton extends JButton {
	private Color color;

	public FunButton(String var) {
		super(var);
		color = new Color(131,111,255);
		setBackground(color);
		setForeground(Color.white);
		setHorizontalAlignment(JButton.LEFT);
		setHorizontalTextPosition(SwingConstants.RIGHT);
		setFont(new Font("Arial",Font.BOLD,15));
		setFocusable(false);
	}

	public FunButton(String var, ImageIcon icon) {
		super(var, icon);
		color = new Color(131,111,255);
		setBackground(color);
		setForeground(Color.white);
		setHorizontalAlignment(JButton.LEFT);
		setHorizontalTextPosition(SwingConstants.RIGHT);
		setFont(new Font("Arial",Font.BOLD,15));
		setFocusable(false);
	}

}
