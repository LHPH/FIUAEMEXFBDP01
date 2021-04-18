package FBD;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JPanel;
 
public class GradientPane extends JPanel {
    
    public GradientPane() {
            setOpaque(false);
            try {
                jbInit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    private void jbInit() throws Exception {
            this.setLayout( null );
        }

 
 public void paintComponent(Graphics g) {
             Graphics2D g2d = (Graphics2D)g;
             g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
             GradientPaint gradientPaint =
                 new GradientPaint(new Point(0, 0), new Color(0, 126, 0), new Point(0, getHeight() / 2),
                                   new Color(0,126, 0));
             g2d.setPaint(gradientPaint);
             g2d.fillRect(0, 0, getWidth(), getHeight());
             super.paintComponent(g);

 
 }

}