import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Haze {
	String[][] PSI = new String[6][24];
//	String[][] PM25 = new String[6][24];
//	String[][] ThreeHour_PSI = new String[2][12];
	JSONObject hazeJSON;
	
	private String request_url = "http://www.haze.gov.sg/haze-update/past-24-hour-psi-reading.aspx";
	//"http://app2.nea.gov.sg/anti-pollution-radiation-protection/air-pollution/psi/psi-readings-over-the-last-24-hours"
	private Document doc;

	public Haze(){
		
		try {
			doc = Jsoup.connect(request_url).get();
			parsePage();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parsePage(){
		Elements newsHeadlines = doc.select("td").removeAttr("align").removeAttr("width");
		
		for (int i = 0; i<13; i++) {
			newsHeadlines.remove(0);
			newsHeadlines.remove(13*6+12-i);
		}
		
		for (int i = 0; i<13; i++) {
			newsHeadlines.remove(13*12);
			newsHeadlines.remove(13*18+12-i);
		}
		
		/*for (int i = 0; i<13; i++) {
			newsHeadlines.remove(13*24);
			newsHeadlines.remove(13*26-1-i);
		}*/
		//System.out.println(newsHeadlines.toString());
		/*
		for (int i = 0; i<8; i++) {
			newsHeadlines.remove(13*26);
		}
		*/
		Pattern pattern, pattern_special;
		Matcher matcher, matcher_special;
		pattern = Pattern.compile("(> )(.*)( <)");
		pattern_special = Pattern.compile("(px;\">)(.*)(</strong>)");
		
		//parse psi 
		for (int i=0; i<6; i++){
			newsHeadlines.remove(0);
			for (int j=0; j<24; j++){
				Boolean special_flag=false;
				String target = newsHeadlines.first().toString();
				newsHeadlines.remove(0);
				matcher_special = pattern_special.matcher(target);
				//match Bold figure
				while (matcher_special.find()){
					int a,b;
					if (i<3 && j<12){
						a=i*2; b=j;
					} else if (i<3 && j>=12){
						a=i*2+1; b=j-12;
					} else if (i>=3 && j<12){
						a=i*2-6; b=j+12;
					} else {
						a=i*2-5; b=j;
					}
					PSI[a][b] = (matcher_special.group().substring(5, (matcher_special.group().length()-9)).toString());
//					System.out.print(PSI[i][j]+" ");
					special_flag = true;
					if (j==11) {
						newsHeadlines.remove(0);
					}
				}
				
				if (special_flag) continue;
				matcher = pattern.matcher(target);
				while (matcher.find()){
					int a,b;
					if (i<3 && j<12){
						a=i*2; b=j;
					} else if (i<3 && j>=12){
						a=i*2+1; b=j-12;
					} else if (i>=3 && j<12){
						a=i*2-6; b=j+12;
					} else {
						a=i*2-5; b=j;
					}
					PSI[a][b] = (matcher.group().substring(1, (matcher.group().length()-1)).toString());
//					System.out.print(PSI[i][j]+" ");
				}
				if (j==11) {
					newsHeadlines.remove(0);
				}
			}
//			System.out.println();
		}
		/*for(int i =0; i<6;i++){
			for (int j=0;j<24;j++){
				System.out.print(PSI[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		*/
		
		/*
		//parse pm2.5
		for (int i=0; i<6; i++){
			newsHeadlines.remove(0);
			for (int j=0; j<24; j++){
				Boolean special_flag=false;
				String target = newsHeadlines.first().toString();
				newsHeadlines.remove(0);
				matcher_special = pattern_special.matcher(target);
				//match Bold figure
				while (matcher_special.find()){
					int a,b;
					if (i<3 && j<12){
						a=i*2; b=j;
					} else if (i<3 && j>=12){
						a=i*2+1; b=j-12;
					} else if (i>=3 && j<12){
						a=i*2-6; b=j+12;
					} else {
						a=i*2-5; b=j;
					}
					PM25[a][b] = (matcher_special.group().substring(5, (matcher_special.group().length()-9)).toString());
					
//					System.out.print(PM25[i][j]+" ");
					special_flag = true;
					if (j==11) {
						newsHeadlines.remove(0);
					}
				}
				
				if (special_flag) continue;
				matcher = pattern.matcher(target);
				while (matcher.find()){
					int a,b;
					if (i<3 && j<12){
						a=i*2; b=j;
					} else if (i<3 && j>=12){
						a=i*2+1; b=j-12;
					} else if (i>=3 && j<12){
						a=i*2-6; b=j+12;
					} else {
						a=i*2-5; b=j;
					}
					PM25[a][b] = (matcher.group().substring(1, (matcher.group().length()-1)).toString());
					
//					System.out.print(PM25[i][j]+" ");
					if (j==11) {
						newsHeadlines.remove(0);
					}
				}
				
			}
//			System.out.println();
		}

		*/
		
/*		for(int i =0; i<6;i++){
			for (int j=0;j<24;j++){
				System.out.print(PM25[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
		//parse 3hour PSI
		for (int i=0; i<2; i++){
			newsHeadlines.remove(0);
			for(int j=0; j<12; j++){
				Boolean special_flag=false;
				String target = newsHeadlines.first().toString();
				newsHeadlines.remove(0);
				matcher_special = pattern_special.matcher(target);
				while (matcher_special.find()){
					ThreeHour_PSI[i][j] =(matcher_special.group().substring(5, (matcher_special.group().length()-9)).toString());
//					System.out.print(ThreeHour_PSI[i][j]+" ");
					special_flag = true;
				}
				if (special_flag) continue;
				matcher = pattern.matcher(target);
				while (matcher.find()){
					ThreeHour_PSI[i][j] =(matcher.group().substring(1, (matcher.group().length()-1)).toString());
//					System.out.print(ThreeHour_PSI[i][j]+" ");
				}
			}
//			System.out.println();
		}
		*/
		
//		System.out.println(newsHeadlines.toString());
	}
	
	public JSONObject getHazeBundle(){
		Date date = new Date();
		String timeStamp = new Timestamp(date.getTime()).toString();
		Integer hour = Integer.valueOf(timeStamp.substring(11,13));
		Integer level = 0;
		String [] classification = new String [6];
//		System.out.println(timeStamp);
//		System.out.println(hour);
		hazeJSON = new JSONObject();
		
		JSONArray hazeArray = new JSONArray();
		JSONObject hazeObject;
		
		for (int i=0;i<6;i++) {
			if (Integer.valueOf(PSI[0][hour-1].trim())<=50) {
				classification[i] = "Good";
				level = level>1?level:1;
			} else if (Integer.valueOf(PSI[0][hour-1].trim())<=100) {
				classification[i] = "Moderate";
				level = level>2?level:2;
			} else if (Integer.valueOf(PSI[0][hour-1].trim())<=200) {
				classification[i] = "Unhealthy";
				level = level>3?level:3;
			} else if (Integer.valueOf(PSI[0][hour-1].trim())<=300) {
				classification[i] = "Very unhealthy";
				level = level>4?level:4;
			}else {
				classification[i] = "Hazardous";
				level = level>5?level:5;
			}
		}
		
		hazeObject = new JSONObject();
		hazeObject.put("dateTimeReported",timeStamp)
		.put("psi",PSI[0][hour-1].trim() )
		.put("location", "North")
		.put("classification", classification[0]);
		hazeArray.add(hazeObject);
		
		hazeObject = new JSONObject();
		hazeObject.put("dateTimeReported",timeStamp)
		.put("psi",PSI[1][hour-1].trim() )
		.put("location", "South")
		.put("classification", classification[1]);
		hazeArray.add(hazeObject);
		
		hazeObject = new JSONObject();
		hazeObject.put("dateTimeReported",timeStamp)
		.put("psi",PSI[2][hour-1].trim() )
		.put("location", "East")
		.put("classification",classification[2] );
		hazeArray.add(hazeObject);
		
		hazeObject = new JSONObject();
		hazeObject.put("dateTimeReported",timeStamp)
		.put("psi",PSI[3][hour-1].trim() )
		.put("location", "West")
		.put("classification", classification[3]);
		hazeArray.add(hazeObject);
		
		hazeObject = new JSONObject();
		hazeObject.put("dateTimeReported",timeStamp)
		.put("psi",PSI[4][hour-1].trim() )
		.put("location", "Central")
		.put("classification", classification[4]);
		hazeArray.add(hazeObject);
		
		hazeObject = new JSONObject();
		hazeObject.put("dateTimeReported",timeStamp)
		.put("psi",PSI[5][hour-1].trim() )
		.put("location", "Overall")
		.put("classification", classification[5]);
		hazeArray.add(hazeObject);
		
		//put PSI
		
		hazeJSON.put("messageType", "eventMessage")
		.put("level", level.toString())
		.put("type", "haze")
		.put("hazeArray",hazeArray);
//		.put("North", PSI[0][hour-1].trim())
//		.put("South", PSI[1][hour-1].trim())
//		.put("East", PSI[2][hour-1].trim())
//		.put("West", PSI[3][hour-1].trim())
//		.put("Central", PSI[4][hour-1].trim())
//		.put("Overall", PSI[5][hour-1].trim()));

		/*//put PM2.5
		hazeJSON.put("PM25", new JSONObject()
		.put("North", PM25[0])
		.put("South", PM25[1])
		.put("East", PM25[2])
		.put("West", PM25[3])
		.put("Central", PM25[4])
		.put("Overall", PM25[5]));*/
		
		System.out.println("Set haze info at " + timeStamp);
		
		return hazeJSON;
	}

}
