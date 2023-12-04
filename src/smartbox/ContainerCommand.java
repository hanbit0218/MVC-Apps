package smartbox;
import mvc.*;

import java.lang.invoke.SwitchPoint;

public class ContainerCommand extends Command {
    boolean add = false;
    boolean rem = false;
    boolean run = false;
    public ContainerCommand(Model m, String command) {
        super(m);
        switch (command){
            case "add": {
                add = true;
                break;
            }
            case "rem": {
                rem = true;
                break;
            }
            case "run": {
                run = true;
                break;
            }
        }
    }

    @Override
    public void execute() throws Exception {
        Container c = (Container) model;
        if (add) {
            String component = Utilities.ask("What component to add?");
            c.addComponent(component);
        }
        if (rem) {
            String component = Utilities.ask("What component to remove?");
            c.remComponent(component);
        }
        if (run) {
            String component = Utilities.ask("What component to run?");
            c.launch(component);
        }
    }
}
