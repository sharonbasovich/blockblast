package src;

import javax.swing.*;
import java.awt.*;

public class BlockTile extends JComponent {
    private boolean exists; //check if there should be a tile at this location
    public boolean isExists() {
        return exists;
    }
    //instance variables
    private int x;
    private int y;
    private String color;
    //constructor
    public BlockTile(int x, int y, String color, boolean exists) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.exists = exists;
    }
    //more getters and setters
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
        //if the tile exists, paint this tile and outline
        if (exists) {
            super.paintComponent(g);
            g.setColor(Color.decode(color));
            g.fillRect(x, y, 40, 40);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, 40, 40);
        }
    }
}
