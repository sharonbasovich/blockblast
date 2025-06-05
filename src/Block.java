package src;

import javax.swing.*;
import java.awt.*;

public class Block extends JPanel {
    private int w;
    private int h;
    private boolean[][] shape;
    private String color;
    private BlockTile[][] blockTiles;
    private int numberOfBlocks;

    public Block(int w, int h, String color, boolean[][] shape) {
        this.w = w;
        this.h = h;
        this.shape = shape;
        this.color = color;
        
        // Initialize BlockTile array
        blockTiles = new BlockTile[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (shape[i][j]) {
                    // Create BlockTile with coordinates relative to this panel
                    blockTiles[i][j] = new BlockTile(j * 40, i * 40, color, true);
                } else {
                    blockTiles[i][j] = null; // No tile for empty spaces
                }
            }
        }
        
        // Force size
        setPreferredSize(new Dimension(w * 40, h * 40));
        setMinimumSize(new Dimension(w * 40, h * 40));
        setMaximumSize(new Dimension(w * 40, h * 40));
        
        // Make visible
        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        // Draw each BlockTile
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (blockTiles[i][j] != null) {
                    blockTiles[i][j].paintComponent(g);
                }
            }
        }
    }
    public int getw() {
        return w;
    }
    
    public void setw(int w) {
        this.w = w;
    }

    public int geth() {
        return h;
    }

    public void seth(int h) {
        this.h = h;
    }

    public boolean[][] getShape() {
        return shape;
    }

    public void setShape(boolean[][] shape) {
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BlockTile[][] getBlockTiles() {
        return blockTiles;
    }

    public void setBlockTiles(BlockTile[][] blockTiles) {
        this.blockTiles = blockTiles;
    }
    public int getNumberOfBlocks() {
        int count = 0;
        for(int i = 0;i<shape.length;i++){
            for(int j = 0;j<shape[0].length;j++){
                if(shape[i][j]) count++;
            }
        }
        return count;
    }
}
