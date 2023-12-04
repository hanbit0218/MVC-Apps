package simstation;

import minefield.MineSquare;
import mvc.*;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class Simulation extends Model {
    protected List<Agent> agents = new ArrayList<>();
    transient private Timer timer;
    protected int clock;
    boolean started = false;

    transient protected ClockUpdater cu = new ClockUpdater();

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 20, 20);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
            changed();
        }
    }

    public void start() {
        if (started) return;
        populate();
        startTimer();
        for (Agent a : agents) {
            Thread thread = new Thread(a);
            thread.start();
        }
        started = true;
    }
    public void suspend() {
        stopTimer();
        for (Agent a : agents) {
            a.suspend();
        }
    }
    public void resume() {
        startTimer();
        for (Agent a : agents) {
            a.resume();
        }
    }
    public void stop() {
        stopTimer();
        for (Agent a : agents) {
            a.stop();
        }
    }
    public Agent getNeighbor(Agent a, double radius) {
        int size = agents.size();
        int startIndex = Utilities.rng.nextInt(agents.size());
        for (int i = startIndex+1; i!=startIndex; i++) {
            if (i>=size) i = 0;
            Agent b = agents.get(i);
            int dx = a.getX() - b.getX();
            int dy = a.getY() - b.getY();
            int distance = (int) Math.sqrt(dx*dx + dy*dy);
            if (distance <=radius) return b;
        }
        return null;
    }
    public void populate() {}

    public void stats () {
        JOptionPane.showMessageDialog(null,
                "#agents = " + agents.size() + "\nclock = " + clock * 0.01 + " s",
                "average",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public synchronized void addAgent(Agent a) {
        System.out.println("Adding agent");
        agents.add(a);
        a.setWorld(this);
    }

    public Iterator<Agent> iterator () {
        return agents.iterator();
    }
}
