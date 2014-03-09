package safeguard;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class SafeGuardDAO implements DAO{

	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("safeguard");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();

	public void persist(Object entity) {
		// TODO Auto-generated method stub
		transaction.begin();
		em.persist(entity);
		transaction.commit();
		
	}
	
	public void remove(Object entity) {
		// TODO Auto-generated method stub
		transaction.begin();
		em.remove(entity);
		transaction.commit();
	}
	


	@Override
	public Object find(Class className, int id)
	{
		return  em.find(className, id);
	}
	
	public Object find(Class className, String id)
	{
		return  em.find(className, id);
	}
	

	
	
	public void addAgency(String jsonString)
	{
		try
		{
			JSONObject json = (JSONObject) JSONValue.parse(jsonString);
			String address = (String) json.get("address");
			String postal = (String) json.get("postal");
			String phone = (String) json.get("phone");
			int agentTypeId = Integer.parseInt((String) json.get("typeId"));
			Agenttype agentType = (Agenttype) findById("AgentType", agentTypeId); 
			Agency agency = new Agency(address, postal, phone, agentType);
			persist(agency);
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
			JSONObject json = (JSONObject) JSONValue.parse(jsonString);
			String typeName = (String) json.get("typeName");
			Agenttype tempAgentType = new Agenttype(typeName);
			persist(tempAgentType);
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
			JSONObject json = (JSONObject) JSONValue.parse(jsonString);
			String address = (String) json.get("address");
			Integer count = Integer.parseInt((String) json.get("count"));
			String postal = (String) json.get("postal");
			Denguespot ds = new Denguespot(address, count, postal);
			persist(ds);
		}
		catch(Exception e)
		{
			System.out.println("Error in processing JSON");
		}
	}
	
	//take note
	public void addHaze(String jsonString)
	{
		try
		{
			JSONObject json = (JSONObject) JSONValue.parse(jsonString);
			JSONArray hazeArray = (JSONArray) json.get("hazeArray");
			
			for(int i=0; i<hazeArray.size(); i++)
			{
				JSONObject jsonObj = (JSONObject) hazeArray.get(i);
				String psi = (String) jsonObj.get("psi");
				Timestamp dateTimeReported = Timestamp.valueOf((String) jsonObj.get("dateTimeReported"));
				String location = (String) jsonObj.get("location");
				String classification = (String) jsonObj.get("classification");
				Haze haze = new Haze(psi, dateTimeReported, location, classification);
				persist(haze);
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in processing JSON");
			e.printStackTrace();
		}
	}
	


	public String addIncident(String jsonString)
	{
		//System.out.println(jsonString);
		try
		{
			
			JSONObject json = (JSONObject) JSONValue.parse(jsonString);
			String type = (String) json.get("type");
			
			if(type.equals("haze"))
			{
				System.out.println("Adding Haze");
				addHaze(jsonString);		
				return null;
			}
			else if(type.equals("weather"))
			{
				return null;
			}
			else
			{
				String callerName = (String) json.get("callerName");
				String phone = (String) json.get("callerPhone");
				String address  = (String) json.get("location");
				String postal= (String) json.get("postal");
				String description = (String) json.get("description");
				String operator = (String) json.get("operatorUsername");
				boolean display = Boolean.parseBoolean(json.get("display").toString());
				int levelReported = Integer.parseInt((String) json.get("level"));
				Timestamp dateTimeReported = Timestamp.valueOf((String) json.get("dateTimeReported"));
				
				String incidentTypeName = (String) json.get("type");
				Incidenttype incidentType = (Incidenttype) findByName("IncidentType", incidentTypeName);				
				Incident incident = new Incident(callerName, phone, address, postal, description, levelReported, dateTimeReported, incidentType, display, operator);
				persist(incident);
				System.out.println("added incident");
				//Incident latestIncident = (Incident) em.createQuery("SELECT a from Incident a ORDER BY a.datetimereported DESC",Incident.class).setMaxResults(1).getSingleResult();
				//System.out.println("DAO id: " + latestIncident.getIncidentid());
				
				//return latestIncident.getIncidentid().toString();
				return incident.getIncidentid().toString();
			}
			/*
			//return json object for SMS Server
			JSONObject jsonReturn = new JSONObject();
			jsonReturn.put("message", description);
			jsonReturn.put("postal", postal);
			return jsonReturn.toJSONString();
			*/

		}
		catch(Exception e)
		{
			System.out.println(jsonString);
			System.out.println("Error in Adding Incident");
			e.printStackTrace();
			return null;
		}
	}

	public void addIncidentType(String jsonString)
	{
		try
		{
			JSONObject json = (JSONObject) JSONValue.parse(jsonString);
			String typeName = (String) json.get("typeName");
			int defaultLevel = Integer.parseInt((String) json.get("defaultLevel"));
			
			int agentTypeId = Integer.parseInt((String) json.get("agentTypeId"));
			Agenttype agentType = (Agenttype) findById("AgentType", agentTypeId);
			
			Incidenttype incidentType = new Incidenttype(typeName, defaultLevel, agentType);
			persist(incidentType);
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
			JSONObject json = (JSONObject) JSONValue.parse(jsonString);
			String username = (String) json.get("username");
			String password = (String) json.get("password");
			Operator oper = new Operator(username, password);
			persist(oper);
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
			JSONObject json = (JSONObject) JSONValue.parse(jsonString);
			String address = (String) json.get("address");
			String contact = (String) json.get("contact");
			String postal = (String) json.get("postal");
			Shelter shelter = new Shelter(address,contact, postal);
			persist(shelter);
		}
		catch(Exception e)
		{
			System.out.println("Error in processing JSON");
		}
	}
	
	
	
	public String getAgency()
	{
		
		List <Agency> agencyList = em.createQuery("SELECT a from Agency a", Agency.class).getResultList();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		for(Agency b : agencyList)
		{
			jsonObj.put("id", b.getAgencyid());
			jsonObj.put("address", b.getBranchaddress());
			jsonObj.put("phone", b.getPhone());
			jsonObj.put("postal", b.getBranchpostal());
			jsonObj.put("agentType", b.getAgenttype().getAgenttypeid());
			jsonArray.add(jsonObj);
			jsonObj = new JSONObject();
		
		}
		return jsonArray.toJSONString();
		
	}
	public String getAgentType()
	{
		List <Agenttype> agentTypeList = em.createQuery("SELECT a from Agenttype a", Agenttype.class).getResultList();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		for(Agenttype b : agentTypeList)
		{	
			jsonObj.put("id", b.getAgenttypeid());
			jsonObj.put("typeName", b.getTypename());
			jsonArray.add(jsonObj);
			jsonObj = new JSONObject();
		}
		return jsonArray.toJSONString();
		
	}
	public String getDengueSpot()
	{
		List <Denguespot> dengueSpotList = em.createQuery("SELECT a from Denguespot a", Denguespot.class).getResultList();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		for(Denguespot b : dengueSpotList)
		{
			jsonObj.put("id", b.getDenguespotid());
			jsonObj.put("address", b.getAddress());
			jsonObj.put("postal", b.getPostal());
			jsonObj.put("count", b.getCount());
			jsonArray.add(jsonObj);
			jsonObj = new JSONObject();
		
		}
		return jsonArray.toJSONString();
		
	}
	

	public String getHaze()
	{
		List <Haze> hazeList = em.createQuery("SELECT a from Haze a", Haze.class).getResultList();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		for(Haze b : hazeList)
		{
			jsonObj.put("id", b.getHazeid());
			jsonObj.put("psi", b.getPsi());
			jsonObj.put("dateTimeReported", b.getDatetimereported().toString());
			jsonObj.put("location", b.getLocation());
			jsonObj.put("classification", b.getClassification());
			jsonArray.add(jsonObj);
			jsonObj = new JSONObject();
		
		}
		return jsonArray.toJSONString();
		
	}

	
	public String getRecentTrend()
	{
		List <Incident> incidentList = em.createQuery("SELECT a from Incident a ORDER BY a.datetimereported DESC", Incident.class).setMaxResults(50).getResultList();
		List <Haze> hazeList = em.createQuery("SELECT a from Haze a ORDER BY a.datetimereported DESC", Haze.class).setMaxResults(50).getResultList();

		JSONObject jsonReturn = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		for(Incident b : incidentList)
		{
			jsonObj.put("id", b.getIncidentid());
			jsonObj.put("callerName", b.getCallername());
			jsonObj.put("location", b.getAddress());
			jsonObj.put("callerPhone", b.getPhone());
			jsonObj.put("postal", b.getPostal());
			jsonObj.put("dateTimeReported", b.getDatetimereported().toString());
			jsonObj.put("description", b.getDescription());
			jsonObj.put("level", b.getLevelreported().toString());
			jsonObj.put("display", b.getDisplay());
			jsonObj.put("incidentType", b.getIncidenttype().getIncidentTypeName());
			jsonArray.add(jsonObj);
			jsonObj = new JSONObject();
		
		}
		jsonReturn.put("Incident", jsonArray);
		
		jsonObj = new JSONObject();
		JSONArray hazeArray = new JSONArray();
		for(Haze b : hazeList)
		{
			jsonObj.put("id", b.getHazeid());
			jsonObj.put("psi", b.getPsi());
			jsonObj.put("dateTimeReported", b.getDatetimereported().toString());
			jsonObj.put("location", b.getLocation());
			jsonObj.put("classification", b.getClassification());
			hazeArray.add(jsonObj);
			jsonObj = new JSONObject();
		
		}
		jsonReturn.put("Haze", hazeArray);
		System.out.println(jsonReturn.toJSONString());
		return jsonReturn.toJSONString();
	}
	
	public String updateIncident(String jsonString)
	{
		try
		{
			JSONObject json = (JSONObject) JSONValue.parse(jsonString);
			JSONObject jsonObj = new JSONObject();
			int id = Integer.parseInt((String) json.get("id"));
			Incident tempIncident = (Incident) find(Incident.class, id);
			em.getTransaction().begin();
			tempIncident.setDisplay(false);
			em.getTransaction().commit();
			
			jsonObj.put("id", tempIncident.getIncidentid());
			jsonObj.put("callerName", tempIncident.getCallername());
			jsonObj.put("address", tempIncident.getAddress());
			jsonObj.put("phone", tempIncident.getPhone());
			jsonObj.put("postal", tempIncident.getPostal());
			jsonObj.put("dateTimeReported", tempIncident.getDatetimereported().toString());
			jsonObj.put("description", tempIncident.getDescription());
			jsonObj.put("levelReported", tempIncident.getLevelreported());
			jsonObj.put("display", tempIncident.getDisplay());
			jsonObj.put("incidentType", tempIncident.getIncidenttype().getIncidenttypeid());
			return jsonObj.toJSONString();

		}
		catch(Exception e)
		{
			System.out.println("Error in processing JSON");
		}
		return null;
		
		
	}

	public String getIncident()
	{
		List <Incident> incidentList = em.createQuery("SELECT a from Incident a", Incident.class).getResultList();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		for(Incident b : incidentList)
		{
			if(b.getDisplay())
			{
				jsonObj.put("id", b.getIncidentid());
				jsonObj.put("callerName", b.getCallername());
				jsonObj.put("location", b.getAddress());
				jsonObj.put("callerPhone", b.getPhone());
				jsonObj.put("postal", b.getPostal());
				jsonObj.put("dateTimeReported", b.getDatetimereported().toString());
				jsonObj.put("description", b.getDescription());
				jsonObj.put("level", b.getLevelreported());
				jsonObj.put("display", b.getDisplay());
				jsonObj.put("type", b.getIncidenttype().getIncidentTypeName());
				jsonArray.add(jsonObj);
				jsonObj = new JSONObject();
			}
		
		}
		System.out.println(jsonArray.toJSONString());
		return jsonArray.toJSONString();
		
	}
	public String getIncidentType()
	{
		List <Incidenttype> incidentTypeList = em.createQuery("SELECT a from Incidenttype a", Incidenttype.class).getResultList();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		for(Incidenttype b : incidentTypeList)
		{
			jsonObj.put("id", b.getIncidenttypeid());
			jsonObj.put("typeName", b.getIncidentTypeName());
			jsonObj.put("defaultEmergencyLevel", b.getDefaultEmergencyLevel());
			jsonObj.put("agentTypeId", b.getAgenttype().getAgenttypeid());
			jsonArray.add(jsonObj);
			jsonObj = new JSONObject();
		
		}
		return jsonArray.toJSONString();
		
	}
	public String getOperator()
	{
		List <Operator> operatorList = em.createQuery("SELECT a from Operator a", Operator.class).getResultList();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		for(Operator b : operatorList)
		{
			jsonObj.put("username", b.getUsername());
			jsonObj.put("password", b.getPassword());
			jsonArray.add(jsonObj);
			jsonObj = new JSONObject();		
		}
		return jsonArray.toJSONString();
		
	}
	public String getShelter()
	{
		List <Shelter> shelterList = em.createQuery("SELECT a from Shelter a", Shelter.class).getResultList();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		for(Shelter b : shelterList)
		{
			jsonObj.put("id", b.getShelterid());
			jsonObj.put("address", b.getAddress());
			jsonObj.put("contact", b.getContact());
			jsonObj.put("postal", b.getPostal());
			jsonArray.add(jsonObj);
			jsonObj = new JSONObject();
		
		}
		return jsonArray.toJSONString();
		
	}
	public String getNearestShelter(String jsonString)
	{
		JSONObject json = (JSONObject) JSONValue.parse(jsonString);
		
		String postalCode = (String) json.get("postal");
		if(postalCode == null)
			return null;
		try
		{
			String firstTwoPostalCode = postalCode.substring(0,2);
			List <Shelter> shelterList = em.createQuery("SELECT a from Shelter a where SUBSTRING(a.postal,1,2) = :postalInput", Shelter.class).setParameter("postalInput", firstTwoPostalCode).getResultList();
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			for(Shelter b : shelterList)
			{
				jsonObj.put("id", b.getShelterid());
				jsonObj.put("location", b.getAddress());
				jsonObj.put("contact", b.getContact());
				jsonObj.put("postal", b.getPostal());
				jsonArray.add(jsonObj);
				jsonObj = new JSONObject();
			
			}
			return jsonArray.toJSONString();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String getNearestAgency(String jsonString)
	{

		JSONObject json = (JSONObject) JSONValue.parse(jsonString);
		String postalCode = (String) json.get("postal");
		if(postalCode == null)
			return null;
		String firstTwoPostalCode = postalCode.substring(0,2);
		List <Agency> agencyList = em.createQuery("SELECT a from Agency a where SUBSTRING(a.branchpostal,1,2) = :postalInput", Agency.class).setParameter("postalInput", firstTwoPostalCode).getResultList();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		for(Agency b : agencyList)
		{
			jsonObj.put("id", b.getAgencyid());
			jsonObj.put("location", b.getBranchaddress());
			jsonObj.put("phone", b.getPhone());
			jsonObj.put("postal", b.getBranchpostal());
			jsonArray.add(jsonObj);
			jsonObj = new JSONObject();
		
		}
		return jsonArray.toJSONString();
		
	}
	
	
	public Object findByName(String classType, String typeName)
	{
		if(classType.equals("AgentType"))			
		{	
			Agenttype tempAgenttype = (Agenttype) em.createQuery("SELECT a from Agenttype a where a.typename  = :typeNameInput").setParameter("typeNameInput", typeName).getSingleResult();
			return tempAgenttype;
		}
		else if((classType.equals("IncidentType")))
		{
			
			Incidenttype incidentType = (Incidenttype)  em.createQuery("SELECT a from Incidenttype a where a.incidenttypename  = :typeNameInput").setParameter("typeNameInput", typeName).getSingleResult();
			return incidentType;
		}
		return null;
	
	
	}
	
	public Object findById(String classType, int id)
	{
		if(classType.equals("AgentType"))			
		{	
			Agenttype tempAgenttype = (Agenttype) em.find(Agenttype.class, id);
			return tempAgenttype;
		}
		else if((classType.equals("IncidentType")))
		{
			
			Incidenttype incidentType = (Incidenttype) em.find(Incidenttype.class,id);
			return incidentType;
		}
		return null;
	}



	
}
