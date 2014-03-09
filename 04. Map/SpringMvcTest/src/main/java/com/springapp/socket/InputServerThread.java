package com.springapp.socket;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.springapp.input_adapter.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

//import safeguard.cms.primitive.IncomeEvent;
//import safeguard.cms.primitive.MapMessage;
//import safeguard.cms.stream.MapPoster;


public class InputServerThread extends Thread {

	private Socket socket = null;
	private ObjectInput input;

    // Constructor
	public InputServerThread(Socket socket) {
		super("InputServerThread");
		this.socket = socket;
		try {
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    @Override
	public void run() {
		try {
            String jsonString = (String) input.readObject(); // communication by string
            JSONObject obj = (JSONObject) JSONValue.parse(jsonString);
            System.out.println(obj.get("type").toString()+" Message received");

            // input
            // duplicated code, possible to have a factor
            MapPusher pusher = new PusherFactory().getPusherByMessageType(obj.get("type").toString());
            pusher.push(obj);
            // end input

//            // display received message
//            for(IncidentRecord element: IncidentRecordsContainer.getIncidentRecordHashSet()){
//                System.out.println(element);
//            }

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
