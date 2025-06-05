import javax.swing.*;

import src.GameLoop;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BlockBlast implements ActionListener {

    static JButton start;
    static JTextField name;
    static JLabel username;
    static JLabel error;
    boolean flag = false;
    static String user;
    static JButton viewLeaderboard;
    static JLabel titleLabel;
    static JFrame frame;
    static JButton back;
    static JScrollPane scrollPane;
    static Timer time;
    static JLabel box;
    static falling piece;
    static JLabel titleLeaderboard;

    public static void main(String[] args) {

        String[] test = { "Sharon", "6" };
        new Leaderboard(test);

        frame = new JFrame();
        frame.setTitle("Block Blast - Sharon and Yichen"); // set name of app
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exi program when closing window
        frame.setResizable(false); // prevent resizing
        frame.setSize(400, 800); // set dimensions of window
        frame.setLayout(null);
        // set app icon
        ImageIcon icon = new ImageIcon("logo.png");
        frame.setIconImage(icon.getImage());

        // set background color
        frame.getContentPane().setBackground(new Color(0x1559c1));

        // create title logo
        ImageIcon title = new ImageIcon(
                new ImageIcon("title.png").getImage().getScaledInstance(386, 398, Image.SCALE_SMOOTH));
        titleLabel = new JLabel();
        titleLabel.setIcon(title);
        // titleLabel.setVerticalAlignment(JLabel.TOP);
        // titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(0, 0, 385, 385);

        frame.add(titleLabel);

        // create
        start = new JButton();
        ImageIcon play = new ImageIcon(
                new ImageIcon("play.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        start.setIcon(play);
        start.addActionListener(new BlockBlast());
        start.setBounds(85, 540, 230, 50);
        start.setFocusable(false);
        start.setText("Start");
        start.setFont(new Font("SansSerif", Font.BOLD, 30));
        start.setForeground(Color.white);
        start.setContentAreaFilled(true);
        start.setOpaque(true);
        start.setBackground(new Color(0xeea018));
        start.setBorderPainted(false);
        start.setIconTextGap(10);

        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                start.setBackground(Color.GREEN); // Change color on hover
                start.setBounds(75, 530, 250, 70);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                start.setBounds(85, 540, 230, 50);

                start.setBackground(new Color(0xeea018)); // Restore original color
            }
        });


        frame.add(start);

        // create username prompt
        username = new JLabel();
        username.setText("Username:");
        username.setForeground(Color.white);
        username.setBounds(105, 600, 200, 50);
        username.setFont(new Font("SansSerif", Font.BOLD, 25));
        username.setVisible(false);

        frame.add(username);

        // create name input box
        name = new JTextField();
        name.setBounds(100, 650, 200, 50);
        name.setVisible(false);
        name.setBackground(Color.orange);
        name.setCaretColor(Color.white);
        name.setForeground(Color.white);
        name.setFont(new Font("SansSerif", Font.BOLD, 30));
        name.setBorder(
                BorderFactory.createCompoundBorder(name.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        frame.add(name);

        // create error message
        error = new JLabel();
        error.setText("<html><body>Name must be at least<br>one character</body></html>");
        error.setForeground(Color.red);
        error.setBounds(105, 675, 200, 100);
        error.setFont(new Font("SansSerif", Font.BOLD, 15));
        error.setVisible(false);

        frame.add(error);

        // create leaderboard button
        viewLeaderboard = new JButton();
        ImageIcon view = new ImageIcon(
                new ImageIcon("leaderboard.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        viewLeaderboard.setIcon(view);
        viewLeaderboard.addActionListener(new BlockBlast());
        viewLeaderboard.setBounds(85, 610, 230, 50);
        viewLeaderboard.setFocusable(false);
        viewLeaderboard.setText("Leaderboard");
        viewLeaderboard.setFont(new Font("SansSerif", Font.BOLD, 25));
        viewLeaderboard.setForeground(Color.white);
        viewLeaderboard.setContentAreaFilled(true);
        viewLeaderboard.setOpaque(true);
        viewLeaderboard.setBackground(new Color(0x19cba4));
        viewLeaderboard.setBorderPainted(false);
        viewLeaderboard.setIconTextGap(10);

        viewLeaderboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                viewLeaderboard.setBackground(Color.GREEN); // Change color on hover
                viewLeaderboard.setBounds(75, 600, 250, 70);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                viewLeaderboard.setBounds(85, 610, 230, 50);

                viewLeaderboard.setBackground(new Color(0x19cba4)); // Restore original color
            }
        });

        frame.add(viewLeaderboard);

        // create back button
        back = new JButton();
        ImageIcon backButton = new ImageIcon(
                new ImageIcon("back.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        back.setIcon(backButton);
        back.setVisible(true);
        back.setBounds(20, 20, 60, 60);
        back.setFocusable(false);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.addActionListener(new BlockBlast());

        // create switching piece animation
        time = new Timer(400, new BlockBlast());
        time.start();

        piece = new falling();

        box = new JLabel();
        box.setBounds(125, 370, 150, 150);
        box.setHorizontalAlignment(SwingConstants.CENTER);
        box.setIcon(piece.getNextPiece());
        frame.add(box);

        // leaderboard title
        titleLeaderboard = new JLabel("Leaderboard");
        // titleLeaderboard.setVisible(false);
        titleLeaderboard.setBounds(90, 10, 200, 70);
        // titleLeaderboard.setHorizontalAlignment(SwingConstants.CENTER);
        titleLeaderboard.setFocusable(false);
        titleLeaderboard.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLeaderboard.setForeground(Color.white);
        titleLeaderboard.setOpaque(false);

        // render the frame
        frame.setVisible(true); // make frame visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start && (!flag)) {
            System.out.println("Show username");
            start.setText("Go!");
            name.setVisible(true);
            username.setVisible(true);
            viewLeaderboard.setVisible(false);
            flag = true;
        } else if (e.getSource() == start && flag) {
            System.out.println("Validate username");
            if (name.getText().length() == 0) {
                error.setVisible(true);
                System.out.println("Invalid");
            } else {
                user = name.getText();

                start.setVisible(false);
                viewLeaderboard.setVisible(false);
                titleLabel.setVisible(false);
                box.setVisible(false);
                name.setVisible(false);
                username.setVisible(false);
                new GameLoop(frame, user);
            }
        } else if (e.getSource() == viewLeaderboard) {
            start.setVisible(false);
            viewLeaderboard.setVisible(false);
            titleLabel.setVisible(false);
            box.setVisible(false);
            // titleLeaderboard.setVisible(true);

            String[][] leaderboard;

            try {
                BufferedReader lineCounter = new BufferedReader(new FileReader("leaderboard.txt"));
                int lineCount = 0;
                while (lineCounter.readLine() != null) {
                    lineCount++;
                }

                lineCounter.close();

                leaderboard = new String[lineCount][2];

                BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"));

                int i = 0;

                for (int j = 0; j < leaderboard.length; j++) {
                    for (int j2 = 0; j2 < leaderboard[j].length; j2++) {
                        leaderboard[j][j2] = "";
                    }
                }

                while (reader.ready()) {
                    leaderboard[i] = reader.readLine().split(",");
                    if (leaderboard[i][0].length() > 8) {
                        leaderboard[i][0] = leaderboard[i][0].substring(0, 8);
                    }
                    i++;
                }
                reader.close();

                JPanel leaderboardPanel = new JPanel();
                leaderboardPanel.add(titleLeaderboard);
                leaderboardPanel.setLayout(null);
                leaderboardPanel.setPreferredSize(new Dimension(400, lineCount * 70 + 100));
                leaderboardPanel.setBackground(new Color(0x1559c1));
                JLabel[] entries = new JLabel[lineCount];
                for (int k = 0; k < entries.length; k++) {
                    entries[k] = new JLabel();

                    String spacing = "";
                    if (k < 9) {
                        spacing = "  ";
                    }

                    entries[k].setHorizontalAlignment(SwingConstants.LEFT);
                    if (k == 0) {
                        entries[k].setText(spacing + (k + 1) + ": " + leaderboard[k][0]
                                + "  ".repeat(10 - leaderboard[k][0].length()) + " "
                                + leaderboard[k][1]);
                    } else {
                        entries[k].setText(spacing + (k + 1) + ": " + leaderboard[k][0]
                                + "  ".repeat(10 - leaderboard[k][0].length())
                                + leaderboard[k][1]);
                    }
                    entries[k].setForeground(Color.white);
                    entries[k].setBounds(75, 70 + (70 * k), 250, 50);
                    entries[k].setFont(new Font("SansSerif", Font.BOLD, 25));
                    entries[k].setVisible(true);

                    if (k == 0) {
                        entries[k].setForeground(new Color(0xFFD700));
                    } else if (k == 1) {
                        entries[k].setForeground(new Color(0xC0C0C0));
                    } else if (k == 2) {
                        entries[k].setForeground(new Color(0xCD7F32));
                    }

                    leaderboardPanel.add(back);
                    leaderboardPanel.add(entries[k]);
                }

                scrollPane = new JScrollPane(leaderboardPanel);
                scrollPane.setBounds(0, 0, 400, 800);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                frame.add(scrollPane);

            } catch (Exception exception) {

            }
        } else if (e.getSource() == back) {
            System.out.println("go back");
            scrollPane.setVisible(false);
            titleLabel.setVisible(true);
            start.setVisible(true);
            viewLeaderboard.setVisible(true);
            box.setVisible(true);
            // titleLeaderboard.setVisible(false);

        } else if (e.getSource() == time) {
            // System.out.println("time");
            box.setIcon(piece.getNextPiece());
        }
    }
}
