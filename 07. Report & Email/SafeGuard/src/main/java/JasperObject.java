/* Author: Teo Kok Hien
 * Last Edit: 9 November 2013
 * 
 * JasperObject for data population of the JRXML file.
 * This class adds a layer of abstraction since different emergency situations would have different attributes
 * and this class unifies all the different emergency situations.
 * Any changes made to Haze or Incident would require changes to JasperObject too.
 */

import java.util.Date;

public class JasperObject {
	
	private String typeName;
	private String levelReported;
	private Date dateTimeReported;
	private String location;
	

	public JasperObject(String typeName, String levelReported, Date dateTimeReported, String location){
		this.typeName = typeName;
		this.levelReported = levelReported;
		this.dateTimeReported = dateTimeReported;
		this.location = location;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLevelReported() {
		return levelReported;
	}

	public void setLevelReported(String levelReported) {
		this.levelReported = levelReported;
	}

	public Date getDateTimeReported() {
		return dateTimeReported;
	}

	public void setDateTimeReported(Date dateTimeReported) {
		this.dateTimeReported = dateTimeReported;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
