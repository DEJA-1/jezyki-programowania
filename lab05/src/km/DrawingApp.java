package km;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DrawingApp extends JFrame {

    private DrawingPanel drawingPanel;
    private final JButton drawButton = new JButton("Rysuj");
    private final JButton blurButton = new JButton("Rozmyj");
    private final JButton colorButton = new JButton("Zmień kolor");
    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DrawingApp().setVisible(true));
    }

    public DrawingApp() {
        setupWindow();
        setupLayout();

        handleButtons();
    }

    private void setupWindow() {
        setTitle("Drawing App");
        setSize(800, 600);
        setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupLayout() {
        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(drawButton);
        buttonPanel.add(blurButton);
        buttonPanel.add(colorButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleButtons() {
        handleDrawButtonClick();
        handleBlurButtonClick();
        handleChangeColorButtonClick();
    }

    private void handleChangeColorButtonClick() {
        colorButton.addActionListener(e -> {
            new Thread(() -> {
                lock.lock();
                try {
                    SwingUtilities.invokeLater(() -> new ChangeColorEffect(drawingPanel).run());
                } finally {
                    lock.unlock();
                }
            }).start();
        });
    }

    private void handleBlurButtonClick() {
        blurButton.addActionListener(e -> {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    SwingUtilities.invokeLater(() -> new BlurEffect(drawingPanel).run());
                } finally {
                    lock.unlock();
                }
            });
            System.out.println(thread);
            thread.start();
        });
    }

    private void handleDrawButtonClick() {
        drawButton.addActionListener(e -> {
            new Thread(() -> {
                lock.lock();
                try {
                    SwingUtilities.invokeLater(() -> drawingPanel.addFigure());
                } finally {
                    lock.unlock();
                }
            }).start();
        });
    }
}




