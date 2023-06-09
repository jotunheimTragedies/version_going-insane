import java.awt.*;
import javax.swing.*;

// TODO:
    // 1. Mechanics 
    
public class GameFrame {
    private int width; 
    private int height; 
    private int i;

    private JFrame gameFrame; 
    private GameCanvas gameCanvas;
    private ModuleCanvas moduleCanvas;
    private OverCanvas overCanvas; 
    private WinCanvas winCanvas;
    private explosion explosion;

    public int playerID; 


    public GameFrame(int w, int h) {
        width = w; 
        height = h; 

        gameFrame = new JFrame();
        gameCanvas = new GameCanvas(width, height);
        moduleCanvas = new ModuleCanvas(width, height);
        overCanvas = new OverCanvas(width, height);
        winCanvas = new WinCanvas(width, height);

    }

    public void setUpGUI() {
        Container cp = gameFrame.getContentPane();
        cp.add(gameCanvas, BorderLayout.CENTER);

        gameFrame.setTitle("Player #" + playerID +  "_No Human Being Detonates");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(gameCanvas);
        gameFrame.pack();
        gameFrame.setVisible(true);

        transitiontoGameplayState();
        transitiontoOverState();
        transitiontoWinState();

    }

    public void transitiontoGameplayState() {
        while(true) {
            int currentState = gameCanvas.getCurrentState();

            if(currentState == gameCanvas.titleState) {
                
                try {
                    System.out.println();
                    Thread.sleep(5000);
                
                    
                } catch(InterruptedException ex) {
                    System.out.println("InterruptedException from transitiontoGameplay()");
                } 

            }

            if(currentState == gameCanvas.gameplayState) {
                String musicfilePath = "In the Hall of the Mountain King.wav";
                MusicPlayer musicObject = new MusicPlayer();
                musicObject.playMusic(musicfilePath);

                gameFrame.getContentPane().removeAll();
                gameFrame.repaint();
                gameFrame.revalidate();
                break;
            }
            
        }
        
        moduleCanvas.setUpModules();
        Container cp2 = gameFrame.getContentPane();
        cp2.add(moduleCanvas, BorderLayout.CENTER);
        cp2.repaint();
        cp2.revalidate();
    }

    public void transitiontoOverState() {
        while(true) {
            int currentState = gameCanvas.getCurrentState();
            
            if(currentState == gameCanvas.gameplayState) {
                
                BombTimer timerBomb = moduleCanvas.getBombTimer();
                int zeroMinutes = timerBomb.getMinutesLeft();
                int zeroSeconds = timerBomb.getSecondsLeft();
               
                if((zeroMinutes == 0 && zeroSeconds == 0) || moduleCanvas.strikesModule.playerloses == true) {
                    gameCanvas.gameState = gameCanvas.gameoverState;
                    gameFrame.getContentPane().removeAll();
                    gameFrame.repaint();
                    gameFrame.revalidate();
                    System.out.println("zeroMinutes");

                    break;
                }

                try {
                    System.out.println();
                    Thread.sleep(5000);

                } catch(InterruptedException ex) {
                    System.out.println("InterruptedException from transitiontoOverState()");
                }
            }
            
        }
        
        Container cp3 = gameFrame.getContentPane();
        cp3.add(overCanvas, BorderLayout.CENTER);
        cp3.repaint();
        cp3.revalidate();

    }

    public void transitiontoWinState() {
        while(true) {
            int currentState = gameCanvas.getCurrentState();
            //System.out.println("kita");
            if(currentState == gameCanvas.congratulationsState) {
                
                boolean simonWin = moduleCanvas.simonModule.omgiwon;
                boolean keypadWin = moduleCanvas.keypadModule.omgiwon;

                System.out.println(simonWin);
                System.out.println(keypadWin);
               
                if((simonWin == true && keypadWin == true)) {
                    gameCanvas.gameState = gameCanvas.congratulationsState;
                    gameFrame.getContentPane().removeAll();
                    gameFrame.repaint();
                    gameFrame.revalidate();
                    break;
                }

                try {
                    System.out.println();
                    Thread.sleep(5000);

                } catch(InterruptedException ex) {
                    System.out.println("InterruptedException from transitiontoWinState()");
                }
            }
            
        }
    
        Container cp3 = gameFrame.getContentPane();
        cp3.add(winCanvas, BorderLayout.CENTER);
        cp3.repaint();
        cp3.revalidate();

    }

    public GameCanvas getGameCanvas() {
        return gameCanvas; 
    }
}