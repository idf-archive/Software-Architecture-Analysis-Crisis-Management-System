import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;


public class controlServer {
	
	public static void main(String [] args)
	{

		
		int tcpPort = 2500;		
		try
		{
			
			/* Integration code for CPU Control Port
			 
			ServerSocket serverSocket = new ServerSocket(2500);
			System.out.println("server started at " + InetAddress.getLocalHost()+ ":"+serverSocket.getLocalPort());
			
			while(true)
			{
			
				Socket clientSocket = serverSocket.accept();
				PrintWriter out =   new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String json = in.readLine();
				System.out.println(json);
			}
			*/
			
			//Integration
			Socket smsServerSocket = new Socket("192.168.58.1",5556);
			PrintWriter out =   new PrintWriter(smsServerSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(smsServerSocket.getInputStream()));
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("message", "Tiu Tiu Tiu");
			jsonObj.put("postal", "52");

			out.println(jsonObj.toJSONString());
			out.flush();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	}

}
