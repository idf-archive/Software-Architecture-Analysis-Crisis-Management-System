package safeguard.cms.primitive.message;

import org.json.simple.JSONObject;

import safeguard.cms.Scheduler;

public class ReportMessage extends Message {
	String jsonString;

	@Override
	public void parseMessage(String str) {
		// TODO Auto-generated method stub
		jsonString = str;
	}

	@Override
	public String parseToJason() {
		// TODO Auto-generated method stub
		return jsonString;
	}

	@Override
	public void schedule(Scheduler scheduler) {
		scheduler.sendMessage2Distributer(this);	
	}

}
