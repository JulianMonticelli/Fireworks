/*
 * This program, if distributed by its author to the public as source code,
 * can be used if credit is given to its author and any project or program
 * released with the source code is released under the same stipulations.
 */

package fireworks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author Julian
 */
public class StarHandler {
    private LinkedList<Star> starList;
    Random rand;
    
    
    private void populateStars(float percentOfPixelsAsStars) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
        int pixelsX = (int)width;
        int pixelsY = (int)height;
        
        int totalPixels = pixelsX * pixelsY;
        
        int numStars = (int) (percentOfPixelsAsStars * totalPixels);
        System.out.println("Number of stars: " + numStars);
        for (int i = 0; i < numStars; i++) {
            starList.add(new Star(
                    rand.nextInt(pixelsX),
                    rand.nextInt(pixelsY),
                    new Color(200+rand.nextInt(56), 170+rand.nextInt(86), 200+rand.nextInt(56)),
                    rand.nextInt(3)
            ));
        }
        
    }
    
    public StarHandler() {
        rand = new Random();
        starList = new LinkedList<>();
        populateStars(0.00005f);
    }
    
    public void draw(Graphics g) {
        for (Star star : starList) {
            star.draw(g);
        }
    }
    
}
