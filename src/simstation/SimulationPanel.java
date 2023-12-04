package simstation;

import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class SimulationPanel extends AppPanel {
    private ArrayList<JButton> buttonList = new ArrayList<>();
    public SimulationPanel(SimulationFactory factory) {
        super(factory);
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));

        JButton start = new JButton("Start");
        JButton suspend = new JButton("Suspend");
        JButton resume = new JButton("Resume");
        JButton stop = new JButton("Stop");
        JButton stats = new JButton("Stats");

        buttonList.add(start);
        buttonList.add(suspend);
        buttonList.add(resume);
        buttonList.add(stop);
        buttonList.add(stats);

        for (JButton b : buttonList) {
            b.setSize(40,40);
            b.addActionListener(this);
            buttonPanel.add(b);
        }

        buttonPanel.setPreferredSize(new Dimension(500,500));
        cp.add(buttonPanel);
    }
}
