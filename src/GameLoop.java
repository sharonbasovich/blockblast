package src;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class GameLoop {
    static JLabel score = new JLabel();
    //initialize the board
    public static void initBoard(Game game, JFrame frame, JLayeredPane layeredPane) {
        // Add board panel to the frame
        BoardPanel boardPanel = new BoardPanel(game.getBoard());
        boardPanel.setBounds(30, 100, 325, 500);
        layeredPane.add(boardPanel, JLayeredPane.DEFAULT_LAYER);

        layeredPane.setBounds(0, 0, 400, 800);
        frame.add(layeredPane);
    }
    //initialize all the blocks and make them visible in the frame
    public void initBlocks(Game game, JFrame frame, JLayeredPane layeredPane) {
        Block block = new Block(3, 2, "#ff0000", new boolean[][] { { true, true, true }, { true, true, true } });
        makeDraggable(block, frame, layeredPane, game);
        game.getBlocks().add(block);
        Block block2 = new Block(1, 2, "#fff000", new boolean[][] { { true }, { true } });
        makeDraggable(block2, frame, layeredPane, game);
        game.getBlocks().add(block2);
        Block block3 = new Block(2, 3, "#00ff00", new boolean[][] { { false, true }, { true, true }, { true, false } });
        makeDraggable(block3, frame, layeredPane, game);
        game.getBlocks().add(block3);

        Block l2 = new Block(1, 2, "#ff0000", new boolean[][] { { true }, { true } });
        makeDraggable(l2, frame, layeredPane, game);
        game.getBlocks().add(l2);

        Block l3 = new Block(1, 3, "#fff000", new boolean[][] { { true }, { true }, { true } });
        makeDraggable(l3, frame, layeredPane, game);
        game.getBlocks().add(l3);

        Block l4 = new Block(1, 4, "#ff0000", new boolean[][] { { true }, { true }, { true }, { true } });
        makeDraggable(l4, frame, layeredPane, game);
        game.getBlocks().add(l4);

        Block l5 = new Block(1, 5, "#fff000", new boolean[][] { { true }, { true }, { true }, { true }, { true } });
        makeDraggable(l5, frame, layeredPane, game);
        game.getBlocks().add(l5);

        Block w2 = new Block(2, 1, "#00ff00", new boolean[][] { { true, true } });
        makeDraggable(w2, frame, layeredPane, game);
        game.getBlocks().add(w2);

        Block w3 = new Block(3, 1, "#ff0000", new boolean[][] { { true, true, true } });
        makeDraggable(w3, frame, layeredPane, game);
        game.getBlocks().add(w3);

        Block w4 = new Block(4, 1, "#fff000", new boolean[][] { { true, true, true, true } });
        makeDraggable(w4, frame, layeredPane, game);
        game.getBlocks().add(w4);

        Block w5 = new Block(5, 1, "#ff0000", new boolean[][] { { true, true, true, true, true } });
        makeDraggable(w5, frame, layeredPane, game);
        game.getBlocks().add(w5);

        Block s2 = new Block(2, 2, "#ff0000", new boolean[][] { {true, true } , {true, true} });
        makeDraggable(s2, frame, layeredPane, game);
        game.getBlocks().add(s2);   
        
        Block s3 = new Block(3, 3, "#ff0000", new boolean[][] { { true, true, true }, { true, true, true }, {true, true, true} });
        makeDraggable(s3, frame, layeredPane, game);
        game.getBlocks().add(s3);

        Block r1 = new Block(2, 2, "#00ff00",
                new boolean[][] { { true, true }, { true, false } });
        makeDraggable(r1, frame, layeredPane, game);
        game.getBlocks().add(r1);

        Block r2 = new Block(2, 2, "#ff0000",
                new boolean[][] { { false, true }, { true, true } });
        makeDraggable(r2, frame, layeredPane, game);
        game.getBlocks().add(r2);

        Block r3 = new Block(2, 2, "#00ff00",
                new boolean[][] { { true, false }, { true, true } });
        makeDraggable(r3, frame, layeredPane, game);
        game.getBlocks().add(r3);

        Block r4 = new Block(2, 2, "#ff0000",
                new boolean[][] { { true, true }, { false, true } });
        makeDraggable(r4, frame, layeredPane, game);
        game.getBlocks().add(r4);

        Block t1 = new Block(2, 3, "#ff0000",
                new boolean[][] { { true, false }, { true, true }, {true, false }});
        makeDraggable(t1, frame, layeredPane, game);
        game.getBlocks().add(t1);

        Block t2 = new Block(2, 3, "#00ff00",
                new boolean[][] { { false, true }, { true, true }, { false, true } });
        makeDraggable(t2, frame, layeredPane, game);
        game.getBlocks().add(t2);

        Block t3 = new Block(3, 2, "#ff0000",
                new boolean[][] { { false, true, false }, { true, true,  true } });
        makeDraggable(t3, frame, layeredPane, game);
        game.getBlocks().add(t3);

        Block t4 = new Block(3, 2, "#ff0000",
                new boolean[][] { { true, true, true }, { false, true, false } });
        makeDraggable(t4, frame, layeredPane, game);
        game.getBlocks().add(t4);

        Block t5 = new Block(3, 2, "#00ff00",
                new boolean[][] { { true, true, true }, { true, true, true } });
        makeDraggable(t5, frame, layeredPane, game);
        game.getBlocks().add(t5);

        Block t6 = new Block(2, 3, "#ff0000",
                new boolean[][] { { true, true }, { true, true }, { true, true } });
        makeDraggable(t6, frame, layeredPane, game);
        game.getBlocks().add(t6);

        Block s1 = new Block(2, 3, "#00ff00",
                new boolean[][] { { false, true }, { true, true }, { true, false } });
        makeDraggable(s1, frame, layeredPane, game);
        game.getBlocks().add(s1);

        Block s4 = new Block(2, 3, "#ff0000",
                new boolean[][] { { true, false }, { true, true }, { false, true } });
        makeDraggable(s4, frame, layeredPane, game);
        game.getBlocks().add(s4);

        Block s6 = new Block(3, 2, "#00ff00",
                new boolean[][] { { false, true, true }, { true, true, false } });
        makeDraggable(s6, frame, layeredPane, game);
        game.getBlocks().add(s6);

        Block s7 = new Block(3, 2, "#ff0000",
                new boolean[][] { { true, true, false }, { false, true, true } });
        makeDraggable(s7, frame, layeredPane, game);
        game.getBlocks().add(s7);


        Block a7 = new Block(3, 2, "#ff0000",
                new boolean[][] { { true, false, false }, { true, true, true } });
        makeDraggable(a7, frame, layeredPane, game);
        game.getBlocks().add(a7);

        Block a6 = new Block(3, 2, "#ff0000",
                new boolean[][] { { false, false, true }, { true, true, true } });
        makeDraggable(a6, frame, layeredPane, game);
        game.getBlocks().add(a6);

        Block a5 = new Block(3, 2, "#ff0000",
                new boolean[][] { { true, true, true }, { true, false, false } });
        makeDraggable(a5, frame, layeredPane, game);
        game.getBlocks().add(a5);

        Block a4 = new Block(3, 2, "#ff0000",
                new boolean[][] { { true, true, true }, { false, false, true } });
        makeDraggable(a4, frame, layeredPane, game);
        game.getBlocks().add(a4);

        Block a0 = new Block(2, 3, "#ff0000",
                new boolean[][] { { false, true }, { false, true }, { true, true } });
        makeDraggable(a0, frame, layeredPane, game);
        game.getBlocks().add(a0);

        Block a1 = new Block(2, 3, "#ff0000",
                new boolean[][] { { true, true }, { false, true }, { false, true } });
        makeDraggable(a1, frame, layeredPane, game);
        game.getBlocks().add(a1);

        Block a2 = new Block(2, 3, "#00FFFF",
                new boolean[][] { { true, true }, { true, false }, { true, false } });
        makeDraggable(a2, frame, layeredPane, game);
        game.getBlocks().add(a2);

        Block a3 = new Block(2, 3, "#ff0000",
                new boolean[][] { { true, false }, { true, false }, { true, true } });
        makeDraggable(a3, frame, layeredPane, game);
        game.getBlocks().add(a3);

        Block z2 = new Block(3, 3, "#00FFFF",
                new boolean[][] { { true, false, false }, { true, false, false }, { true, true, true } });
        makeDraggable(z2, frame, layeredPane, game);
        game.getBlocks().add(z2);
        
        Block z3 = new Block(3, 3, "#ff0000",
                new boolean[][] { { true, true, true }, { true, false, false }, { true, false, false } });
        makeDraggable(z3, frame, layeredPane, game);
        game.getBlocks().add(z3);

        Block z4 = new Block(3, 3, "#00FFFF",
                new boolean[][] { { true, true, true }, { false, false, true }, { false, false, true } });
        makeDraggable(z4, frame, layeredPane, game);
        game.getBlocks().add(z4);

        Block z5 = new Block(3, 3, "#00FFFF",
                new boolean[][] { { false, false, true }, { false, false, true }, { true, true, true } });
        makeDraggable(z5, frame, layeredPane, game);
        game.getBlocks().add(z5);
        
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

    public GameLoop(JFrame frame, String username, Runnable goBack) {
        // JFrame frame = new JFrame();

        // setupFrame(frame);
        Game game = new Game();
        JLayeredPane layeredPane = new JLayeredPane();

        score.setText("Score: " + String.valueOf(game.getScore()));
        score.setBounds(100, 60, 200, 30);
        score.setForeground(Color.WHITE);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setFont(new Font("SansSerif", Font.BOLD, 20));
        score.setVisible(true);
        JLabel highscoreLabel = Highscore.getHighscoreElement();
        frame.add(highscoreLabel);
        frame.add(score);

        initBoard(game, frame, layeredPane);
        initBlocks(game, frame, layeredPane);

        // username
        JLabel name = new JLabel();
        name.setText(username);
        name.setBounds(220, 20, 150, 30);
        name.setFont(new Font("SansSerif", Font.BOLD, 20));
        name.setForeground(new Color(0xeea018));
        name.setHorizontalAlignment(SwingConstants.RIGHT);

        frame.add(name);

        JButton quit = new JButton("Quit");
        quit.setBackground(Color.red);
        quit.setForeground(Color.white);
        // quit.setBounds(280, 700, 80, 40);
        quit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                quit.setBackground(new Color(0x19cba4)); // Change color on hover
                quit.setBounds(160, 20, 80, 30);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quit.setBounds(170, 20, 60, 30);

                quit.setBackground(Color.red); // Restore original color
            }
        });
        quit.setBounds(170, 20, 60, 30);
        quit.setFont(new Font("SansSerif", Font.BOLD, 12));
        quit.setFocusable(false);
        quit.setBorderPainted(false);

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sound button = new Sound();
                button.playOnce(4);
                System.out.println("clicked");
                highscoreLabel.setVisible(false);
                score.setVisible(false);
                name.setVisible(false);
                quit.setVisible(false);
                layeredPane.setVisible(false);
                String[] score = { username, Integer.toString(game.getScore()) };
                new Leaderboard(score);
                goBack.run();
            }
        });

        frame.add(quit);

        frame.setVisible(true);
        new Thread(() -> {
            try {
                game.playGame(frame, layeredPane);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    //method to allow a user to drag a block
    private void makeDraggable(Block block, JFrame frame, JLayeredPane layeredPane, Game game) {
        MouseAdapter adapter = new MouseAdapter() {
            Point startPoint; //starting location of the block

            @Override
            public void mousePressed(MouseEvent e) { //method to set the start point and play sound
                startPoint = e.getPoint();
                Sound pickup = new Sound();
                pickup.playOnce(3);
            }

            @Override
            public void mouseDragged(MouseEvent e) { //update the point of the block based on how the user moves the mouse
                Point location = block.getLocation();
                int x = location.x + e.getX() - startPoint.x;
                int y = location.y + e.getY() - startPoint.y;
                block.setLocation(x, y);
                onHover(block, frame, layeredPane, game, new Point(block.getX() + 20, block.getY() + 20)); //onhover method: helps user visualize how the board would look after the block is placed and also shows valid moves
            }

            @Override
            public void mouseReleased(MouseEvent e) { //update the location of the block
                Point location = new Point(block.getX() + 20, block.getY() + 20);
                new Thread(() -> {
                    try {
                        onRelease(block, frame, layeredPane, game, location); //run logic upon dropping
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        };
        //add listeners for drag and drop
        block.addMouseListener(adapter);
        block.addMouseMotionListener(adapter);
    }

    public static void onHover(Block block, JFrame frame, JLayeredPane layeredPane, Game game, Point location) {
        //begin by making sure the board is displayed right (i.e. colors are properly assigned to each tile)
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!game.getBoard()[i][j].isOccupied()) {
                    game.getBoard()[i][j].setColor(null);
                    game.getBoard()[i][j].setTint(null);
                } else {
                    game.getBoard()[i][j].setTint(null);
                }
            }
        }
        frame.repaint();
        if (location.x > 30 && location.x < 30 + (8 - block.getw() + 1) * 40 && location.y > 100
                && location.y < 100 + (8 - block.geth() + 1) * 40) { //check if block is being hovered over the board
            System.out.println("Inside valid drop zone!");
            int x = (int) (location.x - 30) / 40;
            int y = (int) (location.y - 100) / 40; //calculate x and y based on the coordinates of the board/grid
            System.out.println("x: " + x + " y: " + y);
            int w = block.getWidth() / 40;
            int h = block.getHeight() / 40;
            if (game.BlockFits(block, x, y) == true) { //if the block fits at the location of the cursor
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        if (block.getBlockTiles()[i][j].isExists()) {
                            Color c = Color.decode(block.getColor());
                            //set a tint as a "preview" of the block
                            game.getBoard()[y + i][x + j].setTint(new Color(c.getRed(), c.getGreen(), c.getBlue(), 50));
                        }
                    }
                }
                frame.repaint(); //repaint teh frame
            }
        } else {
            System.out.println("Outside valid drop zone, returning to home position");
        }
    }

    public synchronized void onRelease(Block block, JFrame frame, JLayeredPane layeredPane, Game game, Point location)
            throws InterruptedException {
        if (location.x > 30 && location.x < 30 + (8 - block.getw() + 1) * 40 && location.y > 100
                && location.y < 100 + (8 - block.geth() + 1) * 40) { //check if block is within valid location inside the board
            System.out.println("Inside valid drop zone!");
                    //same logic to find location and position of mouse/block as the onHover method
            int x = (int) (location.x - 30) / 40;
            int y = (int) (location.y - 100) / 40;
            System.out.println("x: " + x + " y: " + y);
            int w = block.getWidth() / 40;
            int h = block.getHeight() / 40;
            if (game.BlockFits(block, x, y) == true) {
                Sound place = new Sound();
                place.playOnce(1);
                for (int i = 0; i < h; i++) { //set the color of the board to the same color as the block being placed only where the tile exists in the block shape
                    for (int j = 0; j < w; j++) {
                        if (block.getBlockTiles()[i][j].isExists()) {
                            game.getBoard()[y + i][x + j].setColor(block.getColor());
                        }
                    }
                }
                game.placeBlock(block, x, y); //place the block and update the boolean 2D array
                layeredPane.remove(block);
                game.getOnHand().remove(block); //remove the block from the frame and onHand
                game.getBlocks().add(block); //place the block back into the blocks list
                game.updateBoard(); //update the board (check for full rows and columns)
                synchronized (game) {
                    game.notifyAll(); //notify the game object that it can start checking for whether it needs to replenish the blocks on hand or not
                }
                
                game.setScore(game.getScore() + block.getNumberOfBlocks()); //set the score of the game
                score.setText("Score: " + String.valueOf(game.getScore()));

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (!game.getBoard()[i][j].isOccupied()) {
                            game.getBoard()[i][j].setColor(null);
                            game.getBoard()[i][j].setTint(null);
                        } else {
                            game.getBoard()[i][j].setTint(null);
                        }
                    }
                }
                frame.revalidate(); //update the frame
                frame.repaint();
            } else {
                block.setLocation(block.getReturnX(), 520); //if it cannot be placed, return it to its proper position outside the board
            }
        } else {
            block.setLocation(block.getReturnX(), 520);// if the block is not hovered over the grid/board, return it to its proper position
                                                       // outside the board

        }
    }
}
