
import javax.swing.*;
import java.awt.*;

public class BlockBlast{
    public static void init(Game game, JFrame frame) {
        // Add board panel
        BoardPanel boardPanel = new BoardPanel(game.getBoard());
        boardPanel.setBounds(5, 0, 364, 500);
        frame.add(boardPanel);

        
        SimpleBlock simpleBlock = new SimpleBlock(3, 2, "#ff0000", new boolean[][]{{true, true, true}, {true, true, true}});
        simpleBlock.setBounds(5, 520, 120, 80);
        
        frame.add(simpleBlock);
    }
    public static void setupFrame(JFrame frame){
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


}
