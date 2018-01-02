/*
 * This program, if distributed by its author to the public as source code,
 * can be used if credit is given to its author and any project or program
 * released with the source code is released under the same stipulations.
 */

package fireworks;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author Julian
 */
public class Firework {
    
    private static final Random rand = new Random();    
    private static final int POS_UNIT_PER_PIXEL = 10;
    private static final double GRAVITATIONAL_CONSTANT = -4.9F; // position/second
    
    private double posX;
    private double posY;
    private int size;
    
    private Color color;
    
    private int ticksTilFinish;
    
    private boolean spawnsChildren;
    private int childrenCount;
    private int generation;
    
    private int fireworkForce;
    private double verticalMove;
    private double horizontalMove;
    
    private boolean toRemove;
    
    
    // This assumes an entirely new firework being shot from the "ground"
    public Firework() {
        this.posY = 0;
        this.posX = rand.nextFloat() * 800 * POS_UNIT_PER_PIXEL;
        // Neonish colors are a 25% chance
        if (rand.nextBoolean() && rand.nextBoolean()) {
            this.color = new Color(128+rand.nextInt(128), 128+rand.nextInt(128),
                128+rand.nextInt(128));
        } else {
            if (rand.nextBoolean()) {
                this.color = new Color(128+rand.nextInt(128), rand.nextInt(255),
                    rand.nextInt(255));
            } else if (rand.nextBoolean())
            {
                this.color = new Color(rand.nextInt(255), 128+rand.nextInt(128), 
                    rand.nextInt(255));
            } else {
                this.color = new Color(rand.nextInt(255), rand.nextInt(255),
                    128+rand.nextInt(128));
            }
        }
        this.ticksTilFinish = rand.nextInt(30) + 30;
        this.spawnsChildren = true;
        this.childrenCount = rand.nextInt(64) + 8;
        this.generation = 0;
        this.fireworkForce = rand.nextInt(40) + 80;
        this.size = rand.nextInt(3) + 8;
        
        verticalMove = (rand.nextFloat() + 0.5f) * 200;
        horizontalMove = (rand.nextFloat() - 0.5f) * 20;
        toRemove = false;
    }
    
    // This is a constructor to allow children firework spawning
    public Firework(double posX, double posY, Color color, int ticksTilFinish,
            boolean spawnsChildren, int childrenCount,
            double horizontalMove, double verticalMove,
            int generation, int size, int fireworkForce) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.ticksTilFinish = ticksTilFinish;
        this.spawnsChildren = spawnsChildren;
        this.childrenCount = childrenCount;
        this.horizontalMove = horizontalMove;
        this.verticalMove = verticalMove;
        this.generation = generation;
        this.size = size;
        this.fireworkForce = fireworkForce;
        this.toRemove = false;
    }
    
    public void tick() {
        verticalMove += GRAVITATIONAL_CONSTANT;
        posY += verticalMove;
        posX += horizontalMove;
        
        ticksTilFinish--;
        
        if (ticksTilFinish <= 0) {
            finish();
        } 
    }
    
    public LinkedList<Firework> finish() {
        LinkedList<Firework> children = new LinkedList();
        
        if (spawnsChildren && generation < 5) {
            int childrenTicksTilFinish = rand.nextInt(30) + 10;
            boolean spawnsChildren = rand.nextBoolean() && rand.nextBoolean()
                    && rand.nextBoolean() && rand.nextBoolean();
            int childrenGeneration = generation + 1;
            int childrenSpawnCount = childrenCount / childrenGeneration + 1;
            int childrenForce = fireworkForce / 2;
            int childrenSize = size == 1 ? 1 : size - 1;
            boolean childrenInheritParentColor = rand.nextBoolean()
                    | rand.nextBoolean() | rand.nextBoolean();
            for (int i = 0; i < childrenCount; i++) {
                Color childColor;
                if (childrenInheritParentColor) {
                    childColor = this.color;
                } else {
                    if (rand.nextBoolean() && rand.nextBoolean()) {
                        childColor = new Color(128+rand.nextInt(128), 128+rand.nextInt(128),
                            128+rand.nextInt(128));
                    } else {
                        if (rand.nextBoolean()) {
                            childColor = new Color(128+rand.nextInt(128), rand.nextInt(255),
                                rand.nextInt(255));
                        } else if (rand.nextBoolean())
                        {
                            childColor = new Color(rand.nextInt(255), 128+rand.nextInt(128), 
                                rand.nextInt(255));
                        } else {
                            childColor = new Color(rand.nextInt(255), rand.nextInt(255),
                                128+rand.nextInt(128));
                        }
                    }
                }
                double theta = (360.0f / childrenCount) * i+1;
                float randMult = (rand.nextInt(25) + 100) / (float)125.0f;
                double velocityX = fireworkForce * Math.cos(theta) * randMult;
                double velocityY = fireworkForce * Math.sin(theta) * randMult;
                
                children.add(new Firework(posX, posY, childColor, 
                        childrenTicksTilFinish, spawnsChildren, childrenSpawnCount,
                        velocityX, velocityY, childrenGeneration, childrenSize,
                        childrenForce));
            }
        }
        
        toRemove = true;
        return children;
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillArc((int)posX/POS_UNIT_PER_PIXEL,
                600-(int)posY/POS_UNIT_PER_PIXEL,
                size/2, size/2, 0, 360);
    }
    
    public boolean isToBeRemoved() {
        return toRemove;
    }
    
}