package Warehouse;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Database.DriverClass;
import MyLib.Header;
import MyLib.LButton;

public class Import extends JPanel {
	JPanel headP;

	JPanel infoP;

	JPanel leftP;
	JLabel codeL;
	JTextField codeF;
	JLabel categoryL;
	String[] categories;
	JComboBox categoryF;

	JLabel bPriceL;
	JTextField bPriceF;
	JLabel aDataL;
	JTextField aDataF;
	JLabel eDateL;
	JTextField eDateF;
	JLabel quantiL;
	JTextField quantiF;

	JPanel rightP;
	JLabel nameL;
	JTextField nameF;
	JLabel sPriceL;
	JTextField sPriceF;
	JLabel detailL;
	JTextArea detailA;

	JPanel btnP;
	JButton insertB;
	JButton resetB;

	DriverClass db;
	
	public Import() {
		setLayout(new BorderLayout());
		headP = new Header("Add Inserts");

		infoP = new JPanel(new BorderLayout());

		leftP = new JPanel(new GridLayout(6, 2, 5, 5));
		leftP.setPreferredSize(new Dimension(550, getHeight()));
		codeL = new JLabel("Item Code");
		codeF = new JTextField();
		categoryL = new JLabel("Category");

		getCategoriesList();
		categoryF = new JComboBox(categories);
		//categoryF.setEditable(true);

		bPriceL = new JLabel("Retail Price");
		bPriceF = new JTextField();
		aDataL = new JLabel("Arrival Date");
		aDataF = new JTextField();
		eDateL = new JLabel("Expire Date");
		eDateF = new JTextField();
		quantiL = new JLabel("Initial Quantility");
		quantiF = new JTextField();
		leftP.add(codeL);
		leftP.add(codeF);
		leftP.add(categoryL);
		leftP.add(categoryF);
		leftP.add(bPriceL);
		leftP.add(bPriceF);
		leftP.add(aDataL);
		leftP.add(aDataF);
		leftP.add(eDateL);
		leftP.add(eDateF);
		leftP.add(quantiL);
		leftP.add(quantiF);

		rightP = new JPanel(new BorderLayout());
		JPanel subRP = new JPanel(new GridLayout(2, 2, 5, 5));
		subRP.setBorder(new EmptyBorder(0, 20, 0, 0));
		nameL = new JLabel("Item Name");
		nameF = new JTextField(25);
		sPriceL = new JLabel("Sell Price");
		sPriceF = new JTextField();
		subRP.add(nameL);
		subRP.add(nameF);
		subRP.add(sPriceL);
		subRP.add(sPriceF);
		JPanel detailP = new JPanel(new BorderLayout());
		detailA = new JTextArea();
		detailA.setToolTipText("Detail");
		detailA.setRows(10);
		detailA.setBorder(new EmptyBorder(20, 20, 20, 20));
		detailP.add(detailA, BorderLayout.CENTER);
		detailP.setBorder(new EmptyBorder(10, 20, 8, 0));

		rightP.add(subRP, BorderLayout.CENTER);
		rightP.add(detailP, BorderLayout.SOUTH);

		btnP = new JPanel(new BorderLayout());
		JPanel subP = new JPanel();
		insertB = new LButton("Insert");
		resetB = new LButton("Reset");
		subP.add(insertB);
		subP.add(resetB);
		btnP.add(subP, BorderLayout.EAST);

		infoP.add(leftP, BorderLayout.WEST);
		infoP.add(rightP, BorderLayout.CENTER);
		infoP.setBorder(new EmptyBorder(50, 10, 110, 10));

		add(headP, BorderLayout.NORTH);
		add(infoP, BorderLayout.CENTER);
		add(btnP, BorderLayout.SOUTH);

		resetB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				codeF.setText("");
				categoryF.setSelectedIndex(0);
				bPriceF.setText("");
				aDataF.setText("");
				eDateF.setText("");
				quantiF.setText("");
				nameF.setText("");
				sPriceF.setText("");
				detailA.setText("");
			}
		});

		insertB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				db = new DriverClass();
				String sql = "insert into item values('"
						+ codeF.getText()
						+ "','"
						+ nameF.getText()
						+ "','"
						+ getCategoriesID(categoryF.getSelectedItem()
								.toString()) + "','" + detailA.getText()
						+ "','" + aDataF.getText() + "','" + eDateF.getText()
						+ "','" + Integer.parseInt(quantiF.getText()) + "')";
				String sql2 = "insert into warehouse values('"
						+ codeF.getText() + "','"
						+ Integer.parseInt(quantiF.getText()) + "','"
						+ Double.parseDouble(bPriceF.getText()) + "'); ";
				try {
					Connection c = db.getConnection();
					Statement stmt = c.createStatement();
					int i = stmt.executeUpdate(sql);
					int k = stmt.executeUpdate(sql2);
					if(i==1 && k==1){
						System.out.println("Success");
						JOptionPane.showMessageDialog(null, "Data is successful","Success", JOptionPane.PLAIN_MESSAGE);
						//codeF.setText("");
						//categoryF.setSelectedIndex(0);
						bPriceF.setText("");
						//aDataF.setText("");
						//eDateF.setText("");
						quantiF.setText("");
						nameF.setText("");
						//sPriceF.setText("");
						detailA.setText("");
					}else{
						System.out.println("Fail");
						JOptionPane.showMessageDialog(null, "Data is Fail","Fail", JOptionPane.ERROR_MESSAGE);
						codeF.setText("");
						categoryF.setSelectedIndex(0);
						bPriceF.setText("");
						aDataF.setText("");
						eDateF.setText("");
						quantiF.setText("");
						nameF.setText("");
						sPriceF.setText("");
						detailA.setText("");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		categoryF.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(categoryF.getSelectedItem().toString().equals("---")){
					categoryF.setEditable(true);
				}else{
					categoryF.setEditable(false);
				}
			}
		});
	}

	public void getCategoriesList() {
		try {
			db = new DriverClass();
			String sql = "SELECT * FROM category";

			int count = 0;
			try {
				Connection c = db.getConnection();
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					count++;
				}
				categories = new String[++count];
				categories[categories.length-1]="---";

			} catch (Exception e2) {
				e2.printStackTrace();
			}

			int index = 0;
			try {
				Connection c = db.getConnection();
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					categories[index] = rs.getString("type");
					index++;
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getCategoriesID(String type) {
		int id = 0;
		String sql = "SELECT * FROM category where type='" + type + "'";
		try {
			Connection c = db.getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt("cid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}
