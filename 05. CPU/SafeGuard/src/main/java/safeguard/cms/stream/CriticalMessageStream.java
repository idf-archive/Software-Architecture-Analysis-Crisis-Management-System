package safeguard.cms.stream;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import org.json.simple.JSONObject;

import safeguard.Config;
import safeguard.cms.ClientThread;
import safeguard.cms.primitive.message.EventMessage;
import safeguard.cms.primitive.message.Message;

public class CriticalMessageStream extends StreamPoster {
	Collection<InetAddress> receivers = Config.CRITICAL_RECEIVER;
	int port = Config.CRITICAL_PORT;
	int criticalLevelThreashhold = 5;
	
	public CriticalMessageStream() {
		receivers = new HashSet<InetAddress>();
	}

	@Override
	public void publish(Message message) {
		try {
			String jsonString = message.parseToJason();

			for (InetAddress addr: receivers) 
				new ClientThread(jsonString, new InetSocketAddress(addr,port)).start();
			
		} catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void addReceiver(InetAddress addr) {
		receivers.add(addr);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		port = Config.CRITICAL_PORT;
		receivers = Config.CRITICAL_RECEIVER;
		for (InetAddress addr :receivers)
			System.out.println(addr.toString());
	}

	@Override
	protected boolean canSend(Message message) {
		if (!(message instanceof EventMessage)) {
			return false;
		}
		EventMessage evtMessage = (EventMessage) message;
		return evtMessage.getCriticalLevel() >= criticalLevelThreashhold;
	}
}
