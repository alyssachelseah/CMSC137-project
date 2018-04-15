import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ChatClient implements Runnable{
    String serverName;
    String clientName;
    int port;
    Socket server;
    DataOutputStream out;
    DataInputStream in;

    public ChatClient(String serverName, int port, String clientName){
        try{
            this.serverName = serverName; //get IP address of server from first param
            this.port = port; //get port from second param
            this.clientName = clientName; 
            /* Open a ClientSocket and connect to ServerSocket */
            System.out.println("Connecting to " + serverName + " on port " + port);
            
			//creating a new socket for client and binding it to a port
            server = new Socket(serverName, port);

            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            
			/* Send data to the ServerSocket */
            out = new DataOutputStream(server.getOutputStream());
            new Thread(this).start();
            while(true){
                System.out.print(clientName + ": ");
                Scanner getMessage = new Scanner(System.in);
                String message = getMessage.nextLine();
                if(message.equals("exit")){
                    break;
                }else if(message.equals(" ")){
                    continue;
                }else{
                    out.writeUTF("\n" + this.clientName + ": " + message);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Cannot find (or disconnected from) Server");
        }
    }
    public void run(){
        try{
          in = new DataInputStream(server.getInputStream());
            while(true){
                    /* Receive data from the ServerSocket */
                    System.out.println(in.readUTF());
            }
        }catch(Exception e){
            e.printStackTrace();
        }        
    }
}
