package cms.parser;

import org.json.simple.JSONObject;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterPoster {
	public void send(JSONObject msg)  {
		try {
			
			TwitterFactory factory = new TwitterFactory();
			AccessToken accessToken = loadAccessToken();
		    Twitter twitter = factory.getInstance();
		    twitter.setOAuthConsumer("8k7nuOkjaJ6O7Q7HzbudLA", "cnjZLeK7jalBFnUXXhzj14G2kC3AbGHDZ9qTJ8vJmhA");
		    twitter.setOAuthAccessToken(accessToken);
		    String type = (String) msg.get("type");
		    String description = (String) msg.get("description");
		    String location = (String) msg.get("location");
		    Status status = twitter.updateStatus("Type : "+type+"\n" + "Location: " + location +"\n" +"Description: " +  description+"\n");
		    //Status status = twitter.updateStatus("This is a test");
		    
		    System.out.println(status);
		} catch (Exception e) {
		}
	} 
	private AccessToken loadAccessToken(){
	    String token = "1940650357-IITVlkEQkeYPQPGlJlFqEMlUDnNA4YxaqYIoVC0";
	    String tokenSecret = "9mR24i75XZ5QYmYBilwWDXiujRVSKZrg7mRBpL1inA";// load from a persistent store
	    return new AccessToken(token, tokenSecret);
	  }
}
