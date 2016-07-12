package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Database.DriverClass;
import MyLib.Calculator;
import MyLib.Item;
import MyLib.LButton;
import MyLib.PDFReport;
import MyLib.ShowItemList;
import MyLib.VHeader;

public class Voucher extends JFrame {
	private DriverClass db;
	private double total;

	public JPanel containerP;
	public JPanel calcuP;

	public VHeader headP;
	public JLabel dateL;
	public JLabel dateD;

	public JLabel priceL;
	public JLabel disL;
	public JLabel tpriceL;
	public JPanel listP;
	public JPanel item;

	public JPanel priceP;
	public JLabel totalL;

	public JPanel botP;
	public JButton printB;
	public JButton clearB;
	public JButton cashB;
	public JButton closeB;

	public ArrayList<String> names;
	public ArrayList<String> codes;
	public ArrayList<String> counts;
	public ArrayList<String> prices;
	public ArrayList<String> scounts;

	public ShowItemList showItem;
	public long serial;

	private PDFReport pdf;

	public Voucher() {
		setLayout(new BorderLayout());
		containerP = new JPanel(new BorderLayout());

		headP = new VHeader();

		calcuP = new Calculator();
		calcuP.setBorder(new EmptyBorder(10, 10, 10, 10));

		JPanel dateP = new JPanel(new BorderLayout());
		dateP.setBackground(Color.darkGray);
		dateP.setBorder(new EmptyBorder(10, 10, 10, 10));
		dateD = new JLabel(new Date(System.currentTimeMillis()).toString());
		dateD.setFont(new Font("Arial", Font.PLAIN, 18));
		dateD.setForeground(Color.white);
		dateP.add(dateD, BorderLayout.WEST);

		listP = new JPanel();
		listP.setLayout(new BoxLayout(listP, BoxLayout.Y_AXIS));
		listP.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

		priceP = new JPanel();
		priceP.setLayout(new GridLayout(1, 5, 5, 5));
		priceP.setPreferredSize(new Dimension(900, 40));
		JLabel tL = new JLabel("Total ", JLabel.RIGHT);
		totalL = new JLabel("");
		priceP.add(new JPanel());
		priceP.add(new JPanel());
		priceP.add(tL);
		priceP.add(totalL);
		priceP.add(new JPanel());

		containerP.add(headP, BorderLayout.NORTH);
		JScrollPane sp = new JScrollPane(listP);
		sp.setBorder(new EmptyBorder(0, 10, 0, 0));
		sp.setBackground(Color.darkGray);
		containerP.add(sp, BorderLayout.CENTER);
		containerP.add(priceP, BorderLayout.SOUTH);

		botP = new JPanel(new BorderLayout());
		botP.setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel btnP = new JPanel();
		botP.add(btnP, BorderLayout.EAST);
		printB = new LButton("Print");
		cashB = new LButton("Cash");
		closeB = new LButton("Close");
		clearB = new LButton("Clear");
		btnP.add(printB);
		btnP.add(clearB);
		btnP.add(cashB);
		btnP.add(closeB);

		JPanel sub = new JPanel(new BorderLayout());
		sub.add(containerP, BorderLayout.CENTER);
		// sub.add(calcuP, BorderLayout.EAST);
		sub.add(dateP, BorderLayout.NORTH);
		sub.add(botP, BorderLayout.SOUTH);

		add(sub, BorderLayout.CENTER);
		setTitle("Voucher");
		setSize(900, 500);
		setResizable(false);
		setVisible(true);

		db = new DriverClass();
		names = new ArrayList<String>();
		codes = new ArrayList<String>();
		counts = new ArrayList<String>();
		prices = new ArrayList<String>();
		scounts = new ArrayList<String>();

		headP.addB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!headP.countF.getText().equals("")) {
					int count = Integer.parseInt(headP.countF.getText());
					String code = headP.codeF.getText().trim();

					getDate(code, count);
					listP.repaint();
					listP.revalidate();

				}
			}
		});

		headP.showB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showItem = new ShowItemList();
				showItem.closeB.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						headP.codeF.setText(showItem.code);
					}
				});
			}
		});

		clearB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listP.removeAll();
				listP.repaint();
				listP.revalidate();
				total = 0;
				totalL.setText("" + total);
				codes.clear();
				counts.clear();
				scounts.clear();
				names.clear();
				prices.clear();
				headP.codeF.setText("");
				headP.countF.setText("");
			}
		});

		cashB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cashData();
				// Store SaledItem
				for (int i = 0; i < codes.size(); i++) {
					addItem(codes.get(i), Integer.parseInt(counts.get(i)), Integer.parseInt(scounts.get(i)));
				}
				JOptionPane.showConfirmDialog(null, "Is paid?", "Casiher", JOptionPane.DEFAULT_OPTION);
				

				System.out.println("Name Size :" + names.size());
				System.out.println("Count Size :"+ counts.size());
				System.out.println("price Size :"+ prices.size());
				pdf = new PDFReport(dateD.getText(), names, counts, prices, totalL.getText().toString());
				
				clearB.doClick();

			}
		});
		closeB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
			}
		});
	}

	public void getDate(String code, int count) {
		String sql = "select * from item i,shop s where s.id=i.id and s.id='" + code + "'";
		try {
			Connection c = db.getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				Item item = new Item(rs.getString("name"), count, rs.getInt("sellPrice"));
				total = total + count * rs.getInt("sellPrice");
				totalL.setText("" + total);
				String name = rs.getString("i.name");
				String price = "" + rs.getDouble("sellPrice");
				String scount = "" + rs.getInt("countNumber");

				names.add(name);
				prices.add(price);
				codes.add(code);
				counts.add("" + count);
				scounts.add(scount);

				listP.add(item);
				item.delB.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						total = total - item.totalD;
						totalL.setText("" + total);
						listP.remove(item);
						listP.repaint();
						listP.revalidate();

						names.remove(name);
						prices.remove(price);
						codes.remove(code);
						counts.remove("" + count);
						scounts.remove(scount);

					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Voucher();
	}

	public void cashData() {
		// Save in report
		if (db == null) {
			db = new DriverClass();
		}
		serial = System.currentTimeMillis();
		String report = "insert into report values('0','" + dateD.getText().trim() + "','" + serial + "','" + total
				+ "','0')";
		try {
			Connection c = db.getConnection();
			Statement stmt = c.createStatement();
			int i = stmt.executeUpdate(report);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "Success Voucher Operation", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Fail Voucher Operation", "Fail", JOptionPane.WARNING_MESSAGE);
			}
			stmt.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addItem(String code, int count, int scount) {
		String add = "insert into saleditem values('0','" + code + "','" + count + "','" + serial + "')";
		int var = scount - count;
		String remvoe = "update shop set countNumber='" + var + "' where id='" + code + "'";
		if (db == null) {
			db = new DriverClass();
		}
		try {
			Connection c = db.getConnection();
			Statement stmt = c.createStatement();
			int i = stmt.executeUpdate(add);
			int k = stmt.executeUpdate(remvoe);
			if (i == 1 && k == 1) {
				System.out.println("Success Item");
			} else {
				System.out.println("Fail Item");
			}
			stmt.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
