import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.*;

class ViewExp extends JFrame implements ActionListener
{
    JPanel p1, p2, p3, p4, p6;
    Connection cn;
    JLabel l1;
    JTable table;
	JButton print, back;
    DefaultTableModel tableModel;
    JComboBox<String> cmb;
	DateButton calb,eDate;

    ViewExp() 
	{
        setTitle("Main Form");
        setSize(1550, 1200);
        setLocation(0, 0);
        setLayout(null);

        // Memory Allocation
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p6 = new JPanel();

		table= new JTable();
        l1 = new JLabel("EXPENSE TRACKER..");
		
		print = new JButton("PRINT");
		back = new JButton("BACK");
		
		//Layout
		p1.setLayout(null);
        p2.setLayout(null);
        p3.setLayout(null);
        p4.setLayout(new BorderLayout());
        p6.setLayout(null);

        add(p1);
        add(p3);
        p3.add(p2);
        p3.add(p4);
        p3.add(p6);

        p2.add(l1);

        p1.setBounds(0, 0, 300, 1000);
        p3.setBounds(300, 0, 1300, 850);
        p2.setBounds(0, 0, 1300, 100);
        p4.setBounds(450, 180, 700, 410);
        p6.setBounds(90, 250, 260, 300);

        l1.setBounds(20, 20, 900, 50);
        l1.setFont(new Font("Times New Roman", Font.BOLD, 44));

        p1.setBackground(Color.decode("#DB005B"));
        //p2.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border

        p2.setBackground(new Color(0, 0, 0, 10));
        p4.setBackground(new Color(0, 0, 0, 20));
        p6.setBackground(new Color(0, 0, 0, 20));

        // Load Image
        ImageIcon imageIcon = new ImageIcon("EXP3.jpg");
        JLabel label = new JLabel(imageIcon);
        p3.add(label);
        label.setBounds(0, 0, 1300, 854);

        // Add components for selecting starting date, ending date, and title
        JLabel l1 = new JLabel("FROM :");
        calb = new DateButton();	
        JLabel l2 = new JLabel("TO:");
        eDate = new DateButton();
        JLabel l3 = new JLabel("TITLE :");
        cmb = new JComboBox<>();
        JButton btn = new JButton("SHOW");

        l1.setBounds(20, 40, 80, 30);
        calb.setBounds(90, 40, 150, 30);
        l2.setBounds(20, 90, 80, 30);
        eDate.setBounds(90, 90, 150, 30);
        l3.setBounds(20, 140, 80, 30);
        cmb.setBounds(90, 140, 150, 30);
        btn.setBounds(20, 200, 100, 30);print.setBounds(140, 200, 100, 30);
		back.setBounds(80, 250, 100, 30);

        p6.add(l1);
        p6.add(calb);
        p6.add(l2);
        p6.add(eDate);
        p6.add(l3);
        p6.add(cmb);
        p6.add(btn);
        p6.add(print);	p6.add(back);
        cmb.addItem("All");
		
		print.addActionListener(this);
		back.addActionListener(this);
        
        try 
		{
            cn = DriverManager.getConnection("jdbc:mysql:///Project", "root", "root");

            String sql = "SELECT DISTINCT title FROM expense";
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) 
			{
                cmb.addItem(resultSet.getString("title"));
            }
        } 
		catch (Exception e)
		{
            JOptionPane.showMessageDialog(this, "Failed to connect to database or fetch titles!");
        }

        btn.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                filterExpenses();
            }
        });

        // Initialize table model
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Date");
        tableModel.addColumn("Description");
        tableModel.addColumn("Price");

        // Add table to panel p4
        p4.add(new JScrollPane(table), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
	public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == print) 
		{
            try 
			{
				MessageFormat header = new MessageFormat("JTable Report Print");
				MessageFormat footer = new MessageFormat("Page {0,number,integer}");
				table.print(JTable.PrintMode.NORMAL, header, footer);
			} 
			catch (Exception exp) 
			{
				exp.printStackTrace();
			}
        }
		if (e.getSource() == back) 
		{
			openNewAddExpense();
		}
	}
	
    private void filterExpenses() 
	{
        // Clear previous data
        tableModel.setRowCount(0);

        // Fetch filter values
        String startDate = calb.getText();
        String endDate = eDate.getText();
        String selectedTitle = (String) cmb.getSelectedItem();

        // Fetch data from the database based on filters
        // Replace this placeholder logic with actual data retrieval
        try 
		{
            String sql;
            if (selectedTitle.equals("All")) 
			{
                sql = "SELECT * FROM expense WHERE date BETWEEN ? AND ?";
            } 
			else 
			{
                sql = "SELECT * FROM expense WHERE date BETWEEN ? AND ? AND title = ?";
            }
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);
            if (!selectedTitle.equals("All")) 
			{
                preparedStatement.setString(3, selectedTitle);
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            // Populate table with data
            while (resultSet.next()) 
			{
                Object[] row = 
				{
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getDate("date"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")
                };
                tableModel.addRow(row);
            }
        } 
		catch (Exception e) 
		{
            JOptionPane.showMessageDialog(this, "Failed to filter expenses!");
        }
    }

	private void openNewAddExpense() 
	{
        this.dispose();
        NewAddExpense pc = new NewAddExpense();
        pc.setVisible(true);
    }
	
    public static void main(String[] args)
	{
        SwingUtilities.invokeLater(new Runnable() 
		{
            public void run()
			{
                new ViewExp().setVisible(true);
            }
        });
    }
}
