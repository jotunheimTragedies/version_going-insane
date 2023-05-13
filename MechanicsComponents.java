import java.awt.*;
import java.io.*;

public class MechanicsComponents implements PagesTemplate {
    private Font topSecret; 
    private Font cascadia; 

    private String backButton = "< Back";
    private boolean backPressed; 


    public MechanicsComponents() {
        backPressed = false; 

        try {
            InputStream secretStream = getClass().getResourceAsStream("Top Secret Stamp.ttf");
            topSecret = Font.createFont(Font.TRUETYPE_FONT, secretStream).deriveFont(100F);

            InputStream cascadiaStream = getClass().getResourceAsStream("Cascadia.ttf");
            cascadia = Font.createFont(Font.TRUETYPE_FONT, cascadiaStream).deriveFont(40F);

        } catch(IOException | FontFormatException ex) {

        }

    }

    @Override
    public void draw(Graphics2D g2d) {
        if(backPressed == false) {
            g2d.setFont(cascadia);
            g2d.setPaint(Color.WHITE);
            g2d.drawString(backButton, 40, 50);

        } else if(backPressed == true) {
            g2d.setFont(cascadia);
            g2d.setPaint(Color.RED);
            g2d.drawString(backButton, 40, 50);
        }

        g2d.setFont(topSecret);
        g2d.setPaint(Color.WHITE);
        g2d.drawString("Mechanics", 530, 130);
        g2d.drawRect(85, 140, 1300, 600);

        g2d.setFont(cascadia);
        g2d.setPaint(Color.WHITE);
        g2d.drawString("1. Insert Mechanics here ", 110, 180);

    }

    public void setbackPressed(boolean b) {
        backPressed = b; 
    }
}
