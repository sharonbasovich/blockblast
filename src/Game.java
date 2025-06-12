package src;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Game {
    //getters and setters
    private BoardTile[][] board;
    private int score;

    public void setScore(int score) {
        this.score = score;
    }

    private ArrayList<Block> blocks; //all the blocks in the game

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    private ArrayList<Block> onHand; //blocks given to the user (max 3)

    public ArrayList<Block> getOnHand() {
        return onHand;
    }

    public BoardTile[][] getBoard() {
        return board;
    }
    //constructor
    public Game() {
        this.board = new BoardTile[8][8];
        for (int i = 0; i < 8; i++) { //create the board
            for (int j = 0; j < 8; j++) {
                board[i][j] = new BoardTile(40 * j, 40 * i, false, null);
            }
        }
        this.score = 0;
        this.blocks = new ArrayList<Block>();
        this.onHand = new ArrayList<Block>();
    }

    public synchronized void playGame(JFrame frame, JLayeredPane layeredPane) throws InterruptedException {
        while (true) {
            //shuffle the blocks
            Collections.shuffle(blocks);
            
            if (onHand.size() == 0) { //if there are no more blocks on hand, generate them
                System.out.println(blocks.size());
                for (int i = 0; i < 3; i++) {
                    System.out.println(blocks.get(i));
                }
                while (true) { //if the combination of blocks causes the blocks to go off the screen, reshuffle. do this until you can fit all the blocks onto the screen
                    int endpoint = blocks.get(0).getw() * 40 + 50 + blocks.get(1).getw() * 40 + 50
                            + blocks.get(2).getw() * 40;
                    if (endpoint > 400) {
                        Collections.shuffle(blocks);
                    } else {
                        break;
                    }
                }
                //add the blocks to hand
                onHand.add(blocks.get(0));
                onHand.add(blocks.get(1));
                onHand.add(blocks.get(2));
                //remove the first three blocks
                blocks.remove(0);
                blocks.remove(0);
                blocks.remove(0);
                //initialize the blocks and make them visible to user
                onHand.get(0).setOpaque(false);
                onHand.get(1).setOpaque(false);
                onHand.get(2).setOpaque(false);
                onHand.get(0).setBounds(5, 520, onHand.get(0).getw() * 40, onHand.get(0).geth() * 40);

                layeredPane.add(onHand.get(0), JLayeredPane.DRAG_LAYER);
                onHand.get(0).setReturnX(5);
                onHand.get(1).setBounds(5 + onHand.get(0).getw()
                        * 40 + 50, 520, onHand.get(1).getw() * 40, onHand.get(1).geth() * 40);
                layeredPane.add(onHand.get(1), JLayeredPane.DRAG_LAYER);

                onHand.get(1).setReturnX(5 + onHand.get(0).getw()
                        * 40 + 50);
                onHand.get(2).setBounds(5 + onHand.get(0).getw()
                        * 40 + 50 + onHand.get(1).getw() * 40 + 50, 520, onHand.get(2).getw() * 40,
                        onHand.get(2).geth() * 40);
                onHand.get(2).setReturnX(5 + onHand.get(0).getw()
                        * 40 + 50 + onHand.get(1).getw() * 40 + 50);
                layeredPane.add(onHand.get(2), JLayeredPane.DRAG_LAYER);


            }
            if (checkForLoss()) { // if loss detected, create panel notifying the user
                JPanel loseScreen = new JPanel();
                loseScreen.setBackground(Color.ORANGE);
                JLabel lost = new JLabel();
                loseScreen.setBounds(100, 250, 200, 150);
                lost.setText("<html><body><br>YOU LOST!<br>Press quit to<br>return to menu</body></html>");
                lost.setFont(new Font("SansSerif", Font.BOLD, 20));
                lost.setForeground(Color.WHITE);
                lost.setHorizontalAlignment(SwingConstants.CENTER);
                loseScreen.add(lost);
                layeredPane.add(loseScreen, JLayeredPane.POPUP_LAYER);
                frame.repaint();
                frame.revalidate();
            }
            frame.repaint();
            wait(); //threaded to allow this method to wait until block is placed

        }
    }
    //place block
    public void placeBlock(Block b, int startX, int startY) {
        for (int i = 0; i < b.geth(); i++) {
            for (int j = 0; j < b.getw(); j++) {
                if (b.getShape()[i][j] == true) {
                    //if there is a tile at this location of the block, set the corresponding tile to true
                    board[startY + i][startX + j].setOccupied(true);
                }
            }
        }
        onHand.remove(b);
    }
    //check for loss
    public boolean checkForLoss() {
        if (onHand.size() > 0) {
            for (int i = 0; i < onHand.size(); i++) {
                if (checkValidMoves(onHand.get(i)) == true) { //if there is a possible move for any block, return false
                    return false;
                }
            }
            return true; //if none of the blocks can be placed, return true
        }
        return false; //if onhand is empty, false
    }

    public boolean fullRow(BoardTile[] arr) { //iterate through a row and return false as soon as a tile is detected as empty
        for (int i = 0; i < 8; i++) {
            if (arr[i].isOccupied() == false) { 
                return false; 
            }
        }
        return true; //else return true
    }

    public boolean fullColumn(int column) {
        for (int i = 0; i < 8; i++) {
            if (board[i][column].isOccupied() == false) {// iterate through a column and return false as soon as a tile is
                                                         // detected as empty
                return false;
            }
        }
        return true;
    }

    public void updateBoard() {
        ArrayList<Integer> rowIndices = new ArrayList<Integer>();
        ArrayList<Integer> columnIndices = new ArrayList<Integer>();
        for (int i = 0; i < 8; i++) {
            if (fullRow(board[i])) {
                rowIndices.add(i);
                System.out.println("row");
                score += 10;
                System.out.println(score);
            } //check for full rows and update score accordingly
        }
        for (int i = 0; i < 8; i++) {
            if (fullColumn(i)) {
                columnIndices.add(i);
                System.out.println("column");
                score += 10;
            } //check for full columns and update score accordingly
        }
        System.out.println(rowIndices.size());
        for (int i = 0; i < rowIndices.size(); i++) {
            System.out.println(rowIndices.get(i));
        }
        for (int i = 0; i < columnIndices.size(); i++) {
            System.out.println(columnIndices.get(i));
        }
        System.out.println(columnIndices.size());
        for (int i = 0; i < rowIndices.size(); i++) {
            for (int j = 0; j < 8; j++) {
                board[rowIndices.get(i)][j].setOccupied(false);
                board[rowIndices.get(i)][j].setColor(null); //update the board and clear rows
            }
        }
        for (int i = 0; i < columnIndices.size(); i++) {
            for (int j = 0; j < 8; j++) {
                board[j][columnIndices.get(i)].setOccupied(false);
                board[j][columnIndices.get(i)].setColor(null); //update the board and clear columns
            }
        }
    }

    public boolean checkValidMoves(Block b) {
        for (int i = 0; i < 8 - b.geth() + 1; i++) {
            for (int j = 0; j < 8 - b.getw() + 1; j++) {
                if (BlockFits(b, j, i)) {
                    return true; //iterate through all possible locations of a block, i.e. as long as the block fits on the board, check it. if blcok fits, return true
                }
            }
        }
        return false; //else return false
    }

    public boolean BlockFits(Block b, int startX, int startY) {
        for (int i = 0; i < b.geth(); i++) {
            for (int j = 0; j < b.getw(); j++) {
                if (board[startY + i][startX + j].isOccupied() == true && b.getShape()[i][j] == true) {
                    return false; //iterate through the shape of the block: if the tiles of the block overlap with an occupied board tile, return false (it doesn't fit)
                }
            }
        }
        return true;
    }

    public int getScore() {
        return this.score; //setter for score
    }
}
