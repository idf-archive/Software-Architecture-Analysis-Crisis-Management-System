package safeguard;
import java.sql.Timestamp;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class run {
	private static SafeGuardDAO dao = new SafeGuardDAO();
	
	public static void main(String [ ] args) throws Exception 
	{
		/*
		Agency tempagency = new Agency();
		tempagency.setBranchaddress("91 Ubi Avenue 4");
		tempagency.setBranchpostal("408827");
		tempagency.setPhone("6848 3304");
		Agenttype tempType = (Agenttype) dao.findById("AgentType", 1); 
		System.out.println(tempType.toString());
		tempagency.setAgenttype(tempType);
		dao.persist(tempagency);
		*/
		/*
		List<Agency> a = dao.getAgency();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		for(Agency b : a)
		{
			jsonObj.put("id", b.getAgencyid());
			jsonObj.put("address", b.getBranchaddress());
			jsonObj.put("postal", b.getBranchpostal());
			jsonObj.put("agentType", b.getAgenttype().getAgenttypeid());
			jsonArray.add(jsonObj);
			jsonObj = new JSONObject();
		
		}
		*/
		/*
		JSONObject newJSON = new JSONObject();
        newJSON.put("address", "new agency test one");
        newJSON.put("phone", "412414124");
        newJSON.put("postal", "12567");
        newJSON.put("typeId", "1");
      
        String address = (String) newJSON.get("address");
		String postal = (String) newJSON.get("postal");
		String phone = (String) newJSON.get("phone");
		int agentTypeId = Integer.parseInt((String) newJSON.get("typeId"));
		Agenttype agentType = (Agenttype) dao.findById("AgentType", agentTypeId); 
		Agency agency = new Agency(address, postal, phone, agentType);
		dao.persist(agency);
        //obj.addAgency(newJSON);
		
		//JSONArray jsonA = new JSONArray(a.toString());
	
		//System.out.println(dao.getAgency());
		 */
		/*
		  JSONObject newIncidentType = new JSONObject();
	      newIncidentType.put("typeName", "Dangerous");
	      newIncidentType.put("defaultLevel", "5");
	      newIncidentType.put("AgentType", "1");
	    JSONObject json = (JSONObject) JSONValue.parse(newIncidentType.toJSONString());
		String typeName = (String) json.get("typeName");
		int defaultLevel = Integer.parseInt((String) json.get("defaultLevel"));
		
		int agentTypeId = Integer.parseInt((String) json.get("agentTypeId"));
		Agenttype agentType = (Agenttype) dao.findById("agentType", agentTypeId);
		
		Incidenttype incidentType = new Incidenttype(typeName, defaultLevel, agentType);
		dao.persist(incidentType);
		*/
	/*
	  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
 	   //get current date time with Date()
 	   Date date = new Date();
 	   Calendar cal = Calendar.getInstance();
 	   System.out.println(dateFormat.format(date));
		String dateTime = dateFormat.format(cal.getTime()).toString();
		*/
		/*
		 java.util.Date date= new java.util.Date();

        JSONObject newIncident = new JSONObject();
        newIncident.put("callerName", "Mr Tan");
        newIncident.put("address", "205 Nanyang Avenue");
        newIncident.put("postal", "128930");
        newIncident.put("phone", "514502");
        newIncident.put("description", "this is a trial run");
        newIncident.put("display", "true");
        newIncident.put("levelReported", "5");
        newIncident.put("dateTimeReported", new Timestamp(date.getTime()).toString());
        newIncident.put("incidentTypeId", "501");


        JSONObject json = (JSONObject) JSONValue.parse(newIncident.toJSONString());
		String callerName = (String) json.get("callerName");
		String phone = (String) json.get("phone");
		String address  = (String) json.get("address");
		String postal= (String) json.get("postal");
		String description = (String) json.get("description");
		Boolean display = Boolean.parseBoolean((String) json.get("display"));
		int levelReported = Integer.parseInt((String) json.get("levelReported"));
		Timestamp dateTimeReported = Timestamp.valueOf((String) json.get("dateTimeReported"));
		
		int incidentTypeId = Integer.parseInt((String) json.get("incidentTypeId"));
		Incidenttype incidentType = (Incidenttype) dao.findById("IncidentType", incidentTypeId);


		Incident incident = new Incident(callerName, phone, address, postal, description, levelReported, dateTimeReported, incidentType, display);
		dao.persist(incident);
		*/
		/*
		Agenttype a = (Agenttype) dao.findByName("AgentType", "test123");
		System.out.println(a.toString());
		
		//Hazetype h = (Hazetype) dao.findByName("HazeType", "test123");
		//System.out.println(h.toString());
		
		Incidenttype it = (Incidenttype) dao.findByName("IncidentType", "Dangerous");
		System.out.println(it.toString());
		*/
		
	}

}
