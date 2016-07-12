package GUI;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import MyLib.MainButton;
import MyLib.MainLabel;

public class Main {
	public boolean isLogin;
	// JFrame
	JFrame aWindow;

	// Menu
	JMenu file;
	JMenuItem homeI, stockI, empI, cutomI, vochI, loginI,exitI;
	JMenu help;
	JMenuBar menuBar;

	// Container
	JPanel containP;

	JPanel headP;
	JButton headB;

	// Button
	JPanel butP;

	// Stack
	JButton stackB;
	StockManager stack;
	JLabel stackL;

	// Employee
	JButton emploB;
	EmployeeManager emp;
	JLabel emploL;

	// Customer
	JButton custoB;
	WarehouseManager custo;
	JLabel custoL;

	Welcome welcome;

	public Main() {
		// Screen Display
		aWindow = new JFrame("POS System Services");
		Toolkit theKit = aWindow.getToolkit();
		Dimension winSize = theKit.getScreenSize();
		aWindow.setTitle("Mini Market Management System");
		aWindow.setSize(winSize);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aWindow.setLayout(new BorderLayout());

		menuBar = new JMenuBar();
		file = new JMenu("File");
		file.setMnemonic('F');

		homeI = file.add("Menu");
		stockI = file.add("Stock ");
		empI = file.add("Employee ");
		cutomI = file.add("Warehouse ");
		vochI = file.add("Voucher ");
		loginI = file.add("Log in");
		exitI=file.add("Exit");
		homeI.setAccelerator(KeyStroke.getKeyStroke('H', CTRL_DOWN_MASK));
		stockI.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
		cutomI.setAccelerator(KeyStroke.getKeyStroke('W', CTRL_DOWN_MASK));
		empI.setAccelerator(KeyStroke.getKeyStroke('E', CTRL_DOWN_MASK));
		vochI.setAccelerator(KeyStroke.getKeyStroke('V', CTRL_DOWN_MASK));
		loginI.setAccelerator(KeyStroke.getKeyStroke('L', CTRL_DOWN_MASK));
	

		help = new JMenu("Help");
		help.setMnemonic('H');

		menuBar.add(file);
		menuBar.add(help);

		// Button/
		butP = new JPanel();
		butP.setLayout(new GridLayout(1, 3, 10, 0));
		Border border = butP.getBorder();
		Border margin = new EmptyBorder(10, 10, 140, 10);
		butP.setBorder(new CompoundBorder(border, margin));

		File sF = new File("Resources/Icon/store.jpg");
		ImageIcon sIMG = new ImageIcon(sF.getAbsolutePath());
		stackB = new MainButton("", sIMG);

		File eF = new File("Resources/Icon/employees.png");
		ImageIcon eIMG = new ImageIcon(eF.getAbsolutePath());
		emploB = new MainButton("", eIMG);

		File cF = new File("Resources/Icon/warehouse240.png");
		ImageIcon cIMG = new ImageIcon(cF.getAbsolutePath());
		custoB = new MainButton("", cIMG);

		JPanel stackP = new JPanel(new BorderLayout());
		stackP.setBackground(Color.darkGray);
		stackL = new MainLabel("Shop");
		stackP.add(stackL, BorderLayout.SOUTH);
		stackP.add(stackB, BorderLayout.CENTER);
		butP.add(stackP);

		JPanel customP = new JPanel(new BorderLayout());
		customP.setBackground(Color.darkGray);
		custoL = new MainLabel("WareHouse");
		customP.add(custoL, BorderLayout.SOUTH);
		customP.add(custoB, BorderLayout.CENTER);
		butP.add(customP);

		JPanel employP = new JPanel(new BorderLayout());
		employP.setBackground(Color.darkGray);
		emploL = new MainLabel("Employee");
		employP.add(emploL, BorderLayout.SOUTH);
		employP.add(emploB, BorderLayout.CENTER);

		butP.add(employP);
		/////////
		headP = new JPanel();
		headP.setLayout(new BorderLayout());
		headP.setPreferredSize(new Dimension(200, 200));
		JLabel headL = new JLabel("Mini-Market System", JLabel.CENTER);
		headL.setFont(new Font("Arial", Font.BOLD, 40));
		headL.setForeground(Color.darkGray);
		headP.add(headL);

		containP = new JPanel();
		containP.setLayout(new BorderLayout());

		if (welcome == null) {
			welcome = new Welcome();
		}

		containP.add(welcome, BorderLayout.CENTER);

		aWindow.setJMenuBar(menuBar);
		aWindow.add(containP, BorderLayout.CENTER);
		aWindow.setVisible(true);

		stockI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stack = new StockManager(isLogin);
				containP.removeAll();
				containP.add(stack, BorderLayout.CENTER);
				containP.repaint();
				containP.revalidate(); // *** To detect Swap Panel
			}
		});

		empI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (emp == null) {
					emp = new EmployeeManager();
				}
				containP.removeAll();
				containP.add(emp, BorderLayout.CENTER);
				containP.repaint();
				containP.revalidate(); // *** To detect Swap Panel
			}
		});
		cutomI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (custo == null) {
					custo = new WarehouseManager();
				}
				containP.removeAll();
				containP.add(custo, BorderLayout.CENTER);
				containP.repaint();
				containP.revalidate();// *** To detect Swap Panel
			}
		});
		homeI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				containP.removeAll();
				containP.add(headP, BorderLayout.NORTH);
				containP.add(butP, BorderLayout.CENTER);
				containP.repaint();
				containP.revalidate();// *** To detect Swap Panel
			}
		});
		loginI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (loginI.getText().contains("Out")) {
					loginI.setText("Log in");
					loginI.setEnabled(true);

				}
				cutomI.setEnabled(false);
				empI.setEnabled(false);
				emploB.setEnabled(false);
				custoB.setEnabled(false);
				isLogin = false;

				containP.removeAll();
				containP.add(welcome, BorderLayout.CENTER);
				containP.repaint();
				containP.revalidate();// *** To detect Swap Panel

			}
		});
		vochI.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new Voucher();
			}
		});
		exitI.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		stackB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stack = new StockManager(isLogin);
				containP.removeAll();
				containP.add(stack, BorderLayout.CENTER);
				containP.repaint();
				containP.revalidate();// *** To detect Swap Panel

			}
		});

		emploB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (emp == null) {
					emp = new EmployeeManager();
				}
				containP.removeAll();
				containP.add(emp, BorderLayout.CENTER);
				containP.repaint();
				containP.revalidate();// *** To detect Swap Panel
			}
		});
		custoB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				custo = new WarehouseManager();
				containP.removeAll();
				containP.add(custo, BorderLayout.CENTER);
				containP.repaint();
				containP.revalidate();// *** To detect Swap Panel
			}
		});

		welcome.stockB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stack = new StockManager(isLogin);
				containP.removeAll();
				containP.add(stack, BorderLayout.CENTER);
				containP.repaint();
				containP.revalidate();// *** To detect Swap Panel
			}
		});

		welcome.loginB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Login Check
				if (welcome.emailF.getText().equals("admin") && welcome.passF.getText().equals("1234")) {
					isLogin = true;
					homeI.doClick();
					loginI.setText("Log Out");
					loginI.setEnabled(true);
					cutomI.setEnabled(true);
					empI.setEnabled(true);
					emploB.setEnabled(true);
					custoB.setEnabled(true);
					welcome.resetData();
				} else {
					isLogin = false;
					JOptionPane.showMessageDialog(null, "User Name and Password do not match.", "Try Again!",
							JOptionPane.ERROR_MESSAGE);
					welcome.resetData();
				}
			}
		});

		cutomI.setEnabled(false);
		empI.setEnabled(false);
		emploB.setEnabled(false);
		custoB.setEnabled(false);
	}

	public static void main(String[] args) {
		new Main();
	}
}
