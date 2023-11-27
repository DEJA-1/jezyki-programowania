package km.zad6;

import java.awt.*;
import java.awt.event.KeyEvent;
class SpecialFigure {
    private Color figureColor = Color.DARK_GRAY;
    private ShapeVariant figureType = ShapeVariant.SQUARE;
    private boolean isKPressed = false;
    private boolean isShiftPressed = false;

    public void draw(Graphics g) {
        g.setColor(figureColor);

        if (figureType == ShapeVariant.SQUARE) {
            g.fillRect(50, 50, 100, 100);
        } else {
            g.fillOval(50, 50, 100, 100);
        }
    }
    public void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_K) {
            isKPressed = true;
            updateFigure();
        } else if (keyCode == KeyEvent.VK_SHIFT) {
            isShiftPressed = true;
            updateFigure();
        }
    }

    public void handleKeyRelease(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_K) {
            isKPressed = false;
            updateFigure();
        } else if (keyCode == KeyEvent.VK_SHIFT) {
            isShiftPressed = false;
            updateFigure();
        }
    }

    private void updateFigure() {
        updateColor();
        updateShape();
    }

    private void updateShape() {
        if (isShiftPressed) {
            figureType = ShapeVariant.OVAL;
        } else {
            figureType = ShapeVariant.SQUARE;
        }
    }

    private void updateColor() {
        if (isKPressed) {
            figureColor = Color.ORANGE;
        } else {
            figureColor = Color.DARK_GRAY;
        }
    }
}

enum ShapeVariant {
    SQUARE, OVAL
}
