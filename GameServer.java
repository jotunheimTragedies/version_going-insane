// TODO: Have one GameCanvas for both players that changes with read and write

import java.io.*;
import java.net.*;


public class GameServer {
    private ServerSocket serverSocket; 
    private int numPlayers; 
    private int maxPlayers; 

    private Socket p1Socket; 
    private Socket p2Socket; 
    private ReadFromClient p1ReadRunnable; 
    private ReadFromClient p2ReadRunnable; 
    private WriteToClient p1WriteRunnable; 
    private WriteToClient p2WriteRunnable; 

    public GameServer() {
        System.out.println("---- GAME SERVER ----");
        numPlayers = 0; 
        maxPlayers = 2; 

        try {
            serverSocket = new ServerSocket(65000);


        } catch(IOException ex) {
            System.out.println("IOException from GameServer constructor");

        }
    }

    public void acceptConnections() {
        try {
            System.out.println("Waiting for connections...");

            while(numPlayers < maxPlayers) {
                Socket s = serverSocket.accept();
                
                DataInputStream serverIn = new DataInputStream(s.getInputStream());
                DataOutputStream serverOut = new DataOutputStream(s.getOutputStream());

                numPlayers++; 
                serverOut.writeInt(numPlayers);
                System.out.println("Player #" + numPlayers + " has connected.");

                ReadFromClient rfc = new ReadFromClient(numPlayers, serverIn);
                WriteToClient wtc = new WriteToClient(numPlayers, serverOut);

                if(numPlayers == 1) {
                    p1Socket = s; 
                    p1ReadRunnable = rfc; 
                    p1WriteRunnable = wtc; 
                } else {
                    p2Socket = s; 
                    p2ReadRunnable = rfc; 
                    p2WriteRunnable = wtc; 
                }
            }

            System.out.println("We now have 2 players. No longer accepting connections.");

        } catch(IOException ex) {
            System.out.println("IOException from acceptConnections()");
        }
    }

    private class ReadFromClient implements Runnable {
        private int playerID; 
        private DataInputStream SReadIn; 

        public ReadFromClient(int pid, DataInputStream ri) {
            playerID = pid; 
            SReadIn = ri; 
            System.out.println("    RFC" + playerID + " Runnable created");
        }

        @Override
        public void run() {

        }
    }

    private class WriteToClient implements Runnable {
        private int playerID; 
        private DataOutputStream SWriteOut; 

        public WriteToClient(int pid, DataOutputStream wo) {
            playerID = pid; 
            SWriteOut = wo; 
            System.out.println("    WFC" + playerID + " Runnable created");
        }

        @Override
        public void run() {

        }
    }

    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}
