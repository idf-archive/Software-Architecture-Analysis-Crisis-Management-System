package cms.parser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class FacebookParserServerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try
		{
			MulticastSocket socket = new MulticastSocket(4446);
			InetAddress address = InetAddress.getByName("230.0.0.1");
			socket.joinGroup(address);
			DatagramPacket packet;
			byte[] buf = new byte[1024];
			//packet = new DatagramPacket(buf, buf.length);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("Title", "Test Title");
			jsonObj.put("Type", "Test Type");
			jsonObj.put("Description", "Test Description");
			jsonObj.put("Details", "Test Detail");
			packet = new DatagramPacket(jsonObj.toJSONString().getBytes(), jsonObj.toJSONString().getBytes().length, address, 4446);
			socket.send(packet);
			socket.close();
		}
		catch(Exception e)
		{
			
			
		}

	}

}
