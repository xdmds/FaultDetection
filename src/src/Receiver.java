/**
 * 
 */
package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author derekleung
 *
 */
public class Receiver extends Process {

	private String message = ""; 
	
	public void run(){
		while(true){
			if(message.equalsIgnoreCase("heartbeat")){
				System.out.println("Received heartbeat message");
				this.message="";
			} else {
				System.out.println("Didn't receive heartbeat message");
				//need to restart server process
			}
			try {
				this.waitFor(1, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setMessage(String message){
		this.message = message;
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

}
