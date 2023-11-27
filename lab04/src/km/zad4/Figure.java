package km.zad4;

import java.awt.*;

class Figure {
    private int figureX = 50;
    private int figureY = 50;
    private final int figureWidth = 50;
    private final int figureHeight = 50;

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(figureX, figureY, figureWidth, figureHeight);
    }

    public void move(int deltaX, int deltaY) {
        figureX += deltaX;
        figureY += deltaY;
    }

    public boolean isFigureAbleToMove(int deltaX, int deltaY, int windowWidth, int windowHeight) {
        return isAbleToMoveX(deltaX, windowWidth) && isAbleToMoveY(deltaY, windowHeight);
    }

    private boolean isAbleToMoveX(int deltaX, int windowWidth) {
        int offsetX = 15;
        return figureX + deltaX >= 0 && figureX + figureWidth + offsetX + deltaX <= windowWidth;
    }

    private boolean isAbleToMoveY(int deltaY, int windowHeight) {
        int offsetY = 36;
        return figureY + deltaY >= 0 && figureY + deltaY + figureHeight + offsetY <= windowHeight;
    }
}
