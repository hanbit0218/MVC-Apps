/**
 * Author: David Song
 * Date: 04/12/2023
 *
 * this class was written by David Song
 * [ except for some of the code which was already given by professor :) ]
 */

package simstation.flocking;

import simstation.Agent;
import simstation.Heading;


import java.util.*;

public class Bird extends Agent
{
    //instance variable(s)
    private int speed;


    //constructor(s)
    public Bird()
    {
        Random rand = new Random();
        int randSpeed = rand.nextInt(5) + 1;

        this.speed = randSpeed;
        this.heading = Heading.random();
    }

    //accessor(s)
    public int getSpeed()
    {
        return speed;
    }

    //mutator(s)
    public void setSpeed(int s)
    {
        speed = s;
    }

    public void update()
    {

        Bird neighbor = (Bird)(world.getNeighbor(this, 20));
        if(neighbor != null)
        {
            this.speed = neighbor.speed;
            this.heading = neighbor.heading;
        }
        move(speed);
    }
}
