//100% of this class was written by Danny Matlob
package minefield;

import java.awt.*;
import java.util.Iterator;

public class MinefieldShape {
    private Minefield minefield;
    public MinefieldShape(Minefield mf) {
        minefield = mf;
    }
    public void draw(Graphics2D gc) {
        Color oldColor = gc.getColor();
        Iterator mineIterator = minefield.iterator();
        while (mineIterator.hasNext()) {
            MineSquare mine = (MineSquare) mineIterator.next();
            mine.draw(gc);
        }
        gc.setColor(oldColor);
    }
}
