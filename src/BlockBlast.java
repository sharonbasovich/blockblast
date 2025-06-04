import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BlockBlast {
    public static void init(Game game, JFrame frame) {
        JLayeredPane layeredPane = new JLayeredPane();
        // Add board panel
        BoardPanel boardPanel = new BoardPanel(game.getBoard());
        boardPanel.setBounds(30, 100, 325, 500);
        layeredPane.add(boardPanel, JLayeredPane.DEFAULT_LAYER);

        Block block = new Block(3, 2, "#ff0000", new boolean[][] { { true, true, true }, { true, true, true } });
        block.setBounds(5, 520, 3 * 40, 2 * 40); 
        makeDraggable(block, frame, layeredPane, game);
        layeredPane.add(block, JLayeredPane.DRAG_LAYER);

        Block block2 = new Block(1, 2, "#fff000", new boolean[][] { {true}, {true}});
        block2.setBounds(200, 520, 1 * 40, 2 * 40); 
        makeDraggable(block2, frame, layeredPane, game);
        layeredPane.add(block2, JLayeredPane.DRAG_LAYER);

        Block block3 = new Block(3, 1, "#ffff00", new boolean[][] {{ true, true, true}});
        block3.setBounds(250, 520, 3 * 40, 1 * 40); 
        makeDraggable(block3, frame, layeredPane, game);
        layeredPane.add(block3, JLayeredPane.DRAG_LAYER);
        
        
        layeredPane.setBounds(0, 0, 400, 800);
        frame.add(layeredPane);
    }

    public static void setupFrame(JFrame frame) {
        frame.setTitle("Block Blast - Sharon and Yichen"); // set name of app
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit program when closing window
        frame.setResizable(false); // prevent resizing
        frame.setSize(400, 800); // set dimensions of window
        frame.setLayout(null);
        // set app icon
        ImageIcon icon = new ImageIcon("cpt/logo.png");
        frame.setIconImage(icon.getImage());

        // set background color
        frame.getContentPane().setBackground(new Color(0x1559c1));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        setupFrame(frame);
        Game game = new Game();

        // Score
        JLabel score = new JLabel();
        score.setText("Score: " + String.valueOf(game.getScore()));
        score.setBounds(10, 10, 200, 30); // x, y, width, height
        score.setForeground(Color.WHITE); // Make text visible on blue background
        score.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(score);

        init(game, frame);

        // render the frame
        frame.setVisible(true); // make frame visible
    }

    private static void makeDraggable(Block block, JFrame frame, JLayeredPane layeredPane, Game game) {
        MouseAdapter adapter = new MouseAdapter() {
            Point startPoint;

            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point location = block.getLocation();
                int x = location.x + e.getX() - startPoint.x;
                int y = location.y + e.getY() - startPoint.y;
                block.setLocation(x, y);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Point location = block.getLocation();
                onRelease(block, frame, layeredPane, game, location);
            }
        };

        block.addMouseListener(adapter);
        block.addMouseMotionListener(adapter);
    }

    public static void onRelease(Block block, JFrame frame, JLayeredPane layeredPane, Game game, Point location) {
        System.out.println(30+(8-block.getw()+1)*40);
        System.out.println(100+(8-block.geth()+1)*40);
        if (location.x > 30 && location.x < 30+(8-block.getw()+1)*40 && location.y > 100 && location.y < 100+(8-block.geth()+1)*40) {
            System.out.println("Inside valid drop zone!");
            // Your code for handling valid drop

            // For debugging, let's highlight where we think the block should go
            int x = (int) (location.x - 30) / 40;
            int y = (int) (location.y - 100) / 40;
            System.out.println("x: " + x + " y: " + y);
            // More debugging for the block dimensions
            int w = block.getWidth() / 40;
            int h = block.getHeight() / 40;
            if (game.BlockFits(block, x, y) == true) {
                for (int i = 0; i < w; i++) {
                    for (int j = 0; j < h; j++) {
                        game.getBoard()[x + i][y + j].setColor(block.getColor());

                    }
                    game.placeBlock(block, x, y);
                    layeredPane.remove(block);
                    frame.revalidate();
                    frame.repaint();
                }
            } else {
                System.out.println("Outside valid drop zone, returning to home position");
                block.setLocation(5, 520);
            }

            // Your existing code...
        } else {
            System.out.println("Outside valid drop zone, returning to home position");
            block.setLocation(5, 520);
        }
    }
}
