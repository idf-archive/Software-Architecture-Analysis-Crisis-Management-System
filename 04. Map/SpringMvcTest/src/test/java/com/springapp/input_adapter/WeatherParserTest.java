package com.springapp.input_adapter;

import com.springapp.model.Weather;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Danyang
 * Date: 11/9/13
 * Time: 7:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeatherParserTest {
    private JSONObject jsonObject;
    @Before
    public void setUp() throws Exception {
        String jsonString = " {\n" +
                "   \"level\": \"1\",\n" +
                "   \"rss\": {\n" +
                "       \"xmlns:yweather\": \"http://xml.weather.yahoo.com/ns/rss/1.0\",\n" +
                "       \"xmlns:geo\": \"http://www.w3.org/2003/01/geo/wgs84_pos#\",\n" +
                "       \"channel\": {\n" +
                "           \"link\": \"http://us.rd.yahoo.com/dailynews/rss/weather/Singapore__SG/*http://weather.yahoo.com/forecast/SNXX0006_c.html\",\n" +
                "           \"yweather:wind\": {\n" +
                "               \"speed\": 1.61,\n" +
                "               \"direction\": 90,\n" +
                "               \"chill\": 28\n" +
                "           },\n" +
                "           \"image\": {\n" +
                "               \"title\": \"Yahoo! Weather\",\n" +
                "               \"height\": 18,\n" +
                "               \"link\": \"http://weather.yahoo.com\",\n" +
                "               \"width\": 142,\n" +
                "               \"url\": \"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"\n" +
                "           },\n" +
                "           \"ttl\": 60,\n" +
                "           \"yweather:astronomy\": {\n" +
                "               \"sunset\": \"6:49 pm\",\n" +
                "               \"sunrise\": \"6:46 am\"\n" +
                "           },\n" +
                "           \"yweather:units\": {\n" +
                "               \"distance\": \"km\",\n" +
                "               \"pressure\": \"mb\",\n" +
                "               \"speed\": \"km/h\",\n" +
                "               \"temperature\": \"C\"\n" +
                "           },\n" +
                "           \"title\": \"Yahoo! Weather - Singapore, SG\",\n" +
                "           \"description\": \"Yahoo! Weather for Singapore, SG\",\n" +
                "           \"yweather:location\": {\n" +
                "               \"region\": \"\",\n" +
                "               \"country\": \"Singapore\",\n" +
                "               \"city\": \"Singapore\"\n" +
                "           },\n" +
                "           \"lastBuildDate\": \"Sat, 09 Nov 2013 6:00 pm SGT\",\n" +
                "           \"item\": {\n" +
                "               \"guid\": {\n" +
                "                   \"content\": \"SNXX0006_2013_11_13_7_00_SGT\",\n" +
                "                   \"isPermaLink\": false\n" +
                "               },\n" +
                "               \"pubDate\": \"Sat, 09 Nov 2013 6:00 pm SGT\",\n" +
                "               \"title\": \"Conditions for Singapore, SG at 6:00 pm SGT\",\n" +
                "               \"geo:long\": 103.85,\n" +
                "               \"yweather:forecast\": [\n" +
                "                   {\n" +
                "                       \"text\": \"Scattered Thunderstorms\",\n" +
                "                       \"code\": 47,\n" +
                "                       \"high\": 31,\n" +
                "                       \"day\": \"Sat\",\n" +
                "                       \"low\": 26,\n" +
                "                       \"date\": \"9 Nov 2013\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                       \"text\": \"Scattered Thunderstorms\",\n" +
                "                       \"code\": 38,\n" +
                "                       \"high\": 31,\n" +
                "                       \"day\": \"Sun\",\n" +
                "                       \"low\": 26,\n" +
                "                       \"date\": \"10 Nov 2013\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                       \"text\": \"Scattered Thunderstorms\",\n" +
                "                       \"code\": 38,\n" +
                "                       \"high\": 31,\n" +
                "                       \"day\": \"Mon\",\n" +
                "                       \"low\": 26,\n" +
                "                       \"date\": \"11 Nov 2013\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                       \"text\": \"Scattered Thunderstorms\",\n" +
                "                       \"code\": 38,\n" +
                "                       \"high\": 31,\n" +
                "                       \"day\": \"Tue\",\n" +
                "                       \"low\": 26,\n" +
                "                       \"date\": \"12 Nov 2013\"\n" +
                "                   },\n" +
                "                   {\n" +
                "                       \"text\": \"Scattered Thunderstorms\",\n" +
                "                       \"code\": 38,\n" +
                "                       \"high\": 31,\n" +
                "                       \"day\": \"Wed\",\n" +
                "                       \"low\": 25,\n" +
                "                       \"date\": \"13 Nov 2013\"\n" +
                "                   }\n" +
                "               ],\n" +
                "               \"link\": \"http://us.rd.yahoo.com/dailynews/rss/weather/Singapore__SG/*http://weather.yahoo.com/forecast/SNXX0006_c.html\",\n" +
                "               \"geo:lat\": 1.29,\n" +
                "               \"yweather:condition\": {\n" +
                "                   \"text\": \"Mostly Cloudy\",\n" +
                "                   \"temp\": 28,\n" +
                "                   \"code\": 28,\n" +
                "                   \"date\": \"Sat, 09 Nov 2013 6:00 pm SGT\"\n" +
                "               }\n" +
                "           },\n" +
                "           \"yweather:atmosphere\": {\n" +
                "               \"rising\": 0,\n" +
                "               \"humidity\": 89,\n" +
                "               \"pressure\": 982.05,\n" +
                "               \"visibility\": 7\n" +
                "           },\n" +
                "           \"language\": \"en-us\"\n" +
                "       },\n" +
                "       \"version\": 2\n" +
                "   },\n" +
                "   \"type\": \"weather\",\n" +
                "   \"messageType\": \"eventMessage\"\n" +
                "  }";
        jsonObject = (JSONObject) JSONValue.parse(jsonString);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testParseJsonList() throws Exception {
        new WeatherParser().parseJsonList(jsonObject);
        Weather weather = WeatherContainer.getInstance();
        String Location = "Singapore";
        System.out.println(weather.getLocation());
        System.out.println(weather.getSimpleWeather());
        System.out.println(weather.getAstronomy());
        System.out.println(weather.getWind());
        assert(weather.getSunrise().equals("6:46 am"));
        assert(weather.getSunset().equals("6:49 pm"));
        assert(weather.getLocation().equals(Location));


    }
}
