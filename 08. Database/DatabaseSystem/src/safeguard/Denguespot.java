package safeguard;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the denguespot database table.
 * 
 */
@Entity
@NamedQuery(name="Denguespot.findAll", query="SELECT d FROM Denguespot d")
public class Denguespot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="denguespotid")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="denguespot_denguespotid_seq")
	@javax.persistence.SequenceGenerator(name="denguespot_denguespotid_seq", sequenceName = "denguespot_denguespotid_seq")
	private Integer denguespotid;

	private String address;

	private Integer count;

	private String postal;

	public Denguespot() {
	}

	public Denguespot(String address, Integer count, String postal) {
		this.address = address;
		this.count = count;
		this.postal = postal;
	}

	public Integer getDenguespotid() {
		return this.denguespotid;
	}

	public void setDenguespotid(Integer denguespotid) {
		this.denguespotid = denguespotid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

}