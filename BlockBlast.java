package cpt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlockBlast implements ActionListener {

    static JButton start;

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

        // create title logo
        ImageIcon title = new ImageIcon(
                new ImageIcon("cpt/title.png").getImage().getScaledInstance(386, 398, Image.SCALE_SMOOTH));
        JLabel titleLabel = new JLabel();
        titleLabel.setIcon(title);
        // titleLabel.setVerticalAlignment(JLabel.TOP);
        // titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(0, 0, 400, 400);
        frame.add(titleLabel);

        // create
        start = new JButton();
        ImageIcon play = new ImageIcon(
                new ImageIcon("cpt/play.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        start.setIcon(play);
        start.addActionListener(new BlockBlast());
        start.setBounds(135, 500, 130, 50);
        start.setFocusable(false);
        start.setText("Start");
        start.setFont(new Font("Comic Sans", Font.BOLD, 30));
        start.setForeground(Color.white); 
        start.setContentAreaFilled(true);
        start.setOpaque(true);
        start.setBackground(Color.orange);
        start.setBorderPainted(false);
        frame.add(start);

        // render the frame
        frame.setVisible(true); // make frame visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            System.out.println("clicked!");
        }
    }
}
