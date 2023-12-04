package simstation.plague;

import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;
import java.awt.*;




class Person extends Agent {
    boolean infected = false;
    public Person() {
        super();
        heading = Heading.random();
    }

    public void update() {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);

        if (!infected) return;
        Person victim = (Person) (world.getNeighbor(this, 10));
        if (victim==null) return;

        PlagueSimulation ps = (PlagueSimulation) world;
        if (Utilities.rng.nextInt(100)<=ps.VIRULENCE || //Chance to infect
                Utilities.rng.nextInt(100)<=(100-ps.RESISTANCE)) { //Inverse of Chance to resist infection
            victim.infect();
        }
    }

    public void infect() {
        setColor(Color.RED);
        infected = true;
    }
    public boolean isInfected() {
        return infected;
    }

}





