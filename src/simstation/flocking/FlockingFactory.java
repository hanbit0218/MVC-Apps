/**
 * Author: David Song
 * Date: 04/12/2023
 *
 * this class was written by David Song
 * [ except for some of the code which was already given by professor :) ]
 */

package simstation.flocking;

import mvc.Model;
import simstation.SimulationFactory;
import simstation.randomwalk.RandomWalkSimulation;

public class FlockingFactory extends SimulationFactory
{
    public Model makeModel() { return new FlockingSimulation(); }
    public String getTitle() { return "Flocking";}

    public String about() {return "this is a flocking simulator";}
}
