import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ModuleCanvas extends JPanel {
    private int width; 
    private int height; 

    public SimonSays simonModule; 
    public Keypad keypadModule; 
    private SimonSaysManual simonManual;
    private BombTimer bombTimer;
    private KeypadManual keypadManual;
    public Strikes strikesModule;
    public Timer timer;

    public ModuleCanvas(int w, int h) {
        width = w; 
        height = h; 

        bombTimer = new BombTimer();
        setLayout(new GridLayout(2, 3, 10, 10));
        setPreferredSize(new Dimension(width, height));

    }

    public void setUpModules() {

        simonModule = new SimonSays();
        add(simonModule);

        strikesModule = new Strikes();
        add(strikesModule);

        keypadModule = new Keypad();
        add(keypadModule);

        simonManual = new SimonSaysManual();
        add(simonManual);

        bombTimer = new BombTimer(); 
        add(bombTimer);  

        keypadManual = new KeypadManual();
        add(keypadManual);   
        
        
        timer = new Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent e){
                //constantly checks both modules if the player has made a mistake
                strikesModule.strikechecker(simonModule.struck, keypadModule.struck, simonModule.u, keypadModule.u);
                //System.out.println(strikesModule.playerloses);

			}
		});
        timer.start();

    }

    public BombTimer getBombTimer() {
        return bombTimer; 
    }

}
