package Employee;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Database.DriverClass;
import MyLib.Header;
import MyLib.LButton;

public class Import extends JPanel implements ActionListener {
	JPanel headP;

	JPanel infoP;

	JPanel leftP;
	JLabel empL;
	JTextField empF;
	JLabel nameL;
	JTextField nameF;
	JLabel rankL;
	String[] ranks = new String[] { "Staff", "Cashier", "Manager", "Cleaning Staff" };
	JComboBox<String> rankF;
	JLabel salaryL;
	JTextField salaryF;

	JLabel dateL;
	JComboBox<String> dayF;
	JComboBox<String> monthF;
	JComboBox<String> yearF;
	JLabel nrcL;
	JTextField nrcF;
	JLabel gender;
	JRadioButton maleR;
	JRadioButton femaleR;
	JLabel phL;
	JTextField phF;

	JPanel rightP;

	JLabel profileL;
	JButton profileB;
	JLabel addressL;
	JTextArea addressA;

	JPanel btnP;
	JButton insertB;
	JButton resetB;

	DriverClass db;
	File file = null;
	PreparedStatement prepareStatement;

	public Import() {
		setLayout(new BorderLayout());
		// add(new JLabel("This is Add Employee Page"),BorderLayout.CENTER);

		setLayout(new BorderLayout());
		headP = new Header("Insert Employees");

		infoP = new JPanel(new BorderLayout());

		leftP = new JPanel(new GridLayout(8, 2, 20, 5));
		leftP.setPreferredSize(new Dimension(700, getHeight()));
		empL = new JLabel("Employee ID");
		empF = new JTextField();
		nameL = new JLabel("Name");
		nameF = new JTextField();
		rankL = new JLabel("Rank");
		rankF = new JComboBox(ranks);
		rankF.setEditable(false);
		salaryL = new JLabel("Salary");
		salaryF = new JTextField();
		dateL = new JLabel("Start Date");

		JPanel dateP = new JPanel(new GridLayout(1, 3, 1, 1));
		dayF = new JComboBox(new String[] { "Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31" });
		monthF = new JComboBox(new String[] { "Month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" });
		yearF = new JComboBox(new String[] { "Year", "2015", "2016", "2017", "2018", "2019", "2030", "2031", "2032",
				"2033", "2034", "2035", "2036" });
		dateP.add(dayF);
		dateP.add(monthF);
		dateP.add(yearF);

		nrcL = new JLabel("NRC Number");
		nrcF = new JTextField();
		gender = new JLabel("Gender");
		maleR = new JRadioButton("Male", true);
		femaleR = new JRadioButton("Female");

		JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		radioPanel.add(maleR);
		radioPanel.add(femaleR);

		ButtonGroup bg = new ButtonGroup();
		bg.add(maleR);
		bg.add(femaleR);
		phL = new JLabel("Phone No");
		phF = new JTextField();
		addressL = new JLabel("Address");

		JPanel addressP = new JPanel(new BorderLayout());
		addressA = new JTextArea();
		addressA.setToolTipText("Detail");
		addressA.setRows(7);
		addressA.setBorder(new EmptyBorder(20, 20, 20, 20));
		addressP.add(addressA, BorderLayout.CENTER);
		addressP.setPreferredSize(new Dimension(300, 300));
		leftP.add(empL);
		leftP.add(empF);
		leftP.add(nameL);
		leftP.add(nameF);
		leftP.add(rankL);
		leftP.add(rankF);
		leftP.add(salaryL);
		leftP.add(salaryF);
		leftP.add(nrcL);
		leftP.add(nrcF);
		leftP.add(dateL);
		leftP.add(dateP);
		leftP.add(gender);
		leftP.add(radioPanel);
		leftP.add(phL);
		leftP.add(phF);

		// leftP.add(addressL);
		// leftP.add(addressP);

		// addressP.setBorder(new EmptyBorder(10, 20, 8, 0));

		// JPanel componentLeftP=new JPanel(new BorderLayout());
		// componentLeftP.add(leftP,BorderLayout.CENTER);
		// componentLeftP.add(addressP,BorderLayout.SOUTH);

		rightP = new JPanel(new BorderLayout());
		JPanel profileP = new JPanel(new FlowLayout(FlowLayout.CENTER));
		profileP.setBorder(new EmptyBorder(0, 20, 0, 0));
		profileL = new JLabel("Edit Profile");
		profileB = new JButton("ADD PHOTO");
		profileB.setPreferredSize(new Dimension(160, 160));
		profileP.add(profileL);
		profileP.add(profileB);

		// rightP.setPreferredSize(new Dimension(300,300));
		rightP.add(profileP, BorderLayout.NORTH);
		// rightP.add(addressP, BorderLayout.CENTER);

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
		profileB.addActionListener(this);
		// resetB.addActionListener(this);
		insertB.addActionListener(this);
		resetB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				empF.setText("");
				nameF.setText("");
				rankF.setSelectedIndex(0);
				salaryF.setText("");
				nrcF.setText("");
				dayF.setSelectedIndex(0);
				monthF.setSelectedIndex(0);
				yearF.setSelectedIndex(0);

				maleR.setSelected(true);
				phF.setText("");
				profileB.setIcon(null);
				repaint();
				revalidate();
			}
		});
		salaryF.setText("100000");
		rankF.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				db = new DriverClass();
				String sql = "SELECT * FROM employeerank where rank='" + rankF.getSelectedItem().toString() + "'";
				try {
					Connection c = db.getConnection();
					Statement stmt = c.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						salaryF.setText("" + rs.getInt("salary"));
					}
				} catch (Exception er) {
					er.printStackTrace();
				}
			}
		});

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == profileB) {
			db = new DriverClass();

			JFileChooser f = new JFileChooser();
			int returnValue = f.showOpenDialog(this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				file = f.getSelectedFile();
				ImageIcon photo = new ImageIcon(file.getPath());
				profileB.setIcon(photo);
				profileB.setText("");

			}

		}
		if (e.getSource() == insertB) {
			db = new DriverClass();
			int rank = getRankID(rankF.getSelectedItem().toString());
			int eid = Integer.parseInt(empF.getText());
			String name = nameF.getText();
			int phone = Integer.parseInt(phF.getText());
			String nrc = nrcF.getText();
			String day = String.valueOf(dayF.getSelectedItem());
			String month = String.valueOf(monthF.getSelectedItem());
			String year = String.valueOf(yearF.getSelectedItem());
			String date = day + "-" + month + "-" + year;
			System.out.println(date);

			double salary = Double.parseDouble(salaryF.getText());
			boolean gender = false;
			if (maleR.isSelected()) {
				gender = true;
			}
			try {
				Connection c = db.getConnection();
				InputStream inputStream = new FileInputStream(file);
				prepareStatement = c.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?)");
				prepareStatement.setInt(1, rank);
				prepareStatement.setInt(2, eid);
				prepareStatement.setString(3, name);
				prepareStatement.setInt(4, phone);
				prepareStatement.setString(5, nrc);
				prepareStatement.setString(6, date);
				prepareStatement.setBoolean(7, gender);
				prepareStatement.setBinaryStream(8, inputStream, (int) (file.length()));

				prepareStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Insert successfully");

				empF.setText("");
				nameF.setText("");
				rankF.setSelectedIndex(0);
				salaryF.setText("");
				nrcF.setText("");
				dayF.setSelectedIndex(0);
				monthF.setSelectedIndex(0);
				yearF.setSelectedIndex(0);

				maleR.setSelected(true);
				phF.setText("");
				// addressA.setText("");
				profileB.setText("ADD PHOTO");

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Add error");
				JOptionPane.showMessageDialog(null, "Data is not success");
				e1.printStackTrace();

			}

		}

	}

	public int getRankID(String type) {
		int id = 0;
		String sql = "SELECT * FROM employeerank where rank='" + type + "'";
		try {
			Connection c = db.getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt("rid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}
