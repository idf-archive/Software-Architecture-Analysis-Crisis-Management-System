package com.springapp.input_adapter;

import com.springapp.model.Weather;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *  “Measuring programming progress by lines of code is like measuring aircraft building progress by weight.”
 * - Bill Gates
 * User: Danyang
 * Date: 11/9/13
 * Time: 6:33 PM
 */
public class WeatherParser implements Parser {
    @Override
    public void parseJsonList(JSONObject current){
        Weather weather =  WeatherContainer.getInstance();
        // basics
        //incidentRecord.setAddress(current.get("Address").toString());
        //TODO AOP
        System.out.println("Passing Weather: "+current.toJSONString());
        JSONObject rss = (JSONObject) current.get("rss");
        JSONObject channel = (JSONObject) rss.get("channel");
        weather.setAstronomy(cleaning(channel.get("yweather:astronomy").toString()));
        JSONObject location = (JSONObject) channel.get("yweather:location");
        weather.setLocation(cleaning(location.get("city").toString()));
        weather.setAtmosphere(cleaning(channel.get("yweather:atmosphere").toString()));

        JSONObject wind_obj = (JSONObject) channel.get("yweather:wind");
        weather.setWind(wind_obj.get("speed").toString(), wind_obj.get("direction").toString(), wind_obj.get("chill").toString());

        JSONArray weatherArray = (JSONArray) ((JSONObject) channel.get("item")).get("yweather:forecast");
        JSONObject simpleWeather = (JSONObject) weatherArray.get(1);
        weather.setSimpleWeather(
                simpleWeather.get("text").toString(),
                simpleWeather.get("high").toString(),
                simpleWeather.get("low").toString(),
                simpleWeather.get("day").toString() +" "+ simpleWeather.get("date").toString()
                );
    }
    public String cleaning (String input) {
        String output =  input.replace(",", "\n");
        output = output.replace("\"", "");
        output = output.replace("{", "");
        output = output.replace("}", "");
        return output;
    }
}
