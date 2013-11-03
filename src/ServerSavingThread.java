import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ServerSavingThread extends Thread{
	Scanner inputStream;
	PrintWriter outStream;
	String fileAddress;
	
	public ServerSavingThread(Scanner input, PrintWriter out, String file){
		inputStream = input;
		outStream = out;
		fileAddress = file;
	}
	
	public void run(){
		try {
			FileWriter fout = new FileWriter(new File(fileAddress));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
