import javax.swing.*;
import java.awt.*;

public class Leaderboard {
    JFrame frame = new JFrame();

    Leaderboard() {
        frame.setTitle("Block Blast - Sharon and Yichen"); // set name of app
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit program when closing window
        frame.setResizable(false); // prevent resizing
        frame.setSize(400, 800); // set dimensions of window
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0x1559c1));
        System.out.println("test");
    }
}
