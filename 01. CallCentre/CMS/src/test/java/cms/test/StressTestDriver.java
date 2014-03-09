package cms.test;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;

import cms.model.IncidentForm;
import cms.model.IncidentFormBuilder;
import cms.socket.KnockKnockClient;
import cms.utility.JsonParser;

public class StressTestDriver implements Runnable{

	int threadNo;
	static int totalThreadNumber = 1;
	int totalRequestNumber = 1;
	
	public StressTestDriver(int i){
		threadNo = i;
	}
	
	public void run(){
		//access get all incidents from database for 1000 times
		long threadStartTime = System.nanoTime();
		long threadEndTime;
		long duration;
		int failure = 0;
		
		for(int i=0; i<totalRequestNumber; i++){
			// Send
			IncidentForm incidentForm = new IncidentFormBuilder()
				.callerName("John Doe")
				.callerPhone("85390492")
				.address("32 Nanyang Crescent")
				.postal("639798")
				.description("This girl is on fire!")
				.level("1")
				.type("Gas Leak Control")
				.operatorUsername("wanqing")
				.build();
			
	   		JSONObject incident = JsonParser.parse(incidentForm);
	   		System.out.println(incident.toJSONString());
    		try {
				KnockKnockClient.send("192.168.1.102",5000,incident.toJSONString());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		threadEndTime = System.nanoTime();
		duration = TimeUnit.NANOSECONDS.toMillis(threadEndTime-threadStartTime);
		System.out.println("======== Thread " + threadNo +" is done in "+ duration +" miliseconds with failure of "+ failure+" times =======");
	}

	
	public static void main(String args[]) {
		for(int i=0; i<totalThreadNumber; i++){
			System.out.println("Thread: " + i);
			//create new thread and start
			new Thread(new StressTestDriver(i)).start();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

}
