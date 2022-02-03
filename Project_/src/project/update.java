package project;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.sql.*;
import javax.swing.*;

public class update extends JFrame implements ActionListener
{
	public void Recall()
	{
		jTextField1.setText("");  
	    jTextField2.setText("");  
	    jTextField3.setText("");
	    jTextField4.setText("");  
	    jTextField5.setText("");  
	    jTextField6.setText("");
	}
	JFrame j1;
	JLabel l,l0,l1,l2,l3,l4,l5;
	JButton b1;
    Connection con;
    Statement st,st1;
    PreparedStatement pst;
	JTextField jTextField1,jTextField2,jTextField3,jTextField4,jTextField5,jTextField6;
	
	update()
	{
		l = new JLabel("Data with given ID will be updated!");
		l.setForeground(Color.red);
        l.setFont(new Font("Serif", Font.BOLD, 20));
        
        setTitle("Update Data");
        
		l0 = new JLabel("ID");
		l1 = new JLabel("Name");
		l2 = new JLabel("Amount");
		l3 = new JLabel("Date");
		l4 = new JLabel("Interest");
		l5 = new JLabel("Phone No.");
		
		l.setBounds(60,0,1000,100);
		l0.setBounds(55, 100, 75, 20);
		l1.setBounds(55, 150, 75, 20);
		l2.setBounds(55, 200, 75, 20);
		l3.setBounds(55, 250, 75, 20);
		l4.setBounds(55, 300, 75, 20);
		l5.setBounds(55, 350, 75, 20);
		
		jTextField1 = new JTextField();
		jTextField2 = new JTextField();
		jTextField3 = new JTextField();
		jTextField4 = new JTextField();
		jTextField5 = new JTextField();
		jTextField6 = new JTextField();
		
		jTextField1.setBounds(130,100,150,20);
		jTextField2.setBounds(130,150,150,20);
		jTextField3.setBounds(130,200,150,20);
		jTextField4.setBounds(130,250,150,20);
		jTextField5.setBounds(130,300,150,20);
		jTextField6.setBounds(130,350,150,20);
		
		
	    
	    b1 = new JButton("Update");
	    b1.setBounds(100, 400, 150, 20);
	    
	    setLayout(null);
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add(l);
        add(l0);
        add(jTextField1);
        add(l1);
        add(jTextField2);
        add(l2);
        add(jTextField3);
        add(l3);
        add(jTextField4);
        add(l4);
        add(jTextField5);
        add(l5);
        add(jTextField6);
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
			String str = jTextField6.getText();
			BigInteger bb = new BigInteger(str);
			st.execute("Update interest set Name ='" + jTextField2.getText() + "',Amount = " + Integer.parseInt(jTextField3.getText()) + ",TookDate = '" + jTextField4.getText() + "',Intrst = " + Float.parseFloat(jTextField5.getText()) + ", Phn = " + bb + " where ID = " + Integer.parseInt(jTextField1.getText()) + "");  
			JOptionPane.showMessageDialog(null, "Record Updated...");
			JDialog.setDefaultLookAndFeelDecorated(true);
		    int response = JOptionPane.showConfirmDialog(null, "Do you want to see the table?", "Confirm",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) 
		    {
		      
		    } 
		    else if (response == JOptionPane.YES_OPTION) 
		    {
		      eg ob = new eg();
		      ob.showTableData();
		      Recall();
		    } 
		    else if (response == JOptionPane.CLOSED_OPTION) 
		    {
		      
		    }
			st.close();
			con.close();
        }
        catch(SQLException | ClassNotFoundException e)
        {
        	JOptionPane.showMessageDialog(null, e);
        	Recall();
        }
	}
	public static void main(String args[])
    {
    	new update();
    }
}
