package com.springapp.input_adapter;

import org.json.simple.JSONObject;

/**
 *  “Measuring programming progress by lines of code is like measuring aircraft building progress by weight.”
 * - Bill Gates
 * User: Danyang
 * Date: 11/9/13
 * Time: 7:10 PM
 */
public interface Parser {
    public void parseJsonList(JSONObject obj);
}
