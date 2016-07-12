package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import MyLib.FunButton;
import MyLib.GButton;
import MyLib.MainButton;

public class Welcome extends JPanel {
	JPanel headP;
	
	JPanel adminP;
	JLabel emailL;
	public JTextField emailF;
	JLabel passL;
	public JPasswordField passF;
	public JButton loginB;
	public JButton resetB;
	
	JPanel cashP;
	public JButton cashB;
	public JButton stockB;

	public Welcome() {
		setLayout(new BorderLayout());
		//setBackground(new Color(140,154,156));
		Border border = this.getBorder();
		Border margin = new EmptyBorder(50, 200, 160, 200);
		this.setBorder(new CompoundBorder(border, margin));
		
		
		headP=new JPanel();
		headP.setLayout(new BorderLayout());
		headP.setBackground(new Color(172,32,32));
		headP.setBorder(new EmptyBorder(0,0,80,0));
		JLabel lblHead=new JLabel("ini-Market Management System");
		lblHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblHead.setFont(new Font("Times New Roman",Font.BOLD,35));
		lblHead.setForeground(new Color(255, 250, 205));
		File mF = new File("Resources/Icon/1a.png");
		ImageIcon mIMG = new ImageIcon(mF.getAbsolutePath());
		lblHead.setIcon(mIMG);

		headP.add(lblHead,BorderLayout.CENTER); 
		
		

		adminP = new JPanel(new GridLayout(4, 2));
		adminP.setBorder(new LineBorder(Color.darkGray));
		emailL = new JLabel("        Email");
		emailF = new JTextField();
		passL = new JLabel("        Password");
		passF = new JPasswordField();
		loginB = new GButton("Login");
		resetB = new GButton("Reset");
		loginB.setFont(new Font("Times New Roman",Font.BOLD,20));
		loginB.setFont(new Font("Times New Roman",Font.BOLD,20));
		JPanel adminHeadP=new JPanel(new BorderLayout());
		adminHeadP.setBackground(Color.darkGray);
		JPanel adminHeadP2=new JPanel(new BorderLayout());
		adminHeadP2.setBackground(Color.darkGray);
		JLabel adminL=new JLabel("    Admin Login");
		adminL.setFont(new Font("Times New Roman",Font.BOLD,20));
		adminL.setForeground(new Color(255, 250, 205));
		adminHeadP.add(adminL,BorderLayout.CENTER);
		
		adminP.add(adminHeadP);
		adminP.add(adminHeadP2);
		adminP.add(emailL);
		adminP.add(emailF);
		adminP.add(passL);
		adminP.add(passF);
		JPanel btnP=new JPanel(new GridLayout(1,2));
		btnP.add(loginB);
		btnP.add(resetB);
		adminP.add(new JPanel());
		adminP.add(btnP);
		
		
		cashP=new JPanel(new GridLayout(1,2));
		cashB=new FunButton("Create Voucher");
		File vF = new File("Resources/Icon/1h.png");
		ImageIcon vIMG = new ImageIcon(vF.getAbsolutePath());
		cashB.setIcon(vIMG);
		cashB.setForeground(new Color(255, 250, 205));
		cashB.setBackground(new Color(172,32,32));
		cashB.setFont(new Font("Times New Roman", Font.BOLD, 20));
		cashB.setFocusable(false);
		cashB.setHorizontalAlignment(SwingConstants.CENTER);
		cashB.setPreferredSize(new Dimension(200,70));
		
		stockB=new FunButton("Add Stock");
		File sF = new File("Resources/Icon/1i.png");
		ImageIcon sIMG = new ImageIcon(sF.getAbsolutePath());
		stockB.setIcon(sIMG);
		stockB.setForeground(new Color(255, 250, 205));
		stockB.setBackground(new Color(172,32,32));
		stockB.setFont(new Font("Times New Roman", Font.BOLD, 20));
		stockB.setFocusable(false);
		stockB.setHorizontalAlignment(SwingConstants.CENTER);
		stockB.setPreferredSize(new Dimension(200,70));
		
		cashP.add(cashB);
		cashP.add(stockB);
		
		JPanel sub=new JPanel(new BorderLayout());
		sub.add(adminP,BorderLayout.CENTER);
		sub.add(cashP,BorderLayout.SOUTH);
		
		JPanel contain=new JPanel(new BorderLayout());
		contain.add(headP,BorderLayout.NORTH);
		contain.add(sub,BorderLayout.CENTER);
		
		add(contain,BorderLayout.CENTER);
		
		resetB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				resetData();
			}
		});
	}
	public void resetData(){
		emailF.setText("");
		passF.setText("");
	}
}
