package simstation;

import mvc.Utilities;

import java.awt.*;
import java.io.Serializable;

public class Agent implements Serializable, Runnable {
    String name;
    public Heading heading;
    int xc = Utilities.rng.nextInt(500);
    int yc = Utilities.rng.nextInt(500);
    boolean suspended = false;
    boolean stopped = false;
    protected Color color = Color.gray;

    public Color getColor() {return color;}
    public void setColor(Color newColor) {color = newColor;}

    protected Simulation world;
    transient protected Thread myThread;

    public void setWorld(Simulation s) {
        world = s;
    }
    public int getX () {
        return xc;
    }
    public int getY () {
        return yc;
    }
    public void run() {
        myThread = Thread.currentThread();
        try {
            while (!stopped) {
                update();
                Thread.sleep(20);
                checkSuspended();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private synchronized void checkSuspended() {
        try {
            while (!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void start() {
        run();
    }
    public synchronized void suspend() {
        suspended = true;
    }
    public synchronized void resume() {notify();}
    public synchronized void stop() {
        stopped = true;
    }
    public synchronized void update() {}
    public synchronized void move(int steps)
    {
        switch (heading)
        {
            case NORTH:
            {
                yc -= steps;
                if(getY() < 0)
                {
                    yc = (500 - (steps - yc));
                }
                break;
            }
            case SOUTH:
            {
                yc+=steps;
                if(getY() > 500)
                {
                    yc = (0 + steps - (500 - getY()));
                }
                break;
            }
            case EAST:
            {
                xc += steps;
                if(getX() > 500)
                {
                    xc = (0 + steps - (500 - getX()));
                }
                break;
            }
            case WEST:
            {
                xc-=steps;
                if(getX() < 0)
                {
                    xc = (500 - (steps - getX()));
                }
                break;
            }
        }
    }
}
