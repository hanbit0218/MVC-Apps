/**
 * Author: David Song
 * Date: 04/11/2023
 *
 * this class was written by David Song
 * [ except for some of the code which was already given by professor :) ]
 */

package simstation.prisonersdilemma;

import simstation.Agent;

import java.util.Random;

public class RandomlyCooperate extends Strategy
{
    private String type = "RandCooperate";
    public boolean cooperate()
    {
        Random rd = new Random();
        return rd.nextBoolean();
    }

    public String getType() {
        return type;
    }
}
