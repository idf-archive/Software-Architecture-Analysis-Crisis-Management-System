package safeguard;

import java.net.InetAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.Observable;

public class Config extends Observable{
	public static int CRITICAL_PORT = 5556;
	public static int NONCRITICAL_PORT = 5557;
	public static int REPORT_PORT = 5558;
	public static int INPUT_SERVER_PORT = 5000;
	public static int CONTROLL_SERVER_PORT = 5001;
	public static int DEFAULT_EMERGENCY_LEVEL = 1;
	public static Config instance;
	
	public static String BROAD_CAST_ADDRESS = "230.0.0.1";
	public static String REPORT_PARSER_ADDRESS = "192.168.1.107";
	public static Collection<InetAddress> CRITICAL_RECEIVER;
	
	public Config() {
		CRITICAL_RECEIVER = new HashSet<InetAddress>();
		instance = this;
	}
	public void addCriticalReceiver(InetAddress addr) {
		CRITICAL_RECEIVER.add(addr);
		setChanged();
	    notifyObservers();
	}
}
