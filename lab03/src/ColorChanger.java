import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChanger extends JFrame {

    private final JTextField colorInputField;
    private final JButton changeColorButton;
    private final JPanel colorPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ColorChanger().setVisible(true);
            }
        });
    }

    public ColorChanger() {
        setWindow();

        colorInputField = createInputField();
        changeColorButton = new JButton("Zmień Kolor");
        colorPanel = new JPanel();

        setupLayout();
        handleButtonClick();
    }

    private void setWindow() {
        setTitle("Color change");
        int width = 600;
        int height = 300;
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        colorPanel.setBackground(Color.WHITE);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Wprowadź nazwę koloru: "));
        inputPanel.add(colorInputField);
        inputPanel.add(changeColorButton);

        add(inputPanel, BorderLayout.NORTH);
        add(colorPanel, BorderLayout.CENTER);
    }

    private JTextField createInputField() {
        final JTextField colorInputField;
        colorInputField = new JTextField();
        colorInputField.setColumns(10);
        return colorInputField;
    }

    private void handleButtonClick() {
        changeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeBackgroundColor();
            }
        });
    }

    private void changeBackgroundColor() {
        try {
            Color colorToSet = getColor();
            colorPanel.setBackground(colorToSet);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Nieprawidłowa nazwa koloru!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }

    }

    private  Color getColor() {
        String colorName = colorInputField.getText().toLowerCase();
        Color colorToSet;
        ColorVariant colorVariant = ColorVariant.valueOf(colorName.toUpperCase());
        switch (colorVariant) {
            case CZERWONY:
                colorToSet = Color.RED;
                break;
            case ZIELONY:
                colorToSet = Color.GREEN;
                break;
            case NIEBIESKI:
                colorToSet = Color.BLUE;
                break;
            case POMARANCZOW:
                colorToSet = Color.ORANGE;
                break;
            case SZARY:
                colorToSet = Color.GRAY;
                break;
            default:
                colorToSet = Color.BLACK;
                break;
        }
        return colorToSet;
    }

    enum ColorVariant {
        CZERWONY,
        ZIELONY,
        NIEBIESKI,
        POMARANCZOW,
        SZARY;
    }

}