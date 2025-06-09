// import dependencies and libraries
// this includes java swing, the basis for the game

import javax.swing.*;

import src.GameLoop;
import src.Leaderboard;
import src.Sound;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BlockBlast implements ActionListener {

    // ui elements
    static JButton start;
    static JTextField name;
    static JLabel username;
    static JLabel error;
    static JButton viewLeaderboard;
    static JLabel titleLabel;
    static JFrame frame;
    static JButton back;
    static JScrollPane scrollPane;
    static Timer time;
    static JLabel box;
    static JLabel titleLeaderboard;
    static PieceAnimation piece;

    // flag to track start button state
    static boolean flag = false;

    // string to store username
    static String user;

    public static void main(String[] args) {

        // on startup, play background music
        Sound sound = new Sound();
        sound.play(0);

        // create the viewing window
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

        // create and resize title logo
        ImageIcon title = new ImageIcon(
                new ImageIcon("title.png").getImage().getScaledInstance(386, 398, Image.SCALE_SMOOTH));
        titleLabel = new JLabel();
        titleLabel.setIcon(title);
        titleLabel.setBounds(0, 0, 385, 385);

        // add title logo to frame
        frame.add(titleLabel);

        // create the start button
        start = new JButton();

        // set the image, text, and other styling of the button
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

        // override the mouseEntered and mouseExited functions to create custom hover
        // effects
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

        // create text to prompt for username
        username = new JLabel();

        // set text and styling for username prompt
        username.setText("Username:");
        username.setForeground(Color.white);
        username.setBounds(105, 600, 200, 50);
        username.setFont(new Font("SansSerif", Font.BOLD, 25));
        username.setVisible(false);

        frame.add(username);

        // create the input box for the username
        name = new JTextField();

        // style the input box
        name.setBounds(100, 650, 200, 50);
        name.setVisible(false);
        name.setBackground(Color.orange);
        name.setCaretColor(Color.white);
        name.setForeground(Color.white);
        name.setFont(new Font("SansSerif", Font.BOLD, 30));

        // use BorderFactory to customize the border
        name.setBorder(
                BorderFactory.createCompoundBorder(name.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        frame.add(name);

        // create error message to inform user if text box is empty
        error = new JLabel();

        // use inline html to add line breaks to text
        error.setText("<html><body>Name must be at least<br>one character</body></html>");

        // error message styling
        error.setForeground(Color.red);
        error.setBounds(105, 675, 200, 100);
        error.setFont(new Font("SansSerif", Font.BOLD, 15));
        error.setVisible(false);

        frame.add(error);

        // create leaderboard button
        viewLeaderboard = new JButton();

        // create and resize image
        ImageIcon view = new ImageIcon(
                new ImageIcon("leaderboard.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));

        // add all the styling to the leaderboard button
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

        // similarly to the start button, override the mouse listener to add hover
        // effects like size and color changes
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

        // create back button to go back to main menu from leaderboard
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

        // create and start a timer for the piece switching animation
        time = new Timer(400, new BlockBlast());
        time.start();

        // create the switching piece element
        piece = new PieceAnimation();

        // create a bounding box for the piece animation
        box = new JLabel();
        box.setBounds(125, 370, 150, 150);
        box.setHorizontalAlignment(SwingConstants.CENTER);

        // initialize it to the first piece
        box.setIcon(piece.getNextPiece());
        frame.add(box);

        // create the leaderboard title and style it
        titleLeaderboard = new JLabel("Leaderboard");
        titleLeaderboard.setBounds(90, 10, 200, 70);
        titleLeaderboard.setFocusable(false);
        titleLeaderboard.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLeaderboard.setForeground(Color.white);
        titleLeaderboard.setOpaque(false);

        // render the frame
        // this makes the window visible once all the elements have been added
        frame.setVisible(true);
    }

    // all the implementations of the buttons are in the action handler below
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start && (!flag)) {
            // if the start button is pressed, and hasn't been pressed yet, then play a
            // sound and show the username input box
            Sound button = new Sound();
            button.playOnce(4);
            start.setText("Go!");
            name.setVisible(true);
            username.setVisible(true);
            viewLeaderboard.setVisible(false);
            flag = true;
        } else if (e.getSource() == start && flag) {
            // if start button pressed second time
            if (name.getText().length() == 0) {
                // if the length is 0, the box is blank so make the error visible and play a
                // sound effect
                Sound button = new Sound();
                button.playOnce(4);
                error.setVisible(true);
            } else {
                // if the length is valid, hide all home page ui elements and start the main
                // gameloop, also play a sound
                Sound startSound = new Sound();
                startSound.playOnce(2);
                user = name.getText();
                error.setVisible(false);
                start.setVisible(false);
                viewLeaderboard.setVisible(false);
                titleLabel.setVisible(false);
                box.setVisible(false);
                name.setVisible(false);
                username.setVisible(false);
                new GameLoop(frame, user, () -> resetUI());
            }
        } else if (e.getSource() == viewLeaderboard) {
            // if the leaderboard button is pressed, hide home page elmenets and make
            // leaderboard visible
            Sound button = new Sound();
            button.playOnce(4);
            start.setVisible(false);
            viewLeaderboard.setVisible(false);
            titleLabel.setVisible(false);
            box.setVisible(false);

            // create a 2d array to hold the leaderboard information
            String[][] leaderboard;

            try {
                // create a buffered reader to go through the leaderboard and count how many
                // lines it has
                BufferedReader lineCounter = new BufferedReader(new FileReader("leaderboard.txt"));
                int lineCount = 0;
                while (lineCounter.readLine() != null) {
                    lineCount++;
                }
                lineCounter.close();

                // based on the line count, set the size of the leaderboard array
                // the format of each entry is {username, score}
                leaderboard = new String[lineCount][2];

                // go through the text file again
                BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"));

                // initialize all elements to blank values in case of crash
                for (int j = 0; j < leaderboard.length; j++) {
                    for (int j2 = 0; j2 < leaderboard[j].length; j2++) {
                        leaderboard[j][j2] = "";
                    }
                }

                // go through the text file again, and this time split each entry by a comma to
                // get the username and score
                int i = 0;
                while (reader.ready()) {
                    leaderboard[i] = reader.readLine().split(",");

                    // if the username is over 8 characters, shorten it to work with ui
                    if (leaderboard[i][0].length() > 8) {
                        leaderboard[i][0] = leaderboard[i][0].substring(0, 8);
                    }
                    i++;
                }
                reader.close();

                // create the panel to hold the leaderboard
                JPanel leaderboardPanel = new JPanel();

                // add the title to it
                leaderboardPanel.add(titleLeaderboard);
                leaderboardPanel.setLayout(null);

                // set the size based on the number of leaderboard elements previously counted
                leaderboardPanel.setPreferredSize(new Dimension(400, lineCount * 70 + 100));
                leaderboardPanel.setBackground(new Color(0x1559c1));

                // create an array to hold a JLabel for each leaderboard entry
                JPanel[] entries = new JPanel[lineCount];
                JLabel[] names = new JLabel[lineCount];
                JLabel[] scores = new JLabel[lineCount];
                leaderboardPanel.add(back);

                // set each entry to the
                for (int k = 0; k < entries.length; k++) {
                    // create the JPanels and JLabels
                    entries[k] = new JPanel();
                    names[k] = new JLabel();
                    scores[k] = new JLabel();

                    // set the text to the names and score of the leaderboard
                    names[k].setText(leaderboard[k][0]);
                    scores[k].setText(leaderboard[k][1]);

                    // align the names on the left and score on the right
                    entries[k].setLayout(new BorderLayout());
                    entries[k].add(names[k], BorderLayout.WEST);
                    entries[k].add(scores[k], BorderLayout.EAST);

                    // add styling
                    names[k].setForeground(Color.white);
                    scores[k].setForeground(Color.white);
                    entries[k].setOpaque(false);
                    entries[k].setBounds(75, 70 + (70 * k), 250, 50);
                    names[k].setFont(new Font("SansSerif", Font.BOLD, 25));
                    scores[k].setFont(new Font("SansSerif", Font.BOLD, 25));
                    entries[k].setVisible(true);

                    // for the top 3, set the text colors to gold, silver, and bronze
                    if (k == 0) {
                        names[k].setForeground(new Color(0xFFD700));
                        scores[k].setForeground(new Color(0xFFD700));
                    } else if (k == 1) {
                        names[k].setForeground(new Color(0xC0C0C0));
                        scores[k].setForeground(new Color(0xC0C0C0));
                    } else if (k == 2) {
                        names[k].setForeground(new Color(0xCD7F32));
                        scores[k].setForeground(new Color(0xCD7F32));
                    }

                    leaderboardPanel.add(entries[k]);
                }

                // create the scroll pane to allow for the user to scroll through the
                // leaderboard if it exceeds the screen dimensions
                scrollPane = new JScrollPane(leaderboardPanel);
                scrollPane.setBounds(0, 0, 400, 800);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                frame.add(scrollPane);

            } catch (Exception exception) {

            }
        } else if (e.getSource() == back) {
            // if back button is pressed, set homepage ui to visible and hide leaderboard ui
            Sound button = new Sound();
            button.playOnce(4);
            scrollPane.setVisible(false);
            titleLabel.setVisible(true);
            start.setVisible(true);
            viewLeaderboard.setVisible(true);
            box.setVisible(true);
        } else if (e.getSource() == time) {
            // every tick of the timer, switch the piece in the animation
            box.setIcon(piece.getNextPiece());
        }
    }

    public static void resetUI() {
        // callback function to set ui back to the homepage after the user quits the main gameloop
        start.setVisible(true);
        viewLeaderboard.setVisible(true);
        titleLabel.setVisible(true);
        flag = false;
    }
}
