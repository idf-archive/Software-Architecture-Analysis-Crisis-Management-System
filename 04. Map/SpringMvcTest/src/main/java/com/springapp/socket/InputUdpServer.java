package com.springapp.socket;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import com.springapp.input_adapter.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class InputUdpServer extends Thread {
    private String targetPort;
    private String targetIP;
    @Override
    public void run() {
        boolean listening = true;
		try {
			MulticastSocket socket = new MulticastSocket(Integer.parseInt(this.targetPort));
			InetAddress address = InetAddress.getByName(this.targetIP);
			socket.joinGroup(address);

            System.out.println("Udp server is listening to "+address+" "+socket.getLocalPort());


            DatagramPacket packet;

            while(listening){
                try{
                    System.out.println("Listening");
                    byte[] buf = new byte[4096];
                    packet = new DatagramPacket(buf, buf.length);

                    socket.receive(packet);
                    String received = new String(packet.getData()).trim();
                    System.out.println("debugg: "+received);
                    JSONObject obj = (JSONObject) JSONValue.parse(received);
                    System.out.println(obj.toJSONString());
                    System.out.println(obj.get("type").toString()+" message received");

                    // input
                    MapPusher pusher = new PusherFactory().getPusherByMessageType(obj.get("type").toString());
                    pusher.push(obj);
                    // end input
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

            socket.leaveGroup(address);
            socket.close();
        }
            catch (Exception e) {
                e.printStackTrace();
        }
	}
    public void setTargetPort(String targetPort) {
        this.targetPort = targetPort;
    }

    public void setTargetIP(String targetIP) {
        this.targetIP = targetIP;
    }
}
