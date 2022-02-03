package project;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class updates extends JFrame implements ActionListener
{
	JFrame j1;
	JLabel l0;
	JComboBox c1;
	JButton b1;
    Connection con;
    ResultSet rs, rs1;
    Statement st, st1;
    PreparedStatement pst;
    String ids;
    static JTable table;
    String[] columnNames = {"Insert", "Update", "Delete"};
    String from;
    
    updates()
    {
    	l0 = new JLabel("Fetch");
    	l0.setForeground(Color.red);
        l0.setFont(new Font("Serif", Font.BOLD, 20));
        l0.setBounds(75, 110, 75, 20);
        b1 = new JButton("submit");
        b1.setBounds(150, 150, 150, 20);
        
        setTitle("Fetching Student Info From DataBase");

        setLayout(null);
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        add(l0);
        add(b1);
        b1.addActionListener(this);
        
        try
        {
        	Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String name ="postgres";
			String pass = "1";
			con = DriverManager.getConnection(url,name,pass);
			st = con.createStatement();
			rs = st.executeQuery("select Name from interest");
			
			c1 = new JComboBox(columnNames);
			c1.setBounds(150,110,150,20);
			add(c1);
			st.close();
			rs.close();
        }
        catch(Exception e)
        {
        	
        }
    }
    public void actionPerformed(ActionEvent ae)
    {
    	from = (String)c1.getSelectedItem();
    	if(from.equals("Insert"))
    	{
    		new Insert();
    	}
    	else if(from.equals("Delete"))
    	{
    		new delete();
    	}
    	else
    	{
    		new update();
    	}
    }
    public static void main(String args[])
    {
    	new updates();
    }
}
