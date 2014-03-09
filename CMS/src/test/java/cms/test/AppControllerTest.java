package cms.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cms.init.BaseTestConfig;
import cms.model.IncidentForm;
import cms.model.IncidentFormBuilder;
import cms.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=BaseTestConfig.class)
public class AppControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;


	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.build();
	}

	@Test
	public void testDisplayLogin() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("login"));
	}
	
	@Test 
	public void testConfirmIncident() throws Exception {
        
		String callerPhone = new String("8539");
		String postal = new String ("abc");
		
		IncidentForm formObject = new IncidentFormBuilder()
			.callerPhone(callerPhone)
			.postal(postal)
			.build();
		
		mockMvc.perform(post("/incident")
				.param("_go","GO")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(TestUtil.convertObjectToFormUrlEncodedBytes(formObject))
                .sessionAttr("incidentForm", formObject)
        ) 
        .andExpect(status().isOk())
        .andExpect(view().name("incident"))
        .andExpect(forwardedUrl("/WEB-INF/views/incident.jsp"))
        .andExpect(model().attributeHasFieldErrors("incidentForm", "callerPhone"))
        .andExpect(model().attributeHasFieldErrors("incidentForm", "postal"))
        .andExpect(model().attribute("incidentForm", hasProperty("callerPhone", is(callerPhone))))
        .andExpect(model().attribute("incidentForm", hasProperty("postal", is(postal))));
	}

}