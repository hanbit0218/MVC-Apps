//100% of this class was written by Danny Matlob
package mvc;
public class Command {

    protected Model model;
    public Command(Model m) {
        model = m;
    }
    public void execute() throws Exception{}
}
