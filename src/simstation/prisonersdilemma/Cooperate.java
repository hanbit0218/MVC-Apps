/**
 * Author: David Song
 * Date: 04/11/2023
 *
 * this class was written by David Song
 * [ except for some of the code which was already given by professor :) ]
 */

package simstation.prisonersdilemma;

public class Cooperate extends Strategy {
    private String type = "Cooperate";
    public boolean cooperate()
    {
        return true;
    }

    public String getType() {
        return type;
    }
}
