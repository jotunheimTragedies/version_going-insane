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

    private int p1GameState; 
    private int p2GameState; 

    public GameServer() {
        System.out.println("---- GAME SERVER ----");
        numPlayers = 0; 
        maxPlayers = 2; 

        p1GameState = 0; 
        p2GameState = 0;

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

                    p1WriteRunnable.sendStartMessage();
                    p2WriteRunnable.sendStartMessage();

                    Thread readThread1 = new Thread(p1ReadRunnable);
                    Thread readThread2 = new Thread(p2ReadRunnable);
                    readThread1.start();
                    readThread2.start();

                    Thread writeThread1 = new Thread(p1WriteRunnable);
                    Thread writeThread2 = new Thread(p2WriteRunnable);
                    writeThread1.start();
                    writeThread2.start();
                    
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
            try {
                
                while(true) {
                    if(playerID == 1) {
                        p1GameState = SReadIn.readInt();
                        System.out.println("RFC: Player 1 Game State " + p1GameState);
                    
                    } else {
                        p2GameState = SReadIn.readInt();
                        System.out.println("RFC: Player 2 Game State " + p1GameState);
                    }

                }
            } catch(IOException ex) {
                System.out.println("IOException from ReadFromClient run()");
            }
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
            try {

                while(true) {
                    if(playerID == 1) {
                        SWriteOut.writeInt(p2GameState);
                        SWriteOut.flush();
                        System.out.println("WTC: Game State " + p2GameState);
                    
                    } else {
                        SWriteOut.writeInt(p1GameState);
                        System.out.println("WTC: Game State " + p1GameState);
                    }

                    try {
                        Thread.sleep(25); // ***
                    } catch(InterruptedException ex) {
                        System.out.println("InterruptedException from WriteToClient run()");
                    }
                }

            } catch(IOException ex) {
                System.out.println("IOException from WriteToClient run()");
            }
        }
        
        public void sendStartMessage() {
            try {
                SWriteOut.writeUTF("We now have 2 players. Good luck!");
            } catch(IOException ex) {
                System.out.println("IOException from WriteToClient sendStartMessage()");
            }
        }
    }

    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}
