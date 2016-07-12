package MyLib;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class LButton extends JButton{
	private Color color;
	public LButton(String name) {
		super(name);
		color = new Color(0,128,128);
		setBackground(color);
		setForeground(Color.white);
		setHorizontalAlignment(JButton.CENTER);
		setHorizontalTextPosition(SwingConstants.RIGHT);
		setFont(new Font("Arial",Font.BOLD,15));
		setFocusable(false);
	}
}
