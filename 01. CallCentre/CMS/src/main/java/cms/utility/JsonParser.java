package cms.utility;
import java.sql.Timestamp;
import java.util.Date;

import cms.model.IncidentForm;
import cms.model.ResolvedForm;

import org.json.simple.JSONObject;

public class JsonParser{
    public static JSONObject parse(IncidentForm incidentForm) {
		JSONObject obj = new JSONObject();
		obj.put("callerName", incidentForm.getCallerName());
		obj.put("callerPhone", incidentForm.getCallerPhone());	
		obj.put("location", incidentForm.getAddress());
		obj.put("postal", incidentForm.getPostal());
		obj.put("description",incidentForm.getDescription());
		obj.put("type",incidentForm.getType());
		obj.put("level", incidentForm.getLevel());
		obj.put("operatorUsername", incidentForm.getOperatorUsername());
		obj.put("messageType", "eventMessage");
		obj.put("display", true);
		obj.put("dateTimeReported", new Timestamp(new Date().getTime()).toString());
		return obj;
    }
    
    public static JSONObject parse(ResolvedForm resolvedForm){
    	JSONObject obj = new JSONObject();
		obj.put("incidentId", resolvedForm.getIncidentId());
		obj.put("operatorUsername", resolvedForm.getOperatorUsername());
		obj.put("display", false);
		obj.put("messageType", "eventMessage");
		return obj;
    }
}