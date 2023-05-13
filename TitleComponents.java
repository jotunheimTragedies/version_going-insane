import java.awt.*;
import java.io.*;

public class TitleComponents implements PagesTemplate {
    
    private Font topSecret; 
    private Font cascadia; 

    private String[] titleOptions = {"Play Game", "Mechanics", "Quit"};
    public int currentOption = 0; 


    public TitleComponents() {
        try {
            InputStream secretStream = getClass().getResourceAsStream("Top Secret Stamp.ttf");
            topSecret = Font.createFont(Font.TRUETYPE_FONT, secretStream).deriveFont(122F);

            InputStream cascadiaStream = getClass().getResourceAsStream("Cascadia.ttf");
            cascadia = Font.createFont(Font.TRUETYPE_FONT, cascadiaStream).deriveFont(50F);

        } catch(IOException | FontFormatException ex) {

        }
    }

    @Override
    public void draw(Graphics2D g2d) {

        g2d.setFont(topSecret);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Remain Speaking and No", 90, 180);
        g2d.drawString("Human Being Detonates", 100, 300);

        g2d.setFont(cascadia);
        g2d.drawString("Ateneo CS Edition", 505, 410);

        for(int i = 0; i < titleOptions.length; i++) {
            if(i == currentOption) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.WHITE);
            }

            g2d.setFont(cascadia);
            g2d.drawString(titleOptions[i], 620, 520 + i*90);
        }

    }
    public int getOptionsLength() {
        return titleOptions.length;
    }
}
