import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBException;

import org.json.JSONObject;


public class WeatherParser implements Parser{
	Socket socket;
	Yahoo yahoo;
	JSONObject yahooWeatherJSON;
	public boolean success = false;
	
	
	public void getData() {
		setYahooWeather();
	}
	
	public void pushData(){
		try {
			socket = new Socket(targetAddr,targetPort);
			ObjectOutput output = new ObjectOutputStream(socket.getOutputStream());

			//test
			/*JSONObject testJson = new JSONObject("{\"rss\":{\"xmlns:yweather\":\"http://xml.weather.yahoo.com/ns/rss/1.0\"}}");
		System.out.println(testJson.toString());
		output.writeObject(testJson.toString());*/

			output.writeObject(yahooWeatherJSON.toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setYahooWeather(){
		if (yahoo == null)
			yahoo = new Yahoo();
		try {

			yahooWeatherJSON = yahoo.getWeather();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (yahooWeatherJSON!=null){
			//System.out.println(yahooWeatherJSON.toString());
			success = true;
		}
	}
	
	public boolean isSuccessful(){
		return success;
	}
	
}
