import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BlockBlast {
    public static void init(Game game, JFrame frame) {
        JLayeredPane layeredPane = new JLayeredPane();
        // Add board panel
        BoardPanel boardPanel = new BoardPanel(game.getBoard());
        boardPanel.setBounds(0, 0, 364, 500);
        layeredPane .add(boardPanel,JLayeredPane.DEFAULT_LAYER);

        Block simpleBlock = new Block(3, 2, "#ff0000", new boolean[][] { { true, true, true }, { true, true, true } });
        simpleBlock.setBounds(5, 520, 3*40, 2*40); // Give it enough space to drag

        // BlockPanel blockPanel = new BlockPanel(simpleBlock);
        // blockPanel.setBackground(new Color(0, 0, 0, 1));
        // blockPanel.setBounds(5, 520, 364, 200); // Give it enough space to drag
        makeDraggable(simpleBlock);
        layeredPane.add(simpleBlock,JLayeredPane.DRAG_LAYER);
        layeredPane.setBounds(0, 0, 400, 800);
        frame.add(layeredPane);
    }

    public static void setupFrame(JFrame frame) {
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

    private static void makeDraggable(Component component) {
        MouseAdapter adapter = new MouseAdapter() {
            Point startPoint;

            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point location = component.getLocation();
                int x = location.x + e.getX() - startPoint.x;
                int y = location.y + e.getY() - startPoint.y;
                component.setLocation(x, y);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                component.setLocation(startPoint);
            }
        };

        component.addMouseListener(adapter);
        component.addMouseMotionListener(adapter);
    }
}
