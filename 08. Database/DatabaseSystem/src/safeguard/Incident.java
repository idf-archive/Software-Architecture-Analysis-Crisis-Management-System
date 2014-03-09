package safeguard;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the incident database table.
 * 
 */
@Entity
@NamedQuery(name="Incident.findAll", query="SELECT i FROM Incident i")
public class Incident implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="incidentid")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incident_incidentid_seq")
	@javax.persistence.SequenceGenerator(name="incident_incidentid_seq", sequenceName = "incident_incidentid_seq")
	private Integer incidentid;

	private String address;

	private String callername;

	private Timestamp datetimereported;

	private String description;

	private Boolean display;

	private Integer levelreported;

	private String phone;

	private String postal;
	
	private String operator;

	

	//bi-directional many-to-one association to Incidenttype
	@ManyToOne
	private Incidenttype incidenttype;

	public Incident() {
	}
	
	public Incident(String callerName, String phone, String address, String postal, String description, int levelReported, Timestamp dateTimeReported, Incidenttype incidentType, boolean display, String operator) {
		this.address = address;
		this.callername = callerName;
		this.datetimereported = dateTimeReported;
		this.description = description;
		this.levelreported = levelReported;
		this.phone = phone;
		this.postal = postal;
		this.incidenttype = incidentType;
		this.display = display;
		this.operator = operator;
	}

	public Integer getIncidentid() {
		return this.incidentid;
	}

	public void setIncidentid(Integer incidentid) {
		this.incidentid = incidentid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCallername() {
		return this.callername;
	}

	public void setCallername(String callername) {
		this.callername = callername;
	}

	public Timestamp getDatetimereported() {
		return this.datetimereported;
	}

	public void setDatetimereported(Timestamp datetimereported) {
		this.datetimereported = datetimereported;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDisplay() {
		return this.display;
	}

	public void setDisplay(Boolean display) {
		this.display = display;
	}

	public Integer getLevelreported() {
		return this.levelreported;
	}

	public void setLevelreported(Integer levelreported) {
		this.levelreported = levelreported;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public Incidenttype getIncidenttype() {
		return this.incidenttype;
	}

	public void setIncidenttype(Incidenttype incidenttype) {
		this.incidenttype = incidenttype;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}