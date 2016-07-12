package Stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import Database.DriverClass;
import MyLib.CButton;
import MyLib.Header;
import MyLib.LButton;
import MyLib.PItem;
import MyLib.SItem;

public class Monitor extends JPanel {
	JPanel headP;

	JButton stockB;
	JPanel stockP;
	JPanel slistP;
	JButton sReprtB;

	JButton profitB;
	JPanel profitP;
	JPanel plistP;
	JPanel pbotP;
	JLabel totalPL; // Total Price Label;
	JLabel totalPF;
	JButton pReprtB; // Profit Report Button;

	JPanel conatinerP;

	JPanel filterP;
	String[] dates = new String[] { "1", "2", "3", "4", "5" };
	String[] months = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
	String[] years = new String[] { "2015", "2016", "2017" };
	String[] cateogs = new String[] { "Coffee", "Milk", "Cat" };

	JLabel sdateL;
	JComboBox<String> sdate, smonth, syear;
	JLabel edateL;
	JComboBox<String> edate, emonth, eyear;
	JCheckBox cateogL;
	JComboBox<String> cateogF;
	JCheckBox idL;
	JTextField idF;

	JButton resetB;
	JButton showB;

	DriverClass db;
	double total;

	public Monitor() {
		setLayout(new BorderLayout());

		headP = new Header("Monitor and Review");

		JPanel leftP = new JPanel(new BorderLayout());
		leftP.setBorder(new EmptyBorder(10, 0, 10, 0));
		JButton filB = new LButton("Filter");
		filterP = new JPanel(new GridLayout(5, 2, 5, 5));
		filterP.setBorder(new EmptyBorder(5, 5, 250, 5));
		sdateL = new JLabel("Start Date");

		JPanel sPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		sdate = new JComboBox<String>(dates);
		smonth = new JComboBox<String>(months);
		syear = new JComboBox<String>(years);
		sPanel.add(sdate);
		sPanel.add(smonth);
		sPanel.add(syear);

		edateL = new JLabel("End Date");
		JPanel ePanel = new JPanel(new GridLayout(1, 3, 5, 5));
		edate = new JComboBox<String>(dates);
		emonth = new JComboBox<String>(months);
		eyear = new JComboBox<String>(years);
		ePanel.add(edate);
		ePanel.add(emonth);
		ePanel.add(eyear);

		cateogL = new JCheckBox("Category");
		getCategoriesList();
		cateogF = new JComboBox<String>(cateogs);
		idL = new JCheckBox("ID");
		idF = new JTextField();
		resetB = new LButton("Reset");
		showB = new LButton("Show Review");
		filterP.add(sdateL);
		filterP.add(sPanel);
		filterP.add(edateL);
		filterP.add(ePanel);
		filterP.add(cateogL);
		filterP.add(cateogF);
		filterP.add(idL);
		filterP.add(idF);
		filterP.add(resetB);
		filterP.add(showB);
		leftP.add(filB, BorderLayout.NORTH);
		leftP.add(filterP, BorderLayout.CENTER);

		JPanel rootP = new JPanel(new BorderLayout());
		JPanel bntCP = new JPanel(new BorderLayout());
		bntCP.setBorder(new EmptyBorder(5, 0, 0, 0));
		JPanel bntP = new JPanel();
		profitB = new LButton("yin ya nage");
		stockB = new LButton("late kyan item");
		bntP.add(profitB);
		bntP.add(stockB);
		bntCP.add(bntP, BorderLayout.WEST);
		conatinerP = new JPanel(new BorderLayout());
		// conatinerP.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

		// Stock Panel///////////////////////
		stockP = new JPanel(new BorderLayout());
		slistP = new JPanel();
		slistP.setLayout(new BoxLayout(slistP, BoxLayout.Y_AXIS));
		JScrollPane ssp = new JScrollPane(slistP);
		ssp.setBorder(new EmptyBorder(0, 10, 0, 0));
		ssp.setBackground(Color.darkGray);
		JPanel sbotP = new JPanel(new BorderLayout());
		sbotP.setBorder(new EmptyBorder(10, 0, 10, 0));
		sReprtB = new CButton("Report");
		sbotP.add(sReprtB, BorderLayout.WEST);
		stockP.add(ssp, BorderLayout.CENTER);
		stockP.add(sbotP, BorderLayout.SOUTH);
		// EOF- Stock Panel///////////////////////

		// Profit Panel///////////////////////
		profitP = new JPanel(new BorderLayout());
		plistP = new JPanel();
		plistP.setLayout(new BoxLayout(plistP, BoxLayout.Y_AXIS));
		JScrollPane psp = new JScrollPane(plistP);
		psp.setBorder(new EmptyBorder(0, 10, 0, 0));
		psp.setBackground(Color.darkGray);

		pbotP = new JPanel(new BorderLayout());
		pbotP.setBorder(new EmptyBorder(10, 0, 10, 50));
		JPanel totalP = new JPanel();
		totalPL = new JLabel("Total :", JLabel.LEFT);
		totalPF = new JLabel("0", JLabel.RIGHT);
		totalP.add(totalPL);
		totalP.add(totalPF);
		totalPF.setFont(new Font("Arial", Font.PLAIN, 20));
		totalPL.setFont(new Font("Arial", Font.PLAIN, 20));
		pReprtB = new CButton("Report");
		pbotP.add(pReprtB, BorderLayout.WEST);
		pbotP.add(totalP, BorderLayout.EAST);

		profitP.add(psp, BorderLayout.CENTER);
		profitP.add(pbotP, BorderLayout.SOUTH);
		// EOF -Profit Panel///////////////////////

		rootP.add(bntCP, BorderLayout.NORTH);
		rootP.add(conatinerP, BorderLayout.CENTER);

		add(headP, BorderLayout.NORTH);
		add(rootP, BorderLayout.CENTER);
		add(leftP, BorderLayout.WEST);

		profitB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				conatinerP.removeAll();
				conatinerP.add(profitP);
				conatinerP.repaint();
				conatinerP.revalidate();
			}
		});
		stockB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				conatinerP.removeAll();
				conatinerP.add(stockP);
				conatinerP.repaint();
				conatinerP.revalidate();
			}
		});

		showB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				db = new DriverClass();
				String sql = "select * from item i,shop w where i.id=w.id";

				if (cateogL.isSelected()) {
					String key = " and cid='" + (cateogF.getSelectedIndex() + 1) + "'";
					sql = sql + key;
				} else {
					sql = sql;
				}

				if (idL.isSelected()) {
					String val = idF.getText().trim();
					String key = " and w.id='" + val + "'";
					sql = sql + key;
				} else {
					sql = sql;
				}

				try {
					Connection c = db.getConnection();
					Statement stmt = c.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						total = total + (rs.getInt("countNumber") * rs.getDouble("sellPrice"));
						PItem item = new PItem(rs.getString("id"), rs.getString("name"), rs.getString("arrivalDate"),
								rs.getInt("countNumber"), rs.getInt("sellPrice"));
						plistP.add(item);
					}
					totalPF.setText("" + total);
				} catch (Exception e2) {
					e2.printStackTrace();
				}

				plistP.repaint();
				plistP.revalidate();

				SItem item2 = new SItem("", "YoYo", "1-12-2015", 20, 4);
				slistP.add(item2);
				slistP.repaint();
				slistP.revalidate();
			}
		});
		resetB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				plistP.removeAll();
				slistP.removeAll();
				plistP.repaint();
				plistP.revalidate();
				slistP.repaint();
				slistP.revalidate();
				cateogF.setSelectedIndex(0);
				idF.setText("");
				total = 0;
				totalPF.setText("" + total);
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
				cateogs = new String[count];

			} catch (Exception e2) {
				e2.printStackTrace();
			}

			int index = 0;
			try {
				Connection c = db.getConnection();
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					cateogs[index] = rs.getString("type");
					index++;
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
