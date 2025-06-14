package src;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Game {
    private BoardTile[][] board;
    private int score;
    public void setScore(int score) {
        this.score = score;
    }

    private ArrayList<Block> blocks;

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    private ArrayList<Block> onHand;

    public ArrayList<Block> getOnHand() {
        return onHand;
    }

    public BoardTile[][] getBoard() {
        return board;
    }

    public Game() {
        this.board = new BoardTile[8][8];
        for (int i = 0; i < 8; i++) {
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
            Collections.shuffle(blocks);
            if(checkForLoss()){
                JPanel loseScreen = new JPanel();
                loseScreen.setBackground(Color.ORANGE);
                loseScreen.setBounds(100, 250, 200, 200);
                JLabel lost = new JLabel();
                lost.setText("YOU LOST! \nPress quit to \nreturn to menu");
                lost.setFont(new Font("SansSerif", Font.BOLD, 20));
                lost.setForeground(Color.WHITE);
                lost.setHorizontalAlignment(SwingConstants.CENTER);
                loseScreen.add(lost);
                layeredPane.add(loseScreen, JLayeredPane.POPUP_LAYER);
                frame.repaint();
                frame.revalidate();
            }
            if (onHand.size() == 0) {
                System.out.println(blocks.size());
                for (int i = 0; i < 3; i++) {
                    System.out.println(blocks.get(i));
                }
                while(true){
                    int endpoint = blocks.get(0).getw()*40 + 50 +blocks.get(1).getw()*40 + 50 +blocks.get(2).getw()*40;
                    if(endpoint>400){
                        Collections.shuffle(blocks);
                    }
                    else{
                        break;
                    }
                }
                onHand.add(blocks.get(0));
                onHand.add(blocks.get(1));
                onHand.add(blocks.get(2));
                blocks.remove(0);
                blocks.remove(0);
                blocks.remove(0);
                onHand.get(0).setOpaque(false);
                onHand.get(1).setOpaque(false);
                onHand.get(2).setOpaque(false);
                onHand.get(0).setBounds(5, 520, onHand.get(0).getw() * 40, onHand.get(0).geth() * 40);

                layeredPane.add(onHand.get(0), JLayeredPane.DRAG_LAYER);
                onHand.get(0).setReturnX(5);
                onHand.get(0).setSlot(0);
                onHand.get(1).setBounds(5 + onHand.get(0).getw()
                        * 40 + 50, 520, onHand.get(1).getw() * 40, onHand.get(1).geth() * 40);
                layeredPane.add(onHand.get(1), JLayeredPane.DRAG_LAYER);

                onHand.get(1).setReturnX(5 + onHand.get(0).getw()
                        * 40 + 50);
                onHand.get(1).setSlot(1);
                onHand.get(2).setBounds(5 + onHand.get(0).getw()
                        * 40 + 50 + onHand.get(1).getw() * 40 + 50, 520, onHand.get(2).getw() * 40,
                        onHand.get(2).geth() * 40);
                onHand.get(2).setReturnX(5 + onHand.get(0).getw()
                        * 40 + 50 + onHand.get(1).getw() * 40 + 50);
                layeredPane.add(onHand.get(2), JLayeredPane.DRAG_LAYER);
                
                onHand.get(2).setSlot(2);

                

            }
            wait();

        }
    }

    public void placeBlock(Block b, int startX, int startY) {
        for (int i = 0; i < b.geth(); i++) {
            for (int j = 0; j < b.getw(); j++) {
                if (b.getShape()[i][j] == true) {
                    board[startY + i][startX + j].setOccupied(true);
                }
            }
        }
        onHand.remove(b);
    }

    public boolean checkForLoss() {
        if (onHand.size() > 0) {
            for (int i = 0; i < onHand.size(); i++) {
                if (checkValidMoves(onHand.get(i)) == true) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean fullRow(BoardTile[] arr) {
        for (int i = 0; i < 8; i++) {
            if (arr[i].isOccupied() == false) {
                return false;
            }
        }
        return true;
    }

    public boolean fullColumn(int column) {
        for (int i = 0; i < 8; i++) {
            if (board[i][column].isOccupied() == false) {
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
            }
        }
        for (int i = 0; i < 8; i++) {
            if (fullColumn(i)) {
                columnIndices.add(i);
                System.out.println("column");
                score += 10;
            }
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
                board[rowIndices.get(i)][j].setColor(null);
                System.out.println("set");
            }
        }
        for (int i = 0; i < columnIndices.size(); i++) {
            for (int j = 0; j < 8; j++) {
                board[j][columnIndices.get(i)].setOccupied(false);
                board[j][columnIndices.get(i)].setColor(null);
            }
        }
    }

    public boolean checkValidMoves(Block b) {
        for (int i = 0; i < 8 - b.geth() + 1; i++) {
            for (int j = 0; j < 8 - b.getw() + 1; j++) {
                if (BlockFits(b, j, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean BlockFits(Block b, int startX, int startY) {
        for (int i = 0; i < b.geth(); i++) {
            for (int j = 0; j < b.getw(); j++) {
                if (board[startY + i][startX + j].isOccupied() == true && b.getShape()[i][j] == true) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getScore() {
        return this.score;
    }
}
