package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Highscore {

    private static JLabel highscore;

    public static JLabel getHighscoreElement() {
        // create highscore ui element
        highscore = new JLabel();

        // set the crown icon and resize it
        ImageIcon crown = new ImageIcon(
                new ImageIcon("crown.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        try {
            // if the file is not empty, read the first element of leaderboard.txt
            BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"));
            if (reader.ready()) {
                String[] score = reader.readLine().split(",");
                reader.close();

                // set the text of the highscore to the score of the highest player
                highscore.setText(score[1]);

                // styling
                highscore.setForeground(new Color(0xC28703));
                highscore.setIcon(crown);
                highscore.setIconTextGap(6);
                highscore.setFont(new Font("SansSerif", Font.BOLD, 20));
                highscore.setBounds(20, 20, 95, 25);
            }

            
        } catch (Exception e) {
        }

        // return the highscore element
        return highscore;
    }
}
