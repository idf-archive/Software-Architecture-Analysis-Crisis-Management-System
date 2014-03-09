package com.springapp.input_adapter;

import com.springapp.model.Weather;
import org.json.simple.JSONObject;

/**
 *  “Measuring programming progress by lines of code is like measuring aircraft building progress by weight.”
 * - Bill Gates
 * User: Danyang
 * Date: 11/9/13
 * Time: 6:28 PM
 */
public class WeatherContainer implements MapPusher{
    private static Weather weather;
    public static Weather getInstance() {
        if (weather==null)
            weather = new Weather();
        return weather;
    }

    @Override
    public void push(JSONObject jsonObject) {
        new WeatherParser().parseJsonList(jsonObject);
    }
}
