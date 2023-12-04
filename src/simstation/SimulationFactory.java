package simstation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class SimulationFactory implements AppFactory {
    String[] commands = new String[] {"Start","Suspend","Resume","Stop","Stats"};
    @Override
    public Model makeModel() {
        return null;
    }

    @Override
    public View makeView(Model m) {
        return new SimulationView((Simulation) m);
    }

    @Override
    public String getTitle() {
        return "Simstation";
    }

    @Override
    public String[] getHelp() {
        return new String[] { "Start: begins the simulation","Suspend: pauses the simulation", "Resume: resumes the simulation", "Stop: halts the simulation"};
    }

    @Override
    public String about() {
        return "A framework for testing simulations";
    }

    @Override
    public String[] getEditCommands() {
        return commands;
    }

    @Override
    public Command makeEditCommand(Model m, String name, Object source) {
        for (String c : commands) {
            if (c.equals(name)) {
                return new SimulationCommand(m, name);
            }
        }
        return null;
    }
}
