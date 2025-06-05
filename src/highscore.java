package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.*;
import javax.swing.ImageIcon;
// import javax.swing.JFrame;
import javax.swing.JLabel;

public class highscore {

    // public static void main(String[] args) {
    //     JFrame frame = new JFrame();
    //     frame.setTitle("Block Blast - Sharon and Yichen"); // set name of app
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exi program when closing window
    //     frame.setResizable(false); // prevent resizing
    //     frame.setSize(400, 800); // set dimensions of window
    //     frame.setLayout(null);
    //     frame.add(getHighscoreElement());
        
    //     frame.setVisible(true);
    // }

    public static JLabel getHighscoreElement() {
        JLabel highscore = new JLabel();
        ImageIcon crown = new ImageIcon(
                new ImageIcon("crown.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        try {
            BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"));
            if (reader.ready()) {
                String[] score = reader.readLine().split(",");
                reader.close();

                highscore.setText(score[1]);
                highscore.setForeground(new Color(0xC28703));
                highscore.setIcon(crown);
                highscore.setIconTextGap(6);
                highscore.setFont(new Font("SansSerif", Font.BOLD, 20));
                highscore.setBounds(20, 20, 95, 25);
            }

            
        } catch (Exception e) {
            // TODO: handle exception
        }

        return highscore;
    }
}
