import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect;
import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * 
 * @author derekleung
 * The monitoring process which continuously checks the Server and Receiver 
 * processes to see if they're alive. It logs whether or not each is alive.
 */
public class Main {
	private static Process serverproc;
	private static Process recproc;
	private static ProcessBuilder server;
	private static ProcessBuilder receiver;
	/**
	 * Generates random integers within a set range every second. If the
	 * generated integer is "1", the server process will fail and 
	 * Main will restart the Server process after 3 seconds.
	 */
	public static void crashGenerator() {
		Random rg = new Random();
		int r = rg.nextInt(11 - 1) + 1;
		System.out.println("------------------------------\n\ncrash generator number: " + r);
		if(r == 1){
			//this.destroyForcibly(); //kill this process forcibly
			System.out.println("need to kill process");
			serverproc.destroyForcibly(); //kill the server process
			System.out.println("server process destroyed...Restarting in 3 seconds...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				serverproc = server.start(); //restart server process
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Main method which starts the server and receiver processes. It also
	 * runs the crash generator and monitors the server and receiver processes
	 * logging whether or not they're alive.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

			server = new ProcessBuilder("java", "-jar", "Server.jar");
			server.redirectOutput(Redirect.INHERIT); //redirect output to stdout
			receiver = new ProcessBuilder("java", "-jar", "Receiver.jar");
			receiver.redirectOutput(Redirect.INHERIT); //redirect output to stdout
			try {
				System.out.println("Before Start");
				serverproc = server.start(); //start server process
				recproc = receiver.start(); //start receiver process
				System.out.println("After start");
				while(true){
					crashGenerator();
					//monitor server and receiver processes if they are 
					//alive or not and logs the results
					System.out.println("server alive: " + serverproc.isAlive());
					System.out.println("receiver alive: " + recproc.isAlive());
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
