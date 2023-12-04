//100% of this class was written by Danny Matlob
package minefield;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import mvc.*;

public class MinefieldPanel extends AppPanel {
    private ArrayList<JButton> buttonList = new ArrayList<>();

    public MinefieldPanel(AppFactory factory) {
        super(factory);
        Font font = new Font("Arial", Font.BOLD, 20);

        JButton NW = new JButton("NW");
        JButton N = new JButton("N");
        JButton NE = new JButton("NE");
        JButton W = new JButton("W");
        JButton E = new JButton("E");
        JButton SW = new JButton("SW");
        JButton S = new JButton("S");
        JButton SE = new JButton("SE");
        buttonList.add(N);
        buttonList.add(S);
        buttonList.add(W);
        buttonList.add(E);
        buttonList.add(NW);
        buttonList.add(NE);
        buttonList.add(SW);
        buttonList.add(SE);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 2));
        buttonPanel.setFocusable(true);
        buttonPanel.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) N.doClick();
                if (e.getKeyCode() == KeyEvent.VK_DOWN) S.doClick();
                if (e.getKeyCode() == KeyEvent.VK_LEFT) W.doClick();
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) E.doClick();
            }
        });
        for (JButton b : buttonList) {
            b.addActionListener(this);
            b.setFont(font);
            b.setFocusable(false);
            buttonPanel.add(b);
        }

        buttonPanel.setPreferredSize(new Dimension(500,500));
        cp.add(buttonPanel);
    }

    public static void main(String[] args) {
        AppFactory factory = null;
        if (args.length == 2) {
            try {
                int width = Integer.parseInt(args[0]);
                int percentMined = Integer.parseInt(args[1]);
                factory = new MinefieldFactory(width, percentMined);
            } catch (Exception E) {
                System.out.println("Usage: java -jar <classpath> (int)<mineFieldWidth> (int)<percentageMined>");
                System.exit(1);
            }
        } else {
            factory = new MinefieldFactory();
        }
        AppPanel panel = new MinefieldPanel(factory);
        panel.display();
    }
}
