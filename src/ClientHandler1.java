import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
		System.out.println("client handling befor while");
		outStream.println(" you are connected ");
		while(fromClient!=null && (!fromClient.equals("disconnect"))){
			System.out.println("******"+fromClient.split("\\s")[0]);
			switch (fromClient.split("\\s")[0]) {
			case "Register":
				System.out.println("man khafanam");
				break;
			case "Save": //thread?
				break;
			case "Share":
				break;
			case "Update":
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
