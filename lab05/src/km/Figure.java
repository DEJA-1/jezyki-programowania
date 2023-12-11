package km;

import java.awt.*;

class Figure {
    private final Shape figure;
    private Color color;

    public Figure(Shape figure, Color color, float alpha) {
        this.figure = figure;
        this.color = color;
    }

    public Shape getFigure() {
        return figure;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}