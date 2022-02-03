package project;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class tempo extends JFrame
{
	JFrame f1,f2,f3;
	JLabel l1,l2,l3;
	JButton b1,b2,b3;
	JTextField j1,j2,j3;
	Connection con;
	ResultSet rs;
	Statement st;
	String from;
	JComboBox c1;
	String ids;
	PreparedStatement pst,pst1;
	
	tempo()
	{
		l1 = new JLabel("Select Name to view their particular info!");
    	l1.setForeground(Color.red);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2 = new JLabel("Select name");
        b1 = new JButton("submit");
        
        l1.setBounds(50, 50, 350, 40);
        l2.setBounds(75, 110, 75, 20);
        b1.setBounds(150, 150, 150, 20);
        
        setLayout(null);
        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add(l1);
        add(l2);
        add(b1);
        
        try
        {
        	Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String name ="postgres";
			String pass = "1";
			con = DriverManager.getConnection(url,name,pass);
			st = con.createStatement();
			rs = st.executeQuery("select Name from interest");
			Vector v = new Vector();
			while(rs.next())
			{
				ids = rs.getString(1);
				v.add(ids);
			}
			c1 = new JComboBox(v);
			c1.setBounds(150,110,150,20);
			add(c1);
			
        }
        catch(Exception e)
        {
        	
        }
	}
	public void actionPerformed(ActionEvent ae)
	{
		f1 = new JFrame("Database Search Result");
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setLayout(new BorderLayout());
        
        from = (String)c1.getSelectedItem();
        
        try
        {
        	pst = con.prepareStatement("select TookDate from interest where Name='" + from + "'");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) 
            {
            	l3 = new JLabel("TookDate");
            	l3.setBounds(50,50,100,20);
            	
            	j1 = new JTextField("");
            }
        }
        catch(Exception ex)
        {
        	 JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        f1.setVisible(true);
        f1.setSize(500, 400);
		f1.add(l3);
	}
}
