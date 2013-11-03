import javax.swing.JOptionPane;
import java.net.Socket;

public class Client {

	public static void main(String[] args){
		
		new ClientWriter().start();
		new ClientReader().start();
	}
}
