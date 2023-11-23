package km.zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App extends JFrame {
    private JTextField textField;
    private JPanel drawingPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    public App() {
        initializeComponents();
        setupWindow();
        setupLayout();
        handleMouseClick();
        handleEnterClick();
    }

    private void setupWindow() {
        String title = "App";
        int width = 400;
        int height = 300;

        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        textField = new JTextField();
        drawingPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        add(textField, BorderLayout.SOUTH);
        add(drawingPanel, BorderLayout.CENTER);
    }

    private void handleMouseClick() {
        drawingPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                textField.setText("Kliknięcie w: (" + x + ", " + y + ")");
            }
        });
    }

    private void handleEnterClick() {
        textField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField.setText("");
                }
            }
        });
    }
}