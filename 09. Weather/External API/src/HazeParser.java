import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;


public class HazeParser implements Parser {
	Socket socket;
	Haze haze;
	JSONObject hazeJSON;
	boolean success=false;
	
	public void getData() {
		setHaze();	
	}

	public void pushData() {
		try {
			socket = new Socket(targetAddr,targetPort);
			ObjectOutput output = new ObjectOutputStream(socket.getOutputStream());

			//test
			/*JSONObject testJson = new JSONObject("{\"rss\":{\"xmlns:yweather\":\"http://xml.weather.yahoo.com/ns/rss/1.0\"}}");
		System.out.println(testJson.toString());
		output.writeObject(testJson.toString());*/

			output.writeObject(hazeJSON.toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setHaze(){
		//System.out.println( new Haze().getPSI().toString());
		if (haze == null)
			haze = new Haze();
		
		hazeJSON = haze.getHazeBundle();
		
		String jsonPrettyPrintString = hazeJSON.toString(4);
		System.out.println(jsonPrettyPrintString);
//		System.out.println(hazeJSON.toString());
	}
	public boolean isSuccessful(){
		return success;
	}
	

}
