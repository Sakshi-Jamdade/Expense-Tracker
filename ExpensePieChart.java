import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.sql.*;

public class ExpensePieChart extends JFrame implements ActionListener
{
    private DefaultPieDataset dataset;
    private JButton back; // Declare back button at the class level

    public ExpensePieChart() 
	{
        setTitle("Expense Pie Chart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel p1 = new JPanel();
        DateButton Fdate = new DateButton();
        DateButton Tdate = new DateButton();
        JButton disp = new JButton("DISPLAY");
        back = new JButton("BACK"); // Initialize back button

        p1.add(new JLabel("From Date (YYYY-MM-DD):"));
        p1.add(Fdate);
        p1.add(new JLabel("To Date (YYYY-MM-DD):"));
        p1.add(Tdate);
        p1.add(disp);
        p1.add(back);

        add(p1, BorderLayout.NORTH);

        dataset = new DefaultPieDataset();
        ChartPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);

        disp.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                show(Fdate.getText(), Tdate.getText());
            }
        });

        back.addActionListener(this);
        setSize(1550, 1200);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == back) 
		{
            openNewAddExpense();
        }
    }

    private void openNewAddExpense() 
	{
        dispose();
        NewAddExpense ae = new NewAddExpense();
        ae.setVisible(true);
    }

    private ChartPanel createChartPanel() 
	{
        JFreeChart chart = ChartFactory.createPieChart(
                "EXPENSE DISTRIBUTION",  // Chart title
                dataset,                // Dataset
                true,                   // Include legend
                true,                   // Include tooltips
                false                   // Do not include URLs
        );

        return new ChartPanel(chart);
    }

    private void show(String fromDate, String toDate) 
	{
        String sql = "SELECT title, price AS total_expense FROM expense WHERE date BETWEEN ? AND ?";

        try 
		{
            Connection connection = DriverManager.getConnection("jdbc:mysql:///Project", "root", "root");
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, fromDate);
            statement.setString(2, toDate);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) 
			{
                String category = resultSet.getString("title");
                double totalExpense = resultSet.getDouble("total_expense");

                if (dataset.getKeys().contains(category)) 
				{
                    double existingValue = dataset.getValue(category).doubleValue();
                    dataset.setValue(category, existingValue + totalExpense);
                } 
				else 
				{
                    dataset.setValue(category, totalExpense);
                }
            }
        } 
		catch (SQLException ex) 
		{
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) 
	{
        SwingUtilities.invokeLater(new Runnable() 
		{
            public void run() 
			{
                new ExpensePieChart().setVisible(true);
            }
        });
    }
}
