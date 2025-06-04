import javax.swing.*;
import java.awt.*;

public class BoardTile extends JComponent {

    private int x;
    private int y;
    private String color;
    private boolean isOccupied;

    public BoardTile(int x, int y, boolean occupied, String color) {
        this.x = x;
        this.y = y;
        this.isOccupied = occupied;
        this.color = color;

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (color == null) {
            g.setColor(Color.BLUE);

            g.fillRect(x, y, 40, 40);
        } else {
            g.setColor(Color.decode(color));
            g.fillRect(x, y, 40, 40);
        }

        g.setColor(Color.BLACK);
        g.drawRect(x, y, 40, 40);
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
