package simstation.plague;

import mvc.Model;
import simstation.SimulationFactory;

class PlagueFactory extends SimulationFactory {
    public Model makeModel() { return new PlagueSimulation(); }
    @Override
    public String about() {
        return "A simulation for testing spread of plague";
    }
    public String getTitle() { return "Plague";}
}