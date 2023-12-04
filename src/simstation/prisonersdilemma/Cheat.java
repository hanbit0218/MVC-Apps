/**
 * Author: David Song
 * Date: 04/11/2023
 *
 * this class was written by David Song
 * [ except for some of the code which was already given by professor :) ]
 */

package simstation.prisonersdilemma;

public class Cheat extends Strategy
{
    private String type = "Cheat";
    public boolean cooperate()
    {
        return false;
    }

    public String getType() {
        return type;
    }
}
