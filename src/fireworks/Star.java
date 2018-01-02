/*
 * This program, if distributed by its author to the public as source code,
 * can be used if credit is given to its author and any project or program
 * released with the source code is released under the same stipulations.
 */

package fireworks;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Julian
 */
public class Star {
    int posX;
    int posY;
    
    Color color;
    
    int radius;
    
    public Star(int posX, int posY, Color color, int radius) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.radius = radius;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        if (radius <= 1) {
            g.drawLine(posX, posY, posX, posY);
        } else {
            g.fillArc((int)posX,
                600-(int)posY,
                radius/2, radius/2, 0, 360);
        }
    }
    
}
