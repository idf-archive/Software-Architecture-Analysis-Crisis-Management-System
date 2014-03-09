/**
 *  “Measuring programming progress by lines of code is like measuring aircraft building progress by weight.”
 * - Bill Gates
 * User: Danyang
 * Date: 11/9/13
 * Time: 6:34 PM
 */
package com.springapp.input_adapter;

import com.springapp.model.IncidentRecord;
import org.json.simple.JSONObject;

import java.security.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;


public class IncidentRecordsParser implements Parser {
    @Override
    public void parseJsonList(JSONObject obj){
        HashSet<IncidentRecord> incidentRecordHashSet =  IncidentRecordsContainer.getIncidentRecordHashSet();
        // basics
        System.out.println("Passing Incidents: "+obj.toJSONString());
        IncidentRecord incidentRecord = new IncidentRecord();
        incidentRecord.setAddress(obj.get("location").toString());
        incidentRecord.setDescription(obj.get("description").toString());
        incidentRecord.setLevelReported(Integer.parseInt(obj.get("level").toString()));
        incidentRecord.setTypeName(obj.get("type").toString());

        //Calendar

        Calendar calendar1 = GregorianCalendar.getInstance();
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf((obj.get("dateTimeReported")).toString());
        calendar1.setTimeInMillis(timestamp.getTime());
        incidentRecord.setTimestamp(calendar1);

        // remove
//        System.out.print(obj.toJSONString());
        boolean display;
        try {
            String dis = obj.get("display").toString();
            System.out.println(dis);
            display = Boolean.parseBoolean(obj.get("display").toString());
        }
        catch (NullPointerException e){
            display = true;
        }

        if(display)
            incidentRecordHashSet.add(incidentRecord);
        else
            incidentRecordHashSet.remove(incidentRecord);
    }
}
