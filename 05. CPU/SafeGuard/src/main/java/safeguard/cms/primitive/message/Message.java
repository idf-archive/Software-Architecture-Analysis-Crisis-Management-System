package safeguard.cms.primitive.message;

import safeguard.cms.Scheduler;

public abstract class Message {
	
	protected String description;
	
	
	public String getDescription() {
		return description;
	}
	
	public abstract void parseMessage(String str);       // convert information in jsonstring to itself
	public abstract String parseToJason();               // convert its information to a jsonstring
	public abstract void schedule(Scheduler scheduler);  // to tell scheduler where it goes and what it does
}
