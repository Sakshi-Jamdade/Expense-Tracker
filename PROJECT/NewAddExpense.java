import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.MessageFormat;
import javax.swing.JTable;
import java.text.*;

public class NewAddExpense extends JFrame implements ActionListener 
{
    JTextField t4, t5, cat,Id, Category;
    JComboBox<String> cmb;
    JLabel l, l0, l1, l2, l3, l4, l5, delCat, id, category;
    JButton btn, reg, log, AddCat, print, graph, Add, Back, delete, view, del;
    JPanel p1, p2, p3, p4, p5, p6, p7, p8;
    Connection cn;
    JTable table;
	JLabel Cat;
	DateButton calb;

    NewAddExpense() 
	{
        setTitle("Main Form");
        setSize(1550, 1200);
        setLocation(0, 0);
        setLayout(null);

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
		p5 = new JPanel();
		p6 = new JPanel();
		p7 = new JPanel();
		p8 = new JPanel();

		
        l = new JLabel("EXPENSE TRACKER...");
        l0 = new JLabel("ADD EXPENSE...");
        l1 = new JLabel("Title :");
		l2 = new JLabel("Date :");
        l4 = new JLabel("Description :");
        l5 = new JLabel("Price :");
		table=new JTable();
			
        delCat = new JLabel("DELETE CATEGORY");
        id = new JLabel("ID :");
        category = new JLabel("CATEGORY :");

		Cat = new JLabel("ADD NEW CATEGORY :");
		cat = new JTextField();
		
        cmb = new JComboBox<>();
        calb = new DateButton();
        t4 = new JTextField();
        t5 = new JTextField();
		Id = new JTextField();
        Category = new JTextField();
		
		reg = new JButton("EXPENSETABLE");
        log = new JButton("LOGOUT");
        btn = new JButton("ADD");
		AddCat = new JButton("ADDCATEGORY");
        print = new JButton("CATEGORY");
        graph = new JButton("PIECHART");
		Add = new JButton("ADD");
		Back = new JButton("BACK");
		delete = new JButton("DELETECATEGORY");
		view = new JButton("VIEWCATEGORY");

		del = new JButton("DELETE");

        p2.setLayout(null);
        p3.setLayout(null);
        p4.setLayout(null);
		p5.setLayout(null);
		p6.setLayout(null);
		p7.setLayout(null);
		p8.setLayout(null);


        l.setFont(new Font("Times New Roman", Font.BOLD, 50));
        l.setBounds(20, 0, 600, 100);

        p2.add(l);
        add(p1);
        add(p3);
        p3.add(p2);
        p3.add(p4);
		p3.add(p5);

        p4.add(l0);
        p4.add(l1);
        p4.add(cmb);
		p4.add(l2);
		p4.add(calb);
        p4.add(l4);
        p4.add(t4);
        p4.add(l5);
        p4.add(t5);
        p4.add(btn);
        
		p5.add(AddCat);
		p5.add(reg);
		p5.add(graph);
		p5.add(print);
		p5.add(log);
		p3.add(p6);
		p3.add(p7);
		
		p3.add(p8);
		p6.add(Cat);
		p6.add(cat);
		p6.add(Add);
		p7.add(Back);
		p7.add(delete);
		p7.add(view);
		
		p8.add(delCat);p8.add(del);
		p8.add(id);
		p8.add(category);
		p8.add(Id);
		p8.add(Category);
		
		p6.setVisible(false);
		p7.setVisible(false);
		p8.setVisible(false);


        btn.addActionListener(this);
        reg.addActionListener(this);
        log.addActionListener(this);
		AddCat.addActionListener(this);
        print.addActionListener(this);
        graph.addActionListener(this);
		Add.addActionListener(this);
		delete.addActionListener(this);
		del.addActionListener(this);
		view.addActionListener(this);
		Back.addActionListener(this);
		

        p1.setBackground(Color.decode("#DB005B"));
        p2.setBackground(new Color(0, 0, 0, 10));
        p4.setBackground(new Color(0, 0, 0, 20));
		p5.setBackground(new Color(0, 0, 0, 20));
		p6.setBackground(new Color(0, 0, 0, 20));
		p7.setBackground(new Color(0, 0, 0, 20));
		p7.setBackground(new Color(0, 0, 0, 20));
		p8.setBackground(new Color(0, 0, 0, 20));

        p1.setBounds(0, 0, 300, 1000);
        p3.setBounds(300, 0, 1300, 850);
        p2.setBounds(0, 0, 1300, 100);
        p4.setBounds(270, 220, 500, 390);
		p6.setBounds(400, 300, 350, 220);		p7.setBounds(780, 300, 150, 220);
		p5.setBounds(800, 220, 160, 390);		p8.setBounds(400, 300, 350, 220);		
	
		
        l0.setBounds(50, 20, 400, 50);
        l0.setFont(new Font("Times New Roman", Font.BOLD, 32));
        l1.setBounds(50, 110, 200, 30);
        cmb.setBounds(240, 110, 210, 30);
        l1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        l2.setBounds(50, 160, 200, 30);
        calb.setBounds(240, 160, 210, 30);
		l2.setFont(new Font("Times New Roman", Font.BOLD, 18));
        l4.setFont(new Font("Times New Roman", Font.BOLD, 18));
        l4.setBounds(50, 210, 200, 30);
        t4.setBounds(240, 210, 210, 30);
        l5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		l5.setBounds(50, 260, 200, 30);
        t5.setBounds(240, 260, 210, 30);
        btn.setBounds(50, 320, 400, 30);
		
		Cat.setBounds(20,40,300,30);
		Cat.setFont(new Font("Times New Roman", Font.BOLD, 24));
		cat.setBounds(60,80,230,30);
		Add.setBounds(120,150,110,30);
		
		delCat.setBounds(30,20,300,30);
		id.setBounds(30,70,100,30);        		Id.setBounds(150,70,150,30);
		category.setBounds(30,120,100,30);      Category.setBounds(150,120,150,30);
		del.setBounds(120,170,100,30);
		
		category.setFont(new Font("Times New Roman", Font.BOLD, 16));
		id.setFont(new Font("Times New Roman", Font.BOLD, 16));
		delCat.setFont(new Font("Times New Roman", Font.BOLD, 24));
		
		Back.setBounds(25,50,100,30);
		delete.setBounds(25,100,100,30);
		view.setBounds(25,150,100,30);

		AddCat.setBounds(20,110,120,30);
		reg.setBounds(20,160,120,30);
		graph.setBounds(20,210,120,30);
		print.setBounds(20,260,120,30);
		log.setBounds(20,320,120,30);
		

        addCategoriesToComboBox();

        ImageIcon imageIcon = new ImageIcon("EXP3.jpg");
        JLabel label = new JLabel(imageIcon);
        p3.add(label);
        label.setBounds(0, 0, 1300, 854);

        try 
		{
            cn = DriverManager.getConnection("jdbc:mysql:///Project", "root", "root");
        }
		catch (Exception e) 
		{
            JOptionPane.showMessageDialog(this, "Failed to connect to database!");
        }
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addCategoriesToComboBox() 
	{
        try 
		{
            cn = DriverManager.getConnection("jdbc:mysql:///Project", "root", "root");
            String query = "SELECT name FROM categories";
            PreparedStatement statement = cn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) 
			{
                cmb.addItem(resultSet.getString("name"));
            }

            resultSet.close();
            statement.close();
        } 
		catch (SQLException e) 
		{
            JOptionPane.showMessageDialog(this, "Failed to fetch categories from the database!");
            e.printStackTrace();
        }
    }

	private void addExpenseCategory() 
	{
        String category = cat.getText().trim();
        if (category.isEmpty()) 
		{
            JOptionPane.showMessageDialog(this, "Please enter a category.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try 
		{
            PreparedStatement statement = cn.prepareStatement("INSERT INTO categories (name) VALUES (?)");
            statement.setString(1, category);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) 
			{
                JOptionPane.showMessageDialog(this, "Category added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                cat.setText("");
            } 
			else 
			{
                JOptionPane.showMessageDialog(this, "Failed to add category.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            statement.close();
        } 
		catch (SQLException ex) 
		{
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	/************************************************************************************************************************************/
	
    public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == btn) 
		{
            addexp();
        }

        if (e.getSource() == reg) 
		{
            openViewExp();
        }
        if (e.getSource() == log) 
		{
            openLoginPage();
        }
		if (e.getSource() == graph) 
		{
            openPieChart();
        }
		if (e.getSource() == print) 
		{
			displayCategoriesTable();
        }
		if (e.getSource() == AddCat) 
		{
			p4.setVisible(false);
			p5.setVisible(false);
            p6.setVisible(true);
			p7.setVisible(true);
			
        }
		if (e.getSource() == Add) 
		{
			addExpenseCategory();
			addCategoriesToComboBox();
        }
		if (e.getSource() == Back) 
		{
			p8.setVisible(false);
			p6.setVisible(false);
			p7.setVisible(false);
			p4.setVisible(true);
			p5.setVisible(true);
        }
		if (e.getSource() == delete) 
		{
			p8.setVisible(true);
			p7.setVisible(true);
			p6.setVisible(false);
			p4.setVisible(false);
			p5.setVisible(false);
        }
		if (e.getSource() == view) 
		{
			displayCategoriesTable();
		}
		if (e.getSource() == del) 
		{
			p8.setVisible(true);
			p7.setVisible(true);
			p6.setVisible(false);
			p4.setVisible(false);
			p5.setVisible(false);
					
			String categoryId = Id.getText().trim();
			try 
			{
				PreparedStatement fetchStmt = cn.prepareStatement("SELECT name FROM categories WHERE id = ?");
				fetchStmt.setString(1, categoryId);
				ResultSet resultSet = fetchStmt.executeQuery();

				if (resultSet.next()) 
				{
					Category.setText(resultSet.getString("name"));
					
					int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this category?", "Confirmation", JOptionPane.YES_NO_OPTION);
					
					if (option == JOptionPane.YES_OPTION) 
					{
						
						PreparedStatement deleteStmt = cn.prepareStatement("DELETE FROM categories WHERE id = ?");
						deleteStmt.setString(1, categoryId);
						int rowsAffected = deleteStmt.executeUpdate();
						if (rowsAffected > 0) 
						{
							JOptionPane.showMessageDialog(this, "Category deleted successfully.");
							Category.setText("");
							Id.setText("");
						} 
						else 
						{
							JOptionPane.showMessageDialog(this, "Failed to delete category.");
						}
						deleteStmt.close();
					}
				} 
				else 
				{
					// If no category with the provided ID is found, display a message
					Category.setText("");
					JOptionPane.showMessageDialog(this, "Category not found.");
				}

				resultSet.close();
				fetchStmt.close();
			} 
			catch (SQLException ex) 
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
			}
		}
	}

    /*********************************************************************************************************************************/
    private void addexp() 
	{
        String title = cmb.getSelectedItem().toString();
        String description = t4.getText();
        double price = Double.parseDouble(t5.getText());

        try 
		{
            PreparedStatement preparedStatement = cn.prepareStatement("INSERT INTO expense (title, date, description, price) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, calb.getText()); // Use current date
            preparedStatement.setString(3, description);
            preparedStatement.setDouble(4, price);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) 
			{
                JOptionPane.showMessageDialog(this, "Data inserted successfully");
                t4.setText("");
                t5.setText("");
				openViewExp();
            }
        } 
		catch (SQLException ex) 
		{
            ex.printStackTrace();
        }
    }
	
	private void displayCategoriesTable() 
	{
		try 
		{
			// Fetch data from the categories table
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM categories");

			// Create a DefaultTableModel to hold the data
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("ID");
			model.addColumn("Name");

			// Populate the model with data from the result set
			while (rs.next()) 
			{
				model.addRow(new Object[]{rs.getInt("id"), rs.getString("name")});
			}

			JTable table = new JTable(model);

			JScrollPane scrollPane = new JScrollPane(table);

			JOptionPane.showMessageDialog(this, scrollPane, "Categories", JOptionPane.PLAIN_MESSAGE);

			rs.close();
			stmt.close();
		} 
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}

    /*private void openRegisterPage() 
	{
        this.dispose();
        ViewExp home = new Home();
        home.setVisible(true);
    }*/
	
	private void openPieChart() 
	{
        this.dispose();
        ExpensePieChart pc = new ExpensePieChart();
        pc.setVisible(true);
    }
	
	private void openLoginPage() 
	{
        this.dispose();
        sec_Page sp = new sec_Page();
        sp.setVisible(true);
    }
	
	
	private void openViewExp() 
	{
        this.dispose();
        ViewExp ve = new ViewExp();
        ve.setVisible(true);
    }
	
    public static void main(String[] args)
	{
        SwingUtilities.invokeLater(new Runnable() 
		{
            public void run()
			{
                new NewAddExpense().setVisible(true);
            }
        });
    }
}
