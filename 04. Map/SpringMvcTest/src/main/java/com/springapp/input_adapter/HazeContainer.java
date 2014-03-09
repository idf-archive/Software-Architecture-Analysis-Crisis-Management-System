/**
 *  “Measuring programming progress by lines of code is like measuring aircraft building progress by weight.”
 * - Bill Gates
 * User: Danyang
 * Date: 11/9/13
 * Time: 6:59 PM
 */
package com.springapp.input_adapter;

import com.springapp.model.Haze;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class HazeContainer implements MapPusher {
    private static HashMap<String, Haze> hashMap;
    public static HashMap<String, Haze> getHashMap() {
        if (hashMap==null)
            hashMap = new HashMap<String, Haze>();
        return hashMap;
    }



    @Override
    public void push(JSONObject jsonObject) {
        new HazeParser().parseJsonList(jsonObject);
    }
}
