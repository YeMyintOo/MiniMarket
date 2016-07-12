package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import MyLib.FunButton;
import MyLib.TitleButton;
import Employee.*;

public class EmployeeManager extends JPanel {

	private TitleButton titleB;

	private JPanel main;

	private JPanel shelf; // Function Panel
	private JButton addB;
	private JButton searchB;
	private JButton editB;

	private JPanel slide;
	private Import imp;
	private Search sec;
	private Edit edit;

	public EmployeeManager() {
		setLayout(new BorderLayout());
		setBackground(Color.lightGray);

		titleB = new TitleButton("Employee Management");

		main = new JPanel(new BorderLayout());
		Border border = main.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		main.setBorder(new CompoundBorder(border, margin));

		////////////////////////////////////////////
		shelf = new JPanel(new GridLayout(10, 1, 0, 5));
		shelf.setPreferredSize(new Dimension(230, getHeight()));

		File sF = new File("Resources/Icon/AddEmployee.png");
		ImageIcon sIMG = new ImageIcon(sF.getAbsolutePath());
		addB = new FunButton("Add Employee", sIMG);

		File cF = new File("Resources/Icon/Search.png");
		ImageIcon cIMG = new ImageIcon(cF.getAbsolutePath());
		searchB = new FunButton("Search Employee", cIMG);

		File eF = new File("Resources/Icon/editButton.png");
		ImageIcon eIMG = new ImageIcon(eF.getAbsolutePath());
		editB = new FunButton("Edit Stock", eIMG);

		shelf.add(addB);
		shelf.add(searchB);
		shelf.add(editB);
		////////////////////////////////////////////
		slide = new JPanel(new BorderLayout());
		slide.setBorder(new EmptyBorder(0, 10, 20, 10));

		main.add(shelf, BorderLayout.WEST);
		main.add(slide, BorderLayout.CENTER);

		add(titleB, BorderLayout.NORTH);
		add(main, BorderLayout.CENTER);

		addB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (imp == null) {
					imp = new Import();
				}
				slide.removeAll();
				slide.add(imp, BorderLayout.CENTER);
				slide.repaint();
				slide.revalidate();
			}
		});
		searchB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sec == null) {
					sec = new Search();
				}
				slide.removeAll();
				slide.add(sec, BorderLayout.CENTER);
				slide.repaint();
				slide.revalidate();
			}
		});
		editB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (edit == null) {
					edit = new Edit();
				}
				slide.removeAll();
				slide.add(edit, BorderLayout.CENTER);
				slide.repaint();
				slide.revalidate();
			}
		});
	}

}
