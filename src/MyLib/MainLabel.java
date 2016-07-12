package MyLib;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class MainLabel extends JLabel {
	
	public MainLabel(String name) {
		super(name,JLabel.CENTER);
		setFont(new Font("Times New Roman",Font.PLAIN,35));
		setForeground(Color.white);
		
	}
}
