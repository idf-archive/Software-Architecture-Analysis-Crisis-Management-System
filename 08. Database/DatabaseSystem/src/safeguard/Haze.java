package safeguard;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the haze database table.
 * 
 */
@Entity
@NamedQuery(name="Haze.findAll", query="SELECT h FROM Haze h")
public class Haze implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="hazeid")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="haze_hazeid_seq")
	@javax.persistence.SequenceGenerator(name="haze_hazeid_seq", sequenceName = "haze_hazeid_seq")
	private Integer hazeid;

	private Timestamp datetimereported;

	private String psi;

	//bi-directional many-to-one association to Hazetype
	private String classification;
	
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Haze() {
	}

	public Haze(String psi, Timestamp dateTimeReported, String location, String classification) {
		this.datetimereported = dateTimeReported;
		this.psi = psi;
		this.classification = classification;
		this.location = location;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public Integer getHazeid() {
		return this.hazeid;
	}

	public void setHazeid(Integer hazeid) {
		this.hazeid = hazeid;
	}

	public Timestamp getDatetimereported() {
		return this.datetimereported;
	}

	public void setDatetimereported(Timestamp datetimereported) {
		this.datetimereported = datetimereported;
	}

	public String getPsi() {
		return this.psi;
	}

	public void setPsi(String psi) {
		this.psi = psi;
	}



}