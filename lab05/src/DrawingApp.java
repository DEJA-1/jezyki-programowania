import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
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

class BlurEffect implements Runnable {
    private DrawingPanel drawingPanel;

    public BlurEffect(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void run() {
        drawingPanel.blurFigure();
    }
}

class ChangeColorEffect implements Runnable {

    private DrawingPanel drawingPanel;

    public ChangeColorEffect(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void run() {
        drawingPanel.changeColor();
    }
}