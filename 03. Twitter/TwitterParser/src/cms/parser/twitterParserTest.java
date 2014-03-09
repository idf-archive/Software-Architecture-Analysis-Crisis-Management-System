package cms.parser;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import org.json.simple.JSONObject;

public class twitterParserTest {
	
	public static void main(String args[])
	{
		
		try
		{
			MulticastSocket socket = new MulticastSocket(4999);
			InetAddress address = InetAddress.getByName("230.0.0.1");
			socket.joinGroup(address);
			DatagramPacket packet;
			byte[] buf = new byte[1024];
			//packet = new DatagramPacket(buf, buf.length);
			
			JSONObject jsonObj = new JSONObject();
			//jsonObj.put("Title", "Test Title");
			jsonObj.put("Type", "Test Type");
			jsonObj.put("Description", "Test Description");
			//jsonObj.put("Details", "Test Detail");
			packet = new DatagramPacket(jsonObj.toJSONString().getBytes(), jsonObj.toJSONString().getBytes().length, address, 4446);
			//packet = new DatagramPacket("HELLLO".getBytes(), "HELLLO".getBytes().length, address, 4446);
			socket.send(packet);
			socket.close();
		}
		catch(Exception e)
		{
			
			
		}

	}

}
