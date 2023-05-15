import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import javax.swing.*;


public class WinCanvas extends JComponent {
    private int width; 
    private int height; 

    private Font topSecret; 
    private Font cascadia; 

    public WinCanvas(int w, int h) {
        width = w; 
        height = h; 
        

        try {
            InputStream secretStream = getClass().getResourceAsStream("Top Secret Stamp.ttf");
            topSecret = Font.createFont(Font.TRUETYPE_FONT, secretStream).deriveFont(170F);

            InputStream luckStream = getClass().getResourceAsStream("Cascadia.ttf");
            cascadia = Font.createFont(Font.TRUETYPE_FONT, luckStream).deriveFont(55F);

        } catch(IOException | FontFormatException ex) {

        }

        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; 

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Rectangle2D.Double defaultBackground = new Rectangle2D.Double(0, 0, width, height);
        g2d.setPaint(Color.green);
        g2d.fill(defaultBackground);

        g2d.setFont(topSecret);
        g2d.setColor(Color.white);
        g2d.drawString("You Won!", 330, 330);

        g2d.setFont(cascadia);
        g2d.setColor(Color.white);
        g2d.drawString("Congratulations on living another day.", 410, 450);
    }

}