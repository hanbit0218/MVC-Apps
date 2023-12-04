/**
 * Author: David Song
 * Date: 04/11/2023
 *
 * this class was written by David Song
 * [ except for some of the code which was already given by professor :) ]
 */

package simstation.prisonersdilemma;

import mvc.*;
import simstation.*;
import simstation.randomwalk.RandomWalkSimulation;

public class PrisonerFactory extends SimulationFactory
{
    public Model makeModel() { return new PrisonerSimulation(); }
    public String getTitle() { return "Prisoners Dilemma";}
    @Override
    public String about() {
        return "A simulation for testing different strategies of prisoner's dilemma";
    }
}