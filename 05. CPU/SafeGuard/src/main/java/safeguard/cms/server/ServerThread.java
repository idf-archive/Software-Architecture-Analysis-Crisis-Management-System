package safeguard.cms.server;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import safeguard.cms.primitive.message.EventMessage;
import safeguard.cms.primitive.message.Message;

public class ServerThread extends Thread{
	protected ObjectInput input;
	protected Socket socket = null;
	protected BeanFactory factory;
	protected Server server;
	protected Set<String> listeningMessage;
	
	public ServerThread(Socket socket, Server server) {
		try {
			listeningMessage = new HashSet<String>();
			this.socket = socket;
			this.server = server;
			factory = new XmlBeanFactory(new FileSystemResource("Spring.xml"));			
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ServerThread(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}
	
	@Override
	public void run() {

		try {
			String str = (String) input.readObject();
			System.out.println("received " + str);
			JSONObject jsonObject = (JSONObject) JSONValue.parse(str);
			String type =(String) jsonObject.get("messageType"); 
			if (listeningMessage.contains(type)) {
				Message msg = (Message) factory.getBean(type);
				msg.parseMessage(str);
				//System.out.println("Message received with critical level "+((EventMessage) msg).getCriticalLevel());
				server.send2Sheduler(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addListeningMessage(String str) {
		listeningMessage.add(str);
	}

}
