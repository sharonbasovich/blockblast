package src;

import javax.swing.*;
import java.awt.*;

public class BlockTile extends JComponent {
    private boolean exists;
    public boolean isExists() {
        return exists;
    }

    private int x;
    private int y;
    private String color;

    public BlockTile(int x, int y, String color, boolean exists) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.exists = exists;
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
        if (exists) {
            super.paintComponent(g);
            g.setColor(Color.decode(color));
            g.fillRect(x, y, 40, 40);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, 40, 40);
        }
        else{
            // g.setColor(new Color(0,0,0,0));
            
            // g.fillRect(x, y, 40, 40);
        }
    }
}
