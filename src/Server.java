import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	static int portno = 9090;
	static int clientNum = 0;
	public static void main(String[] args) throws IOException{
		ServerSocket readerSocket = new ServerSocket(portno);
		while(true){
			System.out.println("server");
			Socket clientSocket = readerSocket.accept();
				if(clientNum<5){
					clientNum++;
					new ClientHandler1(clientSocket).start();
					//client handler
				}else if(clientNum == 6){
					clientNum++;
				}else{
					clientNum++;
				}
			
		}
		
		
	}
}
