package MyLib;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Database.DriverClass;

public class ShowItemList extends JDialog {
	private DriverClass db;
	public String code;
	public JPanel resultP;
	public JPanel rlistP;
	JScrollPane jsp;
	public JButton closeB;
	
	public ShowItemList() {
		setLayout(new BorderLayout());
		
		resultP = new JPanel(new BorderLayout());
		resultP.setBorder(new EmptyBorder(50,10,10,50));
		resultP.setBorder(new EmptyBorder(20, 10, 10, 10));
		rlistP = new JPanel();
		rlistP.setLayout(new BoxLayout(rlistP, BoxLayout.Y_AXIS));
		rlistP.setBackground(Color.red);
		jsp = new JScrollPane(rlistP);
		jsp.setBorder(new EmptyBorder(0, 10, 0, 0));
		jsp.setBackground(Color.DARK_GRAY);
		resultP.add(jsp, BorderLayout.CENTER);
		
		closeB=new LButton("Close");
		add(resultP, BorderLayout.CENTER);
		add(closeB,BorderLayout.SOUTH);
		try{
			String sql="select * from item";
			if(db==null){
				db=new DriverClass();
			}
			Connection c=db.getConnection();
			Statement stmt=c.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				String var=rs.getString("id");
				List list=new List(rs.getString("id"),rs.getString("name"));
				list.getB.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						code=var;
					}
				});
				rlistP.add(list);
			}
			rlistP.repaint();
			rlistP.revalidate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		setSize(450,600);
		setResizable(false);
		setTitle("Choose Code");
		setVisible(true);
		
		closeB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}
}

