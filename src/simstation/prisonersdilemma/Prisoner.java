/**
 * Author: David Song
 * Date: 04/11/2023
 *
 * this class was written by David Song
 * [ except for some of the code which was already given by professor :) ]
 */

package simstation.prisonersdilemma;

import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;
import simstation.Simulation;

class Prisoner extends Agent
{
    //instance variable(s)
    private int fitness;
    private boolean partnerCheated;
    Strategy strategy;


    //constructor(s)
    public Prisoner(Strategy strategy)
    {
        this.fitness = 0;
        this.partnerCheated = false;
        this.strategy = strategy;
        strategy.prisoner = this;
    }



    //accessor
    public int getFitness() {
        return fitness;
    }

    public boolean isPartnerCheated() {
        return partnerCheated;
    }

    public Strategy getS() {
        return strategy;
    }

    public boolean cooperate() { return true; }

    public Strategy getStrategy() {
        return strategy;
    }

    //mutator
    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void setPartnerCheated(boolean partnerCheated) {
        this.partnerCheated = partnerCheated;
    }

    public void setS(Strategy s) {
        this.strategy = s;
    }

    public void update()
    {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);

        Prisoner opponent = (Prisoner)(world.getNeighbor(this, 10));
        if(opponent != null) {
            boolean thisCooperated = strategy.cooperate();
            boolean enemyCooperated = opponent.cooperate();
            setPartnerCheated(!enemyCooperated);
            if (thisCooperated && enemyCooperated) {
                updateFitness(3);
                opponent.updateFitness(3);
            } else if (thisCooperated && !enemyCooperated) {
                updateFitness(0);
                opponent.updateFitness(5);
            } else if (!thisCooperated && enemyCooperated) {
                updateFitness(5);
                opponent.updateFitness(0);
            } else if (!thisCooperated && !enemyCooperated) {
                updateFitness(1);
                opponent.updateFitness(1);
            }
        }
    }


    public void updateFitness(int amt) {
        this.fitness += amt;
    }
}