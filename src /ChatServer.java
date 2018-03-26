import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ChatServer{
    private ServerSocket serverSocket;
    Socket client;
    ArrayList<DataOutputStream> clients = new ArrayList<DataOutputStream>();

    public ChatServer(int port) throws IOException{
        //binding a socket to a port
        serverSocket = new ServerSocket(port);

        MessageListener listener = new MessageListener() {
            @Override
            public void readMessage(String message) {
                Iterator<DataOutputStream> i = clients.iterator();
                while (i.hasNext()) {
                    DataOutputStream out = i.next();
                    try{
                        out.writeUTF(message);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };        

        boolean connected = true;
        
        while(connected){
            try{
                /* Start accepting data from the ServerSocket */
                //waits or accepts connection from client
                System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                client = serverSocket.accept();
                clients.add(new DataOutputStream(client.getOutputStream()));
                System.out.println("User Connected!");
                ClientThread ct = new ClientThread(client, listener);
                ct.start();
            }catch(SocketTimeoutException s){
                System.out.println("Socket timed out!");
                break;
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("Input/Output Error!");
                break;
            }
        }
    }
}
