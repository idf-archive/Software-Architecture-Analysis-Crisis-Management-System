package safeguard;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*; 
import java.sql.Timestamp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.List;

public class RmiServer extends UnicastRemoteObject implements RmiServerIntf {

	private SafeGuardDAO dao = new SafeGuardDAO();
	
	 

	public RmiServer() throws RemoteException {
	    super(0);    // required to avoid the 'mic' step, see below
	}
	
	public static void main(String [ ] args) throws Exception 
	{
		System.out.println("RMI server started");
		if (System.getSecurityManager() == null)
	       System.setSecurityManager ( new RMISecurityManager() );
		
        try 
        { 
        	
        	//special exception handler for registry creation
            LocateRegistry.createRegistry(1099); 
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
 
        //Instantiate RmiServer
        RmiServer obj = new RmiServer();
 
        // Bind this object instance to the name "RmiServer"
        Naming.rebind("//192.168.1.103/RmiServer", obj);
        System.out.println("PeerServer bound in registry");
	}

	/*
	public String getMessage() {
	    return MESSAGE;
	}
	*/

	public String updateIncident(String jsonString)
	{
		try
		{
			return dao.updateIncident(jsonString);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	public String getAgency()
	{
		return dao.getAgency();
	}
	public String getAgentType()
	{
		return dao.getAgentType();
		
	}
	public String getDengueSpot()
	{
		return dao.getDengueSpot();
		
	}
	public String getHaze()
	{
		return dao.getHaze();
		
	}

	public String getIncident()
	{
	
		return dao.getIncident();
		
	}
	public String getIncidentType()
	{
		return dao.getIncidentType();
		
	}
	public String getRecentTrend()
	{
		return dao.getRecentTrend();
	}
	public String getOperator()
	{
		
		return dao.getOperator();
	}
	public String getShelter()
	{
		return dao.getShelter();
		
	}
	public String getNearestShelter(String postalCode)
	{
		try
		{
			return dao.getNearestShelter(postalCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;

	}
	
	public String getNearestAgency(String postalCode)
	{
		
		try
		{
			return dao.getNearestAgency(postalCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	
	public void addAgency(String jsonString)
	{
		try
		{
			dao.addAgency(jsonString);
		}
		catch(Exception e)
		{
			System.out.println("Error in processing JSON");
		}
		
	}
	
	public void addAgencyType(String jsonString)
	{
		try
		{
			dao.addAgencyType(jsonString);
		}
		catch(Exception e)
		{
			System.out.println("Error in processing JSON");
		}
		
	}
	
	public void addDengueSpot(String jsonString)
	{
		try
		{
			dao.addDengueSpot(jsonString);
		}
		catch(Exception e)
		{
			System.out.println("Error in processing JSON");
		}
	}
	
	public void addHaze(String jsonString)
	{
		try
		{
			dao.addHaze(jsonString);
		}
		catch(Exception e)
		{
			System.out.println("Error in processing JSON");
		}
	}
	


	public String addIncident(String jsonString)
	{
		try
		{
			return dao.addIncident(jsonString);
		}
		catch(Exception e)
		{
			System.out.println("Error in processing JSON");
		}
		return null;
	
	}

	public void addIncidentType(String jsonString)
	{
		try
		{
			dao.addIncidentType(jsonString);
		}
		catch(Exception e)
		{
			System.out.println("Error in processing JSON");
		}
	}

	public void addOperator(String jsonString)
	{
		try
		{
			dao.addOperator(jsonString);
		}
		catch(Exception e)
		{
			System.out.println("Error in processing JSON");
		}
	}
	public void addShelter(String jsonString)
	{
		try
		{
			dao.addShelter(jsonString);
		}
		catch(Exception e)
		{
			System.out.println("Error in processing JSON");
		}
	}
	
}
