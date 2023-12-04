package simstation;
import mvc.*;

import java.lang.invoke.SwitchPoint;

public class SimulationCommand extends Command {
    boolean start = false;
    boolean suspend = false;
    boolean resume = false;
    boolean stop = false;
    boolean stats = false;
    public SimulationCommand(Model m, String command) {
        super(m);
        switch (command){
            case "Start": {
                start = true;
                break;
            }
            case "Suspend": {
                suspend = true;
                break;
            }
            case "Resume": {
                resume = true;
                break;
            }
            case "Stop": {
                stop = true;
                break;
            }
            case "Stats": {
                stats = true;
                break;
            }
        }
    }

    @Override
    public void execute() throws Exception {
        Simulation s = (Simulation) model;
        if (start) s.start();
        if (suspend) s.suspend();
        if (resume) s.resume();
        if (stop) s.stop();
        if (stats) s.stats();
    }
}
