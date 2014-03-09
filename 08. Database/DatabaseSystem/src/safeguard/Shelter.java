package safeguard;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the shelter database table.
 * 
 */
@Entity
@NamedQuery(name="Shelter.findAll", query="SELECT s FROM Shelter s")
public class Shelter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="shelterid")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="shelter_shelterid_seq")
	@javax.persistence.SequenceGenerator(name="shelter_shelterid_seq", sequenceName = "shelter_shelterid_seq")
	private Integer shelterid;

	private String address;

	private String contact;

	private String postal;

	public Shelter() {
	}
	
	public Shelter(String address, String contact, String postal) {
		this.address =address;
		this.contact = contact;
		this.postal = postal;
	}

	public Integer getShelterid() {
		return this.shelterid;
	}

	public void setShelterid(Integer shelterid) {
		this.shelterid = shelterid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

}