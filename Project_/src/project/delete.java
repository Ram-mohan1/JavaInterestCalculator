package project;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class delete extends JFrame implements ActionListener
{
	public void Recall()
	{
		jTextField1.setText("");  
	  
	}
	JFrame j1;
	JLabel l0,l;
	JButton b1;
    Connection con;
    Statement st,st1;
    PreparedStatement pst;
	JTextField jTextField1;
	
	delete()
	{
		
		l = new JLabel("Enter ID to delete the record!");
		l.setForeground(Color.red);
        l.setFont(new Font("Serif", Font.BOLD, 20));
        
		l0 = new JLabel("ID");
		
		l0.setBounds(80, 100, 75, 20);
		l.setBounds(50,0,1000,100);
		
		jTextField1 = new JTextField();
		
		jTextField1.setBounds(130,100,150,20);
	    
	    b1 = new JButton("Delete!");
	    b1.setBounds(100, 150, 150, 20);
	    
	    setLayout(null);
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add(l);
        add(l0);
        add(jTextField1);
        add(b1);
        
        b1.addActionListener(this);
        
	}

	public void actionPerformed(ActionEvent ae) 
	{
		try
        {
        	Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String name ="postgres";
			String pass = "1";
			con = DriverManager.getConnection(url,name,pass);
			
			Statement st = con.createStatement();
			
			st.executeUpdate("delete from interest where ID = " + Integer.parseInt(jTextField1.getText()) + "");  
			JOptionPane.showMessageDialog(null, "Record deleted...");
			JDialog.setDefaultLookAndFeelDecorated(true);
		    int response = JOptionPane.showConfirmDialog(null, "Do you want to see the refreshed table?", "Confirm",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) 
		    {} 
		    else if (response == JOptionPane.YES_OPTION) 
		    {
		      eg ob = new eg();
		      ob.showTableData();
		      Recall();
		    } 
		    else if (response == JOptionPane.CLOSED_OPTION) 
		    {}
			st.close();
			con.close();
			Recall();
        }
        catch(SQLException | ClassNotFoundException e)
        {
        	JOptionPane.showMessageDialog(null, e);
        	Recall();
        }
	}
	public static void main(String args[])
    {
    	new delete();
    }
}
