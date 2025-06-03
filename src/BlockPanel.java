import javax.swing.*;
import java.awt.*;
public class BlockPanel extends JPanel {
    private BlockTile[][] block;
    public BlockTile[][] getBlock() {
        return block;
    }
    public BlockPanel(BlockTile[][] block) {
        this.block = block;
    }
    public void paintComponent(Graphics g) {
        // Draw all tiles
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[i].length; j++) {
                block[i][j].paintComponent(g);
            }
        }
    }
    

}
