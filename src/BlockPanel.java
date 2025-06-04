import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BlockPanel extends JPanel {

    Block block;
    int IMG_WDTH;
    int IMG_HEIGHT;

    Point block_corner;
    Point previousPoint;

    public BlockPanel(Block block) {
        this.block = block;
        IMG_WDTH = block.getw() * 40;
        IMG_HEIGHT = block.geth() * 40;
        block_corner = new Point(0, 0);
        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);
        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(block_corner.x, block_corner.y);

        // Paint the block at the translated position
        block.paintComponent(g2d);

        // Dispose of the graphics context
        g2d.dispose();
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent evt) {
            previousPoint = evt.getPoint();
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent evt) {
            Point currentPoint = evt.getPoint();
            block_corner.translate((int) (currentPoint.getX() - previousPoint.getX()),
                    (int) (currentPoint.getY() - previousPoint.getY()));
            previousPoint = currentPoint;
            revalidate();
            repaint();
        }
    }

}
