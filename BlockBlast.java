import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlockBlast implements ActionListener {

    static JButton start;
    static JTextField name;
    static JLabel username;
    static JLabel error;
    boolean flag = false;
    static String user;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Block Blast - Sharon and Yichen"); // set name of app
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit program when closing window
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
        JLabel titleLabel = new JLabel();
        titleLabel.setIcon(title);
        // titleLabel.setVerticalAlignment(JLabel.TOP);
        // titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(0, 0, 400, 400);
        frame.add(titleLabel);

        // create
        start = new JButton();
        ImageIcon play = new ImageIcon(
                new ImageIcon("play.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        start.setIcon(play);
        start.addActionListener(new BlockBlast());
        start.setBounds(130, 400, 140, 50);
        start.setFocusable(false);
        start.setText("Start");
        start.setFont(new Font("SansSerif", Font.BOLD, 30));
        start.setForeground(Color.white); 
        start.setContentAreaFilled(true);
        start.setOpaque(true);
        start.setBackground(Color.orange);
        start.setBorderPainted(false);
        start.setIconTextGap(10);
        frame.add(start);

        //create username prompt
        username = new JLabel();
        username.setText("Username:");
        username.setForeground(Color.white);
        username.setBounds(105, 480, 200, 50);
        username.setFont(new Font("SansSerif", Font.BOLD, 25));
        username.setVisible(false);

        frame.add(username);


        // create name input box
        name = new JTextField();
        name.setBounds(100, 530, 200, 50);
        name.setVisible(false);
        name.setBackground(Color.orange);
        name.setCaretColor(Color.white);
        name.setForeground(Color.white);
        name.setFont(new Font("SansSerif", Font.BOLD, 30));
        name.setBorder(BorderFactory.createCompoundBorder(name.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        frame.add(name);

        // create error message
        error = new JLabel();
        error.setText("<html><body>Name must be at least<br>one character</body></html>");
        error.setForeground(Color.red);
        error.setBounds(105, 555, 200, 100);
        error.setFont(new Font("SansSerif", Font.BOLD, 15));
        error.setVisible(false);

        frame.add(error);

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
            flag = true;
        } else if (e.getSource() == start && flag) {
            System.out.println("Validate username");
            if(name.getText().length() == 0) {
                error.setVisible(true);
                System.out.println("Invalid");
            } else {
                user = name.getText();
            }
        }
    }
}
