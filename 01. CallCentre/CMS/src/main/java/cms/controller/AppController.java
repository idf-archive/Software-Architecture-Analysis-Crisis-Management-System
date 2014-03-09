package cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import cms.model.IncidentForm;
import cms.model.ResolvedForm;
import cms.socket.KnockKnockClient;
import cms.utility.JsonParser;
 
@Controller
//@SessionAttributes("incidentForm")
public class AppController {
    
    @RequestMapping(method=RequestMethod.GET, value = {"/","/login"})
    public String displayLogin(Model model) {
        return "login";
    }
     
    @RequestMapping(value="/login", params="errorLogin")
    public String displayFailedLogin(Model model){
    	// Adding an attribute to flag that an error happened at login
    	model.addAttribute("loginFailed", true); 
    	return "login";
    }
    
    
    @RequestMapping(value="/incident")
    public String newIncidentForm(Model model, @ModelAttribute("incidentForm") IncidentForm incidentForm,
    		@ModelAttribute("resolvedForm") ResolvedForm resolvedForm){
    	model.addAttribute("username",getCurrentUsername());
    	model.addAttribute("incidentForm", new IncidentForm()); 
    	model.addAttribute("resolvedForm", new ResolvedForm()); 
    	return "incident";
    }
    
    @RequestMapping(value = "/incident", method = RequestMethod.POST, params="_reset")
    public String resetIncidentForm(Model model, HttpServletRequest request,
    					@ModelAttribute("incidentForm") @Valid IncidentForm incidentForm,
    					BindingResult bindingResult) {
    	model.addAttribute("username",getCurrentUsername());
    	model.addAttribute("incidentForm", new IncidentForm()); 
    	return "incident";
	}
    
    @RequestMapping(value = "/resolve", method = RequestMethod.POST, params="_reset")
    public String resetResolvedForm(Model model, HttpServletRequest request,
    					@ModelAttribute("resolvedForm") @Valid ResolvedForm resolvedForm,
    					BindingResult bindingResult) {
    	model.addAttribute("username",getCurrentUsername());
    	model.addAttribute("resolvedForm", new ResolvedForm()); 
    	return "incident";
	}
    
    @RequestMapping(value = "/incident", method = RequestMethod.POST, params="_go")
    public String confirmIncident(Model model, HttpServletRequest request,
    					@ModelAttribute("incidentForm") @Valid IncidentForm incidentForm, 
    					BindingResult bindingResult) {
    	model.addAttribute("username",getCurrentUsername());
    	if (bindingResult.hasErrors()){ 
        	model.addAttribute("incidentForm", incidentForm); 
    		return "incident";
    	}        		
    	model.addAttribute("incidentForm", incidentForm); 
		return "confirmation";
	}
    
    @RequestMapping(value = "/incident", method = RequestMethod.POST, params="_back")
    public String goBackToIncidentForm(Model model, HttpServletRequest request,
    					@ModelAttribute("incidentForm") IncidentForm incidentForm) {
    	model.addAttribute("username",getCurrentUsername());
    	model.addAttribute("incidentForm", incidentForm); 
		return "incident";     		
	}    
	
    @RequestMapping(value = "/incident", method = RequestMethod.POST, params="_create")
    public String createIncident(Model model, HttpServletRequest request,
    					@ModelAttribute("incidentForm") IncidentForm incidentForm) {
    
    	try {	
    		incidentForm.setOperatorUsername(getCurrentUsername());
    		JSONObject incident = JsonParser.parse(incidentForm);
    		KnockKnockClient.send(System.getProperty("server"),Integer.parseInt(System.getProperty("port")),incident.toJSONString());   
		} catch (Exception e) {
			model.addAttribute("status", e.toString());
			model.addAttribute(e);
		} 
    	model.addAttribute("username",getCurrentUsername());
    	model.addAttribute("incidentForm", new IncidentForm());
    	model.addAttribute("message", "Incident added successfully.");
    	return "incident";
	}        
    

    @RequestMapping(value = "/resolve", method = RequestMethod.POST, params="_go")
    public String resolveIncident(Model model, HttpServletRequest request,
    					@ModelAttribute("resolvedForm") @Valid ResolvedForm resolvedForm,
    					BindingResult bindingResult) {
        if (bindingResult.hasErrors()){ 
        	model.addAttribute("username",getCurrentUsername());
            model.addAttribute("resolvedForm", resolvedForm); 
        } else {
        	try {	
        		resolvedForm.setOperatorUsername(getCurrentUsername());
        		KnockKnockClient.send(System.getProperty("server"),Integer.parseInt(System.getProperty("port")),JsonParser.parse(resolvedForm).toJSONString());   
    		} catch (Exception e) {
    			model.addAttribute("status", e.toString());
    			model.addAttribute(e);
    		} 
        	model.addAttribute("username",getCurrentUsername());
        	model.addAttribute("resolvedForm", new ResolvedForm());
        	model.addAttribute("message", "Incident solved successfully.");
        }
    	return "incident";
	}   
    
    private String getCurrentUsername(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); 
    }

}