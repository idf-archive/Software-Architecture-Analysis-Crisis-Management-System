package com.springapp.socket;

import com.springapp.input_adapter.MapPusher;
import com.springapp.input_adapter.PusherFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread extends Thread {

    public void setTargetPort(int targetPort) {
        this.targetPort = targetPort;
    }

    public void setTargetIP(String targetIP) {
        this.targetIP = targetIP;
    }

    private Object msg;
    private InetAddress targetAddr;


    private int targetPort;
    private String targetIP;

    public ClientThread() throws UnknownHostException {
        this.msg = this.getDefaultMsg();
    }

    public ClientThread(Object msg) throws UnknownHostException {
        this();
        this.msg = msg;
    }

    private Object getDefaultMsg() {
        JSONObject obj = new JSONObject();
        obj.put("messageType", "controlMessage");
        obj.put("incidents", new Boolean(true));
        try {
            obj.put("ipAddress",  InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return (Object) obj.toJSONString();
    }

	@Override
	public void run() {
		try {
            targetAddr = InetAddress.getByName(this.targetIP);
			Socket socket = new Socket(targetAddr, targetPort);
            System.out.println("Client Thread Connecting to "+targetAddr+":"+targetPort);
			ObjectOutput output = new ObjectOutputStream(socket.getOutputStream()); //output
			output.writeObject(msg);

            ObjectInput input = new ObjectInputStream(socket.getInputStream()); // receive
            // get incidents initial
            String json_str = (String) input.readObject();
            System.out.println("Client Thread Received: "+ json_str);
            JSONArray jsonArray = (JSONArray) JSONValue.parse(json_str);
            for (Object jsonObj : jsonArray) {
                JSONObject jsonObject = (JSONObject) jsonObj;
                String type = jsonObject.get("type").toString();
                MapPusher pusher = new PusherFactory().getPusherByMessageType(type);
                //pusher.push(jsonObject);
                System.out.println(jsonObject.toJSONString());
            }


		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
