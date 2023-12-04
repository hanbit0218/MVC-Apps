/**
 * Author: David Song
 * Date: 04/11/2023
 *
 * this class was written by David Song
 * [ except for some of the code which was already given by professor :) ]
 */

package simstation.prisonersdilemma;

public abstract class Strategy
{
    Prisoner prisoner;
    private String type;
    abstract boolean cooperate();
    abstract String getType();
}
