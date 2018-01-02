/*
 * This program, if distributed by its author to the public as source code,
 * can be used if credit is given to its author and any project or program
 * released with the source code is released under the same stipulations.
 */
package fireworks;

import javax.swing.JFrame;

/**
 *
 * @author Julian
 */
public class Fireworks extends JFrame {

    /**
     * @param args the command line arguments
     */
    
    private GraphicsPanel graphicsPanel;
    
    public static void main(String[] args) {
        Fireworks fw = new Fireworks();
        fw.run();
    }
    
    public Fireworks() {
        graphicsPanel = new GraphicsPanel();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Fireworks");
        this.add(graphicsPanel);
        
        this.pack();
        this.setVisible(true);        
    }
    
    public void run() {
        graphicsPanel.start();
    }
    
}
