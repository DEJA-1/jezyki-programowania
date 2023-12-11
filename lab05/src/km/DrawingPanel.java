package km;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

class DrawingPanel extends JPanel {
    private ArrayList<Figure> figures = new ArrayList<>();

    public void addFigure() {
        int width = 100;
        int height = 100;
        Shape figure;

        int x = (int) (Math.random() * (getWidth() - width));
        int y = (int) (Math.random() * (getHeight() - height));

        if (figures.size() % 2 == 0) {
            figure = new Ellipse2D.Double(x, y, width, height);
        } else {
            figure = new Rectangle2D.Double(x, y, width, height);
        }

        Color color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
        figures.add(new Figure(figure, color, 1.0f));

        SwingUtilities.invokeLater(() -> repaint());
    }

    public void blurFigure() {
        if (figures.size() > 0) {
            Graphics2D g2d = (Graphics2D) getGraphics();
            int blurIterations = 40;
            for (int i = 0; i < blurIterations; i++) {
                float alpha = (float) i / blurIterations;
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                g2d.setColor(getBackground());
                for (Figure figure : figures) {
                    g2d.setColor(getBackground());
                    g2d.fill(figure.getFigure());
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        repaint();
    }

    public void changeColor() {
        for (Figure figure : figures) {
            figure.setColor(new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Figure figure : figures) {
            g2d.setColor(figure.getColor());
            g2d.fill(figure.getFigure());
        }
    }
}
