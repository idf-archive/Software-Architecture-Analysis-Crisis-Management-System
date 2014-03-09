package safeguard.cms.stream;

import java.net.DatagramSocket;
import java.util.Observer;

import safeguard.cms.primitive.message.Message;

public abstract class StreamPoster implements Observer {
	//protected DatagramSocket socket;

	public void push(Message message) {
		if (canSend(message))
			publish(message);
	}
	protected abstract boolean canSend(Message message);
	protected abstract void publish(Message message);
	
}
