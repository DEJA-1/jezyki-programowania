package km;

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
