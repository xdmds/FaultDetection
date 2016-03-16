# FaultDetection
heartbeat

1. Navigate to the "jar files" directory containing HeartBeat.jar, Receiver.jar, and Server.jar. They All need to be in the same directory

2. Run the following command "java -jar HeartBeat.jar" in the command line

3. Port 40002 must be open for this to run properly. If it doesn’t run properly, make sure that port is open.

Output will be as follows

-A print statement before starting the Server and Receiver processes<br/>
-A print statement after starting the Server and Receiver processes<br/>
-A print statement stating what integer the crash generator generated. If it generates a "1", the server process will "fail"<br/>
	&nbsp;-If the Server process fails, a print statement will say the server process is destroyed and restarting<br/>
	-The server process will be destroyed, then restarted in 3 seconds.<br/>
	-The Receiver will notice it is no longer connected to the Server and didn't receive a heartbeat message.<br/>
	-Receiver prints this information<br/>
	-Normal operation will resume once the server process is restarted.<br/>
-print statements with a boolean stating whether or not each process is alive (Monitors kind of implements ping echo. This can easily be removed if we only want heartbeat)<br/>
-print statement with the message the Server sends<br/>
-print statement with the message the Receiver gets<br/>
