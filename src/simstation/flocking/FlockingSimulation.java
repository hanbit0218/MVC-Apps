/**
 * Author: David Song
 * Date: 04/12/2023
 *
 * this class was written by David Song
 * [ except for some of the code which was already given by professor :) ]
 */

package simstation.flocking;

import mvc.*;
import simstation.*;
import simstation.prisonersdilemma.PrisonerFactory;

import javax.swing.*;

import java.awt.*;
import java.util.*;

public class FlockingSimulation extends Simulation
{
    //instance variable(s)


    //constructor(s)


    //accessor(s)


    public void stats()
    {
        //System.out.println(agents.toString());

        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        String[] result = null;

        for(Agent bird : agents)
        {
            Bird temp = (Bird)bird;
            int tempSpeed = temp.getSpeed();
            //System.out.println(tempSpeed);

            if(tempSpeed == 1)
            {
                one += 1;
            }

            if(tempSpeed == 2)
            {
                two += 1;
            }

            if(tempSpeed == 3)
            {
                three += 1;
            }

            if(tempSpeed == 4)
            {
                four += 1;
            }

            if(tempSpeed == 5)
            {
                five += 1;
            }
        }

        JOptionPane.showMessageDialog(null,
                "#birds @ speed 1: " + one + "\n#birds @ speed 2: " + two + "\n#birds @ speed 3: " + three + "\n#birds @ speed 4: " + four + "\n#birds @ speed 5: " + five,
                "#birds per speed",
                JOptionPane.INFORMATION_MESSAGE);
    }

    //mutator(s)
    public void populate()
    {
        for(int i = 1; i < 21; i++)
        {
            Bird newBird = new Bird();
            int rand = Utilities.rng.nextInt(3);
            if (rand==0) newBird.setColor(Color.BLACK);
            if (rand==1) newBird.setColor(Color.BLUE);
            if (rand==2) newBird.setColor(Color.orange);
            
            addAgent(newBird);
            System.out.println("added birds: " + i + " with speed " + newBird.getSpeed());
        }
    }

    //main
    public static void main(String[] args)
    {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }
}
