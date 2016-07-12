package MyLib;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MainButton extends JButton {
	
	public MainButton(String var,ImageIcon icon) {
		super(var,icon);
		setBackground(Color.white);
		setFocusable(false);
		
		addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				setBackground(Color.white);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				setBackground(Color.white);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		});
	}
}
