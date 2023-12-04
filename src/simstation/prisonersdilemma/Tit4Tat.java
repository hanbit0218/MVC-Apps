/**
 * Author: David Song
 * Date: 04/11/2023
 *
 * this class was written by David Song
 * [ except for some of the code which was already given by professor :) ]
 */

package simstation.prisonersdilemma;

public class Tit4Tat extends Strategy
{
    private String type = "Tit4Tat";
    public boolean cooperate()
    {
        if (prisoner.isPartnerCheated()) {
            return false;
        }
        return true;
    }

    public String getType() {
        return type;
    }
}
