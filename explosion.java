
import javax.swing.*;

public class explosion extends JPanel{

    public explosion(){

        JPanel backpanel = new JPanel();
        Icon imgIcon = new ImageIcon(this.getClass().getResource("explosion.gif"));
        JLabel label = new JLabel(imgIcon);
        backpanel.add(label);
        add(backpanel);

    }


}
