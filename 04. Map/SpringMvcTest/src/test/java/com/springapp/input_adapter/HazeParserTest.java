package com.springapp.input_adapter;

import com.springapp.model.Haze;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Danyang
 * Date: 11/9/13
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class HazeParserTest {
    private JSONObject jsonObject;
    @Before
    public void setUp() throws Exception {
        String jsonString = " {\n" +
                "   \"level\": \"1\",\n" +
                "   \"hazeArray\": [\n" +
                "       {\n" +
                "           \"location\": \"North\",\n" +
                "           \"psi\": \"44\",\n" +
                "           \"classification\": \"Good\",\n" +
                "           \"dateTimeReported\": \"2013-11-09 15:37:03.012\"\n" +
                "       },\n" +
                "       {\n" +
                "           \"location\": \"South\",\n" +
                "           \"psi\": \"42\",\n" +
                "           \"classification\": \"Good\",\n" +
                "           \"dateTimeReported\": \"2013-11-09 15:37:03.012\"\n" +
                "       },\n" +
                "       {\n" +
                "           \"location\": \"East\",\n" +
                "           \"psi\": \"36\",\n" +
                "           \"classification\": \"Good\",\n" +
                "           \"dateTimeReported\": \"2013-11-09 15:37:03.012\"\n" +
                "       },\n" +
                "       {\n" +
                "           \"location\": \"West\",\n" +
                "           \"psi\": \"33\",\n" +
                "           \"classification\": \"Good\",\n" +
                "           \"dateTimeReported\": \"2013-11-09 15:37:03.012\"\n" +
                "       },\n" +
                "       {\n" +
                "           \"location\": \"Central\",\n" +
                "           \"psi\": \"44\",\n" +
                "           \"classification\": \"Good\",\n" +
                "           \"dateTimeReported\": \"2013-11-09 15:37:03.012\"\n" +
                "       },\n" +
                "       {\n" +
                "           \"location\": \"Overall\",\n" +
                "           \"psi\": \"33-44\",\n" +
                "           \"classification\": \"Good\",\n" +
                "           \"dateTimeReported\": \"2013-11-09 15:37:03.012\"\n" +
                "       }\n" +
                "   ],\n" +
                "   \"type\": \"haze\",\n" +
                "   \"messageType\": \"eventMessage\"\n" +
                "   }";
        jsonObject = (JSONObject) JSONValue.parse(jsonString);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testParseJsonList() throws Exception {
        new HazeParser().parseJsonList(jsonObject);
        HashMap<String, Haze> hazeMap = HazeContainer.getHashMap();
        assert(hazeMap.get("North").getPsi().equals("44"));
        assert(hazeMap.get("South").getTimestamp().equals("2013-11-09 15:37:03.012"));
    }
}
