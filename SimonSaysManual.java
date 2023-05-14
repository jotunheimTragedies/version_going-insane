import java.awt.*;
import javax.swing.*;

public class SimonSaysManual extends JPanel implements ModuleTemplate{
    public JPanel backpanel;
    public JTextArea manual;
    public ImageIcon greenimage;
    public ImageIcon redimage;
    public Font font; 


    public SimonSaysManual(){
        backpanel = new JPanel();
        backpanel.setLayout(new GridLayout(2, 1));
    
        manual = new JTextArea();
        
        setUpComponents();
        add(backpanel);
    }

    public void setUpComponents(){

        font = new Font("Verdana", Font.BOLD, 25);
        manual.setFont(font);
        manual.setText(" While i <= 5\n    Red flash = Yellow button\n    Green flash = Red button\n    Blue Flash = Green button\n    Yellow Flash = Blue button");
        manual.setEditable(false);
        
        backpanel.add(manual);
    }

}
