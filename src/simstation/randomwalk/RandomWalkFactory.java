package simstation.randomwalk;

import mvc.Model;
import simstation.SimulationFactory;

class RandomWalkFactory extends SimulationFactory {
    public Model makeModel() { return new RandomWalkSimulation(); }
    public String getTitle() { return "Random Walks";}
}