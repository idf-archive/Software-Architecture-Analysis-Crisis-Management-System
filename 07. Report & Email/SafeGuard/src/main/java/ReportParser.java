/* Author: Teo Kok Hien
 * Last Edit: 4 November 2013
 * 
 * This class parses the data from JSON format into an arraylist of bean classes in preparation for porting over to Jasper Reports
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;

public class ReportParser {

	public static int createReport(JSONObject jsonObject) throws Exception {
		//public static void main(String args[]) throws Exception {
		InputStream inputStream = new FileInputStream ("C:/Users/Owner/Documents/Eclipse Workspace/SafeGuard/src/main/java/reports/MinisterialReport.jrxml");
		
		//Create Incident Array
		ArrayList<Incident> incidentList = new ArrayList<Incident>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JsonFactory f = new MappingJsonFactory();
	    
	    //Create Haze Array
	    ArrayList<Haze> hazeList = new ArrayList<Haze>();
	    
		//Read in JSON
	    JsonParser jp = f.createJsonParser(new File("C:/Users/Owner/Desktop/JSON/JSONOutput2.json"));
	    //JsonParser jp = f.createJsonParser(jsonObject.toString());
	    JsonToken current = jp.nextToken();
		String type;
	    
	    if (current != JsonToken.START_OBJECT) {
	        System.out.println("Error, JSON should contain nested object.");
	        return -1;
	    }
	    
	    while (jp.nextToken() != JsonToken.END_OBJECT) {
			type = jp.getCurrentName();
			current = jp.nextToken();
			
			//Read in Incidents
			if(type.equals("Incident")){
				if (current == JsonToken.START_ARRAY) {
			    	while (jp.nextToken() != JsonToken.END_ARRAY) {
			    		JsonNode node = jp.readValueAsTree();
			    		incidentList.add(new Incident(node.get("incidentType").textValue(), node.get("level").textValue(), formatter.parse(node.get("dateTimeReported").textValue())));
						/*System.out.println("TypeName: " + incidentList.get(incidentList.size()-1).getTypeName());
						System.out.println("LevelReported: " + incidentList.get(incidentList.size()-1).getLevelReported());
						System.out.println("DateTimeReported: "+ incidentList.get(incidentList.size()-1).getDateTimeReported());*/
			    	}
			    }
			    else {
			    	System.out.println("Error: records should be an array: skipping.");
			    	jp.skipChildren();
				}
			}
			
			//Read in Haze
			else if(type.equals("Haze")){
				if (current == JsonToken.START_ARRAY) {
			    	while (jp.nextToken() != JsonToken.END_ARRAY) {
			    		JsonNode node = jp.readValueAsTree();
			    		hazeList.add(new Haze(node.get("classification").textValue(), node.get("psi").textValue().split("-")[0], formatter.parse(node.get("dateTimeReported").textValue()), node.get("location").textValue()));
			    	}
			    }
			    else {
			    	System.out.println("Error: records should be an array: skipping.");
			    	jp.skipChildren();
				}
			}
	    }
	    
	    //Sort incidentList according to levelReported
	    Collections.sort(incidentList, new Comparator<Incident>(){
	    	public int compare(Incident i1, Incident i2) {
	    		if(Integer.parseInt(i1.getLevelReported()) < Integer.parseInt(i2.getLevelReported()))
	    			return 1;
	    		else if (i1.getLevelReported().equals(i2.getLevelReported()))
	    			return 0;
	    		else return -1;
	    	}
	    });
	    
	    //Read in haze.json
	    /*jp = f.createJsonParser(new File("C:/Users/Owner/Desktop/Haze.json"));
	    current = jp.nextToken();
	    if (current == JsonToken.START_ARRAY) {
	    	while (jp.nextToken() != JsonToken.END_ARRAY) {
	    		JsonNode node = jp.readValueAsTree();
	    		hazeList.add(new Haze(node.get("TypeName").textValue(), node.get("PSI").textValue(), formatter.parse(node.get("DateTimeReported").textValue())));
				/*System.out.println("TypeName: " + hazeList.get(hazeList.size()-1).getTypeName());
				System.out.println("PSI: " + hazeList.get(hazeList.size()-1).getLevelReported());
				System.out.println("DateTimeReported: "+ hazeList.get(hazeList.size()-1).getDateTimeReported());*/
	    	/*}
	    }
	    else {
	    	System.out.println("Error: records should be an array: skipping.");
	    	jp.skipChildren();
		}*/
	    
	    //Sort hazeList according to PSI
	    Collections.sort(hazeList, new Comparator<Haze>(){
	    	public int compare(Haze h1, Haze h2) {
	    		if(Integer.parseInt(h1.getLevelReported()) < Integer.parseInt(h2.getLevelReported()))
	    			return 1;
	    		else if (h1.getLevelReported().equals(h2.getLevelReported()))
	    			return 0;
	    		else return -1;
	    	}
	    });
		
	    //Return top 10 highest level incidents and haze
	    incidentList = new ArrayList<Incident>(incidentList.subList(0, incidentList.size()>=10 ? 10 : incidentList.size()));
	    hazeList = new ArrayList<Haze>(hazeList.subList(0, hazeList.size()>=10 ? 10 : hazeList.size()));
	    
	    //Package both incidents and haze into a single source for JRXML
	    ArrayList mergedList = incidentList;
	    mergedList.addAll(hazeList);
	    
	    //Port mergedList into JasperObject array
	    ArrayList<JasperObject> fullList = new ArrayList<JasperObject>();
	    Iterator<Incident> it = incidentList.iterator();
	    while(it.hasNext())
	    {
	        Object obj = it.next();
	        if(obj instanceof Incident){
	        	Incident incident = (Incident)obj;
	        	fullList.add(new JasperObject(incident.getTypeName(), incident.getLevelReported(), incident.getDateTimeReported(), ""));
	        }
	        else if(obj instanceof Haze){
	        	Haze haze = (Haze)obj;
	        	fullList.add(new JasperObject(haze.getTypeName(), haze.getLevelReported(), haze.getDateTimeReported(), haze.getLocation()));
	        }
	        //Can add different emergency situations here...
	    }
		
		//Create Report
	    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(fullList);
	    Map parameters = new HashMap();
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/Owner/Desktop/MinisterialReport.pdf");
		
		//Send Email
		int emailResponse = -1;
		do{
			emailResponse = EmailManager.sendEmail();
		}
		while(emailResponse == -1);
		
		return 1;
	}
}
