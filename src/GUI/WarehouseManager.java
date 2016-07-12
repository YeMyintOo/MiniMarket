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
import Warehouse.*;


public class WarehouseManager extends JPanel {

	private TitleButton titleB;

	private JPanel main;

	private JPanel shelf; // Function Panel
	private JButton addB;
	private JButton searchB;
	private JButton monitorB;
	private JButton editB;
	
	private JPanel slide;
	private Import imp;
	private Search sec;
	private Monitor mon;
	private Edit edit;
	
	

	public WarehouseManager() {
		setLayout(new BorderLayout());
		setBackground(Color.lightGray);

		titleB = new TitleButton("Warehouse Management");

		main = new JPanel(new BorderLayout());
		Border border = main.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		main.setBorder(new CompoundBorder(border, margin));

		////////////////////////////////////////////
		shelf = new JPanel(new GridLayout(10,1,0,5));
		shelf.setPreferredSize(new Dimension(230, getHeight()));
		
		File sF = new File("Resources/Icon/AddCustomer.png");
		ImageIcon sIMG = new ImageIcon(sF.getAbsolutePath());
		addB = new FunButton("Add New Item",sIMG);
		
		File cF = new File("Resources/Icon/Search.png");
		ImageIcon cIMG = new ImageIcon(cF.getAbsolutePath());
		searchB = new FunButton("Search Item",cIMG);
		
		File mF = new File("Resources/Icon/Monitor.png");
		ImageIcon mIMG = new ImageIcon(mF.getAbsolutePath());
		monitorB = new FunButton("Monitor Stock", mIMG);
		
		File eF = new File("Resources/Icon/editButton.png");
		ImageIcon eIMG = new ImageIcon(eF.getAbsolutePath());
		editB = new FunButton("Edit Stock", eIMG);
		
		shelf.add(addB);
		shelf.add(searchB);
		shelf.add(monitorB);
		shelf.add(editB);
		////////////////////////////////////////////
		slide=new JPanel(new BorderLayout());
		slide.setBorder(new EmptyBorder(0,10,20,10));

		main.add(shelf, BorderLayout.WEST);
		main.add(slide,BorderLayout.CENTER);

		add(titleB, BorderLayout.NORTH);
		add(main, BorderLayout.CENTER);

		addB.addActionListener(new ActionListener(){
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
		searchB.addActionListener(new ActionListener(){
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
		
		monitorB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mon == null) {
					mon = new Monitor();
				}
				slide.removeAll();
				slide.add(mon, BorderLayout.CENTER);
				slide.repaint();
				slide.revalidate();
			}
		});
		editB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ware House Edit");
				if(edit==null){
					edit=new Edit();
				}
				slide.removeAll();
				slide.add(edit, BorderLayout.CENTER);
				slide.repaint();
				slide.revalidate();
			}
		});
		
	}

}
