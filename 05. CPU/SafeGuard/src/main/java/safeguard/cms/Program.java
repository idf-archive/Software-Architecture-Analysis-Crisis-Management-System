package safeguard.cms;

import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import safeguard.Config;
import safeguard.cms.database.DatabaseConnector;
import safeguard.cms.server.ControlServerThread;
import safeguard.cms.server.InputServerThread;
import safeguard.cms.server.Server;
import safeguard.cms.stream.CriticalMessageStream;
import safeguard.cms.stream.NonCriticalMessageStream;
import safeguard.cms.stream.ReportStream;

public class Program {
	public static void main(String[] args) {
		/*JSONArray ary = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("TypeName", "Fire");
		obj.put("LevelReported",2);
		obj.put("DateTimeReported", "2013-02-13 15:15:15");
		ary.add(obj);
        //IncomeEvent event = new IncomeEvent();
        
        
        //event.parseToIncomeEvent(newIncident);
        //new DatabaseConnector().sendEvent(event);
		System.out.print(ary.toJSONString());*/
		
		Scheduler scheduler = new Scheduler();
		scheduler.startScheduler();
		Scanner sc = new Scanner(System.in);
		String command = "";
		while (!command.equals("q")) {
			command = sc.nextLine().trim();
			System.out.println("Command received "+ command);
		}
		System.out.println("Stop received");
		scheduler.stopScheduler();
		try {
			scheduler.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch blockq
			e.printStackTrace();
		}
		System.out.println("Program stopped");

	}
}
