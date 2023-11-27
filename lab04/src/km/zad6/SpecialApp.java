package km.zad6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SpecialApp extends JFrame {
    public SpecialApp() {
        SpecialDrawingPanel drawingPanel = new SpecialDrawingPanel();
        add(drawingPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SpecialApp().setVisible(true);
            }
        });
    }
}