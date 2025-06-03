import javax.swing.*;
import java.awt.*;

public class BlockTile extends JComponent{
    private boolean topCorner;
    public boolean isTopCorner() {
        return topCorner;
    }
    private int x;
    private int y;
    private String color;
    public BlockTile(int x, int y,String color, boolean topCorner){
        this.x = x;
        this.y = y;
        this.color = color;
        this.topCorner = topCorner;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.decode(color));
        g.fillRect(x, y, 40, 40);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 40, 40);
    }
}
