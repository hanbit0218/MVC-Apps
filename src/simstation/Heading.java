//100% of this class was written by Danny Matlob
package simstation;
import mvc.Utilities;

public enum Heading {
    NORTH,
    NORTHWEST,
    WEST,
    SOUTHWEST,
    SOUTH,
    SOUTHEAST,
    EAST,
    NORTHEAST,
    ;

    public static Heading random() {
        int headingNum = Utilities.rng.nextInt(4);
        switch (headingNum) {
            case 0:  {
                return NORTH;
            }
            case 1: {
                return SOUTH;
            }
            case 2: {
                return EAST;
            }
            case 3: {
                return WEST;
            }
        }
        return null;
    }
}
