package safeguard.cms.stream;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;

import org.json.simple.JSONObject;

import safeguard.Config;
import safeguard.cms.primitive.message.EventMessage;
import safeguard.cms.primitive.message.Message;

public class NonCriticalMessageStream extends StreamPoster {
	public int port = Config.NONCRITICAL_PORT;
	public String broadCastAddr = Config.BROAD_CAST_ADDRESS;
	private DatagramSocket socket; 
	private InetAddress group;
	int criticalLevelThreashhold = 5;
	
	public NonCriticalMessageStream() {
		try {
			socket = new DatagramSocket(port);
			group = InetAddress.getByName(broadCastAddr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void publish(Message message) {
		try {
			byte[] buf = new byte[4096];
			String jsonString = message.parseToJason();
			buf = jsonString.getBytes();
			DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
		
			socket.send(packet);
			System.out.println("Noncritical sent");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		port = Config.NONCRITICAL_PORT;
		broadCastAddr = Config.BROAD_CAST_ADDRESS;
	}

	@Override
	protected boolean canSend(Message message) {
		if (!(message instanceof EventMessage)) {
			return false;
		}
		EventMessage evtMessage = (EventMessage) message;
		return evtMessage.getCriticalLevel() < criticalLevelThreashhold;
	}
}
