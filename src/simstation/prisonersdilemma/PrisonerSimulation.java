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

import javax.swing.*;
import java.awt.*;

public class PrisonerSimulation extends Simulation
{
    public static final int COOPERATORS = 10;
    public static final int CHEATERS = 10;
    public static final int RANDOMCOOPERATORS = 10;
    public static final int TIT4TATTERS = 10;
    public void populate()
    {
        for(int i = 0; i < COOPERATORS; i++) {
            System.out.println("Adding cooperating prisoners: " + i);
            Agent coop = new Prisoner(new Cooperate());
            coop.setColor(Color.green);
            addAgent(coop);
        }
        for(int i = 0; i < CHEATERS; i++) {
            System.out.println("Adding cheating prisoners: " + i);
            Agent cheat = new Prisoner(new Cheat());
            cheat.setColor(Color.red);
            addAgent(cheat);
        }
        for(int i = 0; i < RANDOMCOOPERATORS; i++) {
            System.out.println("Adding randomly cooperating prisoners: " + i);
            Agent randcoop = new Prisoner(new RandomlyCooperate());
            randcoop.setColor(Color.magenta);
            addAgent(randcoop);
        }
        for(int i = 0; i < TIT4TATTERS; i++) {
            System.out.println("Adding tit4tat prisoners: " + i);
            Agent tit4tat = new Prisoner(new Tit4Tat());
            tit4tat.setColor(Color.cyan);
            addAgent(tit4tat);
        }
    }

    public void stats()
    {
        double avgCooperate = 0;
        double avgCheat = 0;
        double avgRandCooperate = 0;
        double avgTit4Tat = 0;

        double cooperateCount = 0;
        double cheatCount = 0;
        double randCount = 0;
        double tit4TatCount = 0;

        for(Agent prisoner : agents)
        {
            Prisoner temp = (Prisoner)prisoner;
            String type = temp.getStrategy().getType();
            if(type.equalsIgnoreCase("Cooperate"))
            {
                avgCooperate += temp.getFitness();
                cooperateCount += 1;
            }

            else if(type.equalsIgnoreCase("RandCooperate"))
            {
                avgRandCooperate += temp.getFitness();
                randCount += 1;
            }

            else if(type.equalsIgnoreCase("Cheat"))
            {
                avgCheat += temp.getFitness();
                cheatCount += 1;
            }

            else
            {
                avgTit4Tat += temp.getFitness();
                tit4TatCount += 1;
            }
        }

        avgCooperate = avgCooperate / cooperateCount;
        avgCheat = avgCheat / cheatCount;
        avgRandCooperate = avgRandCooperate / randCount;
        avgTit4Tat = avgTit4Tat / tit4TatCount;

        JOptionPane.showMessageDialog(null,
                "cooperation avg: " + avgCooperate + "\ncheat avg: " + avgCheat + "\nrand coop avg: " + avgRandCooperate + "\ntit4tat avg: " + avgTit4Tat,
                "average",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args)
    {
        AppPanel panel = new SimulationPanel(new PrisonerFactory());
        panel.display();
    }
}
