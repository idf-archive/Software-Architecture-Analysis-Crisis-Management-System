package cms.parser;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import org.json.simple.JSONObject;

public class testFacebookUnitTest {

	public void TestfbParser()
	{
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("Title", "Test Title");
			jsonObj.put("Type", "Test Type");
			jsonObj.put("Description", "Test Description");
			jsonObj.put("Details", "Test Detail");
			

	FacebookPoster testSendMsg = new FacebookPoster();
	testSendMsg().send(jsonObj);


	}
}
