package com.springapp.input_adapter;

import com.springapp.model.Haze;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

/**
 *  “Measuring programming progress by lines of code is like measuring aircraft building progress by weight.”
 * - Bill Gates
 * User: Danyang
 * Date: 11/9/13
 * Time: 7:07 PM
 */
public class HazeParser implements Parser {

    @Override
    public void parseJsonList(JSONObject obj){
        HashMap<String, Haze> hazeMap = HazeContainer.getHashMap();
        /*
           "location": "North",
           "psi": "44",
           "classification": "Good",
           "DateTimeReported": "2013-11-09 15:37:03.012"
         */

        // basics
        System.out.println("Passing Haze: "+obj.toJSONString());
        JSONParser parser=new JSONParser();
        String str = obj.get("hazeArray").toString();
        try {
            Object hazes = parser.parse(str);
            JSONArray array = (JSONArray) hazes;
            for(Object current: array){
                JSONObject current_json = (JSONObject) current;
                Haze haze = new Haze();

                haze.setLocation(current_json.get("location").toString());
                haze.setPsi(current_json.get("psi").toString());
                haze.setClassification(current_json.get("classification").toString());
                haze.setTimestamp(current_json.get("dateTimeReported").toString());

                hazeMap.put(haze.getLocation(), haze);

            }



        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
