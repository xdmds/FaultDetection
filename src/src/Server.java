/**
 * 
 */
package src;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author derekleung
 *
 *	Description: critical process that will randomly die
 */
public class Server extends Process {

	private Receiver rc;
	
	public Server(Receiver rc){
		this.rc = rc;
	}
	
	/**
	 * 
	 */
	public void heartBeat() {
		while(true){ //check if this process is alive
			//send out heartbeat message
			//Writer write = new OutputStreamWriter(this.getOutputStream());
				//write.write("heartbeat");
			if(this.isAlive()){
				this.rc.setMessage("heartbeat");
				System.out.println("alive");
			} else {
				System.out.println("not alive");
			}
			try {
				this.waitFor(1, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * 
	 */
	public void crashGenerator() {
		Random rg = new Random();
		while(true){
			int r = rg.nextInt(21 - 1) + 1;
			if(r == 1){
				this.destroyForcibly(); //kill this process forcibly
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Process#getOutputStream()
	 */
	@Override
	public OutputStream getOutputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Process#getInputStream()
	 */
	@Override
	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Process#getErrorStream()
	 */
	@Override
	public InputStream getErrorStream() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Process#waitFor()
	 */
	@Override
	public int waitFor() throws InterruptedException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Process#exitValue()
	 */
	@Override
	public int exitValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Process#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hi");
		Receiver rc = new Receiver();
		Server serverProc = new Server(rc);
		serverProc.heartBeat();
		//serverProc.crashGenerator();
		rc.run();
	}

}
