
import java.io.*;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class SMSServer {
	
	public static void main(String[] args){
	String smsHost = "localhost";
	int smsPort = 9500;
	String username = "admin";
	String password = "12345";
	
	int tcpPort = 5556;
	
	
	
	//Database Initiation Entity Manager based on persistence.xml for MySQL 
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMSSystem");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();
	List <Phonebook> phoneBookList = em.createQuery("SELECT a from Phonebook a", Phonebook.class).getResultList();
	
	//SMS Initiation with Control on CPU
	initialization();
	try 
	{
		MySmsClient osc = new MySmsClient(smsHost, smsPort);
		osc.login(username, password);
		ServerSocket serverSocket = new ServerSocket(tcpPort);
		System.out.println("SMS Server started at " + InetAddress.getLocalHost()+ ":"+serverSocket.getLocalPort());
		
		while(true)
		{
			Socket clientSocket = serverSocket.accept();
			PrintWriter out =   new PrintWriter(clientSocket.getOutputStream(), true);
			ObjectInput in = new ObjectInputStream(clientSocket.getInputStream());
			String jsonString = (String) in.readObject();
			JSONObject jsonObj = (JSONObject) JSONValue.parse(jsonString);
			
			System.out.println(jsonString);
			String message = (String) jsonObj.get("description");
			String location = (String) jsonObj.get("location");

			String incidentID = (String) jsonObj.get("id");
			String incidentPostal = ((String) jsonObj.get("postal")).substring(0,2);
			String incidentType = (String) jsonObj.get("type");
			
			JSONArray agencyJsonArray =  (JSONArray) JSONValue.parse((String)jsonObj.get("agency"));
			
			
			phoneBookList = em.createQuery("SELECT a from Phonebook a", Phonebook.class).getResultList();	
			if(osc.isLoggedIn()) 
			{
			
					for(int i =0; i < agencyJsonArray.size(); i++)
					{
					
						JSONObject json = (JSONObject) agencyJsonArray.get(i);
						String agencyPostal = (String) json.get("postal");
						String agencyPhone = (String) json.get("phone");
					

						if(agencyPostal.substring(0,2).equals(incidentPostal))
						{
							osc.sendMessage(agencyPhone, "CMS Notification" + "\n Incident ID: " + incidentID +  "\n Location: " +location + "\n Incident Type: " + incidentType + "\n Description: "+ message);

						}
					}
					
					for (Phonebook i : phoneBookList)
					{
						if(i.getPostal().substring(0, 2).equals(incidentPostal))
						{
							System.out.println(i.getPhoneNum() + " " + message);
							osc.sendMessage(i.getPhoneNum(),"CMS Notification" + "\n Location: " +location + "\n Incident Type: " + incidentType + "\n Description: "+ message);
						}
					}
			}
		}

	}
	catch (IOException e) 
	{
        System.out.println(e.toString());
        e.printStackTrace();
	} 
	catch (InterruptedException e)
	{
        System.out.println(e.toString());
        e.printStackTrace();
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	
}

	//send control jsonString to CPU
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
