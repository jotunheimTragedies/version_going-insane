import java.awt.*;
import javax.swing.*;

public class KeypadManual extends JPanel implements ModuleTemplate{
    public JPanel backpanel;
    public JTextArea manual;
    public JLabel pic;
    public ImageIcon image;
    public Font font;


    public KeypadManual(){
        backpanel = new JPanel();
        backpanel.setLayout(new GridLayout(2, 1));

        manual = new JTextArea();
        
        setUpComponents();
        add(backpanel);
    }

    public void setUpComponents(){

        font = new Font("Verdana", Font.BOLD, 15);
        manual.setFont(font);
        manual.setText("Int i = 1;\nIf (!Unlocked)\n    for(int x : Column i)\n        If(x.get(symbol) = currentSymbol)\n            PressButton();\n        i++;");
        manual.setEditable(false);

        image = new ImageIcon(new ImageIcon("Modules/KeypadImages/order of rows for keypad.png").getImage().getScaledInstance(450, 200, Image.SCALE_DEFAULT));

        pic = new JLabel();
        pic.setIcon(image);
        backpanel.add(pic);
        //backpanel.add(pic1);
        backpanel.add(manual);

    }

}