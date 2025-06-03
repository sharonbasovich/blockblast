import javax.swing.*;
import java.awt.*;

public class Block extends JPanel {
    private int width;
    private int height;
    private boolean[][] shape;
    private BlockTile[][] blockTiles;
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
        blockTiles = new BlockTile[height][width];
        
        // Initialize block tiles
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (shape[i][j]) {
                    blockTiles[i][j] = new BlockTile(j * 40, i * 40, color, true);
                }
            }
        }
        
        // CRITICAL: Set size explicitly
        setPreferredSize(new Dimension(width * 40, height * 40));
        setMinimumSize(new Dimension(width * 40, height * 40));
        setMaximumSize(new Dimension(width * 40, height * 40));
        
        // Force the size to be respected
        
        // Make sure it's visible
        setOpaque(true);
        setBackground(Color.WHITE);
        
        // Use a border to see the component
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
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
        
        // Draw background
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // Draw each tile
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (shape[i][j]) {
                    blockTiles[i][j].paintComponent(g);
                }
            }
        }
    }
}
