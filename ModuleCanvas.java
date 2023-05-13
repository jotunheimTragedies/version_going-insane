import java.awt.*;
import javax.swing.*;


public class ModuleCanvas extends JPanel {
    private int width; 
    private int height; 

    //private SimonSays simonModule; 
        // insert strikes here
    //private Keypad keypadModule; 
    //private SimonSaysManual simonManual;
    private BombTimer bombTimer;
    //private KeypadManual keypadManual;

    public ModuleCanvas(int w, int h) {
        width = w; 
        height = h; 

        bombTimer = new BombTimer();

        setLayout(new GridLayout(2, 3, 10, 10));
        setPreferredSize(new Dimension(width, height));
    }

    public void setUpModules() {
    
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        add(redPanel);

        JPanel orangePanel = new JPanel();
        orangePanel.setBackground(Color.orange);
        add(orangePanel);

        JPanel yellowPanel = new JPanel();
        yellowPanel.setBackground(Color.yellow);
        add(yellowPanel);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.green);
        add(greenPanel);

        bombTimer = new BombTimer(); 
        add(bombTimer);  

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.blue);
        add(bluePanel);

        /* simonModule = new SimonSays();
        add(simonModule);   

        fillerModule = new Filler();
        add(fillerModule);

        keypadModule = new Keypad();
        add(keypadModule);       

        simonManual = new SimonSaysManual();
        add(simonManual);  */     
        
        // bombTimer = new BombTimer(); 
        // add(bombTimer);  

        /* keypadManual = new KeypadManual();
        add(keypadManual); */
    }

    public BombTimer getBombTimer() {
        return bombTimer; 
    }

}
