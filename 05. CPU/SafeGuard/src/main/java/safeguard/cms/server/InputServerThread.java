package safeguard.cms.server;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import safeguard.cms.primitive.message.Message;

public class InputServerThread extends ServerThread{
	
	public InputServerThread(Socket socket,Server server) {
		super(socket, server);
		addListeningMessage("eventMessage");
	}

}
