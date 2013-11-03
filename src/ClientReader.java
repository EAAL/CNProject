import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientReader extends Thread{
	public static int portno = 2014;
	ServerSocket readersocket;
	BufferedReader inputStream;
	PrintWriter outStream;
			
	public void run() {
		try {
	 
			ServerSocket readerSocket = new ServerSocket(portno);
			Socket serverSocket = readerSocket.accept();
			PrintWriter outStream =
			    new PrintWriter(serverSocket.getOutputStream(), true);
			BufferedReader inputStream = new BufferedReader(
			    new InputStreamReader(serverSocket.getInputStream()));
			String fromServer = "";
			System.out.println("client reader handling before while");
			while((fromServer = inputStream.readLine())!=null){
				//receiving file from server 
			}
			}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
