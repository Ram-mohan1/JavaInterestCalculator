package project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class sql extends JFrame implements ActionListener
{
	JFrame f1,f2;
	JLabel l0,l1,l2,l3;
	JButton b1,b2,b3,b4;
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
	PreparedStatement pst;
	String ids;
	static JTable table;
	String[] columns = {"Name","Phone Number","Salary"};
	String from;
	sql()
	{
		l0 = new JLabel("Click Here to Get the List!");
		l0.setForeground(Color.red);
        l0.setFont(new Font("Serif", Font.BOLD, 20));
		l1 = new JLabel("Click Here to Get Particular Person's details!");
		l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2 = new JLabel("Click here to Update,Delete,Add entries!");
        l2.setForeground(Color.orange);
        l2.setFont(new Font("Serif", Font.BOLD, 20));
        l3 = new JLabel("Click here to Calculate Interest!");
        l3.setForeground(Color.pink);
        l3.setFont(new Font("Serif", Font.BOLD, 20));
		
		b1 = new JButton("Submit");
		b2 = new JButton("Click Here!");
		b3 = new JButton("Update Data!");
		b4 = new JButton("Interest!");
		
		l0.setBounds(100,50,350,40);
		l1.setBounds(60, 110, 400, 120);
		l2.setBounds(70, 210, 400, 120);
		l3.setBounds(80, 310, 400, 120);
		
		b1.setBounds(150, 100, 150, 40);
		b2.setBounds(150, 200, 150, 40);
		b3.setBounds(150, 300, 150, 40);
		b4.setBounds(150, 400, 150, 40);
		
		b1.addActionListener(this);
		b2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				new partiData();
			}
		});
		b3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new updates();
			}
		});
		b4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new temp();
			}
		});
		
		setTitle("Project");
		setLayout(null);
		setVisible(true);
		setSize(500,500);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		add(l0);
		add(b1);
		
		add(l1);
		add(b2);
		
		add(l2);
		add(b3);
		
		add(l3);
		add(b4);
		try
		{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String name ="postgres";
			String pass = "1";
			con = DriverManager.getConnection(url,name,pass);
			st = con.createStatement();
			rs = st.executeQuery("select * from interest");
			
			st.close();
			rs.close();
		}
		catch(Exception e)
		{
			
		}
	}
	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource() == b1)
		{
			eg ob = new eg();
		    ob.showTableData();
		}
		
	}
	public static void main(String[] args)
	{
		new sql();
	}
	
}
