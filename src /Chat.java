import java.util.Scanner;
import java.io.IOException;

public class Chat {
	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		int choice = Integer.parseInt(args[0]);
		if(choice == 1){
			String port = args[1];
	        try{
	            ChatServer chatServer = new ChatServer(Integer.parseInt(port));
	        }catch(IOException e){
	            e.printStackTrace();
	        }
		}else if(choice == 2){
			String ip = args[2];
			String port = args[1];
			System.out.println("Enter Name: ");
			String name = sc.nextLine();
			ChatClient chatClient = new ChatClient(ip, Integer.parseInt(port), name);
		}
    }
}
