package smartbox;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class ContainerFactory implements AppFactory {
    public Model makeModel() {
        return new Container();
    }

    public View makeView(Model m) {
        return new ContainerView(m);
    }

    public String getTitle() {
        return "Smart Box";
    }

    public String[] getHelp() {
        return new String[] {"Add: adds component","Rem: removes component","Run: runs component"};
    }

    public String about() {
        return "Danny Matlob Smart Box, Spring 2023";
    }

    public String[] getEditCommands() {
        return new String[]{"Add","Rem","Run"};
    }

    public Command makeEditCommand(Model model, String type, Object source) {
        type = type.toLowerCase();
        if (type.equals("add")) return new ContainerCommand(model, "add");
        if (type.equals("rem")) return new ContainerCommand(model, "rem");
        if (type.equals("run")) return new ContainerCommand(model, "run");
        return null;
    }
}
