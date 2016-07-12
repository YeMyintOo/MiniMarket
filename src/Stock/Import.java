package Stock;

import java.awt.BorderLayout;
import java.awt.Font;
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

public class Import extends JPanel {
	JPanel headP;

	JPanel infoP;

	JPanel leftP;
	JLabel codeL;
	JTextField codeF;
	JButton codeB;

	JLabel categoryL;
	String[] categories;
	JComboBox categoryF;
	JLabel aDataL;
	JTextField aDataF;
	JLabel eDateL;
	JTextField eDateF;
	JLabel quantiL;
	JTextField quantiF;
	JLabel wcountF;
	JLabel nameL;
	JTextField nameF;
	JLabel sPriceL;
	JTextField sPriceF;
	JLabel bPriceL;

	JPanel rightP;
	JLabel detailL;
	JTextArea detailA;

	JPanel btnP;
	JButton insertB;
	JButton resetB;

	ShowItemList showList;
	DriverClass db;

	int total;
	int count;

	public Import() {
		setLayout(new BorderLayout());
		headP = new Header("Add Inserts");

		infoP = new JPanel(new BorderLayout());

		leftP = new JPanel(new GridLayout(7, 2, 5, 5));
		codeL = new JLabel("Item Code");
		JPanel codeP = new JPanel(new BorderLayout());
		codeF = new JTextField();
		codeB = new CButton("...");
		codeP.add(codeF, BorderLayout.CENTER);
		codeP.add(codeB, BorderLayout.EAST);

		categoryL = new JLabel("Category");
		getCategoriesList();
		categoryF = new JComboBox(categories);
		nameL = new JLabel("Item Name");
		
		nameF = new JTextField(25);
		nameF.setFont(new Font("Zawgyi-One",Font.PLAIN,12));
		nameF.setEnabled(false);
		sPriceL = new JLabel("Sell Price");
		JPanel priceP = new JPanel(new GridLayout(1, 2, 5, 5));
		sPriceF = new JTextField();
		bPriceL = new JLabel("Data", JLabel.CENTER);
		priceP.add(sPriceF);
		priceP.add(bPriceL);

		aDataL = new JLabel("Arrival Date");
		aDataF = new JTextField();
		aDataF.setEnabled(false);
		eDateL = new JLabel("Expire Date");
		eDateF = new JTextField();
		eDateF.setEnabled(false);
		quantiL = new JLabel("Count");
		JPanel quantiP = new JPanel(new GridLayout(1, 2, 5, 5));
		quantiF = new JTextField();
		wcountF = new JLabel("Data", JLabel.CENTER);
		quantiP.add(quantiF);
		quantiP.add(wcountF);

		leftP.add(codeL);
		leftP.add(codeP);
		leftP.add(nameL);
		leftP.add(nameF);
		leftP.add(categoryL);
		leftP.add(categoryF);
		leftP.add(sPriceL);
		leftP.add(priceP);
		leftP.add(aDataL);
		leftP.add(aDataF);
		leftP.add(eDateL);
		leftP.add(eDateF);
		leftP.add(quantiL);
		leftP.add(quantiP);

		rightP = new JPanel(new BorderLayout());
		JPanel subRP = new JPanel(new GridLayout(2, 2, 5, 5));
		subRP.setBorder(new EmptyBorder(0, 20, 0, 0));

		JPanel detailP = new JPanel(new BorderLayout());
		detailP.setFont(new Font("Zawgyi-One",Font.PLAIN,12));
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
				bPriceL.setText("");
				wcountF.setText("");
				categoryF.setSelectedIndex(0);
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

			}
		});

		codeB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showList = new ShowItemList();
				showList.closeB.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						codeF.setText(showList.code);
					}
				});
			}

		});

		codeF.addKeyListener(new MyKeyListener() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					db = new DriverClass();
					loadData();
				}
			}
		});

		insertB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sql = "insert into shop values('" + codeF.getText().trim() + "','"
						+ Integer.parseInt(quantiF.getText().trim()) + "','"
						+ Double.parseDouble(sPriceF.getText().trim()) + "')";
				int var = total - Integer.parseInt(quantiF.getText().trim());
				String wsql = "UPDATE warehouse SET countNumber='" + var + "' where id='" + codeF.getText().trim()
						+ "'";
				try {
					if (db == null) {
						db = new DriverClass();
					}
					Connection c = db.getConnection();
					Statement stmt = c.createStatement();
					int i = stmt.executeUpdate(sql);
					int k = stmt.executeUpdate(wsql);
					if (i == 1 && k==1) {
						JOptionPane.showMessageDialog(null, "Success Shop Operation", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						resetB.doClick();
					} else {
						JOptionPane.showMessageDialog(null, "Fail Shop Operation", "Fail", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		});
	}

	public void getCategoriesList() {
		try {
			if (db == null) {
				db = new DriverClass();
			}
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

	public void loadData() {
		try {
			String sql = "SELECT * FROM item i,warehouse w where i.id=w.id and i.id='" + codeF.getText().trim() + "'";
			try {
				Connection c = db.getConnection();
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					total = rs.getInt("countNumber");
					bPriceL.setText("" + rs.getDouble("buyPrice"));
					wcountF.setText("" + rs.getInt("w.countNumber"));
					categoryF.setSelectedIndex(rs.getInt("cid") - 1);
					aDataF.setText(rs.getString("arrivalDate"));
					eDateF.setText(rs.getString("expireDate"));
					nameF.setText(rs.getString("name"));
					detailA.setText(rs.getString("detail"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {

		}
	}
}
