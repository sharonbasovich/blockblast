
import java.util.*;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
public class Game {
    private BoardTile[][] board;
    private int score;
    private ArrayList<Block> blocks;
    private ArrayList<Block> onHand;
    public ArrayList<Block> getOnHand() {
        return onHand;
    }
    public BoardTile[][] getBoard() {
        return board;
    }
    public Game(){
        this.board = new BoardTile[8][8];
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                board[i][j] = new BoardTile(40*(i+1), (40*(j+1)+100), false, null);
            }
        }
        this.score = 0;
        this.blocks = new ArrayList<Block>();
        this.onHand = new ArrayList<Block>();
    }
    public void playGame(){
        while(checkForLoss() == false){
            if(onHand.size() == 0){
                onHand.add(blocks.remove(0));
                onHand.add(blocks.remove(0));
                onHand.add(blocks.remove(0));
            }
            placeBlock(onHand.get(0), 0, 0);
            updateBoard();
        }
    }
    public void placeBlock(Block b, int startX, int startY){
        for(int i = 0;i<b.getWidth();i++){
            for(int j = 0;j<b.getHeight();j++){
                if(b.getShape()[i][j] == true){
                    board[startX+i][startY+j].setOccupied(true);
                }
            }
        }
        onHand.remove(b);
    }

    public boolean checkForLoss(){
        if(onHand.size() > 0){
            for(int i = 0;i<onHand.size();i++){
                if(checkValidMoves(onHand.get(i)) == false){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public boolean fullRow(BoardTile[] arr){
        for(int i = 0;i<8;i++){
            if(arr[i].isOccupied() == false){
                return false;
            }
        }
        return true;
    }
    public boolean fullColumn(int column){
        for(int i = 0;i<8;i++){
            if(board[i][column].isOccupied() == false){
                return false;
            }
        }
        return true;
    }
    public void updateBoard(){
        ArrayList<Integer> rowIndices = new ArrayList<Integer>();
        ArrayList<Integer> columnIndices = new ArrayList<Integer>();
        for(int i = 0;i<8;i++){
            if(fullRow(board[i])){
                rowIndices.add(i);
            }
        }
        for(int i = 0;i<8;i++){
            if(fullColumn(i)){
                columnIndices.add(i);
            }
        }
        for(int i = 0;i<rowIndices.size();i++){
            for(int j = 0;j<8;j++){
                board[rowIndices.get(i)][j].setOccupied(false);
            }
        }
        for(int i = 0;i<columnIndices.size();i++){
            for(int j = 0;j<8;j++){
                board[j][columnIndices.get(i)].setOccupied(false);
            }
        }
    }
    public boolean checkValidMoves(Block b){
        for(int i=0;i<8-b.getWidth()+1;i++){
            for(int j=0;j<8-b.getHeight()+1;j++){
                if(BlockFits(b,i,j)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean BlockFits(Block b, int startX, int startY){
        for(int i = 0;i<b.getWidth();i++){
            for(int j = 0;j<b.getHeight();j++){
                if(board[startX+i][startY+j].isOccupied() == true && b.getShape()[i][j] == true){
                    return false;
                }
            }
        }
        return true;
    }
    public int getScore(){
        return this.score;
    }
}

