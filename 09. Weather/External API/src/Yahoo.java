import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.bind.JAXBException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;


public class Yahoo {
	public String request_url;
	public JSONObject xmlJSONObj;
	
	public Yahoo() {
		request_url = "http://weather.yahooapis.com/forecastrss?w=1062617&u=c";
	}
	
	public Yahoo(String url) {
		this.request_url = url;
	}
	public JSONObject getWeather() throws JAXBException, IOException {
		
		try {
			URL yahoo = new URL(request_url);
	        URLConnection yc = yahoo.openConnection();
	        BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                yc.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) 
	            sb.append(inputLine);
	        in.close();
			
//			FileInputStream input = new FileInputStream("resources/xml/sample.xml");
//			byte[] fileData = new byte[input.available()];
//			input.read(fileData);
//			input.close();
//			String xmlFile = new String(fileData, "UTF-8");
	        
            xmlJSONObj = XML.toJSONObject(sb.toString());
            xmlJSONObj.put("messageType", "eventMessage")
            .put("type", "weather")
    		.put("level", "1");
            
            ((JSONObject) ((JSONObject) ((JSONObject) xmlJSONObj.get("rss")).get("channel")).get("item")).remove("description");
            
            String jsonPrettyPrintString = xmlJSONObj.toString(4);
            System.out.println(jsonPrettyPrintString);
        } catch (JSONException je) {
//            System.out.println(je.toString());
        	je.printStackTrace();
            xmlJSONObj = null;
        } catch (FileNotFoundException fe) {
        	fe.printStackTrace();
//          System.out.println(je.toString());
          xmlJSONObj = null;
      }
		return xmlJSONObj;
	}
}
