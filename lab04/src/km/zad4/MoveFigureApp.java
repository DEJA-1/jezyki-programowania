package km.zad4;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

class MoveFigureApp extends JFrame {
    private DrawingPanel drawingPanel;
    private final int width = 400;
    private final int height = 400;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MoveFigureApp().setVisible(true);
            }
        });
    }
    public MoveFigureApp() {
        setupWindow();
        setupLayout();
        handleKeyPressed();
    }

    private void setupWindow() {
        String title = "Move Figure";
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void setupLayout() {
        drawingPanel = new DrawingPanel(width, height);
        add(drawingPanel);
    }

    private void handleKeyPressed() {
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                moveFigure(e);
            }
        });
    }

    private void moveFigure(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                drawingPanel.moveFigure(0, -5);
                break;
            case KeyEvent.VK_DOWN:
                drawingPanel.moveFigure(0, 5);
                break;
            case KeyEvent.VK_LEFT:
                drawingPanel.moveFigure(-5, 0);
                break;
            case KeyEvent.VK_RIGHT:
                drawingPanel.moveFigure(5, 0);
                break;
        }
    }
}