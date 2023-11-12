import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculator extends JFrame {

    private final JTextField weightField;
    private final JTextField heightField;

    private final JButton calculateButton;
    private final JLabel resultLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BMICalculator().setVisible(true);
            }
        });
    }
    public BMICalculator() {
        weightField = new JTextField(5);
        heightField = new JTextField(5);
        resultLabel = new JLabel("BMI: ");
        calculateButton = new JButton("Oblicz");

        setWindow();
        setupLayout(calculateButton);
        handleButtonClick(calculateButton);
    }

    private void setWindow() {
        setTitle("Kalkulator BMI");
        int width = 300;
        int height = 150;
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupLayout(JButton calculateButton) {
        setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(calculateButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputPanel.add(new JLabel("Waga (kg): "));
        inputPanel.add(weightField);
        inputPanel.add(new JLabel("Wzrost (m): "));
        inputPanel.add(heightField);

        return inputPanel;
    }

    private void handleButtonClick(JButton calculateButton) {
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());

            double bmi = weight / (height * height);

            resultLabel.setText("BMI: " + String.format("%.2f", bmi));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Wprowadź poprawne wartości liczbowe.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }


}