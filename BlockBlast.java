package cpt;

import javax.swing.*;
import java.awt.*;

public class BlockBlast {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
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

        // create ui
        ImageIcon title = new ImageIcon(new ImageIcon("cpt/title.png").getImage().getScaledInstance(386, 398, Image.SCALE_SMOOTH));
        JLabel titleLabel = new JLabel();
        titleLabel.setIcon(title);
        // titleLabel.setVerticalAlignment(JLabel.TOP);
        // titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(0, 0, 400, 400);

        frame.add(titleLabel);

        // render the frame
        frame.setVisible(true); // make frame visible
    }
}
