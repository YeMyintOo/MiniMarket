package Stock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import MyLib.CButton;
import MyLib.Header;
import MyLib.LButton;
import MyLib.MyKeyListener;
import MyLib.ShowItemList;

public class Edit extends JPanel {
	JPanel headP;

	JPanel infoP;

	JPanel leftP;
	JLabel codeL;
	JTextField codeF;
	JButton codeB;
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
	JButton updateB;
	JButton deleteB;
	JButton resetB;

	DriverClass db;
	ShowItemList shwoList;
	
	public Edit() {
		setLayout(new BorderLayout());
		headP = new Header("Edit Items");

		infoP = new JPanel(new BorderLayout());

		leftP = new JPanel(new GridLayout(6, 2, 5, 5));
		leftP.setPreferredSize(new Dimension(550, getHeight()));
		codeL = new JLabel("Item Code");
		JPanel codeP = new JPanel(new BorderLayout());
		codeF = new JTextField();
		codeB = new CButton("...");
		codeP.add(codeF, BorderLayout.CENTER);
		codeP.add(codeB, BorderLayout.EAST);

		categoryL = new JLabel("Category");

		getCategoriesList();
		categoryF = new JComboBox(categories);
		categoryF.setEditable(true);

		bPriceL = new JLabel("Retail Price");
		bPriceF = new JTextField();
		aDataL = new JLabel("Arrival Date");
		aDataF = new JTextField();
		eDateL = new JLabel("Expire Date");
		eDateF = new JTextField();
		quantiL = new JLabel("Initial Quantility");
		quantiF = new JTextField();
		leftP.add(codeL);
		leftP.add(codeP);
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
		updateB = new LButton("Update");
		deleteB = new LButton("Delete");
		resetB = new LButton("Reset");
		subP.add(updateB);
		subP.add(deleteB);
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

		updateB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sql = "UPDATE item SET name='" + nameF.getText().trim() + "',cid='"
						+ (categoryF.getSelectedIndex() + 1) + "',detail='" + detailA.getText() + "',arrivalDate='"
						+ aDataF.getText().trim() + "',expireDate='" + eDateF.getText().trim() + "' WHERE id='"
						+ codeF.getText().trim() + "'";
				String wsql = "UPDATE shop SET countNumber='" + quantiF.getText().trim() + "',sellPrice='"
						+ bPriceF.getText().trim() + "' WHERE id='" + codeF.getText().trim() + "' ";
				try {
					if (db == null) {
						db = new DriverClass();
					}
					Connection c = db.getConnection();
					Statement stmt = c.createStatement();
					int i = stmt.executeUpdate(sql);
					int k = stmt.executeUpdate(wsql);
					if (i == 1 && k == 1) {
						JOptionPane.showMessageDialog(null, "Success Update Operation", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Fail Update Operation", "Fail", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}

				resetB.doClick();
			}
		});

		deleteB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					db = new DriverClass();
					String sql = "DELETE FROM item WHERE id='" + codeF.getText().trim() + "'";
					String wsql = "DELETE FROM shop WHERE id='" + codeF.getText().trim() + "'";
					Connection c = db.getConnection();
					Statement stmt = c.createStatement();
					int i = stmt.executeUpdate(sql);
					int k=stmt.executeUpdate(wsql);
					if (i == 1 && k==1) {
						JOptionPane.showMessageDialog(null, "Success Delete Operation", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Fail Delete Operation", "Fail", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				resetB.doClick();
			}
		});

		codeF.addKeyListener(new MyKeyListener() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					loadData();
				}
			}
		});
		
		codeB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				shwoList=new ShowItemList();
				shwoList.closeB.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						codeF.setText(shwoList.code);
					}
				});
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
				categories = new String[count];

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

	public void loadData() {
		try {
			String sql = "SELECT * FROM item i,shop w where i.id='" + codeF.getText().trim() + "'";
			try {
				Connection c = db.getConnection();
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					categoryF.setSelectedIndex(rs.getInt("cid") - 1);
					bPriceF.setText("" + rs.getDouble("sellPrice"));
					aDataF.setText(rs.getString("arrivalDate"));
					eDateF.setText(rs.getString("expireDate"));
					quantiF.setText("" + rs.getInt("countNumber"));
					nameF.setText(rs.getString("name"));
					// sPriceF.setText("");
					detailA.setText(rs.getString("detail"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {

		}
	}

}
