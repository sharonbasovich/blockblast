import java.awt.*;
import java.io.*;
import javax.swing.*;

public class falling {
    ImageIcon[] pieces = new ImageIcon[17];
    File pieceFolder = new File("pieces");
    private int counter = -1;

    File[] files = pieceFolder.listFiles();

    public falling() {
        for (int i = 0; i < files.length; i++) {
            pieces[i] = new ImageIcon(files[i].getAbsolutePath());
            int width = pieces[i].getIconWidth();
            int height = pieces[i].getIconHeight();
            pieces[i] = new ImageIcon(
                    new ImageIcon(files[i].getAbsolutePath()).getImage().getScaledInstance(width / 2, height / 2,
                            Image.SCALE_SMOOTH));

        }
        System.out.println("ran");
    }

    public ImageIcon getNextPiece() {
        // JLabel piece = new JLabel(pieces[counter]);
        // piece.setBounds(300, 600, 80, 80);
        counter++;
        if (counter  == 17) {
            counter = 0;
        }
        return pieces[counter];
    }
}
