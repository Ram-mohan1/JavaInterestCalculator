package project;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class partiData extends JFrame implements ActionListener
{
	JFrame frame1;
    JLabel l0, l1, l2;
    JComboBox c1;
    JButton b1;
    Connection con;
    ResultSet rs, rs1;
    Statement st, st1;
    PreparedStatement pst;
    String ids;
    static JTable table;
    String[] columnNames = {"ID","Name","Amount","Date of Borrowing","Interest/Rs.100","Phone Number"};
    String from;
    
    partiData()
    {
    	l0 = new JLabel("Select Name to view their particular info!");
    	l0.setForeground(Color.red);
        l0.setFont(new Font("Serif", Font.BOLD, 20));
        l1 = new JLabel("Select name");
        b1 = new JButton("submit");
        
        l0.setBounds(50, 50, 350, 40);
        l1.setBounds(75, 110, 75, 20);
        b1.setBounds(150, 150, 150, 20);
        b1.addActionListener(this);
        
        setTitle("Fetching Info From DataBase");

        setLayout(null);

        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        add(l0);
        add(l1);
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
			st.close();
			rs.close();
        }
        catch(Exception e)
        {
        	
        }
    }
    public void actionPerformed(ActionEvent ae)
    {
    	if(ae.getSource()==b1)
    	{
    		showTableData();
    	}
    }
    public void showTableData()
    {
    	frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
       
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        from = (String) c1.getSelectedItem();
        
        String uname = "";
		int id;
		int am;
		float intr;
		Date date;
		BigDecimal phnno;
        try
        {
        	pst = con.prepareStatement("select * from interest where Name='" + from + "'");
            ResultSet rs = pst.executeQuery();

            int i = 0;
            if (rs.next()) 
            {
            	id = rs.getInt("ID");
				uname = rs.getString("Name");
				am = rs.getInt("Amount");
				date = rs.getDate("TookDate");
				intr = rs.getFloat("Intrst");
				phnno = rs.getBigDecimal("Phn");
				model.addRow(new Object[] {id,uname,am,date,intr,phnno});
				i++;
            }
            if (i < 1) 
            {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }

            if (i == 1) 
            {
                System.out.println(i + " Record Found");
            } 
            else 
            {
                System.out.println(i + " Records Found");
            }
        }
        catch(Exception ex)
        {
        	 JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(500, 400);
    }
    public static void main(String args[])
    {
    	new partiData();
    }
}
