import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class ClientHandler1 extends Thread{
	Scanner inputStream;
	PrintWriter outStream;
	BufferedReader stdIn;
	Socket clientSocket;
	
	public ClientHandler1(Socket cSocket) throws IOException{
		clientSocket = cSocket;
		outStream = new PrintWriter(
				clientSocket.getOutputStream(), true);
		inputStream = new Scanner(new InputStreamReader(clientSocket.getInputStream()));
		/*inputStream = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));*/
		stdIn = new BufferedReader(
			            new InputStreamReader(System.in));
		
	}
	public void run() {
		
		String fromClient = "";
		//TODO get client's IP and put it inside IP
		String IP = "127.0.0.1";
		String userID = "";
		String fileName = "";
		String filePath = "";
		String report = "";
		System.out.println("client handling befor while");
		outStream.println(" you are connected ");
		while(fromClient!=null && (!fromClient.equals("disconnect"))){
			System.out.println("******"+fromClient.split("\\s")[0]);
			String command[] = fromClient.split("\\s");
			switch (command[0]) {
			case "Register":
				System.out.println("man khafanam");
				report = DataAccess.registerUser(command[1], IP);
				break;
			case "Save": //thread?
				//TODO set userID, fileName and filePath
				report = DataAccess.addFile(userID, fileName, filePath);
				break;
			case "Share":
				//TODO set userID, fileName and filePath
				report = DataAccess.addFile(userID, fileName, filePath);
				break;
			case "Update":
				//TODO 
				ArrayList<String> sharedWith = DataAccess.getUsersWhoHave(fileName);
				for(String userName : sharedWith){
					String userIP = DataAccess.getUserIPByID(userName);
					//TODO send file to this IP
				}
				break;
			default:
				break;
			}
			fromClient = inputStream.nextLine();
			
		}
		
		if(fromClient.equals("disconnect")){
			// client num
			//dc from server
			// thread close
		} 
		
	}

}
