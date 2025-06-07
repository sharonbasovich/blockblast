package src;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BlockBlast {
    static JLabel score = new JLabel();

    public static void initBoard(Game game, JFrame frame, JLayeredPane layeredPane) {
        // Add board panel
        BoardPanel boardPanel = new BoardPanel(game.getBoard());
        boardPanel.setBounds(30, 100, 325, 500);
        layeredPane.add(boardPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.setBounds(0, 0, 400, 800);
        frame.add(layeredPane);
    }

    public static void initBlocks(Game game, JFrame frame, JLayeredPane layeredPane) {
        Block block = new Block(3, 2, "#ff0000", new boolean[][] { { true, true, true }, { true, true, true } });
        makeDraggable(block, frame, layeredPane, game);
        game.getBlocks().add(block);
        Block block2 = new Block(1, 2, "#fff000", new boolean[][] { { true }, { true } });
        makeDraggable(block2, frame, layeredPane, game);
        game.getBlocks().add(block2);
        Block block3 = new Block(3, 1, "#00ff00", new boolean[][] { { true, true, true } });
        makeDraggable(block3, frame, layeredPane, game);
        game.getBlocks().add(block3);
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
        JLayeredPane layeredPane = new JLayeredPane();

        score.setText("Score: " + String.valueOf(game.getScore()));
        score.setBounds(10, 10, 200, 30);
        score.setForeground(Color.WHITE);
        score.setFont(new Font("Arial", Font.BOLD, 20));
        // Score

        frame.add(score);

        initBoard(game, frame, layeredPane);
        initBlocks(game, frame, layeredPane);

        frame.setVisible(true);
        new Thread(() -> {
            try {
                game.playGame(frame, layeredPane);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
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
                Point location = new Point(block.getX() + 20, block.getY() + 20);
                onRelease(block, frame, layeredPane, game, location);
            }
        };

        block.addMouseListener(adapter);
        block.addMouseMotionListener(adapter);
    }
    public static void onHover(Block block, JFrame frame, JLayeredPane layeredPane, Game game, Point location){
        
    }


    public static void onRelease(Block block, JFrame frame, JLayeredPane layeredPane, Game game, Point location) {
        System.out.println(30 + (8 - block.getw() + 1) * 40);
        System.out.println(100 + (8 - block.geth() + 1) * 40);
        if (location.x > 30 && location.x < 30 + (8 - block.getw() + 1) * 40 && location.y > 100
                && location.y < 100 + (8 - block.geth() + 1) * 40) {
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

                }
                game.placeBlock(block, x, y);
                layeredPane.remove(block);
                game.getOnHand().remove(block);
                System.out.println("removed");

                System.out.println(game.getOnHand().size());
                synchronized (game) {
                    game.notifyAll();
                }
                game.setScore(game.getScore() + block.getNumberOfBlocks());
                
                System.out.println("Score: " + game.getScore());
                score.setText("Score: " + String.valueOf(game.getScore()));
                frame.revalidate();
                frame.repaint();
            } else {
                System.out.println("Outside valid drop zone, returning to home position");
                System.out.println("Outside valid drop zone, returning to home position");
                if(block.getSlot() == 0){
                    block.setLocation(5, 520);
                }
                if(block.getSlot() == 1){
                    block.setLocation(150, 520);
                }
                if(block.getSlot() == 2){
                    block.setLocation(250, 520);
                }
            }

            // Your existing code...
        } else {
            System.out.println("Outside valid drop zone, returning to home position");
            block.setLocation(5, 520);
        }
    }
}
