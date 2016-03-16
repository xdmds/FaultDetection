/**
 * 
 */
package src;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author derekleung
 *
 * The sending process which repeatedly sends heartbeat messages to the
 * receiver process.
 */
public class Server {

			

	/**
	 * @param args
	 * 
	 * Uses server socket to output heartbeat messages, which the receiver
	 * process reads.
	 * 
	 * The Server and Receiver are connected used localhost at port 40002 
	 * by default
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket ss; 
			Socket dataSocket;
			
			while(true){
				ss = new ServerSocket(40002);
				dataSocket = ss.accept();
				PrintStream socketOutput = new PrintStream(dataSocket.getOutputStream());
				String message = "heartbeat message";
				socketOutput.println(message); //send the heartbeat message
				System.out.println("Server sent: " + message); //log the sent message
				socketOutput.flush();
				dataSocket.close();
				ss.close();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}

	}

}
