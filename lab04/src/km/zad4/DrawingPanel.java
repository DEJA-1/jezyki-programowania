package km.zad4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

class DrawingPanel extends JPanel {
    Figure figure = new Figure();
    private final int windowWidth;
    private final int windowHeight;

    public DrawingPanel(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        figure.draw(g);
    }

    public void moveFigure(int deltaX, int deltaY) {
        if (figure.isFigureAbleToMove(deltaX, deltaY, windowWidth, windowHeight)) {
            figure.move(deltaX, deltaY);
        }
        repaint();
    }
}
