package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;

public class Server implements Runnable {

	private static final int GAME_START=0;
	private static final int IN_PROGRESS=1;
	private static final int GAME_END=2;
	private static final int WAITING_FOR_PLAYERS=3;
	
	private static DatagramSocket serverSocket = null;
	private static boolean connected = false;
    
    private static HashMap<String,PlayerInfo> players = new HashMap<String,PlayerInfo>();
    private static int gameStage=WAITING_FOR_PLAYERS;
	private static String playerData;
	private static int playerCount=0;
	private static int numDeadPlayers = 0;
	private static int numPlayers = 0;
	private static boolean bossIsDead = false;
	private static Thread t;

	private static int currX = 400;
	private static int currY = 600;
	
	public Server(int numPlayers, int port) {
		this.numPlayers = numPlayers;
		connected = false;
		
		gameStage = WAITING_FOR_PLAYERS;
		
		playerCount = 0;
		numDeadPlayers = 0;
		numPlayers = 0;
		
		currX = 400;
		currY = 600;

		t = new Thread(this);

		try {
            serverSocket = new DatagramSocket(port);
			serverSocket.setSoTimeout(100);
		} catch (IOException e) {
            System.err.println("Could not listen on port: "+port);
            System.exit(-1);
		}catch(Exception e){
			e.printStackTrace();
		}
		t.start();
	}
	
	public void broadcast(String msg) {
		for(String name : players.keySet()){
			PlayerInfo player = players.get(name);
			send(player,msg);	
        }
	}
	
	public void send(PlayerInfo player, String msg){
		DatagramPacket packet;
		byte buf[] = msg.getBytes();		
		packet = new DatagramPacket(buf, buf.length, player.getAddress(),player.getPort());
		try{
			serverSocket.send(packet);
		}catch(IOException ioe){
			System.out.println("ERROR || Game Server send");
			ioe.printStackTrace();
		}
	}
	
	public static void killAll(){
		connected = false;
    
		if(serverSocket!=null){
			serverSocket.close();
			serverSocket = null;
		}

    	players.clear();
    	gameStage=WAITING_FOR_PLAYERS;
		playerData = "";
		playerCount = 0;
		numDeadPlayers = 0;
		numPlayers = 0;

		currX = 100;
		currY = 0;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		connected = true;
		while(connected) {
			
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			try {
				serverSocket.receive(packet);
			} catch (Exception ioe) {}
			
			playerData = new String(buf);
			
			playerData = playerData.trim();
			
			switch(gameStage) {
				case WAITING_FOR_PLAYERS: //wait for players and register players that connect to the server
					if(playerData.startsWith("CONNECT")) {
						currX += 100;
					}
					
					String tokens[] = playerData.split(" ");
					String name = tokens[1].trim();
					PlayerInfo player = new PlayerInfo(name,packet.getAddress(),packet.getPort());
					player.setX(currX);
					player.setY(currY);
					players.put(name, player);
					
					System.out.println("Player connected: " + name);
					broadcast("CONNECTED " + name);
					
					playerCount++;
					if(playerCount == numPlayers) {
						gameStage = GAME_START;
					}
					break;
					
				case GAME_START: //broadcast player information to th whole server
					String startData = "START";
					
					for(String name1 : players.keySet()){
						PlayerInfo player1 = players.get(name1);
						startData += "#" + name1 + " " + player1.getX() + " " + player1.getY();
					}
					
					broadcast(startData);
					gameStage = IN_PROGRESS;
					break;
				
				case IN_PROGRESS:
					String inGameData = "INGAME";
					int x = 0, y = 0, damage = 0;
					
					if(playerData.equals("")) break;
					
					if(playerData.startsWith("WIN_GAME")) {
						bossIsDead = true;
						gameStage = GAME_END;
						break;
					}
					
					if(numDeadPlayers == numPlayers) {
						gameStage = GAME_END;
						break;
					}
					
					String[] playerInfo = playerData.split(" ");
					String pname = playerInfo[1];
					
					try {
						x = (int) Float.parseFloat(playerInfo[2].trim());
						y = (int) Float.parseFloat(playerInfo[3].trim());
					} catch (Exception e) {}
					
					if(playerData.startsWith("PLAYER")) {
						inGameData += "#PLAYER#" + pname + "#" + x + "#" + y;
 					} else if(playerData.startsWith("ENEMY")) {
 						inGameData += "#ENEMY#" + x + "#" + y;
 					} else if(playerData.startsWith("BULLET")) {
 						inGameData += "#BULLET#" + pname + "#" + x + "#" + y;
 					} else if(playerData.startsWith("DEAD")) {
 						inGameData += "#DEAD#" + pname;
 						numDeadPlayers++;
 					}
					
					broadcast(inGameData);
					break;
			
				case GAME_END:
					if(bossIsDead) {
						broadcast("GAME_OVER#WIN");
					} else {
						broadcast("GAME_OVER#LOSE");
					}
					
					if(playerData.startsWith("GAME_IN_ETERNAL_VOID")) {
						connected = false;
						break;
					}
					
					break;
			}
			
		}
		
	}

}
