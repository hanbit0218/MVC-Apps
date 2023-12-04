package simstation.plague;

import mvc.AppPanel;
import mvc.Utilities;
import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationPanel;
import simstation.plague.*;

import javax.swing.*;
import java.awt.*;

public class PlagueSimulation extends Simulation {
    public static int INITIAL_INFECTED = 10; // % of population infected
    public static int VIRULENCE = 50; // % chance of infection
    public static int RESISTANCE = 2; // % chance of resisting infection
    public static int TOTAL_POPULATION = 20;

    public void populate() {
        for(int i = 0; i < TOTAL_POPULATION; i++) {
            Person p = new Person();
            p.setWorld(this);
            p.setColor(Color.GREEN);
            if (Utilities.rng.nextInt(100) <= INITIAL_INFECTED) {
                p.infect();
            }
            agents.add(p);
        }
    }

    public void stats()
    {
        int totalInfected = 0;
        int totalHealthy = 0;
        for(Agent p : agents) {
            Person person = (Person) p;
            if (person.isInfected()) {
                totalInfected++;
            } else {
                totalHealthy++;
            }
        }

        int percentInfected = 100*totalInfected/TOTAL_POPULATION;
        int percentHealthy = 100*totalHealthy/TOTAL_POPULATION;
        JOptionPane.showMessageDialog(null, "Agents: "+agents.size() + "\nClock: " + clock + "\nInfected %: " + percentInfected + "\nHealthy %: " + percentHealthy);
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }

}