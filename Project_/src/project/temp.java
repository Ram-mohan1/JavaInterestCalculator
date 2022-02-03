package project;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class temp extends JFrame implements ActionListener
{
	JFrame frame1;
    JLabel l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,label;
    JComboBox c1;
    JButton b1,b;
    Connection con;
    ResultSet rs, rs1;
    Statement st, st1;
    JTextField j1,j2,j3,j4,j5,j6,j7,j8;
    PreparedStatement pst,pst1;
    String ids,k;
    static JTable table;
    String from;
    int year1,month1,date1;
    int year2 = 0,month2 = 0, date2 = 0;
    int date_diff, month_diff, year_diff;
    LocalDate dat1;
    LocalDate x;
    float inter,amount,no_of_hnrds, interest_amount,per_day,per_dayx;
    temp()
    {
    	
    	l0 = new JLabel("Select Name to view their particular info!");
    	l0.setForeground(Color.red);
        l0.setFont(new Font("Serif", Font.BOLD, 20));
        l1 = new JLabel("Select name");
        b1 = new JButton("submit");
        
        l0.setBounds(50, 50, 350, 40);
        l1.setBounds(50, 100, 75, 20);
        b1.setBounds(150, 200, 150, 20);
        b1.addActionListener(this);
        
        j2 = new JTextField();
    	j2.setBounds(200,150,25,20);
    	
    	j3 = new JTextField();
    	j3.setBounds(230,150,25,20);
    	
    	j4 = new JTextField();
    	j4.setBounds(260,150,40,20);
    	
    	l3 = new JLabel("To Date (DD/MM/YYYY)");
    	l3.setBounds(50,150,150,20);
    	
        setTitle("Fetching Info From DataBase");

        setLayout(null);

        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        add(l0);
        add(l1);
        add(b1);
        add(l3);
		add(j2);
		add(j3);
		add(j4);
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
			c1.setBounds(150,100,150,20);
			add(c1);
			add(l2);
			
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
        frame1.setLayout(null);
        
        from = (String) c1.getSelectedItem();
        
        try
        {
        	pst = con.prepareStatement("select * from interest where Name='" + from + "'");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) 
            {
            	String da = rs.getString(4);
            	LocalDate dat = LocalDate.parse(da);
            	year2 = dat.getYear();
            	month2 = dat.getMonthValue();
            	date2 = dat.getDayOfMonth();
            	j1 = new JTextField("" + date2 + "/" + month2 + "/"+ year2 + "");
            	j1.setBounds(150,100,150,20);
            	l2 = new JLabel("From Date");
            	l2.setBounds(50,100,100,20);
            	
            	String y = "" + Integer.parseInt(j2.getText()) + "/" + Integer.parseInt(j3.getText()) + "/"+ Integer.parseInt(j4.getText()) + "";
            	System.out.println(y);
            	date1 = Integer.parseInt(j2.getText());
            	month1 = Integer.parseInt(j3.getText());
            	year1 = Integer.parseInt(j4.getText());
            	
            	j5 = new JTextField(y);
            	j5.setBounds(150,150,150,20);
            	l4 = new JLabel("To Date");
            	l4.setBounds(50,150,100,20);
            	
            	l6 = new JLabel("Gap Between " + y + " and " + j1.getText() + " is ");
    			l6.setBounds(50,350,250,20);
    			l6.setVisible(false);
    			
    			/*JPanel p = new JPanel();
    			String strURL = "https://ram-mohan1.github.io/interest_/";
    			label = new JLabel("<html><a href=\" " + strURL + "\"> Click Here for Advanced Interest Calculator</a></html>");
    			frame1.add(label);
    			JEditorPane htmlPane = new JEditorPane();
    			getContentPane().add(BorderLayout.NORTH, p);
    		    getContentPane().add(BorderLayout.CENTER, new JScrollPane(htmlPane));
    		    setBounds(20,200, 500,500);
    		  
    		    label.addMouseListener(new MouseAdapter() {
    		       public void mouseEntered(MouseEvent me) {
    		          label.setCursor(new Cursor(Cursor.HAND_CURSOR));
    		       }
    		       public void mouseExited(MouseEvent me) {
    		          label.setCursor(Cursor.getDefaultCursor());
    		       }
    		       public void mouseClicked(MouseEvent me)
    		       {
    		          System.out.println("Clicked on Label...");
    		          try {
    		               htmlPane.setPage(new URL(strURL));
    		            }
    		            catch(Exception e) {
    		               System.out.println(e);
    		            }
    		       }
    		      });*/
            	Float f = rs.getFloat(5);
            	int amo = rs.getInt(3);
            	
            	j7 = new JTextField(Integer.toString(amo));
            	j7.setBounds(150,250,150,20);
            	
            	b = new JButton("Click");
            	b.setBounds(150,300,150,20);
            	b.addActionListener(new ActionListener()
            	{
            		public void actionPerformed(ActionEvent ae)
            		{
            			l6.setVisible(true);
            			
            			if(valid_date(date1,month1,year1) == 1)
            			{
            				dispose();
            			}
            			if(valid_date(date2,month2,year2) == 1)
            			{
            				dispose();
            			}
            			if(date1 < date2)
            			{
            				if(month1 ==5 || month1 == 7 || month1 ==10 || month1 ==12)
            				{
            					date1 = date1 + 30;
            				}
            				else
            				{
            					date1 = date1 + 30;
            				}
            				month1 = month1 - 1;
            			}
            			if(month1 < month2)
            			{
            				month1 = month1 + 12;
            				year1 = year1 -1;
            			}
            			date_diff = date1 - date2;
            			month_diff = month1 - month2;
            			year_diff = year1- year2;
            			
            			l7 = new JLabel(Integer.toString(year_diff) + " Years, " + Integer.toString(month_diff) + " months, " + Integer.toString(date_diff) + " Days.");
            			l7.setBounds(280,350,250,20);
                    	frame1.add(l7);
                    	amount = amo;
                    	k="";
                    	System.out.println(year_diff + " Years " + month_diff + " Months " + date_diff + " Days.");
                    	for(int i=0; i<year_diff; i++)
                    	{
                    		no_of_hnrds = amount/(float)100;
                    		interest_amount = no_of_hnrds*f*(float)12;
                    		amount = amount + interest_amount;
                    		//k = k + "Amount after  year " + (i+1) + " is " + amount + ".\n";
                    	}
                    	//System.out.println(k);
                    	if(month_diff >= 4)
                    	{
                    		no_of_hnrds = amount/(float)100;
                    		//System.out.println("No_of_hrnds = " + no_of_hnrds);
                    		interest_amount = no_of_hnrds*f*(float)month_diff;
                    		//float xx = no_of_hnrds*f;
                    		//System.out.println("XX is " + xx);
                    		//System.out.println("interest_amount = " + interest_amount);
                    		amount+=interest_amount;
                    		//System.out.println("amount = " + amount);
                    		per_day=interest_amount/((float)month_diff*(float)30);
                    		//System.out.println("per_day = " + per_day);
                    	}
                    	else if(month_diff<4 && month_diff!=0 && year_diff==0)
                        {
                            no_of_hnrds=amount/(float)100;
                            //System.out.println("No_of_hrnds = " + no_of_hnrds);
                            interest_amount=no_of_hnrds*f*(float)month_diff;
                            //System.out.println("F value = "+f);
                            //System.out.println("interest_amount = " + interest_amount);
                            amount=amount+interest_amount;
                            //System.out.println("amount = " + amount);
                            per_day=interest_amount/((float)month_diff*(float)30);
                            //System.out.println("per_day = " + per_day);
                        }
                        else if(month_diff==0)
                        {
                            per_day=interest_amount/((float)30*(float)12);
                            System.out.println("Per_Day = "+ per_day);
                        }
                    	per_dayx = per_day*(float)date_diff;
                    	//System.out.println("per_dayx = " + per_dayx);
                    	amount+=per_dayx;
                    	//System.out.println("amount = " + amount);
                    	inter = amount - amo;
                    	System.out.println("Final Interest is " + inter);
                    	System.out.println("Final amount is " + amount);
                    	l10 = new JLabel("Total Amount with Interest is " + Float.toString(amount) + ". \n Interest is " + inter +"");
                    	l10.setBounds(50,400,400,20);
                    	frame1.add(l10);
            		}
            	});
            	j6 = new JTextField(Float.toString(f));
            	j6.setBounds(150,200,150,20);
            	l5 = new JLabel("Interest");
            	l5.setBounds(50,200,100,20);
            	
            	l8 = new JLabel("Amount");
            	l8.setBounds(50,250,100,20);
            }
        }
        catch(Exception ex)
        {
        	 JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        frame1.setVisible(true);
        frame1.setSize(600, 600);
        frame1.add(l2);
        frame1.add(j1);
        frame1.add(l4);
        frame1.add(j5);
        frame1.add(j6);
        frame1.add(l5);
        frame1.add(b);
        frame1.add(l6);
        frame1.add(l8);
        frame1.add(j7);
        frame1.add(l11);
        frame1.add(l10);
    }
    public int valid_date(int date,int mon,int year)
    {
    	int is_valid = 1;
    	if(year >= 1800 && year <=9999)
    	{
    		if(mon >= 1 && mon <= 12)
    		{
    			is_valid = 1;
    		}
    		else
    		{
    			is_valid = 0;
    		}
    	}
    	else
    	{
    		is_valid = 0;
    	}
		return is_valid;	
    }
    public static void main(String args[])
    {
    	new temp();
    }
}
