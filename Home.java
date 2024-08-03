import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Home extends JFrame implements ActionListener
{
	//1.Declaration
	JTextField t1,t2,t3;
	JLabel l1,l2,l3,l4,l5,l6;
	JButton b1,b2;
	JPanel p1,p2,p3,p4,p5;
	Connection cn;
	 
	Home()
	{
		setTitle("Login");
		setSize(1550,1200);
		setLocation(0,0);
	    setLayout(null);
		
		//2.Memory Allocation
    	p1= new JPanel();
		p2= new JPanel();
		p3= new JPanel();
		p4= new JPanel();
		p5= new JPanel();
			
		t1 = new JTextField();
		t2 = new JTextField();
		t3 = new JTextField();
		
		l1=new JLabel("EXPENSE TRACKER...");
		
		l2=new JLabel("REGISTER HERE...");
		l3=new JLabel("Username :");
		l4=new JLabel("Email :");
		l5=new JLabel("Password :");
		l6=new JLabel("Already Have An Account?Log-in..");
		//l7=new JLabel("Log-In..");
		
		b1= new JButton("REGISTER");
		b2= new JButton("LOGIN");

				
		//Layout
		p1.setLayout(null);
		p2.setLayout(null);
		p3.setLayout(null);
		p4.setLayout(null);
		p5.setLayout(null);
		
		//3.Add
		add(p1);   

		
		//add(p2);
		p3.add(l1);
		l1.setFont(new Font("Times New Roman",Font.BOLD,50));	
		l1.setBounds(20,0,1200,100);
		
		add(p3);
		p3.add(p4);
		p3.add(p2);
		
		p4.add(l2);
		p4.add(l3);
		p4.add(l4);
		p4.add(l5);
		
		p4.add(t1);
		p4.add(t2);
		p4.add(t3);
		p4.add(l6);
		//p4.add(l7);
		
		add(p5);p4.add(b2);

		Color c=new Color(102,0,153); 
		p1.setBackground(Color.decode("#DB005B"));
		p2.setBackground(new Color(0,0,0,10));
		p4.setBackground(new Color(0,0,0,20));
		
		//b1.setBackground(Color.decode("#FBF7F7"));
			  
		p1.setBounds(0,0,300,1000);
		p3.setBounds(300,0,1300,850);
		p2.setBounds(0,0,1300,100);
		p4.setBounds(375,200,440,410);

		l2.setFont(new Font("Times New Roman",Font.BOLD,30));
		l3.setFont(new Font("Times New Roman",Font.BOLD,18));
		l4.setFont(new Font("Times New Roman",Font.BOLD,18));
		l5.setFont(new Font("Times New Roman",Font.BOLD,18));
		l6.setFont(new Font("Times New Roman",Font.BOLD,16));
		//l7.setFont(new Font("Times New Roman",Font.BOLD,14));
		
		l2.setBounds(30,20,300,50);
		l3.setBounds(30,80,150,25);  
		t1.setBounds(30,110,380,30);
		l4.setBounds(30,150,150,25);   
		t2.setBounds(30,180,380,30);
		l5.setBounds(30,220,150,25);
		t3.setBounds(30,250,380,30);

		p4.add(b1);
		b1.setBounds(30,300,380,35);
		b2.setBounds(270,350,80,25);
		l6.setBounds(60,350,200,25);
		//l7.setBounds(320,375,70,25);
	
        // Load Image
        ImageIcon imageIcon = new ImageIcon("EXP3.jpg");

        // Create the JLabel to hold the image
        JLabel label = new JLabel(imageIcon);
        p3.add(label);
		label.setBounds(0,0,1300,854);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
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
		if(e.getSource() == b1) 
		{
			String username= t1.getText();
			String Email= t2.getText();
			String password= t3.getText();
			if (isValidEmail(Email) && isValidPassword(password))
			{
				try 
				{
					PreparedStatement preparedStatement = cn.prepareStatement("INSERT INTO register (username,Email,password) VALUES (?, ?, ?)");
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, Email);
					preparedStatement.setString(3, password);

					int rowsAffected = preparedStatement.executeUpdate();
						
					if (rowsAffected > 0) 
					{
						JOptionPane.showMessageDialog(this, "Data inserted successfully");
						t1.setText("");
						t2.setText("");
						t3.setText("");
						openLogin();
					}
				} 
				catch (SQLException ex) 
				{
						
				}
		    }	
			else 
			{
				JOptionPane.showMessageDialog(this, "Password Must Be Of 8 Characters");
			}
		}
		if(e.getSource() == b2)
		{
			openLogin();
		}
	}
	
	// Email validation method using regex
	private boolean isValidEmail(String email)
	{
		String emailRegex = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private boolean isValidPassword(String password) 
	{
		String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{6,}$";
		return password.matches(passwordRegex);
	}

	private void openLogin()
	{
        // Close the current login frame
        this.dispose();

        // Open the main form
        sec_Page mainForm = new sec_Page();
        mainForm.setVisible(true);
    }
	 public static void main(String[] args)
	{
        SwingUtilities.invokeLater(new Runnable() 
		{
            public void run()
			{
                new Home().setVisible(true);
            }
        });
    }
}

