/*
 * This program, if distributed by its author to the public as source code,
 * can be used if credit is given to its author and any project or program
 * released with the source code is released under the same stipulations.
 */

package fireworks;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author Julian
 */
public class FireworksHandler {
    private static final Random rand = new Random(); 
    private final LinkedList<Firework> fireworks;
    private final LinkedList<Firework> removeList;
    private final LinkedList<Firework> addList;
    
    
    public FireworksHandler() {
         fireworks = new LinkedList<>();
         removeList = new LinkedList<>();
         addList = new LinkedList<>();
    }
    
    public void tick(int pixelsX, int pixelsY) {
        addList.clear();
        removeList.clear();
        synchronized (fireworks) {
            if (rand.nextInt(100) > 90) {
                fireworks.add(new Firework(pixelsX, pixelsY));
            }
            
            for (Firework firework : fireworks) {
                firework.tick();
            }
            for (Firework firework : fireworks) {
                if (firework.isToBeRemoved()) {
                    addList.addAll(firework.finish());
                    removeList.add(firework);
                }
            }
            fireworks.removeAll(removeList);
            fireworks.addAll(addList);
        }
    }
    
    public void draw(Graphics g, int pixelsX, int pixelsY) {
        synchronized (fireworks) {
            for (Firework firework : fireworks) {
                firework.draw(g, pixelsX, pixelsY);
            }
        }
    }

}
