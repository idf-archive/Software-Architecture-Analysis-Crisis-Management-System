/* Author: Teo Kok Hien
 * Last Edit: 4 November 2013
 * 
 * This class is a thread to call ReportParser.java each time there is an activity from the CPU
 */

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class InputServerThread extends Thread{
	private Socket socket = null;
	//private CenterController centerController;
	private ObjectInput input;
	public InputServerThread(Socket socket) {
		super("InputServerThread");
		//this.centerController = centeControllerr;
		this.socket = socket;
		try {
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {

		try {
			String str = (String) input.readObject();
			JSONObject jsonObject = (JSONObject) JSONValue.parse(str);
			
			ReportParser.createReport(jsonObject);
			
			System.out.println(str);
			System.out.println("Message received "+jsonObject.toString());
			/*
			IncomeEvent incomeEvent = new IncomeEvent();
			incomeEvent.parseToIncomeEvent(jsonObject);
			System.out.println(incomeEvent.getType());
			System.out.println(incomeEvent.getAddress());
			System.out.println(incomeEvent.getLevelReported());
			System.out.println(incomeEvent.getDateTimeReported().toString());
			centerController.receiveEvent(incomeEvent);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
