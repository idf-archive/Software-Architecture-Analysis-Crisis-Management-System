package safeguard.cms;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread extends Thread {
	Object msg;
	InetAddress targetAddr;
	int targetPort;
	Socket socket;
	public ClientThread(Object msg, InetSocketAddress addr) {
		this.msg = msg;
		targetAddr = addr.getAddress();
		targetPort = addr.getPort();
	}
	public ClientThread(Object msg, Socket socket) {
		this.msg = msg;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Connecting to "+targetAddr+":"+targetPort);
			if (socket == null)
				socket = new Socket(targetAddr, targetPort);
			ObjectOutput output = new ObjectOutputStream(socket.getOutputStream()); 
			output.writeObject(msg);
			System.out.println("Message sent");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception connecting to " + targetAddr + " " + targetPort);
			e.printStackTrace();
		}
		
	}

}
