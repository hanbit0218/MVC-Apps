//70% of this class was written by Danny Matlob
//30% of this class was written by Aayush
package minefield;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class MineSquare implements Serializable {
    int w;
    int x, y;
    int neighborMines;
    boolean goal = false;
    boolean playerHere = false;
    boolean revealed = false;
    Minefield mf;
    boolean hasMine;
    private Rectangle2D.Double mine;

    public MineSquare(int x, int y, int w, boolean hasMine, Minefield m) {
        mf = m;
        this.x = x;
        this.y = y;
        this.w = w;
        this.hasMine = hasMine;
        mine = new Rectangle2D.Double(x, y, w, w);
    }
    public void setGoal() {
        goal = true;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean hasMine() {
        return hasMine;
    }
    public void setMine(boolean b) {
        hasMine = b;
    }

    public void incrementNeighbors() {
        neighborMines++;
    }
    public void setPlayerHere(boolean b) {
        playerHere = b;
    }
    public void draw(Graphics2D gc) {
        Color oldColor = gc.getColor();
        Stroke oldStroke = gc.getStroke();
        gc.setStroke(new BasicStroke(2));
        if (revealed) {
            gc.setColor(Color.white);
            gc.fill(mine);
        } else {
            gc.setColor(Color.GRAY);
            gc.fill(mine);
        }
        gc.setColor(Color.BLACK);
        if (playerHere) gc.setColor(Color.RED);
        if (goal) gc.setColor(Color.GREEN);
        gc.draw(mine);

        gc.setColor(Color.BLACK);
        if (revealed) {
            switch (neighborMines){
                case 0: {break;}
                case 1: {gc.setColor(Color.BLUE); break;    }
                case 2: {gc.setColor(Color.decode("#136123"));break;}
                case 3: {gc.setColor(Color.RED);break;}
                case 4: {gc.setColor(Color.ORANGE);break;}
                case 5: {gc.setColor(Color.MAGENTA);break;}
            }
            gc.drawString(String.valueOf(neighborMines), x+w/2 - 2, y+w/2 + 2);
        } else {
            gc.drawString("?", x+w/2 - 2, y+w/2 + 2);
        }

        if (revealed && hasMine || (mf.isGameOver()&&hasMine)) {
            gc.setColor(Color.RED);
            gc.fill(mine);
        }

        gc.setColor(oldColor);
        gc.setStroke(oldStroke);
    }
}
