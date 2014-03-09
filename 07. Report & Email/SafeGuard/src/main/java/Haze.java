/* Author: Teo Kok Hien
 * Last Edit: 4 November 2013
 * 
 * Haze Class
 */

import java.util.Date;

public class Haze {
	
	private String typeName;
	private String levelReported;
	private Date dateTimeReported;
	private String location;

	public Haze(String typeName, String PSI, Date dateTimeReported, String location){
		this.typeName = typeName;
		this.levelReported = PSI;
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

	public void setLevelReported(String PSI) {
		this.levelReported = PSI;
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