import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;


public class GameCanvas extends JComponent implements KeyListener {
    private int width; 
    private int height;

    // Game States
    public int gameState; 
    public final int titleState = 0; 
    public final int mechanicsState = 1; 
    public final int gameplayState = 2; 
    public final int gameoverState = 3;
    public final int congratulationsState = 4; 

    // Pages
    private TitleComponents titlePage; 
    private MechanicsComponents mechanicsPage; 


    public GameCanvas(int w, int h) {
        width = w; 
        height = h; 

        gameState = titleState; 

        titlePage = new TitleComponents();
        mechanicsPage = new MechanicsComponents();

        addKeyListener(this);
        setFocusable(true);

        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; 

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Rectangle2D.Double background = new Rectangle2D.Double(0, 0, width, height);
        g2d.setPaint(Color.BLACK);
        g2d.fill(background);

        if(gameState == titleState) {
            titlePage.draw(g2d);

        } 

        if(gameState == mechanicsState) {
            mechanicsPage.draw(g2d);
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int getKeyPressed = e.getKeyCode();
        if(gameState == titleState) {

            if(getKeyPressed == KeyEvent.VK_DOWN) {
                titlePage.currentOption++; 
                repaint();
                
                if(titlePage.currentOption >= titlePage.getOptionsLength()) {
                    titlePage.currentOption = 0; 
                    repaint();
                }

            
            }

            if(getKeyPressed == KeyEvent.VK_UP) {
                titlePage.currentOption--; 
                repaint();
                
                if(titlePage.currentOption < 0) {
                    titlePage.currentOption = titlePage.getOptionsLength()-1; 
                    repaint();
                }
            }

            if(getKeyPressed == KeyEvent.VK_ENTER) {
                if(titlePage.currentOption == 0) {
                    gameState = gameplayState; 

                } else if(titlePage.currentOption == 1) {
                    gameState = mechanicsState;
                    repaint();
                    

                } else if(titlePage.currentOption == 2) {
                    System.exit(0);
                }
            }
        } else if(gameState == mechanicsState) {
            
            if(getKeyPressed == KeyEvent.VK_LEFT) {
                mechanicsPage.setbackPressed(true);
                repaint();
    
            }
    
            if(getKeyPressed == KeyEvent.VK_ENTER) {
                gameState = titleState;
                repaint();
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }


    public int getCurrentState() {
        return gameState;
    }

    public void setCurrentState(int n) {
        gameState = n; 
    }

}
