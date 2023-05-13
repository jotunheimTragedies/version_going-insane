// TODO: Have one GameCanvas for both players that changes with read and write


import java.io.*;
import java.net.*;

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
            clientSocket = new Socket("localhost", 65000);

            DataInputStream clientIn = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream clientOut = new DataOutputStream(clientSocket.getOutputStream());

            playerID = clientIn.readInt();
            gameFrame.playerID = playerID;
            System.out.println("Connected to server as Player# " + playerID + ".");

            if(playerID == 1) {
                System.out.println("Waiting for Player #2 to connect...");
            }

            rfsRunnable = new ReadFromServer(clientIn);
            wtsRunnable = new WriteToServer(clientOut);
            

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

        }
    }
}
