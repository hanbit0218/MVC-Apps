//100% of this class was written by Danny Matlob
package minefield;

import mvc.*;
import java.awt.*;

public class MinefieldView extends View {

    public MinefieldView(Minefield mf) {
        super(mf);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        Minefield mf = (Minefield) model;
        MinefieldShape shape = new MinefieldShape(mf);
        shape.draw((Graphics2D) gc);
        gc.setColor(oldColor);
    }
}
