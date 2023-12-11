package km;

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
