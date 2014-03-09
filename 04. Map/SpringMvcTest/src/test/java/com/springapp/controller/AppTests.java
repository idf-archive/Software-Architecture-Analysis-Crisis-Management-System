package com.springapp.controller;

import com.springapp.input_adapter.IncidentRecordsContainer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.GregorianCalendar;

import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class AppTests {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    private JSONObject getJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("location", "LT2A NTU"); // if not specified, null pointer exception
        obj.put("description", "This is description");
        obj.put("level", 1);
        obj.put("type", "Fire Outbreak");
        obj.put("dateTimeReported", "2013-11-09 15:37:03.012");
        obj.put("display", true);
        return obj;


    }

    @Test
    public void serverSimple() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("main_map")); // the name return by the controller method
    }


    @Test
    public void incidentParsing() throws Exception {
        IncidentRecordsContainer incidentRecordsHolder = new IncidentRecordsContainer();
        JSONObject obj = this.getJsonObject();
        incidentRecordsHolder.push(obj);
        mockMvc.perform(get("/")).andReturn();

    }


}
