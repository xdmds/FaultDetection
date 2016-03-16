/**
 * 
 */
package src;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author derekleung
 * The receiving process which receives heartbeat messages from the
 * server process. Prints to stdout notifying user when connection to the
 * server is lost and no heartbeat message is received
 */
public class Receiver {
	
	/**
	 * @param args
	 * 
	 * Uses client Socket to receive heartbeat messages from the server process.
	 * When the server process fails, Socket connection between the receiver and
	 * server sockets is lost, which the receiver logs to stdout.
	 * 
	 * The Server and Receiver are connected used localhost at port 40002 
	 * by default
	 */
	public static void main(String[] args) {
		try {
			InetAddress acceptorHost = InetAddress.getByName("localhost");
			int serverPortNum = 40002;
			while(true){
				try {
						Socket clientSocket;
							clientSocket = new Socket(acceptorHost, serverPortNum);
							BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
							String line = br.readLine(); //the message received from the server process
						if(line != null){ //check if there's a heartbeat message
								System.out.println("Receiver got: " + line); //log the message received
							}
							clientSocket.close();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("No connection to Server. No heartbeat message received...");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
