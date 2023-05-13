import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;


public class BombTimer extends JPanel implements ModuleTemplate {
    //private JPanel countdowPanel;
    private JLabel countdownLabel; 
    private Font tickingTimeBomb; 
    private Timer countdownTimer; 

    private int minutesLeft;
    private int secondsLeft; 
    private DecimalFormat decimalFormat; 
    private String doubleMinute; 
    private String doubleSecond; 


    public BombTimer() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        try {
            InputStream inputStream = getClass().getResourceAsStream("TickingTimebombBB.ttf");
            tickingTimeBomb = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(200F);

        } catch(IOException | FontFormatException e) {

        }

        countdownLabel = new JLabel();
        countdownLabel.setHorizontalAlignment(JLabel.CENTER);
        countdownLabel.setFont(tickingTimeBomb);
        countdownLabel.setForeground(Color.red);

        decimalFormat = new DecimalFormat("00");
        countdownLabel.setText("2:30");
        minutesLeft = 2; 
        secondsLeft = 30; 

        setUpComponents();
        countdownTimer.start();

        add(countdownLabel, BorderLayout.CENTER);
        repaint();

    }
    
    @Override
    public void setUpComponents() {
        countdownTimer = new Timer(1000, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsLeft--; 
                doubleMinute = decimalFormat.format(minutesLeft);
                doubleSecond = decimalFormat.format(secondsLeft);
                countdownLabel.setText(doubleMinute + ":" + doubleSecond);

                if(secondsLeft == -1) {
                    secondsLeft = 59; 
                    minutesLeft--; 
                    doubleMinute = decimalFormat.format(minutesLeft);
                    doubleSecond = decimalFormat.format(secondsLeft);
                    countdownLabel.setText(doubleMinute + ":" + doubleSecond);
                }
                
                if(minutesLeft == 0 && secondsLeft == 0) {
                    countdownTimer.stop();
                }
            }
        });
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }
}
