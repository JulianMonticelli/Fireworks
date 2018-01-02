/*
 * This program, if distributed by its author to the public as source code,
 * can be used if credit is given to its author and any project or program
 * released with the source code is released under the same stipulations.
 */

package fireworks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * @author Julian
 */
public class GraphicsPanel extends JPanel {

    private static final int FPS = 30;
            
    private boolean running = false;
    
    FireworksHandler fireworksHandler;
    StarHandler starHandler;
    
    public GraphicsPanel() {
        this.setPreferredSize(new Dimension(800,600));
        this.setBackground(Color.BLACK);
        
        starHandler = new StarHandler();
        fireworksHandler = new FireworksHandler();
    }
    
    public void start() {
        running = true;
        run();
    }
    
    private void run() {
        
        long msPerTick = 1000/FPS;
        
        while (running) {
            long startTime = System.currentTimeMillis();
            
            
            tick();
            repaint();
            
            long finishTime = System.currentTimeMillis();
            
            long timeToSleep = msPerTick - (finishTime - startTime);
            
            if (timeToSleep > 0) {
                try {
                    Thread.sleep(timeToSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }
    }
    
    public void tick() {
        fireworksHandler.tick(this.getWidth(), this.getHeight());
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        starHandler.draw(g);
        fireworksHandler.draw(g, this.getWidth(), this.getHeight());
    }
    

}
