/* Author: Teo Kok Hien
 * Last Edit: 4 November 2013
 * 
 * This class listens on a port for activity from the CPU
 */

import java.net.InetAddress;
import java.net.ServerSocket;

public class InputServer extends  Thread{
	
	//int portNumber = Config.SERVER_PORT;
	int portNumber = 5558;
	boolean listening;
	//CenterController centerController;
	
	/*public InputServer(CenterController centerController) {
		this.centerController = centerController;
	}*/
	
	public static void main(String args[]){
		
		(new InputServer()).run();
	}
	
	
	public void run(){
		listening = true;
		try {
			ServerSocket serverSocket = new ServerSocket(portNumber);
			System.out.println("server started at " + InetAddress.getLocalHost()+ ":"+serverSocket.getLocalPort());
			while (listening) {
				//new InputServerThread(serverSocket.accept(), centerController).start();
				new InputServerThread(serverSocket.accept()).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}