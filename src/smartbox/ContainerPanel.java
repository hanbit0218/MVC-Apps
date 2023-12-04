package smartbox;

import mvc.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ContainerPanel extends AppPanel {
    private ArrayList<JButton> buttonList = new ArrayList<>();
    public ContainerPanel(AppFactory factory) {
        super(factory);
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));

        JButton add = new JButton("Add");
        JButton rem = new JButton("Rem");
        JButton run = new JButton("Run");

        buttonList.add(add);
        buttonList.add(rem);
        buttonList.add(run);

        for (JButton b : buttonList) {
            b.setSize(40,40);
            b.addActionListener(this);
            buttonPanel.add(b);
        }

        buttonPanel.setPreferredSize(new Dimension(500,500));
        cp.add(buttonPanel);
    }
    public static void main(String[] args) {
        AppFactory factory = new ContainerFactory();
        AppPanel panel = new ContainerPanel(factory);
        panel.display();
    }

}
