package safeguard.cms.server;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import safeguard.cms.primitive.message.ControlMessage;
import safeguard.cms.primitive.message.Message;

public class ControlServerThread extends ServerThread{
	
	public ControlServerThread(Socket socket, Server server) {
		super(socket, server);
		addListeningMessage("controlMessage");
	}
	
	@Override
	public void run() {

		try {
			String str = (String) input.readObject();
			System.out.println("received " + str);
			JSONObject jsonObject = (JSONObject) JSONValue.parse(str);
			String type =(String) jsonObject.get("messageType"); 
			if (listeningMessage.contains(type)) {
				ControlMessage msg = (ControlMessage) factory.getBean(type);
				msg.parseMessage(str);
				msg.setSocket(socket);
				//System.out.println("Message received with critical level "+((EventMessage) msg).getCriticalLevel());
				server.send2Sheduler(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
