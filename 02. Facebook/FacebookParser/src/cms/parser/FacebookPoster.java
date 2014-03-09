package cms.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

public class FacebookPoster {


	
	public FacebookPoster() {
		super();
	}
	
	public void send(JSONObject msg)  {
		try {
			
			//String title = (String) msg.get("title");
			String type = (String) msg.get("type");
			String description = (String) msg.get("description");
			//String details = (String) msg.get("details");
			String location = (String) msg.get("location");
		
			JSONArray shelterList = (JSONArray) JSONValue.parse((String)msg.get("shelter"));
			String shelter = "\nNearest Shelters: \n";
			for(int i=0; i<shelterList.size(); i++)
			{
				JSONObject newShelter = (JSONObject) shelterList.get(i);
				shelter = shelter + "Shelter " + (i+1) ; 
				shelter = shelter + "\nLocation: " + newShelter.get("location") + " ";
				shelter = shelter + "\nPostal: " + newShelter.get("postal") + " ";
				shelter = shelter + "\nContact: " + newShelter.get("contact") + " ";
				shelter = shelter + "\n";

			}
			
			FacebookClient facebookClient = new DefaultFacebookClient("CAAKWtNZAOW5cBAKa6NC1hAyWGoSSi0XWU7Dyib0XElnRQ6L9NEtwtIEtkmHm4vo7wqFF7In6XxwWeEK4UVKfHXiZBTnMTk47O6Lo639ZA9xcJ76GYaZCCMOAeEIDATsoeT5A8IJr0btmLrDBYwz4OUghG0enBKbhiwK45MvAi9cQMqdiOVIjcZBnDfSaZBRlnWLdrWh21tOwZDZD");
			FacebookType returnResult = facebookClient.publish("sgnotification/feed", FacebookType.class, Parameter.with("Message", "New Event Coming.\nType : "+type+"\n"+description+"\n"+location + shelter));
			System.out.println(returnResult.toString());
		
		} catch (Exception e) {
			//e.printStackTrace();
		}
	} 

}
