package safeguard.cms.stream;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Observable;
import java.util.Observer;

import safeguard.Config;
import safeguard.cms.ClientThread;
import safeguard.cms.primitive.message.Message;
import safeguard.cms.primitive.message.ReportMessage;

public class ReportStream extends StreamPoster {
	String addr = Config.REPORT_PARSER_ADDRESS;
	int port = Config.REPORT_PORT;

	@Override
	protected boolean canSend(Message message) {
		return message instanceof ReportMessage;
	}

	@Override
	protected void publish(Message message) {
		try {
			String jsonString = message.parseToJason();
			new ClientThread(jsonString, new InetSocketAddress(InetAddress.getByName(addr),port)).start();
			
		} catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		port = Config.REPORT_PORT;
		addr = Config.REPORT_PARSER_ADDRESS;
	}


}
