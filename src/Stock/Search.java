package Stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Database.DriverClass;
import MyLib.Header;
import MyLib.LButton;
import MyLib.RItem;

public class Search extends JPanel {
	final String[] day = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
			"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	final String[] month = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
	final String[] year = new String[] { "2015", "2016", "2017", "2018", "2019", "2030", "2031", "2032", "2033", "2034",
			"2035", "2036" };

	JPanel headP;

	JPanel searchP;
	JCheckBox idL;
	JTextField idF;
	JCheckBox nameL;
	JTextField nameF;
	JCheckBox categoryL;
	String[] categories;
	JComboBox categoryF;
	JCheckBox bPriceL;
	JTextField bPriceF;
	JCheckBox sPriceL;
	JTextField sPriceF;
	JCheckBox ratingL;
	JTextField ratingF;
	JCheckBox countL;
	JTextField countF;
	JCheckBox aDateL;
	JComboBox adayF;
	JComboBox amonthF;
	JComboBox ayearF;

	JCheckBox eDateL;
	JComboBox edayF;
	JComboBox emonthF;
	JComboBox eyearF;

	JButton restB;
	JButton searchB;

	JPanel resultP;
	JPanel rlistP;
	JScrollPane jsp;

	private String csql;
	private DriverClass db;

	public Search() {
		setLayout(new BorderLayout());
		headP = new Header("Search Items");

		resultP = new JPanel(new BorderLayout());
		resultP.setBorder(new EmptyBorder(20, 10, 10, 10));
		rlistP = new JPanel();
		rlistP.setLayout(new BoxLayout(rlistP, BoxLayout.Y_AXIS));
		jsp = new JScrollPane(rlistP);
		jsp.setBorder(new EmptyBorder(0, 10, 0, 0));
		jsp.setBackground(Color.DARK_GRAY);
		resultP.add(jsp, BorderLayout.CENTER);

		searchP = new JPanel(new GridLayout(11, 2, 5, 5));
		searchP.setBorder(new EmptyBorder(30, 10, 130, 10));
		searchP.setPreferredSize(new Dimension(390, getHeight()));
		idL = new JCheckBox("Id");
		idF = new JTextField();
		nameL = new JCheckBox("Name");
		nameF = new JTextField();

		categoryL = new JCheckBox("Category");
		getCategoriesList();
		categoryF = new JComboBox(categories);

		bPriceL = new JCheckBox("Buy Price");
		bPriceF = new JTextField();
		bPriceL.setEnabled(false);
		bPriceF.setEnabled(false);
		sPriceL = new JCheckBox("Sell Price");
		sPriceF = new JTextField();
		ratingL = new JCheckBox("Rating");
		ratingF = new JTextField();
		ratingL.setEnabled(false);
		ratingF.setEnabled(false);
		countL = new JCheckBox("Count");
		countF = new JTextField();
		aDateL = new JCheckBox("Arrival Date");
		// aDateF = new JTextField();
		JPanel adateP = new JPanel(new GridLayout(1, 3, 1, 1));
		adayF = new JComboBox(day);
		amonthF = new JComboBox(month);
		ayearF = new JComboBox(year);
		adateP.add(adayF);
		adateP.add(amonthF);
		adateP.add(ayearF);

		eDateL = new JCheckBox("Expire Date");
		JPanel edateP = new JPanel(new GridLayout(1, 3, 1, 1));
		edayF = new JComboBox(day);
		emonthF = new JComboBox(month);
		eyearF = new JComboBox(year);
		edateP.add(edayF);
		edateP.add(emonthF);
		edateP.add(eyearF);

		restB = new LButton("Reset");
		searchB = new LButton("Search");

		searchP.add(idL);
		searchP.add(idF);
		searchP.add(nameL);
		searchP.add(nameF);
		searchP.add(categoryL);
		searchP.add(categoryF);
		searchP.add(bPriceL);
		searchP.add(bPriceF);
		searchP.add(sPriceL);
		searchP.add(sPriceF);
		searchP.add(ratingL);
		searchP.add(ratingF);
		searchP.add(countL);
		searchP.add(countF);
		searchP.add(aDateL);
		searchP.add(adateP);
		searchP.add(eDateL);
		searchP.add(edateP);
		searchP.add(restB);
		searchP.add(searchB);

		add(headP, BorderLayout.NORTH);
		add(searchP, BorderLayout.WEST);
		add(resultP, BorderLayout.CENTER);

		searchB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				csql = "select * from shop s,item i where s.id>=1 and i.id=s.id";

				if (idL.isSelected()) {
					String val = idF.getText().trim();
					String key = " and s.id='" + val + "'";
					csql = csql + key;
				} else {
					csql = csql;
				}

				if (nameL.isSelected()) {
					String key = " and i.name='" + nameF.getText().trim() + "'";
					csql = csql + key;
				} else {
					csql = csql;
				}

				if (categoryL.isSelected()) {
					String key = " and i.cid='" + (categoryF.getSelectedIndex() + 1) + "'";
					csql = csql + key;
				} else {
					csql = csql;
				}

				if (sPriceL.isSelected()) {
					String key = " and s.sellPrice='" + Double.parseDouble(sPriceF.getText().trim()) + "'";
					csql = csql + key;
				} else {
					csql = csql;
				}

				if (countL.isSelected()) {
					String key = " and s.countNumber='" + Integer.parseInt(countF.getText().trim()) + "'";
					csql = csql + key;
				} else {
					csql = csql;
				}

				if (aDateL.isSelected()) {
					String day = adayF.getSelectedItem().toString();
					String month = amonthF.getSelectedItem().toString();
					String year = ayearF.getSelectedItem().toString();
					String date = day + "-" + month + "-" + year;
					String key = " and i.arrivalDate='" + date + "'";
					csql = csql + key;
				} else {
					csql = csql;
				}

				if (eDateL.isSelected()) {
					String day = edayF.getSelectedItem().toString();
					String month = emonthF.getSelectedItem().toString();
					String year = eyearF.getSelectedItem().toString();
					String date = day + "-" + month + "-" + year;
					String key = " and i.expireDate='" + date + "'";
					csql = csql + key;
				} else {
					csql = csql;
				}

				rlistP.removeAll();
				rlistP.repaint();
				rlistP.revalidate();
				getDatas(csql);
				rlistP.repaint();
				rlistP.revalidate();
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

	public void getDatas(String sql) {

		db = new DriverClass();
		String id = null;
		String name = null;
		int count = 0;
		double bprice = 0.0;

		try {
			Connection c = db.getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getString("id");
				count = rs.getInt("countNumber");
				bprice = rs.getDouble("sellPrice");
				name = rs.getString("name");
				RItem item = new RItem(id, name, count, bprice);
				rlistP.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
