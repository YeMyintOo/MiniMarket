package MyLib;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Calculator extends JPanel {

	public JTextArea result;
	public JButton btn_1;
	public JButton btn_2;
	public JButton btn_3;
	public JButton btn_4;
	public JButton btn_5;
	public JButton btn_6;
	public JButton btn_7;
	public JButton btn_8;
	public JButton btn_9;
	public JButton btn_0;
	public JButton btn_plus;
	public JButton btn_minus;
	public JButton btn_multi;
	public JButton btn_equ;
	public JButton btn_del;
	public JButton btn_ac;
	public JButton btn_div;
	public JButton btn_dot;
	public JButton btn_neg;

	public JPanel btnP;

	public Calculator() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(290,getHeight()));

		btn_1 = new CButton("1");
		btn_2 = new CButton("2");
		btn_3 = new CButton("3");
		btn_4 = new CButton("4");
		btn_5 = new CButton("5");
		btn_6 = new CButton("6");
		btn_7 = new CButton("7");
		btn_8 = new CButton("8");
		btn_9 = new CButton("9");
		btn_0 = new CButton("0");
		btn_plus = new CButton("+");
		btn_minus = new CButton("-");
		btn_multi = new CButton("*");
		btn_equ = new CButton("=");
		btn_del = new CButton("DEL");
		btn_ac = new CButton("AC");
		btn_div = new CButton("%");
		btn_dot = new CButton(".");
		btn_neg = new CButton("+/-");

		btnP = new JPanel(new GridLayout(5,4));
		btnP.add(btn_ac);
		btnP.add(btn_del);
		btnP.add(btn_neg);
		btnP.add(btn_div);
		btnP.add(btn_7);
		btnP.add(btn_8);
		btnP.add(btn_9);
		btnP.add(btn_multi);
		btnP.add(btn_4);
		btnP.add(btn_5);
		btnP.add(btn_6);
		btnP.add(btn_minus);
		btnP.add(btn_1);
		btnP.add(btn_2);
		btnP.add(btn_3);
		btnP.add(btn_plus);
		btnP.add(btn_0);
		btnP.add(new JPanel());
		btnP.add(btn_dot);
		btnP.add(btn_equ);
		
		JPanel textP=new JPanel(new BorderLayout());
		result=new JTextArea();
		result.setRows(1);
		result.setBorder(new EmptyBorder(10,10,10,10));
		result.setFont(new Font("Arial",Font.BOLD,50));
		textP.add(result,BorderLayout.CENTER);
		
		add(textP,BorderLayout.NORTH);
		add(btnP,BorderLayout.CENTER);
	}
	
}
