package src;

import javax.swing.*;
import java.awt.*;
public class BoardPanel extends JPanel {
    //instance variable
    private BoardTile[][] board;
    //getter
    public BoardTile[][] getBoard() {
        return board;
    }
    //constructor
    public BoardPanel(BoardTile[][] board) {
        this.board = board;
    }
    public void paintComponent(Graphics g) {
        // Draw all tiles
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].paintComponent(g);
            }
        }
    }
    

}
