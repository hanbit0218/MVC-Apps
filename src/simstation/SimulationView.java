package simstation;

import mvc.*;

import java.awt.*;
import java.util.Iterator;

public class SimulationView extends View {
    public SimulationView(Simulation s) {
        super(s);
    }

    public void paintComponent(Graphics gc) {
        Simulation s = (Simulation) model;
        super.paintComponent(gc);
        Color oldColor = gc.getColor();

        Iterator<Agent> agentIterator = s.iterator();
        while (agentIterator.hasNext()) {
            Agent a = agentIterator.next();
            gc.setColor(a.getColor());
            int x = a.getX();
            int y = a.getY();
            gc.fillOval(x, y, 15, 15);
        }
        gc.setColor(oldColor);
    }
}
