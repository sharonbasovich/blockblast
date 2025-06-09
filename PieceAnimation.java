import java.awt.*;
import java.io.*;
import javax.swing.*;

public class PieceAnimation {
    // create an image array to store all the pieces
    private ImageIcon[] pieces = new ImageIcon[17];

    // access the file with all the pieces
    private File pieceFolder = new File("pieces");
    private int counter = -1;
    private File[] files = pieceFolder.listFiles();

    public PieceAnimation() {
        // for each file in the piececs folder, set one of the image element array slots
        // to
        // that file
        for (int i = 0; i < files.length; i++) {
            pieces[i] = new ImageIcon(files[i].getAbsolutePath());
            int width = pieces[i].getIconWidth();
            int height = pieces[i].getIconHeight();
            // resize the images
            pieces[i] = new ImageIcon(
                    new ImageIcon(files[i].getAbsolutePath()).getImage().getScaledInstance(width / 2, height / 2,
                            Image.SCALE_SMOOTH));

        }
    }

    public ImageIcon getNextPiece() {
        // when the main program requests another piece for the animation send them one
        counter++;
        // if at the end of the list, go back to the start
        if (counter == 17) {
            counter = 0;
        }
        return pieces[counter];
    }
}
