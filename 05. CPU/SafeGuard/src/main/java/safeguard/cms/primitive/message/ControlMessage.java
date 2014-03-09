package safeguard.cms.primitive.message;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import safeguard.Config;
import safeguard.cms.ClientThread;
import safeguard.cms.Scheduler;
import safeguard.cms.database.DatabaseConnector;

public class ControlMessage extends Message{
	
	private InetAddress addr;
	private boolean incidents;
	private Socket socket;
	

	public Socket getSocket() {
		return socket;
	}


	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	public InetAddress getAddr() {
		return addr;
	}


	public void setAddr(InetAddress addr) {
		this.addr = addr;
	}
	


	@Override
	public void parseMessage(String str) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = (JSONObject) JSONValue.parse(str);
		try {
			addr = InetAddress.getByName((String) jsonObject.get("ipAddress"));
			if (jsonObject.containsKey("incidents"))
				incidents = (Boolean) jsonObject.get("incidents");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public String parseToJason() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void schedule(Scheduler scheduler) {
		System.out.println("Adding critical reciever");
		Config.instance.addCriticalReceiver(this.getAddr());
		
		if (incidents) {
			DatabaseConnector dbconnector = scheduler.getDBConnector();
			String str = dbconnector.getIncident();
			System.out.println(str);
			new ClientThread(str, socket).start();
			
		}
		
	}

}
