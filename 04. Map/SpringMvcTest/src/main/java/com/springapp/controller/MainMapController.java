/**
 *  “Measuring programming progress by lines of code is like measuring aircraft building progress by weight.”
 * - Bill Gates
 * User: Danyang
 * Date: 11/9/13
 * Time: 6:59 PM
 */
package com.springapp.controller;

import com.springapp.input_adapter.HazeContainer;
import com.springapp.input_adapter.IncidentRecordsContainer;
import com.springapp.input_adapter.WeatherContainer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.springapp.model.*;

import java.util.*;

@Controller
@RequestMapping({"/", "/welcome"}) // map the url to the view function
public class MainMapController {
	@RequestMapping(method = RequestMethod.GET)
	public String displayIncidents(ModelMap model) {
        HashSet<IncidentRecord> incidentRecords = IncidentRecordsContainer.getIncidentRecordHashSet();
        Weather weather = WeatherContainer.getInstance();
        HashMap<String, Haze> hazeMap =  HazeContainer.getHashMap();

        List<IncidentRecord> incidentRecordList = new ArrayList<IncidentRecord>();
        incidentRecordList.addAll(incidentRecords);

        Collections.sort(incidentRecordList);
        Collections.reverse(incidentRecordList);

        model.addAttribute("weather", weather);
        model.addAttribute("hazeMap", hazeMap);
        model.addAttribute("records", incidentRecordList);
        model.addAttribute("typeNames", this.getTypeNames(incidentRecords));
        //debug
        for(IncidentRecord incidentRecord: incidentRecords){
            System.out.println(incidentRecord.toString());
        }
        return "main_map"; // the template jsp
	}

    private ArrayList<String> getTypeNames(HashSet<IncidentRecord> incidentRecords) {
        HashSet<String> typeNamesTemp = new HashSet<String>();
        for(IncidentRecord record : incidentRecords){
            typeNamesTemp.add(record.getTypeName());
        }
        ArrayList<String> typeNames = new ArrayList<String>();
        for(String str : typeNamesTemp){
            typeNames.add(str);
        }
        return typeNames;
    }

}