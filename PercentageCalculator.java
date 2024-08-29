import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PercentageCalculator extends JFrame {

    private JTextField value1Field, value2Field, resultField;
    private JComboBox<String> calculationTypeBox;
    private JButton calculateButton;

    public PercentageCalculator() {
        
        setTitle("Percentage Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Input fields and labels
        JLabel value1Label = new JLabel("Value 1:");
        value1Field = new JTextField();

        JLabel value2Label = new JLabel("Value 2:");
        value2Field = new JTextField();

        JLabel calculationTypeLabel = new JLabel("Calculation Type:");
        String[] calculationTypes = {"Percentage of Value", "Percentage Increase/Decrease", "Find Whole from Part"};
        calculationTypeBox = new JComboBox<>(calculationTypes);

        // Result field
        JLabel resultLabel = new JLabel("Result:");
        resultField = new JTextField();
        resultField.setEditable(false);

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());

        // Add components to the frame
        add(value1Label);
        add(value1Field);
        add(value2Label);
        add(value2Field);
        add(calculationTypeLabel);
        add(calculationTypeBox);
        add(resultLabel);
        add(resultField);
        add(new JLabel()); 
        add(calculateButton);

        
        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double value1 = Double.parseDouble(value1Field.getText());
                double value2 = Double.parseDouble(value2Field.getText());
                String calculationType = (String) calculationTypeBox.getSelectedItem();
                double result = 0;

                switch (calculationType) {
                    case "Percentage of Value":
                        result = calculatePercentage(value1, value2);
                        break;
                    case "Percentage Increase/Decrease":
                        result = calculatePercentageChange(value1, value2);
                        break;
                    case "Find Whole from Part":
                        result = findWhole(value1, value2);
                        break;
                }

                resultField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers.");
            }
        }
    }

    private double calculatePercentage(double value, double percentage) {
        return (value * percentage) / 100;
    }

    private double calculatePercentageChange(double originalValue, double newValue) {
        return ((newValue - originalValue) / originalValue) * 100;
    }

    private double findWhole(double part, double percentage) {
        return (part * 100) / percentage;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PercentageCalculator());
    }
}
