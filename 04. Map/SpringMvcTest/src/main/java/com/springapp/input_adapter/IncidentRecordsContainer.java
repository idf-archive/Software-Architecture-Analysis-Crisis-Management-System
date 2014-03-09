package com.springapp.input_adapter;

import com.springapp.model.IncidentRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class IncidentRecordsContainer implements MapPusher{
    private static HashSet<IncidentRecord> incidentRecordHashSet = new HashSet<IncidentRecord>();

    @Override
    public void push(JSONObject jsonObject) {
        IncidentRecordsParser parser = new IncidentRecordsParser();
        parser.parseJsonList(jsonObject);
    }

    public static HashSet<IncidentRecord> getIncidentRecordHashSet() {
        return incidentRecordHashSet;
    }

    public static HashSet<IncidentRecord> getRecentIncidentRecordHashSet() {
        HashSet<IncidentRecord> recentIncidentRecordHashSet = new HashSet<IncidentRecord>();
        for(IncidentRecord element: IncidentRecordsContainer.incidentRecordHashSet) {
            Calendar TIMERANGE = GregorianCalendar.getInstance();
            TIMERANGE.add(GregorianCalendar.DAY_OF_YEAR, -2);
            boolean withinTimeRange = element.getTimestamp().after(TIMERANGE);
            if(withinTimeRange)
                recentIncidentRecordHashSet.add(element);
        }
        return recentIncidentRecordHashSet;
    }

}
