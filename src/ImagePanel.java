import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ImagePanel extends JPanel {

    ImageIcon image;
    int IMG_WDTH;
    int IMG_HEIGHT;

    Point image_corner;
    Point previousPoint;

    public ImagePanel(ImageIcon image) {
        this.image = image;
        IMG_WDTH = image.getIconWidth();
        IMG_HEIGHT = image.getIconHeight();
        image_corner = new Point(0, 0);
        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);
        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.paintIcon(this, g, (int) image_corner.getX(), (int) image_corner.getY());
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent evt) {
            previousPoint = evt.getPoint();
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent evt) {
            Point currentPoint = evt.getPoint();
            image_corner.translate((int) (currentPoint.getX() - previousPoint.getX()),
                    (int) (currentPoint.getY() - previousPoint.getY()));
            previousPoint = currentPoint;
            repaint();
        }
    }

}
