package km.zad6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
class SpecialDrawingPanel extends JPanel {
    private final SpecialFigure specialFigure = new SpecialFigure();

    public SpecialDrawingPanel() {
        handleKeyInput();

        setFocusable(true);
    }

    private void handleKeyInput() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                specialFigure.handleKeyPress(e);
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                specialFigure.handleKeyRelease(e);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        specialFigure.draw(g);
    }
}