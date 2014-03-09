package safeguard;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.sql.Timestamp;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
public class RmiClient {
	
	
	public static void addAgency(RmiServerIntf obj) throws Exception
	{
		  JSONObject newJSON = new JSONObject();
	      newJSON.put("address", "New agency test");
	      newJSON.put("phone", "412414124");
	      newJSON.put("postal", "12567");
	      newJSON.put("typeId", "1");
	      obj.addAgency(newJSON.toJSONString());
	
	}
	public static void addAgentType( RmiServerIntf obj) throws Exception
	{
		  JSONObject newJSON = new JSONObject();
	      newJSON.put("typeName", "New agent type test");
	      obj.addAgency(newJSON.toJSONString());

	}
	
	public static void addIncident(RmiServerIntf obj) throws Exception
	{
	
		java.util.Date date= new java.util.Date();
		 JSONObject newIncident = new JSONObject();
        newIncident.put("callerName", "Mr low ba low ba");
        newIncident.put("location", "207 Nanyang Avenue");
        newIncident.put("postal", "122230");
        newIncident.put("callerPhone", "11412412502");
        newIncident.put("description", "this is a trial run3");
        newIncident.put("display", "true");
        newIncident.put("level", "4");
        newIncident.put("dateTimeReported", new Timestamp(date.getTime()).toString());
        newIncident.put("type", "Fire Fighting");
        newIncident.put("operatorUsername", "wanqing");

        System.out.println( obj.addIncident(newIncident.toJSONString()));
	}
	
	public static void addIncidentType( RmiServerIntf obj) throws Exception
	{
	      JSONObject newIncidentType = new JSONObject();
        newIncidentType.put("typeName", "Test Incident Type");
        newIncidentType.put("defaultLevel", "5");
        newIncidentType.put("agentTypeId", "1");

        obj.addIncidentType(newIncidentType.toJSONString());
	
	}
	
	public static void addDengueSpot(RmiServerIntf obj) throws Exception
	{
        JSONObject newDS = new JSONObject();
        newDS.put("address", "dengue spot");
        newDS.put("count", "12356");
        newDS.put("postal", "523457");
        obj.addDengueSpot(newDS.toJSONString());
	}
	public static void addHaze( RmiServerIntf obj) throws Exception
	{
		java.util.Date date= new java.util.Date();
		JSONObject newHaze = new JSONObject();
        newHaze.put("psi", "155");
        newHaze.put("dateTimeReported", new Timestamp(date.getTime()).toString());
        newHaze.put("hazeTypeId", "651");
        obj.addHaze(newHaze.toJSONString());
	
	}
	

	public static void addOperator(RmiServerIntf obj) throws Exception
	{
		JSONObject newOperator = new JSONObject();
        newOperator.put("username", "UserTest");
        newOperator.put("password", "PasswordTest");
        obj.addOperator(newOperator.toJSONString());
	
	}
	public static void addShelter(RmiServerIntf obj) throws Exception
	{
	
	     JSONObject newShelter = new JSONObject();
	     newShelter.put("address", "Test Shelter");
	     newShelter.put("contact", "4124788");
	     newShelter.put("postal", "523453");
	     obj.addShelter(newShelter.toJSONString());
	}
	
	public static void getAgency(RmiServerIntf obj) throws Exception
	{
	   int id;
       String address;
       String postal;
       String phone;
       int agentTypeId;
       JSONArray jsonArray =   (JSONArray) JSONValue.parse(obj.getAgency());
       for (int i = 0; i < jsonArray.size(); ++i) {
    	    JSONObject jsonObj = (JSONObject) jsonArray.get(i);
    	    id = (int) jsonObj.get("id");
    	    address = (String)jsonObj.get("address");
    	    postal = (String)jsonObj.get("postal");
    	    phone =  (String)jsonObj.get("phone");
    	    agentTypeId =  (int) jsonObj.get("agentType");
    	    System.out.println(id + " " + address + " " + postal + " " + phone + " " + agentTypeId);
    	    // ...
    	    
    	}
		
	}
	//CPU testing database
	 public static void main(String args[]) throws Exception {
	        RmiServerIntf obj = (RmiServerIntf)Naming.lookup("//192.168.137.5:1099/RmiServer");
	        if (System.getSecurityManager() == null)
	 	       System.setSecurityManager ( new RMISecurityManager() );
	  
	        //addAgency(obj);
	        //addAgentType(obj);
	        addIncident(obj);
	        //addIncidentType(obj);
	        //addDengueSpot(obj);
	        //addHaze(obj);
	        //addHazeType(obj);
	        //addOperator(obj);
	        //addShelter(obj);
	        //getAgency(obj);
	    }

}
