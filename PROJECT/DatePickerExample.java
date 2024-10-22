import javax.swing.*;
import java.awt.*;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class DatePickerExample extends JFrame
{
    private JLabel selectedDateLabel;

    public DatePickerExample() 
	{
        setTitle("Date Picker Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new FlowLayout());

        // Create a JDateChooser component
        JDateChooser dateChooser = new JDateChooser();

        // Create a label to display the selected date
        selectedDateLabel = new JLabel("Selected Date: ");

        // Add an action listener to the dateChooser to update the label
        dateChooser.getDateEditor().addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                Date selectedDate = dateChooser.getDate();
                if (selectedDate != null) {
                    selectedDateLabel.setText("Selected Date: " + selectedDate);
                } else {
                    selectedDateLabel.setText("Selected Date: ");
                }
            }
        });

        // Add components to the frame
        add(dateChooser);
        add(selectedDateLabel);
		setVisible(true);
    }

    public static void main(String[] args)
	{
        
            new DatePickerExample();
    
    }
}
