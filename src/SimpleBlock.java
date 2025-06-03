import javax.swing.*;
import java.awt.*;

public class SimpleBlock extends JPanel {
    private int width;
    private int height;
    private boolean[][] shape;
    private String color;
    private BlockTile[][] blockTiles;

    public SimpleBlock(int width, int height, String color, boolean[][] shape) {
        this.width = width;
        this.height = height;
        this.shape = shape;
        this.color = color;
        
        // Initialize BlockTile array
        blockTiles = new BlockTile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (shape[i][j]) {
                    // Create BlockTile with coordinates relative to this panel
                    blockTiles[i][j] = new BlockTile(j * 40, i * 40, color, true);
                } else {
                    blockTiles[i][j] = null; // No tile for empty spaces
                }
            }
        }
        
        // Force size
        setPreferredSize(new Dimension(width * 40, height * 40));
        setMinimumSize(new Dimension(width * 40, height * 40));
        setMaximumSize(new Dimension(width * 40, height * 40));
        
        // Make visible
        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        // Draw each BlockTile
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (blockTiles[i][j] != null) {
                    blockTiles[i][j].paintComponent(g);
                }
            }
        }
    }
}
