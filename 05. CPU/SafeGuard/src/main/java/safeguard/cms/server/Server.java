package safeguard.cms.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import safeguard.cms.Scheduler;
import safeguard.cms.primitive.message.Message;

public class Server extends Thread{
	protected int portNumber;
	protected boolean listening;
	protected Scheduler scheduler;
	protected Class<ServerThread> serverThread;
	ServerSocket serverSocket;
	
	public Server(int port, Class<?> serverThread, Scheduler scheduler) {
		this.portNumber = port;
		this.serverThread = (Class<ServerThread>) serverThread;
		this.scheduler = scheduler;
	}
	
	public void startServer() {
		listening = true;
		start();
	}
	
	public void stopServer() {
		listening = false;
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send2Sheduler(Message msg){
		scheduler.receiveMessage(msg);
	}
	
	public void run(){
		try {
			serverSocket = new ServerSocket(portNumber);
			System.out.println("server started at " + InetAddress.getLocalHost()+ ":"+serverSocket.getLocalPort());
			while (listening) {
				serverThread.getConstructor(Socket.class,Server.class).newInstance(serverSocket.accept(),this).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
