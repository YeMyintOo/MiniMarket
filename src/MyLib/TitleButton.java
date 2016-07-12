package MyLib;

import java.awt.Color;
import javax.swing.JButton;

public class TitleButton extends JButton {

	public TitleButton(String var) {
		super(var);
		setEnabled(false);
		setBackground(Color.CYAN);
		setForeground(Color.red);
		setHorizontalAlignment(JButton.LEFT);
	}
}
