package com.springapp.model;

/**
 *  “Measuring programming progress by lines of code is like measuring aircraft building progress by weight.”
 * - Bill Gates
 * User: Danyang
 * Date: 11/9/13
 * Time: 6:23 PM
 */
public class Weather {
    private String location;
    private String atmosphere;
    private String astronomy;


    public class Wind {
        private String speed;
        private String direction;
        private String chill;

        public String getSpeed() {
            return speed;
        }

        public String getDirection() {
            return direction;
        }

        public String getChill() {
            return chill;
        }

        @Override
        public String toString() {
            return Formatter.toString(this);
        }
    }

    public class SimpleWeather {
        /*
         "text": "Scattered Thunderstorms",
                       "code": 47,
                       "high": 31,
                       "day": "Sat",
                       "low": 26,
                       "date": "9 Nov 2013"
         */
        String weatherType;
        String highTemperature;
        String lowTemperature;
        String timeStamp;

        public String getWeatherType() {
            return weatherType;
        }

        public String getHighTemperature() {
            return highTemperature;
        }

        public String getLowTemperature() {
            return lowTemperature;
        }

        public String getTimeStamp() {
            return timeStamp;
        }
    }

    private SimpleWeather simpleWeather = new SimpleWeather();
    private Wind wind = new Wind();

    public void setSimpleWeather(String weatherType, String highTemperature, String lowTemperature, String timeStamp){
        simpleWeather.weatherType = weatherType;
        simpleWeather.lowTemperature = lowTemperature;
        simpleWeather.highTemperature = highTemperature;
        simpleWeather.timeStamp = timeStamp;
    }

    public SimpleWeather getSimpleWeather() {
        return simpleWeather;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(String atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(String speed, String direction,  String chill) {
        this.wind.chill = chill;
        this.wind.direction = direction;
        this.wind.speed = speed;
    }

    public String getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(String astronomy) {
        this.astronomy = astronomy;
    }

    private String parseJsonString (String target, String attribute) {
        try {
            String[] lines =  target.split("\n");
            for(String line: lines) {
                if (line.contains(attribute)){
                    String debugg_temp = line.split(":", 2)[1]; //String.split(String regex, int limit)
                    return debugg_temp;
                }

            }
        }
        catch (NullPointerException e) { // target format not appropriate
            return "";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        return "";
    }
    public String getSunrise() {
        return this.parseJsonString(this.getAstronomy(), "sunrise");
    }
    public String getSunset() {
        return this.parseJsonString(this.getAstronomy(), "sunset");
    }
}
