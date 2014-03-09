package safeguard.cms;

import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import safeguard.Config;
import safeguard.cms.database.DatabaseConnector;
import safeguard.cms.database.RMIDatabaseConnector;
import safeguard.cms.primitive.message.Message;
import safeguard.cms.server.ControlServerThread;
import safeguard.cms.server.InputServerThread;
import safeguard.cms.server.Server;
import safeguard.cms.stream.CriticalMessageStream;
import safeguard.cms.stream.NonCriticalMessageStream;
import safeguard.cms.stream.ReportStream;
import safeguard.cms.stream.StreamPoster;

/*
 * scheduler is doing the scheduling of CPU as  well as relaying messages *
 */

public class Scheduler extends Thread{
	Queue<Message> eventQueue;
	DatabaseConnector dbConnector;
	MessageDistributer distributer;
	ReportManager reportManager;
	Collection<Server> servers;
	boolean running;
	public Scheduler() {
		eventQueue = new ConcurrentLinkedQueue<Message>();
		servers = new HashSet<Server>();
		init();
	}
	
	@Override
	
	public void run() {
		while (running) {
			if (!eventQueue.isEmpty()) {
				System.out.println("Processing Message " + eventQueue.peek());
				processMessage(eventQueue.poll());
			}
		}
	}
	
	public void processMessage(Message msg) {
		msg.schedule(this);
		
	}
	
	public DatabaseConnector getDBConnector() {
		return dbConnector;
	}
	
	public void receiveMessage(Message msg) {
		eventQueue.add(msg);
	}
	
	public void sendMessage2Distributer(Message msg) {
		distributer.addToPushQueue(msg);
	}
	
	public String getRecentTrendFromDB() {
		return dbConnector.getRecentTrend();
		
	}
	/*
	 * creating and initializing components
	 * 		1. Message distributer
	 * 			streams
	 * 		2. Servers
	 * 		3. Config Class
	 */
	public void init() {
		new Config();
		distributer = new MessageDistributer();
		System.out.println("Message distributer initialized");
		servers.add(new Server(Config.INPUT_SERVER_PORT, InputServerThread.class, this));
		System.out.println("Input server initialized");
		servers.add(new Server(Config.CONTROLL_SERVER_PORT, ControlServerThread.class, this));
		System.out.println("Controll server initialized");
		
		dbConnector = new RMIDatabaseConnector();	
		System.out.println("Database connector created");
		reportManager = new ReportManager(this);
		System.out.println("Report manager created");
	}
	public void startScheduler() {
		distributer.startDistributer();
		System.out.println("Message distributer started");
		for (Server server: servers)  {
			server.startServer();
		}
		System.out.println("Servers started");
		running = true;
		start();
		System.out.println("Scheduler started");
		reportManager.startReportManager();
		System.out.println("Report manager started");
	}
	public void stopScheduler() {
		try {
			distributer.stopDistributer();
			reportManager.stopReportManager();
			
			for (Server server: servers) { 
				server.stopServer();
				server.join();
			}

			System.out.println("Servers stopped");
			
			reportManager.join();
			System.out.println("Report manager stopped");
			distributer.join();
			System.out.println("Message distributer stopped");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		running = false;
	}

}
