package km.zad2;

import javax.swing.*;
import java.awt.*;

class DrawingPanel extends JPanel {
    private int figureX = 50;
    private int figureY = 50;

    private final int figureWidth = 50;
    private final int figureHeight = 50;

    private final int windowWidth;
    private final int windowHeight;

    public DrawingPanel(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.ORANGE);
        g.fillRect(figureX, figureY, figureWidth, figureHeight);
    }

    public void moveFigure(int deltaX, int deltaY) {
        if (isFigureAbleToMove(deltaX, deltaY)) {
            figureX += deltaX;
            figureY += deltaY;
        }
        repaint();
    }

    private boolean isFigureAbleToMove(int deltaX, int deltaY) {
        return isAbleToMoveX(deltaX) && isAbleToMoveY(deltaY);
    }

    private boolean isAbleToMoveX(int deltaX) {
        int offsetX = 15;
        return figureX + deltaX >= 0 &&  figureX + figureWidth + offsetX + deltaX <= windowWidth;
    }

    private boolean isAbleToMoveY( int deltaY) {
        int offsetY = 36;
        return figureY + deltaY >= 0 && figureY + deltaY + figureHeight + offsetY <= windowHeight;
    }
}
