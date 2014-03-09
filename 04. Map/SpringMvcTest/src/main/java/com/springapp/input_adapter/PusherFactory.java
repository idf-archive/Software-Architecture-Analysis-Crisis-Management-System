package com.springapp.input_adapter;

/**
 * “Measuring programming progress by lines of code is like measuring aircraft building progress by weight.”
 * - Bill Gates
 * User: Danyang
 * Date: 11/11/13
 * Time: 10:26 PM
 */
public class PusherFactory {
    public MapPusher getPusherByMessageType(String messageType) {
        MapPusher pusher;
        if (messageType.equals("weather"))
            pusher = new WeatherContainer();
        else if (messageType.equals("haze"))
            pusher = new HazeContainer();
        else
            pusher = new IncidentRecordsContainer();
        return pusher;
    }
}
