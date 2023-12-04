//100% of this class was written by Danny Matlob

package minefield;
import mvc.*;

public class MoveCommand extends Command {
    Heading heading;

    public MoveCommand(Model model, Heading h) {
        super(model);
        heading = h;
    }
    public void execute() {
        Minefield mineModel = (Minefield) model;
        try {
            System.out.println(heading);
            switch (heading) {
                //File Functions
                case NORTH: {
                    mineModel.movePlayer(0, -1);
                    break;
                }
                case SOUTH: {
                    mineModel.movePlayer(0, 1);
                    break;
                }
                case EAST: {
                    mineModel.movePlayer(1, 0);
                    break;
                }
                case WEST: {
                    mineModel.movePlayer(-1, 0);
                    break;
                }
                case NORTHEAST: {
                    mineModel.movePlayer(1, -1);
                    break;
                }
                case NORTHWEST: {
                    mineModel.movePlayer(-1, -1);
                    break;
                }
                case SOUTHEAST: {
                    mineModel.movePlayer(1, 1);
                    break;
                }
                case SOUTHWEST: {
                    mineModel.movePlayer(-1, 1);
                    break;
                }
                default: {
                    System.out.println("Invalid Heading Error");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
