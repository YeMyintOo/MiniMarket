package MyLib;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VHeader extends JPanel{
	
	
	public JLabel codeL;
	public JTextField codeF;	
	public JTextField countF;
	public JButton addB;
	public JButton showB;
	public ShowItemList showItem;
	
	public VHeader(){
		setLayout(new GridLayout(1,1));
		setBorder(new EmptyBorder(10,10,10,0));
		
		
		
		JPanel codeCP=new JPanel(new BorderLayout());
		JPanel codeP=new JPanel();
		codeL=new JLabel("Code");
		codeL.setFont(new Font("Arial",Font.BOLD,16));
		codeF=new JTextField(15);
		showB=new CButton("...");
		countF=new JTextField(5);
		addB=new JButton("Add");
		addB.setFont(new Font("Arial",Font.PLAIN,14));
		addB.setBackground(new Color(0,128,128));
		addB.setForeground(Color.white);
		codeF.setBorder(new EmptyBorder(5,10,5,10));
		countF.setBorder(new EmptyBorder(5,10,5,10));
		codeP.add(codeL);
		codeP.add(codeF);
		codeP.add(showB);
		codeP.add(countF);
		codeP.add(addB);
		codeCP.add(codeP,BorderLayout.WEST);
			
		add(codeCP);
	}
}	
