// TODO: Have one GameCanvas for both players that changes with read and write


import java.io.*;
import java.net.*;
import java.util.*;

public class Player {
    private Socket clientSocket; 
    private int playerID;
   
    private GameFrame gameFrame; 

    private ReadFromServer rfsRunnable; 
    private WriteToServer wtsRunnable; 



    public Player() {
        System.out.println("---- CLIENT ----");

        gameFrame = new GameFrame(1920, 1080);
        connectToServer();
        gameFrame.setUpGUI();

    }

    private void connectToServer() {
        try {
            
            Scanner console = new Scanner(System.in);
            System.out.println("IP Address: ");
            String ipAddress = console.nextLine();
            System.out.println("Port Number: ");
            int portNumber = Integer.parseInt(console.nextLine());
            clientSocket = new Socket(ipAddress, portNumber); //portNumber must be 65000

            DataInputStream clientIn = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream clientOut = new DataOutputStream(clientSocket.getOutputStream());

            playerID = clientIn.readInt();
            gameFrame.playerID = playerID;
            System.out.println("Connected to server as Player #" + playerID);

            if(playerID == 1) {
                System.out.println("Waiting for Player #2 to connect...");
            }

            rfsRunnable = new ReadFromServer(clientIn);
            wtsRunnable = new WriteToServer(clientOut);
            rfsRunnable.waitForStartMessage();
            

        } catch(IOException ex) {
            System.out.println("IOException from connectToServer()");
        }
    }

    private class ReadFromServer implements Runnable {
        private DataInputStream CReadIn; 

        public ReadFromServer(DataInputStream ri) {
            CReadIn = ri; 
            System.out.println("    RFS Runnable created");
        }

        @Override
        public void run() {
            try {
                
                while(true) {

                    int state = CReadIn.readInt();
                    
                    if(state != -1) {

                        gameFrame.getGameCanvas().setCurrentState(state);
                    }
                }
            } catch(IOException ex) {
                System.out.println("IOException from ReadFromServer run()");
            }
        }

        public void waitForStartMessage() {
            try {
                String startMessage = CReadIn.readUTF();
                System.out.println("Message from server: " + startMessage);

                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wtsRunnable);
                readThread.start();
                writeThread.start();

            } catch(IOException ex) {
                System.out.println("IOException from ReadFromServer waitForStartMessage()");
            }
        }
    }

    private class WriteToServer implements Runnable {
        private DataOutputStream CWriteOut; 

        public WriteToServer(DataOutputStream wo) {
            CWriteOut = wo; 
            System.out.println("    WTS Runnable created");
        }

        @Override
        public void run() {
            try {
                
                while(true) {
                    GameCanvas checkCanvas = gameFrame.getGameCanvas();
                    
                    if(checkCanvas != null) { 
                        int currentState = gameFrame.getGameCanvas().getCurrentState();
                        CWriteOut.writeInt(currentState);
                        CWriteOut.flush();
                        System.out.println("WTS: Game State " + currentState); 

                    }
                    
                    try {
                        Thread.sleep(25); 
                    
                    } catch(InterruptedException ex) {
                        System.out.println("InterruptedException form WriteToServer run()");
                    }
                }

            } catch(IOException ex) {
                System.out.println("IOException from WriteToServer run()");
            }
        }
    }
}
