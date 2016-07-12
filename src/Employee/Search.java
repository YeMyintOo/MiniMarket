package Employee;

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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Database.DriverClass;
import MyLib.EItem;
import MyLib.Header;
import MyLib.LButton;

public class Search extends JPanel {
	JPanel headP;

	JPanel searchP;
	JCheckBox idL;
	JTextField idF;
	JCheckBox nameL;
	JTextField nameF;
	JCheckBox rankL;
	String[] ranks = new String[] { "Cashier", "Staff", "Cleaning Staff" };
	JComboBox<String> rankCombo;
	JCheckBox salaryL;
	JTextField salaryF;
	JRadioButton maleCb;
	JRadioButton femaleCb;

	JButton restB;
	JButton searchB;
	JPanel resultP;
	JPanel rlistP;
	JScrollPane jsp;

	private String csql = "select * from employee e where e.id>=1 ";
	private String sql = "select * from employee e where e.id>=1 ";
	private DriverClass db;

	public Search() {
		setLayout(new BorderLayout());
		headP = new Header("Search Employees");

		resultP = new JPanel(new BorderLayout());
		resultP.setBorder(new EmptyBorder(20, 10, 10, 10));
		rlistP = new JPanel();
		rlistP.setLayout(new BoxLayout(rlistP, BoxLayout.Y_AXIS));
		jsp = new JScrollPane(rlistP);
		jsp.setBorder(new EmptyBorder(0, 10, 0, 0));
		jsp.setBackground(Color.DARK_GRAY);
		resultP.add(jsp, BorderLayout.CENTER);

		searchP = new JPanel(new GridLayout(6, 2, 5, 5));
		searchP.setBorder(new EmptyBorder(30, 10, 140, 10));
		searchP.setPreferredSize(new Dimension(350, getHeight()));
		idL = new JCheckBox("EmployeeId");
		idF = new JTextField();
		nameL = new JCheckBox("Name");
		nameF = new JTextField();
		rankL = new JCheckBox("Rank");
		rankCombo = new JComboBox<String>(ranks);
		salaryL = new JCheckBox("Salary");
		salaryF = new JTextField();

		ButtonGroup g = new ButtonGroup();
		maleCb = new JRadioButton("Male");
		femaleCb = new JRadioButton("Female");
		g.add(maleCb);
		g.add(femaleCb);

		restB = new LButton("Reset");
		searchB = new LButton("Search");

		searchP.add(idL);
		searchP.add(idF);
		searchP.add(nameL);
		searchP.add(nameF);
		searchP.add(rankL);
		searchP.add(rankCombo);
		searchP.add(salaryL);
		searchP.add(salaryF);
		searchP.add(maleCb);
		searchP.add(femaleCb);
		searchP.add(restB);
		searchP.add(searchB);

		add(headP, BorderLayout.NORTH);
		add(searchP, BorderLayout.WEST);
		add(resultP, BorderLayout.CENTER);

		searchB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				csql = "select * from employee e,employeerank r where e.eRid=r.rid ";

				if (idL.isSelected()) {
					String val = idF.getText().trim();
					String key = " and e.eid='" + val + "'";
					csql = csql + key;
				} else {
					csql = csql;
				}

				if (nameL.isSelected()) {
					String key = " and e.name='" + nameF.getText().trim() + "'";
					csql = csql + key;
				} else {
					csql = csql;
				}

				if (rankL.isSelected()) {
					String key = " and r.rank='" + rankCombo.getSelectedItem().toString() + "'";
					csql = csql + key;
				} else {
					csql = csql;
				}

				if (salaryL.isSelected()) {
					String key = " and r.salary='" + Double.parseDouble(salaryF.getText().trim()) + "'";
					csql = csql + key;
				} else {
					csql = csql;
				}

				if (maleCb.isSelected()) {
					String key = " and e.gender=true";
					csql = csql + key;
				} else {
					csql = csql;
				}
				
				if (femaleCb.isSelected()) {
					String key = " and e.gender=false";
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

	public void getDatas(String sql) {

		db = new DriverClass();
		// int eRid = 0;
		// int eid = 0;
		// String name = null;
		// String rank=null;
		// int rank=rankCombo.setSelectedIndex(0).;;
		// String salary = null;
		// int gender = 0;

		try {
			Connection c = db.getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// eRid = rs.getInt("eRid");
				// eid = rs.getInt("eid");
				// name = rs.getString("name");

				EItem item = new EItem(rs.getString("name"), rs.getString("rank"), rs.getDouble("salary"));
				rlistP.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
