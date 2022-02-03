package project;
import java.awt.BorderLayout;
import java.math.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class eg 
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
	String[] columns = {"ID","Name","Amount","Date of Borrowing","Interest/Rs.100","Phone Number"};
	String from;
	public void showTableData()
	{
		f1 = new JFrame("Data");
		f1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f1.setLayout(new BorderLayout());
		
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		
		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		String uname = "";
		int id;
		int am;
		float intr;
		Date date;
		BigDecimal phnno;
		try
		{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String name ="postgres";
			String pass = "1";
			con = DriverManager.getConnection(url,name,pass);
			pst = con.prepareStatement("select * from interest");
			ResultSet rs = pst.executeQuery();
			int i=0;
			
			while(rs.next())
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
			if(i<1)
			{
				JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if(i==1)
			{
				System.out.println(i + " Record Found");
			}
			else
			{
				System.out.println(i + " Records Found");
			}
			pst.close();
			con.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		f1.add(scroll);
		f1.setVisible(true);
		f1.setSize(500,300);
	}
	public static void main(String args[])
	{
		eg oj = new eg();
		oj.showTableData();
	}
}
