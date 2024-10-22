import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;

public class sec_Page extends JFrame implements ActionListener
{
	//1.Declaration
	JTextField t1;
	JLabel l1,l2,l3,l4;
	JButton b1;
	JPanel p1,p2,p3,p4;
	JPasswordField ps;
	private Connection cn;
	 
	sec_Page()
	{
		setTitle("Login");
		setSize(1550,1500);
		setLocation(0,0);
	    setLayout(null);
		
		//2.Memory Allocation
    	p1= new JPanel();
		p2= new JPanel();
		p3= new JPanel();
		p4= new JPanel();
		ps=new JPasswordField();
			
		t1 = new JTextField();
		//t2 = new JTextField();

		l1=new JLabel("EXPENSE TRACKER...");
	    l2=new JLabel("LOG_IN HERE");
		l3=new JLabel("Username");
		l4=new JLabel("Password");
		
		b1= new JButton("LOGIN...");
				
		//Layout
		p1.setLayout(null);
		p2.setLayout(null);
		p3.setLayout(null);
		p4.setLayout(null);
		
		//3.Add
		add(p1);    p1.setBounds(0,0,300,1000);
		
		p3.add(l1);
		l1.setFont(new Font("Times New Roman",Font.BOLD,50));	
		l1.setBounds(20,0,600,100);
		
		add(p3);
		p3.add(p4);
		p3.add(p2);
		
		p4.add(l2);
		p4.add(l3);
		p4.add(l4);
		p4.add(t1);
		p4.add(ps);
		

		Color c=new Color(102,0,153); 
		p1.setBackground(Color.decode("#DB005B"));
		p2.setBackground(new Color(0,0,0,10));
		p4.setBackground(new Color(0,0,0,20));
			  
		p3.setBounds(300,0,1500,1000);
		p2.setBounds(0,0,1500,100);
		p4.setBounds(450,250,350,300);

		l2.setFont(new Font("Times New Roman",Font.BOLD,30));
		l3.setFont(new Font("Times New Roman",Font.BOLD,18));
		l4.setFont(new Font("Times New Roman",Font.BOLD,18));
		
		l2.setBounds(30,20,300,50);
		l3.setBounds(30,90,100,25);   
		t1.setBounds(30,120,280,30);
		l4.setBounds(30,160,100,25);   
		ps.setBounds(30,190,280,30);
		

		p4.add(b1);
		b1.setBounds(30,250,280,30);
	
        // Load Image
        ImageIcon imageIcon = new ImageIcon("EXP3.jpg");

        // Create the JLabel to hold the image
        JLabel label = new JLabel(imageIcon);
        p3.add(label);
		label.setBounds(0,0,1300,854);
		
		b1.addActionListener(this);
		
		try
		{
			cn=DriverManager.getConnection("jdbc:mysql:///Project","root","root");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "Failed to connect to database!");
		}
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == b1) 
		{
            String username = t1.getText();
            String password = new String(ps.getPassword());
            try
			{
                String query = "SELECT * FROM register WHERE username=? AND password=?";
                PreparedStatement statement = cn.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next())
					{
                    JOptionPane.showMessageDialog(this, "Login Successfull!");
                    openAddExpense();
                    } 
					else
					{
                    JOptionPane.showMessageDialog(this, "Invalid username or password!");
                    }
                resultSet.close();
                statement.close();
				
            } 
			catch (SQLException ex)
			{
                ex.printStackTrace();
            }
        }
	}
	private void openAddExpense()
	{
        // Close the current login frame
        this.dispose();

        // Open the main form
        NewAddExpense mainForm = new NewAddExpense();
        mainForm.setVisible(true);
    }
	
	public static void main(String[] args)
	{
        SwingUtilities.invokeLater(new Runnable() 
		{
            public void run()
			{
                new sec_Page().setVisible(true);
            }
        });
    }
}
