package safeguard.cms.primitive.message;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import safeguard.Config;
import safeguard.cms.Scheduler;
import safeguard.cms.database.DatabaseConnector;

public class EventMessage extends Message {
	protected int criticalLevel;
	JSONObject jsonObject;
	
	public int getCriticalLevel() {
		return criticalLevel;
	}

	public void setCriticalLevel(int criticalLevel) {
		this.criticalLevel = criticalLevel;
	}


	@Override
	public void parseMessage(String str) {
		jsonObject = (JSONObject) JSONValue.parse(str);
		if (jsonObject.containsKey("level"))
			criticalLevel = Integer.parseInt((String) jsonObject.get("level"));
		else  {
			criticalLevel = Config.DEFAULT_EMERGENCY_LEVEL;
			jsonObject.put("level", Config.DEFAULT_EMERGENCY_LEVEL);
		}
	}

	@Override
	public String parseToJason() {
		return jsonObject.toJSONString();
	}

	@Override
	public void schedule(Scheduler scheduler) {
		DatabaseConnector dbConnector = scheduler.getDBConnector();
		String id = dbConnector.sendIncident(this.parseToJason());				// send incident to database and get incident id
		jsonObject.put("id", id);
		//added display
		//jsonObject.put("display", true);
		String shelter = dbConnector.getNearestShelter(this.parseToJason());    // get nearest shelters to that incident from database 
		jsonObject.put("shelter", shelter);
		String agency = dbConnector.getNearestAgency(this.parseToJason());      // get relevant agencies
		System.out.println("agency " + agency);
		jsonObject.put("agency", agency);
		
		scheduler.sendMessage2Distributer(this);								// send to message distributer
		
	}

}
