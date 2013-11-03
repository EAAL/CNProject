import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientWriter extends Thread{
	public static int portno = 9090;
	Socket socket;
	BufferedReader inputStream;
	PrintWriter outStream;
	BufferedReader stdIn;
			
	
	 public void run() {
		 String serverAddress = JOptionPane.showInputDialog("Enter the server address");
		 try {
			socket = new Socket(serverAddress, portno);
			
			System.out.println("Connection established");
			
			outStream = new PrintWriter(
					socket.getOutputStream(), true);
			inputStream = new BufferedReader(
	                new InputStreamReader(socket.getInputStream()));
			stdIn = new BufferedReader(
				            new InputStreamReader(System.in));
			
			System.out.println("client writer handling");
			
			String inputString = stdIn.readLine();
			while(!inputString.equals("disconnect")){
				switch (inputString.split("\\s")[0]) {
				case "Register":
					System.out.println("register client");
					outStream.println(inputString);
					System.out.println("Server Log : "+inputStream.readLine());
					break;
				case "Save": //thread?
					System.out.println("save client");
					break;
				case "Share":
					break;
				case "Update":
					break;
				default:
					break;
				}
				inputString = stdIn.readLine();
				
			} 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	 }
}
