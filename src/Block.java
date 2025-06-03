import javax.swing.*;
import java.awt.*;

public class Block extends JPanel {
    private int width;
    private int height;
    private boolean[][] shape;
    private String color;

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public Block(int width, int height, String color, boolean[][] shape) {
        this.width = width;
        this.height = height;
        this.shape = shape;
        this.color = color;
        this.setBounds(100, 100, 100, 100);
        this.setBackground(Color.black);
        this.setOpaque(true);
        this.setVisible(true);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean[][] getShape() {
        return shape;
    }

    public void setShape(boolean[][] shape) {
        this.shape = shape;
    }

    public void setwidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        boolean special = false;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (shape[i][j] == true) {
                    if (special == false) {
                        int x = j * 40;
                        int y = i * 40;
                        g.setColor(Color.decode(color));
                        g.fillRect(x, y, 40, 40);
                        g.setColor(Color.BLACK);
                        g.drawRect(x, y, 40, 40);

                        // BlockTile blockTile = new BlockTile((j + 1) * 40, (i + 1) * 40, color, true);
                        // blockTile.paintComponent(g);
                        special = true;
                    } else {
                         int x = j * 40;
                        int y = i * 40;
                        g.setColor(Color.decode(color));
                        g.fillRect(x, y, 40, 40);
                        g.setColor(Color.BLACK);
                        g.drawRect(x, y, 40, 40);

                        // BlockTile blockTile = new BlockTile((j + 1) * 40, (i + 1) * 40, color, true);
                        // blockTile.paintComponent(g);
                    }

                }
            }
        }
    }
}
