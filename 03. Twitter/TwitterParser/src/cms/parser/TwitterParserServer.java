package cms.parser;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class TwitterParserServer implements Runnable {
	
	int tcpPort = 5556;
	public void run()
	{
		
		try 
		{
			
			ServerSocket serverSocket = new ServerSocket(tcpPort);
			System.out.println("Twitter Parser TCP Server started at " + InetAddress.getLocalHost()+ ":"+serverSocket.getLocalPort());
			
			while(true)
			{
				Socket clientSocket = serverSocket.accept();
				ObjectInput in = new ObjectInputStream(clientSocket.getInputStream());
				String jsonString = (String) in.readObject();
				JSONObject jsonObj = (JSONObject) JSONValue.parse(jsonString);
				System.out.println(jsonString);
				Poster(jsonObj);	
			}

		}
		catch (IOException e) 
		{
	        System.out.println(e.toString());
	        e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
		public static void main(String[] args) {
			try {
				//tcp server
				(new Thread(new TwitterParserServer())).start();

				//udp server
				MulticastSocket socket = new MulticastSocket(5557);
				InetAddress address = InetAddress.getByName("230.0.0.1");
				socket.joinGroup(address);
				
				DatagramPacket packet;
				
				System.out.println("Twitter Parser UDP Server is listening");
				initialization();
				while(true)
				{
					byte[] buf = new byte[1024];
					packet = new DatagramPacket(buf, buf.length);
					socket.receive(packet);
					System.out.println("received");
					String received = new String(packet.getData()).trim();
					//String JSONTest = new String("{\"Description\":\"this is a description\",\"Type\":\"Type1\",\"Details\":\"this is detail\",\"Title\":\"testTtitle\"}");
					System.out.println(received);
					//JSONObject obj = (JSONObject) JSONValue.parse(JSONTest);
					JSONObject obj = (JSONObject) JSONValue.parse(received);
					System.out.println(obj.toJSONString());
					Poster(obj);
				}
				//socket.leaveGroup(address);
				//socket.close();
				////JSONObject jsonObject = new JSONObject();
				//jsonObject.put("Type","Type1");
				//jsonObject.put("Description","this is test on type 1");
				
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}


public static void Poster(JSONObject input1)
{
	new TwitterPoster().send(input1);
}

public static void initialization()
{
	try
	{
		//connect to be confirmed
		Socket controlSocket = new Socket("192.168.1.102",5001);
		ObjectOutput out =   new ObjectOutputStream(controlSocket.getOutputStream());
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("messageType", "controlMessage" );
		jsonObj.put("ipAddress", InetAddress.getLocalHost().getHostAddress());
	
		out.writeObject(jsonObj.toJSONString());
		out.flush();
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	
	

}

}
