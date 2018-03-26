import java.net.*;
import java.io.*;

public class ClientThread extends Thread{
	private Socket chatClient;
	private final MessageListener serverListener;
	public ClientThread(Socket chatClient, MessageListener serverListener){
		this.chatClient = chatClient;
		this.serverListener = serverListener;
	}

	public void run(){
		DataInputStream in = null;
		try{ 
			in = new DataInputStream(chatClient.getInputStream());
	    }catch(Exception e){
	    	e.printStackTrace();    
        }

        while(true){
	        try{
	            serverListener.readMessage(in.readUTF());
		    }catch(Exception e){
		    	e.printStackTrace();    
	        }            	        
	    }        
	}
}