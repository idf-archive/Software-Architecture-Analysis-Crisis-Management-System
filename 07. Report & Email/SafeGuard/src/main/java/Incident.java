/* Author: Teo Kok Hien
 * Last Edit: 4 November 2013
 * 
 * Incident Class
 */

import java.util.Date;

public class Incident {
	
	private String typeName;
	private String levelReported;
	private Date dateTimeReported;
	

	public Incident(String typeName, String levelReported, Date dateTimeReported){
		this.typeName = typeName;
		this.levelReported = levelReported;
		this.dateTimeReported = dateTimeReported;
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
}
